package extintores_api.demo.dto

data class CategoriaView (
    val id: Int,
    var descricao: String,
    var unidade: String,
    var periodo_inspecao: Int,
    var periodo_validade: Int,
)