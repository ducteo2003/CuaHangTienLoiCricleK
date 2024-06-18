package com.example.DAMH.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vitri") //vị trí sẽ có tủ mát, kệ, quầy counter, tủ OFC
public class VITRI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maViTri;
    private String tenViTri;
}
