package extintores_api.demo.dto

import jakarta.validation.constraints.NotBlank

data class NovaEmpresa (
    var codigo: Int? = null,
    @field: NotBlank(message = "A descrição não pode estar em branco") var descricao: String,
    var senha: String
)