package sn.groupeisi.jeeappli.entiies;

import jakarta.persistence.*;

@Entity
@Table(name = "units")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "unit_number", nullable = false)
    private String unitNumber;

    @Column(name = "number_of_rooms", nullable = false)
    private int numberOfRooms;

    @Column(name = "area", nullable = false)
    private double area;

    @Column(name = "rent", nullable = false)
    private double rent;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "user_id")
    private int userId;
    // Constructors
    public Unit() {
    }

    public Unit(String unitNumber, int numberOfRooms, double area, double rent, Property property) {
        this.unitNumber = unitNumber;
        this.numberOfRooms = numberOfRooms;
        this.area = area;
        this.rent = rent;
        this.property = property;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
