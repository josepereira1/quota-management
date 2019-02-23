/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import business.SGQ;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author MIEIGroup
 */
public class SGQData {
    
    public static final String filename = "dados.dat";
    
    public static void save(SGQ sgq, String nomeFicheiro) throws FileNotFoundException, IOException{
        FileOutputStream foos = new FileOutputStream(nomeFicheiro);
        ObjectOutputStream oos = new ObjectOutputStream(foos);
        oos.writeObject(sgq);
        oos.flush();
        oos.close();
    }
    
    public static SGQ load(String nomeFicheiro) throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(nomeFicheiro);
        ObjectInputStream oos = new ObjectInputStream(fis);
        SGQ sgq = (SGQ) oos.readObject();
        oos.close();
        return sgq;
    }   
}