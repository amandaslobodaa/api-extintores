package extintores_api.demo.controller

import extintores_api.demo.dto.MovimentoView
import extintores_api.demo.dto.NovoMovimento
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import extintores_api.demo.model.ExtintorMovimento
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import extintores_api.demo.repository.ExtintorMovimentoRepository
import extintores_api.demo.service.MovimentacaoService

@RestController
@RequestMapping("/extintoresmovimentacoes")
class Movimentacoes_Controller(
    private val repository: ExtintorMovimentoRepository,
    private val service: MovimentacaoService,
) {
    @GetMapping("/{id}")
    fun buscarMovimentacao(@PathVariable id: Int): MovimentoView {
        return service.buscarMovimentacao(id)
    }

    @GetMapping
    fun listarMovimentacoes(): List<ExtintorMovimento> {
        return repository.findAll()
    }

    @Transactional
    @PostMapping
    fun adicionarMovimentacao(@RequestBody @Valid nova: NovoMovimento): ResponseEntity<MovimentoView> {
        val movimento_view = service.adicionarMovimentacao(nova)
        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(movimento_view.id)
            .toUri()
        return ResponseEntity.created(uri).body(movimento_view)
    }

    @Transactional
    @PutMapping("/{id}")
    fun atualizarMovimentacao(
        @PathVariable id: Int,
        @RequestBody @Valid nova: NovoMovimento,
    ): ResponseEntity<MovimentoView> {
        val movimento_view = service.atualizarMovimentacao(id, nova)
        return ResponseEntity.ok().body(movimento_view)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletarMovimentacao(@PathVariable id: Int) {
        service.deletarMovimentacao(id)
    }
}