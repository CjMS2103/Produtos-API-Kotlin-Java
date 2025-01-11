package api.produtos.kotlin

import api.produtos.kotlin.assertions.Assertions.assertThat
import api.produtos.kotlin.entity.Produto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD
import java.math.BigDecimal

@SpringBootTest(webEnvironment = RANDOM_PORT)
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
class IntegrationTests {
    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    fun `Deve criar novo produto`() {
        val produto = Produto(null, "Laptop", BigDecimal("2400.00"))

        val resposta = restTemplate.postForEntity<Produto>("/produtos", produto)

        assertThat(resposta.statusCode).isEqualTo(CREATED)
        assertThat(resposta.body)
            .hasId(3)
            .hasNome("Laptop")
            .hasPreco(BigDecimal("2400.00"))
    }

    @Test
    fun `Deve buscar produtos`() {
        val resposta = restTemplate.getForEntity<Array<Produto>>("/produtos")

        assertThat(resposta.statusCode).isEqualTo(OK)
        assertThat(resposta.body?.size).isEqualTo(2)
        assertThat(resposta.body!![0])
            .hasId(1)
            .hasNome("TV")
            .hasPreco(BigDecimal("1600.00"))
        assertThat(resposta.body!![1])
            .hasId(2)
            .hasNome("Display")
            .hasPreco(BigDecimal("800.00"))
    }

    @Test
    fun `Deve atualizar produto`() {
        val produto = Produto(1, "Laptop", BigDecimal("2400.00"))

        val resposta = restTemplate.putForEntity<Produto>("/produtos/1", produto)

        assertThat(resposta.statusCode).isEqualTo(OK)
        assertThat(resposta.body)
            .hasId(1)
            .hasNome("Laptop")
            .hasPreco(BigDecimal("2400.00"))
    }

    @Test
    fun `Deve deletar produto`() {
        val resposta = restTemplate.deleteForEntity<Produto>("/produtos/1")

        assertThat(resposta.statusCode).isEqualTo(OK)
        assertThat(resposta.body)
            .hasId(1)
            .hasNome("TV")
            .hasPreco(BigDecimal("1600.00"))
    }
}