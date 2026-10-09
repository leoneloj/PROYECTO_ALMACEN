package proyecto_almacen;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

public class frm_menuPrincipal extends javax.swing.JFrame {

    // =====================================================================
    // 1. VARIABLES DE LA CLASE
    // =====================================================================
    // 1.1 Sidebar
    private static final int ANCHO_SIDEBAR = 210;
    private final List<ItemMenu> itemsMenu = new ArrayList<>();
    private ItemMenu itemConfig, itemSalir;

    // 1.2 Header
    private JLabel lblFecha, lblHora, lblUsuario, lblRol;
    private javax.swing.Timer relojTimer;
    private Point mouseInicio, ventanaInicio;

    // 1.3 Contenido
    private JPanel panelContenido;
    private JLabel lblBienvenida;

    // =====================================================================
    // 2. CONSTRUCTOR
    // =====================================================================
    // Arma la ventana en orden: layout, sidebar, header y contenido
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frm_menuPrincipal.class.getName());

    public frm_menuPrincipal() {
        setUndecorated(true); // Oculta la barra de título por completo
        initComponents();
        configurarLayout();         // 5.
        armarSidebar();             // 6.
        armarHeader();              // 7.
        armarContenido();           // 8.

        setLocationRelativeTo(null);
    }
    // =====================================================================
    // 3. CÓDIGO GENERADO POR NETBEANS (no editar)
    // =====================================================================

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelSidebar_Izquierda = new javax.swing.JPanel();
        jPanelHeader = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelSidebar_Izquierda.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanelSidebar_IzquierdaLayout = new javax.swing.GroupLayout(jPanelSidebar_Izquierda);
        jPanelSidebar_Izquierda.setLayout(jPanelSidebar_IzquierdaLayout);
        jPanelSidebar_IzquierdaLayout.setHorizontalGroup(
            jPanelSidebar_IzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1806, Short.MAX_VALUE)
        );
        jPanelSidebar_IzquierdaLayout.setVerticalGroup(
            jPanelSidebar_IzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelSidebar_Izquierda, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 1810, 70));

        jPanelHeader.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanelHeaderLayout = new javax.swing.GroupLayout(jPanelHeader);
        jPanelHeader.setLayout(jPanelHeaderLayout);
        jPanelHeaderLayout.setHorizontalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
        );
        jPanelHeaderLayout.setVerticalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1096, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 1100));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        java.awt.EventQueue.invokeLater(() -> new frm_menuPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JPanel jPanelSidebar_Izquierda;
    // End of variables declaration//GEN-END:variables
 // =====================================================================
    // 5. LAYOUT GENERAL DE LA VENTANA
    // =====================================================================
    // 5.1 Reemplaza el diseño del NetBeans por BorderLayout:
    //     sidebar a la izquierda, y a la derecha header arriba + contenido
    private void configurarLayout() {
        getContentPane().removeAll();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(238, 243, 251));

        // Sidebar con degradado azul
        jPanelSidebar_Izquierda = new JPanel(new org.netbeans.lib.awtextra.AbsoluteLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setPaint(new GradientPaint(0, 0, new Color(9, 34, 100),
                        0, getHeight(), new Color(11, 54, 145)));
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }

            @Override
            public void doLayout() {
                super.doLayout();
                ubicarInferiores();      // Configuración y Cerrar Sesión siempre al fondo
            }
        };
        jPanelSidebar_Izquierda.setBorder(null);
        jPanelSidebar_Izquierda.setPreferredSize(new Dimension(ANCHO_SIDEBAR, 0));

        // Header
        jPanelHeader.setBorder(null);
        jPanelHeader.setBackground(new Color(10, 44, 120));
        jPanelHeader.setPreferredSize(new Dimension(0, 78));

        // Zona de contenido (aquí va el dashboard)
        panelContenido = new JPanel(new GridBagLayout());
        panelContenido.setBackground(new Color(238, 243, 251));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));

        JPanel derecha = new JPanel(new BorderLayout());
        derecha.add(jPanelHeader, BorderLayout.NORTH);
        derecha.add(panelContenido, BorderLayout.CENTER);

        getContentPane().add(jPanelSidebar_Izquierda, BorderLayout.WEST);
        getContentPane().add(derecha, BorderLayout.CENTER);

        // Opcional: evita que la ventana tape la barra de tareas
        //setMaximizedBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
        setExtendedState(JFrame.MAXIMIZED_BOTH);   // ocupa toda la pantalla
    }

    // =====================================================================
    // 6. SIDEBAR (MENÚ LATERAL IZQUIERDO)
    // =====================================================================
    // 6.1 Crea el logo, la línea separadora, los ítems del menú y los botones inferiores
    private void armarSidebar() {
        JPanel p = jPanelSidebar_Izquierda;
        p.removeAll();
        int anchoItem = ANCHO_SIDEBAR - 24;

        // ---- Logo y título ----
        JLabel logo = new JLabel(FontIcon.of(FontAwesomeSolid.UNIVERSITY, 30, Color.WHITE));
        p.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 16, 42, 42));

        JLabel titulo = new JLabel("ALMACEN");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 17));
        p.add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 16, 170, 24));

        JLabel lema = new JLabel("Exelencia en Tecnologia");
        lema.setForeground(new Color(180, 198, 232));
        lema.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        p.add(lema, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 40, 170, 18));

        // ---- Línea separadora ----
        JPanel linea = new JPanel();
        linea.setBackground(new Color(40, 75, 150));
        p.add(linea, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 76, anchoItem, 1));

        // ---- Ítems del menú ----
        Object[][] datos = {
            {"Inicio", FontAwesomeSolid.HOME},
            {"Personal y usuarios", FontAwesomeSolid.USERS},
            {"Empresa y sucursales", FontAwesomeSolid.BUILDING},
            {"Almacenes", FontAwesomeSolid.WAREHOUSE},
            {"Productos y categorias", FontAwesomeSolid.TAGS},
            {"Inventario y movimiento", FontAwesomeSolid.BOXES},
            {"Proveedores y compras", FontAwesomeSolid.SHOPPING_CART},
            {"Clientes y ventas", FontAwesomeSolid.FILE_INVOICE},};
        int y = 90;
        for (Object[] d : datos) {
            final String texto = (String) d[0];
            final ItemMenu it = new ItemMenu(texto, (FontAwesomeSolid) d[1]);
            it.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    seleccionar(it);
                    abrirModulo(texto);
                }
            });
            itemsMenu.add(it);
            p.add(it, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, y, anchoItem, 40));
            y += 44;
        }
        itemsMenu.get(0).setSeleccionado(true);   // "Inicio" activo por defecto

        // ---- Parte inferior: Configuración y Cerrar Sesión ----
        itemConfig = new ItemMenu("Configuración", FontAwesomeSolid.COG);
        itemConfig.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new frm_area().setVisible(true);
            }
        });

        itemSalir = new ItemMenu("Cerrar Sesión", FontAwesomeSolid.POWER_OFF);
        itemSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cerrarSesion();
            }
        });

        p.add(itemConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 600, anchoItem, 40));
        p.add(itemSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 644, anchoItem, 40));

        p.revalidate();
        p.repaint();
    } // <--- ¡Asegúrate de que esta llave cierre el método armarSidebar()!

    // 6.2 Mantiene Configuración y Cerrar Sesión pegados al borde inferior
    private void ubicarInferiores() {
        if (itemConfig == null || itemSalir == null) {
            return;
        }
        int h = jPanelSidebar_Izquierda.getHeight();
        int ancho = ANCHO_SIDEBAR - 24;
        itemConfig.setBounds(12, h - 96, ancho, 40);
        itemSalir.setBounds(12, h - 52, ancho, 40);
    }

    // 6.3 Marca como activo el ítem elegido y desmarca los demás
    private void seleccionar(ItemMenu elegido) {
        for (ItemMenu it : itemsMenu) {
            it.setSeleccionado(it == elegido);
        }
    }

    // 6.4 Abre el formulario que corresponde al módulo (agrega aquí los demás)
    private void abrirModulo(String nombre) {
        switch (nombre) {
            case "Personal y usuarios":
                new frm_personalUsuarios().setVisible(true);
                break;
            case "Empresa y sucursales":
                new frm_empresaSucursales().setVisible(true);
                break;
            case "Productos y categorías":
                new frm_productosCategorias().setVisible(true);
                break;
        }
    }

    // 6.5 Pide confirmación y cierra la sesión
    private void cerrarSesion() {
        int r = JOptionPane.showConfirmDialog(this, "¿Deseas cerrar sesión?",
                "Cerrar sesión", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (r == JOptionPane.YES_OPTION) {
            dispose();
            // new Frm_LoginAcceso().setVisible(true);
        }
    }

    // 6.6 Clase: ítem del menú (ícono + texto, con hover y estado seleccionado)
    private class ItemMenu extends JComponent {

        private final String texto;
        private final Icon icono;
        private boolean seleccionado = false;
        private boolean hover = false;

        ItemMenu(String texto, FontAwesomeSolid ico) {
            this.texto = texto;
            this.icono = FontIcon.of(ico, 18, Color.WHITE);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    hover = true;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    hover = false;
                    repaint();
                }
            });
        }

        void setSeleccionado(boolean s) {
            seleccionado = s;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            // Fondo
            if (seleccionado) {
                g2.setColor(new Color(37, 99, 235));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
            } else if (hover) {
                g2.setColor(new Color(255, 255, 255, 28));   // blanco translúcido
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
            }

            // Ícono en posición fija (todos alineados)
            int iy = (getHeight() - icono.getIconHeight()) / 2;
            icono.paintIcon(this, g2, 18, iy);

            // Texto en posición fija
            g2.setFont(new Font("Segoe UI", seleccionado ? Font.BOLD : Font.PLAIN, 12));
            g2.setColor(Color.WHITE);
            FontMetrics fm = g2.getFontMetrics();
            int ty = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
            g2.drawString(texto, 54, ty);

            g2.dispose();
        }
    }
    // =====================================================================
    // 7. HEADER (BARRA SUPERIOR)
    // =====================================================================
    // 7.1 Arma el título a la izquierda, y a la derecha fecha/hora, usuario y botones de ventana

    private void armarHeader() {
        JPanel h = jPanelHeader;
        h.removeAll();
        h.setLayout(new BorderLayout());
        h.setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 6));
        h.setBackground(new Color(10, 44, 120));

        // ---------- IZQUIERDA: título ----------
        JPanel izq = new JPanel(new GridBagLayout());
        izq.setOpaque(false);

        JLabel titulo = new JLabel("Sistema de Gestión tecnologicos");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));

        JLabel subtitulo = new JLabel("Administración De almacen de prodcutos Teconologicos");
        subtitulo.setForeground(new Color(200, 215, 240));
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
        textos.add(titulo);
        textos.add(subtitulo);

        izq.add(textos);
        h.add(izq, BorderLayout.WEST);

        // ---------- DERECHA: fecha, usuario y botones ----------
        JPanel der = new JPanel(new GridBagLayout());
        der.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        // Fecha y hora
        JLabel icoCal = new JLabel(FontIcon.of(FontAwesomeSolid.CALENDAR_ALT, 24, Color.WHITE));
        lblFecha = new JLabel();
        lblFecha.setForeground(Color.WHITE);
        lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblHora = new JLabel();
        lblHora.setForeground(new Color(200, 215, 240));
        lblHora.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JPanel fechaHora = new JPanel();
        fechaHora.setOpaque(false);
        fechaHora.setLayout(new BoxLayout(fechaHora, BoxLayout.Y_AXIS));
        fechaHora.add(lblFecha);
        fechaHora.add(lblHora);

        // Usuario
        JLabel avatar = new JLabel(FontIcon.of(FontAwesomeSolid.USER_CIRCLE, 38, Color.WHITE));
        lblUsuario = new JLabel("admin");
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblRol = new JLabel("Administrador");
        lblRol.setForeground(new Color(200, 215, 240));
        lblRol.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        JPanel datosUsuario = new JPanel();
        datosUsuario.setOpaque(false);
        datosUsuario.setLayout(new BoxLayout(datosUsuario, BoxLayout.Y_AXIS));
        datosUsuario.add(lblUsuario);
        datosUsuario.add(lblRol);

        // Botones de ventana
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        botones.setOpaque(false);
        botones.add(new BotonVentana(FontAwesomeSolid.MINUS, new Color(255, 255, 255, 40),
                () -> setExtendedState(JFrame.ICONIFIED)));
        botones.add(new BotonVentana(FontAwesomeSolid.WINDOW_MAXIMIZE, new Color(255, 255, 255, 40),
                this::alternarMaximizado));
        botones.add(new BotonVentana(FontAwesomeSolid.TIMES, new Color(220, 38, 38),
                () -> System.exit(0)));

        // Ensamblado con GridBagLayout
        c.gridx = 0;
        c.insets = new Insets(0, 0, 0, 10);
        der.add(icoCal, c);
        c.gridx = 1;
        c.insets = new Insets(0, 0, 0, 22);
        der.add(fechaHora, c);
        c.gridx = 2;
        c.insets = new Insets(0, 0, 0, 22);
        der.add(crearSeparadorVertical(), c);
        c.gridx = 3;
        c.insets = new Insets(0, 0, 0, 10);
        der.add(avatar, c);
        c.gridx = 4;
        c.insets = new Insets(0, 0, 0, 30);
        der.add(datosUsuario, c);
        c.gridx = 5;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.NORTHEAST;          // pegado arriba a la derecha
        der.add(botones, c);

        h.add(der, BorderLayout.EAST);

        habilitarArrastre(h);
        iniciarReloj();

        h.revalidate();
        h.repaint();
    }

    // 7.2 Línea vertical fina que separa la fecha del usuario
    private JComponent crearSeparadorVertical() {
        JPanel sep = new JPanel();
        sep.setBackground(new Color(70, 110, 190));
        sep.setPreferredSize(new Dimension(1, 38));
        return sep;
    }

    // 7.3 Reloj en vivo: actualiza fecha y hora cada segundo
    private void iniciarReloj() {
        final java.time.format.DateTimeFormatter fFecha
                = java.time.format.DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy",
                        new java.util.Locale("es", "PE"));
        final java.time.format.DateTimeFormatter fHora
                = java.time.format.DateTimeFormatter.ofPattern("hh:mm:ss a",
                        new java.util.Locale("es", "PE"));
        Runnable actualizar = () -> {
            java.time.LocalDateTime ahora = java.time.LocalDateTime.now();
            lblFecha.setText(ahora.format(fFecha));
            lblHora.setText(ahora.format(fHora));
        };
        actualizar.run();
        relojTimer = new javax.swing.Timer(1000, e -> actualizar.run());
        relojTimer.start();
    }

    // 7.4 Muestra el usuario que inició sesión (se llama desde el login)
    public void setUsuarioActual(String nombre, String rol) {
        lblUsuario.setText(nombre);
        lblRol.setText(rol);
        if (lblBienvenida != null) {
            lblBienvenida.setText("¡Bienvenido, " + nombre + "!");
        }
    }

    // 7.5 Alterna entre ventana maximizada y tamaño normal
    private void alternarMaximizado() {
        if ((getExtendedState() & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
            setExtendedState(JFrame.NORMAL);
            setSize(1200, 700);
            setLocationRelativeTo(null);
        } else {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
    }

    // 7.6 Permite mover la ventana arrastrando el header (doble clic = maximizar)
    private void habilitarArrastre(JComponent zona) {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseInicio = e.getLocationOnScreen();
                ventanaInicio = getLocation();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if ((getExtendedState() & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
                    return;
                }
                Point p = e.getLocationOnScreen();
                setLocation(ventanaInicio.x + p.x - mouseInicio.x,
                        ventanaInicio.y + p.y - mouseInicio.y);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    alternarMaximizado();
                }
            }
        };
        zona.addMouseListener(ma);
        zona.addMouseMotionListener(ma);
    }
