/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package liceu;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Madalin
 */
public interface ISecretar {
    void addClasa(String id)throws FileNotFoundException, IOException, ClassNotFoundException;
    void addClasa(Clasa clasa)throws FileNotFoundException, IOException, ClassNotFoundException;
    void delClasa(Clasa clasa)throws FileNotFoundException, IOException, ClassNotFoundException;
    float calculMedieGen(Clasa cl)throws FileNotFoundException, IOException, ClassNotFoundException;
    void modClasa(Clasa clasa,String newid) throws FileNotFoundException, IOException, ClassNotFoundException ;
    void addMatProf(Materie m,Profesor p) throws FileNotFoundException, IOException, ClassNotFoundException ;
    void modMatProf(Materie m,Profesor p) throws FileNotFoundException, IOException, ClassNotFoundException ;
    void delMatProf(Profesor p) throws FileNotFoundException, IOException, ClassNotFoundException ;
    void addElevClasa(Clasa clasa,Elev elev) throws FileNotFoundException, IOException, ClassNotFoundException ;
    void delElevClasa(Clasa clasa,Elev elev)throws FileNotFoundException, IOException, ClassNotFoundException ;
    void addMatClasa(Clasa clasa,Materie materie)throws FileNotFoundException, IOException, ClassNotFoundException ;
    void delMatClasa(Clasa clasa,Materie materie)throws FileNotFoundException, IOException, ClassNotFoundException ;
}
