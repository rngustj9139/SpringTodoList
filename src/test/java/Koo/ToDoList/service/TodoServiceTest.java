package Koo.ToDoList.service;

import Koo.ToDoList.model.TodoEntity;
import Koo.ToDoList.model.dto.TodoRequest;
import Koo.ToDoList.repository.TodoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    void add() {
        when(this.todoRepository.save(ArgumentMatchers.any(TodoEntity.class)))
                .then(AdditionalAnswers.returnsFirstArg());

        TodoRequest expected = new TodoRequest();
        expected.setTitle("Test title");

        TodoEntity actual = this.todoService.add(expected);

        org.junit.jupiter.api.Assertions.assertEquals(expected.getTitle(), actual.getTitle());
    }

    @Test
    void searchById() {
        TodoEntity entity = new TodoEntity();
        entity.setId(123L);
        entity.setTitle("TITLE");
        entity.setOrder(0L);
        entity.setCompleted(false);

        Optional<TodoEntity> optional = Optional.of(entity);

        given(this.todoRepository.findById(anyLong()))
                .willReturn(optional);

        TodoEntity actual = this.todoService.searchById(123L);
        TodoEntity expected = optional.get();

        Assertions.assertThat(actual.getId()).isEqualTo(expected.getId());
        Assertions.assertThat(actual.getTitle()).isEqualTo(expected.getTitle());
        Assertions.assertThat(actual.getOrder()).isEqualTo(expected.getOrder());
        Assertions.assertThat(actual.getCompleted()).isEqualTo(expected.getCompleted());
    }

    @Test
    public void searchByIdFailed() {
        given(this.todoRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        org.junit.jupiter.api.Assertions.assertThrows(ResponseStatusException.class, () -> {
            this.todoService.searchById(123L);
        });
    }

}
