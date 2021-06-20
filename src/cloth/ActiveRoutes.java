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
public class ActiveRoutes {
    private int ClothId;
    private String ClothType;
    private String ClothSize;
    private int TransporterId;

    public String getClothType() {
        return ClothType;
    }

    public int getClothId() {
        return ClothId;
    }

    public void setClothId(int ClothId) {
        this.ClothId = ClothId;
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

    public int getTransporterId() {
        return TransporterId;
    }

    public void setTransporterId(int TransporterId) {
        this.TransporterId = TransporterId;
    }

    public ActiveRoutes(int ClothId,String ClothType, String ClothSize, int TransporterId) {
        this.ClothId=ClothId;
        this.ClothType = ClothType;
        this.ClothSize = ClothSize;
        this.TransporterId = TransporterId;
    }
    
}
