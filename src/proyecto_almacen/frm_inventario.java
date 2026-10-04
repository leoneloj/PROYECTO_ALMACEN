
package proyecto_almacen;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frm_inventario extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frm_inventario.class.getName());
    /* Modelo de la tabla de inventario */
    DefaultTableModel modeloTablaInventario = new DefaultTableModel();

    /* Objeto de conexión a base de datos */
    conexionBD_gabriel conexionBD;
    
    public frm_inventario() {
        setUndecorated(true);
        initComponents();

        txt_inventario.setEnabled(false);
        txt_S_actual.setEditable(true);
        txt_S_minimo.setEditable(true);
        txt_U_Actualizacion.setEditable(false); // No editable por el usuario
        jComboBox_producto.setEnabled(true);
        jComboBox_almacen.setEnabled(true);

        BTN_Guardar.setEnabled(false);
        BTN_Modificar.setEnabled(false);

        setLocationRelativeTo(null);

        // Inicializar conexión
        conexionBD = new conexionBD_gabriel();

        if (conexionBD.getConnection() == null) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos.",
                    "Error de conexión", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Cargar listas desplegables
        this.cargarProductos();
        this.cargarAlmacenes();

        // Configuración de encabezados para la tabla (Alineado con la vista vw_inventario_activo)
        String titulos[] = {"ID Inventario", "ID Producto", "Producto", "ID Almacen", "Almacen", "Stock Actual", "Stock Minimo", "Ultima Actualizacion"};
        modeloTablaInventario.setColumnIdentifiers(titulos);
        JTABLE_MantInventario.setModel(modeloTablaInventario);

        // Ocultar columnas con IDs foráneos en la vista de usuario
        JTABLE_MantInventario.getColumnModel().getColumn(0).setMinWidth(0);
        JTABLE_MantInventario.getColumnModel().getColumn(0).setMaxWidth(0);
        JTABLE_MantInventario.getColumnModel().getColumn(0).setWidth(0);
        
        JTABLE_MantInventario.getColumnModel().getColumn(1).setMinWidth(0);
        JTABLE_MantInventario.getColumnModel().getColumn(1).setMaxWidth(0);
        JTABLE_MantInventario.getColumnModel().getColumn(1).setWidth(0);

        JTABLE_MantInventario.getColumnModel().getColumn(3).setMinWidth(0);
        JTABLE_MantInventario.getColumnModel().getColumn(3).setMaxWidth(0);
        JTABLE_MantInventario.getColumnModel().getColumn(3).setWidth(0);

        // NOTA: No se invoca MostrarInventario() aquí para que la tabla inicie vacía
    }

    /* --- MÉTODOS PARA CARGAR COMBOBOX --- */
    private void cargarProductos() {
        jComboBox_producto.removeAllItems();
        jComboBox_producto.addItem("-- Seleccionar --");
        String sql = "SELECT id_producto, nombre_producto FROM producto";
        try (Statement st = conexionBD.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                jComboBox_producto.addItem(rs.getInt("id_producto") + " - " + rs.getString("nombre_producto"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar productos:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarAlmacenes() {
        jComboBox_almacen.removeAllItems();
        jComboBox_almacen.addItem("-- Seleccionar --");
        String sql = "SELECT id_almacen, nombre_almacen FROM almacen";
        try (Statement st = conexionBD.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                jComboBox_almacen.addItem(rs.getInt("id_almacen") + " - " + rs.getString("nombre_almacen"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar almacenes:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /* --- MÉTODOS AUXILIARES --- */
    private int obtenerIdSeleccionado(javax.swing.JComboBox<String> combo) {
        if (combo.getSelectedIndex() <= 0) return 0;
        String item = combo.getSelectedItem().toString();
        String[] partes = item.split(" - ");
        return Integer.parseInt(partes[0]);
    }

    private void seleccionarComboPorId(javax.swing.JComboBox<String> combo, int id) {
        for (int i = 1; i < combo.getItemCount(); i++) {
            String item = combo.getItemAt(i);
            if (item.startsWith(id + " - ")) {
                combo.setSelectedIndex(i);
                return;
            }
        }
        if (combo.getItemCount() > 0) combo.setSelectedIndex(0);
    }

    private void limpiarCampos() {
        txt_inventario.setText("");
        txt_S_actual.setText("");
        txt_S_minimo.setText("");
        txt_U_Actualizacion.setText("");
        if (jComboBox_producto.getItemCount() > 0) jComboBox_producto.setSelectedIndex(0);
        if (jComboBox_almacen.getItemCount() > 0) jComboBox_almacen.setSelectedIndex(0);
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_U_Actualizacion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_inventario = new javax.swing.JTextField();
        txt_S_actual = new javax.swing.JTextField();
        txt_S_minimo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox_producto = new javax.swing.JComboBox<>();
        jComboBox_almacen = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        BTN_VerInventario = new javax.swing.JButton();
        BTN_Nuevo = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        BTN_Modificar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        TXT_BuscarInventario = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTABLE_MantInventario = new javax.swing.JTable();
        BTN_PDF = new javax.swing.JButton();
        BTN_EXCEL = new javax.swing.JButton();
        BTN_Cerrar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(880, 620));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("MANTENIMIENTO DE INVENTARIO");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("ID Inventario");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        txt_U_Actualizacion.addActionListener(this::txt_U_ActualizacionActionPerformed);
        jPanel2.add(txt_U_Actualizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 180, 30));

        jLabel3.setText(" Producto");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, 120, 20));
        jPanel2.add(txt_inventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 160, 30));
        jPanel2.add(txt_S_actual, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 160, 30));
        jPanel2.add(txt_S_minimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 160, 30));

        jLabel4.setText("Stock Actual");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 70, 20));

        jLabel5.setText("Stock Minimo");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 80, 20));

        jLabel6.setText("Ultima Actualizacion");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 120, 20));

        jComboBox_producto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_producto.addActionListener(this::jComboBox_productoActionPerformed);
        jPanel2.add(jComboBox_producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 250, 30));

        jComboBox_almacen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_almacen.addActionListener(this::jComboBox_almacenActionPerformed);
        jPanel2.add(jComboBox_almacen, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 62, 250, 30));

        jLabel7.setText("Almacen");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 120, 20));

        BTN_VerInventario.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_VerInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/papel.png"))); // NOI18N
        BTN_VerInventario.setText("VER INVENTARIO");
        BTN_VerInventario.addActionListener(this::BTN_VerInventarioActionPerformed);
        jPanel2.add(BTN_VerInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, 180, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 880, 170));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/registro.png"))); // NOI18N
        BTN_Nuevo.setText("NUEVO");
        BTN_Nuevo.addActionListener(this::BTN_NuevoActionPerformed);
        jPanel1.add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 190, 50));

        BTN_Guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disco-flexible (1).png"))); // NOI18N
        BTN_Guardar.setText("GUARDAR");
        BTN_Guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_GuardarMouseClicked(evt);
            }
        });
        BTN_Guardar.addActionListener(this::BTN_GuardarActionPerformed);
        jPanel1.add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 200, 190, 50));

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ahorrar.png"))); // NOI18N
        BTN_Modificar.setText("MODIFICAR");
        BTN_Modificar.addActionListener(this::BTN_ModificarActionPerformed);
        jPanel1.add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 200, 200, 50));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Ingresar el Nombre de Inventario");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 30));

        TXT_BuscarInventario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_BuscarInventarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_BuscarInventarioKeyTyped(evt);
            }
        });
        jPanel3.add(TXT_BuscarInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 290, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        jLabel10.setText("BUSCAR");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 120, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 810, 50));

        JTABLE_MantInventario.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_MantInventario.setForeground(new java.awt.Color(0, 0, 204));
        JTABLE_MantInventario.setModel(new javax.swing.table.DefaultTableModel(
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
        JTABLE_MantInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_MantInventarioMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JTABLE_MantInventario);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 810, 220));

        BTN_PDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/archivo-de-acrobat-reader.png"))); // NOI18N
        BTN_PDF.setText("Exportar");
        BTN_PDF.addActionListener(this::BTN_PDFActionPerformed);
        jPanel1.add(BTN_PDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 550, 120, 40));

        BTN_EXCEL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        BTN_EXCEL.setText("Exportar");
        BTN_EXCEL.addActionListener(this::BTN_EXCELActionPerformed);
        jPanel1.add(BTN_EXCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 550, 120, 40));

        BTN_Cerrar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cerrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrado.png"))); // NOI18N
        BTN_Cerrar1.setText("Cerrar");
        BTN_Cerrar1.addActionListener(this::BTN_Cerrar1ActionPerformed);
        jPanel1.add(BTN_Cerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 550, 110, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_U_ActualizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_U_ActualizacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_U_ActualizacionActionPerformed

    private void jComboBox_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_productoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_productoActionPerformed

    private void jComboBox_almacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_almacenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_almacenActionPerformed

    private void BTN_VerInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VerInventarioActionPerformed
        this.MostrarInventario();
    }//GEN-LAST:event_BTN_VerInventarioActionPerformed

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
        limpiarCampos();
        txt_S_actual.requestFocus();
        BTN_Guardar.setEnabled(true);
        BTN_Modificar.setEnabled(false);
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
        String sActualStr = txt_S_actual.getText().trim();
        String sMinimoStr = txt_S_minimo.getText().trim();
        int idProducto = obtenerIdSeleccionado(jComboBox_producto);
        int idAlmacen = obtenerIdSeleccionado(jComboBox_almacen);

        if (sActualStr.isEmpty() || sMinimoStr.isEmpty() || idProducto == 0 || idAlmacen == 0) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos requeridos.",
                    "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(null,
                "¿Desea guardar el registro de inventario?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                int stockActual = Integer.parseInt(sActualStr);
                int stockMinimo = Integer.parseInt(sMinimoStr);

                // Llamada al método de conexión con los 4 parámetros esperados por el SP
                conexionBD.insertarInventario(idProducto, idAlmacen, stockActual, stockMinimo);

                JOptionPane.showMessageDialog(null, "Inventario registrado correctamente",
                        "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);

                this.MostrarInventario();
                limpiarCampos();
                BTN_Guardar.setEnabled(false);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Los valores de stock deben ser números enteros válidos.", "Error de formato", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al registrar inventario:\n"
                        + ex.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
        String codStr = txt_inventario.getText().trim();
        String sActualStr = txt_S_actual.getText().trim();
        String sMinimoStr = txt_S_minimo.getText().trim();
        int idProducto = obtenerIdSeleccionado(jComboBox_producto);
        int idAlmacen = obtenerIdSeleccionado(jComboBox_almacen);

        if (codStr.isEmpty() || sActualStr.isEmpty() || sMinimoStr.isEmpty() || idProducto == 0 || idAlmacen == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un registro de la tabla y complete todos los campos.",
                    "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(null,
                "¿Desea modificar este registro de inventario?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                int codigo = Integer.parseInt(codStr);
                int stockActual = Integer.parseInt(sActualStr);
                int stockMinimo = Integer.parseInt(sMinimoStr);

                // Llamada al método de conexión con los 5 parámetros esperados por el SP
                conexionBD.modificarInventario(codigo, idProducto, idAlmacen, stockActual, stockMinimo);

                JOptionPane.showMessageDialog(null, "Inventario modificado correctamente",
                        "Modificación exitosa", JOptionPane.INFORMATION_MESSAGE);

                this.MostrarInventario();
                limpiarCampos();
                BTN_Modificar.setEnabled(false);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Los valores de stock deben ser números enteros válidos.", "Error de formato", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al modificar inventario:\n"
                        + ex.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BTN_ModificarActionPerformed

    private void TXT_BuscarInventarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarInventarioKeyReleased
        this.buscarInventario();
    }//GEN-LAST:event_TXT_BuscarInventarioKeyReleased

    private void TXT_BuscarInventarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarInventarioKeyTyped

    }//GEN-LAST:event_TXT_BuscarInventarioKeyTyped

    private void JTABLE_MantInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_MantInventarioMouseClicked
        int filaSeleccionada = JTABLE_MantInventario.getSelectedRow();
        if (filaSeleccionada >= 0) {
            txt_inventario.setText(JTABLE_MantInventario.getValueAt(filaSeleccionada, 0).toString());
            
            int idProd = Integer.parseInt(JTABLE_MantInventario.getValueAt(filaSeleccionada, 1).toString());
            int idAlm = Integer.parseInt(JTABLE_MantInventario.getValueAt(filaSeleccionada, 3).toString());
            
            seleccionarComboPorId(jComboBox_producto, idProd);
            seleccionarComboPorId(jComboBox_almacen, idAlm);

            txt_S_actual.setText(JTABLE_MantInventario.getValueAt(filaSeleccionada, 5).toString());
            txt_S_minimo.setText(JTABLE_MantInventario.getValueAt(filaSeleccionada, 6).toString());
            
            Object objFecha = JTABLE_MantInventario.getValueAt(filaSeleccionada, 7);
            txt_U_Actualizacion.setText(objFecha != null ? objFecha.toString() : "");

            BTN_Modificar.setEnabled(true);
            BTN_Guardar.setEnabled(false);
        }
    }//GEN-LAST:event_JTABLE_MantInventarioMouseClicked

    private void BTN_PDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_PDFActionPerformed

    }//GEN-LAST:event_BTN_PDFActionPerformed

    private void BTN_EXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EXCELActionPerformed

    }//GEN-LAST:event_BTN_EXCELActionPerformed

    private void BTN_Cerrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_Cerrar1ActionPerformed
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas cerrar el formulario?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                if (conexionBD != null) {
                    conexionBD.cerrarConexion();
                }
            } catch (Exception e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
            dispose();
        }
    }//GEN-LAST:event_BTN_Cerrar1ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new frm_inventario().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Cerrar1;
    private javax.swing.JButton BTN_EXCEL;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_PDF;
    private javax.swing.JButton BTN_VerInventario;
    private javax.swing.JTable JTABLE_MantInventario;
    private javax.swing.JTextField TXT_BuscarInventario;
    private javax.swing.JComboBox<String> jComboBox_almacen;
    private javax.swing.JComboBox<String> jComboBox_producto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txt_S_actual;
    private javax.swing.JTextField txt_S_minimo;
    private javax.swing.JTextField txt_U_Actualizacion;
    private javax.swing.JTextField txt_inventario;
    // End of variables declaration//GEN-END:variables
