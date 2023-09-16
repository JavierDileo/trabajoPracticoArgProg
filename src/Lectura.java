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
        try{
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linea;
            diccionarioEstudiantes = new HashMap<>();

            while((linea = bufferedReader.readLine()) != null){
                String[] estudiantes = linea.split(",");
                int legajo = Integer.parseInt(estudiantes[0]);

                if(diccionarioEstudiantes.containsKey(legajo)){
                    Estudiante estudiante = Lectura.diccionarioEstudiantes.get(legajo);
                    estudiante.agregarMateriasAprobadas(estudiantes[2]);

                }else {

                    Estudiante estudiante1 = new Estudiante(Integer.parseInt(estudiantes[0]) , estudiantes[1]);
                    estudiante1.agregarMateriasAprobadas(estudiantes[2]);
                    diccionarioEstudiantes.put(Integer.parseInt(estudiantes[0]) , estudiante1);
                }


            }

            bufferedReader.close();

        }catch (IOException e){
            System.out.println(e);
        }

        System.out.println(diccionarioEstudiantes);

    }


}
