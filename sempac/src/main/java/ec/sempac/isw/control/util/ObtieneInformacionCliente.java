/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sempac.isw.control.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author miguesaca
 */
public class ObtieneInformacionCliente {

    public static String obtenerDireccionMAC() throws SocketException, UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();

        String macAddress = "";

        NetworkInterface network = NetworkInterface.getByInetAddress(ip);

        byte[] mac = network.getHardwareAddress();

        System.out.print("Current MAC address : ");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
        }

        //System.out.println(sb.toString());
        macAddress = sb.toString();

        return null;
    }

    public static String obtenerNombreEquipo() {
        // String remoteUser = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteUser();
        String remoteNombreEquipo = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteHost();
        ////System.out.println("host: "+remoteNombreEquipo+" user "+remoteUser);
        return remoteNombreEquipo;
    }

    public static String obtenerDireccionIP() throws UnknownHostException {

        String remoteAddr = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
        // //System.out.println("remote IP address : " + remoteAddr);
        InetAddress inetAddress = InetAddress.getByName(remoteAddr);
        // //System.out.println("inetAddress: " + inetAddress);
        String computerName = inetAddress.getHostName();
       // //System.out.println("computerName: " + computerName);

        return remoteAddr.toString();
    }

}
