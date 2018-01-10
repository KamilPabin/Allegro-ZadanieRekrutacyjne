package com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne

import org.springframework.stereotype.Service

@Service
class ClientService(private val githubClient: GithubClient) {

    fun getLastEdditedRepo(name: String) = githubClient.getLastEdditedRepo(name)

}