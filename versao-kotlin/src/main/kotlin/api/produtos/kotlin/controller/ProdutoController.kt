package api.produtos.kotlin.controller

import api.produtos.kotlin.entity.Produto
import api.produtos.kotlin.repository.ProdutoRepository
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/produtos")
class ProdutoController(val repository: ProdutoRepository) {
    @PostMapping
    @ResponseStatus(CREATED)
    fun criar(@RequestBody produto: Produto) =
        repository.save(produto.apply { id = null })

    @GetMapping
    @ResponseStatus(OK)
    fun buscar() = repository.findAll()

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    fun atualizar(
        @PathVariable id: Long,
        @RequestBody produto: Produto
    ) =
        repository.save(produto.also { it.id = id })

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    fun deletar(@PathVariable id: Long) =
        repository.findById(id).orElseThrow()
            .also { repository.deleteById(id) }
}