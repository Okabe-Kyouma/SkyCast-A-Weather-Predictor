package com.example.skycast.HourlyRecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skycast.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingAdapter.ProgrammingViewHolder> {

    List<HourForecastModel> hourForecastModelList;

    public ProgrammingAdapter(List<HourForecastModel> hourForecastModelList){
        this.hourForecastModelList = hourForecastModelList;
    }



    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.hourlyforecastdiplay,parent,false);

        return new ProgrammingViewHolder(view);



    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder holder, int position) {

        Log.d("huehue","position in recyler view: " + position + "ans size of list: " + hourForecastModelList.size());

        holder.hour1.setText(hourForecastModelList.get(position).getTime());

        Picasso.get().load("http:" + hourForecastModelList.get(position).getCondition().getIcon()).into(holder.img1);

        holder.condition1.setText(hourForecastModelList.get(position).getCondition().getText());

        holder.temp1.setText(String.valueOf(hourForecastModelList.get(position).getTemp_c()) + "\u00B0");

    }

    @Override
    public int getItemCount() {
        return hourForecastModelList.size();
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder{

        TextView hour1,condition1,temp1;
        ImageView img1;

        public ProgrammingViewHolder(@NonNull View itemView) {
            super(itemView);


            hour1 = itemView.findViewById(R.id.hour1);
            img1 = itemView.findViewById(R.id.img1);
            condition1 = itemView.findViewById(R.id.condition1);
            temp1 = itemView.findViewById(R.id.temp1);

        }
    }

}
