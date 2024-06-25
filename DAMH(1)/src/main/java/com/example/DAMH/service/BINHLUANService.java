package com.example.DAMH.service;

import com.example.DAMH.model.BINHLUAN;
import com.example.DAMH.model.HOADON;
import com.example.DAMH.repository.BINHLUANRepository;
import com.example.DAMH.repository.HOADONRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BINHLUANService {

    @Autowired
    private BINHLUANRepository binhLuanRepository;

    @Autowired
    private HOADONRepository hoadonRepository;

    public BINHLUAN saveComment(int invoiceId, String content) {
        HOADON hoadon = hoadonRepository.findById(invoiceId).orElseThrow(() -> new IllegalArgumentException("Invalid invoice Id:" + invoiceId));

        BINHLUAN comment = new BINHLUAN();
        comment.setNoiDung(content);
        comment.setNgayBinhLuan(new Date());
        comment.setTrangThai(false); // Bình luận mới chưa duyệt
        comment.setHoadon(hoadon);

        BINHLUAN savedComment = binhLuanRepository.save(comment);

        hoadon.setBinhluan(savedComment); // Cập nhật hóa đơn với mã bình luận
        hoadonRepository.save(hoadon);

        return savedComment;
    }

    public List<BINHLUAN> getAllComments() {
        return binhLuanRepository.findAll();
    }
}