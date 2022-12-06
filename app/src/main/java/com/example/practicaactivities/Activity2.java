package com.example.practicaactivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        TextView textView_welcome = findViewById(R.id.welcome);
        Intent intentUsuario = getIntent();

        Usuario usuario = (Usuario) intentUsuario.getSerializableExtra("usuario");

        String nombre = usuario.getNombre();
        String nick = usuario.getNick();
        String welcome = "bienvenid" + (usuario.getSexo() == 'h' ? "o" : "a");

        textView_welcome.setText(nombre + " (" + nick + ") " + welcome + ". Sus pedidos");
    }
}