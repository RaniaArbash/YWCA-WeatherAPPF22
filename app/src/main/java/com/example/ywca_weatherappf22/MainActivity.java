package com.example.ywca_weatherappf22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
implements NetworkingService.NetworkingListener , CitiesAdapter.ItemListener{

    RecyclerView cityList;
    CitiesAdapter adapter;
    ArrayList<City> list = new ArrayList<>(0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((MyApp) getApplication()).networkingService.listener = this;
        cityList = findViewById(R.id.cities_list);
        adapter = new CitiesAdapter(this,list);
        adapter.listener = this;
        cityList.setAdapter(adapter);
        cityList.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.city_search_menu, menu);
        MenuItem searchViewMenu = menu.findItem(R.id.city_searchview);

        SearchView searchView = (SearchView) searchViewMenu.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
              //  Log.d("Donation app submit",query);


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //  Log.d("Donation app change",newText);

                if (newText.length() >= 3){
                    ((MyApp) getApplication()).networkingService.getAllCities(newText);
                }
                else {
                    adapter.list = new ArrayList<>(0);
                    adapter.notifyDataSetChanged();
                }

                return false;
            }
        });


        return true;
    }

    @Override
    public void gettingJsonIsCompleted(String json) {
        // json is a string ==> Json Array of Strings
        // for Recycler view I need ArrayList <City>
        list = JsonService.fromJSONToList(json);
        adapter.list = list;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClicked(int post) {
        Intent i = new Intent(this,WeatherActivity.class);
        i.putExtra("city", list.get(post));
        startActivity(i);
    }
}