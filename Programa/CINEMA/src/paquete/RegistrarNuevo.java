package paquete;

import java.awt.event.*;
import java.sql.Connection;
import javax.swing.*;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

public class RegistrarNuevo extends JFrame implements ActionListener, KeyListener {

    String Nombre, Apellido, TipTel, Telefono, Contraseña, VerifContraseña, Documento;
    String[] Tel = {"Seleccione tipo.", "Celular", "Fijo"};
    JComboBox TipoTel;
    JPasswordField TContraseña, TVerifContraseña;
    JLabel LMensaje, LMensaje2, LNombre, LApellido, LDocumento, LTipTelefono, LTelefono, LContraseña, LVerifContraseña;
    JTextField TNombre, TApellido, TDocumento, TTelefono;
    JButton Insertar, Reiniciar;
    int Codigo = 0, LimiteNombre = 30, LimiteApellido = 30, LimiteContra = 30, LimiteDocumento = 30, LimiteTel = 10;

    Connection conexion = null;
    OraclePreparedStatement pst = null;
    OracleResultSet rs = null;

    public RegistrarNuevo() {
        super("UCCine - Registrarse");

        setLayout(null);
        Etiquetas();
        CajasTexto();
        Botones();

        addWindowListener(new WindowAdapter() {
            @Override

            public void windowClosing(WindowEvent e) {
                control();
            }
        });
    }

    public void control() {
        ControlAcceso r = new ControlAcceso();
        r.setSize(300, 300);
        r.setResizable(false);
        r.setVisible(true);
        r.setDefaultCloseOperation(EXIT_ON_CLOSE);
        r.setLocationRelativeTo(null);
    }

    public void Etiquetas() {
        LMensaje = new JLabel("REGISTRARSE");
        LMensaje.setBounds(100, 20, 300, 20);
        add(LMensaje);
        LMensaje2 = new JLabel("Ingrese los datos solicitados para completar el registro.");
        LMensaje2.setBounds(10, 50, 350, 20);
        add(LMensaje2);
        LNombre = new JLabel("Nombre: ");
        LNombre.setBounds(10, 80, 100, 20);
        add(LNombre);
        LApellido = new JLabel("Apellido: ");
        LApellido.setBounds(10, 110, 100, 20);
        add(LApellido);
        LDocumento = new JLabel("Documento: ");
        LDocumento.setBounds(10, 140, 100, 20);
        add(LDocumento);
        LTipTelefono = new JLabel("Tipo de teléfono: ");
        LTipTelefono.setBounds(10, 170, 100, 20);
        add(LTipTelefono);
        LTelefono = new JLabel("Número de teléfono: ");
        LTelefono.setBounds(10, 200, 120, 20);
        add(LTelefono);
        LContraseña = new JLabel("Contraseña: ");
        LContraseña.setBounds(10, 230, 120, 20);
        add(LContraseña);
        LVerifContraseña = new JLabel("Repita la Contraseña: ");
        LVerifContraseña.setBounds(10, 260, 130, 20);
        add(LVerifContraseña);
    }

    public void CajasTexto() {
        TNombre = new JTextField();
        TNombre.setBounds(135, 80, 120, 20);
        TNombre.addKeyListener(this);
        add(TNombre);

        TApellido = new JTextField();
        TApellido.setBounds(135, 110, 120, 20);
        TApellido.addKeyListener(this);
        add(TApellido);

        TDocumento = new JTextField();
        TDocumento.setBounds(135, 140, 120, 20);
        TDocumento.addKeyListener(this);
        add(TDocumento);

        TipoTel = new JComboBox(Tel);
        TipoTel.setSelectedItem(0);
        TipoTel.setBounds(135, 170, 120, 20);
        add(TipoTel);

        TTelefono = new JTextField();
        TTelefono.setBounds(135, 200, 120, 20);
        TTelefono.addKeyListener(this);
        add(TTelefono);

        TContraseña = new JPasswordField();
        TContraseña.setBounds(135, 230, 120, 20);
        //TContraseña.addKeyListener(this);
        add(TContraseña);

        TVerifContraseña = new JPasswordField();
        TVerifContraseña.setBounds(135, 260, 120, 20);
        add(TVerifContraseña);
    }

