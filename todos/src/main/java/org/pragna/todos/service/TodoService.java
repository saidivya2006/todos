package org.pragna.todos.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import org.pragna.todos.model.Todo;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TodoService {
    private static final Path DAT_FILE_PATH = Path.of("data/todos.json");
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    public List<Todo> getAllTodos() {
        try {
            if (DAT_FILE_PATH.toFile().exists()) {
                Todo[] todoArray = objectMapper.readValue(DAT_FILE_PATH.toFile(), Todo[].class);
                return new ArrayList<>(Arrays.asList(todoArray));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private void saveTodos(List<Todo> todos) {
        try {
            if (DAT_FILE_PATH.getParent() != null) {
                Files.createDirectories(DAT_FILE_PATH.getParent());
            }
            objectMapper.writeValue(DAT_FILE_PATH.toFile(), todos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /* 
        The addTodo method retrieves the current list of todos, adds the new todo to the list, and then saves the updated list back to the file. 
    */
    public void addTodo(Todo todo) {
        List<Todo> todos = getAllTodos();
        todos.add(todo);
        saveTodos(todos);
    }

    /*
        The updateTodo method searches for the todo with the specified ID, updates it if found, and saves the updated list. 
    */
    public void updateTodo(long id, Todo updatedTodo) {
        List<Todo> todos = getAllTodos();
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                int index = todos.indexOf(todo);
                todos.set(index, updatedTodo);
                saveTodos(todos);
                return;
            }
        }
    }

    /*
    The deleteTodo method removes the todo with the specified ID from the list and saves the updated list. 
    */
    public void deleteTodo(long id) {
        List<Todo> todos = getAllTodos();
       todos.removeIf(todo -> todo.getId() == id);
        saveTodos(todos);
    }
}



    


