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
public class Data implements Serializable{
    private Integer an,luna,zi;
    public Data(Integer an,Integer luna,Integer zi){
        this.an=an;
        this.luna=luna;
        this.zi=zi;
    }
    public Data(String an,String luna,String zi){
        this.an=Integer.parseInt(an);
        this.luna=Integer.parseInt(luna);
        this.zi=Integer.parseInt(zi);
    }
    public Data(String an,int luna,String zi){
        this.an=Integer.parseInt(an);
        this.luna=luna;
        this.zi=Integer.parseInt(zi);
    }
    public Integer getZi(){
        return this.zi;
    }
    public Integer getLuna(){
        return this.luna;
    }
    public Integer getAn(){
        return this.an;
    }
    public void setZi(Integer zi){
        this.zi=zi;
    }
    public void setLuna(Integer luna){
        this.luna=luna;
    }
    public void setAn(Integer an){
        this.an=an;
    }
    public boolean compareTo(Data d){
        return ((this.zi.compareTo(d.getZi())==0) &&
               (this.luna.compareTo(d.getLuna())==0) &&
               (this.an.compareTo(d.getAn())==0));
    }
    public String toString(){
        return this.getZi()+"/"+this.getLuna()+"/"+this.getAn();
    }
}
