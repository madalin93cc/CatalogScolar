/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package liceu;

import java.util.ArrayList;
import java.util.Comparator;
import liceu.SituatieMaterieBaza.Absenta;

/**
 *
 * @author Madalin
 */
public interface IProfesor {
    ArrayList<Elev> listEleviClasa(String clasa);
    ArrayList<Elev> ordElevi(ArrayList<Elev>elevi,int opt);
    void addNota(Clasa clasa,Elev elev,Materie materie,int nota);
    void modifMedie(Clasa clasa,Elev elev,Materie materie);
    void addAbsenta(Clasa clasa,Elev elev,Materie materie,Data abs);
    void modifAbsenta(Clasa clasa,Elev elev,Materie materie,Data abs,String status);
}
