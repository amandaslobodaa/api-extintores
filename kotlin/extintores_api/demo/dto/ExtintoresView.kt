package extintores_api.demo.dto

import extintores_api.demo.model.ExtintorCategoria
import extintores_api.demo.model.ExtintorLocalizacao
import extintores_api.demo.model.SituacaoExtintor
import java.time.LocalDate

data class ExtintoresView (
    val numero: Int,
    var tipo: ExtintorCategoria,
    var carga_total: Double,
    var carga_vencimento: LocalDate? = null,
    var data_prox_inspecao: LocalDate? = null,
    var localizacao: ExtintorLocalizacao,
    val situacao_extintor: SituacaoExtintor,
    var centro_custo: String
)