package com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne

import com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne.client.GithubClient
import com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne.client.UserNotFoundException
import org.springframework.beans.factory.annotation.Autowired

class GithubClientIntegrationTest extends IntegrationTest {


    @Autowired
    GithubClient githubClient

    def "should return last eddited repository by allegro"() {
        given:
        stubGithubService(200,
                '[{"name":"ralph","full_name":"allegro/ralph","pushed_at":"2018-01-12T14:18:15Z"},' +
                        '{"name":"mesos-executor","full_name":"allegro/mesos-executor","pushed_at":"2017-01-12T14:18:15Z"},' +
                        '{"name":"hermes","full_name":"allegro/hermes","pushed_at":"2016-01-12T14:18:15Z"}]',
                "/users/allegro/repos?sort=pushed")

        when:
        def result = githubClient.getRepositoriesSortedByPushedDate("allegro")

        then:
        result.collect({ it.name }) == ["ralph", "mesos-executor", "hermes"]

    }

    def "should throw UserNotFoundException"() {
        given:
        stubGithubService(404, '{"message":"Not Found",' +
                '"documentation_url":"https://developer.github.com/v3/repos/#list-user-repositories"}',
                "/users/not_a_real_user213/repos?sort=pushed")
        when:
        githubClient.getRepositoriesSortedByPushedDate("not_a_real_user213")

        then:
        thrown(UserNotFoundException)
    }

}
