package form;

import control.*;
import javax.swing.table.DefaultTableModel;


public class frmKaryawan extends javax.swing.JInternalFrame {
    MenuUtama mUtama;
    ctrlManipulasiData ctrlManData;
    ctrlAlert ctrlAlert;    
    ctrlComponent ctrlComp;    
    ctrlValidasi ctrlValid;
    

    public frmKaryawan(MenuUtama mUtama) {
        initComponents();
        this.mUtama = mUtama;
        ctrlManData = new ctrlManipulasiData(mUtama);
        ctrlAlert = new ctrlAlert(mUtama);        
        ctrlComp = new ctrlComponent();
        ctrlValid = new ctrlValidasi(mUtama);
        
        tblModKaryawan = (DefaultTableModel) ctrlComp.getDefaultTabelModel(new String[]{
           "ID Karyawan ", "Nama Karyawan", "Alamat","Nomer HP","Jabatan"
        });
        tblKaryawan.setModel(tblModKaryawan);
        ctrlComp.setTabel(tblKaryawan, new int[]{100,125,200,100,130,200});
        
        txtIdKaryawan.setDocument(new control.ctrlTextDoc(5));
        txtJabatan.setDocument(new control.ctrlTextDoc(40));
        txtAlamat.setDocument(new control.ctrlTextDoc(125)); 
        txtNoHp.setDocument(new control.ctrlTextDoc(15, control.ctrlTextDoc.DESIMAL));
        txtJabatan.setDocument(new control.ctrlTextDoc(30));
        
        
        
        setAwalForm();
        isiComboCari();
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabPane = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtJabatan = new javax.swing.JTextField();
        lblNmKaryawan = new javax.swing.JLabel();
        lblJabatan = new javax.swing.JLabel();
        lblAlamat = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        lblIdKaryawan = new javax.swing.JLabel();
        txtIdKaryawan = new javax.swing.JTextField();
        lblNoHp = new javax.swing.JLabel();
        txtNoHp = new javax.swing.JTextField();
        txtNmKaryawan = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKaryawan = new javax.swing.JTable();
        lblCari = new javax.swing.JLabel();
        cbCari = new javax.swing.JComboBox();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();

        setClosable(true);
        setTitle("::FORM MASTER KARYAWAN ::"); // NOI18N
        getContentPane().setLayout(null);

        jPanel3.setLayout(null);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Isi Data Dengan Lengkap"));
        jPanel4.setLayout(null);

        jPanel1.setLayout(null);

        txtJabatan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel1.add(txtJabatan);
        txtJabatan.setBounds(110, 210, 140, 25);

        lblNmKaryawan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNmKaryawan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNmKaryawan.setText("Nama Karyawan :"); // NOI18N
        jPanel1.add(lblNmKaryawan);
        lblNmKaryawan.setBounds(0, 50, 100, 25);

        lblJabatan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblJabatan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblJabatan.setText("Jabatan :"); // NOI18N
        jPanel1.add(lblJabatan);
        lblJabatan.setBounds(30, 210, 70, 25);

        lblAlamat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblAlamat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAlamat.setText("Alamat :"); // NOI18N
        jPanel1.add(lblAlamat);
        lblAlamat.setBounds(0, 100, 100, 50);

        txtAlamat.setColumns(20);
        txtAlamat.setLineWrap(true);
        txtAlamat.setRows(3);
        txtAlamat.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtAlamat);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(110, 90, 250, 70);

        lblIdKaryawan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblIdKaryawan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblIdKaryawan.setText("ID Karyawan :"); // NOI18N
        jPanel1.add(lblIdKaryawan);
        lblIdKaryawan.setBounds(10, 10, 90, 25);

        txtIdKaryawan.setEditable(false);
        txtIdKaryawan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel1.add(txtIdKaryawan);
        txtIdKaryawan.setBounds(110, 10, 100, 25);

        lblNoHp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNoHp.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNoHp.setText("Nomer HP :"); // NOI18N
        jPanel1.add(lblNoHp);
        lblNoHp.setBounds(10, 170, 90, 25);

        txtNoHp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel1.add(txtNoHp);
        txtNoHp.setBounds(110, 170, 170, 25);

