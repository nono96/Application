/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.Arrays;
import java.sql.*;

/**
 *https://openclassrooms.com/fr/courses/26832-apprenez-a-programmer-en-java
 * https://docs.oracle.com/javase/tutorial/uiswing/components/tabbedpane.html
 * @author Alexandre
 */
public class PPE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, DataAccessException{
     
        if (args.length == 2) {
      args = Arrays.copyOf(args, 3);
      args[2] = "";
    }
    if (args.length != 3) {
      System.err.println("usage: DataStoreInit <url> <login> <password>");
      System.exit(1);
    }

  System.out.print("connecting to " + args[0] + " as " + args[1]);
    DataAccess dao = new DataAccess(args[0], args[1], args[2]);
    System.out.println("\rconnected to " + args[0]);
        dao.initDataStore(0);
        Fenetre f = new Fenetre(dao);
    }
    
}
