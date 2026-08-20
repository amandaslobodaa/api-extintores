package extintores_api.demo.controller

import extintores_api.demo.dto.CategoriaView
import extintores_api.demo.dto.NovaCategoria
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import extintores_api.demo.model.ExtintorCategoria
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import extintores_api.demo.repository.ExtintorCategoriaRepository
import extintores_api.demo.service.CategoriaService

@RestController
@RequestMapping("/extintorescategorias")
class CategoriaController(
    private val repository: ExtintorCategoriaRepository,
    private val service: CategoriaService,
) {
    @GetMapping("/{id}")
    fun buscarCategoria(@PathVariable id: Int): CategoriaView {
        return service.buscarCategoria(id)
    }
    @GetMapping
    fun listarCategorias(): List<ExtintorCategoria> {
        return repository.findAll()
    }
    @Transactional
    @PostMapping
    fun adicionarCategoria(@RequestBody @Valid novo: NovaCategoria): ResponseEntity<CategoriaView> {
        val categoria_view = service.adicionarCategoria(novo)
        val uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
            .path("/{id}")
            .buildAndExpand(categoria_view.id)
            .toUri()
        return ResponseEntity.created(uri).body(categoria_view)
    }
    @Transactional
    @PutMapping("{id}")
    fun atualizarCategoria(
        @PathVariable id: Int,
        @RequestBody @Valid novo: NovaCategoria,
    ): ResponseEntity<CategoriaView> {
        val categoria_view = service.atualizarCategoria(id, novo)
        return ResponseEntity.ok().body(categoria_view)
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletarCategoria(@PathVariable id: Int) {
        service.deletarCategoria(id)
    }
}