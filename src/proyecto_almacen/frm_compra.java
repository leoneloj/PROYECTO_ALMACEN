
package proyecto_almacen;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frm_compra extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frm_compra.class.getName());
    /* Modelo para mostrar datos en la tabla */
    DefaultTableModel modeloTablaCompra = new DefaultTableModel();

    /* Objeto de conexión a la base de datos */
    conexionBD_gabriel conexionBD;
    
    public frm_compra() {
        setUndecorated(true);
        initComponents();
        
        txt_idcompra.setEnabled(false);
        txt_total.setEditable(true);
        jComboBox_proveedor.setEnabled(true);
        jComboBox_almacen.setEnabled(true);
        jComboBox_personal.setEnabled(true);

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
        this.cargarProveedores();
        this.cargarAlmacenes();
        this.cargarPersonal();

        // Encabezados de la tabla
        String titulos[] = {
            "ID Compra", "ID Proveedor", "Proveedor", "ID Almacen", 
            "Almacen", "ID Personal", "Personal", "Total", "Fecha Reg.", "Estado"
        };

        // Asignar los títulos al modelo
        modeloTablaCompra.setColumnIdentifiers(titulos);
        JTABLE_Mant_compra.setModel(modeloTablaCompra);

        // Ocultar columnas de IDs para mantener limpia la interfaz
        ocultarColumna(0);
        ocultarColumna(1); // ID Proveedor
        ocultarColumna(3); // ID Almacen
        ocultarColumna(5); // ID Personal
        ocultarColumna(9);
        
        txt_idcompra.setEnabled(false);
    }

    private void ocultarColumna(int index) {
        JTABLE_Mant_compra.getColumnModel().getColumn(index).setMinWidth(0);
        JTABLE_Mant_compra.getColumnModel().getColumn(index).setMaxWidth(0);
        JTABLE_Mant_compra.getColumnModel().getColumn(index).setWidth(0);
    }

