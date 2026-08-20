package extintores_api.demo.mapper

import extintores_api.demo.dto.NovoItem
import extintores_api.demo.exception.NotFoundException
import extintores_api.demo.model.ExtintorMovimentoItem
import extintores_api.demo.repository.ExtintorLocalizacaoRepository
import extintores_api.demo.repository.ExtintorMovimentoRepository
import extintores_api.demo.repository.ExtintorRepository
import org.springframework.stereotype.Component

@Component
class ItemFormMapper(
    private val movimentoRepository: ExtintorMovimentoRepository,
    private val extintorRepository: ExtintorRepository,
    private val localizacaoRepository: ExtintorLocalizacaoRepository
) : Mapper<NovoItem, ExtintorMovimentoItem> {

    override fun map(e: NovoItem): ExtintorMovimentoItem {

        val movimento = movimentoRepository.findById(e.movimento.toInt()).orElseThrow {
                NotFoundException("O movimento não foi encontrado")
            }

        val extintor = extintorRepository.findById(e.extintor.toInt()).orElseThrow {
                NotFoundException("O extintor não foi encontrado")
            }

        val localizacao = localizacaoRepository.findById(e.destino.toInt()).orElseThrow {
                NotFoundException("A localização não foi encontrada")
            }

        return ExtintorMovimentoItem(
            movimento = movimento,
            extintor = extintor,
            destino = localizacao,
            tipo_mov_item = e.tipo_mov_item,
            conferido = e.conferido,
            tipo_retorno = e.tipo_retorno,
            carga_vencimento = e.carga_vencimento,
            data_prox_inspecao = e.data_prox_inspecao,
            numero_substituto = e.numero_substituto
        )
    }
}

