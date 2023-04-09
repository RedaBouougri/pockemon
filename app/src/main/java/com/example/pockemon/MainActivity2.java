package com.example.pockemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pockemon.models.Pokemon;
import com.example.pockemon.models.PokemonRequest;
import com.example.pockemon.pokeApi.PockeApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {

    private Retrofit retrofit;

    private ImageView img;

    private RecyclerView recyclerView;
    private PokemonAdapter listaPokemonAdapter;
    private static final String TAG = "POKEDEX";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.recyclerView);
        listaPokemonAdapter = new PokemonAdapter(this);
        recyclerView.setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(MainActivity2.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent2 = new Intent(MainActivity2.this,details.class);
                intent2.putExtra("index",String.valueOf(position+1));

                img= findViewById(R.id.fotoImageView);
                //String value = img.getBackground();
                //ImageView imageView = findViewById(R.id.Imagetest1);
                //Drawable drawable = imageView.getBackground();
                //String color = getColorFromDrawable(drawable);
                //GradientDrawable drawable2 = (GradientDrawable) img.getBackground();
                // intent2.putExtra("color",String.valueOf(drawable2.getColor().getDefaultColor()));
                // int randomColor = Integer.parseInt(String.valueOf(drawable2.getColor().getDefaultColor()));
                // int colorrgb = randomColor;
                // int red = (colorrgb >> 16) & 0xFF;
                //int green = (colorrgb >> 8) & 0xFF;
                //int blue = colorrgb & 0xFF;
                //Log.e(TAG," MainActivity: " + red + "," + green + "," + blue );
                //drawable2.getColor();
                //Log.e(TAG," Mainactivity: "  + String.valueOf(drawable2.getColor().getDefaultColor()) );
                // Get the ImageView
                //int color = drawable.getColor();
                //String hexColorCode = String.format("#%06X", (0xFFFFFF & colorCode));
                //Log.e(TAG," onResponse: " + imageView.getId() );
                startActivity(intent2);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                // ...
            }
        }));

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getData();

    }
    private void getData() {
        PockeApiService service = retrofit.create(PockeApiService.class);
        Call<PokemonRequest> pokemonRespuestaCall = service.ListPokemon();
        pokemonRespuestaCall.enqueue(new Callback<PokemonRequest>() {
            @Override
            public void onResponse(Call<PokemonRequest> call, Response<PokemonRequest> response) {

                if (response.isSuccessful()) {
                    PokemonRequest pokemonRespuesta = response.body();
                    ArrayList<Pokemon> listaPokemon = pokemonRespuesta.getResults();
                    listaPokemonAdapter.adicionarListaPokemon(listaPokemon);

                    for (int i = 0; i < listaPokemon.size(); i++) {
                        Pokemon p = listaPokemon.get(i);
                        Log.i(TAG, "Pokemeon : " + p.getName());
                    }
                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonRequest> call, Throwable t) {


                Log.e(TAG, " onFailure: " + t.getMessage());

            }
        });}

    public void sendMessage(View view) {
        TextView nombreTextView;
        nombreTextView = findViewById(R.id.nombreTextView);
        String txt = (String) nombreTextView.getText();
        Intent i = new Intent(this, details.class);
        i.putExtra("key",txt);
        startActivity(i);


    }
}