/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdev;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Paul
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException, SQLException
    {
        //Client-Seite: hier passieren die Eingaben
        TicketInfo ticket = (TicketInfo) Naming.lookup("rmi://localhost:1098/ticket_info_server");
       
        Scanner scan = new Scanner(System.in);

        System.out.println("Guten Tag, wie können wir Ihnen weiter helfen?");
        System.out.println("Bitte beschreiben Sie ihr Anliegen oder");
        System.out.println("falls es sich bei ihrem Anliegen um eines der folgenden Stichworte handelt geben Sie dieses bitte ein:");
        System.out.println();
        String stichworte = ticket.getStichwortDB();
        System.out.println(stichworte);
        System.out.println();
        System.out.println("Stichwort oder Anliegen bitte eingeben: ");
        String UserBeschwerde = scan.nextLine(); //Usereingabe
        
        for (int i = 1; i < 50; i++)
        {
            String stichwort = ticket.stichwortPruefenDB(i);
            if (UserBeschwerde.equalsIgnoreCase(stichwort)) //Usereingabe mit vorhandenen Stichworten vergleichen
            {
                System.out.println();
                System.out.println("Lösungsvorschlag:");
                String antworttext = ticket.getAntworttextDB(i);
                System.out.println(antworttext);
                System.out.println();
                System.out.println("Ich hoffe wir konnten Ihnen weiterhelfen, bis zum nächsten Mal!");
                System.exit(0); //Programm zu Ende, Stichwort mit Antwort wurde gefunden
            }
        }
        
        System.out.println("Es wurde keine Antwort gefunden, ein Ticket wird erstellt.");
        System.out.println("Geben Sie bitte ihren Vornamen ein:");
        String vorname = scan.nextLine(); //Usereingabe
        System.out.println();
        System.out.println("Geben Sie bitte ihren Nachnamen ein:");
        String nachname = scan.nextLine(); //Usereingabe
        System.out.println();
        
        Kunde kunde = new Kunde (vorname, nachname); //Kunde anlegen (nicht in DB)
        Beschwerde beschwerde = new Beschwerde(UserBeschwerde); //Beschwerde anlegen
        Ticket ticket1 = new Ticket (); //Ticket anlegen
        Mitarbeiter mitarbeiter = new Mitarbeiter(); //Mitarbeiter anlegen
        Antwort antwort = new Antwort(); //Antwort provisorisch anlegen
        Stichwort stichwort = new Stichwort(); //Antwort provisorisch anlegen
        
        int kundenID = ticket.kundenIDFinden(kunde);
        int beschwerdeID = ticket.beschwerdeIDFinden(beschwerde);
        int ticketID = ticket.ticketIDFinden(ticket1);
        beschwerde.setBeschwerdeID(beschwerdeID);
        kunde.setKundenID(kundenID);
        ticket1.setTicketID(ticketID);

        beschwerde.setKundeID(kunde.getKundenID()); //Gefundene Werte auf "beschwerde übertragen"
        beschwerde.setTicketID(ticket1.getTicketID());
        
        mitarbeiter = ticket.freierMitarbeiterDB(); //freien Mitarbeiter finden & speichern
        ticket1.setMitarbeiterID(mitarbeiter.getMitarbeiterID());
        
        ticket1.setBeschwerdeID(beschwerde.getBeschwerdeID());

        try
        {
            ticket.insertIntoDB(kunde); //Kunde anlegen in DB
            ticket.insertIntoDB(ticket1); //Ticket anlegen in DB
            ticket.insertIntoDB(beschwerde); //Beschwerde anlegen in DB
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
        boolean schleife = true;
        boolean schleife2 = true;
        
        do
        {
            mitarbeiter = ticket.freierMitarbeiterDB(); //freien Mitarbeiter finden & speichern

            ticket.updateMitarbeiterBesetztDB(mitarbeiter); //Mitarbeiter auf besetzt setzen
        
            System.out.println();
            System.out.println("Der Mitarbeiter " + mitarbeiter.getVorname() + " " + mitarbeiter.getNachname() + " bearbeitet nun ihr Problem!");
            System.out.println();
            do
            {
                System.out.println("Der Mitarbeiter arbeitet...");
                System.out.println();
                System.out.println("Wurde eine Antwort vom Mitarbeiter gefunden?");
                System.out.println("Bitte geben Sie ja oder nein ein:");
        
                String antwortGefunden = scan.nextLine(); //Usereingabe
        
                System.out.println();
        
                if (antwortGefunden.equalsIgnoreCase("ja"))
                {
                    System.out.println("[Mitarbeiter] Geben Sie die Antwort ein: ");
                    String antwortMitarbeiter = scan.nextLine(); //Usereingabe
            
                    System.out.println("Die Antwort auf das Problem lautet: " + antwortMitarbeiter);
                    System.out.println();
                    System.out.println("Sind Sie zufrieden mit der Atwort?");
                    System.out.println("Bitte geben Sie ja oder nein ein:");
                    String antwortZufrieden = scan.nextLine(); //Usereingabe
                    System.out.println();
            
                    if (antwortZufrieden.equalsIgnoreCase("ja"))
                    {
                        int antwortID = ticket.antwortIDFinden(antwort);
                        antwort.setAntwortID(antwortID);
                        antwort.setAntworttext(antwortMitarbeiter);
                
                        int stichwortID = ticket.stichwortIDFinden(stichwort);
                        stichwort.setStichwortID(stichwortID);
                        stichwort.setBezeichnung(UserBeschwerde);
                        stichwort.setAntwortID(antwortID);
                        
                        beschwerde.setAntwortID(antwortID);
                        
                        ticket.updateBeschwerdeDB(beschwerde, antwortID);
                
                        try
                        {
                            ticket.insertIntoDB(antwort); //Antwort anlegen in DB
                            ticket.insertIntoDB(stichwort);
                        }
                        catch (SQLException ex)
                        {
                            ex.printStackTrace();
                        }
                        ticket.updateMitarbeiterFreiDB(mitarbeiter); //Mitarbeiter auf frei setzen
                        System.out.println("Die Antwort wird für spätere User gespeichert.");
                        System.out.println("Bis zum nächsten Mal!");
                        System.exit(0);
                    }
                    else
                    {
                        schleife2 = false;
                    }  
                }   
                else
                {
                    System.out.println("Sind 3 Tage vergangen?");
                    System.out.println("Bitte geben Sie ja oder nein ein:");
                    String tageVergangen = scan.nextLine(); //Usereingabe
                    if (tageVergangen.equalsIgnoreCase("ja"))
                    {
                        schleife2 = false;
                    }
                }
            } while (schleife2);
            System.out.println();
            System.out.println("Der Mitarbeiter hat es nicht geschafft ihr Problem optimal zu lösen.");
            System.out.println("Wir teilen Ihnen nun einen neuen Mitarbeiter zu");
            System.out.println();
        } while (schleife);
    }
}
