package com.alquiler.ventadecoches;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Jaume on 3/12/16.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText usernameEd;
    private EditText passwordEd;

    private Button loginButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEd = (EditText) findViewById(R.id.username_ed);
        passwordEd = (EditText) findViewById(R.id.password_ed);

        loginButton = (Button) findViewById(R.id.login_button);
        registerButton = (Button) findViewById(R.id.register_button);

        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button: // Login pulsado
                String username = usernameEd.getText().toString();
                String password = passwordEd.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(AlquilerService.ENDPOINT)
                        .build();

                AlquilerService service = retrofit.create(AlquilerService.class);
                Call<ResponseBody> result = service.login(username, password);
                result.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            System.out.println(response.body().string().toString());
                        } catch (IOException | NullPointerException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.printStackTrace();
                    }
                });


                break;
            case R.id.register_button: // Registro pulsado
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
