package extintores_api.demo.mapper

import extintores_api.demo.dto.NovaEmpresa
import extintores_api.demo.model.Empresa
import org.springframework.stereotype.Component

@Component
class EmpresaFormMapper():
    Mapper<NovaEmpresa, Empresa> {
    override fun map(e: NovaEmpresa): Empresa {
        return Empresa(
            codigo = e.codigo,
            descricao = e.descricao,
            senha = e.senha
        )
    }
}