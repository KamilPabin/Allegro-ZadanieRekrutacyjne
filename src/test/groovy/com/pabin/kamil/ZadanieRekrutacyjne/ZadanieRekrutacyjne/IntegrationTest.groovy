package com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.junit.WireMockClassRule
import org.junit.ClassRule
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.client.RestTemplate
import spock.lang.Shared
import spock.lang.Specification


@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = ["integration"])
class IntegrationTest extends Specification{

    @LocalServerPort
    int port

    @Shared
    @ClassRule
    WireMockClassRule wireMockRule = new WireMockClassRule(8089)

    RestTemplate restTemplate

    def setup() {
        restTemplate = new RestTemplate()
    }

    def stubGithubService(int statusCode, String body, String URL) {
        return wireMockRule
                .stubFor(
                WireMock.get(
                        WireMock.urlEqualTo(URL))
                        .willReturn(WireMock.aResponse()
                        .withStatus(statusCode)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)))
    }

}
