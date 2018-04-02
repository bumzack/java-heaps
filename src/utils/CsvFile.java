/* ------------------------------------------------------------- */
/* CsvFile.java                                                  */
/* ------------------------------------------------------------- */
/* author: Georg Schinnerl                                       */
/* date: 2018-03-31                                              */
/* ------------------------------------------------------------- */
/* write data as a csv                                           */
/* ------------------------------------------------------------- */

package utils;

import java.io.FileWriter;
import java.io.IOException;


public class CsvFile {
    
    private FileWriter f;

    public CsvFile(String filename) {  
        try {
            f = new FileWriter(filename);
        } catch (IOException ex) {
            System.out.println("something went wrong with accessing the file");
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
    }   

    public void writeString(String s) {
        try {
            f.write(s);
        } catch (IOException ex) {
            System.out.println("something went wrong with accessing the file");
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
    }

    public void closeFile()  {
        try {
            f.close();
        } catch (IOException ex) {
            System.out.println("something went wrong with accessing the file");
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
    }
}
