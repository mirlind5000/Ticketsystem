/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdev;
/**
 *
 * @author Paul
 */
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

public class Ticket implements Serializable
{
    private int ticketID;
    private Time eroeffnungszeitpunkt;
    private int mitarbeiterID;
    private int beschwerdeID;
        
    public Ticket ()
    {
    }
       
    public void setTicketID(int ticketID)
    {
        this.ticketID = ticketID;
    }

    public int getTicketID()
    {
        return ticketID;
    }

    public Timestamp time()
    {
        Timestamp time = new Timestamp(System.currentTimeMillis());
	return time;
    }

    public Timestamp getEroeffnungszeitpunkt()
    {
        return time();
    }

    public int getMitarbeiterID()
    {
        return mitarbeiterID;
    }
        
    public void setMitarbeiterID(int mitarbeiterID)
    {
        this.mitarbeiterID = mitarbeiterID;
    }
        
    public int getBeschwerdeID()
    {
        return beschwerdeID;
    }
        
    public void setBeschwerdeID(int beschwerdeID)
    {
        this.beschwerdeID = beschwerdeID;
    }
}
