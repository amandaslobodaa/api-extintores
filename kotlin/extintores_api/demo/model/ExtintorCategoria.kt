package extintores_api.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "extintores_categorias")
class ExtintorCategoria (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categorias")
    var id: Int? = null,
    @Column(name = "descricao", length = 30)
    var descricao: String = "",
    @Column(name = "unidade", length = 2)
    var unidade: String = "",
    @Column(name = "periodo_inspecao", length = 32)
    var periodo_inspecao: Int,
    @Column(name = "periodo_validade", length = 32)
    var periodo_validade: Int,
)