    public void Botones() {
        Insertar = new JButton("Insertar");
        Insertar.setBounds(30, 300, 100, 20);
        Insertar.addActionListener(this);
        add(Insertar);
        Reiniciar = new JButton("Reiniciar");
        Reiniciar.setBounds(140, 300, 100, 20);
        Reiniciar.addActionListener(this);
        add(Reiniciar);

    }

    public int consultardocumento(String Documento) {
        int valido = 1;
        String CadenaDocumento = "";
        conexion = ConexionOracle.ConnectDB();
        try {
            String sql = "SELECT documento_cliente FROM CLIENTE WHERE documento_cliente = " + Documento;
            pst = (OraclePreparedStatement) conexion.prepareStatement(sql);
            rs = (OracleResultSet) pst.executeQuery();

            while (rs.next() != false) {
                CadenaDocumento = rs.getString("documento_cliente");
                valido = 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return valido;
    }

    public int CodigoCliente() {
        int codigo = 0;
        String cadena = "";
        conexion = ConexionOracle.ConnectDB();
        try {
            String sql = "SELECT codigo_cliente FROM CLIENTE WHERE codigo_cliente = (SELECT MAX(codigo_cliente) from CLIENTE)";
            pst = (OraclePreparedStatement) conexion.prepareStatement(sql);
            rs = (OracleResultSet) pst.executeQuery();

            while (rs.next() != false) {
                cadena = rs.getString("codigo_cliente");
            }

            codigo = Integer.parseInt(cadena);
            codigo = codigo + 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return codigo;
    }

    public void conexion(int Codigo, String Nombre, String Apellido, String Documento, String TipoTel, String Telefono, String Contraseña) {
        conexion = ConexionOracle.ConnectDB();

        try {
            String sql = "INSERT INTO CLIENTE (codigo_cliente, nombre_cliente, apellido_cliente, documento_cliente, tipo_telefono_cliente, telefono_cliente, contraseña_cliente) VALUES (?,?,?,?,?,?,?)";

            pst = (OraclePreparedStatement) conexion.prepareStatement(sql);
            pst.setInt(1, Codigo);
            pst.setString(2, Nombre);
            pst.setString(3, Apellido);
            pst.setString(4, Documento);
            pst.setString(5, TipoTel);
            pst.setString(6, Telefono);
            pst.setString(7, Contraseña);

            rs = (OracleResultSet) pst.executeQuery();

            JOptionPane.showMessageDialog(null, "Usuario creado correctamente.\n Sus datos son:\n\n- Código: " + Codigo + ".\n- Nombre: " + Nombre + ".\n- Apellido: " + Apellido + ".\n- Documento: " + Documento + ".\n- Tipo de teléfono: " + TipoTel + ".\n- Número de teléfono: " + Telefono + ".\n- Contraseña: " + Contraseña + "\n\nRecuerde siempre sus datos.");

            setVisible(false);
            ControlAcceso r = new ControlAcceso();
            r.setSize(300, 300);
            r.setResizable(false);
            r.setVisible(true);
            r.setDefaultCloseOperation(EXIT_ON_CLOSE);
            r.setLocationRelativeTo(null);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String args[]) {
        JFrame aplicacion = new RegistrarNuevo();
        aplicacion.setSize(350, 500);
        aplicacion.setVisible(true);
        aplicacion.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == Insertar) {
            Nombre = TNombre.getText();
            Apellido = TApellido.getText();
            Documento = TDocumento.getText();
            TipTel = (String) TipoTel.getSelectedItem();
            Telefono = TTelefono.getText();
            Contraseña = TContraseña.getText();
            VerifContraseña = TVerifContraseña.getText();

            if (Contraseña.equals(VerifContraseña) && Contraseña.length() != 0) {
                int ax = JOptionPane.showConfirmDialog(null, "Los datos que usted digito fueron: \n- Nombre: " + Nombre + ".\n- Apellido: " + Apellido + ".\n- Documento: " + Documento + ".\n- Tipo de teléfono: " + TipTel + ".\n- Número de teléfono: " + Telefono + ".\n- Contraseña: " + Contraseña + "\n\n ¿Son correctos?, confirme para crear el usuario.", "Confirmación", JOptionPane.YES_NO_OPTION);

                if (ax == JOptionPane.YES_OPTION) {
                    int documentovalido = consultardocumento(Documento);
                    if (documentovalido == 1) {
                        Codigo = CodigoCliente();
                        conexion(Codigo, Nombre, Apellido, Documento, TipTel, Telefono, Contraseña);
                    } else if (documentovalido == 0) {
                        JOptionPane.showMessageDialog(null, "Ya existe un usuario con el documento digitado.", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
                        TDocumento.requestFocus();
                    }
                } else if (ax == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(null, "Ingreso cancelado, rectifique sus datos.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "La contraseña es incorrecta o no coincide, por favor revisar.", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
                TContraseña.requestFocus();
            }
        }

        if (ae.getSource() == Reiniciar) {
            int ax2 = JOptionPane.showConfirmDialog(null, "El formulario se reiniciara\n¿Desea continuar?", "Reiniciar formulario", JOptionPane.YES_NO_OPTION);

            if (ax2 == JOptionPane.YES_OPTION) {
                TNombre.setText("");
                TApellido.setText("");
                TDocumento.setText("");
                TipoTel.setSelectedItem(Tel[0]);
                TTelefono.setText("");
                TContraseña.setText("");
                TVerifContraseña.setText("");

            } else if (ax2 == JOptionPane.NO_OPTION) {

            }
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        char c = ke.getKeyChar();

        if (ke.getSource() == TNombre) {
            if (Character.isDigit(c)) {
                ke.consume();
                JOptionPane.showMessageDialog(null, "El Nombre solo admite letras, no números.", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
            } else if (((int) ke.getKeyChar()) >= 33 && ((int) ke.getKeyChar()) <= 64 || ((int) ke.getKeyChar()) >= 91 && ((int) ke.getKeyChar()) <= 96 || ((int) ke.getKeyChar()) >= 123 && ((int) ke.getKeyChar()) <= 163 || ((int) ke.getKeyChar()) >= 166 && ((int) ke.getKeyChar()) <= 255) {
                ke.consume();
                JOptionPane.showMessageDialog(null, "No se permite el uso de caracteres especiales.", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
            } else if (((int) ke.getKeyChar()) == 32) {
                ke.consume();
                JOptionPane.showMessageDialog(null, "Este campo no admite Espacios.", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
            }

        }

        if (ke.getSource() == TApellido) {
            if (Character.isDigit(c)) {
                ke.consume();
                JOptionPane.showMessageDialog(null, "El Apellido solo admite letras, no números.", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (ke.getSource() == TDocumento) {
            if (Character.isLetter(c)) {
                ke.consume();
                JOptionPane.showMessageDialog(null, "El Documento solo admite números, no letras.", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (ke.getSource() == TTelefono) {
            if (Character.isLetter(c)) {
                ke.consume();
                JOptionPane.showMessageDialog(null, "El Teléfono solo admite números, no letras.", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (TNombre.getText().length() == LimiteNombre) {
            ke.consume();
        }
        if (TApellido.getText().length() == LimiteApellido) {
            ke.consume();
        }
        if (TDocumento.getText().length() == LimiteDocumento) {
            ke.consume();
        }
        if (TTelefono.getText().length() == LimiteTel) {
            ke.consume();
        }
        /*if (TContraseña.getText().length() == LimiteContra) {
         ke.consume();
         }*/
    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }
}
