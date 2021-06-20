/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloth;

/**
 *
 * @author yolgezer
 */
public class ComboboxItem {
     private String id ;
    private String name;
    public String GetId(){return id;}
    public void SetId(String Id){ id=Id;}
    public String GetName(){return name;}
    public void SetName(String Adi){ name=Adi;}
    
    ComboboxItem(String Id,String Adi){
        this.id = Id;
        this.name=Adi;
    }
    public ComboboxItem(){
    super();
    }
    public String toString(){
        return this.name;
    }
}
