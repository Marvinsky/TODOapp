package todo.mobile.com.todoapp.geolocalization.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import todo.mobile.com.todoapp.R;
import todo.mobile.com.todoapp.listeners.OnTaskListener;


public class TaskMapFragment extends Fragment {

    private OnTaskListener mListener;

    public TaskMapFragment() {
        // Required empty public constructor
    }

    public static TaskMapFragment newInstance(String param1, String param2) {
        TaskMapFragment fragment = new TaskMapFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map_task, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTaskListener) {
            mListener = (OnTaskListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnTaskListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
