package com.example.peliculas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.peliculas.Adapters.CuentaAdapter;
import com.example.peliculas.entities.Cuenta;
import com.example.peliculas.entities.Pelicula;
import com.example.peliculas.factories.RetrofitFactory;
import com.example.peliculas.services.PeliculaService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaCuentas extends AppCompatActivity {

    Retrofit retrofit;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_peliculas);


        Button btnCrearPelicula = findViewById(R.id.btnCrearPelicula);
        Button sincronizar = findViewById(R.id.sincronizar);

        sincronizar.setOnClickListener(view -> sincronizarLista());

        btnCrearPelicula.setOnClickListener(view -> {
            Intent intent = new Intent(ListaCuentas.this, CrearCuenta.class);
            startActivity(intent);
        });

        recyclerView = findViewById(R.id.lista);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

         retrofit = new Retrofit.Builder()
                .baseUrl("https://6387785ee399d2e4730019d2.mockapi.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         sincronizarLista();


    }

    private void sincronizarLista() {

        PeliculaService peliculas = retrofit.create(PeliculaService.class);

        Call<List<Cuenta>> listGet = peliculas.mostrrListaCuenta();

        listGet.enqueue(new Callback<List<Cuenta>>() {
            @Override
            public void onResponse(Call<List<Cuenta>> call, Response<List<Cuenta>> response) {
                List<Cuenta> list = new ArrayList<>();
                String code = String.valueOf(response.code());
                Log.e("cosde:", code);
                list = response.body();
                CuentaAdapter adapterList = new CuentaAdapter(list, ListaCuentas.this);
                recyclerView.setAdapter(adapterList);
                Toast.makeText(getApplicationContext(), "Lsita  sincronizada", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Cuenta>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "ERROR REVISAR ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}