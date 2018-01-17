package com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne

import org.springframework.stereotype.Service

@Service
class ClientService(private val githubClient: GithubClient) {

    fun getLastEdditedRepository(name: String): GithubRepository {

        val allRepositories = githubClient.getRepositoriesSortedByPushedDate(name)
        if (allRepositories.isEmpty())
            throw EmptyRepositoryException()
        return allRepositories.first()
    }
}

class EmptyRepositoryException : RuntimeException("User has no repositories!")