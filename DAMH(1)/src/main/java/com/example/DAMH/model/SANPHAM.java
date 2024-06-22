package com.example.DAMH.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sanpham")
public class SANPHAM {
    @Id
    private int barcode;
    private String tenSP;
    private String donViTinh; //hop,cai,chai,vv......
    private double donGia; //donGia=giaDat

    @ManyToOne
    @JoinColumn(name= "maNCC")
    private NHACUNGCAP ncc;

    @ManyToOne
    @JoinColumn(name= "maViTri")
    private VITRI vitri;

    @ManyToOne
    @JoinColumn(name= "maLoai")
    private LOAISP loaisp;

    @ManyToOne
    @JoinColumn(name= "maKM")
    private KHUYENMAI khuyenmai;




}
