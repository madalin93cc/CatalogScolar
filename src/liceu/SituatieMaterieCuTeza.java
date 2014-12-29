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
public class SituatieMaterieCuTeza extends SituatieMaterieBaza implements Serializable{
    Integer nota_teza1,nota_teza2;
    public SituatieMaterieCuTeza(Materie m){
        super(m);
        this.nota_teza1=this.nota_teza2=null;
    }
    public SituatieMaterieCuTeza(){
        this(null);
    }
    public Integer getTeza1(){
        return this.nota_teza1;
    }
    public Integer getTeza2(){
        return this.nota_teza2;
    }
    public String dispSitNote(){
        String s="";
        s=s+"Semestrul 1:\n\t";
        for (Iterator i=this.note_sem1.iterator();i.hasNext();){
            Integer nota=(Integer)i.next();
            s=s+nota+" ";
        }
        s=s+"\n\tNotaTeza:"+this.nota_teza1;
        s=s+"\nSemestrul 2:\n\t";
        for (Iterator i=this.note_sem2.iterator();i.hasNext();){
            Integer nota=(Integer)i.next();
            s=s+nota;
        }
        s=s+"\n\tNotaTeza:"+this.nota_teza2+"\n";
        return s;
    }
    public void addNotaTeza(Integer nota){
        if (this.nota_teza1==null){
            this.nota_teza1=nota;
        }
        else this.nota_teza2=nota;
    }
    public Integer getNotaTeza(){
        if (this.nota_teza1!=null)
            return this.nota_teza1;
        else return this.nota_teza2;
    }

    public void calculMedie() {
        //System.out.println(this.note_sem1);
        int s=0;
        float med;
        if (super.medie1==null){
            for (Iterator i=note_sem1.iterator();i.hasNext();){
                Integer nota=(Integer)i.next();
                s=s+nota;
            }
            med=(float)(((float)s/note_sem1.size())*3+this.nota_teza1)/4;
            medie1=Math.round(med);
        }
        else {
            for (Iterator i=note_sem2.iterator();i.hasNext();){
                Integer nota=(Integer)i.next();
                s=s+nota;
            }
            med=(float)(((float)s/note_sem2.size())*3+this.nota_teza2)/4;
            medie2=Math.round(med);
            med=(float)(medie1+medie2)/2;
            medie=Math.round(med);
        }
        
    }
}
