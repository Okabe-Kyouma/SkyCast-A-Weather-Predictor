package com.example.skycast.DailyRecycleView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skycast.HourlyRecyclerView.HourForecastModel;
import com.example.skycast.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingAdapter.ProgrammingViewHolder> {

    List<DailyForecastModel> dailyForecastModelList;

    public ProgrammingAdapter(List<DailyForecastModel> dailyForecastModelList){
        this.dailyForecastModelList = dailyForecastModelList;
    }



    @NonNull
    @Override
    public com.example.skycast.DailyRecycleView.ProgrammingAdapter.ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dailyforecastdisplay,parent,false);

        return new ProgrammingViewHolder(view);



    }

    @Override
    public void onBindViewHolder(@NonNull com.example.skycast.DailyRecycleView.ProgrammingAdapter.ProgrammingViewHolder holder, int position) {

        Log.d("huehue"," size of time " + dailyForecastModelList.get(position).getTime().length());

        holder.hour2.setText(dailyForecastModelList.get(position).getTime().substring(10,16));

        holder.daily_forecast_date.setText(dailyForecastModelList.get(position).getTime().substring(5,10));

        Picasso.get().load("http:" + dailyForecastModelList.get(position).getCondition().getIcon()).into(holder.img2);

        holder.condition2.setText(dailyForecastModelList.get(position).getCondition().getText());

        holder.temp2.setText(String.valueOf(dailyForecastModelList.get(position).getTemp_c()) + "\u00B0");

    }

    @Override
    public int getItemCount() {
        return dailyForecastModelList.size();
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder{

        TextView hour2,condition2,temp2,daily_forecast_date;
        ImageView img2;

        public ProgrammingViewHolder(@NonNull View itemView) {
            super(itemView);

            hour2 = itemView.findViewById(R.id.hour2);
            img2 = itemView.findViewById(R.id.img2);
            condition2 = itemView.findViewById(R.id.condition2);
            temp2 = itemView.findViewById(R.id.temp2);
            daily_forecast_date = itemView.findViewById(R.id.daily_forecast_date);

        }
    }

}
