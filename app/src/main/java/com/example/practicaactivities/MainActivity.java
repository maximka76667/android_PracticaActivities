package com.example.practicaactivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText_nick = findViewById(R.id.nick);
        EditText editText_contrasena = findViewById(R.id.contrasena);
        Button button_entrar = findViewById(R.id.entrar);
        Button button_registrarse = findViewById(R.id.registrarse);

        button_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editText_nick.getText().toString().equals("neo") || !editText_contrasena.getText().toString().equals("sesamo")) {
                    Toast.makeText(MainActivity.this, "Nick y/o contraseña no válido/s", Toast.LENGTH_LONG).show();
                    return;
                }
                Usuario usuario = new Usuario("neo", "Andrés", "Harminio Jiménez", 'h');
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("usuario", usuario);
                startActivity(intent);
            }
        });

        button_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                startActivityForResult(intent, 3);
            }
        });
    }
}