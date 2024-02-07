package model.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Ruben
 */
@Entity

@XmlRootElement
@Table(name="RENT")
public class Rental implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "Rental_Gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Rental_Gen")
    private Integer id;

    private float totalAmount;
    private Date rentalDate;
    
    @NotNull(message="Customer can't be null")
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonbTransient
    private Customer customer;
    
    //@NotNull(message="Game can't be null")
    @ManyToMany
    private Collection<Game> games ;
    

    public Rental() {
    }

    public Rental(Date rentalDate, float totalAmount, Customer customer, Collection game) { 
        this.rentalDate = rentalDate;
        this.totalAmount = totalAmount;
        this.customer = customer;      
        games = game; 
        
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Collection<Game> getGame() {
        return games;
    }

   

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}