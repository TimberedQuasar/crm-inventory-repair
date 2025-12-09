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
@Table(name = "documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "machine_id", nullable = false)
    private Machine machine;

    @Size(max = 255)
    @Column(name = "file_path")
    private String filePath;

    @Size(max = 50)
    @Column(name = "file_type", length = 50)
    private String fileType;

    @ColumnDefault("now()")
    @Column(name = "uploaded_at")
    private Instant uploadedAt;

}