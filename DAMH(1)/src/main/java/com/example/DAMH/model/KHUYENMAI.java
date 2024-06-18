package com.example.DAMH.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "khuyenmai")
public class KHUYENMAI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maKM;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private int thoiHan; //ngayKetThuc-ngayBatDau

    @ManyToOne
    @JoinColumn(name= "maHinhThuc")
    private HINHTHUC hinhthuc;

}
