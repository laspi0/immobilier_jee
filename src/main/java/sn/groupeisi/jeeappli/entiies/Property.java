package sn.groupeisi.jeeappli.entiies;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description;

    @ElementCollection
    @CollectionTable(name = "equipments", joinColumns = @JoinColumn(name = "property_id"))
    @Column(name = "equipment")
    private List<String> equipments;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Property() {
    }

    public Property(String name, String address, String description, List<String> equipments, User user) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.equipments = equipments;
        this.user = user;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<String> equipments) {
        this.equipments = equipments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
