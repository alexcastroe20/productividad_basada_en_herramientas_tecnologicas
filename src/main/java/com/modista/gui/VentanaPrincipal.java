package com.modista.gui;

import com.modista.db.DatabaseManager;
import com.modista.exceptions.ModistaException;
import com.modista.model.Cliente;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class VentanaPrincipal extends JFrame {
    private static final long serialVersionUID = 1L;
    
    // Componentes de la interfaz
    private JTextField txtNombre;
    private JTextField txtBusto;
    private JTextField txtCintura;
    private JTextField txtCadera;
    private JLabel lblResultado;
    private transient DatabaseManager dbManager;

    public VentanaPrincipal() {
        dbManager = DatabaseManager.getInstance();
        initializeUI();
        configurarValidadores();
    }

    // Constantes para mensajes
    // Constantes de la interfaz
    private static final String TITULO_VENTANA = "Sistema de Cálculo de Tallas";
    private static final String ERROR = "Error";
    private static final String EXITO = "Éxito";
    private static final String MSG_MEDIDAS_INVALIDAS = "Las medidas deben estar en rangos válidos";
    private static final String MSG_VALORES_NUMERICOS = "Por favor ingrese valores numéricos válidos";
    private static final String MSG_NOMBRE_REQUERIDO = "Por favor ingrese el nombre de la cliente";
    private static final String MSG_CLIENTE_GUARDADO = "Cliente guardado exitosamente";
    
    // Constantes para validación de medidas
    private static final int MIN_BUSTO = 70;
    private static final int MAX_BUSTO = 150;
    private static final int MIN_CINTURA = 50;
    private static final int MAX_CINTURA = 130;
    private static final int MIN_CADERA = 80;
    private static final int MAX_CADERA = 160;
    
    // Constantes de tallas
    private static final double LIMITE_XXS = 80.0;
    private static final double LIMITE_XS = 90.0;
    private static final double LIMITE_S = 100.0;
    private static final double LIMITE_M = 110.0;
    private static final double LIMITE_L = 120.0;
    private static final double LIMITE_XL = 130.0;
    
    // Métodos helper para la interfaz
    private JLabel createLabel(String text, String tooltip) {
        JLabel label = new JLabel(text);
        label.setToolTipText(tooltip);
        return label;
    }
    
    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setColumns(10);
        return field;
    }
    
    private void configurarValidadores() {
        // Validador para campos numéricos
        InputVerifier numericVerifier = new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                JTextField field = (JTextField) input;
                try {
                    if (!field.getText().trim().isEmpty()) {
                        Double.parseDouble(field.getText());
                    }
                    return true;
                } catch (NumberFormatException e) {
                    mostrarError(MSG_VALORES_NUMERICOS);
                    return false;
                }
            }
        };
        
        txtBusto.setInputVerifier(numericVerifier);
        txtCintura.setInputVerifier(numericVerifier);
        txtCadera.setInputVerifier(numericVerifier);
    }
    
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, ERROR, JOptionPane.ERROR_MESSAGE);
    }
    
    private void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, EXITO, JOptionPane.INFORMATION_MESSAGE);
    }

    private void initializeUI() {
        setTitle(TITULO_VENTANA);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Campos de entrada con validación
        panel.add(createLabel("Nombre:", "Ingrese el nombre de la cliente"));
        txtNombre = createTextField();
        panel.add(txtNombre);

        panel.add(createLabel("Busto (cm):", String.format("Rango válido: %d-%d cm", MIN_BUSTO, MAX_BUSTO)));
        txtBusto = createTextField();
        panel.add(txtBusto);

        panel.add(createLabel("Cintura (cm):", String.format("Rango válido: %d-%d cm", MIN_CINTURA, MAX_CINTURA)));
        txtCintura = createTextField();
        panel.add(txtCintura);

        panel.add(createLabel("Cadera (cm):", String.format("Rango válido: %d-%d cm", MIN_CADERA, MAX_CADERA)));
        txtCadera = createTextField();
        panel.add(txtCadera);

        // Botones
        JButton btnCalcular = new JButton("Calcular Talla");
        JButton btnLimpiar = new JButton("Limpiar");
        JButton btnGuardar = new JButton("Guardar");

        // Panel para resultado
        lblResultado = new JLabel("");
        lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
        lblResultado.setFont(new Font("Arial", Font.BOLD, 16));

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnCalcular);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnGuardar);

        // Añadir componentes al frame
        add(panel, BorderLayout.NORTH);
        add(lblResultado, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Eventos
        btnCalcular.addActionListener(e -> calcularTalla());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnGuardar.addActionListener(e -> guardarCliente());
    }

    private void calcularTalla() {
        try {
            double busto = Double.parseDouble(txtBusto.getText().trim());
            double cintura = Double.parseDouble(txtCintura.getText().trim());
            double cadera = Double.parseDouble(txtCadera.getText().trim());

            if (!validarMedidas(busto, cintura, cadera)) {
                mostrarError(MSG_MEDIDAS_INVALIDAS);
                return;
            }

            String talla = determinarTalla(busto, cintura, cadera);
            lblResultado.setText("Talla calculada: " + talla);
            lblResultado.setForeground(new Color(0, 100, 0));
        } catch (NumberFormatException ex) {
            mostrarError(MSG_VALORES_NUMERICOS);
        }
    }

    private boolean validarMedidas(double busto, double cintura, double cadera) {
        return busto >= MIN_BUSTO && busto <= MAX_BUSTO &&
               cintura >= MIN_CINTURA && cintura <= MAX_CINTURA &&
               cadera >= MIN_CADERA && cadera <= MAX_CADERA;
    }

    private String determinarTalla(double busto, double cintura, double cadera) {
        // Cálculo ponderado de talla basado en las medidas
        double promedio = (busto * 0.4 + cintura * 0.3 + cadera * 0.3);
        
        if (promedio < LIMITE_XXS) return "XXS";
        else if (promedio < LIMITE_XS) return "XS";
        else if (promedio < LIMITE_S) return "S";
        else if (promedio < LIMITE_M) return "M";
        else if (promedio < LIMITE_L) return "L";
        else if (promedio < LIMITE_XL) return "XL";
        else return "XXL";
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtBusto.setText("");
        txtCintura.setText("");
        txtCadera.setText("");
        lblResultado.setText("");
    }

    private void guardarCliente() {
        if (txtNombre.getText().trim().isEmpty()) {
            mostrarError(MSG_NOMBRE_REQUERIDO);
            return;
        }

        try {
            double busto = Double.parseDouble(txtBusto.getText().trim());
            double cintura = Double.parseDouble(txtCintura.getText().trim());
            double cadera = Double.parseDouble(txtCadera.getText().trim());

            if (!validarMedidas(busto, cintura, cadera)) {
                mostrarError(MSG_MEDIDAS_INVALIDAS);
                return;
            }

            Cliente cliente = new Cliente(txtNombre.getText().trim(), busto, cintura, cadera);
            cliente.setTalla(determinarTalla(busto, cintura, cadera));

            try {
                dbManager.guardarCliente(cliente);
                mostrarExito(MSG_CLIENTE_GUARDADO);
                limpiarCampos();
            } catch (Exception ex) {
                throw new ModistaException.DatabaseException("Error al guardar el cliente en la base de datos", ex);
            }
        } catch (NumberFormatException ex) {
            mostrarError(MSG_VALORES_NUMERICOS);
        } catch (ModistaException ex) {
            mostrarError(ex.getMessage());
        } catch (Exception ex) {
            mostrarError("Error inesperado: " + ex.getMessage());
        }
    }
}
