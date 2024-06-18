package com.example.DAMH.model;
import jakarta.persistence.*;
import lombok.*;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hinhthuc")
public class HINHTHUC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maHinhThuc;
    private String tenHinhThuc;
}
