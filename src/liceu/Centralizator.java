/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package liceu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

/**
 *
 * @author Madalin
 */
public class Centralizator {
    private ArrayList<Utilizator> utilizatori;
    private ArrayList<Clasa> clase;
    private HashMap<Materie,Object> materii_profesori_clase;
    private static Centralizator instance=null;
    private Centralizator()throws IOException, ClassNotFoundException{
        FileInputStream f_in = new FileInputStream("Utilizatori.data");
        ObjectInputStream obj_in = new ObjectInputStream (f_in);
        utilizatori =(ArrayList)obj_in.readObject();
        f_in = new FileInputStream("Clase.data");
        obj_in = new ObjectInputStream(f_in);
        clase = (ArrayList)obj_in.readObject();
        f_in = new FileInputStream("DictionarCentralizator.data");
        obj_in = new ObjectInputStream(f_in);
        this.materii_profesori_clase = (HashMap)obj_in.readObject();
    }
    public static Centralizator getInstance() throws IOException, ClassNotFoundException {
	if (instance == null) {
		synchronized (Centralizator.class) {
			instance = new Centralizator();
		}
	}
	
        return instance;
    }
    public void actualizare() throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream f_in = new FileInputStream("Utilizatori.data");
        ObjectInputStream obj_in = new ObjectInputStream (f_in);
        utilizatori =(ArrayList)obj_in.readObject();
        f_in = new FileInputStream("Clase.data");
        obj_in = new ObjectInputStream(f_in);
        clase = (ArrayList)obj_in.readObject();
        f_in = new FileInputStream("DictionarCentralizator.data");
        obj_in = new ObjectInputStream(f_in);
        this.materii_profesori_clase = (HashMap)obj_in.readObject();
    }
    public ArrayList<Utilizator> getUsers(){
        return this.utilizatori;
    }
    public ArrayList<Clasa> getClase(){
        return this.clase;
    }
    public HashMap<Materie,Object> getDict(){
        return this.materii_profesori_clase;
    }
}
