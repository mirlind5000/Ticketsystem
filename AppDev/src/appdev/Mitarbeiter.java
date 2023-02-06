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

public class Mitarbeiter implements Serializable
{
    private int mitarbeiterID;
    private String vorname;
    private String nachname;
    private boolean beschaeftigt;
    
    public Mitarbeiter()
    {
    }
    
    public Mitarbeiter (int mitarbeiterID, String vorname, String nachname, boolean beschaeftigt)
    {
        this.mitarbeiterID = mitarbeiterID;
        this.vorname = vorname;
        this.nachname = nachname;
        this.beschaeftigt = beschaeftigt;
    }
    
    public int getMitarbeiterID()
    {
        return mitarbeiterID;
    }
    
    public void setMitarbeiterID(int mitarbeiterID)
    {
        this.mitarbeiterID = mitarbeiterID;
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
    
    public boolean getBeschaeftigt()
    {
        return beschaeftigt;
    }
    
    public void setBeschaeftigt(boolean beschaeftigt)
    {
        this.beschaeftigt = beschaeftigt;
    }
}