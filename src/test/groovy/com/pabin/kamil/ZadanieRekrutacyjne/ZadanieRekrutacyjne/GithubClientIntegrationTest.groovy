package com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.junit.WireMockClassRule
import org.junit.ClassRule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Shared
import spock.lang.Specification

@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = ["integration"])
class GithubClientIntegrationTest extends Specification {

    @Shared
    @ClassRule
    WireMockClassRule wireMockRule = new WireMockClassRule(8089)

    @Autowired
    ClientService clientService

    def "should return last eddited repository by allegro"() {
        given:
        stubGithubService(200,
                '[{"name":"ralph","full_name":"allegro/ralph","pushed_at":"2018-01-12T14:18:15Z"},' +
                        '{"name":"mesos-executor","full_name":"allegro/mesos-executor","pushed_at":"2017-01-12T14:18:15Z"},' +
                        '{"name":"hermes","full_name":"allegro/hermes","pushed_at":"2016-01-12T14:18:15Z"}]',
                "/users/allegro/repos?sort=pushed")
        when:
        def result = clientService.getLastEdditedRepository("allegro").name

        then:
        result == "ralph"

    }

    def "should throw UserNotFoundException"() {
        given:
        stubGithubService(404, '{"message":"Not Found",' +
                '"documentation_url":"https://developer.github.com/v3/repos/#list-user-repositories"}',
                "/users/not_a_real_user213/repos?sort=pushed")
        when:
        def result = clientService.getLastEdditedRepository("not_a_real_user213")

        then:
        thrown(UserNotFoundException)
    }

    def "should throw EmptyRepositoryException"() {
        given:
        stubGithubService(200, "[]",
                "/users/kamilPabin/repos?sort=pushed")

        when:
        def result = clientService.getLastEdditedRepository("kamilPabin")

        then:
        thrown(EmptyRepositoryException)
    }

    def stubGithubService(int statusCode, String body, String URL) {
        return wireMockRule
                .stubFor(
                WireMock.get(
                        WireMock.urlEqualTo(URL))
                        .willReturn(WireMock.aResponse()
                        .withStatus(statusCode)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)))
    }
}
