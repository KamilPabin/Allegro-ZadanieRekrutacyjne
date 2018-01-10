package com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class ApiEndpoint(private val client: ClientService) {


    @GetMapping("/lastEdditedRepository")
    fun getLastEdditedRepository() : ResponseEntity<GithubRepository> {
        return ResponseEntity(client.getLastEdditedRepo("allegro"),HttpStatus.OK)
    }

}