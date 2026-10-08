
package proyecto_almacen;
import java.sql.*; 
import javax.swing.table.DefaultTableModel; 
import javax.swing.JOptionPane;
public class frm_DetalleVenta extends javax.swing.JFrame {
    conexionBD con = new conexionBD();
    DefaultTableModel dtm;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frm_DetalleVenta.class.getName());

    public frm_DetalleVenta() {
        setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(null);
        
        // Configuración de columnas de la tabla coincidiendo con la vista vw_detalle_venta_activa
        dtm = new DefaultTableModel();
        dtm.addColumn("ID Detalle");     // Columna 0 (Oculta)
        dtm.addColumn("ID Venta");       // Columna 1
        dtm.addColumn("Fecha Venta");    // Columna 2
        dtm.addColumn("ID Producto");    // Columna 3
        dtm.addColumn("Código Prod.");   // Columna 4
        dtm.addColumn("Producto");       // Columna 5
        dtm.addColumn("Cantidad");       // Columna 6
        dtm.addColumn("Precio Venta");   // Columna 7
        dtm.addColumn("Subtotal");       // Columna 8
        
        JTABLE_Mant_Detalle_venta.setModel(dtm);
        
        // Ocultar la columna del ID Detalle para mantener orden visual
        JTABLE_Mant_Detalle_venta.getColumnModel().getColumn(0).setMaxWidth(0);
        JTABLE_Mant_Detalle_venta.getColumnModel().getColumn(0).setMinWidth(0);
        JTABLE_Mant_Detalle_venta.getColumnModel().getColumn(0).setPreferredWidth(0);

        cargarCombosVentas();
        cargarCombosProductos();
        
        txtcodigoDetalleventa.setEnabled(false);
        
        // Estado inicial de botones (Nuevo: activo, Guardar: activo, Modificar: inactivo)
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
        jLabel10 = new javax.swing.JLabel();
        txtcodigoDetalleventa = new javax.swing.JTextField();
        txtcantidadDetalleventa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        BTN_VerSubdetalleventa = new javax.swing.JButton();
        txtprecioventa = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jcomboboxIdventa = new javax.swing.JComboBox<>();
        BTN_Modificar = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        BTN_Nuevo = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TXT_BUSCAR_Detalle_venta = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Mant_Detalle_venta = new javax.swing.JTable();
        BTN_Cerrar1 = new javax.swing.JButton();
        BTN_EXCEL1 = new javax.swing.JButton();
        BTN_EXCEL = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("MANTENIMIENTO DETALLE DE VENTA");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 51), null));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Codigo detalle venta");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jcomboboxIdproducto.setForeground(new java.awt.Color(0, 0, 153));
        jcomboboxIdproducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcomboboxIdproducto.addActionListener(this::jcomboboxIdproductoActionPerformed);
        jPanel3.add(jcomboboxIdproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 350, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Cantidad");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Precio venta");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        txtcodigoDetalleventa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtcodigoDetalleventa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(txtcodigoDetalleventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 360, 30));

        txtcantidadDetalleventa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtcantidadDetalleventa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcantidadDetalleventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcantidadDetalleventaKeyTyped(evt);
            }
        });
        jPanel3.add(txtcantidadDetalleventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 360, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Id producto");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, -1, -1));

        BTN_VerSubdetalleventa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BTN_VerSubdetalleventa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/papel.png"))); // NOI18N
        BTN_VerSubdetalleventa.setText("LISTAR DETALLE DE VENTA");
        BTN_VerSubdetalleventa.addActionListener(this::BTN_VerSubdetalleventaActionPerformed);
        jPanel3.add(BTN_VerSubdetalleventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, 240, 40));

        txtprecioventa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtprecioventa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtprecioventa.addActionListener(this::txtprecioventaActionPerformed);
        txtprecioventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtprecioventaKeyTyped(evt);
            }
        });
        jPanel3.add(txtprecioventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 360, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Id venta");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 70, -1, -1));

        jcomboboxIdventa.setForeground(new java.awt.Color(0, 0, 153));
        jcomboboxIdventa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcomboboxIdventa.addActionListener(this::jcomboboxIdventaActionPerformed);
        jPanel3.add(jcomboboxIdventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, 350, 30));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1010, 150));

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ahorrar.png"))); // NOI18N
        BTN_Modificar.setText("MODIFICAR");
        BTN_Modificar.addActionListener(this::BTN_ModificarActionPerformed);
        jPanel2.add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 200, 200, 50));

        BTN_Guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disco-flexible (1).png"))); // NOI18N
        BTN_Guardar.setText("GUARDAR");
        BTN_Guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_GuardarMouseClicked(evt);
            }
        });
        BTN_Guardar.addActionListener(this::BTN_GuardarActionPerformed);
        jPanel2.add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 200, 190, 50));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/registro.png"))); // NOI18N
        BTN_Nuevo.setText("NUEVO");
        BTN_Nuevo.addActionListener(this::BTN_NuevoActionPerformed);
        jPanel2.add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 190, 50));

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

        TXT_BUSCAR_Detalle_venta.addActionListener(this::TXT_BUSCAR_Detalle_ventaActionPerformed);
        TXT_BUSCAR_Detalle_venta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_BUSCAR_Detalle_ventaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_BUSCAR_Detalle_ventaKeyTyped(evt);
            }
        });
        jPanel4.add(TXT_BUSCAR_Detalle_venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 400, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 1010, 50));

        JTABLE_Mant_Detalle_venta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_Mant_Detalle_venta.setForeground(new java.awt.Color(0, 0, 153));
        JTABLE_Mant_Detalle_venta.setModel(new javax.swing.table.DefaultTableModel(
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
        JTABLE_Mant_Detalle_venta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_Mant_Detalle_ventaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTABLE_Mant_Detalle_venta);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 1010, 210));

        BTN_Cerrar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cerrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrado.png"))); // NOI18N
        BTN_Cerrar1.setText("Cerrar");
        BTN_Cerrar1.addActionListener(this::BTN_Cerrar1ActionPerformed);
        jPanel2.add(BTN_Cerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 540, 150, 60));

        BTN_EXCEL1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        BTN_EXCEL1.setText("Exportar");
        BTN_EXCEL1.addActionListener(this::BTN_EXCEL1ActionPerformed);
        jPanel2.add(BTN_EXCEL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 540, 160, 60));

        BTN_EXCEL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/archivo-de-acrobat-reader.png"))); // NOI18N
        BTN_EXCEL.setText("Exportar");
        BTN_EXCEL.addActionListener(this::BTN_EXCELActionPerformed);
        jPanel2.add(BTN_EXCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 540, 150, 60));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1050, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcomboboxIdproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcomboboxIdproductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcomboboxIdproductoActionPerformed

    private void txtcantidadDetalleventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcantidadDetalleventaKeyTyped

    }//GEN-LAST:event_txtcantidadDetalleventaKeyTyped

    private void BTN_VerSubdetalleventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VerSubdetalleventaActionPerformed
