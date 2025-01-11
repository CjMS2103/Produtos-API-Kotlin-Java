package api.produtos.java.assertions;

import api.produtos.java.entity.Produto;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ProdutoAssert {
    private Produto actual;

    public ProdutoAssert(Produto actual) {
        this.actual = actual;
    }

    public ProdutoAssert hasId(Long expectedId) {
        assertThat(actual.getId()).isEqualTo(expectedId);
        return this;
    }

    public ProdutoAssert hasNome(String expectedNome) {
        assertThat(actual.getNome()).isEqualTo(expectedNome);
        return this;
    }

    public ProdutoAssert hasPreco(BigDecimal expectedPreco) {
        assertThat(actual.getPreco()).isEqualTo(expectedPreco);
        return this;
    }
}
