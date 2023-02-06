/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdev;

import appdev.Kunde;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Paul
 */
public class TicketInfoImpl extends UnicastRemoteObject implements  TicketInfo
{
    public TicketInfoImpl() throws RemoteException
    {}

    @Override
    public String stichwortPruefenDB(int i) throws SQLException, RemoteException {
        String dbURL = "jdbc:mariadb://localhost/ticketsystem";
            String driver = "org.mariadb.jdbc.Driver";
            Connection con = null;
            try
            {
                Class.forName(driver);
                con = DriverManager.getConnection(dbURL, "root", "");
                Statement stat = con.createStatement();
                stat.execute("Select Bezeichnung from Stichwort where StichwortID = " + i);
            
                ResultSet res = stat.getResultSet();
                if (res != null)
                {
                    try
                    {
                        while (res.next ())
                        {
                            String stichwort = res.getString("Bezeichnung");
                            return stichwort;
                        }
                    }
                    catch(SQLException e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        stat.close();
                        res.close();
                    }
                }
            }
            
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            finally
            {
                con.close();
            }
            return null;
    }

    @Override
    public String getStichwortDB() throws SQLException, RemoteException
    {
        String stichworte = null;
        String dbURL = "jdbc:mariadb://localhost/ticketsystem";
        String driver = "org.mariadb.jdbc.Driver";
        Connection con = null;
        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(dbURL, "root", "");
            Statement stat = con.createStatement();
            stat.execute("Select Bezeichnung from Stichwort");
            
            ResultSet res = stat.getResultSet();
            if (res != null)
            {
                try
                {
                    while (res.next ())
                    {
                        if (stichworte == null)
                        {
                            stichworte = res.getString("Bezeichnung");
                        }
                        else
                        {
                            stichworte = stichworte + " --- " + res.getString("Bezeichnung");
                        }
                    }
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    res.close();
                    stat.close();
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            con.close();
        }
        return stichworte;
    }
    
    @Override
    public String getAntworttextDB (int i) throws SQLException, RemoteException
        {
            String antwort = null;
            String dbURL = "jdbc:mariadb://localhost/ticketsystem";
            String driver = "org.mariadb.jdbc.Driver";
            Connection con = null;
            try
            {
                Class.forName(driver);
                con = DriverManager.getConnection(dbURL, "root", "");
                Statement stat = con.createStatement();
                stat.execute("Select Antworttext from Antwort where AntwortID = " + i);
            
                ResultSet res = stat.getResultSet();
                if (res != null)
                {
                    try
                    {
                        while (res.next ())
                        {   
                                antwort = res.getString("Antworttext");
                        }
                    }
                    catch(SQLException e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        stat.close();
                        res.close();
                    }
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            finally
            {
                con.close();
            }
            return antwort;
        }
    
    @Override
    public int getKundenIDDB (int i) throws SQLException, RemoteException
        {
            String dbURL = "jdbc:mariadb://localhost/ticketsystem";
            String driver = "org.mariadb.jdbc.Driver";
            Connection con = null;
            try
            {
                Class.forName(driver);
                con = DriverManager.getConnection(dbURL, "root", "");
                Statement stat = con.createStatement();
                stat.execute("Select KundenID from Kunden where KundenID = " + i);
            
                ResultSet res = stat.getResultSet();
                if (res != null)
                {
                    try
                    {
                        while (res.next ())
                        {
                            int iD;
                            return iD = res.getInt("KundenID");
                        }
                    }
                    catch(SQLException e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        stat.close();
                        res.close();
                    }
                }
            }
            
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            finally
            {
                con.close();
            }
            return 0;
        }
    
    @Override
    public int kundenIDFinden (Kunde kunden) throws SQLException, RemoteException
    {
        for(int i = 1; i < 100; i++) //geringste freie KundenID finden
        {
            int kundenID = getKundenIDDB(i);
            if (0 == kundenID) 
            {
                kunden.setKundenID(i);
                return i;
            }
        }
        return 0;
    }
    
    @Override
    public  int getBeschwerdeIDDB (int i) throws SQLException, RemoteException
    {
        String dbURL = "jdbc:mariadb://localhost/ticketsystem";
        String driver = "org.mariadb.jdbc.Driver";
        Connection con = null;
        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(dbURL, "root", "");
            Statement stat = con.createStatement();
            stat.execute("Select BeschwerdeID from Beschwerde where BeschwerdeID = " + i);
            
            ResultSet res = stat.getResultSet();
            if (res != null)
            {
                try
                {
                    while (res.next ())
                    {
                        int iD;
                        iD = res.getInt("BeschwerdeID");
                        
                        return iD;
                    }
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    stat.close();
                    res.close();
                }
            }
        }    
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            con.close();
        }
        return 0;
    }
    
        @Override
    public int beschwerdeIDFinden (Beschwerde beschwerde) throws SQLException, RemoteException
    {
        for(int i = 1; i < 100; i++) //geringste freie BeschwerdeID finden
        {
            int beschwerdeID = getBeschwerdeIDDB(i);
            if (0 == beschwerdeID) 
            {
                beschwerde.setBeschwerdeID(i);
                return i;
                
            }
        }
        return 0;
    }
    
    @Override
    public int ticketIDFinden (Ticket ticket) throws SQLException, RemoteException
    {
        for(int i = 1; i < 100; i++) //geringste freie BeschwerdeID finden
        {
            int ticketID = getTicketIDDB(i);
            if (0 == ticketID) 
            {
                ticket.setTicketID(i);
                return i;
            }
        }
        return 0;
    }
    
    @Override
    public int getTicketIDDB (int i) throws SQLException, RemoteException
        {
            String dbURL = "jdbc:mariadb://localhost/ticketsystem";
            String driver = "org.mariadb.jdbc.Driver";
            Connection con = null;
            try
            {
                Class.forName(driver);
                con = DriverManager.getConnection(dbURL, "root", "");
                Statement stat = con.createStatement();
                stat.execute("Select TicketID from Ticket where TicketID = " + i);
            
                ResultSet res = stat.getResultSet();
                if (res != null)
                {
                    try
                    {
                        while (res.next ())
                        {
                            int iD;
                            return iD = res.getInt("TicketID");
                        }
                    }
                    catch(SQLException e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        stat.close();
                        res.close();
                    }
                }
            }
            
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            finally
            {
                con.close();
            }
            return 0;
        }
    
    @Override
    public Mitarbeiter freierMitarbeiterDB () throws SQLException, RemoteException
    {
        String dbURL = "jdbc:mariadb://localhost/ticketsystem";
        String driver = "org.mariadb.jdbc.Driver";
        Connection con = null;
        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(dbURL, "root", "");
            Statement stat = con.createStatement();
            stat.execute("Select * from Mitarbeiter where beschaeftigt = 0;");
            
            ResultSet res = stat.getResultSet();
            if (res != null)
            {
                try
                {
                    while (res.next ())
                    {
                        Mitarbeiter mitarbeiter = new Mitarbeiter(res.getInt("MitarbeiterID"), res.getString("Vorname"), res.getString("Nachname"), res.getBoolean("Beschaeftigt"));
                        return mitarbeiter;
                    }
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    stat.close();
                    res.close();
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            con.close();
        }            
        return null;
    }
    
    @Override
    public void insertIntoDB(Kunde k) throws SQLException, RemoteException
    {
        String dbURL = "jdbc:mariadb://localhost/ticketsystem";
        String driver = "org.mariadb.jdbc.Driver";
	Connection con = null;
	try 
        {
            Class.forName(driver);
            con = DriverManager.getConnection(dbURL, "root", "");
            Statement stat = con.createStatement();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
	}
	String sql = "INSERT INTO Kunden (KundenID, Vorname, Nachname) VALUES (?, ?, ?);";
	PreparedStatement ps = con.prepareStatement(sql);
	ps.setInt(1, k.getKundenID());
	ps.setString(2, k.getVorname());
	ps.setString(3, k.getNachname());
	ps.execute();
        con.close();
    }
    
    @Override
    public void insertIntoDB(Ticket t) throws SQLException, RemoteException
    {
        String dbURL = "jdbc:mariadb://localhost/ticketsystem";
	String driver = "org.mariadb.jdbc.Driver";
	Connection con = null;
	try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(dbURL, "root", "");
            Statement stat = con.createStatement();
	}
        catch (SQLException e) 
        {
            e.printStackTrace();
	}
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
	}
        String sql = "INSERT INTO Ticket (ticketID, eroeffnungszeitpunkt, mitarbeiterID, beschwerdeID) VALUES (?, ?, ?, ?);";
	PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, t.getTicketID());
	ps.setTimestamp(2, t.getEroeffnungszeitpunkt());
	ps.setInt(3, t.getMitarbeiterID());
	ps.setInt(4, t.getBeschwerdeID());
	ps.execute();
        con.close();
    }
    
    @Override
    public void insertIntoDB(Beschwerde b) throws SQLException, RemoteException
    {
        String dbURL = "jdbc:mariadb://localhost/ticketsystem";
	String driver = "org.mariadb.jdbc.Driver";
	Connection con = null;
	try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(dbURL, "root", "");
            Statement stat = con.createStatement();
	}
        catch (SQLException e) 
        {
            e.printStackTrace();
	}
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
	}
	String sql = "INSERT INTO Beschwerde (BeschwerdeID, Beschwerdetext, KundeID, TicketID) VALUES (?, ?, ?, ?);";
	PreparedStatement ps = con.prepareStatement(sql);
	ps.setInt(1, b.getBeschwerdeID());
	ps.setString(2, b.getBeschwerdetext());
	ps.setInt(3, b.getKundeID());
	ps.setInt(4, b.getTicketID());
	ps.execute();
        con.close();
    }
    
    @Override
    public void insertIntoDB(Antwort a) throws SQLException, RemoteException
    {
        String dbURL = "jdbc:mariadb://localhost/ticketsystem";
	String driver = "org.mariadb.jdbc.Driver";
	Connection con = null;
	try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(dbURL, "root", "");
            Statement stat = con.createStatement();
	}
        catch (SQLException e) 
        {
            e.printStackTrace();
	}
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
	}
	String sql = "INSERT INTO Antwort (AntwortID, Antworttext) VALUES (?, ?);";
	PreparedStatement ps = con.prepareStatement(sql);
	ps.setInt(1, a.getAntwortID());
	ps.setString(2, a.getAntworttext());
	ps.execute();
        con.close();
    }
    
    @Override
    public int antwortIDFinden (Antwort antwort) throws SQLException, RemoteException
    {
        for(int i = 1; i < 100; i++) //geringste freie AntwortID finden
        {
            int antwortID = getAntwortIDDB(i);
            if (0 == antwortID) 
            {
                antwort.setAntwortID(i);
                return i;
            }
        }
        return 0;
    }
    
    @Override
    public int getAntwortIDDB (int i) throws SQLException, RemoteException
        {
            String dbURL = "jdbc:mariadb://localhost/ticketsystem";
            String driver = "org.mariadb.jdbc.Driver";
            Connection con = null;
            try
            {
                Class.forName(driver);
                con = DriverManager.getConnection(dbURL, "root", "");
                Statement stat = con.createStatement();
                stat.execute("Select AntwortID from Antwort where AntwortID = " + i);
            
                ResultSet res = stat.getResultSet();
                if (res != null)
                {
                    try
                    {
                        while (res.next ())
                        {
                            int iD;
                            return iD = res.getInt("AntwortID");
                        }
                    }
                    catch(SQLException e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        stat.close();
                        res.close();
                    }
                }
            }
            
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            finally
            {
                con.close();
            }
            return 0;
        }
    
    @Override
    public void insertIntoDB(Stichwort s) throws SQLException, RemoteException
    {
        String dbURL = "jdbc:mariadb://localhost/ticketsystem";
	String driver = "org.mariadb.jdbc.Driver";
	Connection con = null;
	try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(dbURL, "root", "");
            Statement stat = con.createStatement();
	}
        catch (SQLException e) 
        {
            e.printStackTrace();
	}
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
	}
	String sql = "INSERT INTO Stichwort (StichwortID, Bezeichnung, AntwortID) VALUES (?, ?, ?);";
	PreparedStatement ps = con.prepareStatement(sql);
	ps.setInt(1, s.getStichwortID());
	ps.setString(2, s.getBezeichnung());
        ps.setInt(3, s.getAntwortID());
	ps.execute();
        con.close();
    }
    
    @Override
    public int stichwortIDFinden (Stichwort stichwort) throws SQLException, RemoteException
    {
        for(int i = 1; i < 100; i++) //geringste freie KundenID finden
        {
            int stichwortID = getStichwortIDDB(i);
            if (0 == stichwortID) 
            {
                stichwort.setStichwortID(i);
                return i;
            }
        }
        return 0;
    }
    
    @Override
    public int getStichwortIDDB (int i) throws SQLException, RemoteException
        {
            String dbURL = "jdbc:mariadb://localhost/ticketsystem";
            String driver = "org.mariadb.jdbc.Driver";
            Connection con = null;
            try
            {
                Class.forName(driver);
                con = DriverManager.getConnection(dbURL, "root", "");
                Statement stat = con.createStatement();
                stat.execute("Select StichwortID from Stichwort where StichwortID = " + i);
            
                ResultSet res = stat.getResultSet();
                if (res != null)
                {
                    try
                    {
                        while (res.next ())
                        {
                            int iD;
                            return iD = res.getInt("StichwortID");
                        }
                    }
                    catch(SQLException e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        stat.close();
                        res.close();
                    }
                }
            }
            
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            finally
            {
                con.close();
            }
            return 0;
        }
    
    @Override
    public void updateMitarbeiterBesetztDB(Mitarbeiter m) throws SQLException, RemoteException
    {
        String dbURL = "jdbc:mariadb://localhost/ticketsystem";
	String driver = "org.mariadb.jdbc.Driver";
	Connection con = null;
	try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(dbURL, "root", "");
            Statement stat = con.createStatement();
	}
        catch (SQLException e) 
        {
            e.printStackTrace();
	}
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
	}
	String sql = "Update Mitarbeiter Set Beschaeftigt = 1 where MitarbeiterID = " + m.getMitarbeiterID() + ";";
	PreparedStatement ps = con.prepareStatement(sql);
	ps.execute();
        con.close();
    }
    
    @Override
    public void updateMitarbeiterFreiDB(Mitarbeiter m) throws SQLException, RemoteException
    {
        String dbURL = "jdbc:mariadb://localhost/ticketsystem";
	String driver = "org.mariadb.jdbc.Driver";
	Connection con = null;
	try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(dbURL, "root", "");
            Statement stat = con.createStatement();
	}
        catch (SQLException e) 
        {
            e.printStackTrace();
	}
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
	}
	String sql = "Update Mitarbeiter Set Beschaeftigt = 0 where MitarbeiterID = " + m.getMitarbeiterID() + ";";
	PreparedStatement ps = con.prepareStatement(sql);
	ps.execute();
        con.close();
    }
    
    @Override
    public void updateBeschwerdeDB(Beschwerde b, int antwortID) throws SQLException, RemoteException
    {
        String dbURL = "jdbc:mariadb://localhost/ticketsystem";
	String driver = "org.mariadb.jdbc.Driver";
	Connection con = null;
	try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(dbURL, "root", "");
            Statement stat = con.createStatement();
	}
        catch (SQLException e) 
        {
            e.printStackTrace();
	}
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
	}
	String sql = "Update Beschwerde Set AntwortID = " + antwortID + " where BeschwerdeID = " + b.getBeschwerdeID()+ ";";
	PreparedStatement ps = con.prepareStatement(sql);
	ps.execute();
        con.close();
    }
}
