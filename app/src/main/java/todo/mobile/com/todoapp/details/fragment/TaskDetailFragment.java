package todo.mobile.com.todoapp.details.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import todo.mobile.com.todoapp.R;
import todo.mobile.com.todoapp.model.Task;

public class TaskDetailFragment extends Fragment {

    ImageView iviMainImage;
    TextView tvTitle, tvCategory, tvColor, tvDate, tvDescription;
    Task task;

    public TaskDetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_detail, container, false);
        showToolbar(getResources().getString(R.string.toolbar_title_detail), true, view);
        setHasOptionsMenu(true);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            task = (Task) bundle.getSerializable("TASK_DETAIL");
        }

        ui(view);
        return view;
    }


    public void showToolbar(String title, boolean upButton, View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    private void ui(View view) {
        iviMainImage = (ImageView)view.findViewById(R.id.iviTaskImage);
        tvTitle = (TextView)view.findViewById(R.id.tviTaskTitle);
        tvCategory = (TextView)view.findViewById(R.id.tviCategory);
        tvColor = (TextView)view.findViewById(R.id.tviColor);
        tvDate = (TextView)view.findViewById(R.id.tviDate);
        tvDescription = (TextView)view.findViewById(R.id.tviDescription);

        Picasso.with(getActivity()).load(task.getImageUrl()).into(iviMainImage);
        tvTitle.setText(task.getTitle());
        tvCategory.setText(task.getCategory());
        tvColor.setText(task.getColor());
        tvDate.setText(task.getCreatedDate());
        tvDescription.setText(task.getContent());
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.menu_detail, menu);
    }
}
