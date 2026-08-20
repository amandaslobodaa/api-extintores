package extintores_api.demo.repository

import extintores_api.demo.model.Extintores
import org.springframework.data.jpa.repository.JpaRepository

interface ExtintorRepository: JpaRepository<Extintores, Int> {
}