package com.example.DAMH.model;

import java.util.Objects;

public class CHITIETHOADONKey {
    private int barcode;
    private int maHD;


    public CHITIETHOADONKey() {}

    public CHITIETHOADONKey(int barcode, int maHD) {
        this.barcode = barcode;
        this.maHD = maHD;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CHITIETHOADONKey that = (CHITIETHOADONKey) o;
        return Objects.equals(barcode, that.barcode) && Objects.equals(maHD, that.maHD);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barcode, maHD);
    }
}
