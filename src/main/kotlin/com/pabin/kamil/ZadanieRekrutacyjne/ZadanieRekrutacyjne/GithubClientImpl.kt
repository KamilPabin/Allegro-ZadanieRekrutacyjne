package com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne

import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
class GithubClientImpl(private val githubRestTemplate: RestTemplate) : GithubClient {

    val githubURL = "https://api.github.com"

    override fun getLastEdditedRepo(user: String) : GithubRepository {
        val response = githubRestTemplate.getForEntity("$githubURL/users/$user/repos?sort=pushed",
                Array<GithubRepository>::class.java)
        val allRepositories : List<GithubRepository> = response.body.asList()
        return allRepositories[0]
    }


}