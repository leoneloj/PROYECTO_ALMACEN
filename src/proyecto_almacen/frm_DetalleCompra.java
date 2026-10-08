package proyecto_almacen;

import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class frm_DetalleCompra extends javax.swing.JFrame {

    conexionBD con = new conexionBD();
    DefaultTableModel dtm;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frm_DetalleCompra.class.getName());

    public frm_DetalleCompra() {
        setUndecorated(true);

        initComponents();
        this.setLocationRelativeTo(null);

        // Configuración de columnas exactamente igual a tu base de datos
        dtm = new DefaultTableModel();
        dtm.addColumn("ID Detalle");     // Columna 0 (Oculta)
        dtm.addColumn("ID Compra");      // Columna 1
        dtm.addColumn("ID Producto");    // Columna 2
        dtm.addColumn("Cantidad");       // Columna 3
        dtm.addColumn("Precio Compra");  // Columna 4
        dtm.addColumn("Subtotal");       // Columna 5

        JTABLE_Mant_Detalle_compra.setModel(dtm);

        // Ocultar la columna del ID Detalle para mantener orden
        JTABLE_Mant_Detalle_compra.getColumnModel().getColumn(0).setMaxWidth(0);
        JTABLE_Mant_Detalle_compra.getColumnModel().getColumn(0).setMinWidth(0);
        JTABLE_Mant_Detalle_compra.getColumnModel().getColumn(0).setPreferredWidth(0);

        cargarCombosCompras();
        cargarCombosProductos();

        // Deshabilitar campos automáticos o de control inicial
        txtcodigoDetalleCompra.setEnabled(false);
        txtsubtotalDetalleCompra.setEnabled(false); // Subtotal bloqueado para evitar modificaciones manuales erróneas

        // Estado inicial de los botones (Nuevo: activo, Guardar: activo, Modificar: inactivo)
        gestionarEstadosBotones(true, true, false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jcomboboxIdproducto = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtsubtotalDetalleCompra = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtcodigoDetalleCompra = new javax.swing.JTextField();
        txtcantidadDetalleCompra = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        BTN_VerSubdetallecompra = new javax.swing.JButton();
        txtprecioDetalleCompra = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jcomboboxIdcompra = new javax.swing.JComboBox<>();
        BTN_Modificar = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        BTN_Nuevo = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TXT_BUSCAR_Detalle_compra = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Mant_Detalle_compra = new javax.swing.JTable();
        BTN_Cerrar1 = new javax.swing.JButton();
        BTN_EXCEL1 = new javax.swing.JButton();
        BTN_EXCEL = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("MANTENIMIENTO DETALLE DE COMPRA");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 51), null));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Codigo Detalle Compra");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jcomboboxIdproducto.setForeground(new java.awt.Color(0, 0, 153));
        jcomboboxIdproducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcomboboxIdproducto.addActionListener(this::jcomboboxIdproductoActionPerformed);
        jPanel3.add(jcomboboxIdproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 90, 360, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Cantidad");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        txtsubtotalDetalleCompra.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtsubtotalDetalleCompra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtsubtotalDetalleCompra.addActionListener(this::txtsubtotalDetalleCompraActionPerformed);
        txtsubtotalDetalleCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsubtotalDetalleCompraKeyTyped(evt);
            }
        });
        jPanel3.add(txtsubtotalDetalleCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 360, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Precio_compra");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        txtcodigoDetalleCompra.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtcodigoDetalleCompra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(txtcodigoDetalleCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 11, 360, 30));

        txtcantidadDetalleCompra.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtcantidadDetalleCompra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcantidadDetalleCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcantidadDetalleCompraKeyTyped(evt);
            }
        });
        jPanel3.add(txtcantidadDetalleCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 51, 360, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Id producto");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 100, -1, -1));

        BTN_VerSubdetallecompra.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BTN_VerSubdetallecompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/papel.png"))); // NOI18N
        BTN_VerSubdetallecompra.setText("LISTAR DETALLE DE COMPRA");
        BTN_VerSubdetallecompra.addActionListener(this::BTN_VerSubdetallecompraActionPerformed);
        jPanel3.add(BTN_VerSubdetallecompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 20, 240, 50));

        txtprecioDetalleCompra.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtprecioDetalleCompra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtprecioDetalleCompra.addActionListener(this::txtprecioDetalleCompraActionPerformed);
        txtprecioDetalleCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtprecioDetalleCompraKeyTyped(evt);
            }
        });
        jPanel3.add(txtprecioDetalleCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 360, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Subtotal");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Id compra");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 60, -1, -1));

        jcomboboxIdcompra.setForeground(new java.awt.Color(0, 0, 153));
        jcomboboxIdcompra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcomboboxIdcompra.addActionListener(this::jcomboboxIdcompraActionPerformed);
        jPanel3.add(jcomboboxIdcompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 50, 360, 30));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1320, 140));

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ahorrar.png"))); // NOI18N
        BTN_Modificar.setText("MODIFICAR");
        BTN_Modificar.addActionListener(this::BTN_ModificarActionPerformed);
        jPanel2.add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 190, 200, 50));

        BTN_Guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disco-flexible (1).png"))); // NOI18N
        BTN_Guardar.setText("GUARDAR");
        BTN_Guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_GuardarMouseClicked(evt);
            }
        });
        BTN_Guardar.addActionListener(this::BTN_GuardarActionPerformed);
        jPanel2.add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 190, 190, 50));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/registro.png"))); // NOI18N
        BTN_Nuevo.setText("NUEVO");
        BTN_Nuevo.addActionListener(this::BTN_NuevoActionPerformed);
        jPanel2.add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, 190, 50));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ingresar el Nombre de la Facultad");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        jLabel5.setText("BUSCAR");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, 120, 30));

        TXT_BUSCAR_Detalle_compra.addActionListener(this::TXT_BUSCAR_Detalle_compraActionPerformed);
        TXT_BUSCAR_Detalle_compra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_BUSCAR_Detalle_compraKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_BUSCAR_Detalle_compraKeyTyped(evt);
            }
        });
        jPanel4.add(TXT_BUSCAR_Detalle_compra, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 400, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 1320, 50));

        JTABLE_Mant_Detalle_compra.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_Mant_Detalle_compra.setForeground(new java.awt.Color(0, 0, 153));
        JTABLE_Mant_Detalle_compra.setModel(new javax.swing.table.DefaultTableModel(
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
        JTABLE_Mant_Detalle_compra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_Mant_Detalle_compraMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTABLE_Mant_Detalle_compra);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 1320, 210));

        BTN_Cerrar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cerrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrado.png"))); // NOI18N
        BTN_Cerrar1.setText("Cerrar");
        BTN_Cerrar1.addActionListener(this::BTN_Cerrar1ActionPerformed);
        jPanel2.add(BTN_Cerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 540, 150, 60));

        BTN_EXCEL1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        BTN_EXCEL1.setText("Exportar");
        BTN_EXCEL1.addActionListener(this::BTN_EXCEL1ActionPerformed);
        jPanel2.add(BTN_EXCEL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 540, 160, 60));

        BTN_EXCEL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/archivo-de-acrobat-reader.png"))); // NOI18N
        BTN_EXCEL.setText("Exportar");
        BTN_EXCEL.addActionListener(this::BTN_EXCELActionPerformed);
        jPanel2.add(BTN_EXCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 540, 150, 60));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1340, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtsubtotalDetalleCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsubtotalDetalleCompraKeyTyped

    }//GEN-LAST:event_txtsubtotalDetalleCompraKeyTyped

    private void txtcantidadDetalleCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcantidadDetalleCompraKeyTyped

    }//GEN-LAST:event_txtcantidadDetalleCompraKeyTyped

    private void BTN_VerSubdetallecompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VerSubdetallecompraActionPerformed
        this.listarDetalles();
    }//GEN-LAST:event_BTN_VerSubdetallecompraActionPerformed

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
        try {
            if (txtcodigoDetalleCompra.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione un registro de la tabla para modificar.");
                return;
            }

            int idDetalle = Integer.parseInt(txtcodigoDetalleCompra.getText().trim());
            String compraSel = (String) jcomboboxIdcompra.getSelectedItem();
            String productoSel = (String) jcomboboxIdproducto.getSelectedItem();

            if (compraSel == null || compraSel.contains("Seleccionar") || productoSel == null || productoSel.contains("Seleccionar")) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una compra y un producto válidos.");
                return;
            }

            int idCompra = extraerIdDesdeCombo(compraSel);
            int idProducto = extraerIdDesdeCombo(productoSel);

            int cantidad = Integer.parseInt(txtcantidadDetalleCompra.getText().trim());
            double precio = Double.parseDouble(txtprecioDetalleCompra.getText().trim());

            con.modificarDetalleCompra(idDetalle, idCompra, idProducto, cantidad, precio);
            JOptionPane.showMessageDialog(this, "Detalle de compra modificado correctamente.");
            listarDetalles();
            limpiarCampos();

            // Restablecer estados de botones tras modificar con éxito
            gestionarEstadosBotones(true, true, false);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al modificar: " + ex.getMessage());
        }
    }//GEN-LAST:event_BTN_ModificarActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
        try {
            String compraSel = (String) jcomboboxIdcompra.getSelectedItem();
            String productoSel = (String) jcomboboxIdproducto.getSelectedItem();

            if (compraSel == null || compraSel.contains("Seleccionar")
                    || productoSel == null || productoSel.contains("Seleccionar")) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una compra y un producto válidos.");
                return;
            }

            int idCompra = extraerIdDesdeCombo(compraSel);
            int idProducto = extraerIdDesdeCombo(productoSel);

            int cantidad = Integer.parseInt(txtcantidadDetalleCompra.getText().trim());
            double precio = Double.parseDouble(txtprecioDetalleCompra.getText().trim());

            con.insertarDetalleCompra(idCompra, idProducto, cantidad, precio);
            JOptionPane.showMessageDialog(this, "Detalle de compra guardado correctamente.");
            listarDetalles();
            limpiarCampos();

            // Restablecer estados de botones tras guardar con éxito
            gestionarEstadosBotones(true, true, false);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos en cantidad y precio.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
        }
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
        this.limpiarCampos();
        // Al dar clic en Nuevo, se activa Guardar y se bloquea Modificar
        gestionarEstadosBotones(true, true, false);
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void TXT_BUSCAR_Detalle_compraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_BUSCAR_Detalle_compraActionPerformed

    }//GEN-LAST:event_TXT_BUSCAR_Detalle_compraActionPerformed

    private void TXT_BUSCAR_Detalle_compraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BUSCAR_Detalle_compraKeyReleased
        String texto = TXT_BUSCAR_Detalle_compra.getText().trim();
        int idBuscado = 0;
        try {
            if (!texto.isEmpty()) {
                idBuscado = Integer.parseInt(texto);
            }
        } catch (NumberFormatException ignored) {
        }

        dtm.setRowCount(0);
        try {
            ResultSet rs = con.buscarDetalleCompra(idBuscado);
            while (rs != null && rs.next()) {
                Object fila[] = {
                    rs.getInt("id_detalle_compra"),
                    rs.getInt("id_compra"),
                    rs.getInt("id_producto"),
                    rs.getInt("cantidad"),
                    rs.getDouble("precio_compra"),
                    rs.getDouble("subtotal")
                };
                dtm.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error en la búsqueda: " + e.getMessage());
        }
    }//GEN-LAST:event_TXT_BUSCAR_Detalle_compraKeyReleased

    private void TXT_BUSCAR_Detalle_compraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BUSCAR_Detalle_compraKeyTyped

    }//GEN-LAST:event_TXT_BUSCAR_Detalle_compraKeyTyped

    private void JTABLE_Mant_Detalle_compraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_Detalle_compraMouseClicked
        int fila = JTABLE_Mant_Detalle_compra.getSelectedRow();
        if (fila >= 0) {
            txtcodigoDetalleCompra.setText(JTABLE_Mant_Detalle_compra.getValueAt(fila, 0).toString());

            String idCompraTabla = JTABLE_Mant_Detalle_compra.getValueAt(fila, 1).toString();
            String idProductoTabla = JTABLE_Mant_Detalle_compra.getValueAt(fila, 2).toString();

            // Seleccionar en ComboBox Compra
            for (int i = 0; i < jcomboboxIdcompra.getItemCount(); i++) {
                if (jcomboboxIdcompra.getItemAt(i).startsWith(idCompraTabla + " -") || jcomboboxIdcompra.getItemAt(i).equals(idCompraTabla)) {
                    jcomboboxIdcompra.setSelectedIndex(i);
                    break;
                }
            }

            // Seleccionar en ComboBox Producto
            for (int i = 0; i < jcomboboxIdproducto.getItemCount(); i++) {
                if (jcomboboxIdproducto.getItemAt(i).startsWith(idProductoTabla + " -") || jcomboboxIdproducto.getItemAt(i).equals(idProductoTabla)) {
                    jcomboboxIdproducto.setSelectedIndex(i);
                    break;
                }
            }

            txtcantidadDetalleCompra.setText(JTABLE_Mant_Detalle_compra.getValueAt(fila, 3).toString());
            txtprecioDetalleCompra.setText(JTABLE_Mant_Detalle_compra.getValueAt(fila, 4).toString());
            txtsubtotalDetalleCompra.setText(JTABLE_Mant_Detalle_compra.getValueAt(fila, 5).toString());

            // Al hacer clic en la tabla para editar: se bloquea Guardar (evita duplicados) y se activa Modificar
            gestionarEstadosBotones(true, false, true);
        }
    }//GEN-LAST:event_JTABLE_Mant_Detalle_compraMouseClicked

    private void BTN_Cerrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_Cerrar1ActionPerformed
        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Estás seguro de que deseas cerrar el formulario?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (opcion == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }//GEN-LAST:event_BTN_Cerrar1ActionPerformed

    private void BTN_EXCEL1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EXCEL1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_EXCEL1ActionPerformed

    private void BTN_EXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EXCELActionPerformed

    }//GEN-LAST:event_BTN_EXCELActionPerformed

    private void txtsubtotalDetalleCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsubtotalDetalleCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsubtotalDetalleCompraActionPerformed

    private void txtprecioDetalleCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtprecioDetalleCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprecioDetalleCompraActionPerformed

    private void txtprecioDetalleCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioDetalleCompraKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprecioDetalleCompraKeyTyped

    private void jcomboboxIdproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcomboboxIdproductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcomboboxIdproductoActionPerformed

    private void jcomboboxIdcompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcomboboxIdcompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcomboboxIdcompraActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new frm_DetalleCompra().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Cerrar1;
    private javax.swing.JButton BTN_EXCEL;
    private javax.swing.JButton BTN_EXCEL1;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_VerSubdetallecompra;
    private javax.swing.JTable JTABLE_Mant_Detalle_compra;
    private javax.swing.JTextField TXT_BUSCAR_Detalle_compra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcomboboxIdcompra;
    private javax.swing.JComboBox<String> jcomboboxIdproducto;
    private javax.swing.JTextField txtcantidadDetalleCompra;
    private javax.swing.JTextField txtcodigoDetalleCompra;
    private javax.swing.JTextField txtprecioDetalleCompra;
    private javax.swing.JTextField txtsubtotalDetalleCompra;
    // End of variables declaration//GEN-END:variables
