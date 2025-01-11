package api.produtos.kotlin.assertions

import api.produtos.kotlin.entity.Produto

object Assertions {
    fun assertThat(actual: Produto?) =
        ProdutoAssert(actual)
}