/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdev;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

/**
 *
 * @author Paul
 */
public class Server
{
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException
    {
        //Hier starten wir den Server/ das Programm
        Registry reg;
        int p=1098;
        reg = java.rmi.registry.LocateRegistry.createRegistry(p);
        
        TicketInfoImpl ticket = new TicketInfoImpl ();
        
        Naming.bind("rmi://localhost:1098/ticket_info_server", ticket);
        
        System.out.println("Server läuft...");
        System.out.println("Nicht vergessen Server am Ende zu stopppen!!!");
    }
}
