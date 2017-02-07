package todo.mobile.com.todoapp.details.fragment;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import todo.mobile.com.todoapp.R;
import todo.mobile.com.todoapp.listeners.OnTaskListener;
import todo.mobile.com.todoapp.model.Task;

public class TaskDetailFragment extends Fragment {

    ImageView iviMainImage;
    TextView tvTitle, tvCategory, tvColor, tvDate, tvDescription;
    EditText etTitle, etCategory, etDate, etColor, etDescription;

    Task task;
    OnTaskListener mListener;

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


        etTitle = (EditText)view.findViewById(R.id.etTaskTitle);
        etCategory = (EditText)view.findViewById(R.id.etCategory);
        etDate = (EditText)view.findViewById(R.id.etDate);
        etColor = (EditText)view.findViewById(R.id.etColor);
        etDescription = (EditText)view.findViewById(R.id.etDescription);

        Picasso.with(getActivity()).load(task.getImageUrl()).into(iviMainImage);
        tvTitle.setText(task.getTitle());
        tvCategory.setText(task.getCategory());
        tvColor.setText(task.getColor());
        tvDate.setText(task.getCreatedDate());
        tvDescription.setText(task.getContent());


        hideEditText(etTitle);
        hideEditText(etCategory);
        hideEditText(etDate);
        hideEditText(etColor);
        hideEditText(etDescription);

        tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideTextView(tvTitle);
                showEditText(etTitle);
                etTitle.setText(task.getTitle());
            }
        });

        tvCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideTextView(tvCategory);
                showEditText(etCategory);
                etCategory.setText(task.getCategory());
            }
        });

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideTextView(tvDate);
                showEditText(etDate);
                etDate.setText(task.getCreatedDate());
            }
        });

        tvColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideTextView(tvColor);
                showEditText(etColor);
                etColor.setText(task.getColor());
            }
        });

        tvDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideTextView(tvDescription);
                showEditText(etDescription);
                etDescription.setText(task.getContent());
            }
        });
    }

    private void hideEditText(EditText editText) {
        editText.setVisibility(View.GONE);
    }

    private void showEditText(EditText editText) {
        editText.setVisibility(View.VISIBLE);
    }

    private void hideTextView(TextView textView) {
        textView.setVisibility(View.GONE);
    }


    private void showTextView(TextView textView) {
        textView.setVisibility(View.VISIBLE);
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

    public void saveTask() {
        String title = null,
                category = null,
                color = null,
                date = null,
                description = null;
        Log.d("saveTask", "fragment");


        if (tvTitle.getVisibility() == View.VISIBLE) {
            title = tvTitle.getText().toString();
        } else {
            title = etTitle.getText().toString();
        }

        if (tvCategory.getVisibility() ==  View.VISIBLE) {
            category = tvCategory.getText().toString();
        } else {
            category = etCategory.getText().toString();
        }

        if (tvDate.getVisibility() == View.VISIBLE) {
            date = tvDate.getText().toString();
        } else {
            date = etDate.getText().toString();
        }

        if (tvColor.getVisibility() == View.VISIBLE) {
            color = tvColor.getText().toString();
        } else {
            color = etColor.getText().toString();
        }

        if (tvDescription.getVisibility() == View.VISIBLE) {
            description = tvDescription.getText().toString();
        } else {
            description = etDescription.getText().toString();
        }

        Log.d("title", title);
        Log.d("category", category);
        Log.d("date", date);
        Log.d("color", color);
        Log.d("description", description);
    }
}
