import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la palabra a buscar:");
        String textoABuscar = sc.next();

        ArrayList<String> lista = new ArrayList<String>(Arrays.asList("perro", "gato", "juan", "daniel", "juan", "gato", "perro", "camila", "daniel", "camila"));
        System.out.println(lista);
        File miArchivoATrabajar = crearArchivo("miDirectorio", "miarchivo.txt");
        escribirArchivo(miArchivoATrabajar,lista);
        validarDirectorio("miDirectorio");
        validarArchivo(miArchivoATrabajar.getAbsolutePath());
        buscarTexto(textoABuscar, miArchivoATrabajar);
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
        File directorio = new File(miDirectorio);

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
