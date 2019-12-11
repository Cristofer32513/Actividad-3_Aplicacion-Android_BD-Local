package itsj.programas.escuela_bd_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import database.DataBaseEscuela;
import modelo.Alumno;

public class ActivityConsultaAlumnos extends AppCompatActivity {

    Spinner spinnerFiltro;
    EditText cajaBuscar;
    ArrayList<Alumno> listaAlumnos;
    RecyclerView recyclerViewEmpleados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas_alumnos);

        spinnerFiltro = findViewById(R.id.spinner_parametro);
        spinnerFiltro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(spinnerFiltro.getSelectedItemPosition() == 0) {
                    cajaBuscar.setEnabled(false);
                    DataBaseEscuela bd = new DataBaseEscuela(ActivityConsultaAlumnos.this);
                    listaAlumnos = bd.buscarAlumno(0, "");

                    if(listaAlumnos.size() == 0)
                        Toast.makeText(ActivityConsultaAlumnos.this, "No hay registros", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(ActivityConsultaAlumnos.this, listaAlumnos.size()+" registro(s) encontrado(s)", Toast.LENGTH_SHORT).show();

                    ListaAlumnosAdapter adapter = new ListaAlumnosAdapter(listaAlumnos);
                    adapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(ActivityConsultaAlumnos.this, ActivityEditarAlumno.class);
                            i.putExtra("enable", false);
                            i.putExtra("numControl", listaAlumnos.get(recyclerViewEmpleados.getChildAdapterPosition(v)).getNumControl());
                            i.putExtra("nombre", listaAlumnos.get(recyclerViewEmpleados.getChildAdapterPosition(v)).getNombre());
                            i.putExtra("primer", listaAlumnos.get(recyclerViewEmpleados.getChildAdapterPosition(v)).getPrimerAp());
                            i.putExtra("segundo", listaAlumnos.get(recyclerViewEmpleados.getChildAdapterPosition(v)).getSegundoAp());
                            i.putExtra("edad", listaAlumnos.get(recyclerViewEmpleados.getChildAdapterPosition(v)).getEdad());
                            i.putExtra("semestre", listaAlumnos.get(recyclerViewEmpleados.getChildAdapterPosition(v)).getSemestre());
                            i.putExtra("carrera", listaAlumnos.get(recyclerViewEmpleados.getChildAdapterPosition(v)).getCarrera());
                            startActivity(i);
                            finish();
                        }
                    });
                    recyclerViewEmpleados.setAdapter(adapter);
                }
                else {
                    cajaBuscar.setEnabled(true);
                    cajaBuscar.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}

        });

        cajaBuscar = findViewById(R.id.caja_busqueda);

        listaAlumnos = new ArrayList<>();

        recyclerViewEmpleados = (RecyclerView) findViewById(R.id.recyclerview_alumnos);
        recyclerViewEmpleados.setLayoutManager(new LinearLayoutManager(this));
    }

    public void buscar(View view) {
        DataBaseEscuela bd = new DataBaseEscuela(this);

        if(cajaBuscar.getText().toString().trim().equals(""))
            Toast.makeText(this, "Caja busqueda vacia", Toast.LENGTH_SHORT).show();
        else if(spinnerFiltro.getSelectedItemPosition() == 1)
            listaAlumnos = bd.buscarAlumno(1, cajaBuscar.getText().toString());
        else if(spinnerFiltro.getSelectedItemPosition() == 2)
            listaAlumnos = bd.buscarAlumno(2, cajaBuscar.getText().toString());
        else if(spinnerFiltro.getSelectedItemPosition() == 3)
            listaAlumnos = bd.buscarAlumno(3, cajaBuscar.getText().toString());
        else if(spinnerFiltro.getSelectedItemPosition() == 4)
            listaAlumnos = bd.buscarAlumno(4, cajaBuscar.getText().toString());
        else if(spinnerFiltro.getSelectedItemPosition() == 5)
            listaAlumnos = bd.buscarAlumno(5, cajaBuscar.getText().toString());
        else if(spinnerFiltro.getSelectedItemPosition() == 6)
            listaAlumnos = bd.buscarAlumno(6, cajaBuscar.getText().toString());

        if(listaAlumnos.size() == 0)
            Toast.makeText(this, "No se encontraron registros", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, listaAlumnos.size()+" registro(s) encontrado(s)", Toast.LENGTH_SHORT).show();

        ListaAlumnosAdapter adapter=new ListaAlumnosAdapter(listaAlumnos);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityConsultaAlumnos.this, ActivityEditarAlumno.class);
                i.putExtra("enable", false);
                i.putExtra("numControl", listaAlumnos.get(recyclerViewEmpleados.getChildAdapterPosition(v)).getNumControl());
                i.putExtra("nombre", listaAlumnos.get(recyclerViewEmpleados.getChildAdapterPosition(v)).getNombre());
                i.putExtra("primer", listaAlumnos.get(recyclerViewEmpleados.getChildAdapterPosition(v)).getPrimerAp());
                i.putExtra("segundo", listaAlumnos.get(recyclerViewEmpleados.getChildAdapterPosition(v)).getSegundoAp());
                i.putExtra("edad", listaAlumnos.get(recyclerViewEmpleados.getChildAdapterPosition(v)).getEdad());
                i.putExtra("semestre", listaAlumnos.get(recyclerViewEmpleados.getChildAdapterPosition(v)).getSemestre());
                i.putExtra("carrera", listaAlumnos.get(recyclerViewEmpleados.getChildAdapterPosition(v)).getCarrera());
                startActivity(i);
                finish();
            }
        });
        recyclerViewEmpleados.setAdapter(adapter);
    }

    public void volver(View view) {
        startActivity(new Intent(ActivityConsultaAlumnos.this, ActivityPrincipal.class));
        finish();
    }
}
