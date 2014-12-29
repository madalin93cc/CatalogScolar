/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafic;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import liceu.Centralizator;
import liceu.Clasa;
import liceu.Elev;
import liceu.Materie;
import liceu.Profesor;
import liceu.Secretar;
import liceu.Utilizator;

/**
 *
 * @author Madalin
 */
public class FereastraSecretar extends JFrame implements ActionListener{
    private JLabel username,rol,lusername,lrol,laddclasa,lmodifclasa,lmodifclasa2,
                    laddmatpr1,laddmatpr2,lmodmatpr1,lmodmatpr2,ldelmatpr,laddelevcl1,laddelevcl2;
    private JPanel utilizator,lob,south,paddclasa,pstergeclasa,pmodifclasa,paddmatpr1,paddmatpr2,
                    pmodmatpr1,pmodmatpr2,pdelmatpr,paddelevcl;
    private Choice caddmatpr1,caddmatpr2,cmodmatpr1,cmodmatpr2,cdelmatpr,caddelevcl1,caddelevcl2;
    private JButton logout,baddclasa,bstergeclasa,bmodifclasa,baddmatpr,bmodmatpr,bdelmatpr,baddelevcl;
    private JMenuBar menu;
    private JMenu file,adauga,sterge,modifica;
    private JMenuItem exit,mlogout,addclasa,addmatpr,stergeclasa,stergematpr,modifclasa,modifmatpr,addelevcl;
    private JTextField tfaddclasa,tfmodifclasa;
    private JList listaclasesterge,listaclasemodif;
    private Vector<Clasa> vectorclase;
    Secretar secr;
    public FereastraSecretar(String user) throws IOException, ClassNotFoundException{
        super("Catalog scolar");
        secr=this.creareSecretar(user);
        try { 
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() ); 
        } catch( Exception e ) {}
        this.setSize(new Dimension(640,420));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //user+rol
        username=new JLabel(user);
        rol=new JLabel("Secretar");
        lusername=new JLabel("Username:");
        lrol=new JLabel("Rol:");
        logout=new JButton("Logout");
        utilizator=new JPanel();
        lob=new JPanel();
        south=new JPanel();
        utilizator.setLayout(new GridLayout(2,1));
        utilizator.setBorder(new LineBorder(Color.GRAY));
        utilizator.add(lusername);
        utilizator.add(username);
        utilizator.add(lrol);
        utilizator.add(rol);
        lob.add(logout);
        south.setLayout(new FlowLayout());
        south.add(utilizator);
        south.add(lob);
        logout.addActionListener(this);
        this.add(south,BorderLayout.SOUTH);
        //menu bar
            //file menu
        menu=new JMenuBar();
        file=new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        mlogout=new JMenuItem("Logout",new ImageIcon("24x24-free-application-icons\\gif\\24x24\\Exit.gif"));
        mlogout.setMnemonic(KeyEvent.VK_L);
        mlogout.addActionListener(this);
        file.add(mlogout);
        file.addSeparator();
        exit=new JMenuItem("Exit",new ImageIcon("24x24-free-application-icons\\gif\\24x24\\Close.gif"));
        exit.setMnemonic(KeyEvent.VK_E);
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        file.add(exit);
        menu.add(file);
            //adauga menu
        adauga=new JMenu("Adauga");
        adauga.setMnemonic(KeyEvent.VK_A);
        addclasa=new JMenuItem("Adauga clasa",new ImageIcon("24x24-free-application-icons\\gif\\24x24\\People.gif"));
        addclasa.setMnemonic(KeyEvent.VK_C);
        addclasa.addActionListener(this);
        addmatpr=new JMenuItem("Adauga materie-profesor",new ImageIcon("24x24-free-application-icons\\gif\\24x24\\Briefcase.gif"));
        addmatpr.setMnemonic(KeyEvent.VK_M);
        addmatpr.addActionListener(this);
        addelevcl=new JMenuItem("Adauga elev in clasa",new ImageIcon("24x24-free-application-icons\\gif\\24x24\\User group.gif"));
        addelevcl.setMnemonic(KeyEvent.VK_E);
        addelevcl.addActionListener(this);
        adauga.add(addclasa);
        adauga.add(addmatpr);
        adauga.add(addelevcl);
        menu.add(adauga);
            //sterge menu
        sterge=new JMenu("Sterge");
        sterge.setMnemonic(KeyEvent.VK_S);
        stergeclasa=new JMenuItem("Sterge clasa",new ImageIcon("24x24-free-application-icons\\gif\\24x24\\Remove.gif"));
        stergeclasa.setMnemonic(KeyEvent.VK_C);
        stergeclasa.addActionListener(this);
        stergematpr=new JMenuItem("Sterge materie-profesor",new ImageIcon("24x24-free-application-icons\\gif\\24x24\\Terminate.gif"));
        stergematpr.setMnemonic(KeyEvent.VK_M);
        stergematpr.addActionListener(this);
        sterge.add(stergeclasa);
        sterge.add(stergematpr);
        menu.add(sterge);
            //modifica menu
        modifica=new JMenu("Modifica");
        modifica.setMnemonic(KeyEvent.VK_C);
        modifclasa=new JMenuItem("Modifica ID clasa",new ImageIcon("24x24-free-application-icons\\gif\\24x24\\Repair.gif"));
        modifclasa.setMnemonic(KeyEvent.VK_C);
        modifclasa.addActionListener(this);
        modifmatpr=new JMenuItem("Modifica materie-profesor",new ImageIcon("24x24-free-application-icons\\gif\\24x24\\Wrench.gif"));
        modifmatpr.setMnemonic(KeyEvent.VK_M);
        modifmatpr.addActionListener(this);
        modifica.add(modifclasa);
        modifica.add(modifmatpr);
        menu.add(modifica);        
        // addclasa
        tfaddclasa=new JTextField(20);
        paddclasa=new JPanel();
        baddclasa=new JButton("Adauga");
        baddclasa.addActionListener(this);
        laddclasa=new JLabel("ID clasa:");
        paddclasa.add(tfaddclasa);
        paddclasa.setLayout(new FlowLayout());
        paddclasa.add(laddclasa);
        paddclasa.add(tfaddclasa);
        paddclasa.add(baddclasa);
        //sterge clasa
        vectorclase=new Vector();
        listaclasesterge=new JList(vectorclase);
        bstergeclasa=new JButton("Sterge");
        bstergeclasa.addActionListener(this);
        pstergeclasa=new JPanel();
        pstergeclasa.setLayout(new FlowLayout());
        pstergeclasa.add(listaclasesterge);
        pstergeclasa.add(bstergeclasa);
        //modifica ID clasa
        tfmodifclasa=new JTextField(10);
        bmodifclasa=new JButton("Modifica");
        bmodifclasa.addActionListener(this);
        pmodifclasa=new JPanel();
        pmodifclasa.setLayout(new FlowLayout());
        lmodifclasa=new JLabel("Clasa:");
        lmodifclasa2=new JLabel("ID nou:");
        pmodifclasa.add(lmodifclasa);
        listaclasemodif=new JList(vectorclase);
        pmodifclasa.add(listaclasemodif);
        pmodifclasa.add(lmodifclasa2);
        pmodifclasa.add(tfmodifclasa);
        pmodifclasa.add(bmodifclasa);
        //add materie profesor
        paddmatpr1=new JPanel();
        paddmatpr1.setLayout(new GridLayout(3,2));
        paddmatpr2=new JPanel();
        paddmatpr2.setLayout(new FlowLayout());
        laddmatpr1=new JLabel("Profesor:");
        laddmatpr2=new JLabel("Materie:");
        caddmatpr1=new Choice();
        caddmatpr2=new Choice();
        baddmatpr=new JButton("Adauga materie");
        baddmatpr.addActionListener(this);
        paddmatpr1.add(laddmatpr1);
        paddmatpr1.add(caddmatpr1);
        paddmatpr1.add(laddmatpr2);
        paddmatpr1.add(caddmatpr2);
        paddmatpr2.add(paddmatpr1);
        paddmatpr2.add(baddmatpr);
        //mod materie profesor
        pmodmatpr1=new JPanel();
        pmodmatpr1.setLayout(new GridLayout(3,2));
        pmodmatpr2=new JPanel();
        pmodmatpr2.setLayout(new FlowLayout());
        lmodmatpr1=new JLabel("Profesor:");
        lmodmatpr2=new JLabel("Materie:");
        cmodmatpr1=new Choice();
        cmodmatpr2=new Choice();
        bmodmatpr=new JButton("Modifica materie");
        bmodmatpr.addActionListener(this);
        pmodmatpr1.add(lmodmatpr1);
        pmodmatpr1.add(cmodmatpr1);
        pmodmatpr1.add(lmodmatpr2);
        pmodmatpr1.add(cmodmatpr2);
        pmodmatpr2.add(pmodmatpr1);
        pmodmatpr2.add(bmodmatpr);
        //sterge materie profesor
        pdelmatpr=new JPanel();
        pdelmatpr.setLayout(new FlowLayout());
        ldelmatpr=new JLabel("Profesor:");
        cdelmatpr=new Choice();
        bdelmatpr=new JButton("Sterge materie");
        bdelmatpr.addActionListener(this);
        pdelmatpr.add(ldelmatpr);
        pdelmatpr.add(cdelmatpr);
        pdelmatpr.add(bdelmatpr);
        //adauga elev in clasa
        paddelevcl=new JPanel();
        paddelevcl.setLayout(new FlowLayout());
        laddelevcl1=new JLabel("Elev:");
        laddelevcl2=new JLabel("Clasa");
        caddelevcl1=new Choice();
        caddelevcl2=new Choice();
        baddelevcl=new JButton("Adauga elev");
        baddelevcl.addActionListener(this);
        paddelevcl.add(laddelevcl1);
        paddelevcl.add(caddelevcl1);
        paddelevcl.add(laddelevcl2);
        paddelevcl.add(caddelevcl2);
        paddelevcl.add(baddelevcl);
        
