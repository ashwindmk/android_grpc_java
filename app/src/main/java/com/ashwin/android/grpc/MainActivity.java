package com.ashwin.android.grpc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.ashwin.android.grpc.databinding.ActivityMainBinding;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String SUB_TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private HouseRepository houseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        houseRepository = new HouseRepository();

        binding.fetchButton.setOnClickListener(v -> {
            getHouses(Arrays.asList(1, 2, 3));
        });
    }

    private void getHouses(List<Integer> ids) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<House> houses = houseRepository.getHouses(ids);
                Log.d(Constant.APP_TAG, SUB_TAG + ": houses: " + houses);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Set the result in UI.
                    }
                });
            }
        }).start();
    }

    private void getHouses() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<House> houses = houseRepository.getHouses();
                Log.d(Constant.APP_TAG, SUB_TAG + ": houses: " + houses);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Set the result in UI.
                    }
                });
            }
        }).start();
    }
}
