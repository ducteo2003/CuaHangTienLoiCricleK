package com.example.DAMH.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

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
    @OneToMany(mappedBy = "kho", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LUUKHO> luukhos;
}
