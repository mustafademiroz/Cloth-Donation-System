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
public class Cloth_Entrance {
    private int ClothId;
    private String ClothType;
    private String ClothSize;

    public int getClothId() {
        return ClothId;
    }

    public void setClothId(int ClothId) {
        this.ClothId = ClothId;
    }

    public String getClothType() {
        return ClothType;
    }

    public Cloth_Entrance(int ClothId, String ClothType, String ClothSize) {
        this.ClothId = ClothId;
        this.ClothType = ClothType;
        this.ClothSize = ClothSize;
    }

    public void setClothType(String ClothType) {
        this.ClothType = ClothType;
    }

    public String getClothSize() {
        return ClothSize;
    }

    public void setClothSize(String ClothSize) {
        this.ClothSize = ClothSize;
    }
    
    
}
