package extintores_api.demo.service

import extintores_api.demo.dto.ItemView
import extintores_api.demo.dto.ItemViewMapper
import extintores_api.demo.dto.NovoItem
import extintores_api.demo.exception.NotFoundException
import extintores_api.demo.mapper.ItemFormMapper
import extintores_api.demo.model.ExtintorMovimentoItem
import org.springframework.cache.annotation.CacheEvict
import org.springframework.stereotype.Service
import extintores_api.demo.repository.ExtintorMovimentoItemRepository

@Service
class MovimentoItemService (
    private val repository: ExtintorMovimentoItemRepository,
    private val item_form_mapper: ItemFormMapper,
    private val item_view_mapper: ItemViewMapper,
) {
    fun buscarMovItem(id: Int): ItemView {
        val item = repository.findById(id).orElseThrow { NotFoundException("Movimentação do item não encontrada") }
        return item_view_mapper.map(item)
    }

    fun listarMovItem(): List<ExtintorMovimentoItem> {
        return repository.findAll()
    }
    @CacheEvict(cacheNames = ["Itens"], allEntries = true)
    fun adicionarMovItem(e: NovoItem): ItemView {
        val item = item_form_mapper.map(e)
        val itemCadastrado = repository.save(item)
        return item_view_mapper.map(itemCadastrado)
    }

    @CacheEvict(cacheNames = ["Itens"], allEntries = true)
    fun atualizarMovItem(id: Int, e: NovoItem): ItemView {
        val item = repository.findById(id).orElseThrow { NotFoundException("Movimentação do item não encontrada") }
        val novoItem = item_form_mapper.map(e)
        item.movimento = novoItem.movimento
        item.extintor = novoItem.extintor
        item.destino = novoItem.destino
        item.tipo_mov_item = novoItem.tipo_mov_item
        item.conferido = novoItem.conferido
        item.tipo_retorno = novoItem.tipo_retorno
        item.carga_vencimento = novoItem.carga_vencimento
        item.data_prox_inspecao = novoItem.data_prox_inspecao
        item.numero_substituto = novoItem.numero_substituto
        val itemAtualizado = repository.save(item)
        return item_view_mapper.map(itemAtualizado)
    }

    @CacheEvict(cacheNames = ["Itens"], allEntries = true)
    fun deletarMovItem(id: Int) {
        val item = repository.findById(id).orElseThrow { NotFoundException("Movimentação do item não encontrada") }
        repository.delete(item)
    }

}