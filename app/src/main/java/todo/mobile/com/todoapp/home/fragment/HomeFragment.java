package todo.mobile.com.todoapp.home.fragment;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
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
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.Toast;

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

    ImageButton fab;
    ViewGroup fabContainer;
    private boolean expanded = false;
    private View fabAction1;
    private View fabAction2;
    private float offset1;
    private float offset2;
    private final String TRANSLATION_Y = "translationY";

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
        init(view);

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

    private void init(View view) {
        fab = (ImageButton)view.findViewById(R.id.fab);
        fabContainer = (ViewGroup) view.findViewById(R.id.fab_container);
        fabAction1 = (View)view.findViewById(R.id.fab_action_1);
        fabAction2 = (View)view.findViewById(R.id.fab_action_2);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expanded = !expanded;
                if (expanded) {
                    expandFab();
                } else {
                    collapseFab();
                }
            }
        });

        fabContainer.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                fabContainer.getViewTreeObserver().removeOnPreDrawListener(this);
                offset1 = fab.getY() - fabAction1.getY();
                fabAction1.setTranslationY(offset1);

                offset2 = fab.getY() - fabAction2.getY();
                fabAction2.setTranslationY(offset2);

                return true;
            }
        });

        fabAction1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mListener.navigateNewTask();
            }
        });


        fabAction2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mListener.navigateProfile();
            }
        });


    }

    private void collapseFab() {
        fab.setImageResource(R.drawable.animated_plus);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(createCollapseAnimator(fabAction1, offset1),
                createCollapseAnimator(fabAction2, offset2));
        animatorSet.start();
        animateFab();
    }

    private void expandFab() {
        fab.setImageResource(R.drawable.animated_minus);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(createExpandAnimator(fabAction1, offset1),
                createExpandAnimator(fabAction2, offset2));
        animatorSet.start();
        animateFab();
    }

    private  void animateFab() {
        Drawable drawable = fab.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }

    private Animator createExpandAnimator(View view, float offset) {
        return ObjectAnimator.ofFloat(view, TRANSLATION_Y, offset, 0)
                .setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
    }

    private Animator createCollapseAnimator(View view, float offset) {
        return ObjectAnimator.ofFloat(view, TRANSLATION_Y, 0, offset)
                .setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
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
