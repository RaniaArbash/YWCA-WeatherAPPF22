package com.example.ywca_weatherappf22;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

    public class CitiesAdapter extends
            RecyclerView.Adapter<CitiesAdapter.CityViewHolder> {

        interface ItemListener{
            void onClicked(int post);
        }

        Context context;
        ArrayList<City> list;
        ItemListener listener;

        public CitiesAdapter(Context context, ArrayList<City> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.city_row,parent,false);
            return new CityViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
            holder.cityText.setText(list.get(position).city);
            holder.countryText.setText(list.get(position).country);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        // inner class
        public class CityViewHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener
        {
            TextView cityText;
            TextView countryText;

            public CityViewHolder(@NonNull View itemView) {
                super(itemView);
                cityText =  itemView.findViewById(R.id.city);
                countryText =  itemView.findViewById(R.id.country);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                listener.onClicked( getAdapterPosition());

            }
        }


    }


