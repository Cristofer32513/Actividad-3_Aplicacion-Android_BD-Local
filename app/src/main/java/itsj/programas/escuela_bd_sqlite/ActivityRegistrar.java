package itsj.programas.escuela_bd_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import database.DataBaseEscuela;
import modelo.Usuario;

public class ActivityRegistrar extends AppCompatActivity {

    EditText cajaUsuario, cajaContraseña1, cajaContraseña2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgistrar);

        cajaUsuario = findViewById(R.id.u_caja_usuario);
        cajaContraseña1 = findViewById(R.id.u_caja_contraseña1);
        cajaContraseña2 = findViewById(R.id.u_caja_contraseña2);
    }

    public void registrar(View view) {
        if(cajaUsuario.getText().toString().trim().equals(""))
            Toast.makeText(this, "Caja usuario vacia", Toast.LENGTH_SHORT).show();
        else if(cajaContraseña1.getText().toString().trim().equals(""))
            Toast.makeText(this, "Caja contraseña vacia", Toast.LENGTH_SHORT).show();
        else if(cajaContraseña2.getText().toString().trim().equals(""))
            Toast.makeText(this, "Caja verificar contraseña vacia", Toast.LENGTH_SHORT).show();
        else{
            Usuario usuario = new Usuario(0, cajaUsuario.getText().toString(), cajaContraseña1.getText().toString());
            DataBaseEscuela bd = new DataBaseEscuela(this);

            if(cajaContraseña1.getText().toString().equals(cajaContraseña2.getText().toString())) {
                Usuario us = bd.obtenerUsuario(cajaUsuario.getText().toString());
                if(us == null){
                    if(bd.agregarUsuario(usuario)) {
                        Toast.makeText(this, "Usuario agregado", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    }
                    else
                        Toast.makeText(this, "Error al agregar el usuario", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(this, "El nombre de usuario ya existe", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelar(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
