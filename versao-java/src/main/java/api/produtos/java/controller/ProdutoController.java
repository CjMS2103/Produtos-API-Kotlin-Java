package api.produtos.java.controller;

import api.produtos.java.entity.Produto;
import api.produtos.java.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Produto criar(@RequestBody Produto produto) {
        produto.setId(null);
        return repository.save(produto);
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<Produto> buscar() {
        return repository.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public Produto atualizar(@PathVariable Long id,
                             @RequestBody Produto produto) {
        produto.setId(id);
        return repository.save(produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public Produto deletar(@PathVariable Long id) {
        Produto produto = repository.findById(id).orElseThrow();
        repository.deleteById(id);
        return produto;
    }
}