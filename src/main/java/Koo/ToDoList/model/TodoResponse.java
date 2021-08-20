package Koo.ToDoList.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponse {

    private Long id;
    private String title;
    private Long order;
    private Boolean completed;
    private String url;

    public TodoResponse(TodoEntity todoEntity) { // TodoEntity를 파라미터로 받는 생성자
        this.id = todoEntity.getId();
        this.title = todoEntity.getTitle();
        this.order = todoEntity.getOrder();
        this.completed = todoEntity.getCompleted();

        this.url = "http://localhost:8080/" + this.id; // url은 TodoEntity에 없는 정보라서 임의로 작성(base url이 하드코딩 됨)
    }

}
