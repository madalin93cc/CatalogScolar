/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import liceu.Administrator;
import liceu.Catalog;
import liceu.Centralizator;
import liceu.Clasa;
import liceu.Data;
import liceu.Elev;
import liceu.Materie;
import liceu.Profesor;
import liceu.Secretar;
import liceu.SituatieMaterieBaza;
import liceu.SituatieMaterieCuTeza;
import liceu.UserType;
import liceu.Utilizator;

/**
 *
 * @author Madalin
 */
public class CatalogScolar {
    public static void main(String args[])throws IOException, ClassNotFoundException{
        //utilizatori
        Administrator admin=(Administrator)Administrator.CreateUser(UserType.ADMINISTRATOR,"a","a","a","a", null, null, null);
        Elev e1=new Elev("mada","mada","Colezea","Madalin","1930813277290",new Data(1993,8,13));
        Elev e2=(Elev)Administrator.CreateUser(UserType.ELEV,"gm","gm","Musat","George","1930813297290",new Data(1993,6,19),null);
        Profesor pm=new Profesor("pm","pm","Ion","Ionescu",null);
        Profesor pr=(Profesor)Administrator.CreateUser(UserType.PROFESOR,"pr","pr","Marian","Constantinescu", null, null, null);
        Profesor pp=new Profesor("pp","pp","Odubasteanu","Carmen",null);
        Secretar secretar=new Secretar("s","s","Andreea","Marin");
        //materii
        Materie romana=new Materie("Limba Romana",5,true);
        Materie matematica =new Materie("Matematica",6,true);
        Materie programare=new Materie("Programare",4,false);
        //clase
        Clasa clasa=new Clasa("325CC");
        Catalog cat=new Catalog();
        SituatieMaterieCuTeza sitr1=new SituatieMaterieCuTeza(romana);
        SituatieMaterieCuTeza sitr2=new SituatieMaterieCuTeza(romana);
        SituatieMaterieCuTeza sitm1=new SituatieMaterieCuTeza(matematica);
        SituatieMaterieCuTeza sitm2=new SituatieMaterieCuTeza(matematica);
        SituatieMaterieBaza sitp1=new SituatieMaterieBaza(programare);
        SituatieMaterieBaza sitp2=new SituatieMaterieBaza(programare);
        //situatii
        sitr1.addNota(9);
        sitr1.addNota(10);
        sitr1.addNotaTeza(10);
        sitr1.calculMedie();
        Data d1=new Data(2013,8,5);
        Data d2=new Data(2013,3,20);
        Data d3=new Data(2014,1,1);
        Data d4=new Data(2014,1,15);
        sitr1.addAbsenta(d1);
        sitr2.addAbsenta(d2);
        sitr1.addAbsenta(d4);
        sitr1.addNota(9);
        sitr1.addNota(8);
        sitr1.addNotaTeza(7);
        sitr1.calculMedie();
        sitr2.addNota(7);
        sitr2.addNota(10);
        sitr2.addNotaTeza(8);
        sitr2.calculMedie();
        sitm1.addNota(9);
        sitm1.addNota(5);
        sitm1.addNotaTeza(7);
        sitm1.calculMedie();
        d1=new Data(2013,8,5);
        d2=new Data(2013,3,20);
        sitm1.addAbsenta(d1);
        sitm1.addAbsenta(d2);
        
        sitm2.addNota(10);
        sitm2.addNota(10);
        sitm2.addNotaTeza(8);
        sitm2.calculMedie();
        sitm2.addAbsenta(d4);
        
        sitp1.addNota(6);
        sitp1.addNota(8);
        sitp1.calculMedie();
        sitp1.addAbsenta(d4);
        sitp2.addNota(7);
        sitp2.addNota(7);
        sitp2.calculMedie();
        sitp1.addAbsenta(d4);
        sitp1.addAbsenta(d4);
        //profesori-materii
        pm.setMaterie(matematica);
        pr.setMaterie(romana);
        pp.setMaterie(programare);
        //clasa
        clasa.addElev(e1);
        clasa.addElev(e2);
        clasa.addMaterie(romana);
        clasa.addMaterie(matematica);
        clasa.addMaterie(programare);
             //catalog
        HashMap<Materie,SituatieMaterieBaza> in=new HashMap<>();
        in.put(romana, sitr1);
        in.put(matematica, sitm1);
        in.put(programare, sitp1);
        cat.put(e1, in);
        in.clear();
        in.put(romana, sitr2);
        in.put(matematica, sitm2);
        in.put(programare, sitp2);
        cat.put(e2, in);
        clasa.addCatalog(cat);
       
        //centralizator
            //utilizatori
        ArrayList<Utilizator>users=new ArrayList();
        users.add(admin);
        users.add(e1);
        users.add(e2);
        users.add(pm);
        users.add(pr);
        users.add(pp);
        users.add(secretar);
        FileOutputStream f_out = new FileOutputStream("Utilizatori.data");
        ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
        obj_out.writeObject (users);
        
        ArrayList<Clasa> clase =new ArrayList();
        clase.add(clasa);
        f_out=new FileOutputStream("Clase.data");
        obj_out=new ObjectOutputStream(f_out);
        obj_out.writeObject(clase);
            //tree
        HashMap tree=new HashMap();
        HashMap clpr1=new HashMap();
        HashMap clpr2=new HashMap();
        HashMap clpr3=new HashMap();
        clpr1.put(clasa, pr);
        tree.put(romana, clpr1);
        clpr2.put(clasa, pm);
        tree.put(matematica, clpr2);
        clpr3.put(clasa, pp);
        tree.put(programare, clpr3);
        f_out=new FileOutputStream("DictionarCentralizator.data");
        obj_out=new ObjectOutputStream(f_out);
        obj_out.writeObject(tree);
        
        FereastraAutentificare f_autentificare=new FereastraAutentificare();
        while (true){ 
            System.out.println("");
            if (f_autentificare.getUserType()==null){
                f_autentificare.setVisible(true);
            }
        }
    }
}
