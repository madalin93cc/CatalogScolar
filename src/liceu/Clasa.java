/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package liceu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Madalin
 */
public class Clasa implements Serializable{
    private String idClasa;
    private ArrayList<Elev> elevi;
    private ArrayList<Materie> materii;
    private Catalog catalog;
    public Clasa(String idClasa){
        this.idClasa=idClasa;
        elevi=new ArrayList<>();
        materii=new ArrayList<>();
        catalog=new Catalog();
    }
    public void addCatalog(Catalog c){
        this.catalog=c;
    }
    public void modID(String id){
        this.idClasa=id;
    }
    public void addElev(Elev e){
        elevi.add(e);
    }
    public void removeElev(Elev e){
        for (Iterator i=elevi.iterator();i.hasNext();){
            Elev el=(Elev)i.next();
            if (el.compareTo(e)) {
                elevi.remove(el);
            }
        }
    }
    public boolean containsElev(Elev e){
        for (Iterator i=elevi.iterator();i.hasNext();){
            Elev el=(Elev)i.next();
            if (el.compareTo(e)){
                return true;
            }
        }
        return false;
    }
    public void addMaterie(Materie m){
        materii.add(m);
    }
    public void removeMaterie(Materie m){
        for (Iterator i=materii.iterator();i.hasNext();){
            Materie mat=(Materie)i.next();
            if (mat.compareTo(m)) {
                materii.remove(mat);
            }
        }
    }
    public int compareTo(Clasa c){
        return this.getIdClasa().compareTo(c.getIdClasa());
    }
    public boolean equals(Object o){
        Clasa c=(Clasa)o;
        return (this.getIdClasa().compareTo(c.getIdClasa())==0);
    }
    public ArrayList<Elev> getElevi(){
        return this.elevi;
    }
    public ArrayList<Materie> getMaterii(){
        return this.materii;
    }
    public String getIdClasa(){
        return this.idClasa;
    }
    public Catalog getCatalog(){
        return this.catalog;
    }
    public String toString(){
        String s=this.idClasa+"\n";
        return s;
    }
}
