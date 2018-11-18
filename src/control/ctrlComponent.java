package control;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ctrlComponent {
    
    /** Creates a new instance of ctrlComponent */
    public ctrlComponent() {
    }
    
    public ImageIcon getIcon(String name){
        return new ImageIcon(getClass().getResource(name));
    }
    public void setFrameIcon(JFrame f){
        f.setIconImage(f.getToolkit().getImage(getClass().getResource("/point/image/icon.png")));
    }
    
    public DefaultTableModel getDefaultTabelModel(String nama[]) {
        final boolean x[] = new boolean[nama.length];
        for(int i = 0; i<nama.length; i++){
            x[i]=false;            
        }
         return new javax.swing.table.DefaultTableModel(new Object [][] {}, nama) {
            boolean[] canEdit = x;
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };   
    } 
    
  //utk table menggunakan checkbox  
 public DefaultTableModel getDefaultTabelModelCheckBox(String nama[]) {
        final boolean x[] = new boolean[nama.length];
        final Class y[] = new Class[nama.length];

        for(int i = 0; i<nama.length; i++){           
                x[i] = false;
                y[i] = java.lang.Object.class;
        }
        x[0] = true;
        y[0] = java.lang.Boolean.class;
        
        return new javax.swing.table.DefaultTableModel(new Object [][] {}, nama) {
            boolean[] canEdit = x;
            Class[] types = y;
            
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }    
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        };   
    }       
    
    
    public void setTabel(javax.swing.JTable tb,int lebar[]) {
    	tb.setAutoResizeMode(tb.AUTO_RESIZE_OFF);
    	int kolom=tb.getColumnCount();
    	for(int i=0;i < kolom;i++) {
            javax.swing.table.TableColumn tbc = tb.getColumnModel().getColumn(i);
            tbc.setPreferredWidth(lebar[i]);
            tb.setRowHeight(18);
    	}
    }  
         
    public void removeTable(JTable tabel, DefaultTableModel Model){
        for(int i=tabel.getRowCount(); i>0; i--) {
            Model.removeRow(i-1);
        }
    }
         
    public String setNoHP(String noHP){		
        if(noHP.length() <=3 ){
            noHP = noHP;
        }else if(noHP.substring(0,3).equals("021")){
            noHP = noHP;
        }else if(noHP.substring(0,1).equals("0")){        
            noHP = "62"+noHP.substring(1);
        }else if(noHP.substring(0,1).equals("9")){        
            noHP = "021"+noHP;
        }else if(noHP.substring(0,2).equals("62")){        
            noHP = noHP;
        }
        else {
            noHP = "6221"+noHP;
        }

        return noHP;
    }
    
    public static String getWaktuFULL(){
        return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
    }
    
    public static String getWaktuYMD(){
        return new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());	
    }
    
    public static String getWaktuDMY(){      
        return new java.text.SimpleDateFormat("dd-MM-yyyy").format(new java.util.Date());        
    }
    
    public static String getWaktuJAM(){
        return new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date());  
    }

    public static String balikTanggal(String tgl)
    {
	return tgl.split("-")[2].trim()+"-"+tgl.split("-")[1].trim()+"-"+tgl.split("-")[0].trim();
    }
    
    public String HandleIlegalChar(String pesan){
        String a ="", tampung="";
        for(int i=0; i<pesan.length(); i++){        	        	
            a = String.valueOf(pesan.charAt(i));
            if(a.equals("\\")){
                a = a.replace("\\","\\\\");
                tampung += a;
            }else{
                tampung += a;
            }
        }        
        return pesan = tampung.replaceAll("'","''");
    }
    
   public boolean cekIsiField( Object[] data ){
        boolean status = false;
        for(int i=0 ; i<data.length ; i++){
            if ( data[i].equals("") ) {
                status = false;
                break;
            }else{
                status = true;
            }
        }
        return status;
    }
}
