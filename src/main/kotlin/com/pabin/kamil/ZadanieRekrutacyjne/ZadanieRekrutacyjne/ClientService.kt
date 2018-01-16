package com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne

import org.springframework.stereotype.Service

@Service
class ClientService(private val githubClient: GithubClient) {

    fun getLastEdditedRepository(name: String) = githubClient.getLastEdditedRepository(name)

}