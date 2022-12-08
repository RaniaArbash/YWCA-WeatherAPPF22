package com.example.ywca_weatherappf22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;

public class FavActivity extends AppCompatActivity
        implements DBManager.DataBaseListener, CitiesAdapter.ItemListener {
    CitiesAdapter adapter;
    ArrayList<City> clist = new ArrayList<>(0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        this.setTitle("Fav Cities");

        RecyclerView favList = findViewById(R.id.favcitiesList);
        adapter = new CitiesAdapter(this,clist);
        favList.setAdapter(adapter);
        adapter.listener = this;
        favList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((MyApp) getApplication()).dbManager.getDB(this);
        ((MyApp) getApplication()).dbManager.getAllCities();
        ((MyApp) getApplication()).dbManager.listener = this;

    }

    @Override
    public void insertingCityCompleted() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.add_menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.addButton:
                Intent i = new Intent(this,MainActivity.class);
                startActivity(i);
                break;
        };
        return true;
    }

    @Override
    public void gettingCitiesCompleted(City[] list) {
        clist = new ArrayList( Arrays.asList(list));
        adapter.list = clist;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClicked(int post) {
        Intent i = new Intent(this,WeatherActivity.class);
        i.putExtra("city", clist.get(post));
        startActivity(i);
    }
}