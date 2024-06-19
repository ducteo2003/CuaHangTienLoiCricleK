package com.example.DAMH.model;
import jakarta.persistence.*;
import lombok.*;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "luukho")
public class LUUKHO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maLuuKho;
    @ManyToOne
    @JoinColumn(name="maPhieuLuu")
    private PHIEULUUKHO phieuluukho;
    
    @EmbeddedId
    @ManyToOne
    @JoinColumn(name="maKho")
    private KHO kho;
}
