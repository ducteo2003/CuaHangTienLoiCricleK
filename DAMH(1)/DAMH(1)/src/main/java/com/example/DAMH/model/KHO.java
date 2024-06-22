package com.example.DAMH.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "kho")
public class KHO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maKho;
    private int soLuongTon; //(soLuongDat+soLuongTon)-soLuongMua
}
