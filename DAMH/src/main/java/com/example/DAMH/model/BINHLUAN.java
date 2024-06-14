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
    private byte trangThai; //trạng thái có 3 giá trị: 1.chờ duyệt 2.đã duyệt 3.từ chối

    @OneToOne
    @JoinColumn(name ="maHD")
    private HOADON hoadon;
}
