package com.example.DAMH.model;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Date ngayBinhLuan;
    private boolean trangThai; //trạng thái có 2 giá trị: 1.đã duyệt 2.từ chối

    @OneToOne
    @JoinColumn(name = "maHD")
    private HOADON hoadon;
}