        this.setJMenuBar(menu);
        this.setVisible(true);
    }
    private Secretar creareSecretar(String user) throws IOException, ClassNotFoundException{
        Centralizator c=Centralizator.getInstance();
        ArrayList<Utilizator> users =c.getUsers();
        for (Iterator i=users.iterator();i.hasNext();){
            Utilizator u=(Utilizator)i.next();
            if (u instanceof Secretar){
                if (u.getNumeUtilizator().compareTo(user)==0){
                    return (Secretar)u;
                }
            }
        }
        return null;
    }
    public void actionPerformed(ActionEvent e) {
        if ((e.getActionCommand().compareTo("Logout"))==0){
            this.dispose();
            FereastraAutentificare.setLoggeduser();
            FereastraAutentificare.setUserType();
        }
        
        if ((e.getActionCommand().compareTo("Adauga clasa"))==0){
            this.remove(paddelevcl);
            this.remove(pdelmatpr);
            this.remove(pmodifclasa);
            this.remove(pstergeclasa);
            this.remove(paddmatpr2);
            this.add(paddclasa);
            this.revalidate();
            this.repaint();
        }
        
        if ((e.getActionCommand().compareTo("Adauga"))==0){
            Centralizator c=null;
            try {
                c=Centralizator.getInstance();
            } catch (IOException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            }
            ArrayList<Clasa> clase=c.getClase();
            String clasa=tfaddclasa.getText();
            boolean ok=false;
            for (Iterator i=clase.iterator();i.hasNext();){
                Clasa cl=(Clasa) i.next();
                if (cl.toString().compareToIgnoreCase(clasa+"\n")==0){
                    JOptionPane.showMessageDialog(this,"Clasa exista deja!");
                    ok=false;
                }
                else {
                    ok=true;
                }  
            }
            if (ok==true){
                try {
                    secr.addClasa(new Clasa(clasa));
                } catch (IOException ex) {
                    Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
                }
                ok=false;
                JOptionPane.showMessageDialog(this,"Clasa adaugata cu succes!");
            }
        }
        if ((e.getActionCommand().compareTo("Sterge clasa"))==0){
            Centralizator c=null;
            try {
                c=Centralizator.getInstance();
            } catch (IOException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            }
            ArrayList<Clasa> clase =c.getClase();
            vectorclase.clear();
            for (Iterator i=clase.iterator();i.hasNext();){
                Clasa cl=(Clasa)i.next();
                vectorclase.add(cl);
            }
            listaclasesterge.setListData(vectorclase);
            this.remove(paddelevcl);
            this.remove(pdelmatpr);
            this.remove(pmodmatpr2);
            this.remove(paddclasa);
            this.remove(pmodifclasa);
            this.remove(paddmatpr2);
            this.add(pstergeclasa,BorderLayout.WEST);
            this.revalidate();
            this.repaint();
        }
        if ((e.getActionCommand().compareTo("Sterge"))==0){
            Centralizator c=null;
            try {
                c=Centralizator.getInstance();
            } catch (IOException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (listaclasesterge.getSelectedValue()!=null){
                try {
                    Clasa cls=(Clasa)listaclasesterge.getSelectedValue();
                    secr.delClasa(cls);
                    ArrayList<Clasa> clase =c.getClase();
                    vectorclase.clear();
                    for (Iterator i=clase.iterator();i.hasNext();){
                       Clasa cl=(Clasa)i.next();
                    vectorclase.add(cl);
                    }
                    listaclasesterge.setListData(vectorclase);
                } catch (IOException ex) {
                    Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if ((e.getActionCommand().compareTo("Modifica ID clasa"))==0){
            Centralizator c=null;
            try {
                c=Centralizator.getInstance();
            } catch (IOException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            }
            ArrayList<Clasa> clase =c.getClase();
            vectorclase.clear();
            for (Iterator i=clase.iterator();i.hasNext();){
                Clasa cl=(Clasa)i.next();
                vectorclase.add(cl);
            }
            listaclasemodif.setListData(vectorclase);
            this.remove(paddelevcl);
            this.remove(pdelmatpr);
            this.remove(pmodmatpr2);
            this.remove(paddclasa);
            this.remove(pstergeclasa);
            this.remove(paddmatpr2);
            this.add(pmodifclasa,BorderLayout.NORTH);
            
            this.revalidate();
            this.repaint();
        }
        if ((e.getActionCommand().compareTo("Modifica"))==0){
            boolean ok=true;
            String clasa=tfmodifclasa.getText();
            Centralizator c=null;
            try {
                c=Centralizator.getInstance();
            } catch (IOException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            }
            ArrayList<Clasa> clase= c.getClase();
            for(Iterator i=clase.iterator();i.hasNext();){
                Clasa cl=(Clasa)i.next();
                if (cl.getIdClasa().compareToIgnoreCase(clasa)==0){
                    ok=false;
                    break;
                }
            }
            if (ok==false){
                JOptionPane.showMessageDialog(this,"ID-ul este deja folosit! ");
            }
            else {
                if (listaclasemodif.getSelectedValue()!=null){
                  try {
                    Clasa cls=(Clasa)listaclasemodif.getSelectedValue();
                    secr.modClasa(cls, tfmodifclasa.getText());
                    JOptionPane.showMessageDialog(this,"ID modificat cu succes! ");
                    clase =c.getClase();
                    vectorclase.clear();
                    for (Iterator i=clase.iterator();i.hasNext();){
                       Clasa cl=(Clasa)i.next();
                    vectorclase.add(cl);
                    }
                    listaclasemodif.setListData(vectorclase);
                    } catch (IOException ex) {
                        Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
                    }  
                }
            }
        }
        if ((e.getActionCommand().compareTo("Adauga materie-profesor"))==0){
            this.remove(paddelevcl);
            this.remove(pdelmatpr);
            this.remove(pmodmatpr2);
            this.remove(paddclasa);
            this.remove(pstergeclasa);
            this.remove(pmodifclasa);
            this.add(paddmatpr2,BorderLayout.NORTH);
            caddmatpr1.removeAll();
            caddmatpr2.removeAll();
            ArrayList<Profesor>profesori=secr.listProfesori();
            for(Iterator i=profesori.iterator();i.hasNext();){
                Profesor pr=(Profesor)i.next();
                caddmatpr1.add(pr.toString());
            }
            Set<Materie>materii=null;
            try {
                materii=secr.listMaterii();
            } catch (IOException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            }
            for(Iterator i=materii.iterator();i.hasNext();){
                Materie mat=(Materie)i.next();
                caddmatpr2.add(mat.toString());
            }
            
            this.revalidate();
            this.repaint();
        }
        if ((e.getActionCommand().compareTo("Adauga materie"))==0){
            String pr=caddmatpr1.getSelectedItem();
            String mat=caddmatpr2.getSelectedItem();
            Profesor prof=null;
            Materie materie=null;
            ArrayList<Profesor>profesori=secr.listProfesori();
            for (Iterator i=profesori.iterator();i.hasNext();){
                Profesor pro=(Profesor)i.next();
                if(pro.toString().compareTo(pr)==0){
                    prof=pro;
                }
            }
            Set<Materie> materii=null;
            try {
                materii=secr.listMaterii();
            } catch (IOException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (Iterator i=materii.iterator();i.hasNext();){
                Materie m=(Materie)i.next();
                if (m.toString().compareTo(mat)==0){
                    materie=m;
                }
            }
            try {
                secr.addMatProf(materie, prof);
            } catch (IOException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this,"Materie adaugata cu succes! ");
        }
        if ((e.getActionCommand().compareTo("Modifica materie-profesor"))==0){
            this.remove(paddelevcl);
            this.remove(pdelmatpr);
            this.remove(paddclasa);
            this.remove(pstergeclasa);
            this.remove(pmodifclasa);
            this.remove(paddmatpr2);
            this.add(pmodmatpr2,BorderLayout.NORTH);
            cmodmatpr1.removeAll();
            cmodmatpr2.removeAll();
            ArrayList<Profesor>profesori=secr.listProfesori();
            for(Iterator i=profesori.iterator();i.hasNext();){
                Profesor pr=(Profesor)i.next();
                cmodmatpr1.add(pr.toString());
            }
            Set<Materie>materii=null;
            try {
                materii=secr.listMaterii();
            } catch (IOException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            }
            for(Iterator i=materii.iterator();i.hasNext();){
                Materie mat=(Materie)i.next();
                cmodmatpr2.add(mat.toString());
            }
            
            this.revalidate();
            this.repaint();
        }
        if ((e.getActionCommand().compareTo("Modifica materie"))==0){
            String pr=cmodmatpr1.getSelectedItem();
            String mat=cmodmatpr2.getSelectedItem();
            Profesor prof=null;
            Materie materie=null;
            ArrayList<Profesor>profesori=secr.listProfesori();
            for (Iterator i=profesori.iterator();i.hasNext();){
                Profesor pro=(Profesor)i.next();
                if(pro.toString().compareTo(pr)==0){
                    prof=pro;
                }
            }
            Set<Materie> materii=null;
            try {
                materii=secr.listMaterii();
            } catch (IOException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (Iterator i=materii.iterator();i.hasNext();){
                Materie m=(Materie)i.next();
                if (m.toString().compareTo(mat)==0){
                    materie=m;
                }
            }
            try {
                secr.modMatProf(materie, prof);
            } catch (IOException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this,"Materie modificata cu succes! ");
        }
        if ((e.getActionCommand().compareTo("Sterge materie-profesor"))==0){
            this.remove(paddelevcl);
            this.remove(paddclasa);
            this.remove(pstergeclasa);
            this.remove(pmodifclasa);
            this.remove(paddmatpr2);
            this.remove(pmodmatpr2);
            this.add(pdelmatpr,BorderLayout.NORTH);
            cdelmatpr.removeAll();
            ArrayList<Profesor>profesori=secr.listProfesori();
            for(Iterator i=profesori.iterator();i.hasNext();){
                Profesor pr=(Profesor)i.next();
                cdelmatpr.add(pr.toString());
            }
  
            this.revalidate();
            this.repaint();
        }
        if ((e.getActionCommand().compareTo("Sterge materie"))==0){
            String pr=cdelmatpr.getSelectedItem();
            Profesor prof=null;
            ArrayList<Profesor>profesori=secr.listProfesori();
            for (Iterator i=profesori.iterator();i.hasNext();){
                Profesor pro=(Profesor)i.next();
                if(pro.toString().compareTo(pr)==0){
                    prof=pro;
                }
            }
            try {
                secr.delMatProf(prof);
            } catch (IOException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this,"Materie stearsa cu succes! ");
        }
        if ((e.getActionCommand().compareTo("Adauga elev in clasa"))==0){
            this.remove(paddclasa);
            this.remove(pstergeclasa);
            this.remove(pmodifclasa);
            this.remove(paddmatpr2);
            this.remove(pmodmatpr2);
            this.remove(pdelmatpr);
            this.add(paddelevcl,BorderLayout.NORTH);
            caddelevcl1.removeAll();
            caddelevcl2.removeAll();
            ArrayList<Elev>elevi=secr.listElevi();
            for(Iterator i=elevi.iterator();i.hasNext();){
                Elev pr=(Elev)i.next();
                caddelevcl1.add(pr.toString());
            }
            Centralizator c=null;
            try {
                c=Centralizator.getInstance();
            } catch (IOException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            }
            ArrayList<Clasa> clase=c.getClase();
            for(Iterator i=clase.iterator();i.hasNext();){
                Clasa pr=(Clasa)i.next();
                caddelevcl2.add(pr.toString());
            }
            
            this.revalidate();
            this.repaint();
        }
        if ((e.getActionCommand().compareTo("Adauga elev"))==0){
            String el=caddelevcl1.getSelectedItem();
            String cla=caddelevcl2.getSelectedItem();
            Elev elev=null;
            Clasa clasa=null;
            ArrayList<Elev> elevi=secr.listElevi();
            for(Iterator i=elevi.iterator();i.hasNext();){
                Elev e1=(Elev)i.next();
                if (e1.toString().compareTo(el)==0){
                    elev=e1;
                }
            }
            Centralizator c=null;
            try {
                c=Centralizator.getInstance();
            } catch (IOException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            }
            ArrayList<Clasa> clase=c.getClase();
            for(Iterator i=clase.iterator();i.hasNext();){
                Clasa cl=(Clasa)i.next();     
                if (cl.toString().compareTo(cla)==0){
                    clasa=cl;
                }
            }
            try {
                secr.addElevClasa(clasa, elev);
            } catch (IOException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FereastraSecretar.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this,"Elev adaugat cu succes! ");
        }
    }
}
