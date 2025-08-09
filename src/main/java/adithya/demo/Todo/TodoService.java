package adithya.demo.Todo;

import adithya.demo.Todo.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;
    Todo creteTodo(Todo obj){
        return todoRepository.save(obj);
    }
    Todo getTodoById(Long id){
        return todoRepository.findById(id).orElseThrow(()->new RuntimeException("Todo not found"));
    }
    List<Todo> getTodos(){
        return todoRepository.findAll();
    }
    Todo updateTodo(Long id, Todo todoDetails){
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        existingTodo.setTitle(todoDetails.getTitle());
        existingTodo.setDescription(todoDetails.getDescription());
        existingTodo.setCompleted(todoDetails.isCompleted());
        return todoRepository.save(existingTodo);
    }
    void deleteTodoById(Long id){
        todoRepository.delete(getTodoById(id));
    }
    Page<Todo> getTodosByPage(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return todoRepository.findAll(pageable);
    }
}
