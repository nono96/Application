/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.util.Calendar;
import java.util.LinkedList;

/**
 *
 * @author Alexandre
 */
public class Utilisateur{
    LinkedList RepQuestionnaire;
    String test;
    int id;
    LinkedList img_test;
    File fichier_bci;
    int idrep;
    
    public Utilisateur(){
        RepQuestionnaire = new LinkedList();
        img_test = new LinkedList();
        test = "";
        fichier_bci=null;
        Calendar cal = Calendar.getInstance();
        id = (cal.get(Calendar.YEAR)/rand() + cal.get(Calendar.MONTH)*rand() + cal.get(Calendar.DAY_OF_MONTH)*rand() +
                cal.get(Calendar.HOUR)*rand() + cal.get(Calendar.MINUTE)*rand() + cal.get(Calendar.SECOND)*rand() + rand());
    }
    //Sauvegarder les données
    public void sauvegarder_donnee(Test t){
        img_test=t.list_img;
        idrep = img_test.hashCode();
        File dossier = new File("C:"+File.separator+ "Program Files" + File.separator + "OpenBCI_GUI" + File.separator +"SavedData");
        File[] list_file = dossier.listFiles();
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE)-1;
        for (File list_file1 : list_file) {
            if (list_file1.getName().startsWith("OpenBCI-RAW-" + year +"-"+month +"-"+day+"_"+hour+"-"+minute)) {
                fichier_bci = list_file1;
                break;
            }
        }
        String list_image = "";
        for(int i=0;i<img_test.size();i++)
            list_image+=img_test.get(i) + " and \n ";
        String pref="";
        for(int i=4;i<RepQuestionnaire.size();i++) //On commence avec i=4 vu qu'on est obligé de repondre
            // a 4 tquestions au minimum, !!! Blinder le Questionnaire, sinon bug ? !!!
            //Aussi: On peut cocher plusieurs "Désirs" donc 2 ème boucle normalement :)
            pref += RepQuestionnaire.get(i) + " and \n ";
        
        String request1 = "INSERT INTO PARTICIPANT VALUES( " + String.valueOf(id)+","+idrep+")";
        String request2 = "INSERT INTO REPPHOTOS VALUES ( " + String.valueOf(idrep) + "," + list_image+")";
        String request3 = "INSERT INTO QUESTIONNAIRE VALUES ( "+pref+")" ;
        String request4 = "INSERT INTO DONNEES_BCI VALUES ( " + fichier_bci.getName()+")" ;
        
    }
    
    private int rand(){
        return (int) (Math.random()*1000);
    }

    public void repondre_questionnaire(LinkedList rep) {
        RepQuestionnaire = rep;
    }
}
