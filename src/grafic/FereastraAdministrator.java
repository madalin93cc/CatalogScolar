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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
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
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import liceu.Administrator;
import liceu.Centralizator;
import liceu.Data;
import liceu.Elev;
import liceu.Profesor;
import liceu.Secretar;
import liceu.UserType;
import liceu.Utilizator;

/**
 *
 * @author Madalin
 */
public class FereastraAdministrator extends JFrame implements ActionListener {
    private Choice tip,zi,luna,an;
    private JLabel username,rol,lusername,lrol;
    private JPanel utilizator,lob,south,ucreate,ucreate2,data_nasterii,ptip,plista_u;
    private JButton logout,creare_user,delete_user;
    private JMenuBar menu;
    private JMenu file,menu_u;
    private JMenuItem exit,mlogout,creare_u,listare_u;
    private JLabel lunume,lupren,luuser,lupass,lucnp,ludatan;
    private JTextField tunume,tupren,tuuser,tupass,tucnp,tudatan;
    private String userdorit;
    private Administrator adm;
    private Vector<String> dlm_util;
    private JList lista_u;
    public FereastraAdministrator(String user) throws IOException, ClassNotFoundException{
        super("Catalog scolar");
        this.adm = new Administrator("","","","");
        try { 
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() ); 
        } catch( Exception e ) {}
        this.setSize(new Dimension(640,420));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        userdorit="";
        //username+rol
        username=new JLabel(user);
        rol=new JLabel("Administrator");
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
        //menu utilizator
        menu_u=new JMenu("Utilizator");
        menu_u.setMnemonic(KeyEvent.VK_U);
        creare_u=new JMenuItem("Creare",new ImageIcon("24x24-free-application-icons\\gif\\24x24\\Create.gif"));
        creare_u.setMnemonic(KeyEvent.VK_C);
        creare_u.addActionListener(this);
        menu_u.add(creare_u);
        listare_u=new JMenuItem("Listare",new ImageIcon("24x24-free-application-icons\\gif\\24x24\\List.gif"));
        listare_u.setMnemonic(KeyEvent.VK_L);
        listare_u.addActionListener(this);
        menu_u.add(listare_u);
        menu.add(menu_u);
        this.setJMenuBar(menu);
        //creare utilizator
        tip=new Choice();
        ptip=new JPanel();
        lunume=new JLabel("Nume:");
        lupren=new JLabel("Prenume:");
        luuser=new JLabel("Username:");
        lupass=new JLabel("Parola:");
        ucreate=new JPanel();
        ucreate.setLayout(new GridLayout(7,1));
        tunume=new JTextField(20);
        tupren=new JTextField(20);
        tuuser=new JTextField(20);
        tupass=new JTextField(20);
        lucnp=new JLabel("CNP:");
        tucnp=new JTextField(20);
        ludatan=new JLabel("Data nasterii:");
        zi=new Choice();
        for(Integer i=1;i<=31;i++){
            zi.add(i.toString());
        }
        luna=new Choice();
        String s[]={"Ian","Feb","Mar","Apr","Mai","Iun","Iul","Aug","Sep","Oct","Noi","Dec",};
        for (int i=0;i<s.length;i++){
            luna.add(s[i]);
        }
        an=new Choice();
        for (Integer i=2014;i>=1900;i--){
            an.add(i.toString());
        }
        data_nasterii=new JPanel();
        data_nasterii.setLayout(new FlowLayout());
        data_nasterii.add(zi);
        data_nasterii.add(luna);
        data_nasterii.add(an);
        creare_user=new JButton("Creaza");
        creare_user.addActionListener(this);
        ucreate2=new JPanel();
        //lista utilizatori
        dlm_util=new Vector<>();
        plista_u=new JPanel();
        plista_u.setLayout(new FlowLayout());
        lista_u=new JList(dlm_util);
        lista_u.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        delete_user=new JButton("Sterge");
        delete_user.addActionListener(this);
    }
    private void creareUtilizator(int tip){
        ucreate.removeAll();
        ucreate2.removeAll();
        tunume.setText("");
        tupren.setText("");
        tupass.setText("");
        tuuser.setText("");
        this.add(ucreate);
        this.add(ucreate2,BorderLayout.EAST);
        this.revalidate();
        this.repaint();
        if (tip==0){
            ucreate.add(lunume);
            ucreate.add(tunume);
            ucreate.add(lupren);
            ucreate.add(tupren);
            ucreate.add(luuser);
            ucreate.add(tuuser);
            ucreate.add(lupass);
            ucreate.add(tupass);
            ucreate.add(lucnp);
            ucreate.add(tucnp);
            ucreate.add(ludatan);
            ucreate.add(data_nasterii);
            ucreate2.add(creare_user);
        }
        else {
            ucreate.add(lunume);
            ucreate.add(tunume);
            ucreate.add(lupren);
            ucreate.add(tupren);
            ucreate.add(luuser);
            ucreate.add(tuuser);
            ucreate.add(lupass);
            ucreate.add(tupass);
            ucreate2.add(creare_user);
        }
        this.add(ucreate,BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
    public void actionPerformed(ActionEvent e) {
        if ((e.getActionCommand().compareTo("Logout"))==0){
            this.dispose();
            FereastraAutentificare.setLoggeduser();
            FereastraAutentificare.setUserType();
        }
        if ((e.getActionCommand().compareTo("Creare"))==0){
            this.remove(plista_u);
            this.revalidate();
            this.repaint();
            tip.removeAll();
            tip.add("Elev");
            tip.add("Profesor");
            tip.add("Secretar");
            tip.add("Administrator");
            tip.select(0);
            ptip.setLayout(new FlowLayout());
            ptip.add(tip);
            this.add(ptip,BorderLayout.PAGE_START);
            tip.addItemListener(new ItemListener(){
                public void itemStateChanged(ItemEvent e){
                    int selectie=tip.getSelectedIndex();
                    userdorit=tip.getItem(selectie);
                    creareUtilizator(selectie);
                }
            }); 
        }
        if ((e.getActionCommand().compareTo("Creaza"))==0){
            String nume=this.tunume.getText();
            String prenume=this.tupren.getText();
            String user=this.tuuser.getText();
            String pass=this.tupass.getText();
            String cnp="";
            Data datan=null;
            if (this.userdorit.compareTo("Elev")==0){
                cnp=this.tucnp.getText();
                datan=new Data(this.an.getSelectedItem(),this.luna.getSelectedIndex()+1,this.zi.getSelectedItem());
                Elev el=(Elev)Administrator.CreateUser(UserType.ELEV, user, pass, nume, prenume, cnp, datan, null);
                adm.addUser(el);
                JOptionPane.showMessageDialog(this,"Utilizator adaugat cu succes!");
            } else if(this.userdorit.compareTo("Profesor")==0){
                Profesor pr=(Profesor)Administrator.CreateUser(UserType.PROFESOR, user, pass, nume, prenume, null, null, null);
                adm.addUser(pr);
                JOptionPane.showMessageDialog(this,"Utilizator adaugat cu succes!");
            } else if(this.userdorit.compareTo("Secretar")==0){
                Secretar se=(Secretar)Administrator.CreateUser(UserType.SECRETAR, user, pass, nume, prenume, null, null, null);
                adm.addUser(se);
                JOptionPane.showMessageDialog(this,"Utilizator adaugat cu succes!");
            } else if(this.userdorit.compareTo("Administrator")==0){
                Administrator ad=(Administrator)Administrator.CreateUser(UserType.ADMINISTRATOR, user, pass, nume, prenume, null, null, null);
                adm.addUser(ad);
                JOptionPane.showMessageDialog(this,"Utilizator adaugat cu succes!");
            } 
        }
        if ((e.getActionCommand().compareTo("Listare"))==0){
            this.remove(ucreate);
            this.remove(ucreate2);
            this.remove(ptip);
            this.remove(plista_u);
            ArrayList<Utilizator>users=adm.listUsers();
            for(Iterator i=users.iterator();i.hasNext();){
                Utilizator u=(Utilizator)i.next();
                if (!dlm_util.contains(u.getNume()+" "+u.getPrenume())){
                        dlm_util.add(u.getNume()+" "+u.getPrenume());
                }
            }
            lista_u.setListData(dlm_util);
            plista_u.removeAll();
            plista_u.add(new JScrollPane(lista_u));
            plista_u.add(delete_user,BorderLayout.CENTER);
            this.add(plista_u);
            this.revalidate();
            this.repaint();
        }
        if ((e.getActionCommand().compareTo("Sterge"))==0){
            ArrayList<Utilizator>users=adm.listUsers();
            Utilizator ut=users.get(lista_u.getSelectedIndex());
            adm.delUser(users.get(lista_u.getSelectedIndex()));
            JOptionPane.showMessageDialog(this,"Utilizator sters cu succes!");
            users=adm.listUsers();
            dlm_util.clear();
            for(Iterator i=users.iterator();i.hasNext();){
                Utilizator u=(Utilizator)i.next();
                if (!dlm_util.contains(u.getNume()+" "+u.getPrenume())){
                        dlm_util.add(u.getNume()+" "+u.getPrenume());
                }
            }
            lista_u.setListData(dlm_util);
            this.revalidate();
            this.repaint();
        }
    }
    
}
