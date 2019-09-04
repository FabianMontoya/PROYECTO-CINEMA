package paquete;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.sql.Connection;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

public class ReservarPeli extends JFrame implements ActionListener {

    JLabel LMensaje, LMensaje2, LMensaje3, LMensaje4, LUsuario, LPelicula, LIdioma, LDuracion, LClasificacion, LFecha, LHorario, LSillas;
    JTextField TUsuario, TPelicula, TIdioma, TClasificacion, TDuracion;
    JButton Cambiar, Continuar, Reiniciar;
    JComboBox HorarioPel, FechaPel, SillasPel;

    String LPeli = "", Usuario = "", Pelicula = "", IDPelicula = "", Nombre = "", Idioma = "--Idioma--", Duracion = "--Duración--", Clasificacion = "--Clasificación--", Fecha = "", Hora = "";
    int sillas = 0, IDUsuario=0;

    String FechaTransilvania[] = {"Fecha...", "20/10/2015", "21/10/2015", "22/10/2015", "23/10/2015", "24/10/2015", "25/10/2015"};
    String FechaRio2[] = {"Fecha...", "20/10/2015", "21/10/2015", "22/10/2015", "23/10/2015", "24/10/2015", "25/10/2015"};
    String FechaMinions[] = {"Fecha...", "20/10/2015", "21/10/2015", "22/10/2015", "23/10/2015", "24/10/2015", "25/10/2015"};
    String FechaDiarioNoa[] = {"Fecha...", "20/10/2015", "21/10/2015", "22/10/2015", "23/10/2015", "24/10/2015", "25/10/2015"};
    String FechaConjuro[] = {"Fecha...", "20/10/2015", "21/10/2015", "22/10/2015", "23/10/2015", "24/10/2015", "25/10/2015"};
    String FechaCasaDemonio[] = {"Fecha...", "20/10/2015", "21/10/2015", "22/10/2015", "23/10/2015"};
    String FechaStepUp3[] = {"Fecha...", "20/10/2015", "21/10/2015", "22/10/2015", "23/10/2015", "24/10/2015", "25/10/2015"};
    String FechaJurassic[] = {"Fecha...", "20/10/2015", "21/10/2015", "22/10/2015", "23/10/2015", "24/10/2015", "25/10/2015"};

    String Horario[] = {"Horario..."};
    String HorarioTransilvania[] = {"Horario...", "12:15 pm", "4:20 pm", "8:30 pm"};
    String HorarioRio2[] = {"Horario...", "2:20 pm", "6:30 pm"};
    String HorarioMinions[] = {"Horario...", "12:00 pm", "4:00 pm", "8:00 pm"};
    String HorarioDiarioNoa[] = {"Horario...", "2:10 pm", "6:10 pm", "10:10 pm"};
    String HorarioConjuro[] = {"Horario...", "4:30 pm", "8:10 pm", "10:20 pm"};
    String HorarioCasaDemonio[] = {"Horario...", "6:30 pm", "10:20 pm"};
    String HorarioStepUp3[] = {"Horario...", "12:15 pm", "4:00 pm", "8:00 pm"};
    String HorarioJurassic[] = {"Horario...", "4:20 pm", "6:10 pm", "10:20 pm"};

    String SeleccionSillas[] = {"Sillas...", "1", "2", "3", "4", "5"};

    Connection conexion = null;
    OraclePreparedStatement pst = null;
    OracleResultSet rs = null;

    public ReservarPeli(String movie, String ID, String user, int IDUser) {

        super("Reservar Película");
        Pelicula = movie;
        IDPelicula = ID;
        Usuario = user;
        IDUsuario=IDUser;
        setLayout(null);
        TraerDatos(IDPelicula);
        CajasTexto();
        Etiquetas();
        Botones();

        addWindowListener(new WindowAdapter() {
            @Override

            public void windowClosing(WindowEvent e) {
                control();
            }
        });
    }

