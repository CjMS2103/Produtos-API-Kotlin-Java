package api.produtos.kotlin

import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity

inline fun <reified T> TestRestTemplate.putForEntity(url: String, request: Any): ResponseEntity<T> =
    exchange(RequestEntity.put(url).body(request), T::class.java)

inline fun <reified T> TestRestTemplate.deleteForEntity(url: String): ResponseEntity<T> =
    exchange(RequestEntity.delete(url).build(), T::class.java)
    