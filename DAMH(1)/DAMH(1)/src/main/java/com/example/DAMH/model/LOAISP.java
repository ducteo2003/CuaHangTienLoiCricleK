package com.example.DAMH.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loaisp")
public class LOAISP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maLoai;
    private String tenLoai;
}
