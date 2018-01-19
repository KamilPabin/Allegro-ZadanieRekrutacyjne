package com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne

import com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne.client.GithubRepository
import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpClientErrorException

class GithubClientEndpointTest extends IntegrationTest {

    def "Should return last edited repository"() {
        given:
        stubGithubService(200,
                '[{"name":"ralph","full_name":"allegro/ralph","pushed_at":"2018-01-12T14:18:15Z"},' +
                        '{"name":"mesos-executor","full_name":"allegro/mesos-executor","pushed_at":"2017-01-12T14:18:15Z"},' +
                        '{"name":"hermes","full_name":"allegro/hermes","pushed_at":"2016-01-12T14:18:15Z"}]',
                "/users/allegro/repos?sort=pushed")

        when:
        def response = restTemplate.getForEntity("http://localhost:$port/users/allegro/lastEdit", GithubRepository)

        then:
        with(response.body) {
            name == "ralph"
            fullName == "allegro/ralph"
        }
    }

    def "should return 404 status code with message 'User with this name does not exist"() {
        given:
        stubGithubService(404, '{"message":"Not Found",' +
                '"documentation_url":"https://developer.github.com/v3/repos/#list-user-repositories"}',
                "/users/allegro/repos?sort=pushed")

        when:
        def response = restTemplate.getForEntity("http://localhost:$port/users/allegro/lastEdit", GithubRepository)

        then:
        def exception = thrown(HttpClientErrorException)
        exception.statusCode == HttpStatus.NOT_FOUND
    }

    def "Should return empty JSON"() {
        given:
        stubGithubService(200,
                '[]',
                "/users/allegro/repos?sort=pushed")

        when:
        def result = restTemplate.getForEntity("http://localhost:$port/users/allegro/lastEdit", GithubRepository)

        then:
        result.statusCode == HttpStatus.NO_CONTENT
    }

}
