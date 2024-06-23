package sn.groupeisi.jeeappli.entiies;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "duration_months")
    private int durationMonths;

    @Column(name = "amount")
    private double amount;

    @Column(name = "status")
    private String status; // "en attente", "accepté", "refusé"

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Utilisateur qui est le locataire

    // Constructors, Getters and Setters

    public Location() {
    }

    public Location(LocalDate startDate, int durationMonths, double amount, String status, Unit unit, User user) {
        this.startDate = startDate;
        this.durationMonths = durationMonths;
        this.amount = amount;
        this.status = status;
        this.unit = unit;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(int durationMonths) {
        this.durationMonths = durationMonths;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
