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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import liceu.Centralizator;
import liceu.Clasa;
import liceu.Elev;
import liceu.Materie;
import liceu.Profesor;
import liceu.SituatieMaterieBaza.Absenta;
import liceu.Utilizator;

/**
 *
 * @author Madalin
 */
public class FereastraProfesor extends JFrame implements ActionListener,ItemListener{
    private JLabel username,rol,lusername,lrol,lnotan1,lnotan2;
    private JPanel utilizator,lob,south,vest,pclasa,pinfo,pbutoanen,pbutoanea,notan;
    private JButton logout,stergen,addn,modifn,modifa,adda;
    private JMenuBar menu;
    private JMenu file,afiseaza;
    private JMenuItem exit,mlogout,aflista,afinf;
    private Choice clasa,prclasa,elev,optiune,ordonare;
    private JTable tabinfo;
    private JScrollPane sp,spinfo;
    private JList lelevi,listna;
    private Vector<String> elevi,arna;
    private Profesor prof;
    private JTextArea info;
    private ArrayList<Elev> ar_elevi;
    private String s;
    private ArrayList<Clasa>clase;
    private Clasa clasapr;
    private Elev elevpr;
    private Object[][] abs_note;
    private Materie materie;
    private String[] header1={"Semestru","Nota"},header2={"Data","Status"};
    private int col,row;
    private JTextField notan1,notan2;
    
