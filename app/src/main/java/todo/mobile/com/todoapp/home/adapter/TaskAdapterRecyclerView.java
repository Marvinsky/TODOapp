package todo.mobile.com.todoapp.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import todo.mobile.com.todoapp.R;
import todo.mobile.com.todoapp.model.Task;

/**
 * Created by mabisrror on 1/24/17.
 */

public class TaskAdapterRecyclerView extends RecyclerView.Adapter<TaskAdapterRecyclerView.TasksViewHolder>{
    private ArrayList<Task> tasks;
    private int resource;

    public TaskAdapterRecyclerView(ArrayList<Task> tasks, int resource) {
        this.tasks = tasks;
        this.resource = resource;
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
        private TextView tvTitle;
        private TextView tvContent;
        private TextView tvCategory;

        public TasksViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView)itemView.findViewById(R.id.lblTitle);
            tvContent = (TextView)itemView.findViewById(R.id.lblContent);
            tvCategory = (TextView)itemView.findViewById(R.id.lblCategory);
        }

        public void bindTask(Task task) {
            tvTitle.setText(task.getTitle());
            tvContent.setText(task.getContent());
            tvCategory.setText(task.getCategory());
        }
    }
}
