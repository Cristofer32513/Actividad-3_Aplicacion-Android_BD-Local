package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import modelo.Alumno;
import modelo.Usuario;

public class DataBaseEscuela extends SQLiteOpenHelper {

    private static final int VERSION_BD = 1;
    private static final String NOMBRE_BD = "Escuela";

    //Alumnos
    private static final String TABLA_ALUMNOS = "Alumnos";
    private static final String CAMPO_NUM_CONTROL = "Num_Control";
    private static final String CAMPO_NOMBRE = "Nombre";
    private static final String CAMPO_PRIMER_AP = "Primer_Apellido";
    private static final String CAMPO_SEGUNDO_AP = "Segundo_Apellido";
    private static final String CAMPO_EDAD = "Edad";
    private static final String CAMPO_SEMESTRE = "Semestre";
    private static final String CAMPO_CARRERA = "Carrera";

    //Productos
    private static final String TABLA_USUARIOS = "USUARIOS";
    private static final String CAMPO_ID_USUARIO = "id_Usuario";
    private static final String CAMPO_USUARIO = "Usuario";
    private static final String CAMPO_CONTRASEÑA = "Contraseña";
    private static final String CAMPO_STOCK_PROD = "Stock";


    private static final String CREAR_TABLA_ALUMNOS = "CREATE TABLE " + TABLA_ALUMNOS + "(" +
            CAMPO_NUM_CONTROL + " TEXT PRIMARY KEY, " +
            CAMPO_NOMBRE + " TEXT NOT NULL, " +
            CAMPO_PRIMER_AP + " TEXT NOT NULL, "+
            CAMPO_SEGUNDO_AP + " TEXT NOT NULL, " +
            CAMPO_EDAD + " INTEGER NOT NULL, " +
            CAMPO_SEMESTRE + " INTEGER NOT NULL, " +
            CAMPO_CARRERA + " TEXT NOT NULL);";

    private static final String CREAR_TABLA_USUARIOS = "CREATE TABLE " + TABLA_USUARIOS + "(" +
            CAMPO_ID_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            CAMPO_USUARIO + " TEXT NOT NULL, " +
            CAMPO_CONTRASEÑA + " TEXT NOT NULL);";



    public DataBaseEscuela(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_ALUMNOS);
        db.execSQL(CREAR_TABLA_USUARIOS);

        db.execSQL("INSERT INTO USUARIOS(Usuario, Contraseña) VALUES('Cristofer', 'casas')");
        db.execSQL("INSERT INTO ALUMNOS(Num_Control, Nombre, Primer_Apellido, Segundo_Apellido, Edad, Semestre, Carrera) " +
                "VALUES('S17070157', 'Cristofer', 'Casas', 'Murillo', 20, 5, 'Ingeniería en Sistemas Computacionales')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}



    //Usuarios
    public boolean agregarUsuario (Usuario u) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CAMPO_USUARIO, u.getUsuario());
        cv.put(CAMPO_CONTRASEÑA, u.getContraseña());

        long res = db.insert(TABLA_USUARIOS, null, cv);

        return (res == -1) ? false : true;
    }

    public Usuario verificarUsuario(String usuario, String contraseña) {
        Usuario usua;
        SQLiteDatabase db = this.getWritableDatabase();
        String sql="SELECT * FROM "+TABLA_USUARIOS+ " WHERE "+CAMPO_USUARIO + "='" + usuario + "' AND " + CAMPO_CONTRASEÑA + "='" + contraseña +"'";

        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            usua= new Usuario(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2));
            return usua;
        }else{
            return  null;
        }
    }

    public Usuario obtenerUsuario(String usuario) {
        Usuario usua;
        SQLiteDatabase db= this.getWritableDatabase();
        String sql="SELECT * FROM " + TABLA_USUARIOS + " WHERE " + CAMPO_USUARIO + "='" + usuario + "'";

        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            usua = new Usuario(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2));
            return usua;
        }else
            return  null;
    }



    //Alumnos
    public ArrayList<Alumno> buscarAlumno(int campo, String condicion){
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String sql;
        if(campo == 1)
            sql = "SELECT * FROM "+TABLA_ALUMNOS+ " WHERE " + CAMPO_NOMBRE + " LIKE '%" + condicion + "%'";
        else if(campo == 2)
            sql = "SELECT * FROM "+TABLA_ALUMNOS+ " WHERE " + CAMPO_PRIMER_AP + " LIKE '%" + condicion + "%'";
        else if(campo == 3)
            sql = "SELECT * FROM "+TABLA_ALUMNOS+ " WHERE " + CAMPO_SEGUNDO_AP + " LIKE '%" + condicion + "%'";
        else if(campo == 4)
            sql = "SELECT * FROM "+TABLA_ALUMNOS+ " WHERE " + CAMPO_EDAD + "=" + Integer.parseInt(condicion);
        else if(campo == 5)
            sql = "SELECT * FROM "+TABLA_ALUMNOS+ " WHERE " + CAMPO_SEMESTRE + "=" + Integer.parseInt(condicion);
        else if(campo == 6)
            sql = "SELECT * FROM "+TABLA_ALUMNOS+ " WHERE " + CAMPO_CARRERA + " LIKE '%" + condicion + "%'";
        else
            sql = "SELECT * FROM " + TABLA_ALUMNOS;

        Cursor cursor = db.rawQuery(sql,null);
        while(cursor.moveToNext()){

            listaAlumnos.add(new Alumno(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getString(6)));
        }
        return listaAlumnos;
    }

    public boolean eliminarAlumno (String numControl){
        SQLiteDatabase db = this.getWritableDatabase();

        int res = db.delete(TABLA_ALUMNOS,CAMPO_NUM_CONTROL + "='" + numControl + "'",null);

        return (res == -1) ? false : true;
    }

    public boolean modificarAlumno (Alumno alumno, String numControl) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CAMPO_NUM_CONTROL, alumno.getNumControl());
        cv.put(CAMPO_NOMBRE, alumno.getNombre());
        cv.put(CAMPO_PRIMER_AP, alumno.getPrimerAp());
        cv.put(CAMPO_SEGUNDO_AP, alumno.getSegundoAp());
        cv.put(CAMPO_EDAD, alumno.getEdad());
        cv.put(CAMPO_SEMESTRE, alumno.getSemestre());
        cv.put(CAMPO_CARRERA, alumno.getCarrera());

        long res=db.update(TABLA_ALUMNOS, cv, CAMPO_NUM_CONTROL + "='" + numControl + "'", null);

        return (res == -1) ? false : true;
    }

    public boolean agregarAlumno (Alumno alumno) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CAMPO_NUM_CONTROL, alumno.getNumControl());
        cv.put(CAMPO_NOMBRE, alumno.getNombre());
        cv.put(CAMPO_PRIMER_AP, alumno.getPrimerAp());
        cv.put(CAMPO_SEGUNDO_AP, alumno.getSegundoAp());
        cv.put(CAMPO_EDAD, alumno.getEdad());
        cv.put(CAMPO_SEMESTRE, alumno.getSemestre());
        cv.put(CAMPO_CARRERA, alumno.getCarrera());

        long res=db.insert(TABLA_ALUMNOS, null, cv);

        return (res == -1) ? false : true;
    }
}