public void MostrarInventario() {
        JTABLE_MantInventario.setAutoCreateRowSorter(true);
        modeloTablaInventario.setRowCount(0);
        try {
            ResultSet rs = conexionBD.listarInventarios();
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("id_inventario"),
                    rs.getInt("id_producto"),
                    rs.getString("nombre_producto"),
                    rs.getInt("id_almacen"),
                    rs.getString("nombre_almacen"),
                    rs.getInt("stock_actual"),
                    rs.getInt("stock_minimo"),
                    rs.getString("ultima_actualizacion")
                };
                modeloTablaInventario.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar Inventario:\n" + e.getMessage(),
                    "Error de consulta", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buscarInventario() {
        modeloTablaInventario.setRowCount(0);
        String busqueda = TXT_BuscarInventario.getText().trim();
        try {
            ResultSet rs = conexionBD.buscarInventario(busqueda);
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("id_inventario"),
                    rs.getInt("id_producto"),
                    rs.getString("nombre_producto"),
                    rs.getInt("id_almacen"),
                    rs.getString("nombre_almacen"),
                    rs.getInt("stock_actual"),
                    rs.getInt("stock_minimo"),
                    rs.getString("ultima_actualizacion")
                };
                modeloTablaInventario.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al buscar Inventario:\n" + e.getMessage(),
                    "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
        }
    }
}
