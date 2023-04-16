package com.example.pokemon_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pokemon_app.Adapter.PokemonListadapter;
import com.example.pokemon_app.Common.Common;
import com.example.pokemon_app.Common.ItemOffsetDecoration;
import com.example.pokemon_app.Model.Pokedex;
import com.example.pokemon_app.Retrofit.IPokemonDex;
import com.example.pokemon_app.Retrofit.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonList extends Fragment {




    // TODO: Rename and change types of parameters

    static PokemonList instance;
    IPokemonDex iPokemonDex;
    CompositeDisposable compositeDisposable =new CompositeDisposable();
    RecyclerView pokemon_list_recyclerview;



    public PokemonList() {
        Retrofit retrofit = RetrofitClient.getInstance();
        iPokemonDex = retrofit.create(IPokemonDex.class);
    }


    // TODO: Rename and change types and number of parameters
    public static PokemonList getInstance(){
        if (instance == null)
            instance=new PokemonList();

        return instance;
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);
        pokemon_list_recyclerview=(RecyclerView)view.findViewById(R.id.pokemon_list_recyclerview);
        pokemon_list_recyclerview.setHasFixedSize(true);
        pokemon_list_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2));
        ItemOffsetDecoration itemOffsetDecoration = new ItemOffsetDecoration(getActivity(),R.dimen.spacing);
        pokemon_list_recyclerview.addItemDecoration(itemOffsetDecoration);

        fetchData();
        return view;
    }
    private void fetchData(){
        compositeDisposable.add(iPokemonDex.getListPokemon()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Pokedex>() {
                    @Override
                    public void accept(Pokedex pokedex) throws Exception {
                        Common.commonPokemonList = pokedex.getPokemon();
                        PokemonListadapter adapter = new PokemonListadapter(getActivity(), Common.commonPokemonList);
                        pokemon_list_recyclerview.setAdapter(adapter);
                    }
                })
        );
    }
}