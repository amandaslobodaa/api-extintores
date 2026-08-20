package extintores_api.demo.mapper

import extintores_api.demo.dto.CategoriaView
import extintores_api.demo.model.ExtintorCategoria
import org.springframework.stereotype.Component

@Component
class CategoriaViewMapper():
    Mapper<ExtintorCategoria, CategoriaView> {
    override fun map(e: ExtintorCategoria): CategoriaView {
        return CategoriaView(
            id = e.id!!,
            descricao = e.descricao,
            unidade = e.unidade,
            periodo_inspecao = e.periodo_inspecao,
            periodo_validade = e.periodo_validade
        )
    }
}