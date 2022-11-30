package com.example.peliculas.factories;


import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    SharedPreferences sharedPreferences;

    public RetrofitFactory(Context context){
        sharedPreferences = context.getSharedPreferences("com.example.peliculas.factories", Context.MODE_PRIVATE);
    }

    public Retrofit build() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                       // .header("Authorization", sharedPreferences.getString("AUTORIZATION", null))
                        .method(original.method(),original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://6359396bff3d7bddb99c754a.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        return retrofit;
    }

}
