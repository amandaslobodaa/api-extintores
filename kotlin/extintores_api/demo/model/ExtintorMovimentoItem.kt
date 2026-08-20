package extintores_api.demo.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "extintores_movimento_itens")
class ExtintorMovimentoItem (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mov_itens")
    var id: Int? = null,
    @ManyToOne
    @JoinColumn(name = "movimento_itens")
    var movimento: ExtintorMovimento? = null,
    @ManyToOne
    @JoinColumn(name = "numero_extintor")
    var extintor: Extintores? = null,
    @ManyToOne
    @JoinColumn(name = "destino")
    var destino: ExtintorLocalizacao? = null,
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimento_item")
    var tipo_mov_item: TipoMovimentoItem? = null,
    @Enumerated(EnumType.STRING)
    @Column(name = "conferido")
    var conferido: Conferido,
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_retorno")
    var tipo_retorno: TipoRetorno? = null,
    @Column(name = "carga_vencimento")
    var carga_vencimento: LocalDate? = null,
    @Column(name = "data_prox_inspecao")
    var data_prox_inspecao: LocalDate? = null,
    @Column(name = "numero_substituto")
    var numero_substituto: String = ""
)