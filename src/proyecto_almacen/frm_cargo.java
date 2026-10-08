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

    public frm_cargo() {//
        setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(null); //inicializa los compones visuales
        txtcodigocargo.setEnabled(false);
        BTN_Guardar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
        BTN_Desactivar.setEnabled(false);
        setLocationRelativeTo(null);

        /* Crear la conexión al iniciar el formulario */
        conexionBD = new conexionBD();

        /* Verificar que la conexión fue exitosa */
        if (conexionBD.getConnection() == null) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar con la base de datos.",
                    "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }

        /* Definir encabezados de la tabla */
        String titulos[] = {"Código Cargo", "Nombre Cargo", "estado"};
        //asignar los titulos al modelo
        modeloTablaCargo.setColumnIdentifiers(titulos);

        //Establecer el modelo a la JTable
        JTABLE_Mant_cargos.setModel(modeloTablaCargo);

        //deshabilitar campo de codigo (solo lo mostrara, no se escribe)
        txtcodigocargo.setEnabled(false);
    }

    /**
     * Método para listar/refrescar los datos en el JTable desde la BD
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtcodigocargo = new javax.swing.JTextField();
        txtnombrecargo = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        BTN_Vercargos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Mant_cargos = new javax.swing.JTable();
        BTN_Nuevo = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        BTN_Modificar = new javax.swing.JButton();
        BTN_Desactivar = new javax.swing.JButton();
        BTN_EXCEL = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TXT_Buscarcargos = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        BTN_Cerrar1 = new javax.swing.JButton();
        BTN_PDF = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("MANTENIMIENTO DE CARGOS");
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 240, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 51), null));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Codigo cargo");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Nombre cargo");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        txtcodigocargo.setEditable(false);
        txtcodigocargo.setBackground(new java.awt.Color(255, 255, 255));
        txtcodigocargo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtcodigocargo.setForeground(new java.awt.Color(0, 0, 204));
        txtcodigocargo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcodigocargo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtcodigocargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 300, 40));

        txtnombrecargo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtnombrecargo.setForeground(new java.awt.Color(0, 0, 204));
        txtnombrecargo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnombrecargo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtnombrecargo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombrecargoKeyTyped(evt);
            }
        });
        jPanel1.add(txtnombrecargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 300, 40));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "acciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jCheckBox1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox1.setText("Listar cargo de baja");
        jCheckBox1.addActionListener(this::jCheckBox1ActionPerformed);
        jPanel3.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jCheckBox2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox2.setText("Reactivar cargo");
        jCheckBox2.addActionListener(this::jCheckBox2ActionPerformed);
        jPanel3.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, 190, 90));

        BTN_Vercargos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Vercargos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/papel.png"))); // NOI18N
        BTN_Vercargos.setText("VER CARGOS");
        BTN_Vercargos.addActionListener(this::BTN_VercargosActionPerformed);
        jPanel1.add(BTN_Vercargos, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 140, 50));

        jPanel5.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 820, 160));

        JTABLE_Mant_cargos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_Mant_cargos.setForeground(new java.awt.Color(0, 0, 204));
        JTABLE_Mant_cargos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        JTABLE_Mant_cargos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_Mant_cargosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTABLE_Mant_cargos);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 830, 220));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/registro.png"))); // NOI18N
        BTN_Nuevo.setText("NUEVO");
        BTN_Nuevo.addActionListener(this::BTN_NuevoActionPerformed);
        jPanel5.add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 190, 50));

        BTN_Guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disco-flexible (1).png"))); // NOI18N
        BTN_Guardar.setText("GUARDAR");
        BTN_Guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_GuardarMouseClicked(evt);
            }
        });
        BTN_Guardar.addActionListener(this::BTN_GuardarActionPerformed);
        jPanel5.add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 190, 50));

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ahorrar.png"))); // NOI18N
        BTN_Modificar.setText("MODIFICAR");
        BTN_Modificar.addActionListener(this::BTN_ModificarActionPerformed);
        jPanel5.add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 210, 200, 50));

        BTN_Desactivar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Desactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/expediente.png"))); // NOI18N
        BTN_Desactivar.setText("DAR DE BAJA");
        BTN_Desactivar.addActionListener(this::BTN_DesactivarActionPerformed);
        jPanel5.add(BTN_Desactivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 210, 180, 50));

        BTN_EXCEL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        BTN_EXCEL.setText("Exportar");
        BTN_EXCEL.addActionListener(this::BTN_EXCELActionPerformed);
        jPanel5.add(BTN_EXCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 580, 120, 40));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ingresar el Nombre de la Facultad");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 30));

        TXT_Buscarcargos.addActionListener(this::TXT_BuscarcargosActionPerformed);
        TXT_Buscarcargos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_BuscarcargosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_BuscarcargosKeyTyped(evt);
            }
        });
        jPanel2.add(TXT_Buscarcargos, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 290, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        jLabel5.setText("BUSCAR");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 120, 30));

        jPanel5.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 830, 50));

        BTN_Cerrar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cerrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrado.png"))); // NOI18N
        BTN_Cerrar1.setText("Cerrar");
        BTN_Cerrar1.addActionListener(this::BTN_Cerrar1ActionPerformed);
        jPanel5.add(BTN_Cerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 580, 130, 40));

        BTN_PDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/archivo-de-acrobat-reader.png"))); // NOI18N
        BTN_PDF.setText("Exportar");
        BTN_PDF.addActionListener(this::BTN_PDFActionPerformed);
        jPanel5.add(BTN_PDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 580, 120, 40));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 900, 630));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_VercargosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VercargosActionPerformed
//llamar a la tabla mostrar cargos
        this.mostrarCargos();
    }//GEN-LAST:event_BTN_VercargosActionPerformed

    private void txtnombrecargoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombrecargoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombrecargoKeyTyped

    private void JTABLE_Mant_cargosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_cargosMouseClicked
//obtener la fila seleccionada
        int filaSeleccionada = JTABLE_Mant_cargos.getSelectedRow();
        if (filaSeleccionada >= 0) {
            //llenar los campos de texto con los datos de la fila
            String codigo = JTABLE_Mant_cargos.getValueAt(filaSeleccionada, 0).toString();
            String nombre = JTABLE_Mant_cargos.getValueAt(filaSeleccionada, 1).toString();

            txtcodigocargo.setText(codigo);
            txtnombrecargo.setText(nombre);
            //Habilitar el campo para editar nombre
            txtnombrecargo.setEnabled(true);
            //Habilitar botones relacionados
            BTN_Modificar.setEnabled(true);
            BTN_Desactivar.setEnabled(true);
            //opcional: Deshabilitar boton guardar (si es necesario)
            BTN_Guardar.setEnabled(false);

        }
    }//GEN-LAST:event_JTABLE_Mant_cargosMouseClicked

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
//limpiar los campos de texto
        txtcodigocargo.setText("");
        txtnombrecargo.setText("");
//Da el foco al campo de nombre para que el usuarioempiece a escribir
        txtnombrecargo.requestFocus();
        txtnombrecargo.setEnabled(true);
//Habilita el boton guardar(en caso estee deshabilitado)
        BTN_Guardar.setEnabled(true);
        BTN_Desactivar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
// 1. Validar que el campo no esté vacío
// 1. Validar que los campos no estén vacíos
        String nombre = txtnombrecargo.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre del cargo",
                    "Campo requerido", JOptionPane.WARNING_MESSAGE);
            txtnombrecargo.requestFocus();
            return;
        }
// 2. Confirmar si el usuario desea guardar
        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Desea guardar el registro de cargo?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                // 3. Llamar al método para insertar
                conexionBD.insertarCargo(nombre);

                // 4. Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(this, "cargo registrado correctamente",
                        "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);

                // 5. Actualizar tabla y limpiar campos
                this.mostrarCargos();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al registrar cargo:\n"
                        + ex.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
        String codStr = txtcodigocargo.getText().trim();
        String nuevoNombre = txtnombrecargo.getText().trim();
        if (codStr.isEmpty() || nuevoNombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un cargo y complete"
                    + "el nuevo nombre", "campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int codigo = Integer.parseInt(codStr);
//confirmacion del usuario
        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Desea modifcar este cargo?", "confirmacion",
                JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                conexionBD.modificarCargo(codigo, nuevoNombre);
                JOptionPane.showMessageDialog(this, "cargo modificada correctamente",
                        "Modificacion exitosa", JOptionPane.INFORMATION_MESSAGE);
                this.mostrarCargos();//metodo actualizar
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al modificar cargo:\n"
                        + ex.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_BTN_ModificarActionPerformed

    private void BTN_DesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DesactivarActionPerformed
// 1. Validar que se haya seleccionado una facultad
        String codStr = txtcodigocargo.getText().trim();
        if (codStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un cargo en la tabla para desactivar.", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int codigo = Integer.parseInt(codStr); // Convertir a entero
        // 2. Confirmar la acción con el usuario
        int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea desactivar este proveedor?", "Confirmar desactivación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            try {
                // 3. Llamar al método que ejecuta el procedure de desactivación
                conexionBD.darDeBajaCargo(codigo);
                // 4. Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(this, "cargo desactivado correctamente.", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
                // 5. Actualizar tabla y limpiar campos
                this.mostrarCargos();
                // Limpia los campos de texto
                txtcodigocargo.setText("");
                txtnombrecargo.setText("");
                BTN_Desactivar.setEnabled(false);
                BTN_Modificar.setEnabled(false);
            } catch (SQLException ex) {
                // 6. Captura cualquier error lanzado por el procedure (por SIGNAL)
                JOptionPane.showMessageDialog(this, "Error al desactivar cargo:\n" + ex.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BTN_DesactivarActionPerformed

    private void BTN_EXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EXCELActionPerformed

    }//GEN-LAST:event_BTN_EXCELActionPerformed

    private void TXT_BuscarcargosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarcargosKeyReleased
        this.buscarcargo();
    }//GEN-LAST:event_TXT_BuscarcargosKeyReleased

    private void TXT_BuscarcargosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarcargosKeyTyped

    }//GEN-LAST:event_TXT_BuscarcargosKeyTyped

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

    private void BTN_PDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_PDFActionPerformed

    }//GEN-LAST:event_BTN_PDFActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()) {
            mostrarCargosDeBaja();
        } else {
            mostrarCargos();
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void TXT_BuscarcargosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_BuscarcargosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_BuscarcargosActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        if (jCheckBox2.isSelected()) {
            String codStr = txtcodigocargo.getText().trim();

            if (codStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione un cargo de baja de la tabla para reactivar.", "Atención", JOptionPane.WARNING_MESSAGE);
                jCheckBox2.setSelected(false);
                return;
            }

            int codigo = Integer.parseInt(codStr);
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea reactivar este cargo?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (respuesta == JOptionPane.YES_OPTION) {
                try {
                    conexionBD.reactivarCargo(codigo);

                    JOptionPane.showMessageDialog(this, "¡Cargo reactivado correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                    txtcodigocargo.setText("");
                    txtnombrecargo.setText("");
                    jCheckBox2.setSelected(false);

                    if (jCheckBox1.isSelected()) {
                        mostrarCargosDeBaja();
                    } else {
                        mostrarCargos();
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error al reactivar:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    jCheckBox2.setSelected(false);
                }
            } else {
                jCheckBox2.setSelected(false);
            }
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

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
    private javax.swing.JButton BTN_Vercargos;
    private javax.swing.JTable JTABLE_Mant_cargos;
    private javax.swing.JTextField TXT_Buscarcargos;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtcodigocargo;
    private javax.swing.JTextField txtnombrecargo;
    // End of variables declaration//GEN-END:variables

    public void mostrarCargos() {
        txtnombrecargo.setEnabled(true);//desactivar y activar nombreCargo
        //ordenar Asc, Desc
        JTABLE_Mant_cargos.setAutoCreateRowSorter(true);
        //limpiar la tabla antes de mostar nuevos datos
        modeloTablaCargo.setRowCount(0);
        try {
            //llama al metodo que devuelve los datos a la facultad
            ResultSet rs = conexionBD.listarCargo();
            //recorre cada fila del resultado y agrega a la tabla
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("id_cargo"),
                    rs.getString("nombre_cargo"),
                    rs.getString("estado_cargo")};
                modeloTablaCargo.addRow(fila);
            }
        } catch (SQLException e) {
            //muestra mensaje si ocurre un error en la consulta
            JOptionPane.showMessageDialog(this, "Error al mostrar cargo:\n" + e.getMessage(),
                    "Error de consulta", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buscarcargo() {
        // Limpia la tabla antes de mostrar los resultados filtrados
        modeloTablaCargo.setRowCount(0);
        // obtiene el texto ingresado por el usuario
        String busqueda = TXT_Buscarcargos.getText().trim();
        try {
            // Consulta los datos usando el procedimiento almacenado en la BD
            ResultSet rs = conexionBD.buscarCargo(busqueda);

            // Recorre los resultados y los anade a la tablal
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("id_cargo"),
                    rs.getString("nombre_cargo"),};
                modeloTablaCargo.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al buscar Cargos: \n" + e.getMessage(),
                    "Error de busqueda", JOptionPane.ERROR_MESSAGE);
        }
    }

    /* Método para listar los cargos de baja en la tabla */
    public void mostrarCargosDeBaja() {
        JTABLE_Mant_cargos.setAutoCreateRowSorter(true);
        modeloTablaCargo.setRowCount(0);
        try {
            ResultSet rs = conexionBD.listarCargosDeBaja();
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("id_cargo"),
                    rs.getString("nombre_cargo"),
                    rs.getString("estado_cargo")
                };
                modeloTablaCargo.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al mostrar cargos de baja:\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
