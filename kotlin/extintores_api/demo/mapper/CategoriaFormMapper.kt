package extintores_api.demo.mapper

import extintores_api.demo.dto.NovaCategoria
import extintores_api.demo.model.ExtintorCategoria
import org.springframework.stereotype.Component

@Component
class CategoriaFormMapper():
    Mapper<NovaCategoria, ExtintorCategoria> {
    override fun map(e: NovaCategoria): ExtintorCategoria {
        return ExtintorCategoria(
            descricao = e.descricao,
            unidade = e.unidade,
            periodo_inspecao = e.periodo_inspecao,
            periodo_validade = e.periodo_validade
        )
    }
}