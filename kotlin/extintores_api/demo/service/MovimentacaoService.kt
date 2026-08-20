package extintores_api.demo.service

import extintores_api.demo.dto.ItemView
import extintores_api.demo.dto.LocalizacaoView
import extintores_api.demo.dto.MovimentoView
import extintores_api.demo.dto.NovaLocalizacao
import extintores_api.demo.dto.NovoItem
import extintores_api.demo.dto.NovoMovimento
import extintores_api.demo.exception.NotFoundException
import extintores_api.demo.mapper.MovimentoFormMapper
import extintores_api.demo.mapper.MovimentoView_Mapper
import extintores_api.demo.model.ExtintorMovimento
import extintores_api.demo.repository.EmpresaRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.stereotype.Service
import extintores_api.demo.repository.ExtintorMovimentoRepository

@Service
class MovimentacaoService (
    private val repository: ExtintorMovimentoRepository,
    private val movimento_form_mapper: MovimentoFormMapper,
    private val movimento_view_mapper: MovimentoView_Mapper,
    private val empresaRepository: EmpresaRepository
) {
    fun buscarMovimentacao(id: Int): MovimentoView {
        val movimento = repository.findById(id).orElseThrow { NotFoundException("Movimentação não encontrada") }
        return movimento_view_mapper.map(movimento)
    }

    fun listarMovimentacoes(): List<ExtintorMovimento> {
        return repository.findAll()
    }

    @CacheEvict(cacheNames = ["Movimentacoes"], allEntries = true)
    fun adicionarMovimentacao(nova: NovoMovimento): MovimentoView {
        val movimento = movimento_form_mapper.map(nova)
        val movimento_cadastrado = repository.save(movimento)
        return movimento_view_mapper.map(movimento_cadastrado)
    }

    @CacheEvict(cacheNames = ["Movimentacoes"], allEntries = true)
    fun atualizarMovimentacao(id: Int, nova: NovoMovimento): MovimentoView {
        val movimento = repository.findById(id).orElseThrow { NotFoundException("Movimentação não encontrada") }
       val empresa = empresaRepository.findById(nova.empresa).orElseThrow { NotFoundException ("Empresa não encontrada")}
        val empresa_destino = empresaRepository.findById(nova.empresa_destino).orElseThrow { NotFoundException ("Empresa não encontrada") }
        val novoMovimento = movimento_form_mapper.map(nova)
        movimento.empresa = novoMovimento.empresa
        movimento.data = novoMovimento.data
        movimento.tipo_movimento = novoMovimento.tipo_movimento
        movimento.empresa_destino = novoMovimento.empresa_destino
        val movimento_atualizado = repository.save(movimento)
        return movimento_view_mapper.map(movimento_atualizado)
    }

    @CacheEvict(cacheNames = ["Movimentacoes"], allEntries = true)
    fun deletarMovimentacao(id: Int) {
        val movimento = repository.findById(id).orElseThrow { NotFoundException("Movimentação não encontrada") }
        repository.delete(movimento)
    }
}

