/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafic;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.border.LineBorder;
import liceu.Centralizator;
import liceu.Utilizator;
/**
 *
 * @author Madalin
 */
public class FereastraAutentificare extends JFrame implements ActionListener{
    private JTextField user;
    private JPasswordField password;
    private JButton login,cancel;
    private JLabel luser,lpassword;
    private JPanel jp,butoane;
    private static String usertype;
    private static Utilizator loggeduser;
    private JMenuBar menu;
    private JMenu file;
    private JMenuItem exit,mlogin;
    private JDialog logare;
    
    public FereastraAutentificare() throws IOException{
        super("Catalog scolar");
        usertype=null;
        loggeduser=null;
        try { 
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() ); 
        } catch( Exception e ) {}
        this.setLayout(new FlowLayout());
        
        
        jp = new javax.swing.JPanel();
        luser = new javax.swing.JLabel();
        lpassword = new javax.swing.JLabel();
        user = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        login = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        luser.setText("Username:");

        lpassword.setText("Parola:");

        login.setText("Login");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jp);
        jp.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(luser)
                    .addComponent(lpassword))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(user, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(password))
                .addGap(18, 18, 18)
                .addComponent(login)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(luser)
                            .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lpassword)
                            .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(login)))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(jp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(158, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );
        pack();
        
        jp.setVisible(false);
        login.addActionListener(this);
        //menu bar
        //file menu
        menu=new JMenuBar();
        file=new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        mlogin=new JMenuItem("Login",new ImageIcon("24x24-free-application-icons\\gif\\24x24\\Home.gif"));
        mlogin.setMnemonic(KeyEvent.VK_L);
        mlogin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jp.setVisible(true);
                login.setVisible(true);
            }
        });
        file.add(mlogin);
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
        this.setJMenuBar(menu);
        this.setVisible(true);
    }
    public static Utilizator getLoggeduser(){
        return loggeduser;
    }
    public static void setLoggeduser(){
        loggeduser=null;
    }
    public static void setUserType(){
        usertype=null;
    }
    public String getUser(){
        return user.getText();
    }
    public String getPassword(){
        return new String(password.getPassword());
    }
    public String getUserType(){
        return this.usertype;
    }
    public String autentificare(String user,String pass) throws IOException, ClassNotFoundException{
        Centralizator c=Centralizator.getInstance();
        ArrayList<Utilizator> utilizatori =c.getUsers();
        for (Iterator i=utilizatori.iterator();i.hasNext();){
            Utilizator u=(Utilizator)i.next();
            if ((u.getNumeUtilizator().compareTo(user)==0)&&(u.getParola().compareTo(pass)==0)){
                this.loggeduser=u;
                this.usertype=u.getType().toLowerCase();
            }
        }
        return this.usertype;
    }
    public void actionPerformed(ActionEvent e) {
        try {
            if (autentificare(this.getUser(),this.getPassword())!=null){
                int optiune=JOptionPane.showConfirmDialog(this, "Autentificare reusita!\nDoriti sa continuati?");
                if (optiune==JOptionPane.YES_OPTION){
                    this.usertype=autentificare(this.getUser(),this.getPassword());
                    this.setVisible(false);
                    switch (this.getUserType()){
                        case "administrator":{
                            FereastraAdministrator f_admin=new FereastraAdministrator(this.getUser());
                            break;
                        }
                        case "profesor":{
                            FereastraProfesor f_prof=new FereastraProfesor(this.getUser());
                            break;
                        }
                        case "secretar":{
                            FereastraSecretar f_secretar=new FereastraSecretar(this.getUser());
                            break;
                        }
                        case "elev":{
                            FereastraElev f_elev=new FereastraElev(this.getUser());
                            break;
                        }
                        default:{
                            System.out.println("Imposibil");
                        }
                    }
                }
                else {
                    this.loggeduser=null;
                    this.usertype=null;
                }
            }
            else {
                JOptionPane.showMessageDialog(this,"Username sau parola gresit!\nIncercati din nou!");
            }
        } catch (IOException ex) {
            Logger.getLogger(FereastraAutentificare.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FereastraAutentificare.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
