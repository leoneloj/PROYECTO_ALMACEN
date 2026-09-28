package proyecto_almacen;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frm_cargo extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frm_cargo.class.getName());
    /* Modelo para mostrar datos en la tabla */
    DefaultTableModel modeloTablaCargo = new DefaultTableModel();

    /* Objeto de conexión a la base de datos */
    conexionBD conexionBD;

    public frm_cargo() {
        initComponents();
        this.setLocationRelativeTo(null); // Centrar formulario en pantalla

        /* Estado inicial de controles */
        txtcodigocargo.setEnabled(false);
        txtnombrecargo.setEnabled(false);
        BTN_Guardar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
        BTN_Desactivar.setEnabled(false);

        /* Crear la conexión al iniciar el formulario */
        conexionBD = new conexionBD();

        /* Verificar que la conexión fue exitosa */
        if (conexionBD.getConnection() == null) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar con la base de datos.",
                    "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }

        /* Definir encabezados de la tabla */
        String titulos[] = {"Código Cargo", "Nombre Cargo"};
        modeloTablaCargo.setColumnIdentifiers(titulos);
        JTABLE_Mant_Cargo.setModel(modeloTablaCargo);
    }

    /**
     * Método para listar/refrescar los datos en el JTable desde la BD
     */
    public void mostrarCargos() {
        JTABLE_Mant_Cargo.setAutoCreateRowSorter(true);
        modeloTablaCargo.setRowCount(0); // Limpia la tabla antes de cargar datos
        try {
            ResultSet rs = conexionBD.listarCargos();

            while (rs != null && rs.next()) {
                Object[] fila = {
                    rs.getInt("id_cargo"),
                    rs.getString("nombre_cargo")
                };
                modeloTablaCargo.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al mostrar cargos:\n" + e.getMessage(),
                    "Error de consulta", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtcodigocargo = new javax.swing.JTextField();
        txtnombrecargo = new javax.swing.JTextField();
        BTN_VerCargo = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Mant_Cargo = new javax.swing.JTable();
        BTN_Nuevo = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        BTN_Modificar = new javax.swing.JButton();
        BTN_Desactivar = new javax.swing.JButton();
        BTN_EXCEL = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TXT_BuscarCargo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        BTN_Cerrar1 = new javax.swing.JButton();
        BTN_PDF = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("MANTENIMIENTO DE CARGOS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 240, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 51), null));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Codigo Cargo");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Nombre Cargo");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        txtcodigocargo.setEditable(false);
        txtcodigocargo.setBackground(new java.awt.Color(255, 255, 255));
        txtcodigocargo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtcodigocargo.setForeground(new java.awt.Color(0, 0, 204));
        txtcodigocargo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcodigocargo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtcodigocargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 330, 30));

        txtnombrecargo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtnombrecargo.setForeground(new java.awt.Color(0, 0, 204));
        txtnombrecargo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnombrecargo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtnombrecargo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombrecargoKeyTyped(evt);
            }
        });
        jPanel1.add(txtnombrecargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 59, 330, 30));

        BTN_VerCargo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_VerCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/papel.png"))); // NOI18N
        BTN_VerCargo.setText("VER CARGOS");
        BTN_VerCargo.addActionListener(this::BTN_VerCargoActionPerformed);
        jPanel1.add(BTN_VerCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 180, 50));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, -10, 850, 550));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 790, 120));

        JTABLE_Mant_Cargo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_Mant_Cargo.setForeground(new java.awt.Color(0, 0, 204));
        JTABLE_Mant_Cargo.setModel(new javax.swing.table.DefaultTableModel(
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
        JTABLE_Mant_Cargo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_Mant_CargoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTABLE_Mant_Cargo);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 810, 220));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/registro.png"))); // NOI18N
        BTN_Nuevo.setText("NUEVO");
        BTN_Nuevo.addActionListener(this::BTN_NuevoActionPerformed);
        getContentPane().add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 190, 50));

        BTN_Guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disco-flexible (1).png"))); // NOI18N
        BTN_Guardar.setText("GUARDAR");
        BTN_Guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_GuardarMouseClicked(evt);
            }
        });
        BTN_Guardar.addActionListener(this::BTN_GuardarActionPerformed);
        getContentPane().add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 190, 50));

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ahorrar.png"))); // NOI18N
        BTN_Modificar.setText("MODIFICAR");
        BTN_Modificar.addActionListener(this::BTN_ModificarActionPerformed);
        getContentPane().add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, 200, 50));

        BTN_Desactivar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Desactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/expediente.png"))); // NOI18N
        BTN_Desactivar.setText("DAR DE BAJA");
        BTN_Desactivar.addActionListener(this::BTN_DesactivarActionPerformed);
        getContentPane().add(BTN_Desactivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 160, 180, 50));

        BTN_EXCEL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        BTN_EXCEL.setText("Exportar");
        BTN_EXCEL.addActionListener(this::BTN_EXCELActionPerformed);
        getContentPane().add(BTN_EXCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 500, 120, 40));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ingresar el Nombre del Cargo");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 30));

        TXT_BuscarCargo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_BuscarCargoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_BuscarCargoKeyTyped(evt);
            }
        });
        jPanel2.add(TXT_BuscarCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 290, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        jLabel5.setText("BUSCAR");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 120, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 810, 50));

        BTN_Cerrar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cerrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrado.png"))); // NOI18N
        BTN_Cerrar1.setText("Cerrar");
        BTN_Cerrar1.addActionListener(this::BTN_Cerrar1ActionPerformed);
        getContentPane().add(BTN_Cerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 500, 130, 40));

        BTN_PDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/archivo-de-acrobat-reader.png"))); // NOI18N
        BTN_PDF.setText("Exportar");
        BTN_PDF.addActionListener(this::BTN_PDFActionPerformed);
        getContentPane().add(BTN_PDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 500, 120, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtnombrecargoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombrecargoKeyTyped
// Convierte automáticamente las letras a mayúsculas al escribir
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
            evt.setKeyChar(Character.toUpperCase(c));
        }
    }//GEN-LAST:event_txtnombrecargoKeyTyped

    private void BTN_VerCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VerCargoActionPerformed
