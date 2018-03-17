package com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne

import com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne.client.GithubClient
import com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne.client.GithubRepository
import org.springframework.stereotype.Service

@Service
class ClientService(private val githubClient: GithubClient) {

    fun getLastEditedRepository(user: String): GithubRepository {
        val allRepositories = githubClient.getRepositoriesSortedByPushedDate(user)
        if(allRepositories.isEmpty())
            throw NoRepositoriesFoundException()
        return allRepositories.first()

    }

}

class NoRepositoriesFoundException : RuntimeException("User has no repositories!")