/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdev;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 *
 * @author Paul
 */
public interface TicketInfo extends Remote
{
    public abstract String stichwortPruefenDB (int i) throws SQLException, RemoteException;
     
    public abstract String getStichwortDB () throws SQLException, RemoteException;
     
    public abstract String getAntworttextDB (int i) throws SQLException, RemoteException;
     
    public abstract int getKundenIDDB (int i) throws SQLException, RemoteException;
     
    public abstract int kundenIDFinden (Kunde kunden) throws SQLException, RemoteException;
    
    public abstract int beschwerdeIDFinden (Beschwerde beschwerde) throws SQLException, RemoteException;
    
    public abstract int getBeschwerdeIDDB (int i) throws SQLException, RemoteException;
    
    public abstract int ticketIDFinden (Ticket ticket) throws SQLException, RemoteException;
    
    public abstract int getTicketIDDB (int i) throws SQLException, RemoteException;
    
    public abstract Mitarbeiter freierMitarbeiterDB () throws SQLException, RemoteException;
    
    public abstract void insertIntoDB(Kunde k) throws SQLException, RemoteException;
    
    public abstract void insertIntoDB(Ticket t) throws SQLException, RemoteException;
    
    public abstract void insertIntoDB(Beschwerde b) throws SQLException, RemoteException;
    
    public abstract void insertIntoDB(Antwort a) throws SQLException, RemoteException;
    
    public abstract int antwortIDFinden (Antwort antwort) throws SQLException, RemoteException;
    
    public abstract int getAntwortIDDB (int i) throws SQLException, RemoteException;
    
    public abstract void insertIntoDB(Stichwort s) throws SQLException, RemoteException;
    
    public abstract int stichwortIDFinden (Stichwort stichwort) throws SQLException, RemoteException;
    
    public abstract int getStichwortIDDB (int i) throws SQLException, RemoteException;
    
    public abstract void updateMitarbeiterBesetztDB(Mitarbeiter m) throws SQLException, RemoteException;
    
    public abstract void updateMitarbeiterFreiDB(Mitarbeiter m) throws SQLException, RemoteException;
    
    public abstract void updateBeschwerdeDB(Beschwerde b, int antwortID) throws SQLException, RemoteException;
}
