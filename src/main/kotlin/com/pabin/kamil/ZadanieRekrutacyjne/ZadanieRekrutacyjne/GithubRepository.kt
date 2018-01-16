package com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*


@JsonIgnoreProperties(ignoreUnknown = true)
data class GithubRepository(@JsonProperty("name") val name: String,
                            @JsonProperty("full_name") val fullName: String,
                            @JsonProperty("pushed_at") val pushedAt: Date)