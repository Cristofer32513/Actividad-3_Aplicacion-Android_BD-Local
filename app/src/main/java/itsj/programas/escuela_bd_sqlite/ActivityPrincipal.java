package itsj.programas.escuela_bd_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void juzgarBoton(View view) {
        switch(view.getId()){
            case R.id.btn_agregar:
                startActivity(new Intent(this, ActivityAgregarAlumno.class));
                finish();
                break;
            case R.id.btn_buscar_menu:
                startActivity(new Intent(this, ActivityConsultaAlumnos.class));
                finish();
                break;
        }
    }
}
