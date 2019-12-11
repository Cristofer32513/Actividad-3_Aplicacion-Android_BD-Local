package itsj.programas.escuela_bd_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import database.DataBaseEscuela;
import modelo.Usuario;

public class MainActivity extends AppCompatActivity {

    EditText cajaUsuario, cajaContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cajaUsuario = findViewById(R.id.l_caja_usuario);
        cajaContraseña = findViewById(R.id.l_caja_contraseña);
    }

    public void entrar(View view) {
        if(cajaUsuario.getText().toString().trim().equals(""))
            Toast.makeText(this, "Caja usuario vacia", Toast.LENGTH_SHORT).show();
        else if(cajaContraseña.getText().toString().trim().equals(""))
            Toast.makeText(this, "Caja contraseña vacia", Toast.LENGTH_SHORT).show();
        else{
            DataBaseEscuela bd = new DataBaseEscuela(this);

            Usuario us = bd.verificarUsuario(cajaUsuario.getText().toString(), cajaContraseña.getText().toString());

            if(us != null) {
                startActivity(new Intent(this, ActivityPrincipal.class));
            }
            else{
                Toast.makeText(this, "Usuario y/o Contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void registrar(View view) {
        startActivity(new Intent(this, ActivityRegistrar.class));
        finish();
    }
}
