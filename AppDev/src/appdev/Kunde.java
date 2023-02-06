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

public class Kunde implements Serializable
{
    private String vorname;
    private String nachname;
    private int kundenID;
        
    public Kunde (String vorname, String nachname)
    {
        this.vorname = vorname;
        this.nachname = nachname;
    }
        
    public String getVorname()
    {
        return vorname;
    }

    public void setVorname(String vorname)
    {
        this.vorname = vorname;
    }

    public String getNachname()
    {
        return nachname;
    }

    public void setNachname(String nachname)
    {
        this.nachname = nachname;
    }

    public int getKundenID()
    {
        return kundenID;
    }
        
    public void setKundenID (int kundenID)
    {
        this.kundenID = kundenID;
    }
}
