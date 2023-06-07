package sg.edu.rp.c346.id21007436.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etTask;
    Button btnAdd;
    Button btnDelete;
    Button btnClear;
    ListView lvTask;
    ArrayList<String> alTask;
    Spinner spnAddDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.editText);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDelete = findViewById(R.id.buttonDelete);
        btnClear = findViewById(R.id.buttonClear);
        lvTask = findViewById(R.id.taskToDo);
        alTask = new ArrayList<String>();
        spnAddDelete = findViewById(R.id.spinner);

        ArrayAdapter aaTask = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTask);
        lvTask.setAdapter(aaTask);

        spnAddDelete.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                switch (position){
                    case 0:
                        etTask.setHint("Type in a new task here");
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;
                    case 1:
                        etTask.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        if (alTask.size() == 0) {
                            Toast.makeText(MainActivity.this, "You don't have any task to remove",
                                    Toast.LENGTH_LONG).show();
                        }
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                int pos = Integer.parseInt(etTask.getText().toString());
                if (pos >= 0 && pos < alTask.size() ){
                    alTask.remove(pos);
                    aaTask.notifyDataSetChanged();
                    etTask.setText("");
                }else {
                    Toast.makeText(MainActivity.this,"Wrong index number",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                String newTask = etTask.getText().toString();
                alTask.add(newTask);
                aaTask.notifyDataSetChanged();
                etTask.setText("");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                if (alTask.size() == 0){
                    Toast.makeText(MainActivity.this,"You don't have any task to remove",
                            Toast.LENGTH_LONG).show();
                }else{
                    alTask.clear();
                    aaTask.notifyDataSetChanged();
                }
            }
        });

    }
}