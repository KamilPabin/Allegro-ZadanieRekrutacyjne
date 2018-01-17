package com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne

interface GithubClient {

    fun getRepositoriesSortedByPushedDate(user: String): List<GithubRepository>
}