package modelo;

public class Alumno {
    private String numControl;
    private String nombre;
    private String primerAp;
    private String segundoAp;
    private int edad;
    private int semestre;
    private String carrera;

    public Alumno() {}

    public Alumno(String numControl, String nombre, String primerAp, String segundoAp, int edad, int semestre, String carrera) {
        super();
        this.numControl = numControl;
        this.nombre = nombre;
        this.primerAp = primerAp;
        this.segundoAp = segundoAp;
        this.edad = edad;
        this.semestre = semestre;
        this.carrera = carrera;
    }

    public String getNumControl() {
        return numControl;
    }
    public void setNumControl(String numControl) {
        this.numControl = numControl;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerAp() {
        return primerAp;
    }
    public void setPrimerAp(String primerAp) {
        this.primerAp = primerAp;
    }

    public String getSegundoAp() {
        return segundoAp;
    }
    public void setSegundoAp(String segundoAp) {
        this.segundoAp = segundoAp;
    }

    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getSemestre() {
        return semestre;
    }
    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "___________________________________________\n\n" +
                "                          A L U M N O\n\n" +
                "Numero de Control: "+numControl+"\n"+
                "Nombre: "+nombre+" "+primerAp+" "+segundoAp+"\n"+
                "Edad: "+edad+"\n"+
                "Semestre: "+semestre+"\n"+
                "Carrera: "+carrera;
    }
}