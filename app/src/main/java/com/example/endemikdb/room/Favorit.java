package com.example.endemikdb.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorit")
public class Favorit {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nama;
    private String foto;
    private String asal;
    private String status;
    private String latin;
    private String famili;
    private String genus;
    private String sebaran;
    private String deskripsi;

    public Favorit() {
    }

    public Favorit(String nama, String foto, String asal, String status) {
        this.nama = nama;
        this.foto = foto;
        this.asal = asal;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getAsal() {
        return asal;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLatin() {
        return latin;
    }

    public void setLatin(String latin) {
        this.latin = latin;
    }

    public String getFamili() {
        return famili;
    }

    public void setFamili(String famili) {
        this.famili = famili;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getSebaran() {
        return sebaran;
    }

    public void setSebaran(String sebaran) {
        this.sebaran = sebaran;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}