this.listarDetalles();
    }//GEN-LAST:event_BTN_VerSubdetalleventaActionPerformed

    private void txtprecioventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtprecioventaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprecioventaActionPerformed

    private void txtprecioventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioventaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprecioventaKeyTyped

    private void jcomboboxIdventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcomboboxIdventaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcomboboxIdventaActionPerformed

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
try {
            if (txtcodigoDetalleventa.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione un registro de la tabla para modificar.");
                return;
            }

            int idDetalle = Integer.parseInt(txtcodigoDetalleventa.getText().trim());
            String ventaSel = (String) jcomboboxIdventa.getSelectedItem();
            String productoSel = (String) jcomboboxIdproducto.getSelectedItem();

            if (ventaSel == null || ventaSel.contains("Seleccionar") || productoSel == null || productoSel.contains("Seleccionar")) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una venta y un producto válidos.");
                return;
            }

            int idVenta = extraerIdDesdeCombo(ventaSel);
            int idProducto = extraerIdDesdeCombo(productoSel);

            int cantidad = Integer.parseInt(txtcantidadDetalleventa.getText().trim());
            double precio = Double.parseDouble(txtprecioventa.getText().trim());

            con.modificarDetalleVenta(idDetalle, idVenta, idProducto, cantidad, precio);
            JOptionPane.showMessageDialog(this, "Detalle de venta modificado correctamente.");
            listarDetalles();
            limpiarCampos();

            gestionarEstadosBotones(true, true, false);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos en cantidad y precio.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al modificar: " + ex.getMessage());
        }       
    }//GEN-LAST:event_BTN_ModificarActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
