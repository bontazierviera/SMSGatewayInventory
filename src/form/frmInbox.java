package form;
import control.*;
import server.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frmInbox extends javax.swing.JInternalFrame {
    MenuUtama mUtama;
    ctrlManipulasiData ctrlManData;
    ctrlComponent ctrlComp;
    ServerSMS smsServer;
    ctrlPesan ctrlPesan;
    ctrlAlert ctrlAlert;
    ctrlIDgenerator ctrlID;
    
    /** Creates new form frmInbox */
    public frmInbox(MenuUtama mUtama) {
        initComponents();
        
        this.mUtama = mUtama;
        ctrlManData = new ctrlManipulasiData(mUtama);     
        ctrlComp = new ctrlComponent();
        ctrlAlert = new ctrlAlert(mUtama);
        ctrlID    = new ctrlIDgenerator();
        
        tblModel = (DefaultTableModel) ctrlComp.getDefaultTabelModel(new String[]{
            "NO. INBOX","Judul","Tanggal","Waktu","ISI SMS","No.Handphone","ID Karyawan"
        });
        tblInbox.setModel(tblModel);
        ctrlComp.setTabel(tblInbox, new int[]{100,130,130,130,370,130,100});
        
        isiComboCari();
        isiTableInbox();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblInbox = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        cmbCari = new javax.swing.JComboBox();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setTitle(":: Form Inbox SMS ::");
        getContentPane().setLayout(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED), "DATA INBOX"));
        jPanel2.setLayout(null);

        tblInbox.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        jScrollPane2.setViewportView(tblInbox);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(10, 20, 720, 400);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Cari Bedasarkan :");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(10, 425, 110, 18);

        jPanel2.add(cmbCari);
        cmbCari.setBounds(120, 425, 120, 18);
        jPanel2.add(txtCari);
        txtCari.setBounds(240, 425, 110, 19);

        btnCari.setText("CARI");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });
        jPanel2.add(btnCari);
        btnCari.setBounds(350, 425, 70, 18);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(5, 50, 740, 450);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FORM INBOX SMS");
        jLabel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(-5, 0, 760, 50);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-765)/2, (screenSize.height-545)/2, 765, 545);
    }// </editor-fold>//GEN-END:initComponents

    private void isiComboCari(){
        cmbCari.addItem("NO. INBOX");
        cmbCari.addItem("JUDUL");
        cmbCari.addItem("TANGGAL");
        cmbCari.addItem("WAKTU");
        cmbCari.addItem("ISI SMS");
        cmbCari.addItem("NO.HANDPHONE");
        cmbCari.addItem("ID KARYAWAN");
    }
     
    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        isiTableInbox();
    }//GEN-LAST:event_btnCariActionPerformed
    
    private void isiTableInbox() {  
        dapatData();
        ctrlComp.removeTable(tblInbox,tblModel);
        String SQL="SELECT * FROM sms_req WHERE "+cariBy+" LIKE '%"+kriteria+"%' AND "
                + "SUBSTRING(no_sms_req,1,1)='I' "
                + "ORDER by no_sms_req DESC ";
        ctrlManData.setDefaultTable(SQL,tblModel,7);
   }
    private void dapatData(){
         switch(cmbCari.getSelectedIndex()){
            case 0 : cariBy = "no_sms_req"; break;
            case 1 : cariBy = "judul"; break;
            case 2 : cariBy = "tgl_sms"; break;
            case 3 : cariBy = "waktu_sms"; break;
            case 4 : cariBy = "isi_sms"; break;
            case 5 : cariBy = "no_tlp"; break;
            case 6 : cariBy = "id_karyawan"; break;
            default : cariBy = "no_sms_req";
        }
         
        kriteria = txtCari.getText().trim();     
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JComboBox cmbCari;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblInbox;
    private javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables
    public javax.swing.table.DefaultTableModel tblModel;    
    private String cariBy="", kriteria="";
}
