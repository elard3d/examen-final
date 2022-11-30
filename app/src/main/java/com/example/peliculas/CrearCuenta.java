package com.example.peliculas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.peliculas.entities.Cuenta;
import com.example.peliculas.entities.Pelicula;
import com.example.peliculas.factories.RetrofitFactory;
import com.example.peliculas.services.PeliculaService;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrearCuenta extends AppCompatActivity {

    private Pelicula pelicula = new Pelicula();
    private EditText txtNombrePelicula;
    private EditText txtDescripcionPelicula;
    private Button btnRegistrar;
    private Button btnRegresar;

    private ImageView ivPhoto;

    private Retrofit retrofit;

    private EditText monto;
    private EditText motivo;
    private EditText lag;
    private EditText Long;
    String locationString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_pelicula);


        String[] type = new String[]{"Ingreso", "Gasto"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.drop_down_item,
                type
        );

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.localidadif);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener((adapterView, view, i, l) -> locationString = autoCompleteTextView.getText().toString());

        btnRegresar = findViewById(R.id.registrar);
        monto = findViewById(R.id.monto);
        motivo = findViewById(R.id.motivo);
        lag = findViewById(R.id.lag);
        Long = findViewById(R.id.longi);

         retrofit = new Retrofit.Builder()
                .baseUrl("https://6387785ee399d2e4730019d2.mockapi.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        btnRegresar.setOnClickListener(view -> getData());

    }
    private  void getData(){

        String montoG = monto.getEditableText().toString();
        String motivoG = motivo.getEditableText().toString();
        String lagG = lag.getEditableText().toString();
        String longG = Long.getEditableText().toString();


        Cuenta cuenta = new Cuenta();
        cuenta.setMonto(Integer.parseInt(montoG));
        cuenta.setMotivo(motivoG);
        cuenta.setMontoTipo(locationString);
        cuenta.setLat(lagG);
        cuenta.setLong(longG);

        PeliculaService cuentas = retrofit.create(PeliculaService.class);
        Call<Void> resp = cuentas.publicarCuenta(cuenta);

        resp.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                    onBackPressed();

            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}