private void cargarCombosCompras() {
        try {
            jcomboboxIdcompra.removeAllItems();
            jcomboboxIdcompra.addItem("<<Seleccionar Compra>>");
            ResultSet rs = con.combobox_listarCompra();
            while (rs != null && rs.next()) {
                int id = rs.getInt("id_compra");
                jcomboboxIdcompra.addItem(id + " - Compra ID " + id);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar compras: " + e.getMessage());
        }
    }

    private void cargarCombosProductos() {
        try {
            jcomboboxIdproducto.removeAllItems();
            jcomboboxIdproducto.addItem("<<Seleccionar Producto>>");
            ResultSet rs = con.combobox_listarProductos();
            while (rs != null && rs.next()) {
                int idProd = rs.getInt("id_producto");
                String nombre = rs.getString("nombre_producto");
                jcomboboxIdproducto.addItem(idProd + " - " + nombre);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar productos: " + e.getMessage());
        }
    }

    private int extraerIdDesdeCombo(String itemCombo) {
        try {
            String partes = itemCombo.split(" - ")[0];
            return Integer.parseInt(partes.replaceAll("[^0-9]", ""));
        } catch (Exception e) {
            return -1;
        }
    }

    private void listarDetalles() {
        dtm.setRowCount(0);
        try {
            ResultSet rs = con.listar_DetalleCompra();
            while (rs != null && rs.next()) {
                Object fila[] = {
                    rs.getInt("id_detalle_compra"),
                    rs.getInt("id_compra"),
                    rs.getInt("id_producto"),
                    rs.getInt("cantidad"),
                    rs.getDouble("precio_compra"),
                    rs.getDouble("subtotal")
                };
                dtm.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al listar detalles: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        txtcodigoDetalleCompra.setText("");
        txtcantidadDetalleCompra.setText("");
        txtprecioDetalleCompra.setText("");
        txtsubtotalDetalleCompra.setText("");
        if (jcomboboxIdcompra.getItemCount() > 0) {
            jcomboboxIdcompra.setSelectedIndex(0);
        }
        if (jcomboboxIdproducto.getItemCount() > 0) {
            jcomboboxIdproducto.setSelectedIndex(0);
        }
        TXT_BUSCAR_Detalle_compra.setText("");
        txtcantidadDetalleCompra.requestFocus();
    }

    // Método centralizado para controlar los estados dinámicos de los botones
    private void gestionarEstadosBotones(boolean nuevo, boolean guardar, boolean modificar) {
        BTN_Nuevo.setEnabled(nuevo);
        BTN_Guardar.setEnabled(guardar);
        BTN_Modificar.setEnabled(modificar);
    }
}
