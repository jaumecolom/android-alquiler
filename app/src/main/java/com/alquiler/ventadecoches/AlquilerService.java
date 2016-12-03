package com.alquiler.ventadecoches;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Jaume on 3/12/16.
 */

public interface AlquilerService {

    public static String ENDPOINT = "http://localhost:9000";

    @POST("/login")
    Call<ResponseBody> login(@Field("usuario") String usuario, @Field("password") String password);

    @POST("/register")
    Call<ResponseBody> register(@Field("nombre") String nombre, @Field("usuario") String usuario, @Field("password") String password, @Field("email") String email, @Field("edad") String edad);

    @GET("/search")
    Call<ResponseBody> search(@Field("busqueda") String busqueda);
}
