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
public class ClothTaker {
    private int Id;
    private String Name;
    private String Surname;
    private String UpperSize;
    private String LowerSize;
    private String City;
    private String District;

    public ClothTaker(int Id,String Name, String Surname, String UpperSize, String LowerSize, String City, String District) {
        this.Id=Id;
        this.Name = Name;
        this.Surname = Surname;
        this.UpperSize = UpperSize;
        this.LowerSize = LowerSize;
        this.City = City;
        this.District = District;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public void setDistrict(String District) {
        this.District = District;
    }

    public void setId(int Id) {
        this.Id = Id;
    }


    public int getId() {
        return Id;
    }
    
    public String getCity() {
        return City;
    }

    public String getDistrict() {
        return District;
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

    public String getUpperSize() {
        return UpperSize;
    }

    public void setUpperSize(String UpperSize) {
        this.UpperSize = UpperSize;
    }

    public String getLowerSize() {
        return LowerSize;
    }

    public void setLowerSize(String LowerSize) {
        this.LowerSize = LowerSize;
    }
}
