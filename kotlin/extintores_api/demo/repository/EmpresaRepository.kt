package extintores_api.demo.repository

import extintores_api.demo.model.Empresa
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmpresaRepository : JpaRepository<Empresa, Int> {

    fun findByCodigo(codigo: Int): Empresa?

    fun findByDescricao(descricao: String): Empresa?

}