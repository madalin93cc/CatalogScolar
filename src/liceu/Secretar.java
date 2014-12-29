/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package liceu;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Madalin
 */
public class Secretar extends Utilizator implements ISecretar{
    
    public Secretar(String user,String pass,String nume,String prenume){
        super(UserType.SECRETAR,user,pass,nume,prenume);  
    }
    protected void initializare(Object o1,Object o2){}
    public void addClasa(String id) throws FileNotFoundException, IOException, ClassNotFoundException {
        Administrator a=new Administrator("","","","");
        a.addClasa(id);
    }
    public void addClasa(Clasa clasa) throws FileNotFoundException, IOException, ClassNotFoundException{
       Administrator a=new Administrator("","","","");
       a.addClasa(clasa);
    }
    public void delClasa(Clasa clasa) throws IOException, FileNotFoundException, ClassNotFoundException {
        Administrator a=new Administrator("","","","");
        a.delClasa(clasa);
    }
    public float calculMedieGen(Clasa cl) throws FileNotFoundException, IOException, ClassNotFoundException{
        return 0;
    }
    public void modClasa(Clasa clasa, String newid)throws FileNotFoundException, IOException, ClassNotFoundException  {
        Centralizator c=Centralizator.getInstance();
        ArrayList<Clasa> clase=c.getClase();
        for (Iterator i=clase.iterator();i.hasNext();){
            Clasa cl=(Clasa) i.next();
            if (cl.compareTo(clasa)==0) {
                cl.modID(newid);
                FileOutputStream f_out = new FileOutputStream("Clase.data");
                ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
                obj_out.writeObject (clase);
                c.actualizare();
                return;
            }
        }
    }
    public void addMatProf(Materie m, Profesor p) throws FileNotFoundException, IOException, ClassNotFoundException {
        Administrator a=new Administrator("","","","");
        a.addMatProf(m, p);
    }
    public void modMatProf(Materie m, Profesor p) throws FileNotFoundException, IOException, ClassNotFoundException {
        Administrator a=new Administrator("","","","");
        a.modMatProf(m, p);
    }
    public void delMatProf(Profesor p) throws FileNotFoundException, IOException, ClassNotFoundException {
        Administrator a=new Administrator("","","","");
        a.delMatProf(p);
    }
    public void addElevClasa(Clasa clasa, Elev elev) throws FileNotFoundException, IOException, ClassNotFoundException {
        Administrator a=new Administrator("","","","");
        a.addElevClasa(clasa, elev);
    }
    public void delElevClasa(Clasa clasa, Elev elev) throws FileNotFoundException, IOException, ClassNotFoundException {
        Administrator a=new Administrator("","","","");
        a.delElevClasa(clasa, elev);
    }
    public void addMatClasa(Clasa clasa, Materie materie) throws FileNotFoundException, IOException, ClassNotFoundException {
        Administrator a=new Administrator("","","","");
        a.addMatClasa(clasa, materie);
    }
    public void delMatClasa(Clasa clasa, Materie materie) throws FileNotFoundException, IOException, ClassNotFoundException {
        Administrator a=new Administrator("","","","");
        a.delMatClasa(clasa, materie);
    }
    public ArrayList<Profesor>listProfesori(){
        Administrator a=new Administrator("","","","");
        return a.listProfesori();
    }
    public Set<Materie> listMaterii() throws IOException, ClassNotFoundException{
        Administrator a=new Administrator("","","","");
        return a.listMaterii();
    }
    public ArrayList<Elev> listElevi(){
        Administrator a=new Administrator("","","","");
        return a.listElevi();
    }
}
