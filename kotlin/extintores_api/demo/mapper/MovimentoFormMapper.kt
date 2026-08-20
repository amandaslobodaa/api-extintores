package extintores_api.demo.mapper

import extintores_api.demo.dto.NovaLocalizacao
import extintores_api.demo.dto.NovoMovimento
import extintores_api.demo.exception.NotFoundException
import extintores_api.demo.model.ExtintorLocalizacao
import extintores_api.demo.model.ExtintorMovimento
import extintores_api.demo.repository.EmpresaRepository
import extintores_api.demo.repository.ExtintorMovimentoRepository
import org.springframework.stereotype.Component

@Component
class MovimentoFormMapper(private val empresaRepository: EmpresaRepository):  Mapper<NovoMovimento, ExtintorMovimento> {
    override fun map(e: NovoMovimento): ExtintorMovimento {
        val empresa = empresaRepository.findById(e.empresa)
            .orElseThrow { NotFoundException("A empresa não foi encontrada") }
        val empresa_destino = empresaRepository.findById(e.empresa_destino)
            .orElseThrow { NotFoundException ("A empresa não foi encontrada") }
        return ExtintorMovimento(
            empresa = empresa,
            data = e.data,
            tipo_movimento = e.tipo_movimento,
            empresa_destino = empresa_destino
        )
    }
}


