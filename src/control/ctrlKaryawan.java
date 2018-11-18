package control;

import form.MenuUtama;

public class ctrlKaryawan {
    MenuUtama mUtama;
    /** Creates a new instance of ctrlPelanggan */
    public ctrlKaryawan() {    }
    
    public void setIdKaryawan(String idKaryawan){
        this.idKaryawan = idKaryawan;
    }
    public void setNamaKaryawan(String nmKaryawan){
        this.nmKaryawan = nmKaryawan;
    }
    public void setAlamat(String alamat){
        this.alamat = alamat;
    }
    public void setNoHp(String noHp){
        this.noHp = noHp;
    }   
    public void setJabatan(String jabatan){
        this.jabatan = jabatan;
    }
    
    
    public String gettIdKaryawan(){
        return noHp;
    }
    public String getIdKaryawan(){
        return idKaryawan;
    }
    public String getNamaKaryawan(){
        return nmKaryawan;
    }
    public String getNoHp(){
        return noHp;
    }
    public String getAlamat(){
        return alamat;
    }
    public String getJabatan(){
        return jabatan;
    }
   
    private String idKaryawan,nmKaryawan,alamat, noHp,jabatan;
}
