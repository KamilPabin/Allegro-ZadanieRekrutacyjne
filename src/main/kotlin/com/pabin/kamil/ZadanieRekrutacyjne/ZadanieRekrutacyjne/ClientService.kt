package com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne

import com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne.client.GithubClient
import com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne.client.GithubRepository
import org.springframework.stereotype.Service

@Service
class ClientService(private val githubClient: GithubClient) {

    fun getLastEdditedRepository(name: String): GithubRepository {
        val allRepositories = githubClient.getRepositoriesSortedByPushedDate(name)
        if(allRepositories.isEmpty())
            throw NoRepositoriesFoundException()
        System.out.print(allRepositories.size)
        return allRepositories.first()

    }

}

class NoRepositoriesFoundException : RuntimeException("User has no repositories!")