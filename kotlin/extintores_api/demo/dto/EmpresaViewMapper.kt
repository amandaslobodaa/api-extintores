package extintores_api.demo.dto

import extintores_api.demo.mapper.Mapper
import extintores_api.demo.model.Empresa
import org.springframework.stereotype.Component

@Component
class EmpresaViewMapper:
    Mapper<Empresa, EmpresaView> {
    override fun map(e: Empresa): EmpresaView {
        return EmpresaView(
            codigo = e.codigo!!,
            descricao = e.descricao,
            senha = e.senha
        )
    }
}