    public FereastraProfesor(String user) throws IOException, ClassNotFoundException{
        super("Catalog scolar");
        prof=creareProfesor(user);
        materie=this.getMaterie();
        try { 
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() ); 
        } catch( Exception e ) {}
        this.setSize(new Dimension(640,420));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //user+rol
        username=new JLabel(user);
        rol=new JLabel("Profesor");
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
        menu=new JMenuBar();
        this.setJMenuBar(menu);
        this.setVisible(true);
            //file menu
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
            //afiseaza menu 
        afiseaza=new JMenu("Afiseaza");
        afiseaza.setMnemonic(KeyEvent.VK_A);
        aflista=new JMenuItem("Elevii unei clase",new ImageIcon("24x24-free-application-icons\\gif\\24x24\\List.gif"));
        aflista.setMnemonic(KeyEvent.VK_L);
        aflista.addActionListener(this);
        afiseaza.add(aflista);
        afinf=new JMenuItem("Informatii elev",new ImageIcon("24x24-free-application-icons\\gif\\24x24\\Info.gif"));
        afinf.setMnemonic(KeyEvent.VK_I);
        afinf.addActionListener(this);
        afiseaza.add(afinf);
        menu.add(afiseaza);
        //afiseaza lista elevi clasa
        vest=new JPanel();
        vest.setLayout(new GridLayout());
        clasa=new Choice();
        elevi=new Vector();
        lelevi=new JList(elevi);
        pclasa=new JPanel();
        pclasa.setLayout(new FlowLayout());
        info=new JTextArea();
        info.setEditable(false);
        sp=new JScrollPane(info);
        ordonare=new Choice();
        ordonare.add("Alfabetic");
        ordonare.add("Dupa absente");
        sp=new JScrollPane(info);
        //afiseaza info
        prclasa=new Choice();
        elev=new Choice();
        optiune=new Choice();
        pinfo=new JPanel();
        pinfo.setLayout(new GridLayout(0,2));
        arna=new Vector();
        listna=new JList(arna);
        pbutoanen=new JPanel();
        pbutoanen.setLayout(new FlowLayout());
        pbutoanea=new JPanel();
        pbutoanea.setLayout(new FlowLayout());
        stergen=new JButton("Sterge nota");
        stergen.addActionListener(this);
        addn=new JButton("Adauga nota");
        addn.addActionListener(this);
        modifn=new JButton("Modifica nota");
        modifn.addActionListener(this);
        modifa=new JButton("Modifica absenta");
        modifa.addActionListener(this);
        adda=new JButton("Adauga absenta");
        adda.addActionListener(this);
        pbutoanen.add(stergen);
        pbutoanen.add(addn);
        pbutoanen.add(modifn);
        pbutoanea.add(adda);
        pbutoanea.add(modifa);
        optiune.add("Note");
        optiune.add("Absente");
        tabinfo=new JTable();
        spinfo=new JScrollPane();
        // nota noua
        notan1=new JTextField(5);
        notan2=new JTextField(5);
        lnotan1=new JLabel("Semestru:");
        lnotan2=new JLabel("Nota:");
        notan=new JPanel();
        notan.add(lnotan1);
        notan.add(notan1);
        notan.add(lnotan1);
        notan.add(notan1);
        
    }
    private Profesor creareProfesor(String user) throws IOException, ClassNotFoundException{
        Centralizator c=Centralizator.getInstance();
        ArrayList<Utilizator> users =c.getUsers();
        for (Iterator i=users.iterator();i.hasNext();){
            Utilizator u=(Utilizator)i.next();
            if (u instanceof Profesor){
                if (u.getNumeUtilizator().compareTo(user)==0){
                    return (Profesor)u;
                }
            }
        }
        return null;
    }
    private void eleviiUneiClase(String clasa,int opt){
        ar_elevi=prof.listEleviClasaOrd(clasa,opt);
        elevi.clear();
        for(Iterator i=ar_elevi.iterator();i.hasNext();){
            Elev el=(Elev)i.next();
            elevi.add(el.toString());
        }
        lelevi.removeAll();
        lelevi.setListData(elevi);
        lelevi.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent lse) {
                if (lelevi.getSelectedIndex()!=-1){
                Elev e=ar_elevi.get(lelevi.getSelectedIndex());
                s="";
                try {
                    s=e.getNume()+" "+e.getPrenume()+"\n"+e.getNote()+e.getMedii()+e.getAbsente();
                } catch (IOException ex) {
                    Logger.getLogger(FereastraProfesor.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FereastraProfesor.class.getName()).log(Level.SEVERE, null, ex);
                }
                info.setText(s);
                }
            }
        });
        this.remove(pclasa);
        vest.removeAll();
        vest.add(pclasa);
        vest.add(new JScrollPane(lelevi));
        this.add(vest,BorderLayout.WEST);
        this.add(sp,BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
    public void actionPerformed(ActionEvent e) {
        if ((e.getActionCommand().compareTo("Logout"))==0){
            this.dispose();
            FereastraAutentificare.setLoggeduser();
            FereastraAutentificare.setUserType();
        }
        if ((e.getActionCommand().compareTo("Elevii unei clase"))==0){
            this.remove(pinfo);
            this.remove(vest);
            this.remove(pclasa);
            this.remove(sp);
            this.revalidate();
            this.repaint();
            clasa.removeAll();
            Centralizator c=null;
            try {
                c = Centralizator.getInstance();
            } catch (IOException ex) {
                Logger.getLogger(FereastraProfesor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FereastraProfesor.class.getName()).log(Level.SEVERE, null, ex);
            }
            ArrayList<Clasa> clase=c.getClase();
            for (Iterator i=clase.iterator();i.hasNext();){
                Clasa cl=(Clasa)i.next();
                clasa.add(cl.toString());
            }
            pclasa.add(clasa);
            pclasa.add(ordonare);
            this.add(pclasa,BorderLayout.WEST);
            clasa.addItemListener(new ItemListener(){
                public void itemStateChanged(ItemEvent e){
                    eleviiUneiClase(clasa.getSelectedItem(),ordonare.getSelectedIndex());
                }
            });
            ordonare.addItemListener(new ItemListener(){
                public void itemStateChanged(ItemEvent e){
                    eleviiUneiClase(clasa.getSelectedItem(),ordonare.getSelectedIndex());
                }
            });
        }
        if ((e.getActionCommand().compareTo("Informatii elev"))==0){
            this.remove(pinfo);
            this.remove(vest);
            this.remove(pclasa);
            this.remove(sp);
            pinfo.removeAll();
            this.revalidate();
            this.repaint();
            Centralizator c=null;
            try {
                c=Centralizator.getInstance();
            } catch (IOException ex) {
                Logger.getLogger(FereastraProfesor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FereastraProfesor.class.getName()).log(Level.SEVERE, null, ex);
            }
            prclasa.removeAll();
            clase=c.getClase();
            for(Iterator i=clase.iterator();i.hasNext();){
                Clasa cl=(Clasa) i.next();
                prclasa.add(cl.toString());
            }
            prclasa.addItemListener(new ItemListener(){
                public void itemStateChanged(ItemEvent e){
                    String s=prclasa.getSelectedItem();
                    for (Iterator i=clase.iterator();i.hasNext();){
                        Clasa cl=(Clasa)i.next();
                        if (cl.toString().compareTo(s)==0){
                            clasapr=cl;
                            ar_elevi=cl.getElevi();
                            Collections.sort(ar_elevi,new Comparator (){
                                public int compare(Object o1,Object o2){
                                    Elev e1=(Elev)o1;
                                    Elev e2=(Elev)o2;
                                    return e1.getNume().compareTo(e2.getNume());
                                }
                            });
                            elev.removeAll();
                            for (Iterator j=ar_elevi.iterator();j.hasNext();){
                                Elev el=(Elev)j.next();
                                elev.add(el.toString());
                            }
                        }
                    }
                }
            });
            elev.addItemListener(new ItemListener(){
                public void itemStateChanged(ItemEvent e){
                    String s=elev.getSelectedItem();
                    for (Iterator i=ar_elevi.iterator();i.hasNext();){
                        Elev el=(Elev)i.next();
                        String nume=el.getNume()+" "+el.getPrenume()+"\n";
                        if (nume.compareTo(s)==0){
                            elevpr=el;
                        }
                    }
                }
            });
            optiune.addItemListener(this);
            pinfo.add(new JLabel("Clasa:"));
            pinfo.add(prclasa);
            pinfo.add(new JLabel("Elev:"));
            pinfo.add(elev);
            pinfo.add(new JLabel("Optiune:"));
            pinfo.add(optiune);
            this.add(pinfo,BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        }
        if ((e.getActionCommand().compareTo("Adauga nota"))==0){
            DefaultTableModel tablemodel=new DefaultTableModel(abs_note,header1){
                public boolean isCellEditable(int row,int col){
                    return true;
                }
            };
            tabinfo.setModel(tablemodel);
            this.revalidate();
            this.repaint();
        }
        if ((e.getActionCommand().compareTo("Sterge nota"))==0){
            
        }
        if ((e.getActionCommand().compareTo("Modifica nota"))==0){
            DefaultTableModel dem=(DefaultTableModel)tabinfo.getModel();
            if (dem.isCellEditable(1, 1)){
                DefaultTableModel tablemodel=new DefaultTableModel(abs_note,header1){
                public boolean isCellEditable(int row,int col){
                    return false;
                }
            };
            tabinfo.setModel(tablemodel);
            }
            else {
            DefaultTableModel tablemodel=new DefaultTableModel(abs_note,header1){
                public boolean isCellEditable(int row,int col){
                    return col==1;
                }
            };
            tabinfo.setModel(tablemodel);
            }
        }
        if ((e.getActionCommand().compareTo("Modifica absenta"))==0){
            DefaultTableModel dem=(DefaultTableModel)tabinfo.getModel();
            if (dem.isCellEditable(1, 1)){
                DefaultTableModel tablemodel=new DefaultTableModel(abs_note,header1){
                public boolean isCellEditable(int row,int col){
                    return false;
                }
            };
            tabinfo.setModel(tablemodel);
            }
            else {
            DefaultTableModel tablemodel=new DefaultTableModel(abs_note,header1){
                public boolean isCellEditable(int row,int col){
                    return col==1;
                }
            };
            tabinfo.setModel(tablemodel);
            }
        }
        if ((e.getActionCommand().compareTo("Modifica absenta"))==0){
            
        }
    }
    private Materie getMaterie() throws IOException, ClassNotFoundException{
        Centralizator c=Centralizator.getInstance();
        HashMap map=c.getDict();
        Set<Materie> materii=(Set)map.keySet();
        for(Iterator i=materii.iterator();i.hasNext();){
            Materie m=(Materie)i.next();
            HashMap<Clasa,Profesor> dict=(HashMap)map.get(m);
            Profesor p;
            Collection<Profesor> profesori=dict.values();
            for (Iterator j=profesori.iterator();j.hasNext();){
                p=(Profesor)j.next();
                if (p.equals(this.prof)){
                   return m;
                }
            }
        }
        return null;
    }
    public void itemStateChanged(ItemEvent ie) {
        if (optiune.getSelectedItem().compareTo("Note")==0){
            pinfo.remove(pbutoanea);
            pinfo.add(pbutoanen);
            this.add(pinfo,BorderLayout.CENTER);
            ArrayList<Integer> note1 =elevpr.getNote1Array(clasapr, materie);
            Integer teza1=null;
            int dim;
            if (materie.getTeza()){
                teza1=elevpr.getTeza1(clasapr, materie);
            }
            ArrayList<Integer> note2 =elevpr.getNote2Array(clasapr, materie);
            Integer teza2=null;
            if (materie.getTeza()){
                teza2=elevpr.getTeza2(clasapr, materie);
            }
            dim=note1.size()+note2.size()+2;
            abs_note=new Object[dim][2];
            for(int i=0;i<note1.size();i++){
                abs_note[i][0]=1;
                abs_note[i][1]=note1.get(i);
            }
            abs_note[note1.size()][0]="Teza 1";
            abs_note[note1.size()][1]=teza1;
            
            for(int i=0;i<note2.size();i++){
                abs_note[i+note1.size()][0]=1;
                abs_note[i+note1.size()][1]=note2.get(i);
            }
            abs_note[note1.size()+note2.size()+1][0]="Teza 2";
            abs_note[note1.size()+note2.size()+1][1]=teza2;
            DefaultTableModel tablemodel=new DefaultTableModel(abs_note,header1){
                public boolean isCellEditable(int row,int col){
                    return false;
                }
            };
            tabinfo.setModel(tablemodel);
            tabinfo.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt){
                    col = tabinfo.getSelectedColumn();
                    row = tabinfo.getSelectedRow();
                    }
            });
            tabinfo.setRowSelectionAllowed(true);
            tabinfo.setCellSelectionEnabled(true);
            tabinfo.setColumnSelectionAllowed(true);
            pinfo.remove(spinfo);
            spinfo=new JScrollPane(tabinfo);
            pinfo.add(spinfo);
            this.revalidate();
            this.repaint();
        }
        if (optiune.getSelectedItem().compareTo("Absente")==0){
            pinfo.remove(pbutoanen);
            pinfo.add(pbutoanea);
            this.add(pinfo,BorderLayout.CENTER);
            ArrayList<Absenta>absente=elevpr.getAbsente(clasapr, materie);
            abs_note=new Object[absente.size()][2];
            int j=0;
            for (Iterator i=absente.iterator();i.hasNext();){
                Absenta abs=(Absenta)i.next();
                abs_note[j][0]=abs.getDate();
                abs_note[j][1]=abs.getStatus();
                j++;
            }
            DefaultTableModel tablemodel=new DefaultTableModel(abs_note,header2){
                public boolean isCellEditable(int row,int col){
                    return false;
                }
            };
            tabinfo.setModel(tablemodel);
            tabinfo.setRowSelectionAllowed(true);
            tabinfo.setCellSelectionEnabled(true);
            tabinfo.setColumnSelectionAllowed(true);
            pinfo.remove(spinfo);
            spinfo=new JScrollPane(tabinfo);
            pinfo.add(spinfo);
            
            this.revalidate();
            this.repaint();
        }
    }
}
