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
public class SituatieMaterieBaza implements Serializable{
    private Materie materie;
    protected ArrayList<Integer> note_sem1,note_sem2;
    protected Integer medie1,medie2,medie;
    private ArrayList<Absenta> absente;
    public SituatieMaterieBaza(Materie m){
        this.materie=m;
        note_sem1=new ArrayList<>();
        note_sem2=new ArrayList<>();
        medie=medie1=medie2=null;
        absente=new ArrayList<>();
    }
    public ArrayList<Integer> getNoteSem1(){
        return this.note_sem1;
    }
    public ArrayList<Integer> getNoteSem2(){
        return this.note_sem2;
    }
    public ArrayList<Absenta> getAbsente(){
        return this.absente;
    }
    public String dispSitNote(){
        String s="";
        s=s+"Semestrul 1:\n\t";
        for (Iterator i=this.note_sem1.iterator();i.hasNext();){
            Integer nota=(Integer)i.next();
            s=s+nota+" ";
        }
        s=s+"\nSemestrul 2:\n\t";
        for (Iterator i=this.note_sem2.iterator();i.hasNext();){
            Integer nota=(Integer)i.next();
            s=s+nota;
        }
        s=s+"\n";
        return s;
    }
    public String dispSitAbs(){
        String s="";
        s=s+"Absente:\n\t";
        for (Iterator i=this.absente.iterator();i.hasNext();){
            Absenta abs=(Absenta)i.next();
            s=s+abs;
        }
        s=s+"Numar total absente:"+absente.size()+"\n";
        return s;
    }
    public String dispMedii(){
        String s="\tMedie semestrul 1: ";
        s=s+this.medie1+"\n";
        s=s+"\tMedie semestrul 2: ";
        s=s+this.medie2+"\n";
        s=s+"\tMedie generala: ";
        s=s+this.medie+"\n";
        return s;
    }
    public SituatieMaterieBaza(){
        this(null);
    }
    public void addMaterie(Materie m){
        this.materie=m;
    }
    public void calculMedie(){
        int s=0;
        float med;
        if (medie1==null){
            for (Iterator i=note_sem1.iterator();i.hasNext();){
                Integer nota=(Integer)i.next();
                s=s+nota;
            }
            med=(float)s/note_sem1.size();
            medie1=Math.round(med);
        }
        else {
            for (Iterator i=note_sem2.iterator();i.hasNext();){
                Integer nota=(Integer)i.next();
                s=s+nota;
            }
            med=(float)s/note_sem2.size();
            medie2=Math.round(med);
            med=(float)(medie1+medie2)/2;
            medie=Math.round(med);
        }
    }
    public void addNota(Integer nota){
        if (medie1==null){
            note_sem1.add(nota);
        }
        else 
            note_sem2.add(nota);
    }
    public void addAbsenta(Data data){
        Absenta abs=new Absenta(data);
        absente.add(abs);
    }
    public void modificaAbsenta(Data data,String Status){
        Absenta abs=new Absenta(data);
        int k=0;
        for (Iterator i=absente.iterator();i.hasNext();){
            Absenta a=(Absenta)i.next();
            if (a.compareTo(abs)==true){
                break;
            }
            k++;
        }
        Absenta ab=absente.get(k);
        ab.changeStatus(Status);
    }
    public Integer getMedie1(){
        return this.medie1;
    }
    public Integer getMedie2(){
        return this.medie2;
    }
    public Integer getMedieGen(){
        return this.medie;
    }
    public Materie getMaterie(){
        return this.materie;
    }
    public ArrayList<Data> listAbs(){
        ArrayList<Data> datea=new ArrayList<>();
        for(Iterator i=absente.iterator();i.hasNext();){
            Absenta abs=(Absenta)i.next();
            datea.add(abs.getDate());
        }
        return datea;
    }
    public boolean equals(Object o){
        SituatieMaterieBaza sit=(SituatieMaterieBaza)o;
        return (this.materie.equals(sit.getMaterie()) &&
                this.absente.equals(sit.getAbsente()) &&
                this.note_sem1.equals(sit.getNoteSem1()) &&
                this.note_sem2.equals(sit.getNoteSem2()));
    }
    public class Absenta implements Serializable{
        private String status;
        private Data data;
        public Absenta(Data data,String status){
            this.data=data;
            this.status=status;
        }
        public Absenta(Data data){
            this(data,"nedeterminat");
        }
        public Data getDate(){
            return this.data;
        }
        public String getStatus(){
            return this.status;
        }
        public void changeData(Data nou){
            this.data=nou;
        }
        public void changeStatus(String nou){
            this.status=nou;
        }
        public boolean compareTo(Absenta abs){
            return this.getDate().compareTo(abs.getDate());
        }
        public String toString(){
            return this.data+":"+this.status+"\n";
        }
    }
}
