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
public abstract class Utilizator implements Serializable{
    private String numeUtilizator;
    private String parola;
    private String nume;
    private String prenume;
    private UserType type=null;
    public String toString() {
        return this.nume+" "+this.prenume+"\n";
    }
    public Utilizator(UserType type,String user,String pass,String nume,String prenume){
        this.type=type;
        this.setNumeUtilizator(user);
        this.setParola(pass);
        this.setNume(nume);
        this.setPrenume(prenume);
    }
    protected abstract void initializare(Object o1,Object o2);
    public String getNume(){
        return this.nume;
    }
    public String getType(){
        return this.type.toString();
    }
    public String getPrenume(){
        return this.prenume;
    }
    public String getNumeUtilizator(){
        return this.numeUtilizator;
    }
    public String getParola(){
        return this.parola;
    }
    public void setNumeUtilizator(String s){
        this.numeUtilizator=s;
    }
    public void setParola(String s){
        this.parola=s;
    }
    public void setNume(String s){
        this.nume=s;
    }
    public void setPrenume(String s){
        this.prenume=s;
    }
    public boolean compareTo(Utilizator o){
        return (this.getNume().compareTo(o.getNume())==0) &&
               (this.getPrenume().compareTo(o.getPrenume())==0) &&
               (this.getNumeUtilizator().compareTo(o.getNumeUtilizator())==0) &&
               (this.getParola().compareTo(o.getParola())==0);
    }
    public boolean equals(Object o){
        Utilizator u=(Utilizator) o;
        return this.compareTo(u);
    }
}
