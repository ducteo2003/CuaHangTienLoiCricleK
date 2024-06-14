package com.example.DAMH.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phieuluukho")
public class PHIEULUUKHO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maPhieuLuu;
    private Date ngayTaoPhieu;

    @OneToOne
    @JoinColumn(name="maDon")
    private DONDATHANG dondathang;
}
