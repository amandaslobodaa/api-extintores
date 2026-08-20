package extintores_api.demo.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import extintores_api.demo.model.Empresa
import extintores_api.demo.model.TipoLocalizacao

data class NovaLocalizacao(
    @field:NotNull(message = "A empresa não pode estar em branco")
    var empresa: Int,
    @field:NotBlank(message = "A descrição não pode estar em branco")
    var descricao: String,
    @field:NotBlank(message = "O centro custo não pode estar em branco")
    var centro_custo: String,
    @field:NotNull(message = "O tipo da localização não pode estar em branco")
    var tipo_localizacao: TipoLocalizacao
)
