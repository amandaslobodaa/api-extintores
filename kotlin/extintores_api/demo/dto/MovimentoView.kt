package extintores_api.demo.dto

import extintores_api.demo.model.Empresa
import extintores_api.demo.model.TipoMovimento
import java.time.LocalDate

data class MovimentoView(
    var id: Int,
    var empresa: Empresa,
    var data: LocalDate,
    var tipoMovimento: TipoMovimento,
    var empresa_destino: Empresa
)