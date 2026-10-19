------------------------------------------------------------------------

### Datos del estudiante

**Nombre y apellidos**

**Fecha de entrega** 

# Actividad 1 (individual). Gestión de clientes y pedidos en una aplicación Java

## Objetivos de la actividad

Con esta actividad vas a conseguir afianzar tus competencias en acceso y
manipulación de datos en aplicaciones Java mediante el uso de ficheros,
XML y JDBC. A través de esta actividad podrás:

- Crear clases Java que modelen datos de clientes y pedidos.

- Persistir la información usando serialización de objetos.

- Generar y consultar ficheros XML.

- Aplicar transformaciones con XSL y consultas XPath.

- Manipular datos relacionales con SQL y JDBC.

- Gestionar transacciones y garantizar la integridad de los datos.

## Pautas de elaboración

Vas a desarrollar una aplicación de escritorio para una tienda virtual
llamada **PedidosYa!**, que gestione clientes y sus pedidos. La
actividad tiene varias fases: modelado de datos, almacenamiento en
fichero, generación de XML, conexión a base de datos y uso de
transacciones.

A continuación, vamos a detallar los pasos:

### - **Modelado y serialización de objetos:**

- [x] Crea las clases Cliente (id, nombre, email) y Pedido (id, clienteId,
    producto, cantidad).
- [x]  Implementa la interfaz Serializable en ambas clases.
- [x] Crea al menos tres clientes y cinco pedidos asociados.
- [x] Guarda la lista de objetos en un archivo datos.dat.

### - **Lectura y generación de XML:**

- [x] Deserializa los objetos desde el fichero.
- [x] Usa DOM para generar dos archivos XML: clientes.xml y pedidos.xml.

### - **Transformaciones y consultas XML:**

- [x] Aplica una hoja de estilo XSL para transformar los pedidos en una
    tabla HTML.
- [x] Realiza dos consultas XPath. Por ejemplo, para obtener pedidos con
    cantidad mayor a dos y para obtener los pedidos realizados por un
    cliente específico.

### - NO INCLUIDO EN ESTA ENTREGA: **Base de datos y JDBC:**

  - Crea la base de datos PedidosYaDB con tablas clientes y pedidos,
    estableciendo la relación entre ellas mediante clave foránea.
  - Inserta los datos desde XML, utilizando PreparedStatement.

### - NO INCLUIDO EN ESTA ENTREGA: **Operaciones con DML y transacciones:**

  - Realiza una actualización en lote para aumentar en un 5 % la
    cantidad de todos los pedidos de un cliente concreto.
  - Elimina un pedido dentro de una transacción. Si la operación falla,
    realiza *rollback.*

## Extensión y formato

 **Extensión máxima:**
- 1-2 minutos máximo.
- Documento: máximo diez páginas.
- Proyecto Java: documentado con un PDF de máximo seis páginas.

 **Formato:**
- Video y zip de proyecto.
- Fuente: Calibri.
- Tamaño: 11 puntos.
- Interlineado: 1.5.
- Código con sangría y comentarios explicativos.

## Criterios de evaluación

Especifica los criterios con los que medir los resultados obtenidos por
el estudiante con la herramienta de la rúbrica.


| **Gestión de clientes y pedidos en una aplicación Java** | Descripción | Peso 
|---|---|---|
| Criterio 1 | Creación y serialización de objetos: definición de clases y guardado o lectura binaria| 15%   |
| Criterio 2      | Generación y transformación de XML: DOM y transformación XSL funcional   | 20%   |
| Criterio 3      | Consultas XPath: correctas y relevantes | 10% |
| Criterio 4      | Base de datos y conexión JDBC: tablas bien creadas, datos insertados | 20%   |
| Criterio 5      | Operaciones DML (UPDATE/DELETE): funcionales y seguras | 15%   |
| Criterio 6      | Manejo de transacciones: implementación completa de commit/rollback  | 10% |
| Criterio 7      | Documentación y claridad de código: proyecto ordenado, comentado, entrega clara | 10% |

