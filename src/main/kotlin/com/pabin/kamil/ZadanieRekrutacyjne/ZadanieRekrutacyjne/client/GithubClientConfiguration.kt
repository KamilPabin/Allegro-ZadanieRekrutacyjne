package com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne.client

import org.apache.http.client.HttpClient
import org.apache.http.impl.client.HttpClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate


@Configuration
class GithubClientConfiguration {

    @Bean
    fun githubRestTemplate(@Value("\${githubClient.connectTimeout}") connectTimeout: Int,
                           @Value("\${githubClient.connectionRequestTimeout}") connectionRequestTimeout: Int,
                           @Value("\${githubClient.readTimeout}") readTimeout: Int): RestTemplate {
        return RestTemplate(httpFactory(connectTimeout,
                connectionRequestTimeout,
                readTimeout))
    }

    private fun httpFactory(connectTimeout: Int,
                            connectionRequestTimeout: Int,
                            readTimeout: Int): HttpComponentsClientHttpRequestFactory {
        val requestConfig = HttpComponentsClientHttpRequestFactory()
        requestConfig.setConnectTimeout(connectTimeout)
        requestConfig.setConnectionRequestTimeout(connectionRequestTimeout)
        requestConfig.setReadTimeout(readTimeout)
        requestConfig.httpClient = httpClient()
        return requestConfig

        }


    private fun httpClient(): HttpClient {
        return HttpClientBuilder.create()
                .setMaxConnTotal(10)
                .setMaxConnPerRoute(5)
                .build()
    }
}

