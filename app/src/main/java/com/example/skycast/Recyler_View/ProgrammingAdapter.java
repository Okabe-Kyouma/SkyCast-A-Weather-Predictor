package com.example.skycast.Recyler_View;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skycast.CitySearch.OnItemClickListener;
import com.example.skycast.MainActivity;
import com.example.skycast.R;

import org.w3c.dom.Text;

import java.util.List;

public class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingAdapter.ProgrammingVewHolder> {

    private List<CityList> data;
    OnItemClickListener listener;
    public ProgrammingAdapter(List<CityList> data,OnItemClickListener listener){

        this.data = data;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ProgrammingVewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.autocomplete_city_names,parent,false);

        return new ProgrammingVewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingVewHolder holder, int position) {

        String cityName = String.valueOf(data.get(position).cityName);
        String stateName = String.valueOf(data.get(position).stateName);
        String countryName = String.valueOf(data.get(position).countryName);

        holder.city_Name.setText(cityName);
        holder.state_Name.setText(stateName);
        holder.country_Name.setText(countryName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("huehue"," the Touched city Name is : " + cityName);

                MainActivity.setSelectedCityFromRecycleView(cityName);


               listener.OnItemClick(cityName);

            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ProgrammingVewHolder extends RecyclerView.ViewHolder{

        TextView city_Name;
        TextView state_Name;
        TextView country_Name;

        public ProgrammingVewHolder(@NonNull View itemView) {
            super(itemView);

            city_Name = itemView.findViewById(R.id.city_Name);
            state_Name = itemView.findViewById(R.id.state_Name);
            country_Name = itemView.findViewById(R.id.country_Name);


        }


    }

//    public interface OnItemClickListener {
//        void onItemClick(int position);
//    }




}
