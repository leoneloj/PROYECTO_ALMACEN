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
        initComponents();//inicializa los compones visuales
        txtcodigoarea.setEnabled(false);
        BTN_Guardar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
        //crear la conexion al inciar el formulario
        conexionBD = new conexionBD();

        //verificar que la conexion fue exitosa
        if (conexionBD.getConnection() == null) {
            JOptionPane.showConfirmDialog(this, "No se pudo conectar con la base de datos .",
                    "error de conexion", JOptionPane.ERROR_MESSAGE);
            return; //Evita continuar si no hay conexion
        }

        //definir los encabezados de la tabla
        String titulos[] = {"codigo area", "Nombre area", "Descipcion"};

        //asignar los titulos al modelo
        modeloTablaArea.setColumnIdentifiers(titulos);

        //Establecer el modelo a la JTable
        JTABLE_Mant_Areas.setModel(modeloTablaArea);

        //deshabilitar campo de codigo (solo lo mostrara, no se escribe)
        txtcodigoarea.setEnabled(false);

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
        txtcodigoarea = new javax.swing.JTextField();
        BTN_VerAreas = new javax.swing.JButton();
        txtnombrearea = new javax.swing.JTextField();
        txtdescripcion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Mant_Areas = new javax.swing.JTable();
        BTN_Nuevo = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        BTN_Modificar = new javax.swing.JButton();
        BTN_EXCEL = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TXT_BuscarAreas = new javax.swing.JTextField();
        BTN_Cerrar1 = new javax.swing.JButton();
        BTN_EXCEL1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("MANTENIMIENTO DE AREAS");
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 240, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), null));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Codigo Area");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Descripcion");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        txtcodigoarea.setEditable(false);
        txtcodigoarea.setBackground(new java.awt.Color(255, 255, 255));
        txtcodigoarea.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtcodigoarea.setForeground(new java.awt.Color(0, 0, 204));
        txtcodigoarea.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcodigoarea.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtcodigoarea, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 330, 30));

        BTN_VerAreas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_VerAreas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/papel.png"))); // NOI18N
        BTN_VerAreas.setText("VER AREAS");
        BTN_VerAreas.addActionListener(this::BTN_VerAreasActionPerformed);
        jPanel1.add(BTN_VerAreas, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 180, 50));

        txtnombrearea.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtnombrearea.setForeground(new java.awt.Color(0, 0, 204));
        txtnombrearea.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnombrearea.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtnombrearea.addActionListener(this::txtnombreareaActionPerformed);
        txtnombrearea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreareaKeyTyped(evt);
            }
        });
        jPanel1.add(txtnombrearea, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 330, 30));

        txtdescripcion.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtdescripcion.setForeground(new java.awt.Color(0, 0, 204));
        txtdescripcion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtdescripcion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtdescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdescripcionKeyTyped(evt);
            }
        });
        jPanel1.add(txtdescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 330, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Nombre Area");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jPanel5.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 790, 160));

        JTABLE_Mant_Areas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_Mant_Areas.setForeground(new java.awt.Color(0, 0, 204));
        JTABLE_Mant_Areas.setModel(new javax.swing.table.DefaultTableModel(
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
        JTABLE_Mant_Areas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_Mant_AreasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTABLE_Mant_Areas);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 810, 220));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/registro.png"))); // NOI18N
        BTN_Nuevo.setText("NUEVO");
        BTN_Nuevo.addActionListener(this::BTN_NuevoActionPerformed);
        jPanel5.add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 190, 50));

        BTN_Guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disco-flexible (1).png"))); // NOI18N
        BTN_Guardar.setText("GUARDAR");
        BTN_Guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_GuardarMouseClicked(evt);
            }
        });
        BTN_Guardar.addActionListener(this::BTN_GuardarActionPerformed);
        jPanel5.add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 200, 190, 50));

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ahorrar.png"))); // NOI18N
        BTN_Modificar.setText("MODIFICAR");
        BTN_Modificar.addActionListener(this::BTN_ModificarActionPerformed);
        jPanel5.add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 200, 200, 50));

        BTN_EXCEL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/archivo-de-acrobat-reader.png"))); // NOI18N
        BTN_EXCEL.setText("Exportar");
        BTN_EXCEL.addActionListener(this::BTN_EXCELActionPerformed);
        jPanel5.add(BTN_EXCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 560, 120, 40));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ingresar el Nombre de la Facultad");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        jLabel5.setText("BUSCAR");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 120, 30));

        TXT_BuscarAreas.addActionListener(this::TXT_BuscarAreasActionPerformed);
        TXT_BuscarAreas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_BuscarAreasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_BuscarAreasKeyTyped(evt);
            }
        });
        jPanel2.add(TXT_BuscarAreas, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 290, -1));

        jPanel5.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 810, 50));

        BTN_Cerrar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cerrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrado.png"))); // NOI18N
        BTN_Cerrar1.setText("Cerrar");
        BTN_Cerrar1.addActionListener(this::BTN_Cerrar1ActionPerformed);
        jPanel5.add(BTN_Cerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 560, 130, 40));

        BTN_EXCEL1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        BTN_EXCEL1.setText("Exportar");
        BTN_EXCEL1.addActionListener(this::BTN_EXCEL1ActionPerformed);
        jPanel5.add(BTN_EXCEL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 560, 120, 40));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 900, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_VerAreasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VerAreasActionPerformed
