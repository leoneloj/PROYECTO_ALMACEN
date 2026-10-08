package proyecto_almacen;

import java.sql.*; // Librerías para conexión con MySQL
import javax.swing.table.DefaultTableModel; // Para trabajar con JTable
import javax.swing.JOptionPane; // Para mensajes emergentes

public class frm_sub_categoria extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frm_sub_categoria.class.getName());
    conexionBD con = new conexionBD();
    DefaultTableModel dtm;

    public frm_sub_categoria() {
        setUndecorated(true);

        initComponents();
        this.setLocationRelativeTo(null); // Centrar ventana

        // Configurar modelo de la tabla
        dtm = new DefaultTableModel();
        dtm.addColumn("ID Subcat");
        dtm.addColumn("ID Cat");
        dtm.addColumn("Categoría");
        dtm.addColumn("Nombre Subcategoría");
        dtm.addColumn("Descripción");
        JTABLE_Mant_subCategoria.setModel(dtm);

        // Cargar solo las categorías en el ComboBox al abrir (la tabla inicia vacía)
        cargarCategorias();

        // Configuración inicial de campos
        txtcodigosubCategoria.setEnabled(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jcomboboxsubCategoria = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtdireccionsubCategoria = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtcodigosubCategoria = new javax.swing.JTextField();
        txtnombresubCategoria = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        BTN_VerSubCategoria = new javax.swing.JButton();
        BTN_Modificar = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        BTN_Nuevo = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TXT_BUSCAR_subCategoria = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Mant_subCategoria = new javax.swing.JTable();
        BTN_Cerrar1 = new javax.swing.JButton();
        BTN_EXCEL1 = new javax.swing.JButton();
        BTN_EXCEL = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("MANTENIMIENTO TABLA SUBCATEGORIA");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 51), null));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Codigo subcategoria");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jcomboboxsubCategoria.setForeground(new java.awt.Color(0, 0, 153));
        jcomboboxsubCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(jcomboboxsubCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 360, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Nombre subCategoria");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        txtdireccionsubCategoria.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtdireccionsubCategoria.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtdireccionsubCategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdireccionsubCategoriaKeyTyped(evt);
            }
        });
        jPanel3.add(txtdireccionsubCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 360, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Descripcion");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        txtcodigosubCategoria.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtcodigosubCategoria.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(txtcodigosubCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 360, -1));

        txtnombresubCategoria.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtnombresubCategoria.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnombresubCategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombresubCategoriaKeyTyped(evt);
            }
        });
        jPanel3.add(txtnombresubCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 360, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Categoria");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        BTN_VerSubCategoria.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BTN_VerSubCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/papel.png"))); // NOI18N
        BTN_VerSubCategoria.setText("LISTAR SUBCATEGORIA");
        BTN_VerSubCategoria.addActionListener(this::BTN_VerSubCategoriaActionPerformed);
        jPanel3.add(BTN_VerSubCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 30, 230, 60));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 930, 160));

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ahorrar.png"))); // NOI18N
        BTN_Modificar.setText("MODIFICAR");
        BTN_Modificar.addActionListener(this::BTN_ModificarActionPerformed);
        jPanel2.add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 220, 200, 50));

        BTN_Guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disco-flexible (1).png"))); // NOI18N
        BTN_Guardar.setText("GUARDAR");
        BTN_Guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_GuardarMouseClicked(evt);
            }
        });
        BTN_Guardar.addActionListener(this::BTN_GuardarActionPerformed);
        jPanel2.add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 220, 190, 50));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/registro.png"))); // NOI18N
        BTN_Nuevo.setText("NUEVO");
        BTN_Nuevo.addActionListener(this::BTN_NuevoActionPerformed);
        jPanel2.add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, 190, 50));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ingresar el Nombre de la Facultad");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        jLabel5.setText("BUSCAR");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 120, 30));

        TXT_BUSCAR_subCategoria.addActionListener(this::TXT_BUSCAR_subCategoriaActionPerformed);
        TXT_BUSCAR_subCategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_BUSCAR_subCategoriaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_BUSCAR_subCategoriaKeyTyped(evt);
            }
        });
        jPanel4.add(TXT_BUSCAR_subCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 290, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 930, 50));

        JTABLE_Mant_subCategoria.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_Mant_subCategoria.setForeground(new java.awt.Color(0, 0, 153));
        JTABLE_Mant_subCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        JTABLE_Mant_subCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_Mant_subCategoriaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTABLE_Mant_subCategoria);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 930, 180));

        BTN_Cerrar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cerrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrado.png"))); // NOI18N
        BTN_Cerrar1.setText("Cerrar");
        BTN_Cerrar1.addActionListener(this::BTN_Cerrar1ActionPerformed);
        jPanel2.add(BTN_Cerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 550, 130, 40));

        BTN_EXCEL1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        BTN_EXCEL1.setText("Exportar");
        BTN_EXCEL1.addActionListener(this::BTN_EXCEL1ActionPerformed);
        jPanel2.add(BTN_EXCEL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 550, 120, 40));

        BTN_EXCEL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/archivo-de-acrobat-reader.png"))); // NOI18N
        BTN_EXCEL.setText("Exportar");
        BTN_EXCEL.addActionListener(this::BTN_EXCELActionPerformed);
        jPanel2.add(BTN_EXCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 550, 120, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 1030, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtdireccionsubCategoriaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionsubCategoriaKeyTyped

    }//GEN-LAST:event_txtdireccionsubCategoriaKeyTyped

    private void txtnombresubCategoriaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombresubCategoriaKeyTyped

    }//GEN-LAST:event_txtnombresubCategoriaKeyTyped

    private void BTN_VerSubCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VerSubCategoriaActionPerformed
        this.listarSubcategorias();
    }//GEN-LAST:event_BTN_VerSubCategoriaActionPerformed

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
        try {
            String codigoStr = txtcodigosubCategoria.getText().trim();
            String nombreSub = txtnombresubCategoria.getText().trim();
            String descripcion = txtdireccionsubCategoria.getText().trim();
            String nombreCategoria = (String) jcomboboxsubCategoria.getSelectedItem();

            if (codigoStr.isEmpty() || nombreSub.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione un registro de la tabla para modificar.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (nombreCategoria == null || nombreCategoria.equals("<<Seleccionar>>")) {
                JOptionPane.showMessageDialog(this, "Seleccione una categoría válida.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idSubcategoria = Integer.parseInt(codigoStr);
            int idCategoria = con.obtenerCodigoCategoria(nombreCategoria);

            if (idCategoria == -1) {
                JOptionPane.showMessageDialog(this, "No se encontró el ID de la categoría seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            con.modificarSubcategoria(idSubcategoria, idCategoria, nombreSub, descripcion);
            JOptionPane.showMessageDialog(this, "Subcategoría actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            listarSubcategorias();
            limpiarCampos();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al modificar la subcategoría: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BTN_ModificarActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
        try {
            String nombreSub = txtnombresubCategoria.getText().trim();
            String descripcion = txtdireccionsubCategoria.getText().trim();
            String nombreCategoria = (String) jcomboboxsubCategoria.getSelectedItem();

            if (nombreSub.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor complete el nombre de la subcategoría.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (nombreCategoria == null || nombreCategoria.equals("<<Seleccionar>>")) {
                JOptionPane.showMessageDialog(this, "Seleccione una categoría principal válida.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idCategoria = con.obtenerCodigoCategoria(nombreCategoria);
            if (idCategoria == -1) {
                JOptionPane.showMessageDialog(this, "No se encontró el código de la categoría seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            con.insertarSubcategoria(idCategoria, nombreSub, descripcion);
            JOptionPane.showMessageDialog(this, "Subcategoría registrada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            listarSubcategorias();
            limpiarCampos();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar la subcategoría: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
        this.limpiarCampos();
        BTN_Guardar.setEnabled(true);
        BTN_VerSubCategoria.setEnabled(true);

    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void TXT_BUSCAR_subCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_BUSCAR_subCategoriaActionPerformed

    }//GEN-LAST:event_TXT_BUSCAR_subCategoriaActionPerformed

    private void TXT_BUSCAR_subCategoriaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BUSCAR_subCategoriaKeyReleased
        String texto = TXT_BUSCAR_subCategoria.getText().trim();

        if (texto.isEmpty()) {
            listarSubcategorias();
            return;
        }

        dtm.setRowCount(0);
        try {
            ResultSet rs = con.buscarSubcategoria(texto);
            while (rs != null && rs.next()) {
                Object fila[] = {
                    rs.getInt("id_subcategoria"),
                    rs.getInt("id_categoria"),
                    rs.getString("categoria_nombre"),
                    rs.getString("nombre_subcategoria"),
                    rs.getString("descripcion")
                };
                dtm.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar subcategorías: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_TXT_BUSCAR_subCategoriaKeyReleased

    private void TXT_BUSCAR_subCategoriaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BUSCAR_subCategoriaKeyTyped

    }//GEN-LAST:event_TXT_BUSCAR_subCategoriaKeyTyped

    private void JTABLE_Mant_subCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_subCategoriaMouseClicked
        int filaSeleccionada = JTABLE_Mant_subCategoria.getSelectedRow();
        if (filaSeleccionada >= 0) {
            txtcodigosubCategoria.setText(JTABLE_Mant_subCategoria.getValueAt(filaSeleccionada, 0).toString());
            txtnombresubCategoria.setText(JTABLE_Mant_subCategoria.getValueAt(filaSeleccionada, 3).toString());
            txtdireccionsubCategoria.setText(JTABLE_Mant_subCategoria.getValueAt(filaSeleccionada, 4).toString());

            String categoriaNombre = JTABLE_Mant_subCategoria.getValueAt(filaSeleccionada, 2).toString();
            jcomboboxsubCategoria.setSelectedItem(categoriaNombre);
        }

    }//GEN-LAST:event_JTABLE_Mant_subCategoriaMouseClicked

    private void BTN_Cerrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_Cerrar1ActionPerformed
        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Estás seguro de que deseas cerrar el formulario?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (opcion == JOptionPane.YES_OPTION) {
            this.dispose(); // Cierra el formulario solo si elige "Sí"
        }
    }//GEN-LAST:event_BTN_Cerrar1ActionPerformed

    private void BTN_EXCEL1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EXCEL1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_EXCEL1ActionPerformed

    private void BTN_EXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EXCELActionPerformed

    }//GEN-LAST:event_BTN_EXCELActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new frm_sub_categoria().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Cerrar1;
    private javax.swing.JButton BTN_EXCEL;
    private javax.swing.JButton BTN_EXCEL1;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_VerSubCategoria;
    private javax.swing.JTable JTABLE_Mant_subCategoria;
    private javax.swing.JTextField TXT_BUSCAR_subCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcomboboxsubCategoria;
    private javax.swing.JTextField txtcodigosubCategoria;
    private javax.swing.JTextField txtdireccionsubCategoria;
    private javax.swing.JTextField txtnombresubCategoria;
    // End of variables declaration//GEN-END:variables
private void cargarCategorias() {
        try {
            jcomboboxsubCategoria.removeAllItems();
            jcomboboxsubCategoria.addItem("<<Seleccionar>>");

            ResultSet rs = con.combobox_ListarCategorias();
            while (rs != null && rs.next()) {
                String nombreCat = rs.getString("nombre");
                if (nombreCat != null) {
                    jcomboboxsubCategoria.addItem(nombreCat.trim());
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar categorías: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarSubcategorias() {
        dtm.setRowCount(0);
        try {
            ResultSet rs = con.verSubcategorias();
            while (rs != null && rs.next()) {
                Object fila[] = {
                    rs.getInt("id_subcategoria"),
                    rs.getInt("id_categoria"),
                    rs.getString("categoria_nombre"),
                    rs.getString("nombre_subcategoria"),
                    rs.getString("descripcion")
                };
                dtm.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al listar subcategorías: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtcodigosubCategoria.setText("");
        txtnombresubCategoria.setText("");
        txtdireccionsubCategoria.setText("");
        jcomboboxsubCategoria.setSelectedIndex(0);
        TXT_BUSCAR_subCategoria.setText("");
        BTN_Guardar.setEnabled(true);
        BTN_Modificar.setEnabled(true);
    }

}
