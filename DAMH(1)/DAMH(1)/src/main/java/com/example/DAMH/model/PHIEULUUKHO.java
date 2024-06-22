package com.example.DAMH.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


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
    @JoinColumn(name="maCTDH")
    private CHITIETDATHANG chitietdathang;

    @OneToMany(mappedBy = "phieuluukho", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LUUKHO> luukhos;
}
