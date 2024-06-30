package com.example.DAMH.repositoryAdmin;

import com.example.DAMH.model.BINHLUAN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface binhluanRepository extends JpaRepository<BINHLUAN, Integer> {

    @Query(value = "SELECT * FROM KHUYENMAI WHERE trang_thai = :trang_thai", nativeQuery = true)
    List<BINHLUAN> getallBLfalse(@Param("trang_thai") boolean trang_thai);

    @Query(value = "SELECT * FROM KHUYENMAI WHERE mahd = :mahd OR trang_thai =:mahd", nativeQuery = true)
    List<BINHLUAN> findbymahdortrangthai(@Param("mahd") int mahd);
}
