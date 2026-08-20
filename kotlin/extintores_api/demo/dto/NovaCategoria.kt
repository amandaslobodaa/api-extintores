package extintores_api.demo.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class NovaCategoria(
    @field:NotBlank(message = "A descrição é obrigatória") var descricao: String,
    @field: NotBlank(message = "A unidade é obrigatória") var unidade: String,
    @field:NotNull(message = "O período de inspeção é obrigatório") var periodo_inspecao: Int,
    @field: NotNull(message = "O período de validade é obrigatório") var periodo_validade: Int)
