package com.example.DAMH.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "binhluan")
public class BINHLUAN {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maBinhLuan;
    private String noiDung;
    private Date ngayBinhLuan;
    private boolean trangThai;

    @ManyToOne
    @JoinColumn(name = "maHD", referencedColumnName = "maHD")
    private HOADON hoadon;
}
