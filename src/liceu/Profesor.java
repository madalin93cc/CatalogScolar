/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package liceu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Madalin
 */
public class Profesor extends Utilizator implements IProfesor{
    private Materie materie;
    
    public Profesor(String user,String pass,String nume,String prenume,Materie materie){
        super(UserType.PROFESOR,user,pass,nume,prenume);
        initializare(materie,null);
    }
    public boolean compareTo(Profesor p){
        boolean ok=super.compareTo(p);
        return (ok) && (this.getMaterie().compareTo(p.getMaterie()));
    }
    protected void initializare(Object o1,Object o2){
        this.setMaterie((Materie)o1);
    }
    public Materie getMaterie(){
        return this.materie;
    }
    public void setMaterie(Materie materie){
        this.materie=materie;
    }
    public void delMaterie(){
        this.materie=null;
    }
    public ArrayList<Elev> listEleviClasa(String clasa){
        Administrator adm=new Administrator ("","","","");
        return adm.listEleviClasa(clasa);
    }
    public ArrayList<Elev> listEleviClasaOrd(String clasa,int opt){
        Administrator adm=new Administrator ("","","","");
        return adm.listEleviClasaOrd(clasa, opt);
    }
    public ArrayList<Elev> ordElevi(ArrayList<Elev>elevi,int opt){
        Administrator adm=new Administrator ("","","","");
        return adm.ordElevi(elevi, opt);
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
    }
}
