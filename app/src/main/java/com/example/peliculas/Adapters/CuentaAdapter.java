package com.example.peliculas.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peliculas.DetalleCuenta;
import com.example.peliculas.R;
import com.example.peliculas.entities.Cuenta;
import com.example.peliculas.entities.Pelicula;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CuentaAdapter extends RecyclerView.Adapter {

    List<Cuenta> peliculas;
    Context mContext;

    public CuentaAdapter(List<Cuenta> peliculas,Context mContext) {
        this.peliculas = peliculas;
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater _laLayoutInflater = LayoutInflater.from(parent.getContext());

        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_pelicula,parent,false);

        return new PeliculasVieHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        TextView txtNombre = holder.itemView.findViewById(R.id.txtNombrePelicula);
        ImageView imagenPelicula = holder.itemView.findViewById(R.id.imagenPelicula);
        Button btnDetalle = holder.itemView.findViewById(R.id.btnDetalle);

        Cuenta pelicula = peliculas.get(position);

        txtNombre.setText(pelicula.getMotivo());
        //imageView.setImageResource(R.drawable.hpfoto);
        Picasso.get().load("https://economipedia.com/wp-content/uploads/Gastos-de-administraci%C3%B3n.jpg")
                .into(imagenPelicula);

        btnDetalle.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, DetalleCuenta.class);
            intent.putExtra("cuenta",pelicula);
            mContext.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    class PeliculasVieHolder extends RecyclerView.ViewHolder{
        public PeliculasVieHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
