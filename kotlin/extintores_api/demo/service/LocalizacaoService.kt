package extintores_api.demo.service

import extintores_api.demo.dto.LocalizacaoView
import extintores_api.demo.dto.NovaLocalizacao
import extintores_api.demo.exception.NotFoundException
import extintores_api.demo.mapper.LocalizacaoViewMapper
import extintores_api.demo.model.ExtintorLocalizacao
import extintores_api.demo.repository.EmpresaRepository
import extintores_api.demo.repository.ExtintorLocalizacaoRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.stereotype.Service

@Service
class LocalizacaoService(
    private val repository: ExtintorLocalizacaoRepository,
    private val empresaRepository: EmpresaRepository,
    private val localizacao_view_mapper: LocalizacaoViewMapper,
) {

    fun buscarLocalizacao(id: Int): LocalizacaoView {
        val localizacao = repository.findById(id)
            .orElseThrow { NotFoundException("A localização não existe") }
        return localizacao_view_mapper.map(localizacao)
    }

    fun listarLocalizacoes(): List<ExtintorLocalizacao> {
        return repository.findAll()
    }

    @CacheEvict(cacheNames = ["Localizacoes"], allEntries = true)
    fun adicionarLocalizacao(nova: NovaLocalizacao): LocalizacaoView {
        val empresa = empresaRepository.findById(nova.empresa)
            .orElseThrow { NotFoundException("A empresa não foi encontrada") }
        val localizacao = ExtintorLocalizacao(
            empresa = empresa,
            descricao = nova.descricao,
            centro_custo = nova.centro_custo,
            tipo_localizacao = nova.tipo_localizacao
        )
        val localizacao_cadastrada = repository.save(localizacao)
        return localizacao_view_mapper.map(localizacao_cadastrada)
    }

    @CacheEvict(cacheNames = ["Localizacoes"], allEntries = true)
    fun atualizarLocalizacao(id: Int, nova: NovaLocalizacao): LocalizacaoView {
        val localizacao = repository.findById(id)
            .orElseThrow { NotFoundException("A localização não foi encontrada") }
        val empresa = empresaRepository.findById(nova.empresa)
            .orElseThrow { NotFoundException("A empresa não foi encontrada") }
            localizacao.empresa = empresa
            localizacao.descricao = nova.descricao
            localizacao.centro_custo = nova.centro_custo
            localizacao.tipo_localizacao = nova.tipo_localizacao
        val localizacao_atualizada = repository.save(localizacao)
        return localizacao_view_mapper.map(localizacao_atualizada)
    }

    @CacheEvict(cacheNames = ["Localizacoes"], allEntries = true)
    fun deletarLocalizacao(id: Int) {
        val localizacao = repository.findById(id)
            .orElseThrow { NotFoundException("A localização não foi encontrada") }
        repository.delete(localizacao)
    }
}