/* Métodos para llenar los JComboBoxes */
    private void cargarProveedores() {
        jComboBox_proveedor.removeAllItems();
        jComboBox_proveedor.addItem("-- Seleccionar --");
        try {
            // Se quitó la columna razon_social y la condición estado
            String sql = "SELECT id_proveedor, nombre FROM proveedor"; 
            Statement st = conexionBD.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                jComboBox_proveedor.addItem(rs.getInt("id_proveedor") + " - " + rs.getString("nombre"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar proveedores:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        jComboBox_personal.removeAllItems();
        jComboBox_personal.addItem("-- Seleccionar --");
        try {
            // Se quitó la condición WHERE estado = 1
            String sql = "SELECT id_personal, CONCAT(nombres, ' ', apellidos) AS nombre_completo FROM personal"; 
            Statement st = conexionBD.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                jComboBox_personal.addItem(rs.getInt("id_personal") + " - " + rs.getString("nombre_completo"));
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_idcompra = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_fecha1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox_personal = new javax.swing.JComboBox<>();
        jComboBox_proveedor = new javax.swing.JComboBox<>();
        jComboBox_almacen = new javax.swing.JComboBox<>();
        BTN_VerCompra = new javax.swing.JButton();
        BTN_Nuevo = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        BTN_Modificar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        TXT_BuscarCompra = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Mant_compra = new javax.swing.JTable();
        BTN_PDF = new javax.swing.JButton();
        BTN_EXCEL = new javax.swing.JButton();
        BTN_Cerrar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("MANTENIMIENTO DE COMPRA");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Total");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));
        jPanel2.add(txt_idcompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 230, 30));

        jLabel3.setText("ID Compra");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        txt_total.addActionListener(this::txt_totalActionPerformed);
        jPanel2.add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 230, 30));

        jLabel4.setText("Fecha");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        txt_fecha1.setEditable(false);
        txt_fecha1.addActionListener(this::txt_fecha1ActionPerformed);
        jPanel2.add(txt_fecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 230, 30));

        jLabel5.setText("ID Personal");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, -1, -1));

        jLabel6.setText("ID Proveedor");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, -1, -1));

        jLabel7.setText("ID Almacen");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, -1, -1));

        jComboBox_personal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(jComboBox_personal, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, 170, 30));

        jComboBox_proveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(jComboBox_proveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 170, 30));

        jComboBox_almacen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(jComboBox_almacen, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 170, 30));

        BTN_VerCompra.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_VerCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/papel.png"))); // NOI18N
        BTN_VerCompra.setText("VER COMPRA");
        BTN_VerCompra.addActionListener(this::BTN_VerCompraActionPerformed);
        jPanel2.add(BTN_VerCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, 180, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 850, 200));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/registro.png"))); // NOI18N
        BTN_Nuevo.setText("NUEVO");
        BTN_Nuevo.addActionListener(this::BTN_NuevoActionPerformed);
        jPanel1.add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 190, 50));

        BTN_Guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disco-flexible (1).png"))); // NOI18N
        BTN_Guardar.setText("GUARDAR");
        BTN_Guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_GuardarMouseClicked(evt);
            }
        });
        BTN_Guardar.addActionListener(this::BTN_GuardarActionPerformed);
        jPanel1.add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 240, 190, 50));

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ahorrar.png"))); // NOI18N
        BTN_Modificar.setText("MODIFICAR");
        BTN_Modificar.addActionListener(this::BTN_ModificarActionPerformed);
        jPanel1.add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 240, 200, 50));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Ingresar el Nombre de Compra");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 30));

        TXT_BuscarCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_BuscarCompraKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_BuscarCompraKeyTyped(evt);
            }
        });
        jPanel3.add(TXT_BuscarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 290, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        jLabel9.setText("BUSCAR");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 120, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 810, 50));

        JTABLE_Mant_compra.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_Mant_compra.setForeground(new java.awt.Color(0, 0, 204));
        JTABLE_Mant_compra.setModel(new javax.swing.table.DefaultTableModel(
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
        JTABLE_Mant_compra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_Mant_compraMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTABLE_Mant_compra);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 810, 220));

        BTN_PDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/archivo-de-acrobat-reader.png"))); // NOI18N
        BTN_PDF.setText("Exportar");
        BTN_PDF.addActionListener(this::BTN_PDFActionPerformed);
        jPanel1.add(BTN_PDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 600, 120, 40));

        BTN_EXCEL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        BTN_EXCEL.setText("Exportar");
        BTN_EXCEL.addActionListener(this::BTN_EXCELActionPerformed);
        jPanel1.add(BTN_EXCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 600, 120, 40));

        BTN_Cerrar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cerrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrado.png"))); // NOI18N
        BTN_Cerrar1.setText("Cerrar");
        BTN_Cerrar1.addActionListener(this::BTN_Cerrar1ActionPerformed);
        jPanel1.add(BTN_Cerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 600, 130, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalActionPerformed

    private void txt_fecha1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_fecha1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fecha1ActionPerformed

    private void BTN_VerCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VerCompraActionPerformed
        this.MostrarCompras();
    }//GEN-LAST:event_BTN_VerCompraActionPerformed

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
        txt_idcompra.setText("");
        txt_total.setText("");
        if (jComboBox_proveedor.getItemCount() > 0) jComboBox_proveedor.setSelectedIndex(0);
        if (jComboBox_almacen.getItemCount() > 0) jComboBox_almacen.setSelectedIndex(0);
        if (jComboBox_personal.getItemCount() > 0) jComboBox_personal.setSelectedIndex(0);

        txt_total.requestFocus();
        
        BTN_Guardar.setEnabled(true);
        BTN_Modificar.setEnabled(false);
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
        int idProveedor = obtenerIdSeleccionado(jComboBox_proveedor);
        int idAlmacen = obtenerIdSeleccionado(jComboBox_almacen);
        int idPersonal = obtenerIdSeleccionado(jComboBox_personal);
        String totalStr = txt_total.getText().trim();

        if (idProveedor == 0 || idAlmacen == 0 || idPersonal == 0 || totalStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos requeridos.",
                    "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double total = Double.parseDouble(totalStr);

            int respuesta = JOptionPane.showConfirmDialog(null,
                    "¿Desea registrar esta Compra?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (respuesta == JOptionPane.YES_OPTION) {
                conexionBD.insertarCompra(idProveedor, idAlmacen, idPersonal, total);

                JOptionPane.showMessageDialog(null, "Compra registrada correctamente",
                        "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);

                this.MostrarCompras();

                txt_idcompra.setText("");
                txt_total.setText("");
                jComboBox_proveedor.setSelectedIndex(0);
                jComboBox_almacen.setSelectedIndex(0);
                jComboBox_personal.setSelectedIndex(0);
                
                BTN_Guardar.setEnabled(false);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese un monto válido para el total.",
                    "Error de entrada", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar la Compra:\n"
                    + ex.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
        String codStr = txt_idcompra.getText().trim();
        int idProveedor = obtenerIdSeleccionado(jComboBox_proveedor);
        int idAlmacen = obtenerIdSeleccionado(jComboBox_almacen);
        int idPersonal = obtenerIdSeleccionado(jComboBox_personal);
        String totalStr = txt_total.getText().trim();

        if (codStr.isEmpty() || idProveedor == 0 || idAlmacen == 0 || idPersonal == 0 || totalStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione una compra y complete todos los campos.",
                    "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int idCompra = Integer.parseInt(codStr);
            double total = Double.parseDouble(totalStr);

            int respuesta = JOptionPane.showConfirmDialog(null,
                    "¿Desea modificar esta compra?", "Confirmación",
                    JOptionPane.YES_NO_OPTION);

            if (respuesta == JOptionPane.YES_OPTION) {
                conexionBD.modificarCompra(idCompra, idProveedor, idAlmacen, idPersonal, total);

                JOptionPane.showMessageDialog(null, "Compra modificada correctamente",
                        "Modificación exitosa", JOptionPane.INFORMATION_MESSAGE);

                this.MostrarCompras();

                txt_idcompra.setText("");
                txt_total.setText("");
                jComboBox_proveedor.setSelectedIndex(0);
                jComboBox_almacen.setSelectedIndex(0);
                jComboBox_personal.setSelectedIndex(0);
                
                BTN_Modificar.setEnabled(false);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese un monto válido para el total.",
                    "Error de entrada", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar la compra:\n"
                    + ex.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BTN_ModificarActionPerformed

    private void TXT_BuscarCompraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarCompraKeyReleased
        this.buscarCompras();
    }//GEN-LAST:event_TXT_BuscarCompraKeyReleased

    private void TXT_BuscarCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarCompraKeyTyped

    }//GEN-LAST:event_TXT_BuscarCompraKeyTyped

    private void JTABLE_Mant_compraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_compraMouseClicked
        int filaSeleccionada = JTABLE_Mant_compra.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String codigo = JTABLE_Mant_compra.getValueAt(filaSeleccionada, 0).toString();
            int idProveedor = Integer.parseInt(JTABLE_Mant_compra.getValueAt(filaSeleccionada, 1).toString());
            int idAlmacen = Integer.parseInt(JTABLE_Mant_compra.getValueAt(filaSeleccionada, 3).toString());
            int idPersonal = Integer.parseInt(JTABLE_Mant_compra.getValueAt(filaSeleccionada, 5).toString());
            String total = JTABLE_Mant_compra.getValueAt(filaSeleccionada, 7) != null ? JTABLE_Mant_compra.getValueAt(filaSeleccionada, 7).toString() : "0.0";

            txt_idcompra.setText(codigo);
            txt_total.setText(total);

            // Posicionar Combos
            seleccionarComboPorId(jComboBox_proveedor, idProveedor);
            seleccionarComboPorId(jComboBox_almacen, idAlmacen);
            seleccionarComboPorId(jComboBox_personal, idPersonal);

            BTN_Modificar.setEnabled(true);
            BTN_Guardar.setEnabled(false);
            
        }
    }//GEN-LAST:event_JTABLE_Mant_compraMouseClicked

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
        java.awt.EventQueue.invokeLater(() -> new frm_compra().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Cerrar1;
    private javax.swing.JButton BTN_EXCEL;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_PDF;
    private javax.swing.JButton BTN_VerCompra;
    private javax.swing.JTable JTABLE_Mant_compra;
    private javax.swing.JTextField TXT_BuscarCompra;
    private javax.swing.JComboBox<String> jComboBox_almacen;
    private javax.swing.JComboBox<String> jComboBox_personal;
    private javax.swing.JComboBox<String> jComboBox_proveedor;
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
    private javax.swing.JTextField txt_fecha1;
    private javax.swing.JTextField txt_idcompra;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables
public void MostrarCompras() {
        JTABLE_Mant_compra.setAutoCreateRowSorter(true);
        modeloTablaCompra.setRowCount(0);
        try {
            ResultSet rs = conexionBD.listarCompras();
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("id_compra"),
                    rs.getInt("id_proveedor"),
                    rs.getString("nombre_proveedor"),
                    rs.getInt("id_almacen"),
                    rs.getString("nombre_almacen"),
                    rs.getInt("id_personal"),
                    rs.getString("nombre_personal"),
                    rs.getDouble("total"),
                    rs.getTimestamp("fecha"),
                    1 // Estado activo por defecto si no viene en la vista
                };
                modeloTablaCompra.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar Compras:\n" + e.getMessage(),
                    "Error de consulta", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buscarCompras() {
        modeloTablaCompra.setRowCount(0);
        String busqueda = TXT_BuscarCompra.getText().trim();
        try {
            ResultSet rs = conexionBD.buscarCompras(busqueda);
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("id_compra"),
                    rs.getInt("id_proveedor"),
                    rs.getString("nombre_proveedor"),
                    rs.getInt("id_almacen"),
                    rs.getString("nombre_almacen"),
                    rs.getInt("id_personal"),
                    rs.getString("nombre_personal"),
                    rs.getDouble("total"),
                    rs.getTimestamp("fecha"),
                    1
                };
                modeloTablaCompra.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar Compras:\n" + e.getMessage(),
                    "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
        }
    }
}
