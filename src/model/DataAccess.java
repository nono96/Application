package model;
import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataAccess implements AutoCloseable {

  private Connection connection;

  public DataAccess(String url, String login, String password) throws
      DataAccessException {
 
   {     
       try {
           connection = DriverManager.getConnection(url,login,password);
       } catch (SQLException ex) {
           throw new DataAccessException("unable to connect to the db");
       }
    } 
  }
  
  

  public boolean initDataStore(int NumeroParticipant)
      throws DataAccessException {
    String sql = null;
     try {
      // drop existing tables, if any
      Statement statement = connection.createStatement();
     try {
        statement.executeUpdate("DROP TABLE PARTICIPANT;");
         statement.executeUpdate("DROP TABLE QUESTIONNAIRE  ;");
         statement.executeUpdate("DROP TABLE REPPHOTOS;");
         
         statement.executeUpdate("DROP TABLE DONNEESBCI;");
      } catch (SQLException e) {
        // likely cause: table does not exists: print error and go on
        System.err.print("drop table PARTICIPANT, Donnees bci, Questionnaire et Repphotos: " + e);
        System.err.println(", going on...");
      } 
      // ...
      // create tables
      sql = "CREATE TABLE PARTICIPANT (ID INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT, IDREP INT  );";
     
      statement.executeUpdate(sql);
       sql = "CREATE TABLE REPPHOTOS (IDREP INT ,  Passage varchar(100) );";
     statement.executeUpdate(sql);
      sql = "CREATE TABLE QUESTIONNAIRE(ID INT UNSIGNED PRIMARY KEY  REFERENCES PARTICIPANT(ID), "
              + "AGE INT UNSIGNED NOT NULL, SEXE BOOLEAN NOT NULL, DESIR varchar(100));";
      statement.executeUpdate(sql);
  sql="CREATE TABLE DONNEESBCI (ID INT UNSIGNED PRIMARY KEY  REFERENCES PARTICIPANT(ID), CHEMIN varchar(100))";
  statement.executeUpdate(sql); 
  // ...
      // populate tables if needed
      // ...
      return true;
    } catch (SQLException e) {
      System.err.println(sql + ": " + e);
      return false;
    }
    // TODO
  }
  /*
      try {
      // drop existing tables, if any
      Statement statement = connection.createStatement();
     try {
        statement.executeUpdate("DROP TABLE PARTICIPANT;");
         statement.executeUpdate("DROP TABLE QUESTIONNAIRE  ;");
         statement.executeUpdate("DROP TABLE REPPHOTOS;");
         
         statement.executeUpdate("DROP TABLE DONNEESBCI;");
      } catch (SQLException e) {
        // likely cause: table does not exists: print error and go on
        System.err.print("drop table PARTICIPANT, Donnees bci, Questionnaire et Repphotos: " + e);
        System.err.println(", going on...");
      } 
      // ...
      // create tables
      
      sql = "create table participant(ID INT UNSIGNED PRIMARY KEY NOT NULL , IDREP INT  );";
      statement.executeUpdate(sql);
      sql="INSERT INTO PARTICIPANT  VALUES("+1+" ,"+1+")";
      statement.executeUpdate(sql);
    
     sql = "create table repphotos(IDREP INT PRIMARY KEY  NOT NULL ,  PASSAGE varchar(100) );";
     statement.executeUpdate(sql);
       sql="insert into repphotos("+1+", "+"Passage des images"+")";
     statement.executeUpdate(sql);
     
     sql = "insert into questionnaire(IDpart INT PRIMARY KEY NOT NULL,  AGE INT , SEXE BOOLEAN , DESIR varchar(100))";
    //  sql = "create table questionnaire(IDpart INT  PRIMARY KEY REFERENCES participant(ID),AGE INT UNSIGNED NOT NULL, SEXE BOOLEAN NOT NULL, DESIR varchar(100));";
      statement.executeUpdate(sql);
            String hello = "Ce que j'aime ...";
             sql = "INSERT INTO questionnaire (`ID`, `AGE`, `SEXE`, `DESIR`) VALUES ('1', '1', '0', 'hello');";
             statement.executeUpdate(sql);
  sql="CREATE TABLE DONNEESBCI (IDparticipant INT UNSIGNED PRIMARY KEY  REFERENCES PARTICIPANT(ID), CHEMIN varchar(100));";
  statement.executeUpdate(sql);   
  // ...
      // populate tables if needed
      // ...
          return true;
  
    } catch (SQLException e) {
      System.err.println("Problème dans la création des tables "+ ": " + e);
      return false;
    }
    // TODO
  }*/
 public boolean newParticipant(  boolean Sexe, int Age, String list_imag ,int idrep ) throws SQLException{
     String sql;
     Statement s = connection.createStatement();
     ResultSet rs ;
     //Partie 1: Histoire de générer un ID aléatoire non chronologique (Anonymat)
     
     int nb1 = 0 ;// Pour pas qu'il soit à 0
     int nb2=0;
     while(nb1==nb2){
     nb1=(int) (Math.random() * ( 1000 - 1));
     sql="SELECT * FROM PARTICIPANT WHERE ID = "+nb1+"";
     rs= s.executeQuery(sql);
     while(rs.next()){
            nb2 = rs.getInt(rs.getInt("ID"));
        //    if (nb1!=nb2){break;}
 }}
       //Maintenant on est sur d'avoir un ID unique non reconnaissable !
      System.out.println("le numero du nouveau participant est :"+nb1);
       //Partie 2 on insére dans notre BDD nb1 : id du participant :)
       
      
     sql="INSERT INTO PARTICIPANT VALUES( " +nb1+","+idrep+")";
     s.executeUpdate(sql);
     // On regarde d'abord le max des idrep déjà présent, et on lui rajoute un histoire que la clé soit primaire
     sql="SELECT MAX(IDREP) FROM REPPHOTOS";    
     rs= s.executeQuery(sql);
     while(rs.next()){
            nb2 = rs.getInt(rs.getInt("ID"));
        //    if (nb1!=nb2){break;}
 }
     sql = "  INSERT INTO REPPHOTOS VALUES ( " + idrep+ "," +list_imag+") ";
     
     
     
     return true;
   } 
 //Regardons d'abord si tout cela marche ! :)

    @Override
    public void close() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 }

