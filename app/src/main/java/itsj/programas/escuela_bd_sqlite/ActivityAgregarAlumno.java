package itsj.programas.escuela_bd_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import database.DataBaseEscuela;
import modelo.Alumno;

public class ActivityAgregarAlumno extends AppCompatActivity {

    EditText cajaNumControl, cajaNombre, cajaPAp, cajaSAp;
    Spinner spinnerEdad, spinnerSemestre, spinnerCarrera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_alumno);

        cajaNumControl = findViewById(R.id.agregar_num_control);
        cajaNombre = findViewById(R.id.agregar_nombre);
        cajaPAp = findViewById(R.id.agregar_primer_ap);
        cajaSAp = findViewById(R.id.agregar_segundo_ap);
        spinnerEdad = findViewById(R.id.agregar_spinner_edad);
        spinnerSemestre = findViewById(R.id.agregar_spinner_semestre);
        spinnerCarrera = findViewById(R.id.agregar_spinner_carrera);
    }

    public void agregar(View view) {
        if(cajaNumControl.getText().toString().trim().equals(""))
            Toast.makeText(this, "El campo numero de control esta vacio", Toast.LENGTH_SHORT).show();
        else if(cajaNombre.getText().toString().trim().equals(""))
            Toast.makeText(this, "El campo nombre esta vacio", Toast.LENGTH_SHORT).show();
        else if(cajaPAp.getText().toString().trim().equals(""))
            Toast.makeText(this, "Eñ campo primer Apellido esta vacio", Toast.LENGTH_SHORT).show();
        else if(cajaSAp.getText().toString().trim().equals(""))
            Toast.makeText(this, "El campo Segundo Apellido esta vacion", Toast.LENGTH_SHORT).show();
        else if(spinnerEdad.getSelectedItemPosition() == 0)
            Toast.makeText(this, "Seleccione edad", Toast.LENGTH_SHORT).show();
        else if(spinnerSemestre.getSelectedItemPosition() == 0)
            Toast.makeText(this, "Seleccione Semestre", Toast.LENGTH_SHORT).show();
        else if(spinnerCarrera.getSelectedItemPosition() == 0)
            Toast.makeText(this, "Seleccione Carrera", Toast.LENGTH_SHORT).show();
        else{
            DataBaseEscuela bd = new DataBaseEscuela(this);
            String letra="";
            if(spinnerCarrera.getSelectedItemPosition() == 1)
                letra = "A";
            else if(spinnerCarrera.getSelectedItemPosition() == 2)
                letra = "P";
            else if(spinnerCarrera.getSelectedItemPosition() == 3)
                letra = "I";
            else if(spinnerCarrera.getSelectedItemPosition() == 4)
                letra = "M";
            else if(spinnerCarrera.getSelectedItemPosition() == 5)
                letra = "S";

                        Alumno alu = new Alumno(letra+cajaNumControl.getText().toString(), cajaNombre.getText().toString(), cajaPAp.getText().toString(), cajaSAp.getText().toString(), Integer.parseInt(spinnerEdad.getSelectedItem().toString()),
                    Integer.parseInt(spinnerSemestre.getSelectedItem().toString()), spinnerCarrera.getSelectedItem().toString());

            if(bd.agregarAlumno(alu)) {
                Toast.makeText(this, "Alumno agregado", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, ActivityPrincipal.class));
                finish();
            }
            else
                Toast.makeText(this, "Error al agregar", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelar(View view) {
        startActivity(new Intent(this, ActivityPrincipal.class));
        finish();
    }
}
