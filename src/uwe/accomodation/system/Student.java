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
public class Student {
    
     // Attributes
    private String Name;
    private int ID;
//    private int LeaseNumber;
    
    // Constructor
    public Student(String Name, int ID) {
        this.Name = Name;
        this.ID = ID;
//        this.LeaseNumber = LeaseNumber;
    }
    
    // Getters   
    public String getName() {
        return Name;
    }
    
    public int getID() {
        return ID;
    }   

//    public int getLeaseNumber() {
////        return LeaseNumber;
//    }   
}
