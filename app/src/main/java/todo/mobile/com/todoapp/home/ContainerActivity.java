package todo.mobile.com.todoapp.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;

import todo.mobile.com.todoapp.R;
import todo.mobile.com.todoapp.home.adapter.TaskAdapterRecyclerView;
import todo.mobile.com.todoapp.model.Task;

public class ContainerActivity extends AppCompatActivity {

    RecyclerView todoRecycler;
    LinearLayoutManager linearLayoutManager;
    TaskAdapterRecyclerView adapterRecyclerView;
    ArrayList<Task> tasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        tasks = new ArrayList<Task>();
        for (int i = 0; i < 10; i++) {
            tasks.add(new Task("title" + i, "Content" + i, "Category"+i, "https://pe-libertyschool.wikispaces.com/file/view/tareas-pendientes-L-1.jpeg/481064322/tareas-pendientes-L-1.jpeg", "green", new Date().toString(), false));
        }

        todoRecycler = (RecyclerView)findViewById(R.id.todoRecycler);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        todoRecycler.setLayoutManager(linearLayoutManager);

        adapterRecyclerView = new TaskAdapterRecyclerView(ContainerActivity.this , tasks, R.layout.cardview_picture);
        todoRecycler.setAdapter(adapterRecyclerView);
    }
}
