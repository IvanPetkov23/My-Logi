package com.example.mylogi.services;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mylogi.R;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpRequest {
    final String apiKey = "&appid=c5347555403ae8f6b021ddd616d585f2";
    String url = "https://api.openweathermap.org/data/2.5/weather?";

    public void weatherRequest(Context context, Location location, TextView cityTextView,
                               TextView temperatureTextView, ImageView weatherIcon) {
        url = url.concat("lat=" + location.getLatitude() + "&lon=" + location.getLongitude() + apiKey);

        RequestQueue queue = Volley.newRequestQueue(context);
        @SuppressLint("SetTextI18n") StringRequest request = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject weatherResponse = new JSONObject(response);
                        JSONObject main = weatherResponse.getJSONObject("main");

                        double temperature = main.getDouble("temp") - 273.15;
                        int parsedTemperature = (int) temperature;
                        String city = weatherResponse.getString("name");
                        JSONArray weather = weatherResponse.getJSONArray("weather");
                        JSONObject weatherDetails = weather.getJSONObject(0);
                        String weatherCode = weatherDetails.getString("icon");
                        Log.e("Weather Code: ", weatherCode);
                        if (weatherCode.equals("01d") || weatherCode.equals("01n")) {
                            weatherIcon.setImageResource(R.drawable.ic_baseline_wb_sunny_24);
                        } else {
                            weatherIcon.setImageResource(R.drawable.ic_baseline_wb_cloudy_24);
                        }


                        temperatureTextView.setText(parsedTemperature + "°C");
                        cityTextView.setText(city);

                        Log.d("Response success", parsedTemperature + " " + city);
                    } catch (JSONException e) {
                        Log.e("Response error", e.getMessage());
                    }
                }, error -> {
            Log.d("Request error", error.toString());

        });
        queue.add(request);
    }
}