// 7.7 Clase: botón de ventana (minimizar / maximizar / cerrar)

    private class BotonVentana extends JComponent {

        private final Icon icono;
        private final Color colorHover;
        private boolean hover = false;

        BotonVentana(FontAwesomeSolid ico, Color colorHover, Runnable accion) {
            this.icono = FontIcon.of(ico, 12, Color.WHITE);
            this.colorHover = colorHover;
            setPreferredSize(new Dimension(42, 30));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    hover = true;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    hover = false;
                    repaint();
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    accion.run();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            if (hover) {
                g2.setColor(colorHover);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
            icono.paintIcon(this, g2,
                    (getWidth() - icono.getIconWidth()) / 2,
                    (getHeight() - icono.getIconHeight()) / 2);
            g2.dispose();
        }
    }

    // =====================================================================
    // 8. CONTENIDO DEL DASHBOARD
    // =====================================================================
    // 8.1 Acomoda las piezas en 2 columnas: bienvenida + estadísticas (izq) y accesos rápidos (der)
    private void armarContenido() {
        panelContenido.removeAll();

        // Columna izquierda (69%), fila 0: tarjeta de bienvenida
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.69;
        c.weighty = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTH;
        c.insets = new Insets(0, 0, 14, 14);
        panelContenido.add(crearTarjetaBienvenida(), c);

        // Columna izquierda, fila 1: las 4 tarjetas de estadísticas
        GridBagConstraints e = new GridBagConstraints();
        e.gridx = 0;
        e.gridy = 1;
        e.weightx = 0.69;
        e.weighty = 0;
        e.fill = GridBagConstraints.HORIZONTAL;
        e.anchor = GridBagConstraints.NORTH;
        e.insets = new Insets(0, 0, 14, 14);
        panelContenido.add(crearFilaEstadisticas(), e);

        // Columna derecha (31%): accesos rápidos, ocupa 2 filas
        GridBagConstraints d = new GridBagConstraints();
        d.gridx = 1;
        d.gridy = 0;
        d.gridheight = 2;
        d.weightx = 0.31;
        d.weighty = 0;
        d.fill = GridBagConstraints.HORIZONTAL;
        d.anchor = GridBagConstraints.NORTH;
        d.insets = new Insets(0, 0, 14, 0);
        panelContenido.add(crearPanelAccesos(), d);

        // Relleno que empuja todo hacia arriba (siempre al final)
        GridBagConstraints relleno = new GridBagConstraints();
        relleno.gridx = 0;
        relleno.gridy = 99;
        relleno.gridwidth = 2;
        relleno.weighty = 1;
        relleno.fill = GridBagConstraints.VERTICAL;
        panelContenido.add(Box.createVerticalGlue(), relleno);

        panelContenido.revalidate();
        panelContenido.repaint();
    }
    // ---------------------------------------------------------------------
    // 8.2 TARJETA DE BIENVENIDA
    // ---------------------------------------------------------------------
    // 8.2.1 Crea la tarjeta: círculo con birrete + saludo + texto descriptivo

    private JPanel crearTarjetaBienvenida() {
        JPanel tarjeta = new TarjetaBienvenida();
        tarjeta.setLayout(new BorderLayout());
        tarjeta.setBorder(BorderFactory.createEmptyBorder(14, 24, 14, 24));
        tarjeta.setPreferredSize(new Dimension(0, 120));

        // Ícono circular con birrete
        JPanel izq = new JPanel(new GridBagLayout());
        izq.setOpaque(false);
        izq.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        izq.add(new IconoCircular(FontAwesomeSolid.GRADUATION_CAP, 72, new Color(21, 82, 190)));
        tarjeta.add(izq, BorderLayout.WEST);

        // Textos
        lblBienvenida = new JLabel("¡Bienvenido, admin!");
        lblBienvenida.setForeground(new Color(10, 44, 120));
        lblBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 26));

        JLabel linea1 = new JLabel("Gestiona la información de nuestro Almacen de productos téconologicos");
        JLabel linea2 = new JLabel("de manera eficiente y segura.");
        for (JLabel l : new JLabel[]{linea1, linea2}) {
            l.setForeground(new Color(70, 85, 115));
            l.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        }

        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
        textos.add(lblBienvenida);
        textos.add(Box.createVerticalStrut(6));
        textos.add(linea1);
        textos.add(linea2);

        JPanel centro = new JPanel(new GridBagLayout());   // centra los textos en vertical
        centro.setOpaque(false);
        GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.WEST;
        gc.weightx = 1;
        centro.add(textos, gc);
        tarjeta.add(centro, BorderLayout.CENTER);

        return tarjeta;
    }

    // 8.2.2 Clase: fondo blanco con degradado celeste a la derecha y borde redondeado
    private class TarjetaBienvenida extends JPanel {

        TarjetaBienvenida() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Shape forma = new java.awt.geom.RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);

            g2.setColor(Color.WHITE);
            g2.fill(forma);

            g2.setClip(forma);
            g2.setPaint(new GradientPaint(getWidth() * 0.5f, 0, new Color(255, 255, 255, 0),
                    getWidth(), 0, new Color(205, 224, 250)));
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.setClip(null);

            g2.setColor(new Color(214, 224, 240));
            g2.draw(forma);
            g2.dispose();
        }
    }

    // ---------------------------------------------------------------------
    // 8.3 ACCESOS RÁPIDOS
    // ---------------------------------------------------------------------
    // 8.3.1 Crea el panel con los 4 botones de acceso rápido en cuadrícula 2x2
    private JPanel crearPanelAccesos() {
        PanelRedondeado panel = new PanelRedondeado();
        panel.setLayout(new BorderLayout(0, 12));
        panel.setBorder(BorderFactory.createEmptyBorder(14, 16, 16, 16));
        panel.setPreferredSize(new Dimension(0, 258));   // misma altura que bienvenida + estadísticas

        JLabel titulo = new JLabel("Accesos rápidos",
                FontIcon.of(FontAwesomeSolid.BOLT, 18, new Color(10, 44, 120)), SwingConstants.LEFT);
        titulo.setIconTextGap(10);
        titulo.setForeground(new Color(10, 44, 120));
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panel.add(titulo, BorderLayout.NORTH);

        JPanel rejilla = new JPanel(new GridLayout(2, 2, 12, 12));
        rejilla.setOpaque(false);
        rejilla.add(new BotonAcceso("Registrar<br>Docente", FontAwesomeSolid.USER_PLUS,
                new Color(37, 99, 235), () -> abrirModulo("Docentes")));
        rejilla.add(new BotonAcceso("Registrar<br>Curso", FontAwesomeSolid.BOOK_OPEN,
                new Color(22, 163, 74), () -> abrirModulo("Cursos")));
        rejilla.add(new BotonAcceso("Plan de<br>Estudios", FontAwesomeSolid.FILE_ALT,
                new Color(124, 58, 237), () -> abrirModulo("Plan de Estudios")));
        rejilla.add(new BotonAcceso("Asignar<br>Carga", FontAwesomeSolid.CALENDAR_ALT,
                new Color(249, 115, 22), () -> abrirModulo("Carga Académica")));
        panel.add(rejilla, BorderLayout.CENTER);

        return panel;
    }

    // 8.3.2 Clase: botón de acceso rápido (cuadro de color con ícono + texto, con hover)
    private class BotonAcceso extends JPanel {

        private boolean hover = false;

        BotonAcceso(String textoHtml, FontAwesomeSolid ico, Color color, Runnable accion) {
            setOpaque(false);
            setLayout(new BorderLayout(12, 0));
            setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 8));
            setCursor(new Cursor(Cursor.HAND_CURSOR));

            JPanel contIcono = new JPanel(new GridBagLayout());   // evita que el ícono se estire
            contIcono.setOpaque(false);
            contIcono.add(new IconoCuadrado(ico, 42, color));
            add(contIcono, BorderLayout.WEST);

            JLabel lbl = new JLabel("<html>" + textoHtml + "</html>");
            lbl.setForeground(new Color(10, 44, 120));
            lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            add(lbl, BorderLayout.CENTER);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    hover = true;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    hover = false;
                    repaint();
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    accion.run();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(hover ? new Color(232, 240, 253) : Color.WHITE);
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 12, 12);
            g2.setColor(hover ? new Color(150, 180, 235) : new Color(214, 224, 240));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 12, 12);
            g2.dispose();
        }
    }

    // ---------------------------------------------------------------------
    // 8.4 TARJETAS DE ESTADÍSTICAS
    // ---------------------------------------------------------------------
    // 8.4.1 Crea la fila con las 4 tarjetas (Docentes, Facultades, Escuelas, Cursos)
    private JPanel crearFilaEstadisticas() {
        JPanel fila = new JPanel(new GridLayout(1, 4, 12, 0));
        fila.setOpaque(false);
        fila.setPreferredSize(new Dimension(0, 124));

        fila.add(crearTarjetaEstadistica("Total Docentes", "48", "5%",
                "Docentes registrados en el sistema",
                FontAwesomeSolid.USERS, new Color(37, 99, 235)));
        fila.add(crearTarjetaEstadistica("Facultades", "6", "0%",
                "Total de facultades",
                FontAwesomeSolid.UNIVERSITY, new Color(22, 163, 74)));
        fila.add(crearTarjetaEstadistica("Escuelas Prof.", "12", "9%",
                "Total de escuelas profesionales",
                FontAwesomeSolid.GRADUATION_CAP, new Color(124, 58, 237)));
        fila.add(crearTarjetaEstadistica("Cursos", "86", "6%",
                "Total de cursos registrados",
                FontAwesomeSolid.BOOK_OPEN, new Color(249, 115, 22)));
        return fila;
    }
    // 8.4.2 Crea UNA tarjeta: ícono de color, título, número grande, porcentaje y descripción

    private JPanel crearTarjetaEstadistica(String titulo, String valor, String porcentaje,
            String descripcion, FontAwesomeSolid ico, Color color) {
        PanelRedondeado tarjeta = new PanelRedondeado();
        tarjeta.setLayout(new BorderLayout(0, 8));
        tarjeta.setBorder(BorderFactory.createEmptyBorder(12, 12, 10, 12));

        // ---- Arriba: ícono + título + número + porcentaje ----
        JPanel arriba = new JPanel(new BorderLayout(10, 0));
        arriba.setOpaque(false);
        JPanel contIcono = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 2));   // ícono pegado arriba
        contIcono.setOpaque(false);
        contIcono.add(new IconoCuadrado(ico, 44, color));
        arriba.add(contIcono, BorderLayout.WEST);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setForeground(new Color(10, 44, 120));
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 12));

        JLabel lblValor = new JLabel(valor);
        lblValor.setForeground(new Color(10, 44, 120));
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 30));

        JLabel lblPorc = new JLabel(porcentaje,
                FontIcon.of(FontAwesomeSolid.ARROW_UP, 10, new Color(22, 163, 74)), SwingConstants.LEFT);
        lblPorc.setIconTextGap(3);
        lblPorc.setForeground(new Color(22, 163, 74));
        lblPorc.setFont(new Font("Segoe UI", Font.BOLD, 12));

        JPanel filaNumero = new JPanel(new BorderLayout());
        filaNumero.setOpaque(false);
        filaNumero.add(lblValor, BorderLayout.WEST);
        filaNumero.add(lblPorc, BorderLayout.EAST);

        JPanel textos = new JPanel(new BorderLayout());
        textos.setOpaque(false);
        textos.add(lblTitulo, BorderLayout.NORTH);
        textos.add(filaNumero, BorderLayout.CENTER);
        arriba.add(textos, BorderLayout.CENTER);

        tarjeta.add(arriba, BorderLayout.CENTER);

        // ---- Abajo: descripción ----
        JLabel lblDesc = new JLabel(descripcion);
        lblDesc.setForeground(new Color(110, 125, 150));
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        tarjeta.add(lblDesc, BorderLayout.SOUTH);

        return tarjeta;
    }

    // ---------------------------------------------------------------------
    // 8.5 COMPONENTES REUTILIZABLES (los usan varias tarjetas)
    // ---------------------------------------------------------------------
    // 8.5.1 Clase: panel blanco con bordes redondeados (base de todas las tarjetas)
    private class PanelRedondeado extends JPanel {

        PanelRedondeado() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
            g2.setColor(new Color(214, 224, 240));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
            g2.dispose();
        }
    }

    // 8.5.2 Clase: cuadro redondeado de color con ícono blanco (estadísticas y accesos)
    private class IconoCuadrado extends JComponent {

        private final Icon icono;
        private final Color color;

        IconoCuadrado(FontAwesomeSolid ico, int tamano, Color color) {
            this.icono = FontIcon.of(ico, tamano / 2, Color.WHITE);
            this.color = color;
            setPreferredSize(new Dimension(tamano, tamano));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
            icono.paintIcon(this, g2,
                    (getWidth() - icono.getIconWidth()) / 2,
                    (getHeight() - icono.getIconHeight()) / 2);
            g2.dispose();
        }
    }

    // 8.5.3 Clase: círculo de color con ícono blanco (birrete de la bienvenida)
    private class IconoCircular extends JComponent {

        private final Icon icono;
        private final Color color;

        IconoCircular(FontAwesomeSolid ico, int tamano, Color color) {
            this.icono = FontIcon.of(ico, tamano / 2, Color.WHITE);
            this.color = color;
            setPreferredSize(new Dimension(tamano, tamano));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fillOval(0, 0, getWidth() - 1, getHeight() - 1);
            icono.paintIcon(this, g2,
                    (getWidth() - icono.getIconWidth()) / 2,
                    (getHeight() - icono.getIconHeight()) / 2);
            g2.dispose();
        }
    }
}
