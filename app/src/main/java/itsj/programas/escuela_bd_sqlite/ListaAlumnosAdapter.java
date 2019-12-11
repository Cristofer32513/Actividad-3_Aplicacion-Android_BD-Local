package itsj.programas.escuela_bd_sqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import modelo.Alumno;

public class ListaAlumnosAdapter extends RecyclerView.Adapter<ListaAlumnosAdapter.AlumnosViewHolder> implements View.OnClickListener {

    ArrayList<Alumno> listaAlumnos;
    private View.OnClickListener listener;

    public ListaAlumnosAdapter(ArrayList<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    @Override
    public AlumnosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_alumnos,null,false);
        view.setOnClickListener(this);
        return new AlumnosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlumnosViewHolder holder, int position) {
        holder.numControl.setText(String.valueOf(listaAlumnos.get(position).getNumControl()));
        holder.nombre.setText(listaAlumnos.get(position).getNombre());
        holder.primerAp.setText(listaAlumnos.get(position).getPrimerAp());
        holder.segundoAp.setText(listaAlumnos.get(position).getSegundoAp());
        holder.edad.setText(String.valueOf(listaAlumnos.get(position).getEdad()));
        holder.semestre.setText(String.valueOf(listaAlumnos.get(position).getSemestre()));
        holder.carrera.setText(listaAlumnos.get(position).getCarrera());
    }

    @Override
    public int getItemCount() {
        return listaAlumnos.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener != null)
            listener.onClick(v);
    }

    public class AlumnosViewHolder extends RecyclerView.ViewHolder {

        TextView numControl, nombre, primerAp, segundoAp, edad, semestre, carrera;

        public AlumnosViewHolder(View itemView) {
            super(itemView);
            numControl = (TextView) itemView.findViewById(R.id.rec_num_control);
            nombre = (TextView) itemView.findViewById(R.id.rec_nombre);
            primerAp = (TextView) itemView.findViewById(R.id.rec_primer_ap);
            segundoAp = (TextView) itemView.findViewById(R.id.rec_segundo_ap);
            edad = (TextView) itemView.findViewById(R.id.rec_edad);
            semestre = (TextView) itemView.findViewById(R.id.rec_semestre);
            carrera = (TextView) itemView.findViewById(R.id.rec_carrera);
        }
    }
}