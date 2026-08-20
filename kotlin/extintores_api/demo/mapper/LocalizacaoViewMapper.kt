package extintores_api.demo.mapper

import extintores_api.demo.dto.LocalizacaoView
import extintores_api.demo.model.ExtintorLocalizacao
import org.springframework.stereotype.Component

@Component
class LocalizacaoViewMapper:
    Mapper<ExtintorLocalizacao, LocalizacaoView> {
    override fun map(e: ExtintorLocalizacao): LocalizacaoView {
        return LocalizacaoView(
            id = e.id!!,
            empresa = e.empresa!!,
            descricao = e.descricao,
            centro_custo = e.centro_custo,
            tipo_localizacao = e.tipo_localizacao!!
        )
    }
}