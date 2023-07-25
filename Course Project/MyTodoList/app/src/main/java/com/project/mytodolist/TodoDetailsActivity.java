package com.project.mytodolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class TodoDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_details);

        Intent intent = getIntent();
        int todoPosition = intent.getIntExtra("com.project.mytodolist.TODO_INDEX", -1);

        if (todoPosition > -1) {
            TodoRepository todoRepository = TodoRepository.getInstance();
            TextView todoDetailNameTextView = findViewById(R.id.todoDetailNameTextView);
            TextView todoDetailDescriptionTextView = findViewById(R.id.todoDetailDescriptionTextView);
            Todo todo = todoRepository.getTodo(todoPosition);
            todoDetailNameTextView.setText(todo.getName());
            todoDetailDescriptionTextView.setText(todo.getDescription());


            Button completeTodoButton = findViewById(R.id.completeTodoButton);
            completeTodoButton.setOnClickListener((view -> {
                todoRepository.removeTodo(todo);
                Toast.makeText(getApplicationContext(), "Todo completed", Toast.LENGTH_SHORT).show();
                finish();
            }));

            ImageButton navigateBackImageButton = findViewById(R.id.navigateBackImageButton);
            navigateBackImageButton.setOnClickListener((view) -> finish());
        } else {
            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}