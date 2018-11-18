package form;

import control.*;
import javax.swing.table.DefaultTableModel;

public class popUpSupplier extends javax.swing.JDialog {
    MenuUtama mUtama;
    ctrlManipulasiData ctrlManData;
    ctrlAlert ctrlAlert;
    ctrlComponent ctrlComp;
    private String cariBy;
    private String kriteria;
    private String idSupplier="",nmSupplier="";
    
    public popUpSupplier(java.awt.Frame parent, boolean modal, MenuUtama mUtama) {
        super(parent, modal);
        initComponents();

        this.mUtama = mUtama;
        ctrlManData = new ctrlManipulasiData(mUtama);
        ctrlAlert = new ctrlAlert(mUtama);
        ctrlComp = new ctrlComponent();

        tblModel = (DefaultTableModel) ctrlComp.getDefaultTabelModel(new String[]{
            "ID Supplier","Nama Supplier"
        });
        tblSupplier.setModel(tblModel);
        ctrlComp.setTabel(tblSupplier, new int[]{140,150});
        isiComboCari();
        isiTableSupplier();
    }

    public void setIdSupplier(String idSupplier){
        this.idSupplier=idSupplier;
    }

    public void setNamaSupplier(String nmSupplier){
        this.nmSupplier=nmSupplier;
    }
    
    public String getIdSupplier(){
        return this.idSupplier;
    }

    public String getNamaSupplier(){
        return this.nmSupplier;
    }
    
    
    private void isiTableSupplier() {
        dapatData();
        ctrlComp.removeTable(tblSupplier,tblModel);
        String SQL = "SELECT id_supplier, nama_supplier FROM supplier where "+cariBy+" LIKE '%"+kriteria+"%' "+
                     "ORDER BY id_supplier DESC ";
        ctrlManData.setDefaultTable(SQL,tblModel,2);
    }

    private void isiComboCari(){
        cmbCari.addItem("ID Supplier");
        cmbCari.addItem("Nama Supplier");
    }

    private void dapatData(){
         switch(cmbCari.getSelectedIndex()){
            case 0 : cariBy = "id_supplier"; break;
            case 1 : cariBy = "nama_supplier"; break;
            default : cariBy = "id_supplier";
        }
        kriteria = txtCari.getText().trim();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSupplier = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cmbCari = new javax.swing.JComboBox();
        txtCari = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Data Supplier"));

        tblSupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSupplierMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSupplier);

        jLabel1.setText("Cari");

        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbCari, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCari)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void tblSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSupplierMouseClicked
        // TODO add your handling code here:
        int row = tblSupplier.getSelectedRow();
        if(evt.getClickCount()==2){
            this.setIdSupplier(tblSupplier.getValueAt(row, 0).toString());
            this.setNamaSupplier(tblSupplier.getValueAt(row, 1).toString());
            dispose();
        }
    }//GEN-LAST:event_tblSupplierMouseClicked

    private void txtCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyReleased
        // TODO add your handling code here:
        isiTableSupplier();
    }//GEN-LAST:event_txtCariKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbCari;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSupplier;
    private javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables

    public javax.swing.table.DefaultTableModel tblModel;
}
