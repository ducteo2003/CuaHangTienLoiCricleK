<<<<<<< HEAD:DAMH(1)/DAMH(1)/src/main/java/com/example/DAMH/model/LUUKHO.java
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
    private int ID;

    @ManyToOne
    @JoinColumn(name = "maPhieuLuu", nullable = false)
    private PHIEULUUKHO phieuluukho;

    @ManyToOne
    @JoinColumn(name = "maKho", nullable = false)
    private KHO kho;

    // Các thuộc tính khác nếu cần
}
=======
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
>>>>>>> main:DAMH(1)/src/main/java/com/example/DAMH/model/LUUKHO.java
