package extintores_api.demo.mapper

import extintores_api.demo.dto.NovoExtintor
import extintores_api.demo.dto.NovoItem
import extintores_api.demo.exception.NotFoundException
import extintores_api.demo.model.ExtintorMovimentoItem
import extintores_api.demo.model.Extintores
import extintores_api.demo.repository.ExtintorCategoriaRepository
import extintores_api.demo.repository.ExtintorLocalizacaoRepository
import extintores_api.demo.repository.ExtintorMovimentoRepository
import extintores_api.demo.repository.ExtintorRepository
import org.springframework.stereotype.Component

@Component
class ExtintorFormMapper(private val categoriaRepository: ExtintorCategoriaRepository,
    private val localizacaoRepository: ExtintorLocalizacaoRepository
    ): Mapper<NovoExtintor, Extintores> {
    override fun map(e: NovoExtintor): Extintores {
        val tipo = categoriaRepository.findById(e.tipo.toInt()).orElseThrow { NotFoundException ("A categoria não foi encontrada") }
        val localizacao = localizacaoRepository.findById(e.localizacao.toInt()).orElseThrow { NotFoundException ("A localização não foi encontrada") }
        return Extintores(
            numero = null,
            tipo = tipo,
            carga_total = e.carga_total,
            carga_vencimento = e.carga_vencimento,
            data_prox_inspecao = e.data_prox_inspecao,
            localizacao = localizacao,
            situacao_extintor = e.situacao_extintor,
            centro_custo = e.centro_custo
        )
    }
}

