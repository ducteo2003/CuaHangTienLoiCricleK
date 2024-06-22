package com.example.DAMH.model;
import jakarta.persistence.*;
import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chucvu")

public class CHUCVU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maChucVu;
    private String tenChucVu;
}
