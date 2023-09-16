

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu{



    public static void menu() {
        Scanner sc = new Scanner(System.in);
        boolean bandera = true;
        String agregarMateria;
        int opciones;

        do {
            System.out.println("Menu");
            System.out.println("1. Buscar estudiante por numero de legajo");
            System.out.println("2. Salir");
            opciones = sc.nextInt();

            switch (opciones) {
                case 1:
                    System.out.print("Ingrese el numero de legajo: ");
                    int legajo = sc.nextInt();
                    //VERIFICA SI ESTA EL LEGAJO EN EL DICCIONARIO
                    if (Lectura.diccionarioEstudiantes.containsKey(legajo)) {
                        Estudiante estudiante = Lectura.diccionarioEstudiantes.get(legajo);

                        //OPCION PARA CARGAR UNA MATERIA APROBADA
                        while (bandera) {
                            System.out.println("Agregar materia (S/N)");
                            agregarMateria = sc.next();

                            if (agregarMateria.equalsIgnoreCase("s")) {
                                String materiaAprobada = getMateriaAprobada("Ingrese la materia aprobada: ", sc);
                                estudiante.agregarMateriasAprobadas(materiaAprobada);
                                bandera = false;
                            } else {
                                bandera = false;
                            }

                        }

                        //REASIGNACION DE LA VARIABLE BANDERA A TRUE PARA QUE VUELVA A PEDIR SI QUIERE AGREGAR UNA
                        // MATERIA APROBADA
                        bandera = true;

                        //CARGAR AL ARCHIVO TXT LA NUEVA CADENA
                        cargarAlArchivo(estudiante);

                        //SE IMPRIMEN LOS DATOS POR PANTALLA
                        System.out.println("DATOS DEL ESTUDIANTE");
                        System.out.println(estudiante.getLegajo() + " - " + estudiante.getNombreApellido() + " - " + estudiante.getMateriasAprobadas());

                        //SI NO SE ENCUENTRA EL LEGAJO
                    } else {
                        System.out.println("Ingrese el Nombre y Apellido del estudiante: ");
                        String nombreApellido = sc.next();
                        String materiaAprobada = getMateriaAprobada("Ingrese la materia aprobada: ", sc);

                        //AGREGA UN NUEVO ESTUDIANTE Y SU MATERIA APROBADA
                        Estudiante estudiante = new Estudiante(legajo, nombreApellido);
                        estudiante.agregarMateriasAprobadas(materiaAprobada);

                        //SE AGREGA AL DICCIONARIO EXISTENTE
                        Lectura.diccionarioEstudiantes.put(legajo, estudiante);

                        cargarAlArchivo(estudiante);
                    }
                    break;
                case 2:
                    System.out.println("Has salido exitosamente");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (opciones !=2);

    }

    private static void cargarAlArchivo(Estudiante estudiante) {
        try{
            FileWriter fileWriter = new FileWriter(Lectura.path, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.append("\n").append(estudiante.cadena());

             printWriter.close();

        }catch (IOException e){
            System.out.println(e);
        }
    }

    private static String getMateriaAprobada(String s, Scanner sc) {
        System.out.print(s);
        String materiaAprobada = sc.next();
        return materiaAprobada;
    }
}
