package com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate


@Service
class GithubClientImpl(private val githubRestTemplate: RestTemplate,
                       @Value("\${githubClient.url}") private val githubURL: String) : GithubClient {

    override fun getRepositoriesSortedByPushedDate(user: String): List<GithubRepository> {
        try {
            val response = githubRestTemplate.getForEntity("$githubURL/users/$user/repos?sort=pushed",
                    Array<GithubRepository>::class.java)
            return response.body.asList()
        } catch (httpClientErrorException: HttpClientErrorException) {
            throw UserNotFoundException()
        }
    }
}

class UserNotFoundException : RuntimeException("User with this name does not exist!")

