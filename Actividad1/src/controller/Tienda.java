package controller;

import model.Cliente;
import model.Pedido;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class Tienda {
    List<Cliente> clientes = new ArrayList<>();
    List<Pedido> pedidos = new ArrayList<>();
    String rutaBase = "src/resources/";
    String rutaXML = rutaBase+"xml";
    String rutaXSL = rutaBase+"xsl/";
    String rutaHTML = rutaBase+"html/";
    String rutaDatos = rutaBase+"datos/";
    File ficheroDatos = new File(rutaDatos, "datos.dat");

    // crear un cliente
    public void crearCliente(String nombre, String correo){
        int id = clientes.size() + 1;
        Cliente c = new Cliente(id, nombre, correo);
        clientes.add(c);
    }

    // mostrar los clientes por consola
    public void mostrarClientes(){
        for(Cliente c : clientes){
            System.out.println(c.toString());
        }
    }

    // devuelve el numero de clientee
    public int contarClientes(){
        return clientes.size();
    }

    // devuelve la lista de clientes en memoria
    public List<Cliente> obtenerClientes(){
        return clientes;
    }

    // crear un pedido
    public void crearPedido(int clienteId, String nombre, int cantidad){
        int id = pedidos.size() + 1;
        Pedido p = new Pedido(id, clienteId, nombre, cantidad);
        pedidos.add(p);
    }

    // contar los pedidos en memoria
    public int contarPedidos(){
        return pedidos.size();
    }

    // muestra los pedidos en memoria por consola
    public void mostrarPedidos(){
        for(Pedido p : pedidos){
            System.out.println(p.toString());
        }
    }

    // devuelve la lista de pedidos en memoria
    public List<Pedido> obtenerPedidos(){
        return pedidos;
    }

    // guarda los objetos en memoria en el fichero datos.dat
    public void guardarDatos(){
        File carpetaDatos = new File(rutaDatos);
        if (!carpetaDatos.exists()){
            carpetaDatos.mkdirs();
        }
        // crea un fichero en la carpeta datos llamado datos.dat
        if(!ficheroDatos.exists()){
            try{
                ficheroDatos.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear el fichero de guardado: "+e.getMessage());
            }
        }
        // stream de lectura del fichero
        try(ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(ficheroDatos))){
            /* grabado de las listas en el fichero
            stream.writeObject(clientes);
            stream.writeObject(pedidos);
             */

            // he preferido guardarlo por objetos para practicar
            for(Cliente c : clientes){
                stream.writeObject(c);
            }
            for(Pedido p : pedidos){
                stream.writeObject(p);
            }
            System.out.println("Datos guardados en datos.dat");
        } catch (FileNotFoundException e1) {
            System.out.println("No se ha encontrado el fichero: " + ficheroDatos +". "+ e1.getMessage());
        } catch (IOException e2) {
            System.out.println("Error al guardar los datos: " + e2.getMessage());
        }
    }

    // deserializacion
    public void cargarDatos(){
        clientes.clear();
        pedidos.clear();
        System.out.println("Leyendo el fichero de datos...");
        //stream de lectura del fichero
        try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream(ficheroDatos))){
            while(true) {
                Object o = stream.readObject();

                //este flujo lee por si esta guardado como lista o como objeto
                if(o instanceof List<?>){
                    for(Object elemento : (List<?>)o){
                        if(elemento instanceof Cliente){
                            clientes.add((Cliente)elemento);
                        } else if (elemento instanceof Pedido) {
                            pedidos.add((Pedido)elemento);
                        }
                    }
                } else if (o instanceof Cliente) {
                    clientes.add((Cliente)o);
                } else if (o instanceof Pedido) {
                    pedidos.add((Pedido)o);
                }
            }
            //uso la excepcion end of file para que la lectura acabe al terminar de leer todo el contenido del fichero
        } catch (EOFException e) {
            System.out.println("Final del fichero alcanzado. Datos cargados desde datos.dat.");
            this.mostrarClientes();
            this.mostrarPedidos();
        } catch (FileNotFoundException e1) {
            System.out.println("No se ha encontrado el fichero: " + ficheroDatos + ". " + e1.getMessage());
        } catch (IOException e2) {
            System.out.println("Error al leer los datos: " + e2.getMessage());
        } catch (ClassNotFoundException e3) {
            System.out.println("Clase no encontrada: " + e3.getMessage());
        }
    }

    // parseo con DOM de clientes
    public void crearClientesXML() throws ParserConfigurationException, TransformerException {
        // creacion de un documento DOM vacio y agregacion del elemento raiz
        Document documentoDOM = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = documentoDOM.createElement("clientes");
        documentoDOM.appendChild(root);

        // por cada objeto Cliente en la lista se genera un elemento <cliente>
        for(Cliente c : clientes){
            Element cliente = documentoDOM.createElement("cliente");
            cliente.setAttribute("id", String.valueOf(c.getId()));

            Element nombre = documentoDOM.createElement("nombre");
            nombre.setTextContent(c.getNombre());
            cliente.appendChild(nombre);

            Element email = documentoDOM.createElement("email");
            email.setTextContent(c.getEmail());
            cliente.appendChild(email);

            root.appendChild(cliente);
        }
        guardarXML(documentoDOM, rutaXML, "clientes.xml");
    }

    //-----------------------------------
    // parseo con DOM de pedidos
    private void crearPedidosXML() throws ParserConfigurationException, TransformerException {
        // creacion de un documento DOM vacio y agregacion del elemento raiz
        Document documentoDOM = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = documentoDOM.createElement("pedidos");
        documentoDOM.appendChild(root);

        // por cada objeto Pedido en la lista se genera un elemento <pedido> y clienteId enlaza con el cliente
        for(Pedido p : pedidos){
            Element pedido = documentoDOM.createElement("pedido");
            pedido.setAttribute("id", String.valueOf(p.getId()));
            pedido.setAttribute("clienteId", String.valueOf(p.getClienteId()));

            Element producto = documentoDOM.createElement("producto");
            producto.setTextContent(p.getProducto());
            pedido.appendChild(producto);

            Element cantidad = documentoDOM.createElement("cantidad");
            cantidad.setTextContent(String.valueOf(p.getCantidad()));
            pedido.appendChild(cantidad);

            root.appendChild(pedido);
        }
        guardarXML(documentoDOM, rutaXML, "pedidos.xml");
    }

    public void crearXML(){
        File carpetaXML = new File(rutaXML);
        if(!carpetaXML.exists()){
            carpetaXML.mkdirs();
        }
        try{
            crearClientesXML();
            crearPedidosXML();
            System.out.println("Creados ficheros clientes.xml y pedidos.xml " + rutaXML);
        } catch (ParserConfigurationException e1) {
            System.out.println("Error al configurar el parser XML: " + e1.getMessage());
        } catch (TransformerException e2) {
            System.out.println("Error al escribir el XML: " + e2.getMessage());
        }
    }

    // volcado del arbol DOM en fichero XML con sangria
    private void guardarXML(Document documentoDOM, String ruta, String nombreXML) throws TransformerException{
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.transform(new DOMSource(documentoDOM), new StreamResult(new File(ruta, nombreXML)));
    }

    //-----------------------------------------
    // transformacion aplicando pedidos.xsl a pedidos.xml, generando pedidos.html
    public void transformarPedidos(){
        File xml = new File(rutaXML, "pedidos.xml");
        File xsl = new File(rutaXSL, "pedidos.xsl");

        File carpetaHTML = new File(rutaHTML);
        if(!carpetaHTML.exists()){carpetaHTML.mkdirs();}
        File html = new File(rutaHTML, "pedidos.html");

        try{
            Transformer transformer = TransformerFactory.newInstance().newTransformer(new StreamSource(xsl));
            transformer.transform(new StreamSource(xml), new StreamResult(html));
            System.out.println("Fichero pedidos.html generado en " + rutaHTML);
        } catch (TransformerException e) {
            System.out.println("Error al aplicar la hoja XSL: " + e.getMessage());
        }
    }

    //--------------------------------------------
    // consultas XPath
    // metodo para ejecutar expresiones sobre pedidos.xml y mostrarlos
    private void consultarPedidos(String expresion){
        try{
            // cargar el xml en un DOM
            Document documentoDOM = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new File(rutaXML, "pedidos.xml"));

            // evaluacion de la expresion y obtencion de la lista de nodos
            NodeList resultado = (NodeList) XPathFactory.newInstance().newXPath()
                    .evaluate(expresion, documentoDOM, XPathConstants.NODESET);

            if(resultado.getLength() == 0){
                System.out.println("  No hay resultados");
            }
            // recorre todos los resultados y los muestra
            for(int i = 0; i < resultado.getLength(); i++){
                Element pedido = (Element) resultado.item(i);
                System.out.println("  Pedido: "+pedido.getAttribute("id")+", cliente: "+
                        pedido.getAttribute("clienteId") + ", " + pedido.getElementsByTagName("producto")
                        .item(0).getTextContent()+ ", cantidad: " + pedido.getElementsByTagName("cantidad")
                        .item(0).getTextContent());
            }
        } catch (ParserConfigurationException | SAXException e1) {
            System.out.println("Error al leer el XML: " + e1.getMessage());
        } catch (IOException e2) {
            System.out.println("No se ha podido abrir pedidos.xml: " + e2.getMessage());
        } catch (XPathExpressionException e3) {
            System.out.println("Expresión XPath incorrecta: " + e3.getMessage());
        }
    }

    // consulta XPath 1 - pedidos con cantidad mayor que un valor minimo
    public void consultarPedidosCantidadMayor(int minimo){
        System.out.println("Pedidos con cantidad mayor que " + minimo + ":");
        consultarPedidos("//pedido[cantidad > " + minimo + "]");
    }

    // Consulta XPath 2 - pedidos de un cliente concreto
    public void consultarPedidosDeCliente(int clienteId){
        System.out.println("Pedidos del cliente " + clienteId + ":");
        consultarPedidos("//pedido[@clienteId='" + clienteId + "']");
    }

    // Consulta XPath 3 - quien ha pedido a wooyoung de ateez
    public void consultarPedidoWooyoung(){
        System.out.println("Yo tambien me preguntaba quién había pedido a Wooyoung... Este es el resumen " +
                "del pedido: ");
        consultarPedidos("//pedido[producto='" + "Wooyoung" + "']");
    }

}
