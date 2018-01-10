package com.pabin.kamil.ZadanieRekrutacyjne.ZadanieRekrutacyjne

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.*


@JsonIgnoreProperties( ignoreUnknown = true)
data class GithubRepository(val name: String, val full_name : String, val pushed_at: Date)