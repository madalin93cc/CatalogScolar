/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package liceu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Madalin
 */
public class Administrator extends Utilizator implements IAdministrator{
    public Administrator(String user,String pass,String nume,String prenume){
        super(UserType.ADMINISTRATOR,user,pass,nume,prenume);
    }
    protected void initializare(Object o1,Object o2){}
    public static Utilizator CreateUser(UserType type,String user,String pass,
            String nume,String prenume,String cnp,Data datan,Materie materie){
        Utilizator util=null;
        switch (type){
            case ELEV:{
                util=new Elev(user,pass,nume,prenume,cnp,datan);
                break;
            }
            case PROFESOR:{
                util=new Profesor(user,pass,nume,prenume,materie);
                break;
            }
            case ADMINISTRATOR:{
                util=new Administrator(user,pass,nume,prenume);
                break;
            }
            case SECRETAR:{
                util=new Secretar(user,pass,nume,prenume);
                break;
            }
            default :{
                System.out.println("Tip necunoscut");
                break;
            }
        }
        return util;
    }
    public void addUser(Utilizator o){
        try {
            Centralizator c=Centralizator.getInstance();
            ArrayList<Utilizator> users=c.getUsers();
            users.add(o);
            FileOutputStream f_out = new FileOutputStream("Utilizatori.data");
            ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
            obj_out.writeObject (users);
            c.actualizare();
        } catch (IOException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void delUser(Utilizator o){
        try {
            Centralizator c=Centralizator.getInstance();
            if (o instanceof Elev){
                Elev e=(Elev)o;
                ArrayList clase=c.getClase();
                for (Iterator j=clase.iterator();j.hasNext();){
                    Clasa cl=(Clasa)j.next();
                    if (cl.containsElev(e)){
                        cl.removeElev(e);
                        FileOutputStream f_out = new FileOutputStream("Clase.data");
                        ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
                        obj_out.writeObject (clase);
                    }
                }
            }
            
            ArrayList<Utilizator> users=c.getUsers();
            int k=-1;
            for (Iterator i=users.iterator();i.hasNext();){
                Utilizator obj=(Utilizator)i.next();
                k++;
                if (obj.compareTo(o)){
                    break;
                }
            }
            users.remove(users.get(k));
            FileOutputStream f_out = new FileOutputStream("Utilizatori.data");
            ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
            obj_out.writeObject (users);
            c.actualizare();
        } catch (IOException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<Utilizator> listUsers(){
        Centralizator c=null;
        try {
            c = Centralizator.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c.getUsers();
    }
    public ArrayList<Elev> listElevi() {
        ArrayList<Elev> elevi = null;
        try {
            Centralizator c=Centralizator.getInstance();
            ArrayList<Utilizator> users=c.getUsers();
            elevi=new ArrayList<>();
            for(Iterator i=users.iterator();i.hasNext();){
                Object o=i.next();
                if (o instanceof Elev){
                    Elev elev=(Elev)o;
                    elevi.add(elev);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return elevi;
    }
    public ArrayList<Profesor> listProfesori() {
        ArrayList<Profesor> prof = null;
        try {
            Centralizator c=Centralizator.getInstance();
            ArrayList<Utilizator> users=c.getUsers();
            prof=new ArrayList<>();
            for(Iterator i=users.iterator();i.hasNext();){
                Object o=i.next();
                if (o instanceof Profesor){
                    Profesor pr=(Profesor)o;
                    prof.add(pr);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prof;
    }
    public void addClasa(String id) throws FileNotFoundException, IOException, ClassNotFoundException{
        Clasa clasa=new Clasa(id);
        Centralizator c=Centralizator.getInstance();
        ArrayList<Clasa> clase=c.getClase();
        for (Iterator i=clase.iterator();i.hasNext();){
            Clasa cl=(Clasa) i.next();
            if (cl.compareTo(clasa)==0){
                return;
            }
        }
        clase.add(clasa);
        FileOutputStream f_out = new FileOutputStream("Clase.data");
        ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
        obj_out.writeObject (clase);
        c.actualizare();
    }
    public ArrayList<Elev> ordElevi(ArrayList<Elev>elevi,int opt){
        if (opt==0){
            Collections.sort(elevi,new Comparator (){
            public int compare(Object o1,Object o2){
                Elev e1=(Elev)o1;
                Elev e2=(Elev)o2;
                return e1.getNume().compareTo(e2.getNume());                           
            }
        });
        }
        if (opt==1){
            Collections.sort(elevi,new Comparator (){
            public int compare(Object o1,Object o2){
                Elev e1=(Elev)o1;
                Elev e2=(Elev)o2;
                String abs1=null,abs2=null;
                try {
                    abs1=e1.getAbsente();
                } catch (IOException ex) {
                    Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    abs2=e2.getAbsente();
                } catch (IOException ex) {
                    Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
                }
                return abs1.length()-abs2.length();
            }
        });
        }
        return elevi;
    }
    public void addNota(Clasa clasa, Elev elev, Materie materie, int nota) {
        Centralizator c=null;
        try {
            c = Centralizator.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(Profesor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Profesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        Catalog cat=null;
        ArrayList<Clasa> clase=c.getClase();
        for (Iterator i=clase.iterator();i.hasNext();){
            Clasa cl=(Clasa)i.next();
            if (cl.equals(clasa)){
                cat=cl.getCatalog();
            }
        }
        Set<Elev> elevi=cat.keySet();
        HashMap<Materie,SituatieMaterieBaza> map=null;
        for (Iterator i=elevi.iterator();i.hasNext();){
            Elev el=(Elev)i.next();
            if (el.equals(elev)){
                map=(HashMap)cat.get(el);
            }
        }
        SituatieMaterieBaza sit=null;
        Set<Materie>materii=map.keySet();
        for(Iterator i=materii.iterator();i.hasNext();){
            Materie mat=(Materie)i.next();
            if (mat.compareTo(materie)){
                sit=map.get(mat);
            }
        }
        sit.addNota(nota);
        try {
            c.actualizare();
        } catch (IOException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void modifMedie(Clasa clasa, Elev elev, Materie materie) {
        Centralizator c=null;
        try {
            c = Centralizator.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(Profesor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Profesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        Catalog cat=null;
        ArrayList<Clasa> clase=c.getClase();
        for (Iterator i=clase.iterator();i.hasNext();){
            Clasa cl=(Clasa)i.next();
            if (cl.equals(clasa)){
                cat=cl.getCatalog();
            }
        }
        Set<Elev> elevi=cat.keySet();
        HashMap<Materie,SituatieMaterieBaza> map=null;
        for (Iterator i=elevi.iterator();i.hasNext();){
            Elev el=(Elev)i.next();
            if (el.equals(elev)){
                map=(HashMap)cat.get(el);
            }
        }
        SituatieMaterieBaza sit=null;
        Set<Materie>materii=map.keySet();
        for(Iterator i=materii.iterator();i.hasNext();){
            Materie mat=(Materie)i.next();
            if (mat.compareTo(materie)){
                sit=map.get(mat);
            }
        }
        sit.calculMedie();
        try {
            c.actualizare();
        } catch (IOException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void addAbsenta(Clasa clasa, Elev elev, Materie materie, Data abs) {
        Centralizator c=null;
        try {
            c = Centralizator.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(Profesor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Profesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        Catalog cat=null;
        ArrayList<Clasa> clase=c.getClase();
        for (Iterator i=clase.iterator();i.hasNext();){
            Clasa cl=(Clasa)i.next();
            if (cl.equals(clasa)){
                cat=cl.getCatalog();
            }
        }
        Set<Elev> elevi=cat.keySet();
        HashMap<Materie,SituatieMaterieBaza> map=null;
        for (Iterator i=elevi.iterator();i.hasNext();){
            Elev el=(Elev)i.next();
            if (el.equals(elev)){
                map=(HashMap)cat.get(el);
            }
        }
        SituatieMaterieBaza sit=null;
        Set<Materie>materii=map.keySet();
        for(Iterator i=materii.iterator();i.hasNext();){
            Materie mat=(Materie)i.next();
            if (mat.compareTo(materie)){
                sit=map.get(mat);
            }
        }
        sit.addAbsenta(abs);
        try {
            c.actualizare();
        } catch (IOException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void modifAbsenta(Clasa clasa, Elev elev, Materie materie, Data abs, String status) {
        Centralizator c=null;
        try {
            c = Centralizator.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(Profesor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Profesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        Catalog cat=null;
        ArrayList<Clasa> clase=c.getClase();
        for (Iterator i=clase.iterator();i.hasNext();){
            Clasa cl=(Clasa)i.next();
            if (cl.equals(clasa)){
                cat=cl.getCatalog();
            }
        }
        Set<Elev> elevi=cat.keySet();
        HashMap<Materie,SituatieMaterieBaza> map=null;
        for (Iterator i=elevi.iterator();i.hasNext();){
            Elev el=(Elev)i.next();
            if (el.equals(elev)){
                map=(HashMap)cat.get(el);
            }
        }
        SituatieMaterieBaza sit=null;
        Set<Materie>materii=map.keySet();
        for(Iterator i=materii.iterator();i.hasNext();){
            Materie mat=(Materie)i.next();
            if (mat.compareTo(materie)){
                sit=map.get(mat);
            }
        }
        sit.modificaAbsenta(abs, status);
        try {
            c.actualizare();
        } catch (IOException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void addClasa(Clasa clasa)throws FileNotFoundException, IOException, ClassNotFoundException {
        Centralizator c=Centralizator.getInstance();
        ArrayList<Clasa> clase=c.getClase();
        for (Iterator i=clase.iterator();i.hasNext();){
            Clasa cl=(Clasa) i.next();
            if (cl.compareTo(clasa)==0){
                return;
            }
        }
        clase.add(clasa);
        FileOutputStream f_out = new FileOutputStream("Clase.data");
        ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
        obj_out.writeObject (clase);
        c.actualizare();
    }
    public void delClasa(Clasa clasa)throws FileNotFoundException, IOException, ClassNotFoundException {
        Centralizator c=Centralizator.getInstance();
        ArrayList<Clasa> clase=c.getClase();
        for (Iterator i=clase.iterator();i.hasNext();){
            Clasa cl=(Clasa) i.next();
            if (cl.compareTo(clasa)==0) {
                clase.remove(cl);
                FileOutputStream f_out = new FileOutputStream("Clase.data");
                ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
                obj_out.writeObject (clase);
                c.actualizare();
                return;
            }
        }
    }
    public float calculMedieGen(Clasa clasa) throws FileNotFoundException, IOException, ClassNotFoundException{
        return 0;
    }
    public ArrayList<Elev> listEleviClasa(String clasa) {
        Centralizator c=null;
        try {
            c = Centralizator.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Clasa> clase =c.getClase();
        Clasa cl=null;
        for(Iterator i=clase.iterator();i.hasNext();){
            cl=(Clasa)i.next();
            if (cl.toString().compareTo(clasa)==0){
                break;
            }
        }
        try {
            c.actualizare();
        } catch (IOException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cl.getElevi();
    }
    
    public ArrayList<Elev> listEleviClasaOrd(String clasa,int opt) {
        ArrayList<Elev>eleviord=this.ordElevi(this.listEleviClasa(clasa), opt);
        return eleviord;
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
        ArrayList<Profesor> profi=this.listProfesori();
        for (Iterator i=profi.iterator();i.hasNext();){
            Profesor pr=(Profesor) i.next();
            if (pr.equals(p)){
                pr.setMaterie(m);
            }
        }
    }
    public void modMatProf(Materie m, Profesor p) throws FileNotFoundException, IOException, ClassNotFoundException {
        this.addMatProf(m, p);
    }
    public void delMatProf(Profesor p) throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<Profesor> profi=this.listProfesori();
        for (Iterator i=profi.iterator();i.hasNext();){
            Profesor pr=(Profesor) i.next();
            if (pr.equals(p)){
                pr.delMaterie();
            }
        }
    }
    public void addElevClasa(Clasa clasa, Elev elev) throws FileNotFoundException, IOException, ClassNotFoundException {
        Centralizator c=Centralizator.getInstance();
        ArrayList<Clasa> clase=c.getClase();
        for(Iterator i=clase.iterator();i.hasNext();){
            Clasa cl=(Clasa) i.next();
            if (cl.equals(clasa)){
                cl.addElev(elev);
            }
        }
        c.actualizare();
    }
    public void delElevClasa(Clasa clasa, Elev elev) throws FileNotFoundException, IOException, ClassNotFoundException {
        Centralizator c=Centralizator.getInstance();
        ArrayList<Clasa> clase=c.getClase();
        for(Iterator i=clase.iterator();i.hasNext();){
            Clasa cl=(Clasa) i.next();
            if (cl.equals(clasa)){
                cl.removeElev(elev);
            }
        }
        c.actualizare();
    }
    public void addMatClasa(Clasa clasa, Materie materie) throws FileNotFoundException, IOException, ClassNotFoundException {
        Centralizator c=Centralizator.getInstance();
        ArrayList<Clasa> clase=c.getClase();
        for(Iterator i=clase.iterator();i.hasNext();){
            Clasa cl=(Clasa) i.next();
            if (cl.equals(clasa)){
                cl.addMaterie(materie);
            }
        }
        c.actualizare();
    }
    public void delMatClasa(Clasa clasa, Materie materie) throws FileNotFoundException, IOException, ClassNotFoundException {
        Centralizator c=Centralizator.getInstance();
        ArrayList<Clasa> clase=c.getClase();
        for(Iterator i=clase.iterator();i.hasNext();){
            Clasa cl=(Clasa) i.next();
            if (cl.equals(clasa)){
                cl.removeMaterie(materie);
            }
        }
        c.actualizare();
    }
    public Set<Materie> listMaterii() throws IOException, ClassNotFoundException{
        Centralizator c=null;
        c=Centralizator.getInstance();
        HashMap map=c.getDict();
        Set<Materie> set=map.keySet();
        return set;
    }
}
