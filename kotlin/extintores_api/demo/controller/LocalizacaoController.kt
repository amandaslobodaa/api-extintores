package extintores_api.demo.controller

import extintores_api.demo.dto.LocalizacaoView
import extintores_api.demo.dto.NovaLocalizacao
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import extintores_api.demo.model.ExtintorLocalizacao
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import extintores_api.demo.repository.ExtintorLocalizacaoRepository
import extintores_api.demo.service.LocalizacaoService

@RestController
@RequestMapping("/extintoreslocalizacoes")
class LocalizacaoController(
    private val repository: ExtintorLocalizacaoRepository, private val service: LocalizacaoService,
) {
    @GetMapping("/{id}")
    fun buscarLocalizacao(@PathVariable id: Int): LocalizacaoView {
        return service.buscarLocalizacao(id)
    }
    @GetMapping
    fun listarLocalizacoes(): List<ExtintorLocalizacao> {
        return repository.findAll()
    }
    @Transactional
    @PostMapping
    fun adicionarLocalizacao(@RequestBody @Valid nova: NovaLocalizacao): ResponseEntity<LocalizacaoView> {
        val localizacao_view = service.adicionarLocalizacao(nova)
        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(localizacao_view.id)
            .toUri()
        return ResponseEntity.created(uri).body(localizacao_view)
    }

    @Transactional
    @PutMapping("/{id}")
    fun atualizarLocalizacao(
        @PathVariable id: Int,
        @RequestBody @Valid nova: NovaLocalizacao,
    ): ResponseEntity<LocalizacaoView> {
        val localizacoes_view = service.atualizarLocalizacao(id, nova)
        return ResponseEntity.ok().body(localizacoes_view)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletarLocalizacao(@PathVariable id: Int) {
        service.deletarLocalizacao(id)
    }
}