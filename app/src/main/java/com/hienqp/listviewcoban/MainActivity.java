package com.hienqp.listviewcoban;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listViewCourse;
    private ArrayList<String> arrayCource;
    private ArrayAdapter<String> arrayAdapter;
    private EditText editTextCource;
    private Button buttonAddCourse, buttonUpdate;
    private int currentPositionOnArrayCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ánh xạ
        listViewCourse = (ListView) findViewById(R.id.listview_course);
        arrayCource = new ArrayList<>();
        editTextCource = (EditText) findViewById(R.id.editText_course);
        buttonAddCourse = (Button) findViewById(R.id.button_add_course);
        buttonUpdate = (Button) findViewById(R.id.button_update);

        // add data vào DataSource
        arrayCource.add("Android");
        arrayCource.add("PHP");
        arrayCource.add("IOS");
        arrayCource.add("JavaScript");
        arrayCource.add("Java");
        arrayCource.add("ASP.NET");

        // gắn DataSource vào Adapter
        arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, arrayCource);
        // gắn Adapter vào AdapterView
        listViewCourse.setAdapter(arrayAdapter);

        buttonAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String course = editTextCource.getText().toString();
                if (!course.isEmpty()) {
                    arrayCource.add(course);
                    arrayAdapter.notifyDataSetChanged();
                }
            }
        });

        listViewCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editTextCource.setText(arrayCource.get(position));
                currentPositionOnArrayCourse = position;
            }
        });

        listViewCourse.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                arrayCource.remove(position);
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayCource.set(currentPositionOnArrayCourse, editTextCource.getText().toString());
                arrayAdapter.notifyDataSetChanged();
            }
        });

//        // bắt sự kiện khi click vào phần tử trong AdapterView
//        listViewCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, arrayCource.get(position) + ": ID " + id + " clicked ", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        // bắt sự kiện long click vào phần tử trong AdapterView
//        listViewCourse.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, arrayCource.get(position) + ": setOnItemLongClickListener", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
    }
}