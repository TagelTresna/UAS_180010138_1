package com.va181.tresna;

import java.util.Date;

public class Buku {
    private int idBuku;
    private String judul;
    private Date tanggal;
    private String gambar;
    private String penulis;
    private String isiBuku;
    private String link;
    public Buku(int idBuku, String judul_buku, Date tanggal, String gambar, String penulis, String isiBuku, String link) {
        this.idBuku = idBuku;
        this.judul = judul_buku;
        this.tanggal = tanggal;
        this.gambar = gambar;
        this.penulis = penulis;
        this.isiBuku = isiBuku;
        this.link = link;
    }

    public int getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(int idBuku) {
        this.idBuku = idBuku;
    }

    public String getJudul_buku() {
        return judul;
    }

    public void setJudul(String judul_buku) {
        this.judul = judul;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul_buku(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getIsiBuku() {
        return isiBuku;
    }

    public void setIsiBuku(String isiBuku) {
        this.isiBuku = isiBuku;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getidBuku() {
        return idBuku;
    }

    public String getjudul() {
        return judul;
    }

    public Date gettanggal() {
        return tanggal;
    }

    public String getgambar() {
        return gambar;
    }

    public String getpenulis() {
        return penulis;
    }

    public String getisiBuku() {
        return isiBuku;
    }

    public String getLink() {
        return link;
    }
}

