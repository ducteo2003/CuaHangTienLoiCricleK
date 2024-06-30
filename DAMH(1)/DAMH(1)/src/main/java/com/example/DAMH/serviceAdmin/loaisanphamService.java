package com.example.DAMH.serviceAdmin;

import com.example.DAMH.model.LOAISP;
import com.example.DAMH.model.SANPHAM;
import com.example.DAMH.repositoryAdmin.loaisanphamRepository;
import com.example.DAMH.repositoryAdmin.sanphamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class loaisanphamService {
    @Autowired
    private loaisanphamRepository lspRepository;

    @Autowired
    private sanphamRepository spRepository;

    public List<LOAISP> Getall()
    {
        return lspRepository.findAll();
    }
    public LOAISP findByID(int id)
    {
        return lspRepository.findById(id).orElseThrow();
    }
    public void AddLSP(LOAISP lsp)
    {
        lspRepository.save(lsp);
    }
    public void DeleteLSP(int id)
    {
        LOAISP lsp = findByID(id);
        List<SANPHAM> sps = spRepository.findSanphamByIDLoai(id);

            for(SANPHAM sp : sps)
            {
                sp.setLoaisp(null);
            }
            spRepository.saveAll(sps);



        lspRepository.delete(lsp);
    }

    public void EditLSP(LOAISP lsp)
    {
        LOAISP lspEdit = findByID(lsp.getMaLoai());
        lspEdit.setTenLoai(lsp.getTenLoai());
        lspRepository.save(lspEdit);
    }
    public List<SANPHAM> getallSPinLoaisp(int id)
    {
        return spRepository.findSanphamByIDLoai(id);
    }
}
