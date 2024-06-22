package com.example.DAMH.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ncc")
public class NHACUNGCAP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maNCC;
    private String tenNCC;
}
