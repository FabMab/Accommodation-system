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
public class Hall {
   // Attributes
    private String hallName;
    private int hallID;
 
    
    // Constructor
    public Hall(String hallName, int hallID) {
        this.hallName = hallName;
        this.hallID = hallID;
    }
    
    //Getters- */    
    public String getHallName() {
        return hallName;
    }
    
    public int getHallID() {
        return hallID;
    }
    
 
}
