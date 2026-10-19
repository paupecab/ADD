package controller;
/*
## Controlador: lógica de programación
            Responde a eventos e invoca peticiones al 'modelo' cuando se hace alguna solicitud
            sobre la información (por ejemplo, editar un documento o un registro en una base de datos).
            También puede enviar comandos a su 'vista' asociada si se solicita un cambio en la forma en que se
            presenta el 'modelo' (por ejemplo, desplazamiento o scroll por un documento o por los diferentes
            registros de una base de datos), por tanto se podría decir que el 'controlador' hace
            de intermediario entre la 'vista' y el 'modelo'.
*/
import java.io.File;
import java.io.IOException;


/*
- MVC - Model View Controller es un patrón de desarrollo modelo-vista-controlador: patrón de arquitectura de software

## Modelo:
clases modelo (POJO) -> presentacion de la informacion con la que opera el sistema. Gestiona los accesos
al sistema, consultas, actualizaciones etc. Envía a la 'vista' aquella parte de la información que en cada momento
se le solicita para que sea mostrada. Las peticiones de acceso o manipulación de información llegan al 'modelo'
 a través del 'controlador'.



+ gestión del DAO
es un patrón arquitectónico que encapsula la lógica de acceso a datos, separándola de la lógica
de negocio y permitiendo cambiar la fuente de datos sin afectar el resto de la aplicación
actúa como intermediario entre la aplicación y la fuente de datos (base de datos, archivo, servicio web, etc.)
Su objetivo es aislar la lógica de acceso a datos de la lógica de negocio,
centralizando las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
y ocultando los detalles técnicos de la persistencia
*/

public class FileController {
    // --- VARIABLES ---
    private String basePath = "src/resources/";

    // --- METODOS ---

    //metodo no estatico, publico para poder acceder a el
    public void createFile(String path) throws IOException {   // pasa la responsabilidad a la gestión de la ejecución

        // -- con formateo --
        String finalPath = String.format("%s%s", basePath, path);
        System.out.println("La ruta del fichero a crear es: " + finalPath);

        File file = new File(finalPath); // fichero lógico -> solo existe en memoria
        if (file.createNewFile()){
            System.out.println("Fichero creado correctamente.");
        } else {
            System.out.println("Ya existe un fichero con ese nombre.");
        }
    }

    public void erraseFile(String path){

        // -- con formateo --
        String finalPath = String.format("%s%s", basePath, path);
        System.out.println("La ruta del fichero a borrar es: "+finalPath);

        File file = new File(finalPath); // fichero lógico -> solo existe en memoria

        if (file.exists()){
            file.delete();
        }else{
            System.out.println("El fichero no se puede borrar porque no existe.");
        }

        System.out.println("Fichero creado.");
    }

    public void createFolder(String path) {   // pasa la responsabilidad a la gestión de la ejecución

        // -- con formateo --
        String finalPath = String.format("%s%s", basePath, path, "/");
        System.out.println("La ruta de la carpeta a crear es: " + finalPath);

        File file = new File(finalPath); // fichero lógico -> solo existe en memoria
        if (file.mkdir()){
            System.out.println("Carpeta creada correctamente.");
        } else {
            System.out.println("No se encuentra la ruta.");
        }
    }

}

