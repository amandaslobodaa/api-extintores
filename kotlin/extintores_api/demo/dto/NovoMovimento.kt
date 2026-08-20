package extintores_api.demo.dto

import jakarta.validation.constraints.NotNull
import extintores_api.demo.model.Empresa
import extintores_api.demo.model.TipoMovimento
import java.time.LocalDate

data class NovoMovimento (
    @field: NotNull(message = "A empresa não pode estar em branco") var empresa: Int,
    @field: NotNull(message="A data não pode estar em branco") var data: LocalDate,
    @field: NotNull(message = "O tipo do movimento não pode estar em branco") var tipo_movimento: TipoMovimento,
    @field: NotNull(message = "O destino não pode estar em branco") var empresa_destino: Int
)