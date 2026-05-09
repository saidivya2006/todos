package org.pragna.todos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.pragna", "org.pragna.todos.controller"})
public class TodoApp{
    public static void main(String[] args) {
        SpringApplication.run(TodoApp.class, args);
    }
}
