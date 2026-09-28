
package proyecto_almacen;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class frm_area extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frm_area.class.getName());
/* Modelo para mostrar datos en la tabla */
    DefaultTableModel modeloTablaArea = new DefaultTableModel();

    /* Objeto de conexión a la base de datos */
    conexionBD conexionBD;
    public frm_area() {
        initComponents();
        this.setLocationRelativeTo(null); // Centrar en pantalla

        /* Deshabilitar campos y botones al iniciar */
        txtcodigoarea.setEnabled(false);
        txtnombrearea.setEnabled(false);
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
        String titulos[] = {"Código Área", "Nombre Área"};
        modeloTablaArea.setColumnIdentifiers(titulos);
        JTABLE_Mant_Area.setModel(modeloTablaArea);
    }

    /**
     * Método para listar/refrescar los datos en el JTable desde la BD
     */
    public void mostrarAreas() {
        JTABLE_Mant_Area.setAutoCreateRowSorter(true);
        modeloTablaArea.setRowCount(0); // Limpia la tabla antes de cargar datos
        try {
            ResultSet rs = conexionBD.listarAreas();

            while (rs != null && rs.next()) {
                Object[] fila = {
                    rs.getInt("id_area"),
                    rs.getString("nombre_area")
                };
                modeloTablaArea.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al mostrar áreas:\n" + e.getMessage(),
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
        txtcodigoarea = new javax.swing.JTextField();
        txtnombrearea = new javax.swing.JTextField();
        BTN_VerAreas = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Mant_Area = new javax.swing.JTable();
        BTN_Nuevo = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        BTN_Modificar = new javax.swing.JButton();
        BTN_Desactivar = new javax.swing.JButton();
        BTN_EXCEL = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TXT_BuscarArea = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        BTN_Cerrar1 = new javax.swing.JButton();
        BTN_PDF = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("MANTENIMIENTO DE AREAS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 240, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 51), null));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Codigo Area");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Nombre Area");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        txtcodigoarea.setEditable(false);
        txtcodigoarea.setBackground(new java.awt.Color(255, 255, 255));
        txtcodigoarea.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtcodigoarea.setForeground(new java.awt.Color(0, 0, 204));
        txtcodigoarea.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcodigoarea.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtcodigoarea, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 330, 30));

        txtnombrearea.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtnombrearea.setForeground(new java.awt.Color(0, 0, 204));
        txtnombrearea.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnombrearea.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtnombrearea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreareaKeyTyped(evt);
            }
        });
        jPanel1.add(txtnombrearea, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 59, 330, 30));

        BTN_VerAreas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_VerAreas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/papel.png"))); // NOI18N
        BTN_VerAreas.setText("VER Areas");
        BTN_VerAreas.addActionListener(this::BTN_VerAreasActionPerformed);
        jPanel1.add(BTN_VerAreas, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 180, 50));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 550));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 790, 120));

        JTABLE_Mant_Area.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_Mant_Area.setForeground(new java.awt.Color(0, 0, 204));
        JTABLE_Mant_Area.setModel(new javax.swing.table.DefaultTableModel(
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
        JTABLE_Mant_Area.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_Mant_AreaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTABLE_Mant_Area);

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
        jLabel4.setText("Ingresar el Nombre del Area");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 30));

        TXT_BuscarArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_BuscarAreaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_BuscarAreaKeyTyped(evt);
            }
        });
        jPanel2.add(TXT_BuscarArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 290, -1));

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

    private void txtnombreareaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreareaKeyTyped
// Convertir automáticamente a mayúsculas mientras escribe
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
            evt.setKeyChar(Character.toUpperCase(c));
        }
    }//GEN-LAST:event_txtnombreareaKeyTyped

    private void BTN_VerAreasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VerAreasActionPerformed
this.mostrarAreas();
        this.BTN_Guardar.setEnabled(false);
        this.BTN_Desactivar.setEnabled(false);
        this.BTN_Modificar.setEnabled(false);
        txtcodigoarea.setText("");
        txtnombrearea.setText("");
        txtnombrearea.setEnabled(false);
    }//GEN-LAST:event_BTN_VerAreasActionPerformed

    private void JTABLE_Mant_AreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_AreaMouseClicked
