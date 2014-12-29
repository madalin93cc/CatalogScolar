    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package liceu;

import java.util.ArrayList;

/**
 *
 * @author Madalin
 */
public interface IAdministrator extends IProfesor,ISecretar{
    void addUser(Utilizator o);
    void delUser(Utilizator o);
    ArrayList<Utilizator> listUsers();
}
