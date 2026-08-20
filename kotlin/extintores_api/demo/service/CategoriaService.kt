package extintores_api.demo.service

import extintores_api.demo.dto.CategoriaView
import extintores_api.demo.dto.NovaCategoria
import extintores_api.demo.exception.NotFoundException
import extintores_api.demo.mapper.CategoriaFormMapper
import extintores_api.demo.mapper.CategoriaViewMapper
import extintores_api.demo.model.ExtintorCategoria
import org.springframework.cache.annotation.CacheEvict
import org.springframework.stereotype.Service
import extintores_api.demo.repository.ExtintorCategoriaRepository

@Service
class CategoriaService (
    private val repository: ExtintorCategoriaRepository,
    private val categoria_form_mapper: CategoriaFormMapper,
    private val categoria_view_mapper: CategoriaViewMapper,
) {
    fun buscarCategoria(id: Int): CategoriaView {
        val categoria = repository.findById(id).orElseThrow { NotFoundException("A categoria não existe") }
        return categoria_view_mapper.map(categoria)
    }
    fun listarCategorias(): List<ExtintorCategoria> {
        return repository.findAll()
    }
    @CacheEvict(cacheNames = ["Categorias"], allEntries = true)
    fun adicionarCategoria(form: NovaCategoria): CategoriaView {
        val categoria = categoria_form_mapper.map(form)
        val categoria_Cadastrada = repository.save(categoria)
        return categoria_view_mapper.map(categoria_Cadastrada)
    }
    @CacheEvict(cacheNames = ["Categorias"], allEntries = true)
    fun atualizarCategoria(id: Int, nova: NovaCategoria): CategoriaView {
        val categoria = repository.findById(id).orElseThrow { NotFoundException("A categoria não existe") }
        categoria.unidade = nova.unidade
        categoria.descricao = nova.descricao
        categoria.periodo_inspecao = nova.periodo_inspecao
        categoria.periodo_validade = nova.periodo_validade
        val categoria_atualizada = repository.save(categoria)
        return categoria_view_mapper.map(categoria_atualizada)
    }
    @CacheEvict(cacheNames = ["Categorias"], allEntries = true)
    fun deletarCategoria(id: Int) {
        val categoria = repository.findById(id).orElseThrow { NotFoundException("A categoria não existe") }
        repository.delete(categoria)
    }
}