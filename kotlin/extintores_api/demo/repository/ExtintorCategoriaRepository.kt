package extintores_api.demo.repository

import extintores_api.demo.model.ExtintorCategoria
import org.springframework.data.jpa.repository.JpaRepository

interface ExtintorCategoriaRepository: JpaRepository<ExtintorCategoria, Int> {
}