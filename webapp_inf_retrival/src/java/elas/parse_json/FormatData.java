/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elas.parse_json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ducvu
 */
public class FormatData {

    public static boolean readAndWriteFormatFile(String filename) {
        FileReader fileReader = null;
        try {

            File file = new File(filename);
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            PrintWriter pw = new PrintWriter(new FileWriter
        ("/home/ducvu/Desktop/elas/data/data-format.json",true));
            int id = 1;
            while ((line = bufferedReader.readLine()) != null) {
                pw.write(ParseJson.createIndex(id));
                pw.write("\n");
                pw.write(line);
                pw.write("\n");
                System.out.println(line);
                id++;
                System.out.println(id);
            }
            pw.close();
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FormatData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FormatData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileReader.close();
            } catch (IOException ex) {
                Logger.getLogger(FormatData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    public static void main(String []args){
        readAndWriteFormatFile("/home/ducvu/Desktop/elas/data/yahoo-tech.json");
        return;
    }
}
