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
public class Avaliable {
    private String HangarName;
    private String ClothSize;
    private String ClothType;

    public String getHangarName() {
        return HangarName;
    }

    public void setHangarName(String HangarName) {
        this.HangarName = HangarName;
    }

    public Avaliable(String HangarName, String ClothSize, String ClothType) {
        this.HangarName = HangarName;
        this.ClothSize = ClothSize;
        this.ClothType = ClothType;
    }

    public String getClothSize() {
        return ClothSize;
    }

    public void setClothSize(String ClothSize) {
        this.ClothSize = ClothSize;
    }

    public String getClothType() {
        return ClothType;
    }

    public void setClothType(String ClothType) {
        this.ClothType = ClothType;
    }
    
    
    
}
