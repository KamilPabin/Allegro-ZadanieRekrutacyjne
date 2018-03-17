package com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne.client

import com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne.ClientService
import com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne.NoRepositoriesFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class GithubClientEndpoint(private val client: ClientService) {

    @GetMapping("/users/{user}/lastEdit")
    fun getLastEditedRepository(@PathVariable user: String):
            ResponseEntity<GithubRepository> {
        val repository = client.getLastEditedRepository(user)
        return ResponseEntity(repository, HttpStatus.OK)
    }

    @ExceptionHandler(NoRepositoriesFoundException::class)
    fun handleException(emptyRepositoryException: NoRepositoriesFoundException) =
            ResponseEntity(emptyRepositoryException.message, HttpStatus.NO_CONTENT)

    @ExceptionHandler(UserNotFoundException::class)
    fun handleException(userNotFoundException: UserNotFoundException) =
            ResponseEntity(userNotFoundException.message, HttpStatus.NOT_FOUND)

}