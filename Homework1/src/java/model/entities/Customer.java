package model.entities;


import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.List;

@Entity
@NamedQuery(name = "Customer.find",
        query = "SELECT c FROM Customer c WHERE c.id = :id")
@XmlRootElement
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "Customer_Gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Customer_Gen")
    private int id;

    @NotNull(message = "Username can't be null")
    private String name;

    @Column(unique = true)
    @NotNull(message = "Email can't be null")
    private String email;
    
    private String password;

    @Column(unique = true)
    private String phone;
    
    
    @NotNull(message="Rental can't be null")
    @OneToMany(mappedBy = "customer")
    private Collection<Rental> rentals;
    
    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setPassword(String password){
        this.password=password;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public List<Rental> getRental() {
        return (List<Rental>) rentals;
    }
    
    public void addRental(Rental r){
        rentals.add(r);
    }
    
}
