/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloth;

/**
 *
 * @author anil
 */
public class Transporter {
    private int TransporterId;
    private String Name;
    private String Surname;

    public Transporter(int TransporterId, String Name, String Surname) {
        this.TransporterId = TransporterId;
        this.Name = Name;
        this.Surname = Surname;
    }

    public int getTransporterId() {
        return TransporterId;
    }

    public void setTransporterId(int TransporterId) {
        this.TransporterId = TransporterId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String Surname) {
        this.Surname = Surname;
    }
    
}
