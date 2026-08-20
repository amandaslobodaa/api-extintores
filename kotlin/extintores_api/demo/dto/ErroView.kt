package extintores_api.demo.dto

import java.time.LocalDateTime

data class ErroView(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val message: String?,
    val path: String,
)