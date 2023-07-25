package com.project.mytodolist;

import android.content.Context;

import java.util.ArrayList;

public class TodoRepository {
    private static TodoRepository instance;
    private ArrayList<Todo> todoList;
    private TodoAdapter todoAdapter;

    private TodoRepository() {
        todoList = new ArrayList<>();
    }

    public static TodoRepository getInstance() {
        if (instance == null) {
            instance = new TodoRepository();
        }
        return instance;
    }

    public void addTodo(Todo todo) {
        todoList.add(todo);
        todoAdapter.notifyDataSetChanged();
    }

    public void removeTodo(Todo todo) {
        todoList.remove(todo);
        todoAdapter.notifyDataSetChanged();
    }

    public ArrayList<Todo> getTodoList() {
        return todoList;
    }

    public Todo getTodo(int todoPosition) {
        return todoList.get(todoPosition);
    }

    public TodoAdapter createTodoAdapter(Context context) {
        todoAdapter = new TodoAdapter(context, todoList);
        return todoAdapter;
    }
}
