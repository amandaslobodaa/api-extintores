package extintores_api.demo.model

import jakarta.persistence.*
import jakarta.persistence.EnumType


@Entity
@Table(name = "extintores_localizacoes")
class ExtintorLocalizacao (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_localizacoes")
    var id: Int? = null,
    @ManyToOne
    @JoinColumn(name = "empresa")
    var empresa: Empresa? = null,
    @Column(name = "descricao", length = 200)
    var descricao: String = "",
    @Column(name = "centro_custo", length = 20)
    var centro_custo: String = "",
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_localizacao", length = 1)
    var tipo_localizacao: TipoLocalizacao? = null
)