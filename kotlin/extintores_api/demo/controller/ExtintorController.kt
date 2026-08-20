package extintores_api.demo.controller

import extintores_api.demo.dto.ExtintoresView
import extintores_api.demo.dto.NovoExtintor
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import extintores_api.demo.model.Extintores
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import extintores_api.demo.repository.ExtintorRepository
import extintores_api.demo.service.ExtintorService

@RestController
@RequestMapping("/extintores")
class ExtintorController(private val repository: ExtintorRepository, private val service: ExtintorService) {
    @GetMapping("/{numero}")
    fun buscarNumeroExtintor(@PathVariable numero: Int): ExtintoresView {
        return service.buscarNumeroExtintor(numero)
    }

    @GetMapping
    fun listarExtintores(): List<Extintores> {
        return repository.findAll()
    }

    @Transactional
    @PostMapping
    fun adicionarExtintor(@RequestBody @Valid form: NovoExtintor): ResponseEntity<ExtintoresView> {
        val extintor_view = service.adicionarExtintor(form)
        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{numero}")
            .buildAndExpand(extintor_view.numero)
            .toUri()
        return ResponseEntity.created(uri).body(extintor_view)
    }

    @PutMapping("/{numero}")
    @Transactional
    fun atualizarExtintor(
        @PathVariable numero: Int,
        @RequestBody @Valid form: NovoExtintor,
    ): ResponseEntity<ExtintoresView> {
        val extintor_view = service.atualizarExtintor(numero, form)
        return ResponseEntity.ok().body(extintor_view)
    }

    @DeleteMapping("/{numero}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletarExtintor(@PathVariable numero: Int) {
        service.deletarExtintor(numero)
    }
}