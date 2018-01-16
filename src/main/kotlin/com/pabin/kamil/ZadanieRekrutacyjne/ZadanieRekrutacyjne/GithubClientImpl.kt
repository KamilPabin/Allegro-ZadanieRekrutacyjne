package com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
class GithubClientImpl(private val githubRestTemplate: RestTemplate) : GithubClient {

    @Value("\${origins.github}") private val githubURL = ""

    override fun getLastEdditedRepository(user: String): GithubRepository {

        val response = githubRestTemplate.getForEntity("$githubURL/users/$user/repos?sort=pushed",
                Array<GithubRepository>::class.java)

        val allRepositories: List<GithubRepository> = response.body.asList()
        if (allRepositories.isEmpty())
            throw EmptyRepositoryException()
        return allRepositories[0]
    }
}

class EmptyRepositoryException : RuntimeException("User has no repositories!")
