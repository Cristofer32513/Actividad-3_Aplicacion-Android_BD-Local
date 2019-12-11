package itsj.programas.escuela_bd_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import database.DataBaseEscuela;
import modelo.Alumno;

public class ActivityEditarAlumno extends AppCompatActivity {

    EditText cajaNumControl, cajaNombre, cajaPAp, cajaSAp;
    Spinner spinnerEdad, spinnerSemestre, spinnerCarrera;
    Button btnEliminar, btnEditar;
    String antiguoNumControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_alumno);

        cajaNumControl = findViewById(R.id.editar_num_control);
        cajaNombre = findViewById(R.id.editar_nombre);
        cajaPAp = findViewById(R.id.editar_primer_ap);
        cajaSAp = findViewById(R.id.editar_segundo_ap);
        spinnerEdad = findViewById(R.id.editar_spinner_edad);
        spinnerSemestre = findViewById(R.id.editar_spinner_semestre);
        spinnerCarrera = findViewById(R.id.editar_spinner_carrera);

        antiguoNumControl = getIntent().getStringExtra("numControl");

        cajaNumControl.setText(antiguoNumControl.substring(1));
        cajaNumControl.setEnabled(false);

        cajaNombre.setText(getIntent().getStringExtra("nombre"));
        cajaNombre.setEnabled(getIntent().getBooleanExtra("enable", false));

        cajaPAp.setText(getIntent().getStringExtra("primer"));
        cajaPAp.setEnabled(getIntent().getBooleanExtra("enable", false));

        cajaSAp.setText(getIntent().getStringExtra("segundo"));
        cajaSAp.setEnabled(getIntent().getBooleanExtra("enable", false));


        spinnerEdad.setSelection(getIntent().getIntExtra("edad", 0));
        spinnerEdad.setEnabled(getIntent().getBooleanExtra("enable", false));

        spinnerSemestre.setSelection(getIntent().getIntExtra("semestre", 0));
        spinnerSemestre.setEnabled(getIntent().getBooleanExtra("enable", false));

        boolean seEncontroCarrera = false;
        int posicionCarrera = 0;
        while(!seEncontroCarrera){
            if(spinnerCarrera.getItemAtPosition(posicionCarrera).toString().equals(getIntent().getStringExtra("carrera"))) {
                seEncontroCarrera = true;
                break;
            }
            posicionCarrera++;
        }
        spinnerCarrera.setSelection(posicionCarrera);
        spinnerCarrera.setEnabled(getIntent().getBooleanExtra("enable", false));

        btnEliminar = findViewById(R.id.btn_eliminar_editar);
        btnEditar = findViewById(R.id.btn_editar);

    }

    public void eliminar(View view) {
        DataBaseEscuela bd = new DataBaseEscuela(this);

        if(bd.eliminarAlumno(antiguoNumControl)) {
            Toast.makeText(this, "Alumno eliminado", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, ActivityConsultaAlumnos.class));
            finish();
        }
        else
            Toast.makeText(this, "Error al eliminar", Toast.LENGTH_SHORT).show();
    }

    public void guardar(View view) {
        if(cajaNombre.isEnabled()){
            if(cajaNombre.getText().toString().trim().equals(""))
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

                DataBaseEscuela bd = new DataBaseEscuela(this);
                Alumno alu = new Alumno(letra+cajaNumControl.getText().toString(), cajaNombre.getText().toString(), cajaPAp.getText().toString(), cajaSAp.getText().toString(), Integer.parseInt(spinnerEdad.getSelectedItem().toString()),
                        Integer.parseInt(spinnerSemestre.getSelectedItem().toString()), spinnerCarrera.getSelectedItem().toString());

                if(bd.modificarAlumno(alu, antiguoNumControl)) {
                    Toast.makeText(this, "Informacion guardada", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, ActivityConsultaAlumnos.class));
                    finish();
                }
                else
                    Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            btnEditar.setText("GUARDAR");
            btnEliminar.setEnabled(false);
            cajaNombre.setEnabled(true);
            cajaPAp.setEnabled(true);
            cajaSAp.setEnabled(true);
            spinnerEdad.setEnabled(true);
            spinnerSemestre.setEnabled(true);
            spinnerCarrera.setEnabled(true);
        }
    }

    public void cancelar(View view) {
        startActivity(new Intent(this, ActivityConsultaAlumnos.class));
        finish();
    }
}
