package extintores_api.demo.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "extintores_movimento")
class ExtintorMovimento (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimento")
    var id: Int? = null,
    @ManyToOne
    @JoinColumn(name = "empresa")
    var empresa: Empresa? = null,
    @Column(name = "data")
    var data: LocalDate? = null,
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimento")
    var tipo_movimento: TipoMovimento? = null,
    @ManyToOne
    @JoinColumn(name = "empresa_destino")
    var empresa_destino: Empresa? = null,
)