package adithya.demo.Todo;

import adithya.demo.Todo.models.Todo;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;
    @PostMapping("/create")
    ResponseEntity<Todo> createTodo(@RequestBody Todo ent){
        return new ResponseEntity<>(todoService.creteTodo(ent), HttpStatus.CREATED);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "todo retrieved successfully"),
            @ApiResponse(responseCode = "404",description = "todo not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<Todo> getTodoById(@PathVariable Long id){
        try {
            Todo ent = todoService.getTodoById(id);
            return new ResponseEntity<>(ent, HttpStatus.OK);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/")
    ResponseEntity<List<Todo>> getTodos(){
        return new ResponseEntity<>(todoService.getTodos(),HttpStatus.OK);
    }
    @GetMapping("/page/")
    ResponseEntity<Page<Todo>> getTodosByPage(@RequestParam int no,@RequestParam int size){
        return new ResponseEntity<>(todoService.getTodosByPage(no,size),HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Todo> updateTodoById(@PathVariable Long id, @RequestBody Todo todoDetails) {

        Todo updatedTodo = todoService.updateTodo(id, todoDetails);
        return ResponseEntity.ok(updatedTodo);
    }
    @DeleteMapping("/{id}")
    void del(@PathVariable Long id){
        todoService.deleteTodoById(id);
    }
}
