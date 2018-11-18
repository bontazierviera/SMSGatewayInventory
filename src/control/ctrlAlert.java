package control;

import form.MenuUtama;
import javax.swing.JOptionPane;


public class ctrlAlert {
    /** Creates a new instance of ctrlAlert */
    MenuUtama mUtama ;
    ctrlComponent ctrlComp ;
    private JOptionPane JOP = new JOptionPane();
    
    public ctrlAlert(MenuUtama mUtama) {    
        this.mUtama = mUtama;
        ctrlComp = new ctrlComponent();
    }
    
    public static String ERROR = "ERROR";
    public static String INFO = "INFORMASI";
    public static String WARNING = "PERINGATAN";
    public static String CONFIRM = "KONFIRMASI";
    public static String ERROR_GEN_ID = "<html><body align=center><font color=red><b>" +
                                        "GAGAL DALAM PROSES GENERATE ID!<BR> ERROR @ : " +
                                        "</b></font></body></html>";
    
    public static String NO_FILE_EXIST      = "<html><b> FILE TIDAK DITEMUKAN </b></html>";
    public static String NO_PORT            = "<html><b> TENTUKAN PORT UNTUK KONEKSI ! </b></html>";
    public static String ERROR_NOSERVER_CON = "<html><head><body align=center><b><font color=red> " +
                                              "Server Belum Terkoneksi Ke Alat!<br>" +
                                              "Silahkan Anda Koneksikan Melalui Pengaturan SMS!<br>" +
                                              "Proses Pengiriman SMS Gagal, Silahkan Ulangi Lagi Setelah Server Terkoneksi!" +
                                              "</font></b></body></head></html>";
    public static String ERROR_PORT_IN_USE  = "<html><b> GAGAL MELAKUKAN KONEKSI<br>" +
                                              "PORT SEDANG DIGUNAKAN SISTEM LAIN </b></html>";
    public static String KONEKSI_OPEN       = "<html><head><body align=center>" +
                                              "<Font Color=RED><b> KONEKSI KE ALAT BELUM DI TUTUP,<br>" +
                                              "HARAP KONEKSI DI TUTUP TERLEBIH DAHULU !!!  </b></Font>" +
                                              "</body></head></html>" ;  
    public static String EMPTY_SMS          = "PESAN MASUK KOSONG, DATA SMS MASUK TIDAK DAPAT DISIMPAN !";
    public static String EMPTY_BROADCAST    = "<html><head><body align=center>SILAHKAN ISI PESAN YANG AKAN DI KIRIM !</body></head></html>" ;
    public static String EMPTY_DESTINATION  = "<html><head><body align=center>SILAHKAN PILIH TUJUAN PENGIRIMAN SMS !</body></head></html>" ;
    public static String ERROR_SEND_SMS     = "<html><Font Color=RED><b> PROSES PENGIRIMAN PESAN GAGAL !</b></Font></html>";
    public static String WRONG_FORMAT       = "FORMAT SMS YANG ANDA KIRIMKAN SALAH !";
    public static String ERROR_SAVE_IN      = "<html><b> SMS MASUK GAGAL DI SIMPAN KE DATABASE !</b></html>";   
    public static String ERROR_SAVE_OUTBX   = "<html><b> SMS KELUAR GAGAL DI SIMPAN KE DATABASE !</b></html>";  

    public JOptionPane getDataKosongMessage(){
        JOP.showMessageDialog(mUtama,"<html><body align=center><font color=red><b>" +
                                     "MAAF, DATA TIDAK BOLEH KOSONG <BR>ISI DATA DENGAN BENAR ! " +
                                     "</b></font></body></html>",
                              WARNING,JOptionPane.WARNING_MESSAGE,ctrlComp.getIcon("/image/w.png"));
        return JOP;
    }
     
    public JOptionPane getInfoMessage(String pesan, String title){
        JOP.showMessageDialog(mUtama,pesan,title,JOptionPane.INFORMATION_MESSAGE,ctrlComp.getIcon("/image/i.png"));
        return JOP;
    }
    
    public JOptionPane getWarningMessage(String pesan,String title){
        JOP.showMessageDialog(mUtama,pesan,title,JOptionPane.WARNING_MESSAGE,ctrlComp.getIcon("/image/w.png"));
        return JOP;
    }
    
    public JOptionPane getErrorMessage(String pesan,String title){
        JOP.showMessageDialog(mUtama,pesan,title,JOptionPane.ERROR_MESSAGE,ctrlComp.getIcon("/image/e.png"));
        return JOP;
    }
    
    public JOptionPane getSuccessExecuteQuery(String ID, String ket){
        JOP.showMessageDialog(mUtama, "<html><body align=center><font color=green><b>" +
                                      "DATA DENGAN ID : <font color=red>"+ID+"</font> BERHASIL DI"+ket+" !"+
                                      "</b></font></body></html>",
                              INFO,0,ctrlComp.getIcon("/image/i.png"));
        return JOP;
    }
    
    public JOptionPane getErrorExecuteQuery(String ID, String ket, String error){
        JOP.showMessageDialog(mUtama, "<html><body align=center><font color=red><b>" +
                                      "DATA DENGAN ID : <font color=green>"+ID+"</font> GAGAL DI"+ket+" !<br>"+
                                      "ERROR @ : "+error+"</b></font></body></html>",
                              ERROR,0,ctrlComp.getIcon("/image/e.png"));
        return JOP;
    }
    
    public JOptionPane getErrorExecuteQuerySelect(String error){
        JOP.showMessageDialog(mUtama, "<html><body align=center><font color=red><b>" +
                                      "GAGAL MELAKUKAN PROSES EXECUTE QUERY SELECT !<br>"+
                                      "ERROR @ : "+error+"</b></font></body></html>",
                              ERROR,0,ctrlComp.getIcon("/image/e.png"));
        return JOP;
    }
    
    public boolean KONFIRMASI( String ID,String ket){
        int tanya = JOptionPane.showConfirmDialog(mUtama,"<html><body align=center><font color=green><b>" +
                                      "APAKAH DATA DENGAN ID : <font color=red ><b> "+ID+" </b> </font>" +
                                      "INI INGIN DI "+ket+" ? </b></font></body></html>",
                                CONFIRM,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        
        if(tanya == JOptionPane.OK_OPTION)
            return true;
        else 
            return false;
    }
}
