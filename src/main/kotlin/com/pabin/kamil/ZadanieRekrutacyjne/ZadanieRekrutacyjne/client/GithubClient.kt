package com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne.client

interface GithubClient {

    fun getRepositoriesSortedByPushedDate(user: String): List<GithubRepository>

}