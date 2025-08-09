package adithya.demo.Todo.repositories;

import adithya.demo.Todo.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long> {

}
