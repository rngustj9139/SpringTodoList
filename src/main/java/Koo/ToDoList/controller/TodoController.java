package Koo.ToDoList.controller;

import Koo.ToDoList.model.TodoEntity;
import Koo.ToDoList.model.TodoRequest;
import Koo.ToDoList.model.TodoResponse;
import Koo.ToDoList.repository.TodoRepository;
import Koo.ToDoList.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin // CORS 이슈를 해결하는 어노테이션
@RestController
@RequestMapping("/")
@AllArgsConstructor
public class TodoController {

    private final TodoService service;

    @PostMapping
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest request) {
        System.out.println("CREATE");

        if(ObjectUtils.isEmpty(request.getTitle())) {
            return ResponseEntity.badRequest().build();
        }

        if(ObjectUtils.isEmpty(request.getOrder())) {
            request.setOrder(0L);
        }

        if(ObjectUtils.isEmpty(request.getCompleted())) {
            request.setCompleted(false);
        }

        TodoEntity result = this.service.add(request);

        return ResponseEntity.ok(new TodoResponse(result));
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoResponse> readOne(@PathVariable Long id) {
        System.out.println("READ ONE");

        TodoEntity result = this.service.searchById(id);

        return ResponseEntity.ok(new TodoResponse(result));
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> readAll() {
        System.out.println("READ ALL");

        List<TodoEntity> list = this.service.searchAll();
        List<TodoResponse> response = list.stream().map(TodoResponse::new)
                                                    .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable Long id, @RequestBody TodoRequest request) {
        System.out.println("UPDATE");

        TodoEntity result = this.service.updateById(id, request);

        return ResponseEntity.ok(new TodoResponse(result));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        System.out.println("DELETE");

        this.service.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        System.out.println("DELETE ALL");

        this.service.deleteAll();
        return ResponseEntity.ok().build();
    }

}
