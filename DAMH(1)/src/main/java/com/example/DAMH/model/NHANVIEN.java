package com.example.DAMH.model;
import jakarta.persistence.*;
import lombok.*;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nhanvien")
public class NHANVIEN {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maNV;
    private String tenNV;
    private String email;
    private Long namSinh;
    private boolean gioiTinh;
    private String tenDangNhap;
    private String matKhau;

    @ManyToOne
    @JoinColumn(name = "maChucVu")
    private CHUCVU chucvu;
}
