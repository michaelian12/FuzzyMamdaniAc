/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.unikom.fuzzymamdaniac;

import java.lang.Math;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Michael
 */
public class Fuzzy {
    /* variabel nilai keanggotaan */
    ArrayList<NilaiKeanggotaan> listLuasRuangan = new ArrayList<NilaiKeanggotaan>();
    ArrayList<NilaiKeanggotaan> listJumlahOrang = new ArrayList<NilaiKeanggotaan>();
    ArrayList<NilaiKeanggotaan> listSuhuOptimal = new ArrayList<NilaiKeanggotaan>();
    double[] a = new double[3];
    double[] M = new double[4];
    double[] A = new double[4];
    double Z;
    
    ArrayList<NilaiKeanggotaan> arrayZ = new ArrayList<NilaiKeanggotaan>();
    
    DecimalFormat decimal = new DecimalFormat(".###");
    
    public String hasilFuzzy(double luasRuangan, int jumlahOrang) {
        keanggotaanLuasRuangan(luasRuangan);
//        for (int i = 0; i < listLuasRuangan.size(); i++) {
//            JOptionPane.showMessageDialog(null, "u: " + listLuasRuangan.get(i).getU() + "nilai: " + listLuasRuangan.get(i).getHimpunanFuzzy());
//        }
        keanggotaanJumlahOrang(jumlahOrang);
//        for (int i = 0; i < listJumlahOrang.size(); i++) {
//            JOptionPane.showMessageDialog(null, "u: " + listJumlahOrang.get(i).getU() + "nilai: " + listJumlahOrang.get(i).getHimpunanFuzzy());
//        }
        keanggotaanSuhuOptimal();
//        for (int i = 0; i < listSuhuOptimal.size(); i++) {
//            JOptionPane.showMessageDialog(null, "u: " + listSuhuOptimal.get(i).getU() + "nilai: " + listSuhuOptimal.get(i).getHimpunanFuzzy());
//        }
        hitunga();
//        for (int i = 0; i < a.length; i++) {
//            JOptionPane.showMessageDialog(null, "a[" + i + "] = " + a[i]);
//        }
        hitungM();
//        for (int i = 0; i < M.length; i++) {
//            JOptionPane.showMessageDialog(null, "M[" + i + "] = " + M[i]);
//        }
        hitungA();
        hitungZ();
        
        /*nilai u Suhu optimal */
        double uSuhuOptimal;
            
        if ((Z >= 16) && (Z <= 20)) {
            uSuhuOptimal = Double.parseDouble(decimal.format(rumusTurun(16, 20, Z)));
            arrayZ.add(new NilaiKeanggotaan(uSuhuOptimal, "Dingin"));
            
            uSuhuOptimal = Double.parseDouble(decimal.format(rumusNaik(16, 21, Z)));
            arrayZ.add(new NilaiKeanggotaan(uSuhuOptimal, "Sedang"));
        } else if ((Z >= 16) && (Z <= 21)) {
            uSuhuOptimal = Double.parseDouble(decimal.format(rumusNaik(16, 21, Z)));
            arrayZ.add(new NilaiKeanggotaan(uSuhuOptimal, "Sedang"));
        } else if ((Z >= 21) && (Z <= 26)) {
            uSuhuOptimal = Double.parseDouble(decimal.format(rumusTurun(21, 26, Z)));
            arrayZ.add(new NilaiKeanggotaan(uSuhuOptimal, "Sedang"));
        }
        
        if (arrayZ.size() == 1) {
            return "Suhu optimal ruangan adalah " + Z + ", dengan nilai u " + arrayZ.get(0).getHimpunanFuzzy() + " = " + arrayZ.get(0).getU();
        } else {
            return "Suhu optimal ruangan adalah " + Z + ", dengan nilai u " + arrayZ.get(0).getHimpunanFuzzy() + " = " + arrayZ.get(0).getU()
                    + " dan nilai u " + arrayZ.get(1).getHimpunanFuzzy() + " = " + arrayZ.get(1).getU();
        }
    }
    
