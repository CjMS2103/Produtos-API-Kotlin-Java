package api.produtos.kotlin.assertions

import api.produtos.kotlin.entity.Produto
import org.assertj.core.api.Assertions.assertThat
import java.math.BigDecimal

class ProdutoAssert(val actual: Produto?) {
    fun hasId(expectedId: Long) =
        apply { assertThat(actual?.id).isEqualTo(expectedId) }

    fun hasNome(expectedNome: String) =
        apply { assertThat(actual?.nome).isEqualTo(expectedNome) }

    fun hasPreco(expectedPreco: BigDecimal) =
        apply { assertThat(actual?.preco).isEqualTo(expectedPreco) }
}