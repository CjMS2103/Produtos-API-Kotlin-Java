package api.produtos.java;

import api.produtos.java.entity.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;

import static api.produtos.java.assertions.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.RequestEntity.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class IntegrationTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void deveCriarNovoProduto() {
        Produto produto = new Produto(null, "Laptop", new BigDecimal("2400.00"));

        ResponseEntity<Produto> resposta = restTemplate.exchange(
                post("/produtos").body(produto), Produto.class);

        assertThat(resposta.getStatusCode()).isEqualTo(CREATED);
        assertThat(resposta.getBody())
                .hasId(3L)
                .hasNome("Laptop")
                .hasPreco(new BigDecimal("2400.00"));
    }

    @Test
    public void deveBuscarProdutos() {
        ResponseEntity<Produto[]> resposta = restTemplate.exchange(
                get("/produtos").build(), Produto[].class);

        assertThat(resposta.getStatusCode()).isEqualTo(OK);
        assertThat(resposta.getBody().length).isEqualTo(2);
        assertThat(resposta.getBody()[0])
                .hasId(1L)
                .hasNome("TV")
                .hasPreco(new BigDecimal("1600.00"));
        assertThat(resposta.getBody()[1])
                .hasId(2L)
                .hasNome("Display")
                .hasPreco(new BigDecimal("800.00"));
    }

    @Test
    public void deveAtualizarProduto() {
        Produto produto = new Produto(1L, "Laptop", new BigDecimal("2400.00"));

        ResponseEntity<Produto> resposta = restTemplate.exchange(
                put("/produtos/1").body(produto), Produto.class);

        assertThat(resposta.getStatusCode()).isEqualTo(OK);
        assertThat(resposta.getBody())
                .hasId(1L)
                .hasNome("Laptop")
                .hasPreco(new BigDecimal("2400.00"));
    }

    @Test
    public void deveDeletarProduto() {
        ResponseEntity<Produto> resposta = restTemplate.exchange(
                delete("/produtos/1").build(), Produto.class);

        assertThat(resposta.getStatusCode()).isEqualTo(OK);
        assertThat(resposta.getBody())
                .hasId(1L)
                .hasNome("TV")
                .hasPreco(new BigDecimal("1600.00"));
    }
}