txtnombrearea.setEnabled(true);
        int filaseleccionada = JTABLE_Mant_Area.getSelectedRow();

        if (filaseleccionada >= 0) {
            String codigo = JTABLE_Mant_Area.getValueAt(filaseleccionada, 0).toString();
            String nombre = JTABLE_Mant_Area.getValueAt(filaseleccionada, 1).toString();

            txtcodigoarea.setText(codigo);
            txtnombrearea.setText(nombre);

            BTN_Modificar.setEnabled(true);
            BTN_Desactivar.setEnabled(true);
            BTN_Guardar.setEnabled(false);
        }
    }//GEN-LAST:event_JTABLE_Mant_AreaMouseClicked

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
// Limpiar cajas de texto
        txtcodigoarea.setText("");
        txtnombrearea.setText("");

        // Habilitar la caja de nombre de área y darle enfoque
        txtnombrearea.setEnabled(true);
        txtnombrearea.requestFocus();

        // Estado de botones para nuevo registro
        BTN_Guardar.setEnabled(true);
        BTN_Desactivar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
String nombre = txtnombrearea.getText().trim();

        // 1. Validar que no esté vacío
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre del area", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            txtnombrearea.requestFocus();
            return;
        }

        // 2. Confirmación
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea guardar el registro del area?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                // 3. Llamar a insertarArea en conexionBD
                conexionBD.insertarArea(nombre);

                JOptionPane.showMessageDialog(this, "area registrada correctamente", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);

                // 4. Actualizar la tabla y resetear controles
                this.mostrarAreas();

                txtcodigoarea.setText("");
                txtnombrearea.setText("");
                txtnombrearea.setEnabled(false);
                BTN_Guardar.setEnabled(false);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al registrar area:\n" + ex.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
String codStr = txtcodigoarea.getText().trim();
        String nuevoNombre = txtnombrearea.getText().trim();

        if (codStr.isEmpty() || nuevoNombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un area y complete el nuevo nombre", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int codigo = Integer.parseInt(codStr);

        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea modificar esta area?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                conexionBD.modificarArea(codigo, nuevoNombre);
                JOptionPane.showMessageDialog(this, "area modificada correctamente", "Modificación exitosa", JOptionPane.INFORMATION_MESSAGE);

                this.mostrarAreas();

                txtcodigoarea.setText("");
                txtnombrearea.setText("");
                txtnombrearea.setEnabled(false);
                BTN_Desactivar.setEnabled(false);
                BTN_Modificar.setEnabled(false);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al modificar area:\n" + ex.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BTN_ModificarActionPerformed

    private void BTN_DesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DesactivarActionPerformed
String codStr = txtcodigoarea.getText().trim();
        if (codStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un área en la tabla para dar de baja.", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int codigo = Integer.parseInt(codStr);

        int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea dar de baja esta área?", "Confirmar acción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (opcion == JOptionPane.YES_OPTION) {
            try {
                conexionBD.desactivarArea(codigo);
                JOptionPane.showMessageDialog(this, "Área dada de baja correctamente.", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);

                this.mostrarAreas();

                txtcodigoarea.setText("");
                txtnombrearea.setText("");
                txtnombrearea.setEnabled(false);
                BTN_Desactivar.setEnabled(false);
                BTN_Modificar.setEnabled(false);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al dar de baja el área:\n" + ex.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BTN_DesactivarActionPerformed

    private void BTN_EXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EXCELActionPerformed
JOptionPane.showMessageDialog(this, "Función Exportar Excel disponible.", "Información", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_BTN_EXCELActionPerformed

    private void TXT_BuscarAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarAreaKeyReleased
modeloTablaArea.setRowCount(0);
        String nombre = TXT_BuscarArea.getText().trim();

        try {
            ResultSet rs = conexionBD.buscarAreas(nombre);

            while (rs != null && rs.next()) {
                Object[] fila = {
                    rs.getInt("id_area"),
                    rs.getString("nombre_area")
                };
                modeloTablaArea.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar áreas:\n" + e.getMessage(), "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_TXT_BuscarAreaKeyReleased

    private void TXT_BuscarAreaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarAreaKeyTyped

    }//GEN-LAST:event_TXT_BuscarAreaKeyTyped

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
        java.awt.EventQueue.invokeLater(() -> new frm_area().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Cerrar1;
    private javax.swing.JButton BTN_Desactivar;
    private javax.swing.JButton BTN_EXCEL;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_PDF;
    private javax.swing.JButton BTN_VerAreas;
    private javax.swing.JTable JTABLE_Mant_Area;
    private javax.swing.JTextField TXT_BuscarArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField txtcodigoarea;
    private javax.swing.JTextField txtnombrearea;
    // End of variables declaration//GEN-END:variables
}
