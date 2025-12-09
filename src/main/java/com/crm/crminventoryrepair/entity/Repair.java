package com.crm.crminventoryrepair.entity;

import com.crm.crminventoryrepair.enums.RepairPriority;
import com.crm.crminventoryrepair.enums.WorkOrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "repairs")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "machine_id", nullable = false)
    @ToString.Exclude
    private Machine machine;

    @Enumerated(EnumType.STRING)
    private RepairPriority priority;

    @Enumerated(EnumType.STRING)
    private WorkOrderStatus status;

    @Column(name = "estimated_hours", precision = 7, scale = 2)
    private BigDecimal estimatedHours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_technician_id")
    @ToString.Exclude
    private Technician assignedTechnician;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    private LocalDate scheduledDate;
    private LocalDate completedDate;

    @OneToMany(mappedBy = "repair", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<WorkOrder> workOrders = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}