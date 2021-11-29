package com.xuancanhit.dogapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.xuancanhit.dogapi.model.Dog;
import com.xuancanhit.dogapi.retrofit.APIUtils;
import com.xuancanhit.dogapi.retrofit.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    TextView tvURL, tvStatus;
    ImageView ivImageDog;
    Button btnRandom;

    Dog dog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        //Button Random
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomDog();
            }
        });


    }

    private void randomDog() {
        DataClient dataClient = APIUtils.getData();
        retrofit2.Call<Dog> callback = dataClient.DogData();
        callback.enqueue(new Callback<Dog>() {
            @Override
            public void onResponse(Call<Dog> call, Response<Dog> response) {
                dog = (Dog) response.body();
                tvURL.setText(dog.getMessage());
                tvStatus.setText(dog.getStatus());

                Picasso.get()
                        .load(dog.getMessage())
                        .into(ivImageDog);
            }

            @Override
            public void onFailure(Call<Dog> call, Throwable t) {
                Log.d("Err Load: ", t.getMessage());
                Toast.makeText(MainActivity.this, "Failed when load data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initUI() {
        tvURL = findViewById(R.id.tv_message);
        tvStatus = findViewById(R.id.tv_status);
        btnRandom = findViewById(R.id.btn_random);
        ivImageDog = findViewById(R.id.iv_image_dog);
    }
}