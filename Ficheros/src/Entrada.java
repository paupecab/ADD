import controller.FileController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/*
## Vista: lo que intercambia datos con usuario o sistema
Presenta el 'modelo' (información y lógica de negocio) en un formato adecuado para interactuar
(usualmente la interfaz de usuario), por tanto requiere de dicho 'modelo' la información
que debe representar como salida.
*/


public class Entrada {

    static void main(String[] args) {
        FileController fc = new FileController();

        /* ---------------CREACIÓN Y TRATAMIENTO DE ERROR EN FICHERO------------------

                          ---- LECTURAS POR CONSOLA ----

                              --- BufferedReader ----

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            //lectura por consola -> solo se puede leer string
            String fileName = br.readLine();
            fc.createFile(fileName);
            !! br.close(); -> no hace falta porque al acabar el try se da por cerrado
        } catch (IOException e) {
            System.out.println("Error: "+e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }

                              ---  Scanner  ---

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Introduce nombre de carpeta para crear: ");
            String fileName = scanner.next();
            try {
                fc.createFile(fileName);
                break;
            } catch (IOException e) {
                System.out.println("Error al crear el fichero: "+ e.getMessage());
            }
        } while (true);

         -------- fin de flujo de datos -> cierre del scanner ---------

        scanner.close();
        System.out.println("Terminado.");


         */

        /* -----------CREACIÓN Y TRATAMIENTO DE ERROR EN CARPETA-----------
         */
        Scanner s = new Scanner(System.in);
        System.out.println("Proyecto de creación de carpetas.");
        System.out.println("Introduce el nombre de carpeta: ");
        String carpeta = s.next();
        fc.createFolder(carpeta);
        s.close();
    }
}