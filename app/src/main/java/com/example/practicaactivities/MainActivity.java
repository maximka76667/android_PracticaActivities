package com.example.practicaactivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText_nick = findViewById(R.id.et_login_nick);
        EditText editText_contrasena = findViewById(R.id.et_login_clave);
        Button button_entrar = findViewById(R.id.bt_login_Entrar);
        TextView textView_registrarse = findViewById(R.id.tv_login_registrarse);

        button_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editText_nick.getText().toString().equals("neo") || !editText_contrasena.getText().toString().equals("sesamo")) {
                    Toast.makeText(MainActivity.this, "Nick y/o contraseña no válido/s", Toast.LENGTH_LONG).show();
                    return;
                }

                Usuario usuario = new Usuario("neo", "Andrés", "Harminio Jiménez", 'h');
                startUserActivity(usuario);
            }
        });

        textView_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity3.class);
                intent.setAction("Registrarse");
                startActivityForResult(intent, 3);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {
            return;
        }

        if (requestCode == 3) {
            startUserActivity((Usuario) data.getSerializableExtra("usuario"));
            return;

        }
    }

    public void startUserActivity(Usuario usuario) {
        LoginTask loginTask = new LoginTask(new ILogin() {
            @Override
            public void onLogin(Usuario usuario) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("usuario", usuario);
                startActivity(intent);
            }
        });
        loginTask.execute(usuario);
    }

    interface ILogin {
        void onLogin(Usuario usuario);
    }

    class LoginTask extends AsyncTask<Usuario, Void, Usuario> {

        ILogin loginController;
        ProgressDialog progressDialog;

        LoginTask(ILogin loginController) {
            this.loginController = loginController;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Login...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
        }

        @Override
        protected Usuario doInBackground(Usuario... usuarios) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return usuarios[0];
        }

        @Override
        protected void onPostExecute(Usuario usuario) {
            super.onPostExecute(usuario);
            progressDialog.dismiss();
            loginController.onLogin(usuario);
        }
    }
}
