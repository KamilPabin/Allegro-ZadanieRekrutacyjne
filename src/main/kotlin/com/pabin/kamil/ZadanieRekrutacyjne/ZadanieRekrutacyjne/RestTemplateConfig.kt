package com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate


@Configuration
class RestTemplateConfig {

    @Bean
    fun githubRestTemplate(@Value("\${githubClient.connectTimeout}") connectTimeout: Int,
                           @Value("\${githubClient.connectionRequestTimeout}") connectionRequestTimeout: Int,
                           @Value("\${githubClient.readTimeout}") readTimeout: Int): RestTemplate {
        return RestTemplate(getHttpConfig(connectTimeout,
                connectionRequestTimeout,
                readTimeout))
    }


    private fun getHttpConfig(connectTimeout: Int,
                              connectionRequestTimeout: Int,
                              readTimeout: Int): HttpComponentsClientHttpRequestFactory {

        val requestConfig = HttpComponentsClientHttpRequestFactory()
        requestConfig.setConnectTimeout(connectTimeout)
        requestConfig.setConnectionRequestTimeout(connectionRequestTimeout)
        requestConfig.setReadTimeout(readTimeout)
        return requestConfig
    }

}