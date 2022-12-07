package com.example.practicaactivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {

    TextView textView_welcome = null;
    Usuario usuario = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        textView_welcome = findViewById(R.id.welcome);

        Intent intentUsuario = getIntent();
        updateUser(intentUsuario);
    }

    public void updateUser(Intent intent) {
        usuario = (Usuario) intent.getSerializableExtra("usuario");

        String nombre = usuario.getNombre();
        String nick = usuario.getNick();
        String welcome = "bienvenid" + (usuario.getSexo() == 'h' ? "o" : "a");
        textView_welcome.setText(nombre + " (" + nick + ") " + welcome + ".\nSus pedidos");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_pedidos, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.perfil:
                Intent updateProfileIntent = new Intent(Activity2.this, Activity3.class);
                updateProfileIntent.setAction("Actualizar perfil");
                updateProfileIntent.putExtra("usuario", usuario);
                startActivityForResult(updateProfileIntent, 5);
                break;

            case R.id.contactar:
                Intent linkIntent = new Intent("http://www.evay.net");
                startActivity(linkIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }

        if (requestCode == 5) {
            updateUser(data);
        }
    }
}