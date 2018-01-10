package com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne

interface GithubClient {

    fun getLastEdditedRepo(user : String) : GithubRepository

}