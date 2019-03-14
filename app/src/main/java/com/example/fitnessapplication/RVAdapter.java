package com.example.fitnessapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.WorkoutViewHolder>{



    public static class WorkoutViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView workoutName;
        TextView workoutReps;
        Button workoutButton;

        WorkoutViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            workoutName = (TextView)itemView.findViewById(R.id.workout_name);
            workoutReps = (TextView)itemView.findViewById(R.id.workout_reps);
            workoutButton = itemView.findViewById(R.id.workout_button);

        }
    }

    List<Workout> workouts;

    RVAdapter (List <Workout> workouts){
        this.workouts = workouts;
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    @Override
    public WorkoutViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        WorkoutViewHolder wvh = new WorkoutViewHolder(v);
        return wvh;
    }

    @Override
    public void onBindViewHolder (WorkoutViewHolder workoutViewHolder, int i) {
        workoutViewHolder.workoutName.setText(workouts.get(i).name);
        workoutViewHolder.workoutReps.setText(workouts.get(i).reps);

        workoutViewHolder.workoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CalendarPage.class);
                Bundle bundle = new Bundle();
                //bundle.putString("workoutRep", workouts.get(i).reps);

                //Date c = Calendar.getInstance().getTime();

                //SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                //String currentDate = df.format(c);
                Calendar day = Calendar.getInstance();
                MyEventDay myEventDay = new MyEventDay(day,
                        R.drawable.ic_message_black_48dp, workouts.get(i).reps);

                //bundle.putString("ADD", "ADD");
                //bundle.putParcelable("event", myEventDay)
                intent.putExtra("ADD", "ADD");
                intent.putExtra("event", myEventDay);




                //intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView (RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}