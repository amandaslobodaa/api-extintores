package extintores_api.demo.dto

import extintores_api.demo.mapper.Mapper
import extintores_api.demo.model.ExtintorMovimentoItem
import org.springframework.stereotype.Component

@Component
class ItemViewMapper:
    Mapper<ExtintorMovimentoItem, ItemView> {
    override fun map(e: ExtintorMovimentoItem): ItemView {
        return ItemView(
            id = e.id,
            movimento = e.movimento,
            extintor = e.extintor,
            destino = e.destino!!,
            tipo_mov_item = e.tipo_mov_item,
            conferido = e.conferido,
            tipo_retorno = e.tipo_retorno,
            carga_vencimento = e.carga_vencimento,
            data_prox_inspecao = e.data_prox_inspecao,
            numero_substituto = e.numero_substituto
        )
    }
}