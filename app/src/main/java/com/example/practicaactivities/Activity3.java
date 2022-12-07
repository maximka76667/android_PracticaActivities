package com.example.practicaactivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        EditText editText_nick = (EditText) findViewById(R.id.tv_regOperfil_nick);
        EditText editText_nombre = (EditText) findViewById(R.id.tv_regOperfil_nombre);
        EditText editText_apellidos = (EditText) findViewById(R.id.tv_regOperfil_apellidos);
        RadioGroup radioGroup_sexo = (RadioGroup) findViewById(R.id.rg_regOperfil_sexo);

        Button button_aceptar = findViewById(R.id.bt_regOPerfil_Aceptar);
        Button button_cancelar = findViewById(R.id.bt_regOperfil_Cancelar);

        Intent profileIntent = getIntent();
        String intentAction = profileIntent.getAction();
        Usuario usuario = (Usuario) profileIntent.getSerializableExtra("usuario");

        ((TextView) findViewById(R.id.cabecera)).setText(intentAction);

        if (usuario != null) {
            editText_nick.setText(usuario.getNick());
            editText_nombre.setText(usuario.getNombre());
            editText_apellidos.setText(usuario.getApellidos());
            radioGroup_sexo.check(usuario.getSexo() == 'h' ? R.id.rg_regOperfil_rb_hombre : R.id.rg_regOperfil_rb_mujer);
        }

        button_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nick = editText_nick.getText().toString();
                String nombre = editText_nombre.getText().toString();
                String apellidos = editText_apellidos.getText().toString();
                char sexo = radioGroup_sexo.getCheckedRadioButtonId() == R.id.rg_regOperfil_rb_hombre ? 'h' : 'm';

                Usuario usuarioActualizado = new Usuario(nick, nombre, apellidos, sexo);
                Intent updateUserIntent = new Intent();
                updateUserIntent.putExtra("usuario", usuarioActualizado);
                setResult(RESULT_OK, updateUserIntent);
                finish();
            }
        });

        button_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}