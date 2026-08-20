package extintores_api.demo.dto

import extintores_api.demo.model.Conferido
import extintores_api.demo.model.ExtintorLocalizacao
import extintores_api.demo.model.ExtintorMovimento
import extintores_api.demo.model.Extintores
import extintores_api.demo.model.TipoMovimentoItem
import extintores_api.demo.model.TipoRetorno
import java.time.LocalDate

data class ItemView (
    var id: Int? = null,
    var movimento: ExtintorMovimento? = null,
    var extintor: Extintores? = null,
    var destino: ExtintorLocalizacao? = null,
    var tipo_mov_item: TipoMovimentoItem? = null,
    var conferido: Conferido,
    var tipo_retorno: TipoRetorno? = null,
    var carga_vencimento: LocalDate? = null,
    var data_prox_inspecao: LocalDate? = null,
    var numero_substituto: String = ""
)