try {
            if (txtcodigoDetalleventa.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione un registro de la tabla para modificar.");
                return;
            }

            int idDetalle = Integer.parseInt(txtcodigoDetalleventa.getText().trim());
            String ventaSel = (String) jcomboboxIdventa.getSelectedItem();
            String productoSel = (String) jcomboboxIdproducto.getSelectedItem();

            if (ventaSel == null || ventaSel.contains("Seleccionar") || productoSel == null || productoSel.contains("Seleccionar")) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una venta y un producto válidos.");
                return;
            }

            int idVenta = extraerIdDesdeCombo(ventaSel);
            int idProducto = extraerIdDesdeCombo(productoSel);

            int cantidad = Integer.parseInt(txtcantidadDetalleventa.getText().trim());
            double precio = Double.parseDouble(txtprecioventa.getText().trim());

            con.modificarDetalleVenta(idDetalle, idVenta, idProducto, cantidad, precio);
            JOptionPane.showMessageDialog(this, "Detalle de venta modificado correctamente.");
            listarDetalles();
            limpiarCampos();

            gestionarEstadosBotones(true, true, false);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos en cantidad y precio.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al modificar: " + ex.getMessage());
        }    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
       try {
            String ventaSel = (String) jcomboboxIdventa.getSelectedItem();
            String productoSel = (String) jcomboboxIdproducto.getSelectedItem();

            if (ventaSel == null || ventaSel.contains("Seleccionar") ||
                productoSel == null || productoSel.contains("Seleccionar")) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una venta y un producto válidos.");
                return;
            }

            int idVenta = extraerIdDesdeCombo(ventaSel);
            int idProducto = extraerIdDesdeCombo(productoSel);

            int cantidad = Integer.parseInt(txtcantidadDetalleventa.getText().trim());
            double precio = Double.parseDouble(txtprecioventa.getText().trim());

            con.insertarDetalleVenta(idVenta, idProducto, cantidad, precio);
            JOptionPane.showMessageDialog(this, "Detalle de venta guardado correctamente.");
            listarDetalles();
            limpiarCampos();

            gestionarEstadosBotones(true, true, false);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos en cantidad y precio.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
        }
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
      this.limpiarCampos();
        gestionarEstadosBotones(true, true, false);
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void TXT_BUSCAR_Detalle_ventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_BUSCAR_Detalle_ventaActionPerformed

    }//GEN-LAST:event_TXT_BUSCAR_Detalle_ventaActionPerformed

    private void TXT_BUSCAR_Detalle_ventaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BUSCAR_Detalle_ventaKeyReleased
     String texto = TXT_BUSCAR_Detalle_venta.getText().trim();

    dtm.setRowCount(0);
    try {
        // Ahora sí aceptará el String sin marcar error rojo
        ResultSet rs = con.buscarDetalleVenta(texto); 
        
        while (rs != null && rs.next()) {
            Object fila[] = {
                rs.getInt("id_detalle_venta"),
                rs.getInt("id_venta"),
                rs.getString("fecha_venta"),
                rs.getInt("id_producto"),
                rs.getString("codigo_producto"),
                rs.getString("nombre_producto"),
                rs.getInt("cantidad"),
                rs.getDouble("precio_venta"),
                rs.getDouble("subtotal")
            };
            dtm.addRow(fila);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error en la búsqueda: " + e.getMessage());
    }
    }//GEN-LAST:event_TXT_BUSCAR_Detalle_ventaKeyReleased

    private void TXT_BUSCAR_Detalle_ventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BUSCAR_Detalle_ventaKeyTyped

    }//GEN-LAST:event_TXT_BUSCAR_Detalle_ventaKeyTyped

    private void JTABLE_Mant_Detalle_ventaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_Detalle_ventaMouseClicked
        int fila = JTABLE_Mant_Detalle_venta.getSelectedRow();
        if (fila >= 0) {
            txtcodigoDetalleventa.setText(JTABLE_Mant_Detalle_venta.getValueAt(fila, 0).toString());
            
            String idVentaTabla = JTABLE_Mant_Detalle_venta.getValueAt(fila, 1).toString();
            String idProductoTabla = JTABLE_Mant_Detalle_venta.getValueAt(fila, 3).toString();
            
            // Seleccionar en ComboBox Venta
            for (int i = 0; i < jcomboboxIdventa.getItemCount(); i++) {
                if (jcomboboxIdventa.getItemAt(i).startsWith(idVentaTabla + " -") || jcomboboxIdventa.getItemAt(i).equals(idVentaTabla)) {
                    jcomboboxIdventa.setSelectedIndex(i);
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
            
            txtcantidadDetalleventa.setText(JTABLE_Mant_Detalle_venta.getValueAt(fila, 6).toString());
            txtprecioventa.setText(JTABLE_Mant_Detalle_venta.getValueAt(fila, 7).toString());
            
            // Al hacer clic en un registro: se desactiva Guardar y se activa Modificar
            gestionarEstadosBotones(true, false, true);
        }
    }//GEN-LAST:event_JTABLE_Mant_Detalle_ventaMouseClicked

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
        java.awt.EventQueue.invokeLater(() -> new frm_DetalleVenta().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Cerrar1;
    private javax.swing.JButton BTN_EXCEL;
    private javax.swing.JButton BTN_EXCEL1;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_VerSubdetalleventa;
    private javax.swing.JTable JTABLE_Mant_Detalle_venta;
    private javax.swing.JTextField TXT_BUSCAR_Detalle_venta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcomboboxIdproducto;
    private javax.swing.JComboBox<String> jcomboboxIdventa;
    private javax.swing.JTextField txtcantidadDetalleventa;
    private javax.swing.JTextField txtcodigoDetalleventa;
    private javax.swing.JTextField txtprecioventa;
    // End of variables declaration//GEN-END:variables
private void cargarCombosVentas() {
        try {
            jcomboboxIdventa.removeAllItems();
            jcomboboxIdventa.addItem("<<Seleccionar Venta>>");
            ResultSet rs = con.combobox_listarVenta();
            while (rs != null && rs.next()) {
                int id = rs.getInt("id_venta");
                String fecha = rs.getString("fecha");
                jcomboboxIdventa.addItem(id + " - Venta (" + fecha + ")");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar ventas: " + e.getMessage());
        }
    }

    private void cargarCombosProductos() {
        try {
            jcomboboxIdproducto.removeAllItems();
            jcomboboxIdproducto.addItem("<<Seleccionar Producto>>");
            ResultSet rs = con.combobox_ListarProductos();
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
            ResultSet rs = con.listar_DetalleVenta(); 
            while (rs != null && rs.next()) {
                Object fila[] = {
                    rs.getInt("id_detalle_venta"),
                    rs.getInt("id_venta"),
                    rs.getString("fecha_venta"),
                    rs.getInt("id_producto"),
                    rs.getString("codigo_producto"),
                    rs.getString("nombre_producto"),
                    rs.getInt("cantidad"),
                    rs.getDouble("precio_venta"),
                    rs.getDouble("subtotal")
                };
                dtm.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al listar detalles de venta: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        txtcodigoDetalleventa.setText("");
        txtcantidadDetalleventa.setText("");
        txtprecioventa.setText("");
        if (jcomboboxIdventa.getItemCount() > 0) jcomboboxIdventa.setSelectedIndex(0);
        if (jcomboboxIdproducto.getItemCount() > 0) jcomboboxIdproducto.setSelectedIndex(0);
        TXT_BUSCAR_Detalle_venta.setText("");
        txtcantidadDetalleventa.requestFocus();
    }

    // Método centralizado para controlar los estados dinámicos de los botones
    private void gestionarEstadosBotones(boolean nuevo, boolean guardar, boolean modificar) {
        BTN_Nuevo.setEnabled(nuevo);
        BTN_Guardar.setEnabled(guardar);
        BTN_Modificar.setEnabled(modificar);
    }
}
