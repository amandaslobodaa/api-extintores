package extintores_api.demo.repository

import extintores_api.demo.model.ExtintorMovimento
import org.springframework.data.jpa.repository.JpaRepository

interface ExtintorMovimentoRepository : JpaRepository<ExtintorMovimento, Int> {
}