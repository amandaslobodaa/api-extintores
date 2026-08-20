package extintores_api.demo.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import extintores_api.demo.model.ExtintorCategoria
import extintores_api.demo.model.ExtintorLocalizacao
import extintores_api.demo.model.SituacaoExtintor
import java.time.LocalDate

data class NovoExtintor (
    @field: NotNull (message = "O tipo não pode ser vazio") var tipo: String,
    @field: NotNull (message = "A carga total é obrigatória") var carga_total: Double,
    @field: NotNull(message = "A carga de vencimento é obrigatória") var carga_vencimento: LocalDate,
    @field: NotNull(message = "A data da próxima inspeção é obrigatória") var data_prox_inspecao: LocalDate,
    @field: NotNull (message = "O id da localização é obrigatório") var localizacao: String,
    @field: NotNull(message = "A situação do extintor é obrigatória") var situacao_extintor: SituacaoExtintor,
    @field: NotBlank(message = "O centro custo é obrigatório") var centro_custo: String
)