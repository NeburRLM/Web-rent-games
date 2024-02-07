/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.model;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 *
 * @author Ruben
 */
@SessionScoped
@Named("infoPurchase")
public class InfoPurchase implements Serializable{
    private int id;
    private String rentalDate;
    private float totalAmount;

    
    public InfoPurchase() {
    }

    public int getId() {
        return id;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    
}
