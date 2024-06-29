package com.example.DAMH.model;

import java.util.List;

public class ProductOrderForm {
    private String diaChi;
    private String maKho;
    private List<ProductOrderDTO> products;

    // Getters and Setters

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMaKho() {
        return maKho;
    }

    public void setMaKho(String maKho) {
        this.maKho = maKho;
    }

    public List<ProductOrderDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductOrderDTO> products) {
        this.products = products;
    }
}
