import java.util.ArrayList;

public class Estudiante {
   private final int legajo;
    private final String nombreApellido;
    private ArrayList <String> materiasAprobadas = new ArrayList<>();

    public Estudiante(int legajo , String nombreApellido){
        this.legajo = legajo;
        this.nombreApellido = nombreApellido;
        materiasAprobadas = new ArrayList<>();

    }

    public  int getLegajo(){
        return legajo;
    }

    public  String getNombreApellido(){
        return nombreApellido;
    }

    public ArrayList<String> getMateriasAprobadas() {
        return materiasAprobadas;
    }

    public void agregarMateriasAprobadas(String materia){
        materiasAprobadas.add(materia);
    }


  public  String cadena() {
        String materias = null;
        for (String materia: materiasAprobadas) {
            materias = materia ;

        }
        return legajo + "," + nombreApellido + "," + materias  ;
    }
}
