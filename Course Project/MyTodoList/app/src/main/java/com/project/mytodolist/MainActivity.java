package com.project.mytodolist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView todoListView;
    TodoRepository todoRepository;
    FloatingActionButton openAddTodoDialogButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoRepository = TodoRepository.getInstance();
        todoListView = findViewById(R.id.todoListview);
        todoListView.setAdapter(todoRepository.createTodoAdapter(this));
        todoListView.setOnItemClickListener(((adapterView, view, i, l) -> openTodoDetails(i)));

        openAddTodoDialogButton = findViewById(R.id.openAddTodoDialogButton);
        openAddTodoDialogButton.setOnClickListener(view -> openAddTodoDialog());

    }

    private void openTodoDetails(int todoPosition) {
        Intent showTodoDetailsActivity = new Intent(getApplicationContext(), TodoDetailsActivity.class);
        showTodoDetailsActivity.putExtra("com.project.mytodolist.TODO_INDEX", todoPosition);
        startActivity(showTodoDetailsActivity);

    }

    private void openAddTodoDialog() {
        final Dialog addTodoDialog = new Dialog(this);
        addTodoDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        addTodoDialog.setCancelable(true);
        addTodoDialog.setContentView(R.layout.add_todo_item);

        EditText todoNameEditText = addTodoDialog.findViewById(R.id.todoNameEditText);
        EditText todoDescriptionEditText = addTodoDialog.findViewById(R.id.todoDescriptionEditText);
        Button addTodoButton = addTodoDialog.findViewById(R.id.addTodoButton);

        addTodoButton.setOnClickListener((view -> {
            String name = todoNameEditText.getText().toString();
            String description = todoDescriptionEditText.getText().toString();

            if (name.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Please enter name and description", Toast.LENGTH_SHORT).show();
            } else {
                todoRepository.addTodo(new Todo(name, description));
                Toast.makeText(this, "Todo added", Toast.LENGTH_SHORT).show();
                addTodoDialog.cancel();
            }
        }));

        addTodoDialog.show();
    }
}