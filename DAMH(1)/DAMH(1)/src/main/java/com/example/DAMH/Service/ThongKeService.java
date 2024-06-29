package com.example.DAMH.Service;

import com.example.DAMH.model.BINHLUAN;
import com.example.DAMH.repository.BINHLUANRepository;
import com.example.DAMH.repository.CHITIETHOADONRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThongKeService {

    @Autowired
    private CHITIETHOADONRepository chiTietHoaDonRepository;

    @Autowired
    private BINHLUANRepository binhLuanRepository;

    public List<Object[]> getWeeklySales() {
        return chiTietHoaDonRepository.findWeeklySales();
    }

    public List<Object[]> getMonthlySales() {
        return chiTietHoaDonRepository.findMonthlySales();
    }

    public List<BINHLUAN> getAllComments() {
        return binhLuanRepository.findActiveComments();
    }
}
