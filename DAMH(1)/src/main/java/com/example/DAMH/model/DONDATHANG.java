package com.example.DAMH.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dondathang")
public class DONDATHANG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maDon;
    private Date ngayTao;
    private String ghiChu;

    @OneToOne
    @JoinColumn(name="maPhieuLuu")
    private PHIEULUUKHO phieuluukho;
}
