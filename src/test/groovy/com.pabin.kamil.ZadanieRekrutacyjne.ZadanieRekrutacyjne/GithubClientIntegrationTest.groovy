package com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.junit.WireMockClassRule
import org.junit.ClassRule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.client.HttpClientErrorException
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
        wireMockRule.
                stubFor(WireMock.get(
                        WireMock.urlEqualTo("/users/allegro/repos?sort=pushed"))
                        .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody('[{"name":"ralph","full_name":"allegro/ralph","pushed_at":"2018-01-12T14:18:15Z"}]')))

        when:

        def result = clientService.getLastEdditedRepository("allegro").name

        then:
        result == "ralph"

    }

    def "should throw HttpClientNotFoundException"() {
        given:
        wireMockRule.
                stubFor(WireMock.get(
                        WireMock.urlEqualTo("/users/not_a_real_user213/repos?sort=pushed"))
                        .willReturn(WireMock.aResponse()
                        .withStatus(404)
                        .withHeader("Content-Type", "application/json")
                        .withBody('{"message":"Not Found","documentation_url":"https://developer.github.com/v3/repos/#list-user-repositories"}')))

        when:

        def result = clientService.getLastEdditedRepository("not_a_real_user213")

        then:
        thrown(HttpClientErrorException)
    }

    def "should throw EmptyRepositoryException"() {
        given:
        wireMockRule
                .stubFor(WireMock.get(
                WireMock.urlEqualTo("/users/kamilPabin/repos?sort=pushed"))
                .willReturn(WireMock.aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("[]")))

        when:
        def result = clientService.getLastEdditedRepository("kamilPabin")

        then:
        thrown(EmptyRepositoryException)
    }
}