this.mostrarCargos();
        this.BTN_Guardar.setEnabled(false);
        this.BTN_Desactivar.setEnabled(false);
        this.BTN_Modificar.setEnabled(false);
        txtcodigocargo.setText("");
        txtnombrecargo.setText("");
        txtnombrecargo.setEnabled(false);
    }//GEN-LAST:event_BTN_VerCargoActionPerformed

    private void JTABLE_Mant_CargoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_CargoMouseClicked
txtnombrecargo.setEnabled(true);
        int filaseleccionada = JTABLE_Mant_Cargo.getSelectedRow();

        if (filaseleccionada >= 0) {
            String codigo = JTABLE_Mant_Cargo.getValueAt(filaseleccionada, 0).toString();
            String nombre = JTABLE_Mant_Cargo.getValueAt(filaseleccionada, 1).toString();

            txtcodigocargo.setText(codigo);
            txtnombrecargo.setText(nombre);

            BTN_Modificar.setEnabled(true);
            BTN_Desactivar.setEnabled(true);
            BTN_Guardar.setEnabled(false);
        }
    }//GEN-LAST:event_JTABLE_Mant_CargoMouseClicked

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
txtcodigocargo.setText("");
        txtnombrecargo.setText("");

        txtnombrecargo.requestFocus();
        txtnombrecargo.setEnabled(true);

        BTN_Guardar.setEnabled(true);
        BTN_Desactivar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
