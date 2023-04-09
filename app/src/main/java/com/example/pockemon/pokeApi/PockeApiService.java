package com.example.pockemon.pokeApi;

import com.example.pockemon.models.Pokemon;
import com.example.pockemon.models.PokemonRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PockeApiService {

    @GET("pokemon")
    Call<PokemonRequest> ListPokemon();

    @GET("{indexpoke}/")
    Call<Pokemon> getPokemon(@Path("indexpoke") String indexpoke);
}
