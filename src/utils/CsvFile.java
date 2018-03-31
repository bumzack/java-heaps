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

public class CsvFile {
    
    private FileWriter f;

    public CsvFile(String filename) throws java.io.IOException {     
        f = new FileWriter(filename);
    }   

    public void writeString(String s)  throws java.io.IOException {
        f.write(s);
    }

    public void closeFile()  throws java.io.IOException  {
        f.close();
    }
}
