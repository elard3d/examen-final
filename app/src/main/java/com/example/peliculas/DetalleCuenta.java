package com.example.peliculas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peliculas.entities.Cuenta;
import com.squareup.picasso.Picasso;

public class DetalleCuenta extends AppCompatActivity {


    Cuenta cuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pelicula);

        cuenta = (Cuenta) getIntent().getSerializableExtra("cuenta");

        ImageView imageView = findViewById(R.id.imageView);
        TextView tipoMonto = findViewById(R.id.tipoMonto);
        TextView monto = findViewById(R.id.monto);
        TextView montivo = findViewById(R.id.montivo);

        Picasso.get().load("https://economipedia.com/wp-content/uploads/Gastos-de-administraci%C3%B3n.jpg")
                .into(imageView);

        tipoMonto.setText(String.format("%s%s", "Movimiento: ",cuenta.getMontoTipo()));

        monto.setText(String.format("%s%s%s","Monto:", "S/",cuenta.getMonto()));
        montivo.setText(String.format("%s%s", "Motivo: ",cuenta.getMotivo()));

    }
}