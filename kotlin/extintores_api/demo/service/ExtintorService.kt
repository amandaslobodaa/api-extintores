package extintores_api.demo.service

import extintores_api.demo.dto.ExtintoresView
import extintores_api.demo.dto.ItemView
import extintores_api.demo.dto.NovoExtintor
import extintores_api.demo.dto.NovoItem
import extintores_api.demo.exception.NotFoundException
import extintores_api.demo.mapper.ExtintorFormMapper
import extintores_api.demo.mapper.ExtintorViewMapper
import extintores_api.demo.model.Extintores
import org.springframework.cache.annotation.CacheEvict
import org.springframework.stereotype.Service
import extintores_api.demo.repository.ExtintorRepository

@Service
class ExtintorService (
    private val repository: ExtintorRepository,
    private val extintorFormMapper: ExtintorFormMapper,
    private val extintorViewMapper: ExtintorViewMapper,
) {
    fun buscarNumeroExtintor(numero: Int): ExtintoresView {
        val extintor = repository.findById(numero).orElseThrow { NotFoundException("Extintor não encontrado") }
        return extintorViewMapper.map(extintor)
    }

    fun listarExtintores(): List<Extintores> {
        return repository.findAll()
    }

    @CacheEvict(cacheNames = ["Extintores"], allEntries = true)
    fun adicionarExtintor(form: NovoExtintor): ExtintoresView {
        val extintor = extintorFormMapper.map(form)
        val extintorCadastrado = repository.save(extintor)
        return extintorViewMapper.map(extintorCadastrado)
    }

    @CacheEvict(cacheNames = ["Extintores"], allEntries = true)
    fun atualizarExtintor(numero: Int, form: NovoExtintor): ExtintoresView {
        val extintor = repository.findById(numero).orElseThrow { NotFoundException("Extintor não encontrado") }
        val novoExtintor = extintorFormMapper.map(form)
        extintor.situacao_extintor = novoExtintor.situacao_extintor
        extintor.tipo = novoExtintor.tipo
        extintor.carga_total = novoExtintor.carga_total
        extintor.localizacao = novoExtintor.localizacao
        extintor.carga_vencimento = novoExtintor.carga_vencimento
        extintor.centro_custo = novoExtintor.centro_custo
        val extintor_atualizado = repository.save(extintor)
        return extintorViewMapper.map(extintor_atualizado)
    }

    @CacheEvict(cacheNames = ["Extintores"], allEntries = true)
    fun deletarExtintor(numero: Int) {
        val extintor = repository.findById(numero).orElseThrow { NotFoundException("Extintor não encontrado") }
        repository.delete(extintor)
    }
}
