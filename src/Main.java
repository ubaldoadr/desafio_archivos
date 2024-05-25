import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    static File  miArchivoATrabajar=null;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        ArrayList<String> lista = new ArrayList<String>(Arrays.asList("perro", "gato", "juan", "daniel", "juan", "gato", "perro", "camila", "daniel", "camila"));
        System.out.println(lista);

        int op;
        do {
            op = mostrarMenu();

            if (op == 1) {
                miArchivoATrabajar = crearArchivo("miDirectorio", "miarchivo.txt");
                escribirArchivo(miArchivoATrabajar, lista);
            } else if (op == 2) {
                validarDirectorio("miDirectorio");
            } else if (op == 3) {
                if (miArchivoATrabajar != null){
                    validarArchivo(miArchivoATrabajar.getAbsolutePath());
                }else {
                    mensaje("el archivo aun no ha sido creado");
                }
            } else if (op == 4) {
                if (miArchivoATrabajar != null){
                    System.out.println("Ingrese la palabra a buscar:");
                    String textoABuscar = sc.next();
                    buscarTexto(textoABuscar, miArchivoATrabajar);
                }else {
                    mensaje("el archivo aun no ha sido creado");
                }

            } else if (op == 0) {
                System.out.println("Adios");
            } else {
                System.out.println("Opción no válida");
            }
        } while (op != 0);

    }
    public static int leerValorInt(String mensaje) {
        Scanner sc = new Scanner(System.in);
        System.out.println(mensaje);
        return sc.nextInt();
    }

    public static int mostrarMenu(){
        mensaje(" === MENÚ DE OPCIONES ===\n");
        mensaje(" 1. Crear directorio y archivo\n");
        mensaje(" 2. Validar un directorio\n");
        mensaje(" 3. Validar un archivo\n");
        mensaje(" 4. buscar texto\n");
        mensaje(" 0. Salir\n");
        return leerValorInt("\nElige una opción\n");
    }
    public static void mensaje(String mensaje){
        System.out.println(mensaje);
    }

    public static void validarArchivo (String nombreArchivo) {
        File archivo = new File(nombreArchivo);

        if (!archivo.exists()) {
            System.out.println("El archivo no existe");
        } else {
            System.out.println("El archivo ya existe");
        }
    }

    public static void buscarTexto (String textoABuscar, File miArchivo){
        try {
            int contador = 0;
            FileReader fr = new FileReader(miArchivo);
            BufferedReader br = new BufferedReader(fr);
            boolean comprobar = false;
            String data;
            while ((data = br.readLine()) != null) {
                if (data.equalsIgnoreCase(textoABuscar)){
                    comprobar= true;
                    contador ++;
                }
            }
            fr.close();
            br.close();
            if (comprobar){
                System.out.println("La palabra existe en el archivo y se encontro: "+ contador+ " veces");
            } else {
                System.out.println("La palabra no existe en el archivo");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void validarDirectorio (String nombreDirectorio) {
        File directorio = new File(nombreDirectorio);

        if (!directorio.exists()) {
            System.out.println("El directorio no existe");
        } else {
            System.out.println("El directorio ya existe");
        }
    }

    public static File crearArchivo(String miDirectorio, String archivo) {
         //File directorio = new File(miDirectorio);
       File directorio = new File("src/" + miDirectorio);

        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado exitosamente!");
            }
            else {
                System.out.println("Error al crear directorio");
            }
        }
        File Fichero = new File(directorio.getAbsoluteFile() + "/" + archivo);
        System.out.println(Fichero);
        try {
            if (Fichero.createNewFile()) {
                System.out.println("Archivo creado");
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo");
            e.printStackTrace();
        }
        return Fichero;
    }

    public static void escribirArchivo(File f, ArrayList<String> milista ) {

        try {
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);

           /* for (int i = 0; i < milista.size(); i++) {
                bw.write(milista.get(i)+ "\n");
            } */
            for (Iterator<String> iterator = milista.iterator(); iterator.hasNext();) {
                bw.write(iterator.next()+"\n");
            }
            bw.close();
            fw.close();

        } catch (IOException e) {
            System.out.println("Error escribiendoArchivo");
            e.printStackTrace();

        }
    }
}
