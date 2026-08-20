package extintores_api.demo.service

import extintores_api.demo.dto.EmpresaView
import extintores_api.demo.dto.EmpresaViewMapper
import extintores_api.demo.dto.NovaEmpresa
import extintores_api.demo.exception.NotFoundException
import extintores_api.demo.mapper.EmpresaFormMapper
import extintores_api.demo.model.Empresa
import org.springframework.cache.annotation.CacheEvict
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import extintores_api.demo.repository.EmpresaRepository

@Service
class EmpresaService (private val repository: EmpresaRepository,
                      private val empresa_form_mapper: EmpresaFormMapper,
                      private val empresa_view_mapper: EmpresaViewMapper): UserDetailsService{
    fun buscarEmpresa(codigo: Int): EmpresaView {
        val empresa = repository.findById(codigo).orElseThrow { NotFoundException("Empresa não encontrada") }
        return empresa_view_mapper.map(empresa)
    }
    fun listEmpresas(): List<Empresa>{
        return repository.findAll()
    }
    @CacheEvict(cacheNames = ["Empresas"], allEntries = true)
    fun adicionarEmpresa(emp: NovaEmpresa): EmpresaView {
        val empresa = empresa_form_mapper.map(emp)
        val empresaCadastrada = repository.save(empresa)
        return empresa_view_mapper.map(empresaCadastrada)
    }
    @CacheEvict(cacheNames = ["Empresas"], allEntries = true)
    fun atualizarEmpresa(codigo: Int, emp: NovaEmpresa): EmpresaView {
        val empresa = repository.findById(codigo).orElseThrow { NotFoundException("Empresa não encontrada") }
        empresa.descricao = emp.descricao
        val empresa_atualizada = repository.save(empresa)
        return empresa_view_mapper.map(empresa_atualizada)
    }

    @CacheEvict(cacheNames = ["Empresas"], allEntries = true)
    fun deletarEmpresa(codigo: Int){
        val empresa = repository.findById(codigo).orElseThrow { NotFoundException("Empresa não encontrada") }
        repository.delete(empresa)
    }

    override fun loadUserByUsername(descricao: String): UserDetails {
        val empresa = repository.findByDescricao(descricao) ?: throw RuntimeException()
        return UserDetail(empresa)
    }

    fun buscarPorId(codigo: Int): Empresa {
        return repository.findByCodigo(codigo) ?: throw RuntimeException()
    }
}