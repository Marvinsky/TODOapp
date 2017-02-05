package todo.mobile.com.todoapp.home.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import todo.mobile.com.todoapp.R;
import todo.mobile.com.todoapp.details.TaskContainerActivity;
import todo.mobile.com.todoapp.details.fragment.TaskDetailFragment;
import todo.mobile.com.todoapp.details.fragment.TaskEditFragment;
import todo.mobile.com.todoapp.home.fragment.HomeFragment;
import todo.mobile.com.todoapp.listeners.OnTaskListener;
import todo.mobile.com.todoapp.model.Task;

/**
 * Created by mabisrror on 1/24/17.
 */

public class TaskAdapterRecyclerView extends RecyclerView.Adapter<TaskAdapterRecyclerView.TasksViewHolder>{
    private ArrayList<Task> tasks;
    private int resource;
    Activity activity;
    OnTaskListener mListener;

    public TaskAdapterRecyclerView(Activity activity, ArrayList<Task> tasks, int resource, OnTaskListener listener) {
        this.activity = activity;
        this.tasks = tasks;
        this.resource = resource;
        this.mListener = listener;
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.bindTask(task);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class TasksViewHolder extends RecyclerView.ViewHolder {
        private ImageView iviPicture;
        private TextView tvTitle;
        private TextView tvContent;
        private TextView tvCategory;

        public TasksViewHolder(View itemView) {
            super(itemView);
            iviPicture = (ImageView)itemView.findViewById(R.id.imageCard);
            tvTitle = (TextView)itemView.findViewById(R.id.lblTitle);
            tvContent = (TextView)itemView.findViewById(R.id.lblContent);
            tvCategory = (TextView)itemView.findViewById(R.id.lblCategory);
        }

        public void bindTask(final Task task) {
            tvTitle.setText(task.getTitle());
            tvContent.setText(task.getContent());
            tvCategory.setText(task.getCategory());
            Picasso.with(activity).load(task.getImageUrl()).into(iviPicture);
            iviPicture.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if (mListener != null && task != null) {
                        mListener.selectedItemTask(task);
                    }
                    /*Intent intent = new Intent(activity, TaskContainerActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("TASK_DETAIL", task);
                    intent.putExtras(bundle);
                    activity.startActivity(intent);
                    */
                }
            });


        }
    }
}
