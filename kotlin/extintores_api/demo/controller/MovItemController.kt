package extintores_api.demo.controller

import extintores_api.demo.dto.ItemView
import extintores_api.demo.dto.NovoItem
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import extintores_api.demo.model.ExtintorMovimentoItem
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import extintores_api.demo.repository.ExtintorMovimentoItemRepository
import extintores_api.demo.service.MovimentoItemService

@RestController
@RequestMapping("/movimentacaoitens")
class MovItemController(
    private val repository: ExtintorMovimentoItemRepository,
    private val service: MovimentoItemService,
) {
    @GetMapping
    fun listarMovItens(): List<ExtintorMovimentoItem> {
        return repository.findAll()
    }
    @GetMapping("/{id}")
    fun buscarMovItem(@PathVariable id: Int): ItemView {
        return service.buscarMovItem(id)
    }
    @Transactional
    @PostMapping
    fun adicionarMovItem(@RequestBody @Valid e: NovoItem): ResponseEntity<ItemView> {
        val item_view = service.adicionarMovItem(e)
        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(item_view.id)
            .toUri()
        return ResponseEntity.created(uri).body(item_view)
    }
    @Transactional
    @PutMapping("/{id}")
    fun atualizarMovItem(@PathVariable id: Int, @RequestBody @Valid e: NovoItem): ResponseEntity<ItemView> {
        val item_view = service.atualizarMovItem(id, e)
        return ResponseEntity.ok(item_view)
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletarMovItem(@PathVariable id: Int) {
        service.deletarMovItem(id)
    }
}