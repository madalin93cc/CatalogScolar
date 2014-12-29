/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package liceu;

import java.io.IOException;

/**
 *
 * @author Madalin
 */
public interface IElev {
    String getDatePersonale();
    String getAbsente() throws IOException, ClassNotFoundException;
    String getNote() throws IOException, ClassNotFoundException;
    String getMedii() throws IOException, ClassNotFoundException;
    }
