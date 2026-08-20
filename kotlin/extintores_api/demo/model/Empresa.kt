package extintores_api.demo.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "empresas")
data class Empresa (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_empresas")
    var codigo: Int? = null,
    @Column(name = "descricao", length = 150)
    var descricao: String,
    var senha : String,

    @JsonIgnore //para ignorar esse campo
    @ManyToMany(fetch = FetchType.EAGER) //traz todas as entidades relacionadas do relacionamento sempre que isso for buscado
    @JoinTable(name = "empresa_role",
        joinColumns = [JoinColumn(name = "codigo_empresas")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var role: List<Role> = mutableListOf()
)