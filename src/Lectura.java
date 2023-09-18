import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class Lectura {

   protected static String path;

    protected static Map<Integer,Estudiante > diccionarioEstudiantes;



    public Lectura(String path)  {
        Lectura.path = path;
        diccionarioEstudiantes = new HashMap<>();
        leerArchivo();
    }

    private static void leerArchivo() {
        try(FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader)){
            String linea;

            while((linea = bufferedReader.readLine()) != null){
                procesarLinea(linea);
            }

        }catch (IOException e){
            System.out.println("Error de lectura del archivo " + e);
        }
    }

    private static void procesarLinea(String linea) {
        String[] estudiantes = linea.split(",");
        if(estudiantes.length >= 3){
            int legajo = Integer.parseInt(estudiantes[0]);
            String nombreApellido = estudiantes[1];
            String materiaAprobada = estudiantes[2];

            if(diccionarioEstudiantes.containsKey(legajo)){
                Estudiante estudiante = Lectura.diccionarioEstudiantes.get(legajo);
                estudiante.agregarMateriasAprobadas(materiaAprobada);

            }else {

                Estudiante nuevoEstudiante = new Estudiante(legajo , nombreApellido);
                nuevoEstudiante.agregarMateriasAprobadas(materiaAprobada);
                diccionarioEstudiantes.put(legajo , nuevoEstudiante);
            }
        }else{
            System.out.println("Error: La linea no tiene suficientes datos para procesar " + linea);
        }
    }


}