    public void control() {
        int ax = JOptionPane.showConfirmDialog(null, "Al cerrar la ventana cerrará la sesión.\n¿Desea continuar?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (ax == JOptionPane.YES_OPTION) {
            ControlAcceso r = new ControlAcceso();
            r.setSize(300, 300);
            r.setResizable(false);
            r.setVisible(true);
            r.setDefaultCloseOperation(EXIT_ON_CLOSE);
            r.setLocationRelativeTo(null);
        }
        if (ax == JOptionPane.NO_OPTION) {
            ReservarPeli c = new ReservarPeli(Pelicula, IDPelicula, Usuario, IDUsuario);
            c.setSize(400, 500);
            c.setResizable(false);
            c.setVisible(true);
            c.setLocationRelativeTo(null);
        }
    }

    public void Etiquetas() {
        LMensaje = new JLabel("RESERVAR PELÍCULA");
        LMensaje.setBounds(100, 20, 300, 20);
        add(LMensaje);
        LMensaje2 = new JLabel("Complete los datos solicitados para completar la reserva.");
        LMensaje2.setBounds(20, 50, 350, 20);
        add(LMensaje2);

        LMensaje3 = new JLabel("OPCIONES DE RESERVA");
        LMensaje3.setBounds(10, 80, 350, 20);
        add(LMensaje3);

        LFecha = new JLabel("- Seleccione la fecha:");
        LFecha.setBounds(20, 110, 300, 20);
        add(LFecha);

        LHorario = new JLabel("- Seleccione el horario:");
        LHorario.setBounds(20, 140, 300, 20);
        add(LHorario);

        LSillas = new JLabel("- Seleccione el número de sillas:");
        LSillas.setBounds(20, 170, 300, 20);
        add(LSillas);

        LMensaje4 = new JLabel("RESUMÉN DE TU RESERVA");
        LMensaje4.setBounds(10, 210, 300, 20);
        add(LMensaje4);

        LUsuario = new JLabel("- Cliente:");
        LUsuario.setBounds(20, 240, 300, 20);
        add(LUsuario);

        LPelicula = new JLabel("- Película:");
        LPelicula.setBounds(20, 270, 300, 20);
        add(LPelicula);

        LIdioma = new JLabel("- Idioma:");
        LIdioma.setBounds(20, 300, 300, 20);
        add(LIdioma);

        LClasificacion = new JLabel("- Clasificación:");
        LClasificacion.setBounds(20, 330, 300, 20);
        add(LClasificacion);

        LDuracion = new JLabel("- Duración:");
        LDuracion.setBounds(20, 360, 300, 20);
        add(LDuracion);
    }

    public void CajasTexto() {

        if (Pelicula.equals("Transilvania")) {
            FechaPel = new JComboBox(FechaTransilvania);
            LPeli = "Hotel Transilvania 2";
        }
        if (Pelicula.equals("Rio2")) {
            FechaPel = new JComboBox(FechaRio2);
            LPeli = "Rio 2";
        }
        if (Pelicula.equals("Minions")) {
            FechaPel = new JComboBox(FechaMinions);
            LPeli = "Los Minions";
        }
        if (Pelicula.equals("DiarioNoa")) {
            FechaPel = new JComboBox(FechaDiarioNoa);
            LPeli = "El Diario de Noa";
        }
        if (Pelicula.equals("Conjuro")) {
            FechaPel = new JComboBox(FechaConjuro);
            LPeli = "El Conjuro";
        }
        if (Pelicula.equals("CasaDemonio")) {
            FechaPel = new JComboBox(FechaCasaDemonio);
            LPeli = "La casa del Demonio";
        }
        if (Pelicula.equals("StepUp3")) {
            FechaPel = new JComboBox(FechaStepUp3);
            LPeli = "Step Up 3";
        }
        if (Pelicula.equals("Jurassic")) {
            FechaPel = new JComboBox(FechaJurassic);
            LPeli = "Jurassic World";
        }

        FechaPel.setBounds(208, 110, 120, 20);
        FechaPel.addActionListener(this);
        add(FechaPel);

        HorarioPel = new JComboBox(Horario);
        HorarioPel.setBounds(208, 140, 120, 20);
        HorarioPel.setEnabled(false);
        HorarioPel.addActionListener(this);
        add(HorarioPel);

        SillasPel = new JComboBox(SeleccionSillas);
        SillasPel.setBounds(208, 170, 120, 20);
        SillasPel.setEnabled(false);
        add(SillasPel);

        //
        TUsuario = new JTextField("" + Usuario);
        TUsuario.setBounds(110, 240, 125, 20);
        TUsuario.setEditable(false);
        add(TUsuario);

        TPelicula = new JTextField("" + LPeli);
        TPelicula.setBounds(110, 270, 125, 20);
        TPelicula.setEditable(false);
        add(TPelicula);

        TIdioma = new JTextField("" + Idioma);
        TIdioma.setBounds(110, 300, 125, 20);
        TIdioma.setEditable(false);
        add(TIdioma);

        TClasificacion = new JTextField("" + Clasificacion);
        TClasificacion.setBounds(110, 330, 125, 20);
        TClasificacion.setEditable(false);
        add(TClasificacion);

        TDuracion = new JTextField("" + Duracion);
        TDuracion.setBounds(110, 360, 125, 20);
        TDuracion.setEditable(false);
        add(TDuracion);
    }

