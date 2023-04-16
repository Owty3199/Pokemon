package com.example.pokemon_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokemon_app.Model.Pokemon;
import com.example.pokemon_app.R;

import java.util.List;

public class PokemonListadapter extends RecyclerView.Adapter<PokemonListadapter.MyViewHolder> {

    Context context;
    List<Pokemon> pokemonlist;

    public PokemonListadapter(Context context, List<Pokemon> pokemonlist) {
        this.context = context;
        this.pokemonlist = pokemonlist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.pokemon_list_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //loading the image
        Glide.with(context).load(pokemonlist.get(position).getImg()).into(holder.pokemon_image);
        //setting the name
        holder.pokemon_name.setText(pokemonlist.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return pokemonlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView pokemon_image;
        TextView pokemon_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            pokemon_image = (ImageView) itemView.findViewById(R.id.pokemon_image);
            pokemon_name = (TextView) itemView.findViewById(R.id.txt_pokemon_name);
        }
    }
}