    /* menentukan nilai keanggotaan Luas Ruangan (input) */
    public void keanggotaanLuasRuangan(double x){
        /* nilai u Luas Ruangan */
        double uLuasRuangan;
        
        if ((x >= 20) && (x <= 35)) {
            uLuasRuangan = Double.parseDouble(decimal.format(rumusTurun(20, 35, x)));
            listLuasRuangan.add(new NilaiKeanggotaan(uLuasRuangan, "Kecil"));
            
            uLuasRuangan = Double.parseDouble(decimal.format(rumusNaik(20, 40, x)));
            listLuasRuangan.add(new NilaiKeanggotaan(uLuasRuangan, "Sedang"));
        } else if ((x >= 20) && (x <= 40)) {
            uLuasRuangan = Double.parseDouble(decimal.format(rumusNaik(20, 40, x)));
            listLuasRuangan.add(new NilaiKeanggotaan(uLuasRuangan, "Sedang"));
        } else if ((x >= 45) && (x <= 60)) {
            uLuasRuangan = Double.parseDouble(decimal.format(rumusTurun(40, 60, x)));
            listLuasRuangan.add(new NilaiKeanggotaan(uLuasRuangan, "Sedang"));
            
            uLuasRuangan = Double.parseDouble(decimal.format(rumusNaik(45, 60, x)));
            listLuasRuangan.add(new NilaiKeanggotaan(uLuasRuangan, "Besar"));
        } else if ((x >= 40) && (x <= 60)) {
            uLuasRuangan = Double.parseDouble(decimal.format(rumusTurun(40, 60, x)));
            listLuasRuangan.add(new NilaiKeanggotaan(uLuasRuangan, "Sedang"));
        }
    }
    
    /* menentukan nilai keanggotaan Jumlah Orang (input) */
    public void keanggotaanJumlahOrang(int x){
        /* nilai u Jumlah Orang */
        double uJumlahOrang;
        
        if ((x >= 3) && (x <= 5)) {
            uJumlahOrang = Double.parseDouble(decimal.format(rumusTurun(3, 5, x)));
            listJumlahOrang.add(new NilaiKeanggotaan(uJumlahOrang, "Sedikit"));
            
            uJumlahOrang = Double.parseDouble(decimal.format(rumusNaik(3, 6.5, x)));
            listJumlahOrang.add(new NilaiKeanggotaan(uJumlahOrang, "Sedang"));
        } else if ((x >= 3) && (x <= 6.5)) {
            uJumlahOrang = Double.parseDouble(decimal.format(rumusNaik(3, 6.5, x)));
            listJumlahOrang.add(new NilaiKeanggotaan(uJumlahOrang, "Sedang"));
        } else if ((x >= 7) && (x <= 10)) {
            uJumlahOrang = Double.parseDouble(decimal.format(rumusTurun(6.5, 10, x)));
            listJumlahOrang.add(new NilaiKeanggotaan(uJumlahOrang, "Sedang"));
            
            uJumlahOrang = Double.parseDouble(decimal.format(rumusNaik(7, 10, x)));
            listJumlahOrang.add(new NilaiKeanggotaan(uJumlahOrang, "Banyak"));
        } else if ((x >= 6.5) && (x <= 10)) {
            uJumlahOrang = Double.parseDouble(decimal.format(rumusTurun(6.5, 10, x)));
            listJumlahOrang.add(new NilaiKeanggotaan(uJumlahOrang, "Sedang"));
        }
    }
    
    /* menentukan nilai keanggotaan Suhu Optimal (output) */
    public void keanggotaanSuhuOptimal() {
        /* nilai alfa predikat */
        double alfaPredikat;
        
        /* nilai himpunan fuzzy */
        String himpunanFuzzy;
        
        for (int i = 0; i < listLuasRuangan.size(); i++) {
            for (int j = 0; j < listJumlahOrang.size(); j++) {
                alfaPredikat = Math.min(listLuasRuangan.get(i).getU(), listJumlahOrang.get(j).getU());
                himpunanFuzzy = aturanPengetahuanDasar(listLuasRuangan.get(i).getHimpunanFuzzy(), listJumlahOrang.get(j).getHimpunanFuzzy());
                listSuhuOptimal.add(new NilaiKeanggotaan(alfaPredikat, himpunanFuzzy));
            }
        }
    }
    
    /* menghitung nilai a */
    public void hitunga() {
        a[0] = Double.parseDouble(decimal.format(aTurun(16, 20, listSuhuOptimal.get(0).getU())));
        a[1] = Double.parseDouble(decimal.format(aTurun(16, 20, listSuhuOptimal.get(1).getU())));
        a[2] = Double.parseDouble(decimal.format(aTurun(21, 26, listSuhuOptimal.get(3).getU())));
    }
    
