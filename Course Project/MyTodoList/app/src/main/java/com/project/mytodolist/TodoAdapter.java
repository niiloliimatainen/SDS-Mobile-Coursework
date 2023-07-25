package com.project.mytodolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TodoAdapter extends ArrayAdapter<Todo> {
    private ArrayList<Todo> todoList;
    private Context context;

    public TodoAdapter(Context context, ArrayList<Todo> todoList) {
        super(context, 0, todoList);
        this.context = context;
        this.todoList = todoList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.todo_list_item, parent, false);
        }

        Todo currentTodo = todoList.get(position);

        TextView todoNameTextView = listItemView.findViewById(R.id.todoNameTextView);
       todoNameTextView.setText(currentTodo.getName());

        return listItemView;
    }
}