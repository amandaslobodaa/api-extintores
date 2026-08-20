package extintores_api.demo.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "extintores")
class Extintores (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_extintor", length = 15)
    var numero: Int? = null,
    @ManyToOne
    @JoinColumn(name = "tipo")
    var tipo: ExtintorCategoria? = null,
    @Column(name = "carga_total", length = 15 )
    var carga_total: Double? = null,
    @Column(name = "carga_vencimento")
    var carga_vencimento: LocalDate? = null,
    @Column(name = "data_prox_inspecao")
    var data_prox_inspecao: LocalDate? = null,
    @ManyToOne
    @JoinColumn(name = "localizacao")
    var localizacao: ExtintorLocalizacao?  = null,
    @Enumerated(EnumType.STRING)
    @Column(name = "situacao_extintor", precision = 1)
    var situacao_extintor: SituacaoExtintor? = null,
    @Column(name = "centro_custo", precision = 20)
    var centro_custo: String = "" 
)