        txtNmKaryawan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel1.add(txtNmKaryawan);
        txtNmKaryawan.setBounds(110, 50, 250, 25);

        jPanel4.add(jPanel1);
        jPanel1.setBounds(10, 20, 380, 250);

        jPanel3.add(jPanel4);
        jPanel4.setBounds(20, 0, 460, 280);

        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Save.png"))); // NOI18N
        btnSimpan.setIconTextGap(10);
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        jPanel3.add(btnSimpan);
        btnSimpan.setBounds(30, 290, 80, 40);

        btnUbah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/update.png"))); // NOI18N
        btnUbah.setIconTextGap(10);
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });
        jPanel3.add(btnUbah);
        btnUbah.setBounds(120, 290, 80, 40);

        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/delete.png"))); // NOI18N
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        jPanel3.add(btnHapus);
        btnHapus.setBounds(210, 290, 80, 40);

        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cancel.png"))); // NOI18N
        btnBatal.setIconTextGap(10);
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        jPanel3.add(btnBatal);
        btnBatal.setBounds(300, 290, 80, 40);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/exit.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);
        jButton1.setBounds(390, 290, 80, 40);

        tabPane.addTab("Form Input Data Karyawan", jPanel3);

        jPanel5.setLayout(null);

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel2.setLayout(null);

        tblKaryawan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        tblKaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKaryawanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKaryawan);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(10, 10, 480, 300);

        lblCari.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCari.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCari.setText("Cari Berdasarkan :"); // NOI18N
        jPanel2.add(lblCari);
        lblCari.setBounds(0, 320, 120, 20);

        cbCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCariActionPerformed(evt);
            }
        });
        jPanel2.add(cbCari);
        cbCari.setBounds(110, 320, 80, 20);
        jPanel2.add(txtCari);
        txtCari.setBounds(190, 320, 130, 20);

        btnCari.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnCari.setText("Cari"); // NOI18N
        btnCari.setIconTextGap(10);
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });
        jPanel2.add(btnCari);
        btnCari.setBounds(320, 320, 70, 20);

        jPanel5.add(jPanel2);
        jPanel2.setBounds(0, 0, 500, 370);

        tabPane.addTab("Data Karyawan", jPanel5);

        getContentPane().add(tabPane);
        tabPane.setBounds(10, 10, 500, 380);

        setBounds(0, 0, 537, 430);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        isiTableKaryawan();
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        setAwalForm();
    }//GEN-LAST:event_btnBatalActionPerformed
