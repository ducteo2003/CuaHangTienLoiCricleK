package com.example.DAMH.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.format.DateTimeFormatter;
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
    private DateTimeFormatter ngayBinhLuan;
    private boolean trangThai; //trạng thái có 2 giá trị: 1.đã duyệt 2.từ chối

    @OneToOne
    @JoinColumn(name = "maHD")
    private HOADON hoadon;
}
