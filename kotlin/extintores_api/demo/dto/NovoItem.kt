package extintores_api.demo.dto

import extintores_api.demo.model.Conferido
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import extintores_api.demo.model.ExtintorLocalizacao
import extintores_api.demo.model.ExtintorMovimento
import extintores_api.demo.model.Extintores
import extintores_api.demo.model.TipoMovimentoItem
import extintores_api.demo.model.TipoRetorno
import java.time.LocalDate

data class NovoItem(
    @field: NotBlank(message = "A identificação do movimento é obrigatória") var movimento: String,
    @field: NotBlank(message = "A identificação do extintor é obrigatória") var extintor: String,
    @field: NotNull(message = "O destino é obrigatório") var destino: String,
    @field: NotNull(message = "O tipo do movimento é obrigatório") var tipo_mov_item: TipoMovimentoItem,
    @field: NotNull(message = "A conferencia é obrigatória") var conferido: Conferido,
    @field: NotNull(message = "O tipo de retorno é obrigatório") var tipo_retorno: TipoRetorno,
    @field: NotNull(message = "A carga de vencimento é obrigatória") var carga_vencimento: LocalDate,
    @field: NotNull(message = "A data da próxima inspeção é obrigatória") var data_prox_inspecao: LocalDate,
    @field: NotBlank(message = "O número substituto é obrigatório") var numero_substituto: String
)