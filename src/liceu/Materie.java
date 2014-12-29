/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package liceu;

import java.io.Serializable;

/**
 *
 * @author Madalin
 */
public class Materie implements Serializable{
    private String nume;
    private int ore;
    private boolean teza;
    public Materie(String nume,int ore,boolean teza){
        this.nume=nume;
        this.ore=ore;
        this.teza=teza;
    }
    public String toString(){
        return this.nume+"\n";
    }
    public String getNume(){
        return this.nume;
    }
    public int getOre(){
        return this.ore;
    }
    public boolean getTeza(){
        return this.teza;
    }
    public void setNume(String nume){
        this.nume=nume;
    }
    public void setOre(int ore){
        this.ore=ore;
    }
    public void setTeza(boolean teza){
        this.teza=teza;
    }
    public boolean compareTo(Materie mat){
        return ((this.nume.compareTo(mat.getNume())==0) &&
                (this.ore==mat.getOre())&&
                (this.teza==mat.getTeza()));
    }
    public boolean equals(Object o){
        Materie m=(Materie)o;
        return this.compareTo(m);
    }
}
