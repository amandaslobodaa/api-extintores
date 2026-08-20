package extintores_api.demo.mapper

import extintores_api.demo.dto.ExtintoresView
import extintores_api.demo.model.Extintores
import org.springframework.stereotype.Component

@Component
class ExtintorViewMapper:
    Mapper<Extintores, ExtintoresView> {
    override fun map(e: Extintores): ExtintoresView {
        return ExtintoresView(
            numero = e.numero!!,
            tipo = e.tipo!!,
            carga_total = e.carga_total!!,
            carga_vencimento = e.carga_vencimento,
            data_prox_inspecao = e.data_prox_inspecao,
            localizacao = e.localizacao!!,
            situacao_extintor = e.situacao_extintor!!,
            centro_custo = e.centro_custo
        )
    }
}