String nombre = txtnombrecargo.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre del cargo", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            txtnombrecargo.requestFocus();
            return;
        }
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea guardar el registro del cargo?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                conexionBD.insertarCargo(nombre);
                JOptionPane.showMessageDialog(this, "Cargo registrado correctamente", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
                this.mostrarCargos();

                txtcodigocargo.setText("");
                txtnombrecargo.setText("");
                txtnombrecargo.setEnabled(false);
                BTN_Guardar.setEnabled(false);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al registrar cargo:\n" + ex.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
String codStr = txtcodigocargo.getText().trim();
        String nuevoNombre = txtnombrecargo.getText().trim();

        if (codStr.isEmpty() || nuevoNombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un cargo y complete el nuevo nombre", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int codigo = Integer.parseInt(codStr);

        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea modificar este cargo?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                conexionBD.modificarCargo(codigo, nuevoNombre);
                JOptionPane.showMessageDialog(this, "Cargo modificado correctamente", "Modificación exitosa", JOptionPane.INFORMATION_MESSAGE);

                this.mostrarCargos();

                txtcodigocargo.setText("");
                txtnombrecargo.setText("");
                txtnombrecargo.setEnabled(false);
                BTN_Desactivar.setEnabled(false);
                BTN_Modificar.setEnabled(false);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al modificar cargo:\n" + ex.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BTN_ModificarActionPerformed

    private void BTN_DesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DesactivarActionPerformed
String codStr = txtcodigocargo.getText().trim();
        if (codStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un cargo en la tabla para dar de baja.", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int codigo = Integer.parseInt(codStr);

        int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea dar de baja este cargo?", "Confirmar acción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (opcion == JOptionPane.YES_OPTION) {
            try {
                conexionBD.desactivarCargo(codigo);
                JOptionPane.showMessageDialog(this, "Cargo dado de baja correctamente.", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);

                this.mostrarCargos();

                txtcodigocargo.setText("");
                txtnombrecargo.setText("");
                txtnombrecargo.setEnabled(false);
                BTN_Desactivar.setEnabled(false);
                BTN_Modificar.setEnabled(false);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al dar de baja el cargo:\n" + ex.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BTN_DesactivarActionPerformed

    private void BTN_EXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EXCELActionPerformed
JOptionPane.showMessageDialog(this, "Función Exportar Excel disponible.", "Información", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_BTN_EXCELActionPerformed

    private void TXT_BuscarCargoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarCargoKeyReleased
modeloTablaCargo.setRowCount(0);
        String nombre = TXT_BuscarCargo.getText().trim();

        try {
            ResultSet rs = conexionBD.buscarCargos(nombre);

            while (rs != null && rs.next()) {
                Object[] fila = {
                    rs.getInt("id_cargo"),
                    rs.getString("nombre_cargo")
                };
                modeloTablaCargo.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar cargos:\n" + e.getMessage(), "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_TXT_BuscarCargoKeyReleased

    private void TXT_BuscarCargoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarCargoKeyTyped

    }//GEN-LAST:event_TXT_BuscarCargoKeyTyped

    private void BTN_Cerrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_Cerrar1ActionPerformed
int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas cerrar el formulario?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                conexionBD.cerrarConexion();
            } catch (Exception e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
            dispose();
        }
    }//GEN-LAST:event_BTN_Cerrar1ActionPerformed

    private void BTN_PDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_PDFActionPerformed
JOptionPane.showMessageDialog(this, "Función Exportar PDF disponible.", "Información", JOptionPane.INFORMATION_MESSAGE);
    
    }//GEN-LAST:event_BTN_PDFActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new frm_cargo().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Cerrar1;
    private javax.swing.JButton BTN_Desactivar;
    private javax.swing.JButton BTN_EXCEL;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_PDF;
    private javax.swing.JButton BTN_VerCargo;
    private javax.swing.JTable JTABLE_Mant_Cargo;
    private javax.swing.JTextField TXT_BuscarCargo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField txtcodigocargo;
    private javax.swing.JTextField txtnombrecargo;
    // End of variables declaration//GEN-END:variables
}
