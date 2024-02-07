package model.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Column;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

/**
 *
 * @author Ruben
 */
@Entity

@NamedQuery(name = "Game.findName",
        query = "SELECT g FROM Game g WHERE g.name = :name")
@NamedQuery(name = "Game.find",
        query = "SELECT g FROM Game g WHERE g.id = :id")
@XmlRootElement
public class Game implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name="Game_Gen", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Game_Gen") 
    
    private int id;
    private int counter;
    @Column(unique=true)
    @NotNull(message="Game name can't be null")
    private String name;
   
    private String console;
    private Boolean availability;
    private Float weeklyRentalPrice;
    private String description;
    private String gameType;
    private String pickupLocationAddress;
    
    
    @ManyToMany(mappedBy="games")
    @JsonbTransient
    private Collection<Rental> rentals;
    
    // Constructor, getters, and setters

    public Game() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public Boolean isAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public Float getWeeklyRentalPrice() {
        return weeklyRentalPrice;
    }

    public void setWeeklyRentalPrice(Float weeklyRentalPrice) {
        this.weeklyRentalPrice = weeklyRentalPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getPickupLocationAddress() {
        return pickupLocationAddress;
    }

    public void setPickupLocationAddress(String pickupLocationAddress) {
        this.pickupLocationAddress = pickupLocationAddress;
    }
    
    public int getCounter() {
        return counter;
    }
    
    public void check(){
        counter--;
        if (counter <=0){
            availability = false;
        }
    }
    public void setCounter(int counter) {
        this.counter = counter;
    }
    
    public void putGameList(Rental r){
        rentals.add(r);
    }
}