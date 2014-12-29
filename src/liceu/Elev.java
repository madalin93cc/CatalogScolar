/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package liceu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import liceu.SituatieMaterieBaza.Absenta;


/**
 *
 * @author Madalin
 */
public class Elev extends Utilizator implements IElev{
    private String CNP;
    private Data data_nastere;
    public Elev(String user,String pass,String nume,String prenume,String cnp,Data datan){
        super(UserType.ELEV,user,pass,nume,prenume);
        initializare(cnp,datan);        
    }
    protected void initializare(Object cnp,Object datan){
        this.setCNP((String)cnp);
        this.setDatNasterii((Data)datan);
    }
    public String getDatePersonale(){
        String s="";
        s=s+"Nume: "+this.getNume()+"\n"
           +"Prenume: "+this.getPrenume()+"\n"
           +"Username: "+this.getNumeUtilizator()+"\n"
           +"Parola: "+this.getParola()+"\n"
           +"CNP: "+this.getCNP()+"\n"
           +"Data nasterii: "+this.getDataNasterii().toString()+"\n";
        return s;
    }
    public String getNote() throws IOException, ClassNotFoundException{
        String s="";
        Centralizator c=Centralizator.getInstance();
        ArrayList<Clasa> clase=c.getClase();
        Clasa clasa=null;
        for (Iterator i=clase.iterator();i.hasNext();){
            clasa=(Clasa)i.next();
            if (clasa.containsElev(this)){
                break;
            }
        }
        Catalog cat=clasa.getCatalog();
        Set<Elev> elevi=cat.keySet();
        Elev e=null;
        for(Iterator i=elevi.iterator();i.hasNext();){
            e=(Elev)i.next();
            if (e.compareTo(this)){
                break;
            }
        }
        HashMap<Materie,SituatieMaterieBaza> sit=(HashMap)cat.get(e);
        ArrayList<Materie>materii=clasa.getMaterii();
        for (int i=0;i<materii.size();i++){
            SituatieMaterieBaza sm=sit.get(materii.get(i));
            s=s+materii.get(i);
            s=s+sm.dispSitNote();
        }        
        return s;
    }
    public String getAbsente() throws IOException, ClassNotFoundException{
        String s="";
        Centralizator c=Centralizator.getInstance();
        ArrayList<Clasa> clase=c.getClase();
        Clasa clasa=null;
        for (Iterator i=clase.iterator();i.hasNext();){
            clasa=(Clasa)i.next();
            if (clasa.containsElev(this)){
                break;
            }
        }
        Catalog cat=clasa.getCatalog();
        Set<Elev> elevi=cat.keySet();
        Elev e=null;
        for(Iterator i=elevi.iterator();i.hasNext();){
            e=(Elev)i.next();
            if (e.compareTo(this)){
                break;
            }
        }
        HashMap<Materie,SituatieMaterieBaza> sit=(HashMap)cat.get(e);
        ArrayList<Materie>materii=clasa.getMaterii();
        for (int i=0;i<materii.size();i++){
            SituatieMaterieBaza sm=sit.get(materii.get(i));
            s=s+materii.get(i);
            s=s+sm.dispSitAbs();
        }        
        return s;
    }
    public String getMedii() throws IOException, ClassNotFoundException{
        String s="";
        Centralizator c=Centralizator.getInstance();
        ArrayList<Clasa> clase=c.getClase();
        Clasa clasa=null;
        for (Iterator i=clase.iterator();i.hasNext();){
            clasa=(Clasa)i.next();
            if (clasa.containsElev(this)){
                break;
            }
        }
        Catalog cat=clasa.getCatalog();
        Set<Elev> elevi=cat.keySet();
        Elev e=null;
        for(Iterator i=elevi.iterator();i.hasNext();){
            e=(Elev)i.next();
            if (e.compareTo(this)){
                break;
            }
        }
        HashMap<Materie,SituatieMaterieBaza> sit=(HashMap)cat.get(e);
        ArrayList<Materie>materii=clasa.getMaterii();
        for (int i=0;i<materii.size();i++){
            SituatieMaterieBaza sm=sit.get(materii.get(i));
            s=s+materii.get(i);
            s=s+sm.dispMedii();
        }        
        return s;    
        }
    public ArrayList<Integer> getNote1Array(Clasa clasa,Materie materie){
        Catalog catalog=clasa.getCatalog();
        HashMap<Materie,SituatieMaterieBaza>map=(HashMap)catalog.get(this);
        SituatieMaterieBaza sit=null;
        Set<Materie>materii=map.keySet();
        for(Iterator i=materii.iterator();i.hasNext();){
            Materie m=(Materie)i.next();
            if (m.equals(materie)){
                sit=map.get(m);
            }
        }
        return sit.getNoteSem1();
    }
    public ArrayList<Integer> getNote2Array(Clasa clasa,Materie materie){
        Catalog catalog=clasa.getCatalog();
        HashMap<Materie,SituatieMaterieBaza>map=(HashMap)catalog.get(this);
        SituatieMaterieBaza sit=null;
        Set<Materie>materii=map.keySet();
        for(Iterator i=materii.iterator();i.hasNext();){
            Materie m=(Materie)i.next();
            if (m.equals(materie)){
                sit=map.get(m);
            }
        }
        return sit.getNoteSem2();
    }
    public Integer getTeza1(Clasa clasa,Materie materie){
        Catalog catalog=clasa.getCatalog();
        HashMap<Materie,SituatieMaterieBaza>map=(HashMap)catalog.get(this);
        SituatieMaterieCuTeza sit=null;
        Set<Materie>materii=map.keySet();
        for(Iterator i=materii.iterator();i.hasNext();){
            Materie m=(Materie)i.next();
            if (m.equals(materie)){
                sit=(SituatieMaterieCuTeza)map.get(m);
            }
        }
        sit=(SituatieMaterieCuTeza)sit;
        return sit.getTeza1();
    }
    public Integer getTeza2(Clasa clasa,Materie materie){
        Catalog catalog=clasa.getCatalog();
        HashMap<Materie,SituatieMaterieBaza>map=(HashMap)catalog.get(this);
        SituatieMaterieCuTeza sit=null;
        Set<Materie>materii=map.keySet();
        for(Iterator i=materii.iterator();i.hasNext();){
            Materie m=(Materie)i.next();
            if (m.equals(materie)){
                sit=(SituatieMaterieCuTeza)map.get(m);
            }
        }
        sit=(SituatieMaterieCuTeza)sit;
        return sit.getTeza2();
    }
    public ArrayList<Absenta> getAbsente(Clasa clasa,Materie materie){
        Catalog catalog=clasa.getCatalog();
        HashMap<Materie,SituatieMaterieBaza>map=(HashMap)catalog.get(this);
        SituatieMaterieBaza sit=null;
        Set<Materie>materii=map.keySet();
        for(Iterator i=materii.iterator();i.hasNext();){
            Materie m=(Materie)i.next();
            if (m.equals(materie)){
                sit=map.get(m);
            }
        }
        return sit.getAbsente();
    }
    public String getCNP(){
        return this.CNP;
    }
    public Data getDataNasterii(){
        return this.data_nastere;
    }
    public void setCNP(String nou){
        this.CNP=nou;
    }
    public void setDatNasterii(Data nou){
        this.data_nastere=nou;
    }
    public boolean compareTo(Elev e){
        boolean ok=super.compareTo(e);
        return (ok) &&
               (this.getCNP().compareTo(e.getCNP())==0) &&
               (this.getDataNasterii().compareTo(e.getDataNasterii())==true);
    }
}
