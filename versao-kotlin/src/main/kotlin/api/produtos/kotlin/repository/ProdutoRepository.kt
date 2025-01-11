package api.produtos.kotlin.repository

import api.produtos.kotlin.entity.Produto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProdutoRepository : JpaRepository<Produto, Long>