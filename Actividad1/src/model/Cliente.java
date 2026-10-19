package model;

import java.io.Serializable;

public class Cliente implements Serializable {
    private int id;
    private String nombre;
    private String email;

    public Cliente (int id, String nombre, String email){
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public int getId(){return id;}
    public String getNombre(){return nombre;}
    public String getEmail(){return email;}
    public void setId(int id){this.id = id;};
    public void setNombre(String nombre){this.nombre = nombre;};
    public void setEmail(String email){this.email = email;};

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nombre=" + nombre + ", email=" + email + "]";
    }

    /*
    * private void writeObject(java.io.ObjectOutputStream out)
     throws IOException;
    * private void readObject(java.io.ObjectInputStream in)
     throws IOException, ClassNotFoundException;
    * private void readObjectNoData()
     throws ObjectStreamException;
    * */
}
