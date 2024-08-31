package com.house.transport.model;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
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
    @FutureOrPresent(message = "The date cannot be before today")
    private LocalDate date;

    @Column(name = "property_type", nullable = false)
    private String propertyType;  // studio, apartment/condo, house

    @Column(name = "bedroom_num", nullable = false)
    @Min(value = 0, message = "Bedroom number must be at least 0")
    private int bedroomNum;

    @Column(name = "floor_num", nullable = false)
    private int floorNum;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;


    @Column(name = "mover_id", nullable = false)
    private Long moverId;

    @Column(name = "total_price", nullable = false)
    @Min(value = 0, message = "Total price cannot be lower than 0")
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
