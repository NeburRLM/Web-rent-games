/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 *
 * @author Ruben
 */
@XmlRootElement
public class RentalResponse {
    private Integer id;
    private float totalAmount;
    private Date rentalDate;
    
    public RentalResponse (Integer id, float totalAmount, Date rentalDate){
        this.id=id;
        this.totalAmount=totalAmount;
        this.rentalDate=rentalDate;
    }
    
    public Integer getId() {
        return id;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public Date getRentalDate() {
        return rentalDate;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public void setRentalDate(Date rentalDate){
        this.rentalDate=rentalDate;
    }
    
    
}   


