package com.crm.crminventoryrepair.entity;

import com.crm.crminventoryrepair.enums.HandleType;
import com.crm.crminventoryrepair.enums.OtherEquipmentType;
import com.crm.crminventoryrepair.enums.WireSize;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "machines")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "machine_id", nullable = false, unique = true)
    @ToString.Include
    private String machineId;

    @Column(name = "serial_number", unique = true)
    private String serialNumber;

    private String model;
    private String manufacturer;

    @Enumerated(EnumType.STRING)
    private com.crm.crminventoryrepair.enums.MachineStatus status;

    @Enumerated(EnumType.STRING)
    private HandleType weldingHandle;

    @Enumerated(EnumType.STRING)
    private WireSize wire;

    @Enumerated(EnumType.STRING)
    private OtherEquipmentType other;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @ToString.Exclude
    private Customer customer;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "machine", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<MachineEquipment> equipment = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}