//llama  a la tabla mostrar cliente
        this.MostrarAreas();

    }//GEN-LAST:event_BTN_VerAreasActionPerformed

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
//limpiar los campos de texto
        txtcodigoarea.setText("");
        txtnombrearea.setText("");
        txtdescripcion.setText("");
//Da el foco al campo de nombre para que el usuarioempiece a escribir
        txtnombrearea.requestFocus();
        txtnombrearea.setEnabled(true);
//Habilita el boton guardar(en caso estee deshabilitado)
        BTN_Guardar.setEnabled(true);
        BTN_Modificar.setEnabled(false);
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
// 1. Validar que el campo no esté vacío
// 1. Validar que los campos no estén vacíos
        String nombre = txtnombrearea.getText().trim();
        String descripcion = txtdescripcion.getText().trim(); // Asegúrate de que el campo de texto se llame así

        if (nombre.isEmpty() || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre y la descripción del Área",
                    "Campo requerido", JOptionPane.WARNING_MESSAGE);
            txtnombrearea.requestFocus();
            return;
        }
// 2. Confirmar si el usuario desea guardar
        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Desea guardar el registro de Area?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                // 3. Llamar al método para insertar
                conexionBD.insertarArea(nombre, descripcion);

                // 4. Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(this, "Area registrada correctamente",
                        "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);

                // 5. Actualizar tabla y limpiar campos
                this.MostrarAreas();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al registrar Area:\n"
                        + ex.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BTN_GuardarActionPerformed
//validar que se haya seleccionado un registro
    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
        String codStr = txtcodigoarea.getText().trim();
        String nuevoNombre = txtnombrearea.getText().trim();
        String nuevaDescripcion = txtdescripcion.getText().trim();
        if (codStr.isEmpty() || nuevoNombre.isEmpty() || nuevaDescripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione una area y complete"
                    + "el nuevo nombre", "campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int codigo = Integer.parseInt(codStr);
//confirmacion del usuario
        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Desea modifcar esta area?", "confirmacion",
                JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                conexionBD.modificarArea(codigo, nuevoNombre, nuevaDescripcion);
                JOptionPane.showMessageDialog(this, "Area modificada correctamente",
                        "Modificacion exitosa", JOptionPane.INFORMATION_MESSAGE);
                this.MostrarAreas();//metodo actualizar
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al modificar cargo:\n"
                        + ex.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_BTN_ModificarActionPerformed

    private void BTN_EXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EXCELActionPerformed

    }//GEN-LAST:event_BTN_EXCELActionPerformed

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

    private void JTABLE_Mant_AreasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_AreasMouseClicked