private void setAwalForm() {
        txtIdKaryawan.setText(""+new ctrlIDgenerator().getIDKaryawan());
        txtNmKaryawan.setText("");
        txtAlamat.setText("");
        txtNoHp.setText("");
        txtJabatan.setText("");
        
        
        btnSimpan.setEnabled(true);
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
        
        isiTableKaryawan();
    }
    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        dapatData();
        
        dataKaryawan = new Object []{idKaryawan, nama, alamat, noHp, jabatan};
        
        if(ctrlComp.cekIsiField(dataKaryawan)){
                       
                if(ctrlAlert.KONFIRMASI(idKaryawan, "HAPUS")){
                    String SQL = "DELETE FROM karyawan WHERE id_karyawan ='"+idKaryawan+"';";
                    if(ctrlManData.EXECUTE_QUERY(SQL,idKaryawan,"HAPUS", true)){
                        setAwalForm();
                    }else{
                        setAwalForm();
                    }
                }    
            
        }else{
            ctrlAlert.getDataKosongMessage();
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        dapatData();
        
        dataKaryawan = new Object []{ idKaryawan, nama, alamat, noHp, jabatan};
        
        if(ctrlComp.cekIsiField(dataKaryawan)){
       
                if(ctrlAlert.KONFIRMASI(idKaryawan, "UBAH")){
                    String SQL = "UPDATE karyawan SET nama='"+nama+"', alamat='"+alamat+"', no_hp='"+noHp+"', jabatan='"+jabatan+"' WHERE id_karyawan='"+idKaryawan+"';";
                    if(ctrlManData.EXECUTE_QUERY(SQL,idKaryawan,"UBAH", true)){
                        setAwalForm();
                    }else{
                        setAwalForm();
                    }
                }    
            
        }else{
            ctrlAlert.getDataKosongMessage();
        }
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        dapatData();
        
        dataKaryawan = new Object []{idKaryawan, nama, alamat, noHp, jabatan};
        
        if(ctrlComp.cekIsiField(dataKaryawan)){
            
                if(ctrlAlert.KONFIRMASI(idKaryawan, "SIMPAN")){
                    String SQL = "INSERT INTO karyawan VALUES('"+idKaryawan+"','"+nama+"','"+alamat+"','"+noHp+"','"+jabatan+"');";
                    if(ctrlManData.EXECUTE_QUERY(SQL,idKaryawan,"SIMPAN", true)){
                        setAwalForm();
                    }else{
                        setAwalForm();
                    }
                }    
            
        }else{
            ctrlAlert.getDataKosongMessage();
        }
    }//GEN-LAST:event_btnSimpanActionPerformed
     
    int row = 0;
    private void tblKaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKaryawanMouseClicked
        row = tblKaryawan.getSelectedRow();
        if(evt.getClickCount()==2){
            txtIdKaryawan.setText(tblKaryawan.getValueAt(row,0).toString());
            txtNmKaryawan.setText(tblKaryawan.getValueAt(row,1).toString());
            txtAlamat.setText(tblKaryawan.getValueAt(row,2).toString());
            txtNoHp.setText(tblKaryawan.getValueAt(row,3).toString());           
            txtJabatan.setText(tblKaryawan.getValueAt(row,4).toString());
                       
            btnSimpan.setEnabled(false);
            btnUbah.setEnabled(true);
            btnHapus.setEnabled(true);
            
            tabPane.setSelectedIndex(0);
        }
    }//GEN-LAST:event_tblKaryawanMouseClicked

    private void cbCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCariActionPerformed
    
    
    private void isiTableKaryawan() {  
        dapatData();
        ctrlComp.removeTable(tblKaryawan,tblModKaryawan);
        String SQL = "SELECT * FROM karyawan WHERE "+cariBy+" LIKE '%"+kriteria+"%';";
        ctrlManData.setDefaultTable(SQL,tblModKaryawan,5);
    }

    private void isiComboCari(){
        cbCari.addItem("ID Karyawan");
        cbCari.addItem("Nama Karyawan");        
        cbCari.addItem("Alamat");
        cbCari.addItem("Nomer HP");
        cbCari.addItem("Jabatan");
    }
    
    private void dapatData(){
         switch(cbCari.getSelectedIndex()){
            case 0 : cariBy = "id_karyawan"; break;
            case 1 : cariBy = "nama"; break;    
            case 2 : cariBy = "alamat"; break;
            case 3 : cariBy = "no_hp"; break;
            case 4 : cariBy = "jabatan"; break;
            default : cariBy = "id_karyawan";
        }
        kriteria = txtCari.getText().trim();
        
        idKaryawan=txtIdKaryawan.getText().trim();
        nama=txtNmKaryawan.getText().trim().replaceAll("'","''");        
        alamat = txtAlamat.getText().trim();
        noHp = ctrlComp.setNoHP(txtNoHp.getText().trim());
        jabatan = txtJabatan.getText().trim();
        
    }
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox cbCari;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblCari;
    private javax.swing.JLabel lblIdKaryawan;
    private javax.swing.JLabel lblJabatan;
    private javax.swing.JLabel lblNmKaryawan;
    private javax.swing.JLabel lblNoHp;
    private javax.swing.JTabbedPane tabPane;
    private javax.swing.JTable tblKaryawan;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtIdKaryawan;
    private javax.swing.JTextField txtJabatan;
    private javax.swing.JTextField txtNmKaryawan;
    private javax.swing.JTextField txtNoHp;
    // End of variables declaration//GEN-END:variables
    private String idKaryawan="", nama="", alamat="",noHp="", jabatan="";    
    public javax.swing.table.DefaultTableModel tblModKaryawan;    
    private String cariBy="", kriteria="";
    private Object dataKaryawan[];
}