    public void Botones() {

        Cambiar = new JButton("Cambiar Pelicula");
        Cambiar.setBounds(245, 270, 130, 20);
        Cambiar.addActionListener(this);

        Continuar = new JButton("Continuar");
        Continuar.setBounds(30, 400, 90, 20);
        Continuar.addActionListener(this);

        Reiniciar = new JButton("Reiniciar");
        Reiniciar.setBounds(140, 400, 90, 20);
        Reiniciar.addActionListener(this);

        add(Cambiar);
        add(Continuar);
        add(Reiniciar);
    }

    public void TraerDatos(String idPelicula) {
        int codigo = Integer.parseInt(idPelicula);
        conexion = ConexionOracle.ConnectDB();
        try {
            String sql = "SELECT idioma_pelicula, duracion_pelicula, clasificacion_pelicula FROM PELICULAS WHERE codigo_pelicula=" + codigo;
            pst = (OraclePreparedStatement) conexion.prepareStatement(sql);
            rs = (OracleResultSet) pst.executeQuery();

            while (rs.next() != false) {
                Idioma = rs.getString("idioma_pelicula");
                Duracion = rs.getString("duracion_pelicula");
                Clasificacion = rs.getString("clasificacion_pelicula");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == Cambiar) {
            int ax = JOptionPane.showConfirmDialog(null, "¿Desea cambiar la película a reservar?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (ax == JOptionPane.YES_OPTION) {
                setVisible(false);
                INICIO r = new INICIO(Usuario, IDUsuario);
                r.setSize(700, 700);
                r.setResizable(false);
                r.setVisible(true);
                r.setLocationRelativeTo(null);
            }
        }
        /////////////////////////////
        if (ae.getSource() == Continuar) {
            if (FechaPel.getSelectedIndex() == 0 || HorarioPel.getSelectedIndex() == 0 || SillasPel.getSelectedIndex() == 0) {
                if (FechaPel.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "La opción elegida en <<Fecha>> no es valida.\nRectifique para continuar.", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
                    FechaPel.requestFocus();
                }
                if (HorarioPel.getSelectedIndex() == 0 && FechaPel.getSelectedIndex() != 0) {
                    JOptionPane.showMessageDialog(null, "La opción elegida en <<Horario>> no es valida.\nRectifique para continuar.", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
                    HorarioPel.requestFocus();
                }
                if (SillasPel.getSelectedIndex() == 0 && HorarioPel.getSelectedIndex() != 0 && FechaPel.getSelectedIndex() != 0) {
                    JOptionPane.showMessageDialog(null, "La opción elegida en <<Número de Sillas>> no es valida.\nRectifique para continuar.", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
                    SillasPel.requestFocus();
                }

            } else {
                sillas = Integer.parseInt((String) SillasPel.getSelectedItem());

                if (Pelicula.equals("Transilvania")) {
                    EnviarTransilvania();
                }
                if (Pelicula.equals("Rio2")) {
                    EnviarRio2();
                }
                if (Pelicula.equals("Minions")) {
                    EnviarMinions();
                }
                if (Pelicula.equals("DiarioNoa")) {
                    EnviarNoa();
                }
                if (Pelicula.equals("Conjuro")) {
                    EnviarConjuro();
                }
                if (Pelicula.equals("CasaDemonio")) {
                    EnviarCasaDemonio();
                }
                if (Pelicula.equals("StepUp3")) {
                    EnviarStepUp3();
                }
                if (Pelicula.equals("Jurassic")) {
                    EnviarJurassic();
                }
            }

        }
        ////////////////////////
        if (ae.getSource() == Reiniciar) {
            if (Pelicula.equals("Transilvania")) {
                FechaPel.setSelectedItem(FechaTransilvania[0]);
            }
            if (Pelicula.equals("Rio2")) {
                FechaPel.setSelectedItem(FechaRio2[0]);
            }
            if (Pelicula.equals("Minions")) {
                FechaPel.setSelectedItem(FechaMinions[0]);
            }
            if (Pelicula.equals("DiarioNoa")) {
                FechaPel.setSelectedItem(FechaDiarioNoa[0]);
            }
            if (Pelicula.equals("Conjuro")) {
                FechaPel.setSelectedItem(FechaConjuro[0]);
            }
            if (Pelicula.equals("CasaDemonio")) {
                FechaPel.setSelectedItem(FechaCasaDemonio[0]);
            }
            if (Pelicula.equals("StepUp3")) {
                FechaPel.setSelectedItem(FechaStepUp3[0]);
            }
            if (Pelicula.equals("Jurassic")) {
                FechaPel.setSelectedItem(FechaJurassic[0]);
            }

        }
        //////////////////////////////////
        if (ae.getSource() == FechaPel) {
            if (FechaPel.getSelectedIndex() == 0) {
                HorarioPel.removeAllItems();
                for (int i = 0; i < Horario.length; i++) {
                    HorarioPel.addItem(Horario[i]);
                }
                HorarioPel.setEnabled(false);
            }

            if (FechaPel.getSelectedIndex() >= 1) {
                HorarioPel.removeAllItems();
                if (Pelicula.equals("Transilvania")) {
                    for (int i = 0; i < HorarioTransilvania.length; i++) {
                        HorarioPel.addItem(HorarioTransilvania[i]);
                    }

                }
                if (Pelicula.equals("Rio2")) {
                    HorarioPel.removeAllItems();
                    for (int i = 0; i < HorarioRio2.length; i++) {
                        HorarioPel.addItem(HorarioRio2[i]);
                    }
                }
                if (Pelicula.equals("Minions")) {
                    HorarioPel.removeAllItems();
                    for (int i = 0; i < HorarioMinions.length; i++) {
                        HorarioPel.addItem(HorarioMinions[i]);
                    }
                }
                if (Pelicula.equals("DiarioNoa")) {
                    HorarioPel.removeAllItems();
                    for (int i = 0; i < HorarioDiarioNoa.length; i++) {
                        HorarioPel.addItem(HorarioDiarioNoa[i]);
                    }
                }
                if (Pelicula.equals("Conjuro")) {
                    HorarioPel.removeAllItems();
                    for (int i = 0; i < HorarioConjuro.length; i++) {
                        HorarioPel.addItem(HorarioConjuro[i]);
                    }
                }
                if (Pelicula.equals("CasaDemonio")) {
                    HorarioPel.removeAllItems();
                    for (int i = 0; i < HorarioCasaDemonio.length; i++) {
                        HorarioPel.addItem(HorarioCasaDemonio[i]);
                    }
                }
                if (Pelicula.equals("StepUp3")) {
                    HorarioPel.removeAllItems();
                    for (int i = 0; i < HorarioStepUp3.length; i++) {
                        HorarioPel.addItem(HorarioStepUp3[i]);
                    }
                }
                if (Pelicula.equals("Jurassic")) {
                    HorarioPel.removeAllItems();
                    for (int i = 0; i < HorarioJurassic.length; i++) {
                        HorarioPel.addItem(HorarioJurassic[i]);
                    }
                }

                HorarioPel.setEnabled(true);
            }
        }
        ///////////////////////
        if (ae.getSource() == HorarioPel) {
            if (HorarioPel.getSelectedIndex() == 0) {
                SillasPel.setSelectedItem(SeleccionSillas[0]);
                SillasPel.setEnabled(false);
            }
            if (HorarioPel.getSelectedIndex() >= 1) {
                SillasPel.setEnabled(true);
            }
        }
    }

    public void EnviarTransilvania() {
        setVisible(false);
        Fecha = (String) FechaPel.getSelectedItem();
        Hora = (String) HorarioPel.getSelectedItem();
        CineSala2 a = new CineSala2(Usuario, IDUsuario, Pelicula, IDPelicula, sillas, Fecha, Hora);
        a.setSize(460, 500);
        a.setLocationRelativeTo(null);
        a.setVisible(true);
        a.setResizable(false);
    }

    public void EnviarRio2() {
        setVisible(false);
        Fecha = (String) FechaPel.getSelectedItem();
        Hora = (String) HorarioPel.getSelectedItem();
        CineSala2 a = new CineSala2(Usuario, IDUsuario, Pelicula, IDPelicula, sillas, Fecha, Hora);
        a.setSize(460, 500);
        a.setLocationRelativeTo(null);
        a.setVisible(true);
        a.setResizable(false);
    }

    public void EnviarMinions() {
        setVisible(false);
        Fecha = (String) FechaPel.getSelectedItem();
        Hora = (String) HorarioPel.getSelectedItem();
        CineSala1 a = new CineSala1(Usuario, IDUsuario, Pelicula, IDPelicula, sillas, Fecha, Hora);
        a.setSize(460, 500);
        a.setLocationRelativeTo(null);
        a.setVisible(true);
        a.setResizable(false);
    }

    public void EnviarNoa() {
        setVisible(false);
        Fecha = (String) FechaPel.getSelectedItem();
        Hora = (String) HorarioPel.getSelectedItem();
        CineSala1 a = new CineSala1(Usuario, IDUsuario, Pelicula, IDPelicula, sillas, Fecha, Hora);
        a.setSize(460, 500);
        a.setLocationRelativeTo(null);
        a.setVisible(true);
        a.setResizable(false);
    }

    public void EnviarConjuro() {
        setVisible(false);
        Fecha = (String) FechaPel.getSelectedItem();
        Hora = (String) HorarioPel.getSelectedItem();
        CineSala4 a = new CineSala4(Usuario, IDUsuario, Pelicula, IDPelicula, sillas, Fecha, Hora);
        a.setSize(460, 500);
        a.setLocationRelativeTo(null);
        a.setVisible(true);
        a.setResizable(false);
    }

    public void EnviarCasaDemonio() {
        setVisible(false);
        Fecha = (String) FechaPel.getSelectedItem();
        Hora = (String) HorarioPel.getSelectedItem();
        CineSala4 a = new CineSala4(Usuario, IDUsuario, Pelicula, IDPelicula, sillas, Fecha, Hora);
        a.setSize(460, 500);
        a.setLocationRelativeTo(null);
        a.setVisible(true);
        a.setResizable(false);
    }

    public void EnviarStepUp3() {
        setVisible(false);
        Fecha = (String) FechaPel.getSelectedItem();
        Hora = (String) HorarioPel.getSelectedItem();
        CineSala3 a = new CineSala3(Usuario, IDUsuario, Pelicula, IDPelicula, sillas, Fecha, Hora);
        a.setSize(460, 500);
        a.setLocationRelativeTo(null);
        a.setVisible(true);
        a.setResizable(false);
    }

    public void EnviarJurassic() {
        setVisible(false);
        Fecha = (String) FechaPel.getSelectedItem();
        Hora = (String) HorarioPel.getSelectedItem();
        CineSala3 a = new CineSala3(Usuario, IDUsuario, Pelicula, IDPelicula, sillas, Fecha, Hora);
        a.setSize(460, 500);
        a.setLocationRelativeTo(null);
        a.setVisible(true);
        a.setResizable(false);
    }

    public static void main(String args[]) {
        /*JFrame aplicacion = new ReservarPeli();
         aplicacion.setSize(400, 500);
         aplicacion.setVisible(true);*/
    }
}
