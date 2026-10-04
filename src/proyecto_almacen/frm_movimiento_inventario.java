
package proyecto_almacen;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frm_movimiento_inventario extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frm_movimiento_inventario.class.getName());
    /* Modelo de la tabla de movimientos */
    DefaultTableModel modeloTablaMovimiento = new DefaultTableModel();

    /* Objeto de conexión a base de datos */
    conexionBD_gabriel conexionBD;
    
    public frm_movimiento_inventario() {
        setUndecorated(true);
        initComponents();
        
        txt_movimientoinventario.setEnabled(false);
        txt_tipo_movimiento.setEditable(true);
        txt_cantidad.setEditable(true);
        txt_fecha.setEditable(false);
        jComboBox_producto.setEnabled(true);
        jComboBox_almacen.setEnabled(true);
        jComboBox_personal.setEnabled(true);

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
        this.cargarPersonal();

        // Configuración de encabezados para la tabla
        String titulos[] = {"ID Movimiento", "Tipo Movimiento", "Cantidad", "Fecha", "ID Producto", "Producto", "ID Almacen", "Almacen", "ID Personal", "Personal"};
        modeloTablaMovimiento.setColumnIdentifiers(titulos);
        JTABLE_Mantmovimiento.setModel(modeloTablaMovimiento);

        // Ocultar columnas con IDs foráneos en la vista de usuario
        JTABLE_Mantmovimiento.getColumnModel().getColumn(0).setMinWidth(0);
        JTABLE_Mantmovimiento.getColumnModel().getColumn(0).setMaxWidth(0);
        JTABLE_Mantmovimiento.getColumnModel().getColumn(0).setWidth(0);
        
        JTABLE_Mantmovimiento.getColumnModel().getColumn(4).setMinWidth(0);
        JTABLE_Mantmovimiento.getColumnModel().getColumn(4).setMaxWidth(0);
        JTABLE_Mantmovimiento.getColumnModel().getColumn(4).setWidth(0);

        JTABLE_Mantmovimiento.getColumnModel().getColumn(6).setMinWidth(0);
        JTABLE_Mantmovimiento.getColumnModel().getColumn(6).setMaxWidth(0);
        JTABLE_Mantmovimiento.getColumnModel().getColumn(6).setWidth(0);

        JTABLE_Mantmovimiento.getColumnModel().getColumn(8).setMinWidth(0);
        JTABLE_Mantmovimiento.getColumnModel().getColumn(8).setMaxWidth(0);
        JTABLE_Mantmovimiento.getColumnModel().getColumn(8).setWidth(0);

        // Cargar registros al abrir
        //this.MostrarMovimientos();
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

    private void cargarPersonal() {
    jComboBox_personal.removeAllItems();
    jComboBox_personal.addItem("-- Seleccionar --");
    String sql = "SELECT id_personal, CONCAT(nombres, ' ', apellidos) AS nombre_personal FROM personal";
    try (Statement st = conexionBD.getConnection().createStatement();
         ResultSet rs = st.executeQuery(sql)) {
        while (rs.next()) {
            jComboBox_personal.addItem(rs.getInt("id_personal") + " - " + rs.getString("nombre_personal"));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al cargar personal:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        txt_movimientoinventario.setText("");
        txt_tipo_movimiento.setText("");
        txt_cantidad.setText("");
        txt_fecha.setText("");
        if (jComboBox_producto.getItemCount() > 0) jComboBox_producto.setSelectedIndex(0);
        if (jComboBox_almacen.getItemCount() > 0) jComboBox_almacen.setSelectedIndex(0);
        if (jComboBox_personal.getItemCount() > 0) jComboBox_personal.setSelectedIndex(0);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_fecha = new javax.swing.JTextField();
        txt_movimientoinventario = new javax.swing.JTextField();
        txt_tipo_movimiento = new javax.swing.JTextField();
        txt_cantidad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox_producto = new javax.swing.JComboBox<>();
        jComboBox_almacen = new javax.swing.JComboBox<>();
        jComboBox_personal = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        BTN_VerMovimiento = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        BTN_Nuevo = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        BTN_Modificar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        TXT_Buscarmovimiento = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTABLE_Mantmovimiento = new javax.swing.JTable();
        BTN_PDF = new javax.swing.JButton();
        BTN_EXCEL = new javax.swing.JButton();
        BTN_Cerrar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Fecha");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        txt_fecha.setEditable(false);
        txt_fecha.addActionListener(this::txt_fechaActionPerformed);
        jPanel2.add(txt_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 180, 30));
        jPanel2.add(txt_movimientoinventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 12, 180, 30));

        txt_tipo_movimiento.addActionListener(this::txt_tipo_movimientoActionPerformed);
        jPanel2.add(txt_tipo_movimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 180, 30));
        jPanel2.add(txt_cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 180, 30));

        jLabel3.setText("ID Movimiento inventario");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel4.setText("Tipo Movimiento");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel5.setText("Cantidad");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jLabel6.setText("Personal");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, -1, -1));

        jComboBox_producto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(jComboBox_producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 220, 30));

        jComboBox_almacen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(jComboBox_almacen, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, 220, 30));

        jComboBox_personal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(jComboBox_personal, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 130, 220, 30));

        jLabel7.setText("Producto");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, -1, -1));

        jLabel8.setText("Almacen");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, -1, -1));

        BTN_VerMovimiento.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_VerMovimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/papel.png"))); // NOI18N
        BTN_VerMovimiento.setText("VER MOVIMIENTO");
        BTN_VerMovimiento.addActionListener(this::BTN_VerMovimientoActionPerformed);
        jPanel2.add(BTN_VerMovimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 20, 180, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 920, 230));

        jLabel1.setText("MANTENIMIENTO DE MOVIMIENTO INVENTARIO");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, -1, -1));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/registro.png"))); // NOI18N
        BTN_Nuevo.setText("NUEVO");
        BTN_Nuevo.addActionListener(this::BTN_NuevoActionPerformed);
        jPanel1.add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 190, 50));

        BTN_Guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disco-flexible (1).png"))); // NOI18N
        BTN_Guardar.setText("GUARDAR");
        BTN_Guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_GuardarMouseClicked(evt);
            }
        });
        BTN_Guardar.addActionListener(this::BTN_GuardarActionPerformed);
        jPanel1.add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 260, 190, 50));

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ahorrar.png"))); // NOI18N
        BTN_Modificar.setText("MODIFICAR");
        BTN_Modificar.addActionListener(this::BTN_ModificarActionPerformed);
        jPanel1.add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 260, 200, 50));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Ingresar el Nombre de Movimiento");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 30));

        TXT_Buscarmovimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_BuscarmovimientoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_BuscarmovimientoKeyTyped(evt);
            }
        });
        jPanel3.add(TXT_Buscarmovimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 290, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        jLabel10.setText("BUSCAR");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 120, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 810, 50));

        JTABLE_Mantmovimiento.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_Mantmovimiento.setForeground(new java.awt.Color(0, 0, 204));
        JTABLE_Mantmovimiento.setModel(new javax.swing.table.DefaultTableModel(
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
        JTABLE_Mantmovimiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_MantmovimientoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JTABLE_Mantmovimiento);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, 810, 220));

        BTN_PDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/archivo-de-acrobat-reader.png"))); // NOI18N
        BTN_PDF.setText("Exportar");
        BTN_PDF.addActionListener(this::BTN_PDFActionPerformed);
        jPanel1.add(BTN_PDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 610, 120, 40));

        BTN_EXCEL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        BTN_EXCEL.setText("Exportar");
        BTN_EXCEL.addActionListener(this::BTN_EXCELActionPerformed);
        jPanel1.add(BTN_EXCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 610, 120, 40));

        BTN_Cerrar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cerrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrado.png"))); // NOI18N
        BTN_Cerrar1.setText("Cerrar");
        BTN_Cerrar1.addActionListener(this::BTN_Cerrar1ActionPerformed);
        jPanel1.add(BTN_Cerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 610, 130, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_tipo_movimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tipo_movimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tipo_movimientoActionPerformed

    private void BTN_VerMovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VerMovimientoActionPerformed
        this.MostrarMovimientos();
    }//GEN-LAST:event_BTN_VerMovimientoActionPerformed

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
       limpiarCampos();
        txt_tipo_movimiento.requestFocus();
        BTN_Guardar.setEnabled(true);
        BTN_Modificar.setEnabled(false);
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
        // Obtener la fecha y hora exacta del sistema al momento de guardar
    java.time.LocalDateTime ahora = java.time.LocalDateTime.now();
    java.time.format.DateTimeFormatter formato = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String fechaActual = ahora.format(formato);
    
    // Colocarla en el campo visual
    txt_fecha.setText(fechaActual);

    String tipo = txt_tipo_movimiento.getText().trim();
    String cantidadStr = txt_cantidad.getText().trim();
    String fecha = txt_fecha.getText().trim();
    int idProducto = obtenerIdSeleccionado(jComboBox_producto);
    int idAlmacen = obtenerIdSeleccionado(jComboBox_almacen);
    int idPersonal = obtenerIdSeleccionado(jComboBox_personal);

    if (tipo.isEmpty() || cantidadStr.isEmpty() || idProducto == 0 || idAlmacen == 0 || idPersonal == 0) {
        JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos requeridos.",
                "Campo requerido", JOptionPane.WARNING_MESSAGE);
        return;
    }

    int respuesta = JOptionPane.showConfirmDialog(null,
            "¿Desea guardar el movimiento de inventario?", "Confirmación", JOptionPane.YES_NO_OPTION);

    if (respuesta == JOptionPane.YES_OPTION) {
        try {
            int cantidad = Integer.parseInt(cantidadStr);
            // Se envía a la base de datos con los parámetros de tu conexión
            conexionBD.insertarMovimientoInventario(idProducto, idAlmacen, idPersonal, tipo, cantidad);

            JOptionPane.showMessageDialog(null, "Movimiento registrado correctamente",
                    "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);

            this.MostrarMovimientos();
            limpiarCampos();
            BTN_Guardar.setEnabled(false);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La cantidad debe ser un número entero válido.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar Movimiento:\n"
                    + ex.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
        String codStr = txt_movimientoinventario.getText().trim();
        String tipo = txt_tipo_movimiento.getText().trim();
        String cantidadStr = txt_cantidad.getText().trim();
        String fecha = txt_fecha.getText().trim();
        int idProducto = obtenerIdSeleccionado(jComboBox_producto);
        int idAlmacen = obtenerIdSeleccionado(jComboBox_almacen);
        int idPersonal = obtenerIdSeleccionado(jComboBox_personal);

        if (codStr.isEmpty() || tipo.isEmpty() || cantidadStr.isEmpty() || fecha.isEmpty() || idProducto == 0 || idAlmacen == 0 || idPersonal == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un movimiento de la tabla y complete todos los campos.",
                    "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int codigo = Integer.parseInt(codStr);

        int respuesta = JOptionPane.showConfirmDialog(null,
                "¿Desea modificar este movimiento?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                int cantidad = Integer.parseInt(cantidadStr);
                // BTN_GuardarActionPerformed
                conexionBD.insertarMovimientoInventario(idProducto, idAlmacen, idPersonal, tipo, cantidad);

                JOptionPane.showMessageDialog(null, "Movimiento modificado correctamente",
                        "Modificación exitosa", JOptionPane.INFORMATION_MESSAGE);

                this.MostrarMovimientos();
                limpiarCampos();
                BTN_Modificar.setEnabled(false);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "La cantidad debe ser un número entero válido.", "Error de formato", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al modificar movimiento:\n"
                        + ex.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BTN_ModificarActionPerformed

    private void TXT_BuscarmovimientoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarmovimientoKeyReleased
        this.buscarMovimientos();
    }//GEN-LAST:event_TXT_BuscarmovimientoKeyReleased

    private void TXT_BuscarmovimientoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarmovimientoKeyTyped

    }//GEN-LAST:event_TXT_BuscarmovimientoKeyTyped

    private void JTABLE_MantmovimientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_MantmovimientoMouseClicked
        int filaSeleccionada = JTABLE_Mantmovimiento.getSelectedRow();
        if (filaSeleccionada >= 0) {
            txt_movimientoinventario.setText(JTABLE_Mantmovimiento.getValueAt(filaSeleccionada, 0).toString());
            txt_tipo_movimiento.setText(JTABLE_Mantmovimiento.getValueAt(filaSeleccionada, 1).toString());
            txt_cantidad.setText(JTABLE_Mantmovimiento.getValueAt(filaSeleccionada, 2).toString());
            txt_fecha.setText(JTABLE_Mantmovimiento.getValueAt(filaSeleccionada, 3).toString());

            int idProd = Integer.parseInt(JTABLE_Mantmovimiento.getValueAt(filaSeleccionada, 4).toString());
            int idAlm = Integer.parseInt(JTABLE_Mantmovimiento.getValueAt(filaSeleccionada, 6).toString());
            int idPer = Integer.parseInt(JTABLE_Mantmovimiento.getValueAt(filaSeleccionada, 8).toString());

            seleccionarComboPorId(jComboBox_producto, idProd);
            seleccionarComboPorId(jComboBox_almacen, idAlm);
            seleccionarComboPorId(jComboBox_personal, idPer);

            BTN_Modificar.setEnabled(true);
            BTN_Guardar.setEnabled(false);
        }
    }//GEN-LAST:event_JTABLE_MantmovimientoMouseClicked

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

    private void txt_fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_fechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fechaActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new frm_movimiento_inventario().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Cerrar1;
    private javax.swing.JButton BTN_EXCEL;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_PDF;
    private javax.swing.JButton BTN_VerMovimiento;
    private javax.swing.JTable JTABLE_Mantmovimiento;
    private javax.swing.JTextField TXT_Buscarmovimiento;
    private javax.swing.JComboBox<String> jComboBox_almacen;
    private javax.swing.JComboBox<String> jComboBox_personal;
    private javax.swing.JComboBox<String> jComboBox_producto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txt_cantidad;
    private javax.swing.JTextField txt_fecha;
    private javax.swing.JTextField txt_movimientoinventario;
    private javax.swing.JTextField txt_tipo_movimiento;
    // End of variables declaration//GEN-END:variables
public void MostrarMovimientos() {
    JTABLE_Mantmovimiento.setAutoCreateRowSorter(true);
    modeloTablaMovimiento.setRowCount(0);
    try {
        ResultSet rs = conexionBD.listarMovimientosInventario();
        while (rs.next()) {
            Object[] fila = {
                rs.getInt("id_movimiento_inventario"), // <--- NOMBRE EXACTO DE LA VISTA
                rs.getString("tipo_movimiento"),
                rs.getInt("cantidad"),
                rs.getString("fecha"),
                rs.getInt("id_producto"),
                rs.getString("nombre_producto"),
                rs.getInt("id_almacen"),
                rs.getString("nombre_almacen"),
                rs.getInt("id_personal"),
                rs.getString("nombre_personal")
            };
            modeloTablaMovimiento.addRow(fila);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al mostrar Movimientos:\n" + e.getMessage(),
                "Error de consulta", JOptionPane.ERROR_MESSAGE);
    }
}

    public void buscarMovimientos() {
        modeloTablaMovimiento.setRowCount(0);
        String busqueda = TXT_Buscarmovimiento.getText().trim();
        try {
            ResultSet rs = conexionBD.buscarMovimientoInventario(busqueda);
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("id_movimiento_inventario"),
                    rs.getString("tipo_movimiento"),
                    rs.getInt("cantidad"),
                    rs.getString("fecha"),
                    rs.getInt("id_producto"),
                    rs.getString("nombre_producto"),
                    rs.getInt("id_almacen"),
                    rs.getString("nombre_almacen"),
                    rs.getInt("id_personal"),
                    rs.getString("nombre_personal")
                };
                modeloTablaMovimiento.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al buscar Movimientos:\n" + e.getMessage(),
                    "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
        }
    }
}
