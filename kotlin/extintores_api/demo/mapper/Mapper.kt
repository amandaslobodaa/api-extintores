package extintores_api.demo.mapper

interface Mapper <E, U>{
    fun map(item: E): U
}