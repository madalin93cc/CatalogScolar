/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import liceu.Elev;

/**
 *
 * @author Madalin
 */
public class FereastraElev extends JFrame implements ActionListener{
    private JTextArea date_p,tfnote,tfabsente,tfmedii;
    private JLabel username,rol,lusername,lrol;
    private JPanel utilizator,lob,south;
    private JButton logout;
    private JMenuBar menu;
    private JMenu file,show,situatie;
    private JMenuItem exit,mlogout,date,note,abs,medii;
    private JScrollPane jsp;
    public FereastraElev(String user) throws IOException, ClassNotFoundException{
        super("Catalog scolar");
        try { 
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() ); 
        } catch( Exception e ) {}
        this.setSize(new Dimension(640,420));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //user+rol
        username=new JLabel(user);
        rol=new JLabel("Elev");
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
        this.setVisible(true);
        
        //menu bar
        menu=new JMenuBar();
        this.setJMenuBar(menu);
   
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
        
        //show menu
        show=new JMenu("Afiseaza");
        show.setMnemonic(KeyEvent.VK_A);
        situatie=new JMenu("Situatia Scolara");
        situatie.setMnemonic(KeyEvent.VK_S);
        date=new JMenuItem("Date personale");
        date.setMnemonic(KeyEvent.VK_D);
        date.addActionListener(this);
        note=new JMenuItem("Note");
        note.setMnemonic(KeyEvent.VK_N);
        note.addActionListener(this);
        abs=new JMenuItem("Absente");
        abs.setMnemonic(KeyEvent.VK_A);
        abs.addActionListener(this);
        medii=new JMenuItem("Medii");
        medii.setMnemonic(KeyEvent.VK_M);
        medii.addActionListener(this);
        date_p=new JTextArea();
        tfnote=new JTextArea();
        tfabsente=new JTextArea();
        tfmedii=new JTextArea();
        situatie.add(date);
        situatie.add(note);
        situatie.add(medii);
        situatie.add(abs);
        show.add(situatie);
        menu.add(show);
        jsp=new JScrollPane();
    }
    private void datePersonale(){
        Elev el=(Elev)FereastraAutentificare.getLoggeduser();
        date_p.setText(el.getDatePersonale());
        date_p.setEditable(false);
    }
    private void note() throws IOException, ClassNotFoundException{
        Elev el=(Elev)FereastraAutentificare.getLoggeduser();
        tfnote.setText(el.getNote());
        tfnote.setEditable(false);
    }
    private void absente() throws IOException, ClassNotFoundException{
        Elev el=(Elev)FereastraAutentificare.getLoggeduser();
        tfabsente.setText(el.getAbsente());
        tfabsente.setEditable(false);
    }
    private void medii() throws IOException, ClassNotFoundException{
        Elev el=(Elev)FereastraAutentificare.getLoggeduser();
        tfmedii.setText(el.getMedii());
        tfmedii.setEditable(false);
    }
    public void actionPerformed(ActionEvent e) {
        if ((e.getActionCommand().compareTo("Logout"))==0){
            this.dispose();
            FereastraAutentificare.setLoggeduser();
            FereastraAutentificare.setUserType();
        }
        if (e.getActionCommand().compareTo("Date personale")==0){
            this.remove(tfnote);
            this.remove(tfmedii);
            this.remove(tfabsente);
            this.remove(jsp);
            this.revalidate();
            this.repaint();
            this.datePersonale();
            jsp=new JScrollPane(date_p);
            this.add(jsp,BorderLayout.NORTH);
            this.setVisible(true);
        }
        if (e.getActionCommand().compareTo("Note")==0){
            this.remove(date_p);
            this.remove(tfmedii);
            this.remove(tfabsente);
            this.remove(jsp);
            this.revalidate();
            this.repaint();
            try {
                this.note();
            } catch (IOException ex) {
                Logger.getLogger(FereastraElev.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FereastraElev.class.getName()).log(Level.SEVERE, null, ex);
            }
            jsp=new JScrollPane(tfnote);
            this.add(jsp,BorderLayout.NORTH);
            this.setVisible(true);
            
        }
        if (e.getActionCommand().compareTo("Medii")==0){
            this.remove(date_p);
            this.remove(tfnote);
            this.remove(tfabsente);
            this.remove(jsp);
            this.revalidate();
            this.repaint();
            try {
                this.medii();
            } catch (IOException ex) {
                Logger.getLogger(FereastraElev.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FereastraElev.class.getName()).log(Level.SEVERE, null, ex);
            }
            jsp=new JScrollPane(tfmedii);
            this.add(jsp,BorderLayout.NORTH);
            this.setVisible(true);
        }
        if (e.getActionCommand().compareTo("Absente")==0){
            this.remove(date_p);
            this.remove(tfmedii);
            this.remove(tfnote);
            this.remove(jsp);
            this.revalidate();
            this.repaint();
            try {
                this.absente();
            } catch (IOException ex) {
                Logger.getLogger(FereastraElev.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FereastraElev.class.getName()).log(Level.SEVERE, null, ex);
            }
            jsp=new JScrollPane(tfabsente);
            this.add(jsp,BorderLayout.NORTH);
            this.setVisible(true);
        }
    }
}
