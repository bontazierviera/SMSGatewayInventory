package control;
import form.MenuUtama;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;


public class ctrlManipulasiData {
    MenuUtama mUtama;
    ctrlKoneksiDB koneksi;
    ctrlAlert ctrlAlert;
    
    private Connection cn;
    private Statement st;
    private ResultSet rs;
    private String fieldData = "";
    private HashMap hasMap ;
    private boolean cek ;
    /** Creates a new instance of ctrlManipulasiData */
    public ctrlManipulasiData(MenuUtama mUtama) {
        this.mUtama = mUtama;        
        koneksi = new ctrlKoneksiDB();
        ctrlAlert = new ctrlAlert(mUtama);
        hasMap = new HashMap();
    }
    
    public boolean EXECUTE_QUERY(String query, String id, String ket, boolean flag){
        try {
             cn = koneksi.openConnection();
             st = cn.createStatement();
             
             st.executeUpdate(query);             
             st.close(); cn.close();
             
             if(flag)
                ctrlAlert.getSuccessExecuteQuery(id, ket);
             
             return true;
             
        } catch (Exception e) {
             ctrlAlert.getErrorExecuteQuery(id,ket,e.getMessage().toUpperCase());
             return false;
        }
    }

    public void EXECUTE_QUERY_TABLE(String query){
        System.out.println(query);
        try {
             cn = koneksi.openConnection();
             st = cn.createStatement();

             st.executeUpdate(query);
             st.close(); cn.close();
        } catch (Exception e) {
             ctrlAlert.getErrorMessage("ERROR QUERY TABLE "+e.getMessage().toUpperCase(),"ERROR");
        }
    }
    
    public boolean EXECUTE_QUERY_SELECT(String query){
        cek = false; 
        System.out.println("Size Before : "+hasMap.size());
        
        try {
             cn = koneksi.openConnection();
             st = cn.createStatement();
             
             ResultSet rs = st.executeQuery(query);    
             ResultSetMetaData rsMD = rs.getMetaData();
             
             System.out.println("SQL : "+query);
             
             int jmlKolom = rsMD.getColumnCount();
             hasMap = new HashMap();
             if(rs.next()){                 
                 for(int i=1; i <= jmlKolom; i++){
                      hasMap.put(rsMD.getColumnName(i),rs.getString(i));
                      System.out.println("ResultSet Ke : "+i+
                              " Nama Field "+rsMD.getColumnName(i)+
                              " Isi : "+rs.getString(i));
                 }
                 cek = true;
             }else cek = false;
             
             st.close(); cn.close();
             System.out.println("Size After : "+hasMap.size());
             return cek;
        } catch (Exception e) {       
             ctrlAlert.getErrorExecuteQuerySelect(e.getMessage().toUpperCase());
             return false;
        }
    }
    public void clearHasMap(){
        hasMap.clear();
        
    }
    public HashMap getDataQuery(){
        System.out.println("Size Return : "+hasMap.size());
        return hasMap;
    }
    public static String EXECUTE_QUERY(String query){
        String data="";
        try{
            java.sql.Connection cn = new ctrlKoneksiDB().openConnection();
             Statement st = cn.createStatement();

             java.sql.ResultSet rs = st.executeQuery(query);
             if(rs.next()){
                 data = rs.getString(1);
             }
             st.close(); cn.close();
              return data;
        }catch (Exception e) {
             javax.swing.JOptionPane.showMessageDialog(null,"ERROR EXECUTE QUERY , ERROR @ : "+e.getMessage());
             return null;
        }      
    }
    
    public static String[] EXECUTE_QUERY_CARI(String query){
       try {
             java.sql.Connection cn = new ctrlKoneksiDB().openConnection();
             Statement st = cn.createStatement();
             
             java.sql.ResultSet rs = st.executeQuery(query); 
             rs.last();
             int rec = rs.getRow(), i = 0;
             String data [] = new String[rec];
             rs.beforeFirst();
             while (rs.next()){
                 data[i] = rs.getString(1);
                 i++;
             }
             
             st.close(); cn.close();
             
             return data;
        } catch (Exception e) {
             javax.swing.JOptionPane.showMessageDialog(null,"ERROR EXECUTE QUERY CARI, ERROR @ : "+e.getMessage());
             return null;
        }
    }
 
    
    public static java.util.Date getTanggal(String SQL){
        java.util.Date tgl = null; 
        try {
             java.sql.Connection cn = new ctrlKoneksiDB().openConnection();
             Statement st = cn.createStatement();
             
             java.sql.ResultSet rs = st.executeQuery(SQL); 
            
             if(rs.next()){
                 tgl = rs.getDate(1);                
             }
             st.close(); cn.close();
             return tgl;
        } catch (Exception e) {
             javax.swing.JOptionPane.showMessageDialog(null,"ERROR EXECUTE QUERY , ERROR @ : "+e.getMessage());
             return null;
        }
    }
  
