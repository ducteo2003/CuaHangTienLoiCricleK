package com.example.DAMH.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phieuluukho", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"mactdh", "ma_don"})
})
public class PHIEULUUKHO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maPhieuLuu;
    private Date ngayTaoPhieu;

    @OneToOne
    @JoinColumn(name="mactdh", referencedColumnName = "maCTDH", nullable = false)
    private CHITIETDATHANG chitietdathang;

    @ManyToOne
    @JoinColumn(name="ma_don", referencedColumnName = "maDon", nullable = false)
    private DONDATHANG dondathang;

    @OneToMany(mappedBy = "phieuluukho", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LUUKHO> luukhos;
}
