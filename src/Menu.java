

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {



    public static void menu() {
        Scanner sc = new Scanner(System.in);
        boolean bandera = true;
        String agregarMateria;
        int opciones;

        do {
            System.out.println("Datos del Mapa: \n" + Lectura.diccionarioEstudiantes);
            opciones = menuDeOpciones(sc);

            switch (opciones) {
                case 1 -> {
                    int legajo = 0;
                    legajo = validarNumeros(sc,"Ingrese el numero de legajo: ");
                    //VERIFICA SI ESTA EL LEGAJO EN EL DICCIONARIO
                    if (Lectura.diccionarioEstudiantes.containsKey(legajo)) {
                        Estudiante estudiante = Lectura.diccionarioEstudiantes.get(legajo);

                        //OPCION PARA CARGAR UNA MATERIA APROBADA
                        while (bandera) {
                            System.out.println("Agregar materia (S/N)");
                            agregarMateria = sc.next();
                            //CARGA DE LA NUEVA MATERIA APROBADA
                            if (agregarMateria.equalsIgnoreCase("s")) {
                                sc.nextLine();
                                String materiaAprobada = getMateriaAprobada(sc);
                                estudiante.agregarMateriasAprobadas(materiaAprobada);
                                System.out.println("Materia " + materiaAprobada + " agregada exitosamente.\n");
                                //CARGAR AL ARCHIVO TXT LA NUEVA CADENA
                                cargarAlArchivo(estudiante);
                                bandera = false;
                            }else{
                                bandera = false;
                            }

                        }
                        bandera = true;
                        //SE IMPRIMEN LOS DATOS POR PANTALLA
                        imprimir(estudiante);

                     //SI NO SE ENCUENTRA EL LEGAJO
                    } else {
                        System.out.println("El legajo " + legajo + " no se encuentra en el diccionario.");
                        System.out.println("Genere el alta para el estudiante con legajo N° " + legajo);
                        System.out.println("Ingrese el Nombre y Apellido del estudiante: ");
                        //LIMPIA EL BUFFER DEL SCANNER
                        sc.nextLine();

                        String nombreApellido = sc.nextLine();
                        String materiaAprobada = getMateriaAprobada(sc);

                        //AGREGA UN NUEVO ESTUDIANTE Y SU MATERIA APROBADA
                        Estudiante estudiante = new Estudiante(legajo, nombreApellido);
                        estudiante.agregarMateriasAprobadas(materiaAprobada);
                        System.out.println("Estudiante registrado exitosamente.\n");

                        //SE IMPRIMEN LOS DATOS POR PANTALLA
                        imprimir(estudiante);

                        //SE AGREGA AL DICCIONARIO EXISTENTE
                        Lectura.diccionarioEstudiantes.put(legajo, estudiante);

                        //CARGAR AL ARCHIVO TXT LA NUEVA CADENA
                        cargarAlArchivo(estudiante);
                    }
                }
                case 2 -> System.out.println("Has salido exitosamente");
                default -> System.out.println("Opcion no valida\n");

            }
        } while (opciones !=2);
        sc.close();
    }

    private static void imprimir(Estudiante estudiante) {
        System.out.println();
        System.out.println("DATOS DEL ESTUDIANTE");
        System.out.println(estudiante.getLegajo() + " - " + estudiante.getNombreApellido() + " - " + estudiante.getMateriasAprobadas());
        System.out.println();
    }

    private static int menuDeOpciones(Scanner sc) {
        System.out.println("\t\tMenu\t\t");
        System.out.println("Seleccione una opcion (1-2)");
        System.out.println("1. Buscar estudiante por numero de legajo");
        System.out.println("2. Salir");
        int opciones = 0;
        opciones = validarNumeros(sc, "Opcion: ");
        return opciones;
    }

    private static int validarNumeros(Scanner sc ,String str) {
        int numero;
        while(true){

            try {
                System.out.print(str);
                numero = sc.nextInt();
                System.out.println();
                break;

            } catch (InputMismatchException e) {
                sc.nextLine();//Limpia el buffer
                System.out.println(e);
                System.out.println("Error: Ingresar solo numeros.");
                System.out.println();
            }
        }
        return numero;
    }

    private static void cargarAlArchivo(Estudiante estudiante) {
        try(FileWriter fileWriter = new FileWriter(Lectura.path, true);
         PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.append("\n").append(estudiante.cadena());
      
        }catch (IOException e){
            System.out.println("Error en la escritura del archivo " + e);
        }
        
    }

    private static String getMateriaAprobada(Scanner sc) {
        System.out.print("Ingrese la materia aprobada: ");
        return sc.nextLine();
    }




}
