package extintores_api.demo.repository

import extintores_api.demo.model.ExtintorLocalizacao
import org.springframework.data.jpa.repository.JpaRepository

interface ExtintorLocalizacaoRepository: JpaRepository<ExtintorLocalizacao, Int> {
}