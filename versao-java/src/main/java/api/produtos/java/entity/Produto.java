package api.produtos.java.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal preco;

    public Produto() {
    }

    public Produto(Long id, String nome, BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Produto produto)) return false;
        return id != null && Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
