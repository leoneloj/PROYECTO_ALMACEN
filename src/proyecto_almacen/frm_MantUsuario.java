package proyecto_almacen;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frm_MantUsuario extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frm_MantUsuario.class.getName());
    DefaultTableModel dtm = new DefaultTableModel();
    conexionBD conexionBD;
    String placeholderText = "Buscar Código, Cargo o Personal...";

    public frm_MantUsuario() {
        setUndecorated(true);

        initComponents();
        this.setLocationRelativeTo(null); // Centrar ventana

        this.BTN_GUARDARUSUARIO.setEnabled(false);
        this.BTN_RESETPASSWORD.setEnabled(false);
        this.jRadio_ActivarUsuarios.setEnabled(false);
        this.jRadio_DesactivarUsuarios.setEnabled(false);
        this.jRadio_ListarUsuarios_Desactivados.setEnabled(false);
        this.txtIdUsuario.setEnabled(false);

        // Configuración inicial del Placeholder
        TXTBUSCAR_Usuario.setText(placeholderText);
        TXTBUSCAR_Usuario.setForeground(new java.awt.Color(153, 153, 153));

        TXTBUSCAR_Usuario.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (TXTBUSCAR_Usuario.getText().equals(placeholderText)) {
                    TXTBUSCAR_Usuario.setText("");
                    TXTBUSCAR_Usuario.setForeground(new java.awt.Color(0, 0, 0));
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (TXTBUSCAR_Usuario.getText().trim().isEmpty()) {
                    TXTBUSCAR_Usuario.setText(placeholderText);
                    TXTBUSCAR_Usuario.setForeground(new java.awt.Color(153, 153, 153));
                }
            }
        });

        String titulos[] = {"ID", "Código", "Contraseña", "Cargo", "Personal", "Estado"};
        dtm.setColumnIdentifiers(titulos);
        JTABLE_USUARIOS.setModel(dtm);

        JTABLE_USUARIOS.getColumnModel().getColumn(0).setPreferredWidth(40);   // ID
        JTABLE_USUARIOS.getColumnModel().getColumn(1).setPreferredWidth(90);   // Código
        JTABLE_USUARIOS.getColumnModel().getColumn(2).setPreferredWidth(330);  // Contraseña
        JTABLE_USUARIOS.getColumnModel().getColumn(3).setPreferredWidth(200);  // Cargo
        JTABLE_USUARIOS.getColumnModel().getColumn(4).setPreferredWidth(250);  // Personal
        JTABLE_USUARIOS.getColumnModel().getColumn(5).setPreferredWidth(80);   // Estado

        /**/
        conexionBD = new conexionBD();

        /**/
        this.llenarJComboBox_Cargos_usuarios();
        this.llenarJComboBox_Personal_usuarios();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jcomboboxPersonal = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtaPassword = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtIdUsuario = new javax.swing.JTextField();
        txtcodigoUsuario = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        BTN_listarUsuarios = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jRadio_ListarUsuarios_Desactivados = new javax.swing.JRadioButton();
        jRadio_DesactivarUsuarios = new javax.swing.JRadioButton();
        jRadio_ActivarUsuarios = new javax.swing.JRadioButton();
        jcomboboxCargo1 = new javax.swing.JComboBox<>();
        BTN_NuevoUSUARIO = new javax.swing.JButton();
        BTN_GUARDARUSUARIO = new javax.swing.JButton();
        BTN_RESETPASSWORD = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TXTBUSCAR_Usuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_USUARIOS = new javax.swing.JTable();
        BTN_CERRAR = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 51), null));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Codigo usuario.");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jcomboboxPersonal.setForeground(new java.awt.Color(0, 0, 153));
        jcomboboxPersonal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcomboboxPersonal.addActionListener(this::jcomboboxPersonalActionPerformed);
        jPanel1.add(jcomboboxPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 410, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Codigo:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        txtaPassword.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtaPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtaPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtaPasswordKeyTyped(evt);
            }
        });
        jPanel1.add(txtaPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 410, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Personal");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Cargo:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        txtIdUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtIdUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(txtIdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 410, -1));

        txtcodigoUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtcodigoUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcodigoUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodigoUsuarioKeyTyped(evt);
            }
        });
        jPanel1.add(txtcodigoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 410, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Password:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        BTN_listarUsuarios.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_listarUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/papel.png"))); // NOI18N
        BTN_listarUsuarios.setText("LISTAR USUARIOS");
        BTN_listarUsuarios.addActionListener(this::BTN_listarUsuariosActionPerformed);
        jPanel1.add(BTN_listarUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, -1, 50));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jRadio_ListarUsuarios_Desactivados.setText("Listar Desactivado");
        jRadio_ListarUsuarios_Desactivados.addActionListener(this::jRadio_ListarUsuarios_DesactivadosActionPerformed);
        jPanel3.add(jRadio_ListarUsuarios_Desactivados, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jRadio_DesactivarUsuarios.setText("Desactivar");
        jRadio_DesactivarUsuarios.addActionListener(this::jRadio_DesactivarUsuariosActionPerformed);
        jPanel3.add(jRadio_DesactivarUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, -1, 20));

        jRadio_ActivarUsuarios.setText("Activar");
        jRadio_ActivarUsuarios.addActionListener(this::jRadio_ActivarUsuariosActionPerformed);
        jPanel3.add(jRadio_ActivarUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 400, 80));

        jcomboboxCargo1.setForeground(new java.awt.Color(0, 0, 153));
        jcomboboxCargo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcomboboxCargo1.addActionListener(this::jcomboboxCargo1ActionPerformed);
        jPanel1.add(jcomboboxCargo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 410, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 1070, 210));

        BTN_NuevoUSUARIO.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_NuevoUSUARIO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/registro.png"))); // NOI18N
        BTN_NuevoUSUARIO.setText("NUEVO");
        BTN_NuevoUSUARIO.addActionListener(this::BTN_NuevoUSUARIOActionPerformed);
        getContentPane().add(BTN_NuevoUSUARIO, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 190, 50));

        BTN_GUARDARUSUARIO.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_GUARDARUSUARIO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disco-flexible (1).png"))); // NOI18N
        BTN_GUARDARUSUARIO.setText("GUARDAR");
        BTN_GUARDARUSUARIO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_GUARDARUSUARIOMouseClicked(evt);
            }
        });
        BTN_GUARDARUSUARIO.addActionListener(this::BTN_GUARDARUSUARIOActionPerformed);
        getContentPane().add(BTN_GUARDARUSUARIO, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 250, 190, 50));

        BTN_RESETPASSWORD.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_RESETPASSWORD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ahorrar.png"))); // NOI18N
        BTN_RESETPASSWORD.setText("RESET");
        BTN_RESETPASSWORD.addActionListener(this::BTN_RESETPASSWORDActionPerformed);
        getContentPane().add(BTN_RESETPASSWORD, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 250, 200, 50));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ingresar Escuela Profesional");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, 30));

        TXTBUSCAR_Usuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TXTBUSCAR_Usuario.setToolTipText("");
        TXTBUSCAR_Usuario.addActionListener(this::TXTBUSCAR_UsuarioActionPerformed);
        TXTBUSCAR_Usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXTBUSCAR_UsuarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTBUSCAR_UsuarioKeyTyped(evt);
            }
        });
        jPanel2.add(TXTBUSCAR_Usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 370, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("BUSCAR");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 120, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 1090, 50));

        JTABLE_USUARIOS.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_USUARIOS.setForeground(new java.awt.Color(0, 0, 153));
        JTABLE_USUARIOS.setModel(new javax.swing.table.DefaultTableModel(
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
        JTABLE_USUARIOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_USUARIOSMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTABLE_USUARIOS);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 1090, 260));

        BTN_CERRAR.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_CERRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrado.png"))); // NOI18N
        BTN_CERRAR.setText("Cerrar");
        BTN_CERRAR.addActionListener(this::BTN_CERRARActionPerformed);
        getContentPane().add(BTN_CERRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 640, 130, 40));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("MANTENIMIENTO DE USUARIOS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, 240, 30));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.addActionListener(this::jTextField1ActionPerformed);
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, -20, 1130, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcomboboxPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcomboboxPersonalActionPerformed

    }//GEN-LAST:event_jcomboboxPersonalActionPerformed

    private void txtaPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaPasswordKeyTyped

    }//GEN-LAST:event_txtaPasswordKeyTyped

    private void txtcodigoUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoUsuarioKeyTyped

    }//GEN-LAST:event_txtcodigoUsuarioKeyTyped

    private void BTN_listarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_listarUsuariosActionPerformed
        this.mostrarDatosTablaUsuarios();
        this.BTN_listarUsuarios.setEnabled(false);
        this.jRadio_ListarUsuarios_Desactivados.setEnabled(true);
    }//GEN-LAST:event_BTN_listarUsuariosActionPerformed

    private void jRadio_ListarUsuarios_DesactivadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadio_ListarUsuarios_DesactivadosActionPerformed
        // Limpiar la tabla antes de cargar los registros inactivos
        dtm.setRowCount(0);

        boolean hayRegistros = false;

        try (ResultSet rs = conexionBD.listarUsuariosInactivos()) {
            while (rs != null && rs.next()) {
                hayRegistros = true;
                Object fila[] = {
                    rs.getInt("id_usuario"),
                    rs.getString("codigo"),
                    rs.getString("password"),
                    rs.getString("nombre_cargo"),
                    rs.getString("personal"),
                    rs.getString("estado_usuario") // Muestra 'Inactivo' según la vista
                };
                dtm.addRow(fila);
            }

            if (!hayRegistros) {
                JOptionPane.showMessageDialog(this, "No se encontraron usuarios inactivos registrados.",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al listar los usuarios inactivos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        this.jRadio_ListarUsuarios_Desactivados.setEnabled(false);
        this.jRadio_ActivarUsuarios.setEnabled(true);
        this.jRadio_DesactivarUsuarios.setEnabled(false);
        this.BTN_listarUsuarios.setEnabled(true);
    }//GEN-LAST:event_jRadio_ListarUsuarios_DesactivadosActionPerformed

    private void jRadio_DesactivarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadio_DesactivarUsuariosActionPerformed
// 1. Verificar si hay una fila seleccionada en la tabla
        int filaSeleccionada = JTABLE_USUARIOS.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un usuario de la tabla para desactivar.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Obtener el ID y el estado actual desde la tabla
        // (Índice 0: id_usuario, índice 5: estado_usuario)
        int idUsuario = Integer.parseInt(JTABLE_USUARIOS.getValueAt(filaSeleccionada, 0).toString());
        String estadoUsuario = JTABLE_USUARIOS.getValueAt(filaSeleccionada, 5).toString();

        // 3. Validar que el usuario esté activo para poder desactivarlo
        if (estadoUsuario.equalsIgnoreCase("Inactivo")) {
            JOptionPane.showMessageDialog(this, "El usuario seleccionado ya se encuentra inactivo.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 4. Ventana de confirmación antes de proceder
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de desactivar al usuario seleccionado?",
                "Confirmar Desactivación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                // 5. Llamar al método que ejecuta el procedimiento almacenado
                conexionBD.desactivarUsuario(idUsuario);

                JOptionPane.showMessageDialog(this, "Usuario desactivado exitosamente.",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // 6. Refrescar la tabla de usuarios activos
                mostrarDatosTablaUsuarios();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error de base de datos al desactivar el usuario: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        this.jRadio_DesactivarUsuarios.setEnabled(false);
    }//GEN-LAST:event_jRadio_DesactivarUsuariosActionPerformed

    private void jRadio_ActivarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadio_ActivarUsuariosActionPerformed
        // 1. Verificar si hay una fila seleccionada en la tabla
        int filaSeleccionada = JTABLE_USUARIOS.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un usuario de la tabla para activar.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Obtener el ID y el estado actual desde la tabla
        // (Índice 0: id_usuario, índice 5: estado_usuario)
        int idUsuario = Integer.parseInt(JTABLE_USUARIOS.getValueAt(filaSeleccionada, 0).toString());
        String estadoUsuario = JTABLE_USUARIOS.getValueAt(filaSeleccionada, 5).toString();

        // 3. Validar que el usuario esté inactivo para poder activarlo
        if (estadoUsuario.equalsIgnoreCase("Activo")) {
            JOptionPane.showMessageDialog(this, "El usuario seleccionado ya se encuentra activo.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 4. Ventana de confirmación antes de proceder
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de activar al usuario seleccionado?",
                "Confirmar Activación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                // 5. Llamar al método que ejecuta el procedimiento almacenado
                conexionBD.reactivarUsuario(idUsuario);

                JOptionPane.showMessageDialog(this, "Usuario activado exitosamente.",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // 6. Refrescar la tabla
                mostrarDatosTablaUsuarios();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error de base de datos al activar el usuario: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        this.jRadio_ListarUsuarios_Desactivados.setEnabled(false);
        this.jRadio_ActivarUsuarios.setEnabled(false);
    }//GEN-LAST:event_jRadio_ActivarUsuariosActionPerformed

    private void BTN_NuevoUSUARIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoUSUARIOActionPerformed
        this.BTN_GUARDARUSUARIO.setEnabled(true);
        this.BTN_listarUsuarios.setEnabled(true);
        this.txtIdUsuario.setText("");
        this.txtcodigoUsuario.setText("");
        this.txtaPassword.setText("");
        this.jcomboboxCargo1.setSelectedIndex(0);
        this.jcomboboxPersonal.setSelectedIndex(0);
        this.jRadio_ActivarUsuarios.setEnabled(true);
        this.jRadio_DesactivarUsuarios.setEnabled(true);
        this.jRadio_ListarUsuarios_Desactivados.setEnabled(false);
        this.txtcodigoUsuario.requestFocus();
    }//GEN-LAST:event_BTN_NuevoUSUARIOActionPerformed

    private void BTN_GUARDARUSUARIOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GUARDARUSUARIOMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GUARDARUSUARIOMouseClicked

    private void BTN_GUARDARUSUARIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GUARDARUSUARIOActionPerformed
        String codigo = txtcodigoUsuario.getText().trim();
        String passwordPlano = txtaPassword.getText().trim();

        // 1. Validar campos obligatorios
        if (codigo.isEmpty() || passwordPlano.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Obtener el nombre del cargo seleccionado en el ComboBox
        String nombreCargoSeleccionado = jcomboboxCargo1.getSelectedItem().toString();
        if (nombreCargoSeleccionado.equals("<<Seleccionar>>") || nombreCargoSeleccionado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un cargo válido.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 3. Obtener el personal seleccionado en el ComboBox (opcional)
        String nombrePersonalSeleccionado = jcomboboxPersonal.getSelectedItem().toString();

        try {
            // 4. Obtener el ID numérico (id_cargo) a partir del nombre seleccionado
            int idCargo = conexionBD.obtenerCodigoCargoPorNombre_usuarios(nombreCargoSeleccionado);
            if (idCargo == 0) {
                JOptionPane.showMessageDialog(this, "El cargo seleccionado no es válido en la base de datos.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 5. Obtener el ID numérico (id_personal); 0 significa sin personal
            int idPersonal = 0;
            if (!nombrePersonalSeleccionado.equals("<<Seleccionar>>")) {
                idPersonal = conexionBD.obtenerIdPersonalPorNombre_usuarios(nombrePersonalSeleccionado);
                if (idPersonal == 0) {
                    JOptionPane.showMessageDialog(this, "El personal seleccionado no es válido en la base de datos.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // 6. Encriptar la contraseña a SHA-256 antes de enviarla
            String passwordHash = conexionBD.convertirSHA256(passwordPlano);

            // 7. Ejecutar el procedimiento de inserción
            conexionBD.insertarUsuario(codigo, passwordHash, idCargo, idPersonal);

            JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            mostrarDatosTablaUsuarios(); // Refrescar la grilla
            limpiarCampos();

        } catch (SQLException e) {
            // El mensaje del SIGNAL del procedure llega en e.getMessage()
            JOptionPane.showMessageDialog(this, "Error de base de datos al guardar el usuario: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BTN_GUARDARUSUARIOActionPerformed

    private void BTN_RESETPASSWORDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_RESETPASSWORDActionPerformed
        // 1. Verificar si hay una fila seleccionada en la tabla
        int filaSeleccionada = JTABLE_USUARIOS.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un usuario de la tabla para "
                    + "restablecer su contraseña.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Obtener el ID del usuario (Índice 0) y su Código (Índice 1)
        int idUsuario = Integer.parseInt(JTABLE_USUARIOS.getValueAt(filaSeleccionada, 0).toString());
        String codigoUsuario = JTABLE_USUARIOS.getValueAt(filaSeleccionada, 1).toString();

        // 3. Ventana de confirmación antes de ejecutar
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de restablecer la contraseña del usuario con código [" + codigoUsuario + "] "
                + "al valor por defecto ('123456')?",
                "Confirmar Restablecimiento", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                // 4. Llamar al método que gestiona el procedimiento almacenado
                conexionBD.resetearPasswordUsuario(idUsuario);

                JOptionPane.showMessageDialog(this, "¡Contraseña restablecida exitosamente al valor"
                        + " por defecto ('123456')!",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // 5. Actualizar la tabla
                mostrarDatosTablaUsuarios();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error de base de datos al restablecer la contraseña: "
                        + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BTN_RESETPASSWORDActionPerformed

    private void TXTBUSCAR_UsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTBUSCAR_UsuarioKeyReleased
        String texto = TXTBUSCAR_Usuario.getText().trim();

        // 1. Si tiene el texto de guía o está vacío, mostramos todos los registros y salimos
        if (texto.equals(placeholderText) || texto.isEmpty()) {
            mostrarDatosTablaUsuarios();
            return;
        }

        // 2. Limpiar tabla antes de mostrar resultados nuevos del filtro
        dtm.setRowCount(0);

        // 3. Ejecutar el procedimiento almacenado de búsqueda
        try (ResultSet rs = conexionBD.buscarUsuario(texto)) {

            // 4. Llenar la tabla con los resultados obtenidos del filtro
            while (rs != null && rs.next()) {
                Object fila[] = {
                    rs.getInt("id_usuario"),
                    rs.getString("codigo"),
                    rs.getString("password"),
                    rs.getString("nombre_cargo"),
                    rs.getString("personal"),
                    rs.getString("estado_usuario")
                };
                dtm.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar usuarios: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_TXTBUSCAR_UsuarioKeyReleased

    private void TXTBUSCAR_UsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTBUSCAR_UsuarioKeyTyped

    }//GEN-LAST:event_TXTBUSCAR_UsuarioKeyTyped

    private void JTABLE_USUARIOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_USUARIOSMouseClicked
        this.BTN_RESETPASSWORD.setEnabled(true);
        this.jRadio_ActivarUsuarios.setEnabled(true);
        this.jRadio_DesactivarUsuarios.setEnabled(true);
        this.jRadio_ListarUsuarios_Desactivados.setEnabled(false);

        int fila = JTABLE_USUARIOS.getSelectedRow();
        if (fila == -1) {
            return;
        }
        txtIdUsuario.setText(JTABLE_USUARIOS.getValueAt(fila, 0).toString());
        txtcodigoUsuario.setText(JTABLE_USUARIOS.getValueAt(fila, 1).toString());
        txtaPassword.setText("");

        jcomboboxCargo1.setSelectedIndex(0);
        jcomboboxCargo1.setSelectedItem(JTABLE_USUARIOS.getValueAt(fila, 3).toString());

        String personal = JTABLE_USUARIOS.getValueAt(fila, 4).toString();
        jcomboboxPersonal.setSelectedIndex(0);
        for (int i = 1; i < jcomboboxPersonal.getItemCount(); i++) {
            if (jcomboboxPersonal.getItemAt(i).startsWith(personal + " - ")) {
                jcomboboxPersonal.setSelectedIndex(i);
                break;
            }
        }
    }//GEN-LAST:event_JTABLE_USUARIOSMouseClicked

    private void BTN_CERRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CERRARActionPerformed
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas cerrar el formulario?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            dispose();
        }
    }//GEN-LAST:event_BTN_CERRARActionPerformed

    private void jcomboboxCargo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcomboboxCargo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcomboboxCargo1ActionPerformed

    private void TXTBUSCAR_UsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTBUSCAR_UsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTBUSCAR_UsuarioActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new frm_MantUsuario().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_CERRAR;
    private javax.swing.JButton BTN_GUARDARUSUARIO;
    private javax.swing.JButton BTN_NuevoUSUARIO;
    private javax.swing.JButton BTN_RESETPASSWORD;
    private javax.swing.JButton BTN_listarUsuarios;
    private javax.swing.JTable JTABLE_USUARIOS;
    private javax.swing.JTextField TXTBUSCAR_Usuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadio_ActivarUsuarios;
    private javax.swing.JRadioButton jRadio_DesactivarUsuarios;
    private javax.swing.JRadioButton jRadio_ListarUsuarios_Desactivados;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JComboBox<String> jcomboboxCargo1;
    private javax.swing.JComboBox<String> jcomboboxPersonal;
    private javax.swing.JTextField txtIdUsuario;
    private javax.swing.JTextField txtaPassword;
    private javax.swing.JTextField txtcodigoUsuario;
    // End of variables declaration//GEN-END:variables
  /*Metodo para llenar los objetos del tipo JComboBox*/
    private void llenarJComboBox_Cargos_usuarios() {
        try {
            jcomboboxCargo1.removeAllItems();
            jcomboboxCargo1.addItem("<<Seleccionar>>"); // Opción por defecto

            try (ResultSet rs = conexionBD.combobox_ListarCargosUsuarios()) {
                while (rs != null && rs.next()) {
                    String nombreCargo = rs.getString("nombre_cargo");
                    if (nombreCargo != null) {
                        jcomboboxCargo1.addItem(nombreCargo.trim());
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar nombre_cargo: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void llenarJComboBox_Personal_usuarios() {
        try {
            jcomboboxPersonal.removeAllItems();
            jcomboboxPersonal.addItem("<<Seleccionar>>"); // Opción por defecto (sin personal)

            try (ResultSet rs = conexionBD.combobox_ListarPersonalUsuarios()) {
                while (rs != null && rs.next()) {
                    String personal = rs.getString("personal");
                    if (personal != null) {
                        jcomboboxPersonal.addItem(personal.trim());
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar el personal: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /*Metodo para listar los registro en la tabla*/
    private void mostrarDatosTablaUsuarios() {
        // Limpiar la tabla antes de cargar nuevos registros
        dtm.setRowCount(0);

        try (ResultSet rs = conexionBD.listarUsuariosDesdeVista()) {
            while (rs != null && rs.next()) {
                Object fila[] = {
                    rs.getInt("id_usuario"),
                    rs.getString("codigo"),
                    rs.getString("password"),
                    rs.getString("nombre_cargo"),
                    rs.getString("personal"),
                    rs.getString("estado_usuario") // Llega como 'Activo' o 'Inactivo' gracias a la vista
                };
                dtm.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al listar los usuarios: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*Metodo para limpiar los campos después de guardar*/
    private void limpiarCampos() {
        this.txtIdUsuario.setText("");
        this.txtcodigoUsuario.setText("");
        this.txtaPassword.setText("");
        this.jcomboboxCargo1.setSelectedIndex(0);
        this.jcomboboxPersonal.setSelectedIndex(0);
        this.BTN_GUARDARUSUARIO.setEnabled(false);
    }
}
