package extintores_api.demo.mapper

import extintores_api.demo.dto.MovimentoView
import extintores_api.demo.model.ExtintorMovimento
import org.springframework.stereotype.Component

@Component
class MovimentoView_Mapper(): Mapper<ExtintorMovimento, MovimentoView> {
    override fun map(e: ExtintorMovimento): MovimentoView {
        return MovimentoView(
            id = e.id!!,
            empresa = e.empresa!!,
            data = e.data!!,
            tipoMovimento = e.tipo_movimento!!,
            empresa_destino = e.empresa_destino!!
        )
    }
}