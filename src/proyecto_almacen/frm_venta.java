
package proyecto_almacen;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frm_venta extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frm_venta.class.getName());
    /* Modelo para mostrar datos en la tabla */
    DefaultTableModel modeloTablaVenta = new DefaultTableModel();

    /* Objeto de conexión a la base de datos */
    conexionBD_gabriel conexionBD;
    
    public frm_venta() {
        setUndecorated(true);
        initComponents();
        txt_idventa.setEnabled(false);
        txt_total.setEditable(true);
        jComboBox_cliente.setEnabled(true);
        jComboBox_almacen.setEnabled(true);
        jComboBox_Personal.setEnabled(true);

        BTN_Guardar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
        
        setLocationRelativeTo(null);
        // Crear la conexión al iniciar el formulario
        conexionBD = new conexionBD_gabriel();

        // Verificar conexión
        if (conexionBD.getConnection() == null) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar con la base de datos.",
                    "Error de conexión", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Cargar los ComboBoxes desde la BD
        this.cargarClientes();
        this.cargarAlmacenes();
        this.cargarPersonal();

        // Encabezados de la tabla
        String titulos[] = {
            "ID Venta", "ID Cliente", "Cliente", "ID Almacen", 
            "Almacen", "ID Personal", "Personal", "Total", "Fecha Reg.", "Estado"
        };

        // Asignar los títulos al modelo
        modeloTablaVenta.setColumnIdentifiers(titulos);
        JTABLE_Mant_venta.setModel(modeloTablaVenta);

        // Ocultar columnas de IDs para mantener limpia la interfaz
        ocultarColumna(0);
        ocultarColumna(1); // ID Cliente
        ocultarColumna(3); // ID Almacen
        ocultarColumna(5); // ID Personal
        ocultarColumna(9);//estado
        txt_idventa.setEnabled(false);
    }

    private void ocultarColumna(int index) {
        JTABLE_Mant_venta.getColumnModel().getColumn(index).setMinWidth(0);
        JTABLE_Mant_venta.getColumnModel().getColumn(index).setMaxWidth(0);
        JTABLE_Mant_venta.getColumnModel().getColumn(index).setWidth(0);
    }

    /* Métodos para llenar los JComboBoxes */
    private void cargarClientes() {
    jComboBox_cliente.removeAllItems();
    jComboBox_cliente.addItem("-- Seleccionar --");
    try {
        // Se utiliza la vista vw_venta_activa para obtener los clientes de forma segura
        String sql = "SELECT DISTINCT id_cliente, nombre_cliente FROM vw_venta_activa"; 
        Statement st = conexionBD.getConnection().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            jComboBox_cliente.addItem(rs.getInt("id_cliente") + " - " + rs.getString("nombre_cliente"));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al cargar clientes:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private void cargarAlmacenes() {
        jComboBox_almacen.removeAllItems();
        jComboBox_almacen.addItem("-- Seleccionar --");
        try {
            String sql = "SELECT id_almacen, nombre_almacen FROM almacen"; 
            Statement st = conexionBD.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                jComboBox_almacen.addItem(rs.getInt("id_almacen") + " - " + rs.getString("nombre_almacen"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar almacenes:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarPersonal() {
        jComboBox_Personal.removeAllItems();
        jComboBox_Personal.addItem("-- Seleccionar --");
        try {
            String sql = "SELECT id_personal, CONCAT(nombres, ' ', apellidos) AS nombre_completo FROM personal"; 
            Statement st = conexionBD.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                jComboBox_Personal.addItem(rs.getInt("id_personal") + " - " + rs.getString("nombre_completo"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar personal:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /* Métodos auxiliares para obtener IDs seleccionados */
    private int obtenerIdSeleccionado(javax.swing.JComboBox<String> combo) {
        if (combo.getSelectedIndex() <= 0) {
            return 0;
        }
        String item = combo.getSelectedItem().toString();
        String[] partes = item.split(" - ");
        return Integer.parseInt(partes[0]);
    }
        
    /* Método para seleccionar el ítem del JComboBox según el ID recibido */
    private void seleccionarComboPorId(javax.swing.JComboBox<String> combo, int id) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            String item = combo.getItemAt(i);
            if (item != null && item.startsWith(id + " - ")) {
                combo.setSelectedIndex(i);
                break;
            }
        }
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
        txt_total = new javax.swing.JTextField();
        txt_idventa = new javax.swing.JTextField();
        txt_fecha = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox_Personal = new javax.swing.JComboBox<>();
        jComboBox_cliente = new javax.swing.JComboBox<>();
        jComboBox_almacen = new javax.swing.JComboBox<>();
        BTN_Verventa = new javax.swing.JButton();
        BTN_Nuevo = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        BTN_Modificar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        TXT_BuscarVenta = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Mant_venta = new javax.swing.JTable();
        BTN_PDF = new javax.swing.JButton();
        BTN_EXCEL = new javax.swing.JButton();
        BTN_Cerrar1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Total");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        txt_total.addActionListener(this::txt_totalActionPerformed);
        jPanel2.add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 190, 30));

        txt_idventa.addActionListener(this::txt_idventaActionPerformed);
        jPanel2.add(txt_idventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 190, 30));

        txt_fecha.addActionListener(this::txt_fechaActionPerformed);
        jPanel2.add(txt_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 190, 30));

        jLabel3.setText("ID Venta");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        jLabel4.setText("Fecha");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        jLabel5.setText("Personal");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, -1, -1));

        jLabel6.setText("Cliente");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, -1));

        jLabel7.setText("Almacen");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, -1, -1));

        jComboBox_Personal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(jComboBox_Personal, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, 180, 30));

        jComboBox_cliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(jComboBox_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 180, 30));

        jComboBox_almacen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(jComboBox_almacen, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 180, 30));

        BTN_Verventa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Verventa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/papel.png"))); // NOI18N
        BTN_Verventa.setText("VER VENTA");
        BTN_Verventa.addActionListener(this::BTN_VerventaActionPerformed);
        jPanel2.add(BTN_Verventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 30, 180, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 870, 160));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/registro.png"))); // NOI18N
        BTN_Nuevo.setText("NUEVO");
        BTN_Nuevo.addActionListener(this::BTN_NuevoActionPerformed);
        jPanel1.add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 190, 50));

        BTN_Guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disco-flexible (1).png"))); // NOI18N
        BTN_Guardar.setText("GUARDAR");
        BTN_Guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_GuardarMouseClicked(evt);
            }
        });
        BTN_Guardar.addActionListener(this::BTN_GuardarActionPerformed);
        jPanel1.add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, 190, 50));

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ahorrar.png"))); // NOI18N
        BTN_Modificar.setText("MODIFICAR");
        BTN_Modificar.addActionListener(this::BTN_ModificarActionPerformed);
        jPanel1.add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 180, 200, 50));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Ingresar el Nombre de Venta");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 30));

        TXT_BuscarVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_BuscarVentaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_BuscarVentaKeyTyped(evt);
            }
        });
        jPanel3.add(TXT_BuscarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 290, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        jLabel9.setText("BUSCAR");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 120, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 810, 50));

        JTABLE_Mant_venta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_Mant_venta.setForeground(new java.awt.Color(0, 0, 204));
        JTABLE_Mant_venta.setModel(new javax.swing.table.DefaultTableModel(
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
        JTABLE_Mant_venta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_Mant_ventaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTABLE_Mant_venta);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 810, 220));

        BTN_PDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/archivo-de-acrobat-reader.png"))); // NOI18N
        BTN_PDF.setText("Exportar");
        BTN_PDF.addActionListener(this::BTN_PDFActionPerformed);
        jPanel1.add(BTN_PDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 520, 120, 40));

        BTN_EXCEL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        BTN_EXCEL.setText("Exportar");
        BTN_EXCEL.addActionListener(this::BTN_EXCELActionPerformed);
        jPanel1.add(BTN_EXCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 520, 120, 40));

        BTN_Cerrar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cerrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrado.png"))); // NOI18N
        BTN_Cerrar1.setText("Cerrar");
        BTN_Cerrar1.addActionListener(this::BTN_Cerrar1ActionPerformed);
        jPanel1.add(BTN_Cerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 520, 130, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 890, 570));

        jLabel1.setText("MANTENIMIENTO DE VENTA");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalActionPerformed

    private void txt_idventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idventaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idventaActionPerformed

    private void txt_fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_fechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fechaActionPerformed

    private void BTN_VerventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VerventaActionPerformed
        this.MostrarVentas();
    }//GEN-LAST:event_BTN_VerventaActionPerformed

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
    txt_idventa.setText("");
        txt_total.setText("");
        if (jComboBox_cliente.getItemCount() > 0) jComboBox_cliente.setSelectedIndex(0);
        if (jComboBox_almacen.getItemCount() > 0) jComboBox_almacen.setSelectedIndex(0);
        if (jComboBox_Personal.getItemCount() > 0) jComboBox_Personal.setSelectedIndex(0);

        txt_total.requestFocus();
        
        BTN_Guardar.setEnabled(true);
        BTN_Modificar.setEnabled(false);
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
        int idCliente = obtenerIdSeleccionado(jComboBox_cliente);
        int idAlmacen = obtenerIdSeleccionado(jComboBox_almacen);
        int idPersonal = obtenerIdSeleccionado(jComboBox_Personal);
        String totalStr = txt_total.getText().trim();

        if (idCliente == 0 || idAlmacen == 0 || idPersonal == 0 || totalStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos requeridos.",
                    "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double total = Double.parseDouble(totalStr);

            int respuesta = JOptionPane.showConfirmDialog(null,
                    "¿Desea registrar esta Venta?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (respuesta == JOptionPane.YES_OPTION) {
                conexionBD.insertarVenta(idCliente, idAlmacen, idPersonal, total);

                JOptionPane.showMessageDialog(null, "Venta registrada correctamente",
                        "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);

                this.MostrarVentas();

                txt_idventa.setText("");
                txt_total.setText("");
                jComboBox_cliente.setSelectedIndex(0);
                jComboBox_almacen.setSelectedIndex(0);
                jComboBox_Personal.setSelectedIndex(0);
                
                BTN_Guardar.setEnabled(false);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese un monto válido para el total.",
                    "Error de entrada", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar la Venta:\n"
                    + ex.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
        String codStr = txt_idventa.getText().trim();
        int idCliente = obtenerIdSeleccionado(jComboBox_cliente);
        int idAlmacen = obtenerIdSeleccionado(jComboBox_almacen);
        int idPersonal = obtenerIdSeleccionado(jComboBox_Personal);
        String totalStr = txt_total.getText().trim();

        if (codStr.isEmpty() || idCliente == 0 || idAlmacen == 0 || idPersonal == 0 || totalStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione una venta y complete todos los campos.",
                    "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int idVenta = Integer.parseInt(codStr);
            double total = Double.parseDouble(totalStr);

            int respuesta = JOptionPane.showConfirmDialog(null,
                    "¿Desea modificar esta venta?", "Confirmación",
                    JOptionPane.YES_NO_OPTION);

            if (respuesta == JOptionPane.YES_OPTION) {
                conexionBD.modificarVenta(idVenta, idCliente, idAlmacen, idPersonal, total);

                JOptionPane.showMessageDialog(null, "Venta modificada correctamente",
                        "Modificación exitosa", JOptionPane.INFORMATION_MESSAGE);

                this.MostrarVentas();

                txt_idventa.setText("");
                txt_total.setText("");
                jComboBox_cliente.setSelectedIndex(0);
                jComboBox_almacen.setSelectedIndex(0);
                jComboBox_Personal.setSelectedIndex(0);
                
                BTN_Modificar.setEnabled(false);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese un monto válido para el total.",
                    "Error de entrada", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar la venta:\n"
                    + ex.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BTN_ModificarActionPerformed

    private void TXT_BuscarVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarVentaKeyReleased
        this.buscarVentas();
    }//GEN-LAST:event_TXT_BuscarVentaKeyReleased

    private void TXT_BuscarVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarVentaKeyTyped

    }//GEN-LAST:event_TXT_BuscarVentaKeyTyped

    private void JTABLE_Mant_ventaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_ventaMouseClicked
        int filaSeleccionada = JTABLE_Mant_venta.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String codigo = JTABLE_Mant_venta.getValueAt(filaSeleccionada, 0).toString();
            int idCliente = Integer.parseInt(JTABLE_Mant_venta.getValueAt(filaSeleccionada, 1).toString());
            int idAlmacen = Integer.parseInt(JTABLE_Mant_venta.getValueAt(filaSeleccionada, 3).toString());
            int idPersonal = Integer.parseInt(JTABLE_Mant_venta.getValueAt(filaSeleccionada, 5).toString());
            String total = JTABLE_Mant_venta.getValueAt(filaSeleccionada, 7) != null ? JTABLE_Mant_venta.getValueAt(filaSeleccionada, 7).toString() : "0.0";

            txt_idventa.setText(codigo);
            txt_total.setText(total);

            // Posicionar Combos
            seleccionarComboPorId(jComboBox_cliente, idCliente);
            seleccionarComboPorId(jComboBox_almacen, idAlmacen);
            seleccionarComboPorId(jComboBox_Personal, idPersonal);

            BTN_Modificar.setEnabled(true);
            BTN_Guardar.setEnabled(false);
        }
    }//GEN-LAST:event_JTABLE_Mant_ventaMouseClicked

    private void BTN_PDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_PDFActionPerformed

    }//GEN-LAST:event_BTN_PDFActionPerformed

    private void BTN_EXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EXCELActionPerformed

    }//GEN-LAST:event_BTN_EXCELActionPerformed

    private void BTN_Cerrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_Cerrar1ActionPerformed
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas cerrar el formulario?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                conexionBD.cerrarConexion();
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
        java.awt.EventQueue.invokeLater(() -> new frm_venta().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Cerrar1;
    private javax.swing.JButton BTN_EXCEL;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_PDF;
    private javax.swing.JButton BTN_Verventa;
    private javax.swing.JTable JTABLE_Mant_venta;
    private javax.swing.JTextField TXT_BuscarVenta;
    private javax.swing.JComboBox<String> jComboBox_Personal;
    private javax.swing.JComboBox<String> jComboBox_almacen;
    private javax.swing.JComboBox<String> jComboBox_cliente;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_fecha;
    private javax.swing.JTextField txt_idventa;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables
public void MostrarVentas() {
        JTABLE_Mant_venta.setAutoCreateRowSorter(true);
        modeloTablaVenta.setRowCount(0);
        try {
            ResultSet rs = conexionBD.listarVentas();
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("id_venta"),
                    rs.getInt("id_cliente"),
                    rs.getString("nombre_cliente"),
                    rs.getInt("id_almacen"),
                    rs.getString("nombre_almacen"),
                    rs.getInt("id_personal"),
                    rs.getString("nombre_personal"),
                    rs.getDouble("total"),
                    rs.getTimestamp("fecha"),
                    1 // Estado activo por defecto si no viene en la vista
                };
                modeloTablaVenta.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar Ventas:\n" + e.getMessage(),
                    "Error de consulta", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buscarVentas() {
        modeloTablaVenta.setRowCount(0);
        String busqueda = TXT_BuscarVenta.getText().trim();
        try {
            ResultSet rs = conexionBD.buscarVentas(busqueda);
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("id_venta"),
                    rs.getInt("id_cliente"),
                    rs.getString("nombre_cliente"),
                    rs.getInt("id_almacen"),
                    rs.getString("nombre_almacen"),
                    rs.getInt("id_personal"),
                    rs.getString("nombre_personal"),
                    rs.getDouble("total"),
                    rs.getTimestamp("fecha"),
                    1
                };
                modeloTablaVenta.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar Ventas:\n" + e.getMessage(),
                    "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
        }
    }
}
