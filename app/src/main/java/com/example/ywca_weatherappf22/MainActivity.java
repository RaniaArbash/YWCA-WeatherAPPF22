package com.example.ywca_weatherappf22;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
implements NetworkingService.NetworkingListener , CitiesAdapter.ItemListener,
    DBManager.DataBaseListener
{

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
        this.setTitle("Search For Cities....");
        adapter.listener = this;
        ((MyApp)getApplication()).dbManager.listener = this;
        ((MyApp)getApplication()).dbManager.getDB(this);
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
    public void gettingImageIsCompleted(Bitmap image) {

    }

    @Override
    public void onClicked(int post) {
        // show and alert
        // save the city
        showAlert(list.get(post));
    }

    void showAlert(City city){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to save "+ city.city+" to the DB??");
        builder.setNegativeButton("No",null);
        builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ((MyApp)getApplication()).dbManager.insertNewCityAsync(city);
            }
        });
        builder.create().show();

    }

    @Override
    public void insertingCityCompleted() {
        finish();
    }

    @Override
    public void gettingCitiesCompleted(City[] list) {

    }
}