package com.example.pokemon_app.Retrofit;

import com.example.pokemon_app.Model.Pokedex;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IPokemonDex {
    @GET("pokedex.json")
    Observable<Pokedex>getListPokemon();
}
