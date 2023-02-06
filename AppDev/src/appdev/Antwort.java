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

public class Antwort implements Serializable
{
    private int antwortID;
    private String antworttext;
    
    public Antwort()
    {
    }
    
    public Antwort(int antwortID, String antworttext)
    {
        this.antwortID = antwortID;
        this.antworttext = antworttext;
    }

    public int getAntwortID()
    {
        return antwortID;
    }

    public void setAntwortID(int antwortID)
    {
        this.antwortID = antwortID;
    }
    
    public String getAntworttext()
    {
        return antworttext;
    }

    public void setAntworttext(String antworttext)
    {
        this.antworttext = antworttext;
    }
}