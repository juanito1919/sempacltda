/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sempac.isw.control.util;

import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author miguesaca
 */
public class Validaciones {

    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PATTERN_SEGURIDAD_BAJA = "(?=^.{1,5}$)(?=.*[A-Z])(?=.*[a-z]).*";
    private static final String PATTERN_SEGURIDAD_MEDIA = "(?=^.{6,8}$)(?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).*";
    private static final String PATTERN_SEGURIDAD_ALTA = "(?=^.{8,15}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*";
    private static final String PATTERN_NUMERO = "[\\d]*";
    private static final String PATTERN_LETRAS = "[a-zA-Z]*";
    private static final String PATTERN_PALABRAS = "[a-zA-Z[\\s]]*";
    public static String msg = "";

    /**
     *
     * @param cedula
     * @return
     */
    public static boolean validaCedula(String cedula) {
        boolean cedulaCorrecta = false;
        try {
            if (cedula.length() == 10) // ConstantesApp.LongitudCedula  
            {
                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito < 6) {

                    // Coeficientes de validaciÃ³n cÃ©dula  
                    // El decimo digito se lo considera dÃ­gito verificador  
                    int[] coefValCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};

                    int verificador = Integer.parseInt(cedula.substring(9, 10));
                    int suma = 0;
                    int digito = 0;

                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }

                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        cedulaCorrecta = true;
                    } else if ((10 - (suma % 10)) == verificador) {
                        cedulaCorrecta = true;
                    } else {
                        cedulaCorrecta = false;
                    }
                } else {
                    cedulaCorrecta = false;
                }
            } else {

                cedulaCorrecta = false;
            }
        } catch (NumberFormatException nfe) {
            cedulaCorrecta = false;
        } catch (Exception err) {

            cedulaCorrecta = false;
        }

        return cedulaCorrecta;

    }

    /**
     *
     * @param correoElectronico
     * @return
     */
    public static boolean validaCorreoElectronico(String correoElectronico) {
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(correoElectronico);
        return matcher.matches();

    }

    public static boolean validarNumeros(String numero) {
        Pattern pattern = Pattern.compile(PATTERN_NUMERO);
        Matcher matcher = pattern.matcher(numero);
        return matcher.matches();
    }
    public static boolean validarPalabras(String numero) {
        Pattern pattern = Pattern.compile(PATTERN_PALABRAS);
        Matcher matcher = pattern.matcher(numero);
        return matcher.matches();
    }
    public static boolean validaPersonaMayorEdad(Date fechaNacimiento) {

        int y1 = fechaNacimiento.getYear() + 1900;
        int m1 = fechaNacimiento.getMonth() + 1;
        int d1 = fechaNacimiento.getDate();

        GregorianCalendar fechaNacimientoActual = new GregorianCalendar();
        int y2 = fechaNacimientoActual.get(GregorianCalendar.YEAR);
        int m2 = fechaNacimientoActual.get(GregorianCalendar.MONTH) + 1;
        int d2 = fechaNacimientoActual.get(GregorianCalendar.DAY_OF_MONTH);
        int anos = y2 - y1;
        anos = anos - (((m1 - m2) > 0) ? 1 : (((d1 - d2) > 0) ? 1 : 0));
        return anos >= 18;
    }

    public static boolean validaFechaMenorHoy(Date fecha) {

        DateFormat formato = DateFormat.getDateInstance(DateFormat.MEDIUM);

        return formato.format(fecha).compareTo((formato.format(new Date()))) < 0;
    }

    public static boolean validaFechaMayorHoy(Date fecha) {

        DateFormat formato = DateFormat.getDateInstance(DateFormat.MEDIUM);

        return formato.format(fecha).compareTo((formato.format(new Date()))) > 0;
    }

    public static boolean validaFechaIgualHoy(Date fecha) {

        DateFormat formato = DateFormat.getDateInstance(DateFormat.MEDIUM);

        return formato.format(fecha).compareTo((formato.format(new Date()))) == 0;
    }

    public static boolean contrasenaComplejidadBaja(String contrasena) {
        Pattern pattern = Pattern.compile(PATTERN_SEGURIDAD_BAJA);
        Matcher matcher = pattern.matcher(contrasena);
        return matcher.matches();
    }

    public static boolean contrasenaComplejidadMedia(String contrasena) {
        Pattern pattern = Pattern.compile(PATTERN_SEGURIDAD_MEDIA);
        Matcher matcher = pattern.matcher(contrasena);
        return matcher.matches();
    }

    public static boolean contrasenaComplejidadAlta(String contrasena) {
        Pattern pattern = Pattern.compile(PATTERN_SEGURIDAD_ALTA);
        Matcher matcher = pattern.matcher(contrasena);
        return matcher.matches();
    }

    public static boolean validaTelefonoConvencional(String telefono) {
        try {
            Integer.parseInt(telefono);
            if (telefono.length() > 6) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }

    public static boolean validaTelefonoCelular(String celular) {
        try {
            Integer.parseInt(celular);
            if (celular.length() > 9) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean cumpleRequerimientoCampo(String texto, int tamanioMinimo) {
        if (texto.isEmpty()) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            return false;
        }

        if (texto.trim().length() == 0) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            return false;
        }

        if (texto.length() < tamanioMinimo) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TamanoDebeSerMayorA") + " " + tamanioMinimo;
            return false;
        }

        return true;
    }

    public static boolean cumpleRequerimientoCampo(int valor, int cantidadMinima) {
        if (String.valueOf(valor).isEmpty()) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            return false;
        }

        if (valor < cantidadMinima) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CantidadMinimaAceptada") + " " + cantidadMinima;
            return false;
        }

        return true;
    }

    public static boolean cumpleRequerimientoCampo(Date fecha) {

        if (fecha == null) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            return false;
        }

        return true;
    }
}
