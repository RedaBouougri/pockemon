package com.example.pockemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pockemon.models.Pokemon;
import com.example.pockemon.models.PokemonRequest;
import com.example.pockemon.pokeApi.PockeApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class details extends AppCompatActivity {

    private Retrofit retrofit;

    private static final String TAG = "POKEDEX";

    private PokemonAdapter listaPokemonAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String id = extras.getString("index");

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://pokeapi.co/api/v2/pokemon/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            getData(id);
        }
    }

    private void getData(String pos) {
        PockeApiService service = retrofit.create(PockeApiService.class);
        Call<Pokemon> poke = service.getPokemon(pos);
        poke.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {

                TextView txt = findViewById(R.id.txt1);
                TextView txt4 = findViewById(R.id.txt4);
                TextView txt2 = findViewById(R.id.txt2);
                ImageView img = findViewById(R.id.img1);
                Pokemon p = response.body();
                txt.setText(p.getName());
                txt2.setText("Height : "+p.getHeight()+" M");
                txt4.setText("Weight : "+p.getWeight()+" Kg");


                Glide.with(details.this).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+pos+".png").into(img);

            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {

            }
        });

    }

    public void name() {

    }
}