    /* menghitung nilai M */
    public void hitungM() {
        M[0] = Double.parseDouble(decimal.format(((listSuhuOptimal.get(0).getU() / 2) * Math.pow(a[0], 2)) - ((listJumlahOrang.get(0).getU() / 2) * Math.pow(16, 2))));
        M[1] = Double.parseDouble(decimal.format(((2.5 * Math.pow(a[1], 2)) - (0.083 * Math.pow(a[1], 3))) - ((2.5 * Math.pow(a[0], 2)) - (0.083 * Math.pow(a[0], 3)))));
        M[2] = Double.parseDouble(decimal.format(((listSuhuOptimal.get(1).getU() / 2) * Math.pow(a[2], 2)) - ((listJumlahOrang.get(1).getU() / 2) * Math.pow(a[1], 2))));
        M[3] = Double.parseDouble(decimal.format(((2.6 * Math.pow(26, 2)) - (0.067 * Math.pow(26, 3))) - ((2.6 * Math.pow(a[2], 2)) - (0.067 * Math.pow(a[2], 3)))));
    }
    
    /* menghitung nilai A */
    public void hitungA() {        
        A[0] = Double.parseDouble(decimal.format((a[0] - 16) * (listSuhuOptimal.get(0).getU())));
        A[1] = Double.parseDouble(decimal.format((listSuhuOptimal.get(1).getU() + listSuhuOptimal.get(0).getU()) * (a[1] - a[0]) / 2));
        A[2] = Double.parseDouble(decimal.format((a[2] - a[1]) * (listSuhuOptimal.get(1).getU())));
        A[3] = Double.parseDouble(decimal.format((26 - a[2]) * (listSuhuOptimal.get(1).getU()) / 2));
    }
    
    public void hitungZ() {
        double totalM = 0, totalA = 0;
        
        for (double value : M) {
//            JOptionPane.showMessageDialog(null, value);
            totalM += value;
        }
//        JOptionPane.showMessageDialog(null, totalM);
        
        for (double value : A) {
//            JOptionPane.showMessageDialog(null, value);
            totalA += value;
        }
//        JOptionPane.showMessageDialog(null, totalA);
        
        Z = Double.parseDouble(decimal.format(totalM / totalA));
    }
    
    /**
     * 
     * Knowledge Base 
     */
    public String aturanPengetahuanDasar(String luasRuangan, String jumlahOrang) {
        /* nilai himpunan fuzzy */
        String suhu = "";
        
        if ((luasRuangan == "Kecil") && (jumlahOrang == "Sedikit")) {
            suhu = "Dingin";
        } else if ((luasRuangan == "Kecil") && (jumlahOrang == "Sedang")) {
            suhu = "Sedang";
        } else if ((luasRuangan == "Kecil") && (jumlahOrang == "Banyak")) {
            suhu = "";
        } else if ((luasRuangan == "Sedang") && (jumlahOrang == "Sedikit")) {
            suhu = "Dingin";
        } else if ((luasRuangan == "Sedang") && (jumlahOrang == "Sedang")) {
            suhu = "Sedang";
        } else if ((luasRuangan == "Sedang") && (jumlahOrang == "Banyak")) {
            suhu = "";
        } else if ((luasRuangan == "Besar") && (jumlahOrang == "Sedikit")) {
            suhu = "";
        } else if ((luasRuangan == "Besar") && (jumlahOrang == "Sedang")) {
            suhu = "";
        } else if ((luasRuangan == "Besar") && (jumlahOrang == "Banyak")) {
            suhu = "";
        }
        
        return suhu;
    }
    
    /**
     * 
     * Kumpulan Rumus
     */
    /* rumus a saat grafik turun */
    public double aTurun(double a, double b,double alfaPredikat) {
        return b - (b-a) * alfaPredikat;
    }
    
    /* rumus a saat grafik turun */
    public double aNaik(double a, double b, double alfaPredikat) {
        return a + (b-a) * alfaPredikat;
    }
    
    /* rumus saat grafik turun */
    public double rumusTurun(double a, double b, double x) {
       return  (b - x) / (b - a);
    }
    
    /* rumus saat grafik naik */
    public double rumusNaik(double a, double b, double x) {
       return  (x - a) / (b - a);
    }
}
