package com.habitree.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.habitree.R;
import com.habitree.models.Habit;
import com.habitree.models.Journal;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HabitRecyclerAdapter extends RecyclerView.Adapter<HabitRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Habit> habitList;

    public HabitRecyclerAdapter(Context context, List<Habit> habitList) {
        this.context = context;
        this.habitList = habitList;
    }

    @NonNull
    @Override
    public HabitRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.habit_row, viewGroup, false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitRecyclerAdapter.ViewHolder viewHolder, int position) {

        Habit habit = habitList.get(position);

        viewHolder.title.setText(habit.getHabitName());
        viewHolder.desc.setText(habit.getHabitDesc());
    }

    @Override
    public int getItemCount() {
        return habitList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView
                title,
                desc;
        String userId;
        String username;


        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            context = ctx;

            title = itemView.findViewById(R.id.habit_title_list);
            desc = itemView.findViewById(R.id.habit_desc_list);
        }
    }
}
