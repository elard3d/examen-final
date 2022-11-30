package com.example.peliculas.services;

import com.example.peliculas.entities.Cuenta;
import com.example.peliculas.entities.Pelicula;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PeliculaService {
    //https://6359396bff3d7bddb99c754a.mockapi.io/api/v1/:endpoint
    @GET("api/v1/peliculas")
    Call<List<Pelicula>> getAll();

    @GET("Cuenta")
    Call<List<Cuenta>> mostrrListaCuenta();

    @POST("Cuenta")
    Call<Void> publicarCuenta(@Body Cuenta cuenta);

    @GET("/Cuenta/{id}")
    Call<Cuenta> getLibro(@Path("id") String id);
}
