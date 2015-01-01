/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sempac.isw.control.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author pablito
 */
public class ControlArchivos {

    public void subirArchivo(String filePath, UploadedFile file) throws FileNotFoundException, IOException {
        System.out.println("filePath: " + filePath);
        System.out.println("file: " + file.getContents().length);
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        byte[] buffer = new byte[6124];
        int bulk;
        InputStream inputStream = file.getInputstream();
        while (true) {
            bulk = inputStream.read(buffer);
            if (bulk < 0) {
                break;
            }
            fileOutputStream.write(buffer, 0, bulk);
            fileOutputStream.flush();
        }
        fileOutputStream.close();
        inputStream.close();
    }

    public void descargarArchivo() {

    }

}