 public void setDefaultTableCheckBox(String SQL, javax.swing.table.DefaultTableModel tblModel, int banyakData) {
       Object data[] = new Object[banyakData];       
       try {               
            cn          = koneksi.openConnection(); 
            st      	= cn.createStatement();
         
            ResultSet rs= st.executeQuery(SQL);  
            
            while(rs.next()) {  
                for(int i = 1; i<banyakData; i++){
                    data[0] = new Boolean(false);
                    data[i] = rs.getString(i);
                }
                
                tblModel.addRow(data);
            }
            
            rs.close();  st.close();   cn.close();
        } catch(Exception exc) {
           javax.swing.JOptionPane.showMessageDialog(null,"ERROR setDefaultTableCheckBox , ERROR @ : "+exc.getMessage());
           System.err.println("ERROR setDefaultTableChekBox @ : "+exc.getMessage());            
        } 
    }
       
    
    
    
    public void setDefaultTable(String SQL, javax.swing.table.DefaultTableModel tblModel, int banyakData) {
       Object data[] = new Object[banyakData];       
       try {               
            cn          = koneksi.openConnection(); 
            st      	= cn.createStatement();
         
            ResultSet rs= st.executeQuery(SQL);
            while(rs.next()) {  
                for(int i = 0; i<banyakData; i++){
                    data[i] = rs.getString(i+1).toString();
                }
                tblModel.addRow(data);
            }
            
            rs.close();  st.close();   cn.close();
        } catch(Exception exc) {
           javax.swing.JOptionPane.showMessageDialog(null,"ERROR setDefaultTable , ERROR @ : "+exc.getMessage());
           System.err.println(exc.getMessage());            
        } 
    }
    
    
    String result="";

    public boolean updateStokBarang(String SQLUpdatePesan, String SQLGetQty){
        cek = false;
        try {               
            cn          = koneksi.openConnection(); 
            st      	= cn.createStatement();
         
            cn.setAutoCommit(false);            
            System.out.println("Commit  =  false");
            
            st.executeUpdate(SQLUpdatePesan);
            
            ResultSet rs = st.executeQuery(SQLGetQty);  
            
            while(rs.next()) {  
                try{
                     String SQL2 = "UPDATE  barang " +
                                   "SET qty = qty - "+rs.getInt(2)+" " +
                                   "WHERE id_brg = '"+rs.getString(1)+"';";
                     
                     Statement st2 = cn.createStatement();
                     st2.executeUpdate(SQL2);

                     cek = true;
                }catch(Exception e){
                    System.out.println("Gagal Mengupdate Quantity Barang Dgn ID : "+rs.getString(1));
                    cek = false;
                }
            }
            
            cn.commit();
//            rs.close();  st.close();   cn.close();
            System.out.println("Transaksi Commit");
        }catch(java.sql.SQLException sqler){
            System.out.println("Transaksi Gagal @ :"+sqler.getMessage());
            try{
                cn.rollback();
                System.out.println("Transaksi Di RollBack");
            }catch(java.sql.SQLException sqler2){
                System.out.println("Rollback Gagal @ : "+sqler2.getMessage());
            }
            
            cek = false;
        }catch(Exception exc) {
           javax.swing.JOptionPane.showMessageDialog(null,"ERROR getDataStokBarang , ERROR @ : "+exc.getMessage());
           System.err.println("ERROR getDataStokBarang @ : "+exc.getMessage()); 
           cek = false;
        }finally{
            try{
                st.close();   cn.close();
            }catch(java.sql.SQLException sqle){
                System.out.println("Error Menutup Connectino @ : "+sqle.getMessage());
                cek = false;
            }
        }
        
        return cek;
    }
    public void updateStokBarangFromBatalPesan(String SQLGetQty){
        cek = false;
        try {
            cn          = koneksi.openConnection();
            st      	= cn.createStatement();
            ResultSet rs = st.executeQuery(SQLGetQty);
            while(rs.next()) {
                try{
                     String SQL2 = "UPDATE  barang " +
                                   "SET qty = qty + "+rs.getInt(2)+" " +
                                   "WHERE id_brg = '"+rs.getString(1)+"';";

                     Statement st2 = cn.createStatement();
                     st2.executeUpdate(SQL2);

                     cek = true;
                }catch(Exception e){
                    System.out.println("Gagal Mengupdate Quantity Barang Dengan ID : "+rs.getString(1));
                    cek = false;
                }
            }

          
            rs.close();  st.close();   cn.close();
            System.out.println("Transaksi Commit");
        }catch(Exception exc) {
           javax.swing.JOptionPane.showMessageDialog(null,"ERROR getDataStokBarang , ERROR @ : "+exc.getMessage());
           System.err.println("ERROR getDataStokBarang @ : "+exc.getMessage());
           cek = false;
        }
    }
    
}
