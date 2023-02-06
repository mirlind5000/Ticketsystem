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

public class Stichwort implements Serializable
{
    private int stichwortID;
    private String bezeichung;
    private int antwortID;
    
    public Stichwort()
    {}
    
    public Stichwort(int stichwortID, String bezeichung)
    {
        this.stichwortID = stichwortID;
        this.bezeichung = bezeichung;
    }
    
    public int getStichwortID()
    {
        return stichwortID;
    }
    
    public void setStichwortID (int stichwortID)
    {
        this.stichwortID = stichwortID;
    }
    
    public String getBezeichnung()
    {
        return bezeichung;
    }

    public void setBezeichnung(String bezeichung)
    {
        this.bezeichung = bezeichung;
    }
    
    public int getAntwortID()
    {
        return antwortID;
    }
    
    public void setAntwortID (int antwortID)
    {
        this.antwortID = antwortID;
    }
}
