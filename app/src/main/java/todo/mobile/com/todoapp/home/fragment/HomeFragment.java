package todo.mobile.com.todoapp.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;

import todo.mobile.com.todoapp.R;
import todo.mobile.com.todoapp.home.adapter.TaskAdapterRecyclerView;
import todo.mobile.com.todoapp.listeners.OnTaskListener;
import todo.mobile.com.todoapp.model.Task;


public class HomeFragment extends Fragment {

    RecyclerView todoRecycler;
    LinearLayoutManager linearLayoutManager;
    TaskAdapterRecyclerView adapterRecyclerView;
    ArrayList<Task> tasks;
    OnTaskListener mListener;
    boolean mDualPane;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        showToolbar(getResources().getString(R.string.app_name), false, view);

        tasks = new ArrayList<Task>();
        for (int i = 0; i < 10; i++) {
            tasks.add(new Task("title" + i, "Content" + i, "Category"+i, "https://pe-libertyschool.wikispaces.com/file/view/tareas-pendientes-L-1.jpeg/481064322/tareas-pendientes-L-1.jpeg", "green", new Date().toString(), false));
        }

        todoRecycler = (RecyclerView)view.findViewById(R.id.todoRecycler);
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        todoRecycler.setLayoutManager(linearLayoutManager);
        adapterRecyclerView = new TaskAdapterRecyclerView(HomeFragment.this.getActivity(), tasks, R.layout.cardview_picture, mListener);
        todoRecycler.setAdapter(adapterRecyclerView);

        return view;
    }

    public void showToolbar(String title, boolean upButton, View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (OnTaskListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnTaskListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
