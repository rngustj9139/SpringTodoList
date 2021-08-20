package Koo.ToDoList.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.text.StyledEditorKit;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequest {

    private String title;
    private Long order;
    private Boolean completed;

}
