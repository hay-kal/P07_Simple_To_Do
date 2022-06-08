package sg.edu.rp.c346.id21040411.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    EditText etTask;
    Button btnAdd, btnDelete, btnClear;
    Spinner spinTask;
    ListView lvTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etTask = findViewById(R.id.etAddTask);
        spinTask = findViewById(R.id.spinner);
        btnAdd = findViewById(R.id.btnAddTask);
        btnDelete = findViewById(R.id.btnDeleteTask);
        btnClear = findViewById(R.id.btnClearTask);
        lvTask = findViewById(R.id.lvTask);

        ArrayList alTask = new ArrayList<String>();


        ArrayAdapter aaTask = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, alTask);

        lvTask.setAdapter(aaTask);


        spinTask.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        etTask.setHint(R.string.AddTask);
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;

                    case 1:
                        etTask.setHint(R.string.DeleteTask);
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String addTask = etTask.getText().toString();
                alTask.add(addTask);
                aaTask.notifyDataSetChanged();

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = Integer.parseInt(etTask.getText().toString());

                if (alTask.isEmpty() && etTask.getText().toString().trim().equals("")) {
                    //still crashes
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                } else if (position > alTask.size()) {
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                } else {
                    alTask.remove(position);
                    aaTask.notifyDataSetChanged();
                }

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alTask.clear();
                aaTask.notifyDataSetChanged();

            }
        });

    }
}