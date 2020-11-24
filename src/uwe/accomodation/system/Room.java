/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uwe.accomodation.system;

/**
 *
 * @author Brice
 */
public class Room {
    // Attributes
    private int roomNumber;
    private int hallID;
    private String cleanStatus;
    private String occupancy;
    
    // Constructor
    public Room(int roomNumber, int hallID,
            String cleanStatus, String occupancy) {
        this.roomNumber = roomNumber;
        this.hallID = hallID;
        this.cleanStatus = cleanStatus;
        this.occupancy = occupancy;
    }
    
     //Getters   
    public int getRoomNumber() {
        return roomNumber;
    }
    
    public int getHallID() {
        return hallID;
    }
    
    public String getCleanStatus() {
        return cleanStatus;
    }
    
    public String getOccupancy() {
        return occupancy;
    }
    
    /* -Class Setters- */    
    public void setCleanStatus(String cleanStatus){
        this.cleanStatus = cleanStatus;
    }
    
    public void setOccupancy(String occupancy){
        this.occupancy = occupancy;
    }

}
