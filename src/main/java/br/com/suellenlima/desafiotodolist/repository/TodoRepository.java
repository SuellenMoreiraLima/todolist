package br.com.suellenlima.desafiotodolist.repository;

import br.com.suellenlima.desafiotodolist.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
