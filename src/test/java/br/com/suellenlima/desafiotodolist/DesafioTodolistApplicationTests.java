package br.com.suellenlima.desafiotodolist;

//import static br.com.suellenlima.desafiotodolist.TestConstants.TODO;
//import static br.com.suellenlima.desafiotodolist.TestConstants.TODOS;

import br.com.suellenlima.desafiotodolist.entities.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TodolistApplicationTests {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreateTodoSuccess() {
        var todo = new Todo("todo 1", "desc todo 1", false, 1);

        webTestClient
                .post()
                .uri("/todos")
                .bodyValue(todo)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$.length()").isEqualTo(9)
                .jsonPath("$[0].nome").isEqualTo(todo.getNome())
                .jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
                .jsonPath("$[0].realizado").isEqualTo(todo.isRealizado())
                .jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());
    }

    @Test
    public void testCreateTodoFailure() {
        webTestClient
                .post()
                .uri("/todos")
                .bodyValue(
                        new Todo("", "", false, 0))
                .exchange()
                .expectStatus().isBadRequest();
    }

}