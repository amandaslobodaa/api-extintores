package extintores_api.demo.controller

import extintores_api.demo.dto.EmpresaView
import extintores_api.demo.dto.NovaEmpresa
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import extintores_api.demo.model.Empresa
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import extintores_api.demo.repository.EmpresaRepository
import extintores_api.demo.service.EmpresaService

@RestController
@RequestMapping("/empresas")
class EmpresaController(private val repository: EmpresaRepository, private val service: EmpresaService) {

    @GetMapping("/{codigo}")
    fun buscarEmpresa(@PathVariable codigo: Int): EmpresaView {
        return service.buscarEmpresa(codigo)
    }

    @GetMapping
    fun listarEmpresas(): List<Empresa> {
        return repository.findAll()
    }

    @Transactional
    @PostMapping
    fun adicionarEmpresa(@RequestBody @Valid emp: NovaEmpresa): ResponseEntity<EmpresaView> {
        val empresa_view = service.adicionarEmpresa(emp)
        val uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
            .path("/{id}")
            .buildAndExpand(empresa_view.codigo)
            .toUri()
        return ResponseEntity.created(uri).body(empresa_view)
    }

    @Transactional
    @PutMapping("/{codigo}")
    fun atualizarEmpresa(
        @PathVariable codigo: Int,
        @RequestBody @Valid emp: NovaEmpresa,
    ): ResponseEntity<EmpresaView> {
        val empresa_view = service.atualizarEmpresa(codigo, emp)
        return ResponseEntity.ok().body(empresa_view)
    }
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletarEmpresa(@PathVariable codigo: Int) {
        service.deletarEmpresa(codigo)
    }
}