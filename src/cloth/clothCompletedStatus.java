/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloth;

/**
 *
 * @author BATU
 */
public class clothCompletedStatus {
   private String cloth_size;
   private String cloth_type;
   private String hangar_name;

    public clothCompletedStatus(String cloth_size, String cloth_type, String hangar_name) {
        this.cloth_size = cloth_size;
        this.cloth_type = cloth_type;
        this.hangar_name = hangar_name;
    }

    public String getCloth_size() {
        return cloth_size;
    }

    public void setCloth_size(String cloth_size) {
        this.cloth_size = cloth_size;
    }

    public String getCloth_type() {
        return cloth_type;
    }

    public void setCloth_type(String cloth_type) {
        this.cloth_type = cloth_type;
    }

  

    public String getHangar_name() {
        return hangar_name;
    }

    public void setHangar_name(String hangar_name) {
        this.hangar_name = hangar_name;
    }

  
    
    
}
