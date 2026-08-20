package extintores_api.demo.repository

import extintores_api.demo.model.ExtintorMovimentoItem
import org.springframework.data.jpa.repository.JpaRepository

interface ExtintorMovimentoItemRepository: JpaRepository<ExtintorMovimentoItem, Int> {
}