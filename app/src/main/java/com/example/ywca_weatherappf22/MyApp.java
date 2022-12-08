package com.example.ywca_weatherappf22;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyApp extends Application {


   static ExecutorService executorService = Executors.newFixedThreadPool(4);
    NetworkingService networkingService = new NetworkingService();
    DBManager dbManager = new DBManager();
}
