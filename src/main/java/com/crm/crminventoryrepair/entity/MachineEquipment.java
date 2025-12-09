package com.crm.crminventoryrepair.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "machine_equipment")
public class MachineEquipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "machine_id", nullable = false)
    private Machine machine;

    @Size(max = 200)
    @NotNull
    @Column(name = "equipment_name", nullable = false, length = 200)
    private String equipmentName;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @ColumnDefault("1")
    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "notes", length = Integer.MAX_VALUE)
    private String notes;

    @ColumnDefault("now()")
    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;

/*
 TODO [Reverse Engineering] create field to map the 'condition' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "condition", columnDefinition = "equipment_condition")
    private Object condition;
*/
}