//obtener la fila seleccionada
        int filaSeleccionada = JTABLE_Mant_Areas.getSelectedRow();
        if (filaSeleccionada >= 0) {
            //llenar los campos de texto con los datos de la fila
            String codigo = JTABLE_Mant_Areas.getValueAt(filaSeleccionada, 0).toString();
            String nombre = JTABLE_Mant_Areas.getValueAt(filaSeleccionada, 1).toString();
            String descripcion = JTABLE_Mant_Areas.getValueAt(filaSeleccionada, 1).toString();

            txtcodigoarea.setText(codigo);
            txtnombrearea.setText(nombre);
            txtdescripcion.setText(descripcion);
            //Habilitar el campo para editar nombre
            txtnombrearea.setEnabled(true);
            //Habilitar botones relacionados
            BTN_Modificar.setEnabled(true);
            //opcional: Deshabilitar boton guardar (si es necesario)
            BTN_Guardar.setEnabled(false);

        }
    }//GEN-LAST:event_JTABLE_Mant_AreasMouseClicked

    private void txtnombreareaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreareaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreareaKeyTyped

    private void BTN_EXCEL1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EXCEL1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_EXCEL1ActionPerformed

    private void TXT_BuscarAreasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarAreasKeyTyped

    }//GEN-LAST:event_TXT_BuscarAreasKeyTyped

    private void TXT_BuscarAreasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarAreasKeyReleased
        this.buscarArea();
    }//GEN-LAST:event_TXT_BuscarAreasKeyReleased

    private void txtnombreareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreareaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreareaActionPerformed

    private void TXT_BuscarAreasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_BuscarAreasActionPerformed

    }//GEN-LAST:event_TXT_BuscarAreasActionPerformed

    private void txtdescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdescripcionKeyTyped

    }//GEN-LAST:event_txtdescripcionKeyTyped

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
    private javax.swing.JButton BTN_EXCEL;
    private javax.swing.JButton BTN_EXCEL1;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_VerAreas;
    private javax.swing.JTable JTABLE_Mant_Areas;
    private javax.swing.JTextField TXT_BuscarAreas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtcodigoarea;
    private javax.swing.JTextField txtdescripcion;
    private javax.swing.JTextField txtnombrearea;
    // End of variables declaration//GEN-END:variables
 public void MostrarAreas() {
        txtnombrearea.setEnabled(true);/*desactivar y activar nombreFacultad*/
        //Ordenar Asc, Desc
        JTABLE_Mant_Areas.setAutoCreateRowSorter(true);
        //Limpia la tabla antes de mostrar nuevos datos
        modeloTablaArea.setRowCount(0);
        try {
            //Llama al métdo que devuelve los datos de facultad
            ResultSet rs = conexionBD.listarArea();
            //Recorre cada fila del resultado y agrega a la tabla
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("id_area"),
                    rs.getString("nombre_area"),
                    rs.getString("descripcion")};

                modeloTablaArea.addRow(fila);
            }
        } catch (SQLException e) {
            //Muestra mensaje si ocurre un error en la consulta
            JOptionPane.showMessageDialog(this, "Error al mostrar Cliente:\n" + e.getMessage(),
                    "Error de consulta", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buscarArea() {
        // Limpia la tabla antes de mostrar los resultados filtrados
        modeloTablaArea.setRowCount(0);
        // obtiene el texto ingresado por el usuario
        String busqueda = TXT_BuscarAreas.getText().trim();
        try {
            // Consulta los datos usando el procedimiento almacenado en la BD
            ResultSet rs = conexionBD.buscarArea(busqueda);

            // Recorre los resultados y los anade a la tablal
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("id_area"),
                    rs.getString("nombre_area"),
                    rs.getString("descripcion"),};
                modeloTablaArea.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al buscar Areas: \n" + e.getMessage(),
                    "Error de busqueda", JOptionPane.ERROR_MESSAGE);
        }
    }

}
