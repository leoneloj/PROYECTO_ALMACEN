package proyecto_almacen;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class frm_loginSeguridad extends javax.swing.JFrame {

conexionBD conexionBD;
// Declarar esta variable al inicio de la clase LoginAcceso:
    private int intentosFallidos = 0;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frm_loginSeguridad.class.getName());

    public frm_loginSeguridad() {
        setUndecorated(true); // Oculta la barra de título por completo
        initComponents();
   this.setLocationRelativeTo(null); // Centra la ventana

        conexionBD = new conexionBD(); // Inicializar la instancia global

        // Método para llenar los cargos en el jcombobox
        this.llenarJComboBox_Cargos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ComboBox_Cargos = new javax.swing.JComboBox<>();
        txtcodigousuario = new javax.swing.JTextField();
        txtpassword = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnsalir = new javax.swing.JButton();
        btningresar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acceso al sistema..!", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ComboBox_Cargos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ComboBox_Cargos.setForeground(new java.awt.Color(0, 0, 204));
        ComboBox_Cargos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(ComboBox_Cargos, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 260, -1));

        txtcodigousuario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtcodigousuario.setForeground(new java.awt.Color(0, 0, 204));
        jPanel1.add(txtcodigousuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 260, -1));

        txtpassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtpassword.setForeground(new java.awt.Color(0, 0, 204));
        jPanel1.add(txtpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 260, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("Ingrese Codigo:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 170, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setText("Ingrese contraseña:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 200, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setText("Seleccione Cargo:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 160, -1));

        btnsalir.setBackground(new java.awt.Color(0, 0, 204));
        btnsalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnsalir.setForeground(new java.awt.Color(255, 255, 255));
        btnsalir.setText("SALIR");
        btnsalir.addActionListener(this::btnsalirActionPerformed);
        jPanel1.add(btnsalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 120, -1));

        btningresar.setBackground(new java.awt.Color(0, 0, 204));
        btningresar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btningresar.setForeground(new java.awt.Color(255, 255, 255));
        btningresar.setText("INGRESAR");
        btningresar.addActionListener(this::btningresarActionPerformed);
        jPanel1.add(btningresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 120, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 450, 180));

        jPanel2.setBackground(new java.awt.Color(0, 0, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Acceso restringido - Solo para personal autorizado");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 300, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 40));

        jPanel3.setBackground(new java.awt.Color(0, 0, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Copyright © SENATI 2026");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 200, 20));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Software empresarial, desarrollado en el tercer ciclo de Ingenieria de Software ");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 470, 20));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 650, 60));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 150, 140));

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
       // Muestra una ventana emergente de confirmación
        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de que desea salir del sistema?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        // Si el usuario hace clic en "Sí" (YES_OPTION)
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0); // Cierra la aplicación por completo
        }
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btningresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btningresarActionPerformed
       try {
            String codigo = txtcodigousuario.getText().trim();
            String password = String.valueOf(txtpassword.getPassword()).trim();
            String cargo = ComboBox_Cargos.getSelectedItem().toString();

            // 1. Validaciones corregidas (Permite alfanumérico y acepta códigos desde 5 caracteres)
            if (cargo.equals("<<Seleccionar>>")) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un cargo.", "Validación", 
                        JOptionPane.WARNING_MESSAGE);
                ComboBox_Cargos.requestFocus(); return;
            }
            if (codigo.length() < 5 || codigo.length() >= 15 || !codigo.matches("[a-zA-Z0-9]+")) {
                JOptionPane.showMessageDialog(this, "El código debe ser alfanumérico "
                        + "(letras y números) y tener entre 5 y 14 caracteres.", "Validación",
                        JOptionPane.WARNING_MESSAGE);
                txtcodigousuario.requestFocus(); return;
            }
            if (password.length() < 6) {
                JOptionPane.showMessageDialog(this, "La contraseña debe tener al menos 6 caracteres.", 
                        "Validación", JOptionPane.WARNING_MESSAGE);
                txtpassword.requestFocus(); return;
            }

            // 2. Validación de acceso con la Base de Datos
            if (conexionBD.validarLogin(codigo, EncriptadorSHA256.encriptar(password), cargo)) {
                JOptionPane.showMessageDialog(this, "Bienvenido al sistema.", "Acceso concedido", 
                        JOptionPane.INFORMATION_MESSAGE);
                intentosFallidos = 0;

                // 3. Redirección por roles adaptada al sistema de almacén
                switch (cargo.toUpperCase()) {
                    case "Cajero": new frm_cargo().setVisible(true); break;
                    case "Vendedor de Campo": new frm_menuPrincipal().setVisible(true); break;
                    default: new frm_menuPrincipal().setVisible(true); break;
                }
                this.dispose(); // Cierra el login
            } else {
                intentosFallidos++;
                if (intentosFallidos >= 3) {
                    try { conexionBD.bloquearUsuario(codigo); } catch (SQLException ignored) {}
                    JOptionPane.showMessageDialog(this, "Ha superado el número máximo de intentos."
                            + "\nSu cuenta ha sido bloqueada por seguridad.", "Cuenta bloqueada", 
                            JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas o usuario inactivo."
                        + "\nIntento " + intentosFallidos + " de 3.", "Acceso denegado", JOptionPane.ERROR_MESSAGE);
                txtpassword.setText("");
                txtpassword.requestFocus();
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error de conexión con la Base de Datos:\n" + ex.getMessage(), 
                    "Error crítico", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btningresarActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new frm_loginSeguridad().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBox_Cargos;
    private javax.swing.JButton btningresar;
    private javax.swing.JButton btnsalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField txtcodigousuario;
    private javax.swing.JPasswordField txtpassword;
    // End of variables declaration//GEN-END:variables
 /* Método para cargar los registros en el JComboBox */
    private void llenarJComboBox_Cargos() {
        try {
            ComboBox_Cargos.removeAllItems();
            ComboBox_Cargos.addItem("<<Seleccionar>>"); // Opción por defecto

            try (ResultSet rs = conexionBD.combobox_ListarCargos()) {
                while (rs != null && rs.next()) {
                    String nombreCargo = rs.getString("nombre_cargo"); // Apunta correctamente a tu columna en minúsculas[cite: 8]
                    if (nombreCargo != null) {
                        ComboBox_Cargos.addItem(nombreCargo.trim());
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar cargos: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Clase utilitaria que permite encriptar texto utilizando el algoritmo SHA-256
    public class EncriptadorSHA256 {
        public static String encriptar(String texto) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hash = md.digest(texto.getBytes());
                StringBuilder sb = new StringBuilder();
                for (byte b : hash) {
                    sb.append(String.format("%02x", b));
                }
                return sb.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Error al encriptar contraseña.", e);
            }
        }
    }
}
