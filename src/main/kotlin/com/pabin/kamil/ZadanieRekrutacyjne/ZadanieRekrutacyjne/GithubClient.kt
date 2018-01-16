package com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne

interface GithubClient {

    fun getLastEdditedRepository(user: String): GithubRepository

}