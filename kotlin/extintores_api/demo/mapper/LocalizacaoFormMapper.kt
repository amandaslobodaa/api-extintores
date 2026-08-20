package extintores_api.demo.mapper

import extintores_api.demo.dto.NovaLocalizacao
import extintores_api.demo.exception.NotFoundException
import extintores_api.demo.model.ExtintorLocalizacao
import extintores_api.demo.repository.EmpresaRepository
import org.springframework.stereotype.Component

@Component
class LocalizacaoFormMapper(
    private val empresaRepository: EmpresaRepository
) : Mapper<NovaLocalizacao, ExtintorLocalizacao> {

    override fun map(e: NovaLocalizacao): ExtintorLocalizacao {

        val empresa = empresaRepository.findById(e.empresa)
            .orElseThrow {
                NotFoundException("A empresa não foi encontrada")
            }
        return ExtintorLocalizacao(
            empresa = empresa,
            descricao = e.descricao,
            centro_custo = e.centro_custo,
            tipo_localizacao = e.tipo_localizacao
        )
    }
}