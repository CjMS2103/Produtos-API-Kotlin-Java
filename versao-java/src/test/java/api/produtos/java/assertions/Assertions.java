package api.produtos.java.assertions;

import api.produtos.java.entity.Produto;

public class Assertions {
    public static ProdutoAssert assertThat(Produto actual) {
        return new ProdutoAssert(actual);
    }
}
