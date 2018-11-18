package control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ctrlIDgenerator {
    private ctrlKoneksiDB koneksi;    
    
    private Connection cn;
    private Statement st;
    private int i,h;
    private String kd,j;  
    
    /** Creates a new instance of ctrlIDgenerator */
    public ctrlIDgenerator() {   
        koneksi = new ctrlKoneksiDB();
    }
    
 public String getIDRegistrasi(){
        try {     
                cn          = koneksi.openConnection();
                st          = cn.createStatement();
                String  SQL = "SELECT id_registrasi FROM registrasi ORDER BY id_registrasi DESC;";
                ResultSet rs= st.executeQuery(SQL);            
                if(rs.next()) {
                    kd = rs.getString(1);
                    kd  = kd.substring(1);
                    i   = Integer.parseInt(kd)+1;
                    j   = String.valueOf(i);
                    h   = j.length();
                    switch(h) {              
                        case 1 : kd="R000"+j; break;                    
                        case 2 : kd="R00"+j; break;   
                        case 3 : kd="R0"+j; break;   
                        case 4 : kd="R"+j; break;
                    }    
                }else {
                    kd="R0001";
                }           
                rs.close(); st.close(); cn.close();
       } 
       catch(Exception exc) {
            System.err.println(exc.getMessage());            
       }        
        return kd;
    }
 
 public String getIDKaryawan(){
        try {     
                cn          = koneksi.openConnection();
                st          = cn.createStatement();
                String  SQL = "SELECT id_karyawan FROM karyawan ORDER BY id_karyawan DESC;";
                ResultSet rs= st.executeQuery(SQL);            
                if(rs.next()) {
                    kd = rs.getString(1);
                    kd  = kd.substring(1);
                    i   = Integer.parseInt(kd)+1;
                    j   = String.valueOf(i);
                    h   = j.length();
                    switch(h) {              
                        case 1 : kd="K000"+j; break;                    
                        case 2 : kd="K00"+j; break;   
                        case 3 : kd="K0"+j; break;   
                        case 4 : kd="K"+j; break;
                    }    
                }else {
                    kd="K0001";
                }           
                rs.close(); st.close(); cn.close();
       } 
       catch(Exception exc) {
            System.err.println(exc.getMessage());            
       }        
        return kd;
    }
    
    public String getIDAdmin(){
        try {     
                cn          = koneksi.openConnection();
                st          = cn.createStatement();
                String  SQL = "SELECT id_admin FROM admin ORDER BY id_admin DESC;";
                ResultSet rs= st.executeQuery(SQL);            
                if(rs.next()) {
                    kd = rs.getString(1);
                    kd  = kd.substring(2);
                    i   = Integer.parseInt(kd)+1;
                    j   = String.valueOf(i);
                    h   = j.length();
                    switch(h) {              
                        case 1 : kd="AD000"+j; break;                    
                        case 2 : kd="AD00"+j; break;   
                        case 3 : kd="AD0"+j; break;   
                        case 4 : kd="AD"+j; break;   
                    }    
                }else {
                    kd="AD0001";
                }           
                rs.close(); st.close(); cn.close();
       } 
       catch(Exception exc) {
            System.err.println(exc.getMessage());            
       }        
        return kd;
    }
 
public String getIDSupplier(){
        try {
                cn          = koneksi.openConnection();
                st          = cn.createStatement();
                String  SQL = "SELECT id_supplier FROM supplier ORDER BY id_supplier DESC;";
                ResultSet rs= st.executeQuery(SQL);
                if(rs.next()) {
                    kd = rs.getString(1);
                    kd  = kd.substring(1);
                    i   = Integer.parseInt(kd)+1;
                    j   = String.valueOf(i);
                    h   = j.length();
                    switch(h) {
                        case 1 : kd="S000"+j; break;
                        case 2 : kd="S00"+j; break;
                        case 3 : kd="S0"+j; break;
                        case 4 : kd="S"+j; break;
                    }
                }else {
                    kd="S0001";
                }
                rs.close(); st.close(); cn.close();
       }
       catch(Exception exc) {
            System.err.println(exc.getMessage());
       }
        return kd;
    }    
  
public String getIDPO(){
        try {     
                cn          = koneksi.openConnection();
                st          = cn.createStatement();
                String  SQL = "SELECT no_po FROM po ORDER BY no_po DESC;";
                ResultSet rs= st.executeQuery(SQL);            
                if(rs.next()) {
                    kd = rs.getString(1);
                    kd  = kd.substring(2);
                    i   = Integer.parseInt(kd)+1;
                    j   = String.valueOf(i);
                    h   = j.length();
                    switch(h) {              
                        case 1 : kd="P0000"+j; break;   
                        case 2 : kd="P000"+j; break;                    
                        case 3 : kd="P00"+j; break;   
                        case 4 : kd="P0"+j; break;    
                        case 5 : kd="P"+j; break;    
                    }    
                }else {
                    kd="P00001";
                }           
                rs.close(); st.close(); cn.close();
       } 
       catch(Exception exc) {
            System.err.println(exc.getMessage());            
       }        
        return kd;
    }
    
    public String getIDInbox(){
        try {     
                cn          = koneksi.openConnection();
                st          = cn.createStatement();
                String  SQL = "SELECT * FROM sms_req WHERE SUBSTRING(no_sms_req,1,1)='I' " +
                        "ORDER by no_sms_req DESC ";
                ResultSet rs= st.executeQuery(SQL);            
                if(rs.next()) {
                    kd = rs.getString(1);
                    kd  = kd.substring(1);
                    i   = Integer.parseInt(kd)+1;
                    j   = String.valueOf(i);
                    h   = j.length();
                    switch(h) {              
                        case 1 : kd="I000"+j; break;                    
                        case 2 : kd="I00"+j; break;   
                        case 3 : kd="I0"+j; break;   
                        case 4 : kd="I"+j; break;          
                    }    
                }else {
                    kd="I0001";
                }           
                rs.close(); st.close(); cn.close();
       } 
       catch(Exception exc) {
            System.err.println(exc.getMessage());            
       }        
        return kd;
    }

    public String getIDOutbox(){
        try {     
                cn          = koneksi.openConnection();
                st          = cn.createStatement();
                String  SQL = "SELECT * FROM balas_req WHERE SUBSTRING(no_balas_req,1,1)='O' " +
                        "ORDER by no_balas_req DESC ";
                ResultSet rs= st.executeQuery(SQL);            
                if(rs.next()) {
                    kd = rs.getString(1);
                    kd  = kd.substring(1);
                    i   = Integer.parseInt(kd)+1;
                    j   = String.valueOf(i);
                    h   = j.length();
                    switch(h) {              
                        case 1 : kd="O000"+j; break;                    
                        case 2 : kd="O00"+j; break;   
                        case 3 : kd="O0"+j; break;   
                        case 4 : kd="O"+j; break;          
                    }    
                }else {
                    kd="O0001";
                }           
                rs.close(); st.close(); cn.close();
       } 
       catch(Exception exc) {
            System.err.println(exc.getMessage());            
       }        
        return kd;
    }
    
     public String getIDBroadcast(){
        try {     
                cn          = koneksi.openConnection();
                st          = cn.createStatement();
                String  SQL = "SELECT * FROM broadcast WHERE SUBSTRING(no_broadcast,1,1)='B' " +
                        "ORDER by no_broadcast DESC ";
                ResultSet rs= st.executeQuery(SQL);            
                if(rs.next()) {
                    kd = rs.getString(1);
                    kd  = kd.substring(1);
                    i   = Integer.parseInt(kd)+1;
                    j   = String.valueOf(i);
                    h   = j.length();
                    switch(h) {              
                        case 1 : kd="B000"+j; break;
                        case 2 : kd="B00"+j; break;
                        case 3 : kd="B0"+j; break;
                        case 4 : kd="B"+j; break;
                    }    
                }else {
                    kd="B0001";
                }           
                rs.close(); st.close(); cn.close();
       } 
       catch(Exception exc) {
            System.err.println(exc.getMessage());            
       }        
        return kd;
    } 
     
   
    public String generateCode(String field, String code) {
        int countCode;
        String retCode = "";
        try{
            cn = koneksi.openConnection();
            st = cn.createStatement();
            String codeGenSQL = "SELECT "+field+" FROM counter";
            ResultSet rs = st.executeQuery(codeGenSQL);
            if(rs.next())   countCode = rs.getInt(1);
            else            countCode = 0;            
            
            countCode += 1;
            if(countCode == 0)
                retCode = code + "01";            
            else if(countCode >= 1 && countCode < 10)
                retCode = code + "0000000" + countCode;
            else if(countCode >= 10 && countCode < 100)
                retCode = code + "000000" + countCode;
            else if(countCode >= 100 && countCode < 1000)
                retCode = code + "00000" + countCode;
            else if(countCode >= 1000 && countCode < 10000)
                retCode = code + "0000" + countCode;
            else if(countCode >= 10000 && countCode < 100000)
                retCode = code + "000" + countCode;
            else if(countCode >= 100000 && countCode < 1000000)
                retCode = code + "00" + countCode;
            else if(countCode >= 1000000 && countCode < 10000000)
                retCode = code + "0" + countCode;
            else if(countCode >= 10000000 && countCode < 100000000)
                retCode = code + "" + countCode;
            
            rs.close();            st.close();            cn.close();            
	}
	catch(Exception e){            
            JOptionPane.showMessageDialog(null,"GAGAL MENGENARATE ID : "+e.getMessage(),"ERROR", 0);
            System.out.println("GAGAL GENERATE ID OUTBOX !"+e.getMessage());
	}
	return retCode;           
    }
   
}
