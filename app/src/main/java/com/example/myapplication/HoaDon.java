package com.example.myapplication;

import java.text.DecimalFormat;

public class HoaDon {
    private int ma,soPhong,donGia,soNgay;
    private String ten;

    public HoaDon(int soPhong, int donGia, int soNgay, String ten) {
        this.soPhong = soPhong;
        this.donGia = donGia;
        this.soNgay = soNgay;
        this.ten = ten;
    }

    public String getTongTien(){
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(donGia*soNgay);
    }

    public HoaDon() {
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public int getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(int soPhong) {
        this.soPhong = soPhong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getSoNgay() {
        return soNgay;
    }

    public void setSoNgay(int soNgay) {
        this.soNgay = soNgay;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
