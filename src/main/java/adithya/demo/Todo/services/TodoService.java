package adithya.demo.Todo.services;

import adithya.demo.Todo.models.Todo;
import adithya.demo.Todo.repositories.TodoRepository;
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
    public Todo createTodo(Todo obj){
        return todoRepository.save(obj);
    }
    public Todo getTodoById(Long id){
        return todoRepository.findById(id).orElseThrow(()->new RuntimeException("Todo not found"));
    }
    public List<Todo> getTodos(){
        return todoRepository.findAll();
    }
    public Todo updateTodo(Long id, Todo todoDetails){
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        existingTodo.setTitle(todoDetails.getTitle());
        existingTodo.setDescription(todoDetails.getDescription());
        existingTodo.setCompleted(todoDetails.isCompleted());
        return todoRepository.save(existingTodo);
    }
    public void deleteTodoById(Long id){
        todoRepository.delete(getTodoById(id));
    }
    public Page<Todo> getTodosByPage(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return todoRepository.findAll(pageable);
    }

}
