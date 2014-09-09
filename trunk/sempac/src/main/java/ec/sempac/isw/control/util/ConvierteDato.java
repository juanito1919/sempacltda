/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sempac.isw.control.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author miguesaca
 */
public class ConvierteDato {

    public static Date getFechaMedium(Date fecha) {
        try {
            DateFormat formato = DateFormat.getDateInstance(DateFormat.MEDIUM);
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(formato.format(fecha));
            return date;
        } catch (Exception ex) {
            return fecha;
        }

    }
}
