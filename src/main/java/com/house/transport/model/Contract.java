package com.house.transport.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "contracts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loading_city", nullable = false)
    private String loadingCity;

    @Column(name = "unloading_city", nullable = false)
    private String unloadingCity;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "property_type", nullable = false)
    private String propertyType;  // studio, apartment/condo, house

    @Column(name = "bedroom_num", nullable = false)
    private int bedroomNum; //frontend de seçenek konulabilir.

    @Column(name = "floor_num", nullable = false)
    private int floorNum;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;


    @Column(name = "mover_id", nullable = false)
    private Long moverId;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @Column(name = "status", nullable = false)
    private String status;

    // Enum types
    public enum PropertyType {
        STUDIO, APARTMENT_CONDO, HOUSE
    }

    public enum Status {
        PENDING, CONFIRMED, COMPLETED, CANCELLED
    }

    // Additional methods to work with enums

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType.name();
    }

    public PropertyType getPropertyTypeEnum() {
        return PropertyType.valueOf(this.propertyType);
    }

    public void setStatus(Status status) {
        this.status = status.name();
    }

    public Status getStatusEnum() {
        return Status.valueOf(this.status);
    }
}

