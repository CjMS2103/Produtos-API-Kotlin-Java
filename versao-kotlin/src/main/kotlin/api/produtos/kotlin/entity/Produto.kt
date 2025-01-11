package api.produtos.kotlin.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import java.math.BigDecimal

@Entity
class Produto(
    @Id @GeneratedValue(strategy = IDENTITY)
    var id: Long?,
    var nome: String,
    var preco: BigDecimal
) {
    override fun equals(other: Any?): Boolean {
        if (other !is Produto) return false
        return id != null && id == other.id
    }

    override fun hashCode() = javaClass.hashCode()
}