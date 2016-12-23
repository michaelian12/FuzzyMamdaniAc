/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.unikom.fuzzymamdaniac;

/**
 *
 * @author Michael
 */
public class NilaiKeanggotaan {
    
    /* nilai u */
    private double mU;
    
    /* nilai himpunan fuzzy */
    private String mHimpunanFuzzy;
    
    /* Membuat objek Luas Ruangan baru */
    public NilaiKeanggotaan(double u, String himpunanFuzzy) {
        mU = u;
        mHimpunanFuzzy = himpunanFuzzy;
    }
    
    /* mengembalikan nilai u */
    public double getU() {
        return mU;
    }
    
    /* mengembalikan nilai himpunan fuzzy */
    public String getHimpunanFuzzy() {
        return mHimpunanFuzzy;
    }
    
}
