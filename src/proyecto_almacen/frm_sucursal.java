package proyecto_almacen;

import java.sql.*; // Librerías para conexión con MySQL
import javax.swing.table.DefaultTableModel; // Para trabajar con JTable
import javax.swing.JOptionPane;

public class frm_sucursal extends javax.swing.JFrame {

    DefaultTableModel dtm = new DefaultTableModel();
    conexionBD conexionBD;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frm_sucursal.class.getName());

    public frm_sucursal() {
        setUndecorated(true);

        initComponents();
        setLocationRelativeTo(null);

        // 1. Configuración del Placeholder para la búsqueda
        TXT_BUSCAR_SUCURSAL.setText("Ingrese nombre de sucursal o empresa...");
        TXT_BUSCAR_SUCURSAL.setForeground(new java.awt.Color(153, 153, 153)); // Color gris tenue

        TXT_BUSCAR_SUCURSAL.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (TXT_BUSCAR_SUCURSAL.getText().equals("Ingrese nombre de sucursal o empresa...")) {
                    TXT_BUSCAR_SUCURSAL.setText("");
                    TXT_BUSCAR_SUCURSAL.setForeground(new java.awt.Color(0, 0, 0)); // Color negro normal
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (TXT_BUSCAR_SUCURSAL.getText().trim().isEmpty()) {
                    TXT_BUSCAR_SUCURSAL.setText("Ingrese nombre de sucursal o empresa...");
                    TXT_BUSCAR_SUCURSAL.setForeground(new java.awt.Color(153, 153, 153));
                }
            }
        });

        // 2. Desactivar botones y campos iniciales (siguiendo el estándar del ejemplo de docentes)[cite: 1]
        txtcodigosucursal.setEnabled(false);
        BTN_Guardar.setEnabled(false);
        BTN_Modificar.setEnabled(false);

        // 3. Instanciación de la conexión a la base de datos
        conexionBD = new conexionBD();

        // 4. Método para cargar las empresas al JComboBox
        this.cargarEmpresas();

        // 5. Definir los encabezados de la tabla basados en la vista vw_sucursal_activa
        String titulos[] = {"ID Sucursal", "ID Empresa", "Empresa", "Nombre Sucursal", "Dirección", "Teléfono"};
        dtm.setColumnIdentifiers(titulos);
        JTABLE_Mant_Sucursal.setModel(dtm);

        // 6. Ocultar la columna ID Sucursal (Índice 0)
        JTABLE_Mant_Sucursal.getColumnModel().getColumn(0).setMinWidth(0);
        JTABLE_Mant_Sucursal.getColumnModel().getColumn(0).setMaxWidth(0);
        JTABLE_Mant_Sucursal.getColumnModel().getColumn(0).setWidth(0);

        // 7. Ocultar la columna ID Empresa (Índice 1)
        JTABLE_Mant_Sucursal.getColumnModel().getColumn(1).setMinWidth(0);
        JTABLE_Mant_Sucursal.getColumnModel().getColumn(1).setMaxWidth(0);
        JTABLE_Mant_Sucursal.getColumnModel().getColumn(1).setWidth(0);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jcomboboxsucursal = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtdireccionsucursal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtcodigosucursal = new javax.swing.JTextField();
        txtnombresucursal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        BTN_VerSucursal = new javax.swing.JButton();
        BTN_Modificar = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        BTN_Nuevo = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TXT_BUSCAR_SUCURSAL = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Mant_Sucursal = new javax.swing.JTable();
        BTN_Cerrar1 = new javax.swing.JButton();
        BTN_EXCEL1 = new javax.swing.JButton();
        BTN_EXCEL = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("MANTENIMIENTO SUCURSAL");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 51), null));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Codigo sucursal");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jcomboboxsucursal.setForeground(new java.awt.Color(0, 0, 153));
        jcomboboxsucursal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(jcomboboxsucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 420, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Nombre sucursal");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        txtdireccionsucursal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtdireccionsucursal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtdireccionsucursal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdireccionsucursalKeyTyped(evt);
            }
        });
        jPanel3.add(txtdireccionsucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 360, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Telefono");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        txttelefono.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txttelefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefonoKeyTyped(evt);
            }
        });
        jPanel3.add(txttelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 360, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Direccion");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        txtcodigosucursal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtcodigosucursal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(txtcodigosucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 360, -1));

        txtnombresucursal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtnombresucursal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnombresucursal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombresucursalKeyTyped(evt);
            }
        });
        jPanel3.add(txtnombresucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 360, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Empresa");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, -1, -1));

        BTN_VerSucursal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BTN_VerSucursal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/papel.png"))); // NOI18N
        BTN_VerSucursal.setText("Listar sucursal");
        BTN_VerSucursal.addActionListener(this::BTN_VerSucursalActionPerformed);
        jPanel3.add(BTN_VerSucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 60, 200, 60));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 1090, 160));

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ahorrar.png"))); // NOI18N
        BTN_Modificar.setText("MODIFICAR");
        BTN_Modificar.addActionListener(this::BTN_ModificarActionPerformed);
        jPanel2.add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 230, 200, 50));

        BTN_Guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disco-flexible (1).png"))); // NOI18N
        BTN_Guardar.setText("GUARDAR");
        BTN_Guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_GuardarMouseClicked(evt);
            }
        });
        BTN_Guardar.addActionListener(this::BTN_GuardarActionPerformed);
        jPanel2.add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 230, 190, 50));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/registro.png"))); // NOI18N
        BTN_Nuevo.setText("NUEVO");
        BTN_Nuevo.addActionListener(this::BTN_NuevoActionPerformed);
        jPanel2.add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 230, 190, 50));

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

        TXT_BUSCAR_SUCURSAL.addActionListener(this::TXT_BUSCAR_SUCURSALActionPerformed);
        TXT_BUSCAR_SUCURSAL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_BUSCAR_SUCURSALKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_BUSCAR_SUCURSALKeyTyped(evt);
            }
        });
        jPanel4.add(TXT_BUSCAR_SUCURSAL, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 290, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 1090, 50));

        JTABLE_Mant_Sucursal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_Mant_Sucursal.setForeground(new java.awt.Color(0, 0, 153));
        JTABLE_Mant_Sucursal.setModel(new javax.swing.table.DefaultTableModel(
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
        JTABLE_Mant_Sucursal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_Mant_SucursalMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTABLE_Mant_Sucursal);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 1100, 180));

        BTN_Cerrar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cerrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrado.png"))); // NOI18N
        BTN_Cerrar1.setText("Cerrar");
        BTN_Cerrar1.addActionListener(this::BTN_Cerrar1ActionPerformed);
        jPanel2.add(BTN_Cerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 550, 130, 40));

        BTN_EXCEL1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        BTN_EXCEL1.setText("Exportar");
        BTN_EXCEL1.addActionListener(this::BTN_EXCEL1ActionPerformed);
        jPanel2.add(BTN_EXCEL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 550, 120, 40));

        BTN_EXCEL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/archivo-de-acrobat-reader.png"))); // NOI18N
        BTN_EXCEL.setText("Exportar");
        BTN_EXCEL.addActionListener(this::BTN_EXCELActionPerformed);
        jPanel2.add(BTN_EXCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 550, 120, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 1130, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtdireccionsucursalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionsucursalKeyTyped

    }//GEN-LAST:event_txtdireccionsucursalKeyTyped

    private void txttelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyTyped

    }//GEN-LAST:event_txttelefonoKeyTyped

    private void txtnombresucursalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombresucursalKeyTyped

    }//GEN-LAST:event_txtnombresucursalKeyTyped

    private void BTN_VerSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VerSucursalActionPerformed
        this.listarSucursales_Activas();
    }//GEN-LAST:event_BTN_VerSucursalActionPerformed

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
        try {
            String codigoStr = txtcodigosucursal.getText().trim();
            String nombreSucursal = txtnombresucursal.getText().trim();
            String direccion = txtdireccionsucursal.getText().trim();
            String telefono = txttelefono.getText().trim();
            String razonSocialEmpresa = (String) jcomboboxsucursal.getSelectedItem();

            if (codigoStr.isEmpty() || nombreSucursal.isEmpty() || direccion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos obligatorios.",
                        "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (razonSocialEmpresa == null || razonSocialEmpresa.equals("<<Seleccionar>>")) {
                JOptionPane.showMessageDialog(this, "Seleccione una empresa válida.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idSucursal = Integer.parseInt(codigoStr);
            int idEmpresa = conexionBD.obtenerCodigoEmpresa(razonSocialEmpresa);

            if (idEmpresa == -1) {
                JOptionPane.showMessageDialog(this, "La empresa seleccionada no es válida.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Llamada al método de modificación en conexionBD
            conexionBD.modificarSucursal(idSucursal, idEmpresa, nombreSucursal, direccion, telefono);

            JOptionPane.showMessageDialog(this, "Sucursal actualizada correctamente.", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            listarSucursales_Activas();
            limpiarCamposSucursal();

            BTN_Guardar.setEnabled(false);
            BTN_Modificar.setEnabled(false);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID de sucursal no válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al modificar la sucursal: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BTN_ModificarActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
        try {
            String nombreSucursal = txtnombresucursal.getText().trim();
            String direccion = txtdireccionsucursal.getText().trim();
            String telefono = txttelefono.getText().trim();
            String razonSocialEmpresa = (String) jcomboboxsucursal.getSelectedItem();

            if (nombreSucursal.isEmpty() || direccion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor complete los campos obligatorios (Nombre y Dirección).",
                        "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (razonSocialEmpresa == null || razonSocialEmpresa.equals("<<Seleccionar>>") || razonSocialEmpresa.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione una empresa válida.",
                        "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idEmpresa = conexionBD.obtenerCodigoEmpresa(razonSocialEmpresa);
            if (idEmpresa == -1) {
                JOptionPane.showMessageDialog(this, "No se encontró el código de la empresa seleccionada.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Llamada al método de inserción en conexionBD
            conexionBD.insertarSucursal(idEmpresa, nombreSucursal, direccion, telefono);

            JOptionPane.showMessageDialog(this, "Sucursal registrada correctamente.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

            this.listarSucursales_Activas();
            this.limpiarCamposSucursal();

            BTN_Guardar.setEnabled(false);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar la sucursal: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
        this.limpiarCamposSucursal();
        BTN_Guardar.setEnabled(true);
        BTN_Modificar.setEnabled(false);
        BTN_VerSucursal.setEnabled(true);
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void TXT_BUSCAR_SUCURSALActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_BUSCAR_SUCURSALActionPerformed

    }//GEN-LAST:event_TXT_BUSCAR_SUCURSALActionPerformed

    private void TXT_BUSCAR_SUCURSALKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BUSCAR_SUCURSALKeyReleased
        String texto = TXT_BUSCAR_SUCURSAL.getText().trim();

        if (texto.equals("Ingrese nombre de sucursal...") || texto.isEmpty()) {
            dtm.setRowCount(0);
            return;
        }

        dtm.setRowCount(0);

        try (ResultSet rs = conexionBD.buscarSucursal(texto)) {
            while (rs != null && rs.next()) {
                Object fila[] = {
                    rs.getInt("id_sucursal"),
                    rs.getInt("id_empresa"),
                    rs.getString("empresa_nombre"),
                    rs.getString("nombre_sucursal"),
                    rs.getString("direccion"),
                    rs.getString("telefono")
                };
                dtm.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar sucursales: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_TXT_BUSCAR_SUCURSALKeyReleased

    private void TXT_BUSCAR_SUCURSALKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BUSCAR_SUCURSALKeyTyped

    }//GEN-LAST:event_TXT_BUSCAR_SUCURSALKeyTyped

    private void JTABLE_Mant_SucursalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_SucursalMouseClicked
        int filaSeleccionada = JTABLE_Mant_Sucursal.getSelectedRow();
        if (filaSeleccionada == -1) {
            return;
        }

        BTN_Modificar.setEnabled(true);
        BTN_Guardar.setEnabled(false);

        try {
            txtcodigosucursal.setText(JTABLE_Mant_Sucursal.getValueAt(filaSeleccionada, 0).toString().trim());

            String empresaTabla = JTABLE_Mant_Sucursal.getValueAt(filaSeleccionada, 2).toString().trim();
            jcomboboxsucursal.setSelectedItem(empresaTabla);

            txtnombresucursal.setText(JTABLE_Mant_Sucursal.getValueAt(filaSeleccionada, 3).toString().trim());
            txtdireccionsucursal.setText(JTABLE_Mant_Sucursal.getValueAt(filaSeleccionada, 4).toString().trim());

            Object telObj = JTABLE_Mant_Sucursal.getValueAt(filaSeleccionada, 5);
            txttelefono.setText((telObj != null) ? telObj.toString().trim() : "");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al seleccionar el registro: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_JTABLE_Mant_SucursalMouseClicked

    private void BTN_Cerrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_Cerrar1ActionPerformed
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas cerrar el formulario?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                // Cerrar conexión si tienes un método cerrarConexion()
                conexionBD.cerrarConexion();
            } catch (Exception e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
            // Cierra el formulario actual
            dispose(); // o this.dispose() si estás dentro del formulario
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
        java.awt.EventQueue.invokeLater(() -> new frm_sucursal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Cerrar1;
    private javax.swing.JButton BTN_EXCEL;
    private javax.swing.JButton BTN_EXCEL1;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_VerSucursal;
    private javax.swing.JTable JTABLE_Mant_Sucursal;
    private javax.swing.JTextField TXT_BUSCAR_SUCURSAL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcomboboxsucursal;
    private javax.swing.JTextField txtcodigosucursal;
    private javax.swing.JTextField txtdireccionsucursal;
    private javax.swing.JTextField txtnombresucursal;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
// Métodos de apoyo lógicos (Cargar, Listar, Limpiar)
    private void cargarEmpresas() {
        try {
            jcomboboxsucursal.removeAllItems();
            jcomboboxsucursal.addItem("<<Seleccionar>>");

            try (ResultSet rs = conexionBD.combobox_ListarEmpresas()) {
                while (rs != null && rs.next()) {
                    String razonSocial = rs.getString("razon_social");
                    if (razonSocial != null) {
                        jcomboboxsucursal.addItem(razonSocial.trim());
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar empresas: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarSucursales_Activas() {
        JTABLE_Mant_Sucursal.setAutoCreateRowSorter(true);
        dtm.setRowCount(0);

        try (ResultSet rs = conexionBD.verSucursales()) {
            while (rs != null && rs.next()) {
                Object fila[] = {
                    rs.getInt("id_sucursal"),
                    rs.getInt("id_empresa"),
                    rs.getString("empresa_nombre"),
                    rs.getString("nombre_sucursal"),
                    rs.getString("direccion"),
                    rs.getString("telefono")
                };
                dtm.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al listar sucursales: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limpiarCamposSucursal() {
        txtcodigosucursal.setText("");
        txtnombresucursal.setText("");
        txtdireccionsucursal.setText("");
        txttelefono.setText("");
        jcomboboxsucursal.setSelectedIndex(0);
    }

}
