package org.pragna.todos.service;

import java.util.ArrayList;
import java.util.List;
import org.pragna.todos.model.Todo;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private List<Todo> todos = new ArrayList<>();
    
public List<Todo> getAllTodos() {
       
        return todos;
    }

    

    public void addTodo(Todo todo) {
        todos.add(todo);
    }

    public void updateTodo(int id, Todo updatedTodo) {
        for (Todo todo:todos){
            if (todo.getId() == id) {
                int index = todos.indexOf(todo);
                todos.set(index, updatedTodo);
                return;
            }
        }
    }

    public void deleteTodo(int id) {
       
        
        todos.removeIf(todo -> todo.getId() == id);
    }
}

    

