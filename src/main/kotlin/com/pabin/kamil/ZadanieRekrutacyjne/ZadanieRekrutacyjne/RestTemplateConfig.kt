package com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate


@Configuration
class RestTemplateConfig {

    @Bean
    fun githubRestTemplate() : RestTemplate{
        return RestTemplate(getHttpConfig())
    }


    private fun getHttpConfig() : HttpComponentsClientHttpRequestFactory {
        val requestConfig = HttpComponentsClientHttpRequestFactory()
        requestConfig.setConnectTimeout(2000)
        requestConfig.setConnectionRequestTimeout(1000)
        requestConfig.setReadTimeout(2000)
        return requestConfig
    }

}