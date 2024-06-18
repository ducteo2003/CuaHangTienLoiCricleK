package com.example.DAMH.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;
    @Data
    @Embeddable
    public class CHITIETDATHANGKey implements Serializable {
        private int barcode;
        private int maDon;


        public CHITIETDATHANGKey() {}

        public CHITIETDATHANGKey(int barcode, int maDon) {
            this.barcode = barcode;
            this.maDon = maDon;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CHITIETDATHANGKey that = (CHITIETDATHANGKey) o;
            return Objects.equals(barcode, that.barcode) && Objects.equals(maDon, that.maDon);
        }

        @Override
        public int hashCode() {
            return Objects.hash(barcode, maDon);
        }
    }


