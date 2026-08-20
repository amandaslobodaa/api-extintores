package extintores_api.demo.dto

import extintores_api.demo.model.Empresa
import extintores_api.demo.model.TipoLocalizacao

data class LocalizacaoView (
    val id: Int,
    var empresa: Empresa,
    var descricao: String,
    var centro_custo: String,
    var tipo_localizacao: TipoLocalizacao
)