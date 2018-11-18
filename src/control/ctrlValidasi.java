package control;

import form.MenuUtama;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ctrlValidasi {
    private boolean cek = false;
    MenuUtama mUtama;
    ctrlManipulasiData ctrlManData;
    
    /** Creates a new instance of ctrlValidasi */
    public ctrlValidasi(MenuUtama mUtama) { 
        this.mUtama = mUtama;
        ctrlManData = new ctrlManipulasiData(mUtama);
    }
    
    public boolean cekPesan(String isiSms) {
        cek = false;
        if(isiSms.equals("")||isiSms.matches("(\\d|\\W)+")){
            cek = false;        
        }
        else if(isiSms.matches("INFO")){
            cek = true;
        }
        else if(isiSms.matches("REG (\\w|\\s|W)*")){
            cek = true;
        }        
        else if(isiSms.matches("STOK (\\w|\\s|W)*")){
            cek = true;
        }
        else if(isiSms.matches("PO (\\w|\\s|W)*")){
            cek = true;
        }
        else if(isiSms.matches("JDWLMASUK (\\w|\\s|\\W)*")){
            cek = true;
        }
        else if(isiSms.matches("SUPPLIER (\\w|\\s|W)*")){
            cek = true;
        }
        else if(isiSms.matches("DETAILPO (\\w|\\s|W)*")){
            cek = true;
        }else if(isiSms.matches("INFOBRG (\\w|\\s|W)*")){
            cek = true;
        }
        else if(isiSms.matches("INFOIDBRG (\\w|\\s|W)*")){
            cek = true;
        }
        else if(isiSms.matches("UNREG (\\w|\\s|W)*")){
            cek = true;
        }
        else cek = false;
        
        return cek;
    }

String idRegistrasi;
    public boolean cekRegistrasi(String nohp){      
        idRegistrasi= ""; idKaryawan = "";
        cek = false;
        
        String SQL = "SELECT id_registrasi,id_karyawan FROM registrasi WHERE no_hp='"+nohp+"'";
        System.out.println(SQL);
        if(ctrlManData.EXECUTE_QUERY_SELECT(SQL)) {
            if(ctrlManData.getDataQuery().isEmpty()){
                idRegistrasi = "";
                cek = false;
            }else{
                idRegistrasi = ctrlManData.getDataQuery().get("id_registrasi").toString();
                idKaryawan = ctrlManData.getDataQuery().get("id_karyawan").toString();
                cek = true;
            }           
            System.out.println("ID Registrasi: "+idRegistrasi+"ID Karyawan: "+idKaryawan+" No : "+nohp);
            
        }else  cek =  false;
        
        ctrlManData.clearHasMap();
        return cek;
    }    
   public String getIDRegistrasi(){
        return idRegistrasi;
    }  
    
    String idKaryawan,nmKaryawan,noHp;
    public boolean cekKaryawan(String nohp){      
        idKaryawan = ""; nmKaryawan = "";
        cek = false;
        
        String SQL = "SELECT id_karyawan,nama FROM karyawan WHERE no_hp='"+nohp+"'";
        System.out.println(SQL);
        if(ctrlManData.EXECUTE_QUERY_SELECT(SQL)) {
            if(ctrlManData.getDataQuery().isEmpty()){
                idKaryawan = ""; noHp="";
                cek = false;
            }else{
                idKaryawan = ctrlManData.getDataQuery().get("id_karyawan").toString();
                nmKaryawan = ctrlManData.getDataQuery().get("nama").toString();
                cek = true;
            }           
            System.out.println("ID : "+idKaryawan+"Nama :"+nmKaryawan+" No : "+nohp);
            
        }else  cek =  false;
        
        ctrlManData.clearHasMap();
        return cek;
    }    
    
    public String getIDKaryawan(){
        return idKaryawan;
    }
    public String getNamaKaryawan(){
        return nmKaryawan;
    }
    
    public String getNoHp(){
        return noHp;
    }        

String idAdmin="", namaAdmin="",userAdmin="", passAdmin="";
    public boolean cekAdmin(String noHp) {
        idAdmin=""; namaAdmin=""; userAdmin=""; passAdmin="";
        cek = false;
        
        String SQL = "SELECT * FROM admin WHERE no_hp = '"+noHp+"'";
        if(ctrlManData.EXECUTE_QUERY_SELECT(SQL)) {
            if(ctrlManData.getDataQuery().isEmpty()){
                idAdmin = ""; namaAdmin="";
                cek = false;
            }else{
                namaAdmin = ctrlManData.getDataQuery().get("nm_admin").toString();
                userAdmin = ctrlManData.getDataQuery().get("username").toString();
                passAdmin = ctrlManData.getDataQuery().get("password").toString();
                cek = true;
            }           
            System.out.println(" namaAdmin : "+namaAdmin+
                               " userAdmin : "+userAdmin+" passAdmin : "+passAdmin);
            
        }else  cek =  false;
        
        ctrlManData.clearHasMap();
        return cek;
    }
    
    public boolean cekAdmin(String user, String pass) {
        idAdmin=""; namaAdmin=""; userAdmin=""; passAdmin="";
        cek = false;
        
        String SQL = "SELECT nm_admin FROM admin " +
                     "WHERE username='"+user+"' AND password='"+pass+"';";
        if(ctrlManData.EXECUTE_QUERY_SELECT(SQL)) {
            if(ctrlManData.getDataQuery().isEmpty()){
                idAdmin = ""; namaAdmin="";
                cek = false;
            }else{
                namaAdmin = ctrlManData.getDataQuery().get("nm_admin").toString();
                cek = true;
            }           
            System.out.println(" namaAdmin : "+namaAdmin+
                               " userAdmin : "+userAdmin+" passAdmin : "+passAdmin);
            
        }else  cek =  false;
        
        ctrlManData.clearHasMap();
        return cek;
    }
    
    public String getNoPO(){
        return noPO;
    }
    
    public String getNamaAdmin(){
        return namaAdmin;
    }
    public String getUsernameAdmin(){
        return userAdmin;
    }
    public String getPasswordAdmin(){
        return passAdmin;
    }

    public boolean cekPesanBarangIsNotSame2(String idbarang[],int index){
        cek = false;
	    if(idbarang.length>1){
           for(int i = 0; i<idbarang.length;i++){
              for(int j = i + 1 ; j<idbarang.length;j++){
                 if(idbarang[i].split(",")[index].equalsIgnoreCase(idbarang[j].split(",")[index-1]))
                    cek = false;
                 else
                    cek = true;
                    }
                }
            }else
                cek = true;
        return cek;
    }       
    
    String qty="", nmBarang= "";
 
String noPO= "",tglPO="",tglMsk="",idSupplier="";

 public String getTanggalPO(){
        return tglPO;
    }   
 public String getTanggalMasuk(){
        return tglMsk;
    }   
 public String getIdSupplier(){
        return idSupplier;
    }
 public String getNamaSupplier(){
        return nmSupplier;
    } 
 public String getAlamat(){
        return alamat;
    }
 public String getJadwalMasuk(){
        return tglMsk;
    }

 
 String nmSupplier="",alamat="";

 
 String idBarang="";
 
 
}
