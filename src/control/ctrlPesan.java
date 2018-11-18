package control;

import form.MenuUtama;
import form.frmServer;
import java.sql.Connection;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class ctrlPesan {
    MenuUtama mUtama;
    frmServer frmServer;
    ctrlKaryawan ctrlPlg ;
    ctrlValidasi ctrlValid ;
    ctrlManipulasiData ctrlManData;
    ctrlComponent ctrlComp;
    ctrlIDgenerator ctrlID;
    
    
    

    /** Creates a new instance of ctrlPesan */
    public ctrlPesan(MenuUtama mUtama, frmServer frmServer) {
        this.mUtama = mUtama;
        this.frmServer = frmServer;
        
        ctrlPlg   = new ctrlKaryawan();
        ctrlValid = new ctrlValidasi(mUtama);
        ctrlManData = new ctrlManipulasiData(mUtama);
        ctrlComp = new ctrlComponent();
        ctrlID = new ctrlIDgenerator();
    }
    
    public void setNoHp(String noHp){
        this.noHp = noHp;
    }
    public void setIsiPesan(String isiPesan){
        if(isiPesan.matches("(\\w|\\d|\\W)*"))
            this.isiPesan = isiPesan.toUpperCase();
        else
            this.isiPesan = isiPesan.toUpperCase().substring(8);
    }
    public String getNoHp(){
        return noHp;
    }
    public String getIsiPesan(){
        return isiPesan;
    }
    
     public void prosesPesanMasuk(){
      wktSMS = ctrlComp.getWaktuFULL();
      wktInbox = ctrlComp.getWaktuJAM();
      wktOutbox = ctrlComp.getWaktuJAM();
      wktRegistrasi = ctrlComp.getWaktuJAM();
      tglMsk = ctrlComp.getWaktuYMD();
      tglKirim = ctrlComp.getWaktuYMD();
      tglRegistrasi = ctrlComp.getWaktuYMD();
      
      
      frmServer.setList("SMS MASUK Dari : "+noHp+" Isi Pesan : "+isiPesan, 300);
      SMSBalasan = ""; 
      String re[]= isiPesan.split(" ");
        if(ctrlValid.cekRegistrasi(noHp)){
            idReg = ctrlValid.getIDRegistrasi();
            idKaryawan = ctrlValid.getIDKaryawan();
            nmKaryawan = ctrlValid.getNamaKaryawan();
            if(ctrlValid.cekPesan(isiPesan)){    
                String[]ar=isiPesan.split(" ");
                if(isiPesan.startsWith("STOK") && ar.length==2){                
                    judul = "STOK";
                    String idBrgCek =  ar[1];
                    SMSBalasan = smsCekQty(idBrgCek); 
                }else if(isiPesan.startsWith("PO")&& ar.length==2){
                    judul = "PURCHASE ORDER";
                    String idbrg = ar[1];
                    SMSBalasan = smsCekPurchaseOrder(idbrg);
                }else if(isiPesan.startsWith("JDWLMASUK")&& ar.length==2){
                    judul = "JADWAL MASUK";
                    String jdM = ar[1];
                    SMSBalasan = smsCekJadwalMasuk(jdM);
                }else if(isiPesan.startsWith("SUPPLIER")&& ar.length==1){
                    judul = "DATA SUPPLIER";
                    SMSBalasan = smsCekSupplier();                    
                }else if(isiPesan.startsWith("DETAILPO")&& ar.length==2){
                    judul = "DETAIL PO";
                    String detPO = ar[1];
                    SMSBalasan = smsCekDetailPO(detPO);
                }else if(isiPesan.startsWith("INFOBRG")&& ar.length==2){
                    judul = "INFO BARANG";
                    String inBrg = ar[1];
                    SMSBalasan = smsInfoBarang(inBrg);
                }else if(isiPesan.startsWith("INFOIDBRG")&& ar.length==1){
                    judul = "INFO ID BARANG";
                    SMSBalasan = smsInfoIdBarang();
                }else if(isiPesan.startsWith("UNREG")&& ar.length==2){
                    judul = "UNREGISTER";
                    String idReg = ar[1];
                    SMSBalasan = Unregistrasi(idReg);
                }else if(isiPesan.startsWith("INFO")&& ar.length==1){
                    judul = "INFO";
                    SMSBalasan = "Daftar Format SMS : \n" +
                                "1. STOK<spasi>IDBRG\n"+
                                "2. PO<spasi>IDBRG\n"+
                                "3. JDWLMASUK<spasi>NOPO\n"+
                                "4. SUPPLIER\n"+
                                "5. DETAILPO<spasi>NOPO\n"+
                                "6. INFOBRG<spasi>IDBRG\n"+
                                "6. INFOIDBRG\n"+
                                "7. UNREG";
                }else{
                    SMSBalasan = "Format SMS anda salah. Silahkan ketik : INFO untuk mengetahui Format SMS yang benar.";
                    judul = "SALAH FORMAT";
                }
                 System.out.println(SMSBalasan);
                 System.out.println("noHp : "+noHp+" id_karyawan : "+idKaryawan+" Nama : "+nmKaryawan);              
            }else{
                    SMSBalasan = "Format SMS anda salah.";
                    judul = "SALAH FORMAT";
                }  
  
        }else if(isiPesan.startsWith("REG")&& re.length==2){
                    judul = "REGISTRASI";
                    String reg = re[1];
                    SMSBalasan = Registrasi(reg);
                 }else{
                    SMSBalasan = "Maaf,Anda Bukan Karyawan PT.Palito Nusantara Atau Anda Belum Terdaftar! \n"+
                                 "Silahkan Ketik : REG<spasi>IDKRYWN";
                    judul = "BELUM TERDAFTAR";
                 }
            
           
       //  JOptionPane.showMessageDialog(null,"Isi Balasan : "+SMSBalasan);
        System.out.println("Balasan : "+SMSBalasan+" - Length : "+SMSBalasan.length());  
         if(!SMSBalasan.equals("")){            
            // buat test tanpa hp  
            frmServer.KirimSMS(noHp, SMSBalasan);
              System.out.println("Test Kirim Balasan !");
            frmServer.setList("Isi Balasan : "+SMSBalasan, 300);
        }    
        simpanPesanMasuk(noHp, isiPesan);
        frmServer.addDataTabelSMSMasuk(noInbox,idReg, noHp, wktSMS, judul, isiPesan, SMSBalasan); 
     }

private String Registrasi(String reg){
        idReg = ctrlID.getIDRegistrasi();
        System.out.println(reg);
        String[] rg;
            
        Pattern pt = Pattern.compile(" ");
        rg = pt.split(reg);
        
        String idKar = rg[0];
        System.out.println("ID Karyawan : "+idKar);        
        simpanRegistrasi(idReg,wktRegistrasi,noHp,tglRegistrasi,idKar);
        System.out.println("Berhasil Melakukan Registrasi untuk nomor : "+noHp);
        
        String respon = "Terima Kasih Anda dengan ID Karyawan: "+idKar+" ,sudah melakukan proses Registrasi "
                + "Ketik UNREG untuk keluar registrasi";
        
        return respon;
}

private String Unregistrasi(String unreg) {
idReg = "";
String[] rg;
Pattern pt = Pattern.compile(" ");
rg = pt.split(unreg);  
// String SQL ="DELETE FROM registrasi WHERE id_registrasi='"+idReg+"'";
    //            ctrlManData.EXECUTE_QUERY(SQL);      
hapusRegistrasi(idReg);
       System.out.println("Berhasil Unregistrasi");
       String respon = "Terima Kasih, Anda sudah tidak terdaftar di Sistem Kami. \n"
                + "Silahkan Ketik REG<spasi>IDKRYWN untuk Mendaftar Kembali.";
        
        return respon;
    }

public String simpanRegistrasi(String idReg,String wktRegistrasi,String noHp,String tglRegistrasi,String idKar){
    blz="";   
    String SQL ="INSERT INTO registrasi VALUES('"+idReg+"','"+wktRegistrasi+"'," +
                 "'"+noHp+"','"+tglRegistrasi+"','"+idKar+"');";
                 ctrlManData.EXECUTE_QUERY_TABLE(SQL);
    return blz;
     }


 public String hapusRegistrasi(String idReg){         
    blz="";     
     String SQL ="DELETE FROM registrasi WHERE id_registrasi='"+idReg+"'";
     ctrlManData.EXECUTE_QUERY_TABLE(SQL);
     return blz;    
     }    


      private String smsCekQty(String idBrgCek) {
       String query="select `qty` from barang where id_brg ='"+idBrgCek+"'";   
       //System.out.println(query);
       String data="";
        try{
            java.sql.Connection cn = new ctrlKoneksiDB().openConnection();
             Statement st = cn.createStatement();

             java.sql.ResultSet rs = st.executeQuery(query);
             if(rs.next()){
                 data = rs.getString("qty");
             }
             st.close(); cn.close();
        }catch (Exception e) {}
        
        if(data.length()>0){
        data="ID Barang: "+idBrgCek+" dengan quantity: "+data+" buah";
        }
        else{
        data="ID barang: "+idBrgCek+" belum terdaftar !";
        }
        return data;
    } 
      
      private String smsCekPurchaseOrder(String idBrgCek) {
       String query="select * from det_po where id_brg ='"+idBrgCek+"'";   
      // System.out.println(query);
       String data="";
        try{
            java.sql.Connection cn = new ctrlKoneksiDB().openConnection();
             Statement st = cn.createStatement();

             java.sql.ResultSet rs = st.executeQuery(query);
             while(rs.next()){
                 data =data+ rs.getString("no_po")+", ";
             }
             st.close(); cn.close();
        }catch (Exception e) {}
        
        if(data.length()>0){
        data="ID barang: "+idBrgCek+" dengan No.PO: "+data+".";
        }
        else{
        data="ID barang: "+idBrgCek+"  belum terdaftar !";
        }
        return data;
    }

    private String smsCekJadwalMasuk(String jdM) {
      String query="select tgl_masuk from po where no_po ='"+jdM+"'";   
       System.out.println(query);
       String data="";
        try{
            java.sql.Connection cn = new ctrlKoneksiDB().openConnection();
             Statement st = cn.createStatement();

             java.sql.ResultSet rs = st.executeQuery(query);
             if(rs.next()){
                 data = rs.getString("tgl_masuk");
             }
             st.close(); cn.close();
        }catch (Exception e) {}
        
        if(data.length()>0){
        data="No. PO: "+jdM+" dengan tanggal masuk: "+data+" ";
        }
        else{
        data="No. PO: "+jdM+" belum terdaftar !";
        }
        return data;
    }
    
    private String smsCekSupplier() {
        String query="select id_supplier,nama_supplier from supplier";   
       //System.out.println(query);
       String data="";
        try{
            java.sql.Connection cn = new ctrlKoneksiDB().openConnection();
             Statement st = cn.createStatement();

             java.sql.ResultSet rs = st.executeQuery(query);
             while(rs.next()){
                 data =data+ rs.getString(1)+": "+rs.getString(2)+", ";
             }
             st.close(); cn.close();
        }catch (Exception e) {}
        
        if(data.length()>0){
        data="Daftar Supplier yang masih dalam kontrak adalah : "+data+".";
        }
        else{
        data="Data Kosong !";
        }
        return data;
    }  
   
 private String smsCekDetailPO(String detPO) {
String query="select * from po where no_po ='"+detPO+"'";    
       System.out.println(query);
       String data="";
        try{
            java.sql.Connection cn = new ctrlKoneksiDB().openConnection();
            Statement st = cn.createStatement();

             java.sql.ResultSet rs = st.executeQuery(query);
               while(rs.next()){                
                    data = data+ "tgl PO: "+rs.getString(2)
                           +", tgl Masuk: "+rs.getString(3)
                           +", Nama Brg: "+rs.getString(4)
                           +", qty: "+rs.getString(5)
                           +", id Supplier: "+rs.getString(6);
                    System.out.println(data);
                    }
            st.close(); cn.close();
        }catch (Exception e) {}
        
        if(data.length()>0){
        data="No. PO: "+detPO+" dengan "+data+".";
        System.out.println(data);
        }
        else{
        data="No. PO: "+detPO+" belum terdaftar !";
        }
        return data;
    }
 
 
 private String smsInfoBarang(String idBrg) {
String query="select * from barang where id_brg ='"+idBrg+"'";   
       System.out.println(query);
       String data="";
        try{
            java.sql.Connection cn = new ctrlKoneksiDB().openConnection();
             Statement st = cn.createStatement();

             java.sql.ResultSet rs = st.executeQuery(query);
             while(rs.next()){
                 data =data+ "Nama Barang="+rs.getString(2)+", quantity="+rs.getString(3);
             }
             st.close(); cn.close();
        }catch (Exception e) {}
        
        if(data.length()>0){
        data="ID Barang: "+idBrg+" dengan "+data+".";
        }
        else{
        data="ID Barang: "+idBrg+"  belum terdaftar !";
        }
        return data;
    }  
   
  private String smsInfoIdBarang() {
String query="select id_brg from barang" ;   
       System.out.println(query);
       String data="";
        try{
            java.sql.Connection cn = new ctrlKoneksiDB().openConnection();
             Statement st = cn.createStatement();

             java.sql.ResultSet rs = st.executeQuery(query);
             while(rs.next()){
                 data =data+ rs.getString("id_brg")+", ";
             }
             st.close(); cn.close();
        }catch (Exception e) {}
        
        if(data.length()>0){
        data="Info ID Barang: "+data+".";
        }
        else{
        data="Data Kosong";
        }
        return data;
    } 
 
    public void simpanPesanMasuk(String noHp, String isiPesan){
        noInbox = ctrlID.getIDInbox();
        String SQL = "INSERT INTO sms_req VALUES('"+noInbox+"','"+judul+"','"+tglMsk+"','"+wktInbox+"','"+isiPesan+"','"+noHp+"','"+idKaryawan+"');";
       
        if(ctrlManData.EXECUTE_QUERY(SQL, noInbox, "SIMPAN", false)){
            frmServer.setList("SMS Masuk Dari : "+noHp+" BERHASIL DISIMPAN Ke Inbox !", 300);
        }else{
             //System.out.println("SQL="+SQL);
            frmServer.setList("SMS Masuk Dari : "+noHp+" GAGAL DISIMPAN Ke Inbox !", 300);
            return;
        }        
        simpanPesanBalasan(noHp,noInbox);   
    }
    
    public void simpanPesanBalasan(String noHp,String noOut ){
        noOutbox = ctrlID.getIDOutbox();
        String SQL = "INSERT INTO balas_req VALUES('"+noOutbox+"','"+tglKirim+"','"+wktOutbox+"','"+SMSBalasan+"','"+noHp+"');";
        if(ctrlManData.EXECUTE_QUERY(SQL, noOutbox, "SIMPAN", false)){
            frmServer.setList("SMS Balasan Untuk : "+noHp+" BERHASIL DISIMPAN Ke Outbox !", 300);
        }else{            
            frmServer.setList("SMS Balasan Untuk : "+noHp+" GAGAL DISIMPAN Ke Outbox !", 300);
        return;
        }
        
    }   
     
    private String idReg="", noHp="", isiPesan="",SMSBalasan="",nmKaryawan="",idKaryawan="",wktRegistrasi,tglRegistrasi,idRegistrasi,
                   wktSMS="", judul="", noInbox="", noOutbox="",wktInbox="",wktOutbox="", tglMsk, tglKirim, blz="";    
    private String idAdmin="", namaAdmin="", userAdmin="", passAdmin="";

  
}
    