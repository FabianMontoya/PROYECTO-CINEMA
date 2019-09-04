package paquete;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.Connection;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

public class ControlAcceso extends JFrame implements ActionListener, KeyListener {

    JButton Ingresar, Registrar;
    JLabel Lusuario, Lpassword, LLogin, LTitulo;
    JTextField Tusuario;
    JPasswordField Tpassword;

    Connection conn = null;
    OraclePreparedStatement pst = null;
    OracleResultSet rs = null;

    String usuario, contra;
    int LimiteNombre = 30, LimiteContra = 30;

    ControlAcceso() {
        super("Acceso - UCCine");
        setLayout(null);

        LLogin = new JLabel(new ImageIcon(getClass().getResource("login.png")));
        LLogin.setBounds(5, 70, 55, 55);

        Tusuario = new JTextField();
        Tusuario.setBounds(145, 70, 100, 20);
        Tusuario.addKeyListener(this);

        Tpassword = new JPasswordField();
        Tpassword.setBounds(145, 100, 100, 20);
        Tpassword.addKeyListener(this);

        Lusuario = new JLabel("Documento:");
        Lusuario.setBounds(70, 70, 100, 20);

        Lpassword = new JLabel("Contraseña:");
        Lpassword.setBounds(70, 100, 100, 20);

        Ingresar = new JButton("Ingresar");
        Ingresar.setBounds(30, 150, 100, 20);
        Ingresar.addActionListener(this);

        Registrar = new JButton("Registrarse");
        Registrar.setBounds(140, 150, 105, 20);
        Registrar.addActionListener(this);

        add(LLogin);
        add(Registrar);
        add(Lusuario);
        add(Lpassword);
        add(Tusuario);
        add(Tpassword);
        add(Ingresar);
        addKeyListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Ingresar) {
            String Usuario = "", ID="";
            int IDUsuario=0;
            conn = ConexionOracle.ConnectDB();
            try {
                String sql = "SELECT * FROM CLIENTE WHERE documento_cliente=? AND contraseña_cliente=?";

                pst = (OraclePreparedStatement) conn.prepareStatement(sql);
                pst.setString(1, Tusuario.getText());
                pst.setString(2, Tpassword.getText());
                rs = (OracleResultSet) pst.executeQuery();

                if (rs.next()) {
                    Usuario = rs.getString("nombre_cliente");
                    ID= rs.getString("codigo_cliente");
                    IDUsuario = Integer.parseInt(ID);
                    JOptionPane.showMessageDialog(null, "Hola, bienvenido a UCCine " + Usuario, "Entrada", JOptionPane.WARNING_MESSAGE);
                    setVisible(false);
                    INICIO c = new INICIO(Usuario, IDUsuario);

                    c.setSize(700, 700);
                    c.setResizable(false);
                    c.setVisible(true);
                    c.setLocationRelativeTo(null);

                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña errada, por favor revice.", "¡ERROR!", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }

        if (e.getSource() == Registrar) {
            RegistrarNuevo r = new RegistrarNuevo();
            r.setSize(350, 400);
            r.setResizable(false);
            r.setVisible(true);
            r.setLocationRelativeTo(null);

        }
    }

    public static void main(String args[]) {
        JFrame aplicacion = new ControlAcceso();
        aplicacion.setSize(300, 300);
        aplicacion.setResizable(false);
        aplicacion.setLocationRelativeTo(null);
        aplicacion.setVisible(true);
        aplicacion.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        char c = ke.getKeyChar();

        if (ke.getSource() == Tusuario) {
            if (Character.isLetter(c)) {
                ke.consume();
                JOptionPane.showMessageDialog(null, "El Usuario solo admite números, no letras.", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
            } else if (((int) ke.getKeyChar()) >= 33 && ((int) ke.getKeyChar()) <= 47 || ((int) ke.getKeyChar()) >= 58) { // && ((int) ke.getKeyChar()) <= 58 || ((int) ke.getKeyChar()) >= 123 && ((int) ke.getKeyChar()) <= 163 || ((int) ke.getKeyChar()) >= 166 && ((int) ke.getKeyChar()) <= 255) {
                ke.consume();
                JOptionPane.showMessageDialog(null, "No se permite el uso de caracteres especiales.", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
            } else if (((int) ke.getKeyChar()) == 32) {
                ke.consume();
                JOptionPane.showMessageDialog(null, "Este campo no admite Espacios.", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (Tusuario.getText().length() == LimiteNombre) {
            ke.consume();
        }
        if (Tpassword.getText().length() == LimiteContra) {
            ke.consume();
        }
    }

    @Override

    public void keyReleased(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {

        int key = ke.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {
            Ingresar.doClick();

        }
    }
}
