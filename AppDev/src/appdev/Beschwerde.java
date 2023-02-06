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

public class Beschwerde implements Serializable
{
    private int beschwerdeID;
    private String beschwerdetext;
    private int kundeID;
    private int ticketID;
    private int antwortID;

    public Beschwerde (String beschwerdetext)
    {
        this.beschwerdetext = beschwerdetext;
    }
    
    public Beschwerde(int beschwerdeID, String beschwerdetext, int kundeID, int ticketID)
    {
        this.beschwerdeID = beschwerdeID;
        this.beschwerdetext = beschwerdetext;
        this.kundeID = kundeID;
        this.ticketID = ticketID;
    }

    public int getBeschwerdeID()
    {
        return beschwerdeID;
    }

    public void setBeschwerdeID(int beschwerdeID)
    {
        this.beschwerdeID = beschwerdeID;
    }
    
    public String getBeschwerdetext()
    {
        return beschwerdetext;
    }

    public void setBeschwerdetext(String beschwerdetext)
    {
        this.beschwerdetext = beschwerdetext;
    }
    
    public int getKundeID()
    {
        return kundeID;
    }

    public void setKundeID(int kundeID)
    {
        this.kundeID = kundeID;
    }
    
    public int getTicketID()
    {
        return ticketID;
    }

    public void setTicketID(int ticketID)
    {
        this.ticketID = ticketID;
    }
    
    public int getAntwortID()
    {
        return antwortID;
    }

    public void setAntwortID(int antwortID)
    {
        this.antwortID = antwortID;
    }
}