package paquete;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.sql.Connection;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

public class CineSala3 extends JFrame implements ActionListener {

    public JPanel PanelInfo, PanelViñetas;
    public JButton A1, A2, A3, A4, A5, A6, A7, A8, A9, B1, B2, B3, B4, B5, B6, B7, B8, B9, C1, C2, C3, C4, C5, C6, C7, C8, C9, D1, D2, D3, D4, D5, D6, D7, D8, D9, Viñe1, Viñe2, Viñe3;
    public JButton Reservar, Cancelar;
    public JLabel L1, L2, L3, L4, L5, L6, L7, L8, L9, Pantalla, LA, LB, LC, LD, LTituloSala, LSillas, LSTotal, LPelicula, LFecha, LHora, Label1, Label2, Label3;
    int SillasElegir = 0, ContadorSelect = 0, TotalSillas = 0;
    /*A*/
    int ContadorA1 = 0, ContadorA2 = 0, ContadorA3 = 0, ContadorA4 = 0, ContadorA5 = 0, ContadorA6 = 0, ContadorA7 = 0, ContadorA8 = 0, ContadorA9 = 0;
    /*B*/
    int ContadorB1 = 0, ContadorB2 = 0, ContadorB3 = 0, ContadorB4 = 0, ContadorB5 = 0, ContadorB6 = 0, ContadorB7 = 0, ContadorB8 = 0, ContadorB9 = 0;
    /*C*/
    int ContadorC1 = 0, ContadorC2 = 0, ContadorC3 = 0, ContadorC4 = 0, ContadorC5 = 0, ContadorC6 = 0, ContadorC7 = 0, ContadorC8 = 0, ContadorC9 = 0;
    /*D*/
    int ContadorD1 = 0, ContadorD2 = 0, ContadorD3 = 0, ContadorD4 = 0, ContadorD5 = 0, ContadorD6 = 0, ContadorD7 = 0, ContadorD8 = 0, ContadorD9 = 0;
    int IDUsuario = 0, reserva = 0;
    String Fecha = "", Hora = "", Pelicula = "", IDPelicula = "", Usuario = "", Idioma = "", Duracion = "", Clasificacion = "", Sala = "3";
    String SillasSeleccionadas = "", SillasEnviar = "";
    String SillasTraidas="";

    Connection conexion = null;
    OraclePreparedStatement pst = null;
    OracleResultSet rs = null;

     public CineSala3(String User, int IDUser, String peli, String ID, int SillasReserva, String fecha, String horario) {
        super("UCCine - Asignación de Asientos (3)");
        Usuario = User;
        IDUsuario = IDUser;
        Pelicula = peli;
        IDPelicula = ID;
        TotalSillas = SillasReserva;
        SillasElegir = SillasReserva;
        Fecha = fecha;
        Hora = horario;
        setLayout(null);
        Sillas();
        TraerDatosPeli(IDPelicula);
        panel();
        Labels();
        Botones();
        DatosSala();

        addWindowListener(new WindowAdapter() {
            @Override

            public void windowClosing(WindowEvent e) {
                control();
            }
        });
    }

    public void DatosSala() {
        int IDsala = 0;
        IDsala = Integer.parseInt(Sala);
        conexion = ConexionOracle.ConnectDB();
        
        try {
            String sql = "SELECT sillasreservadas_sala FROM SALAS WHERE numero_sala=? and fecha_sala=? and hora_sala=?";
            pst = (OraclePreparedStatement) conexion.prepareStatement(sql);
            pst.setInt(1, IDsala);
            pst.setString(2, Fecha);
            pst.setString(3, Hora);
            rs = (OracleResultSet) pst.executeQuery();

            while (rs.next() != false) {
                SillasTraidas = rs.getString("sillasreservadas_sala");

            }
            BloquearSillas();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en Metodo DatosSala():\n\n" + e);
        }
    }

    public void BloquearSillas() {
        String SillasX = SillasTraidas;
        String ArraySillas[] = SillasX.split(",");

        for (int i = 0; i < ArraySillas.length; i++) {
            
            if (ArraySillas[i].equals("A1")) {
                A1.setBackground(Color.RED);
                A1.setEnabled(false);
            }
            if (ArraySillas[i].equals("A2")) {
                A2.setBackground(Color.RED);
                A2.setEnabled(false);
            }
            if (ArraySillas[i].equals("A3")) {
                A3.setBackground(Color.RED);
                A3.setEnabled(false);
            }
            if (ArraySillas[i].equals("A4")) {
                A4.setBackground(Color.RED);
                A4.setEnabled(false);
            }
            if (ArraySillas[i].equals("A5")) {
                A5.setBackground(Color.RED);
                A5.setEnabled(false);
            }
            if (ArraySillas[i].equals("A6")) {
                A6.setBackground(Color.RED);
                A6.setEnabled(false);
            }if (ArraySillas[i].equals("A7")) {
                A7.setBackground(Color.RED);
                A7.setEnabled(false);
            }
            if (ArraySillas[i].equals("A8")) {
                A8.setBackground(Color.RED);
                A8.setEnabled(false);
            }
            if (ArraySillas[i].equals("A9")) {
                A9.setBackground(Color.RED);
                A9.setEnabled(false);
            }
            //==============FILA B======================
            if (ArraySillas[i].equals("B1")) {
                B1.setBackground(Color.RED);
                B1.setEnabled(false);
            }
            if (ArraySillas[i].equals("B2")) {
                B2.setBackground(Color.RED);
                B2.setEnabled(false);
            }
            if (ArraySillas[i].equals("B3")) {
                B3.setBackground(Color.RED);
                B3.setEnabled(false);
            }
            if (ArraySillas[i].equals("B4")) {
                B4.setBackground(Color.RED);
                B4.setEnabled(false);
            }
            if (ArraySillas[i].equals("B5")) {
                B5.setBackground(Color.RED);
                B5.setEnabled(false);
            }
            if (ArraySillas[i].equals("B6")) {
                B6.setBackground(Color.RED);
                B6.setEnabled(false);
            }if (ArraySillas[i].equals("B7")) {
                B7.setBackground(Color.RED);
                B7.setEnabled(false);
            }
            if (ArraySillas[i].equals("B8")) {
                B8.setBackground(Color.RED);
                B8.setEnabled(false);
            }
            if (ArraySillas[i].equals("B9")) {
                B9.setBackground(Color.RED);
                B9.setEnabled(false);
            }
            //=============FILA C=================
            if (ArraySillas[i].equals("C1")) {
                C1.setBackground(Color.RED);
                C1.setEnabled(false);
            }
            if (ArraySillas[i].equals("C2")) {
                C2.setBackground(Color.RED);
                C2.setEnabled(false);
            }
            if (ArraySillas[i].equals("C3")) {
                C3.setBackground(Color.RED);
                C3.setEnabled(false);
            }
            if (ArraySillas[i].equals("C4")) {
                C4.setBackground(Color.RED);
                C4.setEnabled(false);
            }
            if (ArraySillas[i].equals("C5")) {
                C5.setBackground(Color.RED);
                C5.setEnabled(false);
            }
            if (ArraySillas[i].equals("C6")) {
                C6.setBackground(Color.RED);
                C6.setEnabled(false);
            }if (ArraySillas[i].equals("C7")) {
                C7.setBackground(Color.RED);
                C7.setEnabled(false);
            }
            if (ArraySillas[i].equals("C8")) {
                C8.setBackground(Color.RED);
                C8.setEnabled(false);
            }
            if (ArraySillas[i].equals("C9")) {
                C9.setBackground(Color.RED);
                C9.setEnabled(false);
            }
            //===============FILA D==============
            if (ArraySillas[i].equals("D1")) {
                D1.setBackground(Color.RED);
                D1.setEnabled(false);
            }
            if (ArraySillas[i].equals("D2")) {
                D2.setBackground(Color.RED);
                D2.setEnabled(false);
            }
            if (ArraySillas[i].equals("D3")) {
                D3.setBackground(Color.RED);
                D3.setEnabled(false);
            }
            if (ArraySillas[i].equals("D4")) {
                D4.setBackground(Color.RED);
                D4.setEnabled(false);
            }
            if (ArraySillas[i].equals("D5")) {
                D5.setBackground(Color.RED);
                D5.setEnabled(false);
            }
            if (ArraySillas[i].equals("D6")) {
                D6.setBackground(Color.RED);
                D6.setEnabled(false);
            }if (ArraySillas[i].equals("D7")) {
                D7.setBackground(Color.RED);
                D7.setEnabled(false);
            }
            if (ArraySillas[i].equals("D8")) {
                D8.setBackground(Color.RED);
                D8.setEnabled(false);
            }
            if (ArraySillas[i].equals("D9")) {
                D9.setBackground(Color.RED);
                D9.setEnabled(false);
            }
            
        }
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
            CineSala1 a = new CineSala1(Usuario, IDUsuario, Pelicula, IDPelicula, TotalSillas, Fecha, Hora);
            a.setSize(460, 500);
            a.setLocationRelativeTo(null);
            a.setVisible(true);
            a.setResizable(false);
        }
    }

    public void panel() {
        PanelInfo = new JPanel();
        PanelInfo.setLayout(null);
        PanelInfo.setBorder(javax.swing.BorderFactory.createTitledBorder("Información Reserva"));
        add(PanelInfo);
        PanelInfo.setBounds(53, 310, 200, 110);
        PanelInfo.updateUI(); //refrescar panel

        PanelViñetas = new JPanel();
        PanelViñetas.setLayout(null);
        PanelViñetas.setBorder(javax.swing.BorderFactory.createTitledBorder("Leyenda"));
        add(PanelViñetas);
        PanelViñetas.setBounds(270, 310, 130, 110);
        PanelViñetas.updateUI();
        viñetas();
    }

    public void viñetas() {
        Viñe1 = new JButton("");
        Viñe2 = new JButton("");
        Viñe3 = new JButton("");

        Viñe1.setBounds(15, 25, 20, 20);
        Viñe1.setBackground(null);
        PanelViñetas.add(Viñe1);

        Viñe2.setBounds(15, 50, 20, 20);
        Viñe2.setBackground(Color.YELLOW);
        Viñe2.setEnabled(false);
        PanelViñetas.add(Viñe2);

        Viñe3.setBounds(15, 75, 20, 20);
        Viñe3.setBackground(Color.RED);
        Viñe3.setEnabled(false);
        PanelViñetas.add(Viñe3);

        Label1 = new JLabel("Disponible");
        Label1.setBounds(40, 25, 100, 20);
        PanelViñetas.add(Label1);

        Label2 = new JLabel("Seleccionado");
        Label2.setBounds(40, 50, 100, 20);
        PanelViñetas.add(Label2);

        Label3 = new JLabel("Reservado");
        Label3.setBounds(40, 75, 100, 20);
        PanelViñetas.add(Label3);

    }

    public void Sillas() {

        Pantalla = new JLabel(new ImageIcon(getClass().getResource("pantalla.png")));
        Pantalla.setBounds(30, 260, 400, 40);
        add(Pantalla);

        A1 = new JButton("");
        A1.setBounds(70, 210, 25, 25);
        A1.addActionListener(this);
        add(A1);

        A2 = new JButton("");
        A2.setBounds(100, 210, 25, 25);
        A2.addActionListener(this);
        add(A2);

        A3 = new JButton("");
        A3.setBounds(150, 210, 25, 25);
        A3.addActionListener(this);
        add(A3);

        A4 = new JButton("");
        A4.setBounds(180, 210, 25, 25);
        A4.addActionListener(this);
        add(A4);

        A5 = new JButton("");
        A5.setBounds(210, 210, 25, 25);
        A5.addActionListener(this);
        add(A5);

        A6 = new JButton("");
        A6.setBounds(240, 210, 25, 25);
        A6.addActionListener(this);
        add(A6);

        A7 = new JButton("");
        A7.setBounds(270, 210, 25, 25);
        A7.addActionListener(this);
        add(A7);

        A8 = new JButton("");
        A8.setBounds(320, 210, 25, 25);
        A8.addActionListener(this);
        add(A8);

        A9 = new JButton("");
        A9.setBounds(350, 210, 25, 25);
        A9.addActionListener(this);
        add(A9);
        ///////////////////////////////////
        B1 = new JButton("");
        B1.setBounds(70, 175, 25, 25);
        B1.addActionListener(this);
        add(B1);

        B2 = new JButton("");
        B2.setBounds(100, 175, 25, 25);
        B2.addActionListener(this);
        add(B2);

        B3 = new JButton("");
        B3.setBounds(150, 175, 25, 25);
        B3.addActionListener(this);
        add(B3);

        B4 = new JButton("");
        B4.setBounds(180, 175, 25, 25);
        B4.addActionListener(this);
        add(B4);

        B5 = new JButton("");
        B5.setBounds(210, 175, 25, 25);
        B5.addActionListener(this);
        add(B5);

        B6 = new JButton("");
        B6.setBounds(240, 175, 25, 25);
        B6.addActionListener(this);
        add(B6);

        B7 = new JButton("");
        B7.setBounds(270, 175, 25, 25);
        B7.addActionListener(this);
        add(B7);

        B8 = new JButton("");
        B8.setBounds(320, 175, 25, 25);
        B8.addActionListener(this);
        add(B8);

        B9 = new JButton("");
        B9.setBounds(350, 175, 25, 25);
        B9.addActionListener(this);
        add(B9);
        //////////////////////////////
        C1 = new JButton("");
        C1.setBounds(70, 140, 25, 25);
        C1.addActionListener(this);
        add(C1);

        C2 = new JButton("");
        C2.setBounds(100, 140, 25, 25);
        C2.addActionListener(this);
        add(C2);

        C3 = new JButton("");
        C3.setBounds(150, 140, 25, 25);
        C3.addActionListener(this);
        add(C3);

        C4 = new JButton("");
        C4.setBounds(180, 140, 25, 25);
        C4.addActionListener(this);
        add(C4);

        C5 = new JButton("");
        C5.setBounds(210, 140, 25, 25);
        C5.addActionListener(this);
        add(C5);

        C6 = new JButton("");
        C6.setBounds(240, 140, 25, 25);
        C6.addActionListener(this);
        add(C6);

        C7 = new JButton("");
        C7.setBounds(270, 140, 25, 25);
        C7.addActionListener(this);
        add(C7);

        C8 = new JButton("");
        C8.setBounds(320, 140, 25, 25);
        C8.addActionListener(this);
        add(C8);

        C9 = new JButton("");
        C9.setBounds(350, 140, 25, 25);
        C9.addActionListener(this);
        add(C9);
        //////////////////////////////////
        D1 = new JButton("");
        D1.setBounds(70, 105, 25, 25);
        D1.addActionListener(this);
        add(D1);

        D2 = new JButton("");
        D2.setBounds(100, 105, 25, 25);
        D2.addActionListener(this);
        add(D2);

        D3 = new JButton("");
        D3.setBounds(150, 105, 25, 25);
        D3.addActionListener(this);
        add(D3);

        D4 = new JButton("");
        D4.setBounds(180, 105, 25, 25);
        D4.addActionListener(this);
        add(D4);

        D5 = new JButton("");
        D5.setBounds(210, 105, 25, 25);
        D5.addActionListener(this);
        add(D5);

        D6 = new JButton("");
        D6.setBounds(240, 105, 25, 25);
        D6.addActionListener(this);
        add(D6);

        D7 = new JButton("");
        D7.setBounds(270, 105, 25, 25);
        D7.addActionListener(this);
        add(D7);

        D8 = new JButton("");
        D8.setBounds(320, 105, 25, 25);
        D8.addActionListener(this);
        add(D8);

        D9 = new JButton("");
        D9.setBounds(350, 105, 25, 25);
        D9.addActionListener(this);
        add(D9);
    }

    public void Labels() {
        LTituloSala = new JLabel("Sala 3");
        LTituloSala.setBounds(190, 10, 200, 20);
        add(LTituloSala);

        LPelicula = new JLabel("~ Película: " + Pelicula);
        PanelInfo.add(LPelicula);
        LPelicula.setBounds(20, 20, 300, 20);

        LFecha = new JLabel("~ Fecha función: " + Fecha);
        LFecha.setBounds(20, 40, 300, 20);
        PanelInfo.add(LFecha);

        LHora = new JLabel("~ Hora función: " + Hora);
        LHora.setBounds(20, 60, 300, 20);
        PanelInfo.add(LHora);

        LSillas = new JLabel("~ Sillas por elegir:");
        LSillas.setBounds(20, 80, 300, 20);
        PanelInfo.add(LSillas);
        LSTotal = new JLabel("" + SillasElegir);
        LSTotal.setBounds(125, 80, 200, 20);
        PanelInfo.add(LSTotal);

        LA = new JLabel("A");
        LA.setBounds(55, 212, 20, 20);
        add(LA);
        LB = new JLabel("B");
        LB.setBounds(55, 177, 20, 20);
        add(LB);
        LC = new JLabel("C");
        LC.setBounds(55, 142, 20, 20);
        add(LC);
        LD = new JLabel("D");
        LD.setBounds(55, 107, 20, 20);
        add(LD);
        ///////////
        L1 = new JLabel("1");
        L1.setBounds(78, 234, 20, 20);
        add(L1);
        L2 = new JLabel("2");
        L2.setBounds(108, 234, 20, 20);
        add(L2);
        L3 = new JLabel("3");
        L3.setBounds(158, 234, 20, 20);
        add(L3);
        L4 = new JLabel("4");
        L4.setBounds(188, 234, 20, 20);
        add(L4);
        L5 = new JLabel("5");
        L5.setBounds(218, 234, 20, 20);
        add(L5);
        L6 = new JLabel("6");
        L6.setBounds(248, 234, 20, 20);
        add(L6);
        L7 = new JLabel("7");
        L7.setBounds(278, 234, 20, 20);
        add(L7);
        L8 = new JLabel("8");
        L8.setBounds(328, 234, 20, 20);
        add(L8);
        L9 = new JLabel("9");
        L9.setBounds(358, 234, 20, 20);
        add(L9);
    }

    public void Botones() {
        Reservar = new JButton("Reservar");
        Reservar.setBounds(100, 430, 90, 20);
        Reservar.setEnabled(false);
        Reservar.addActionListener(this);
        add(Reservar);

        Cancelar = new JButton("Cancelar");
        Cancelar.setBounds(240, 430, 90, 20);
        Cancelar.addActionListener(this);
        add(Cancelar);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == Cancelar) {
            int ax = JOptionPane.showConfirmDialog(null, "¿Desea cancelar la reserva?\nPerdera los datos ingresados.", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (ax == JOptionPane.YES_OPTION) {
                setVisible(false);
                INICIO r = new INICIO(Usuario, IDUsuario);
                r.setSize(700, 700);
                r.setResizable(false);
                r.setVisible(true);
                r.setLocationRelativeTo(null);
            }
        }

        if (ae.getSource() == Reservar) {
            SillasEnviar = SillasSeleccionadas.substring(1);
            int ax1 = JOptionPane.showConfirmDialog(null, "INFORME DE RESERVA\n\n- Usuario: " + Usuario + "\n- Película: " + Pelicula + "\n- Idioma: " + Idioma + "\n- Clasificación: " + Clasificacion + "\n- Duración: " + Duracion + "\n- Fecha función: " + Fecha + "\n- Hora función: " + Hora + "\n- Asientos reservados: " + SillasEnviar + "\n- Total asientos: " + TotalSillas + "\n\n¿Es correcto?, pulse SI para guardar su reserva.", "Confirmar Reserva", JOptionPane.YES_NO_OPTION);
            if (ax1 == JOptionPane.YES_OPTION) {
                //setVisible(false);
                reserva = EnviarReserva.EnvioReserva(IDUsuario, IDPelicula, Fecha, Hora, TotalSillas, SillasSeleccionadas, Sala); //Mando los datos para la reserva.

                if (reserva == 0) {
                    JOptionPane.showMessageDialog(null, "Reserva completa.");
                    setVisible(false);
                    INICIO r = new INICIO(Usuario, IDUsuario);
                    r.setSize(700, 700);
                    r.setResizable(false);
                    r.setVisible(true);
                    r.setLocationRelativeTo(null);
                }
            }

        }

        if (ae.getSource() == A1) {
            A1();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == A2) {
            A2();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == A3) {
            A3();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == A4) {
            A4();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == A5) {
            A5();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == A6) {
            A6();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == A7) {
            A7();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == A8) {
            A8();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == A9) {
            A9();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }

        ///============B=============///
        if (ae.getSource() == B1) {
            B1();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == B2) {
            B2();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == B3) {
            B3();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == B4) {
            B4();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == B5) {
            B5();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == B6) {
            B6();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == B7) {
            B7();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == B8) {
            B8();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == B9) {
            B9();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }

        ///============C=============///
        if (ae.getSource() == C1) {
            C1();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == C2) {
            C2();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == C3) {
            C3();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == C4) {
            C4();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == C5) {
            C5();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == C6) {
            C6();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == C7) {
            C7();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == C8) {
            C8();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == C9) {
            C9();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }

        ///============D=============///
        if (ae.getSource() == D1) {
            D1();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == D2) {
            D2();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == D3) {
            D3();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == D4) {
            D4();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == D5) {
            D5();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == D6) {
            D6();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == D7) {
            D7();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == D8) {
            D8();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }
        if (ae.getSource() == D9) {
            D9();
            if (SillasElegir == 0) {
                Reservar.setEnabled(true);
            } else if (SillasElegir != 0) {
                Reservar.setEnabled(false);
            }
        }

        ///=========================///
    }

    public static void main(String[] args) {

        /*JFrame cuadro = new CineSala1();
         cuadro.setSize(460, 500);
         cuadro.setLocationRelativeTo(null);
         cuadro.setVisible(true);
         cuadro.setResizable(false);
         cuadro.setDefaultCloseOperation(EXIT_ON_CLOSE);*/
    }

    ///======FILA A=======///
    public void A1() {
        ContadorA1 = ContadorA1 + 1;

        if (ContadorA1 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                A1.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "A1";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorA1 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorA1 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            A1.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",A1", "");
            ContadorA1 = 0;
        }
    }

    public void A2() {
        ContadorA2 = ContadorA2 + 1;

        if (ContadorA2 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                A2.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "A2";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorA2 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorA2 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            A2.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",A2", "");
            ContadorA2 = 0;
        }
    }

    public void A3() {
        ContadorA3 = ContadorA3 + 1;

        if (ContadorA3 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                A3.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "A3";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorA3 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorA3 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            A3.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",A3", "");
            ContadorA3 = 0;
        }
    }

    public void A4() {
        ContadorA4 = ContadorA4 + 1;

        if (ContadorA4 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                A4.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "A4";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorA4 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorA4 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            A4.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",A4", "");
            ContadorA4 = 0;
        }
     }

    public void A5() {
        ContadorA5 = ContadorA5 + 1;

        if (ContadorA5 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                A5.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "A5";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorA5 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorA5 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            A5.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",A5", "");
            ContadorA5 = 0;
        }
    }

    public void A6() {
        ContadorA6 = ContadorA6 + 1;

        if (ContadorA6 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                A6.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "A6";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorA6 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorA6 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            A6.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",A6", "");
            ContadorA6 = 0;
        }
    }

    public void A7() {
        ContadorA7 = ContadorA7 + 1;

        if (ContadorA7 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                A7.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "A7";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorA7 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorA7 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            A7.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",A7", "");
            ContadorA7 = 0;
        }
    }

    public void A8() {
        ContadorA8 = ContadorA8 + 1;

        if (ContadorA8 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                A8.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "A8";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorA8 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorA8 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            A8.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",A8", "");
            ContadorA8 = 0;
        }
    }

    public void A9() {
        ContadorA9 = ContadorA9 + 1;

        if (ContadorA9 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                A9.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "A9";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorA9 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorA9 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            A9.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",A9", "");
            ContadorA9 = 0;
        }
    }

    ///======FILA B=======///
    public void B1() {
        ContadorB1 = ContadorB1 + 1;

        if (ContadorB1 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                B1.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "B1";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorB1 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorB1 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            B1.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",B1", "");
            ContadorB1 = 0;
        }
    }

    public void B2() {
        ContadorB2 = ContadorB2 + 1;

        if (ContadorB2 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                B2.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "B2";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorB2 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorB2 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            B2.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",B2", "");
            ContadorB2 = 0;
        }
    }

    public void B3() {
        ContadorB3 = ContadorB3 + 1;

        if (ContadorB3 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                B3.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "B3";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorB3 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorB3 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            B3.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",B3", "");
            ContadorB3 = 0;
        }
    }

    public void B4() {
        ContadorB4 = ContadorB4 + 1;

        if (ContadorB4 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                B4.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "B4";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorB4 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorB4 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            B4.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",B4", "");
            ContadorB4 = 0;
        }
    }

    public void B5() {
        ContadorB5 = ContadorB5 + 1;

        if (ContadorB5 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                B5.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "B5";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorB5 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorB5 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            B5.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",B5", "");
            ContadorB5 = 0;
        }
    }

    public void B6() {
        ContadorB6 = ContadorB6 + 1;

        if (ContadorB6 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                B6.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "B6";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorB6 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorB6 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            B6.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",B6", "");
            ContadorB6 = 0;
        }
    }

    public void B7() {
        ContadorB7 = ContadorB7 + 1;

        if (ContadorB7 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                B7.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "B7";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorB7 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorB7 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            B7.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",B7", "");
            ContadorB7 = 0;
        }
     }

    public void B8() {
        ContadorB8 = ContadorB8 + 1;

        if (ContadorB8 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                B8.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "B8";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorB8 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorB8 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            B8.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",B8", "");
            ContadorB8 = 0;
        }
    }

    public void B9() {
        ContadorB9 = ContadorB9 + 1;

        if (ContadorB9 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                B9.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "B9";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorB9 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorB9 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            B9.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",B9", "");
            ContadorB9 = 0;
        }
    }

    ///======FILA C=======///
    public void C1() {
        ContadorC1 = ContadorC1 + 1;

        if (ContadorC1 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                C1.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "C1";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorC1 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorC1 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            C1.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",C1", "");
            ContadorC1 = 0;
        }
    }

    public void C2() {
        ContadorC2 = ContadorC2 + 1;

        if (ContadorC2 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                C2.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "C2";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorC2 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorC2 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            C2.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",C2", "");
            ContadorC2 = 0;
        }
    }

    public void C3() {
        ContadorC3 = ContadorC3 + 1;

        if (ContadorC3 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                C3.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "C3";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorC3 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorC3 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            C3.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",C3", "");
            ContadorC3 = 0;
        }
    }

    public void C4() {
        ContadorC4 = ContadorC4 + 1;

        if (ContadorC4 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                C4.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "C4";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorC4 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorC4 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            C4.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",C4", "");
            ContadorC4 = 0;
        }
     }

    public void C5() {
        ContadorC5 = ContadorC5 + 1;

        if (ContadorC5 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                C5.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "C5";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorC5 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorC5 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            C5.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",C5", "");
            ContadorC5 = 0;
        }
     }

    public void C6() {
        ContadorC6 = ContadorC6 + 1;

        if (ContadorC6 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                C6.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "C6";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorC6 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorC6 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            C6.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",C6", "");
            ContadorC6 = 0;
        }
     }

    public void C7() {
        ContadorC7 = ContadorC7 + 1;

        if (ContadorC7 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                C7.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "C7";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorC7 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorC7 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            C7.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",C7", "");
            ContadorC7 = 0;
        }
    }

    public void C8() {
        ContadorC8 = ContadorC8 + 1;

        if (ContadorC8 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                C8.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "C8";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorC8 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorC8 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            C8.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",C8", "");
            ContadorC8 = 0;
        }
    }

    public void C9() {
        ContadorC9 = ContadorC9 + 1;

        if (ContadorC9 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                C9.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "C9";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorC9 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorC9 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            C9.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",C9", "");
            ContadorC9 = 0;
        }
    }

    ///======FILA D=======///
    public void D1() {
        ContadorD1 = ContadorD1 + 1;

        if (ContadorD1 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                D1.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "D1";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorD1 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorD1 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            D1.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",D1", "");
            ContadorD1 = 0;
        }
    }

    public void D2() {
        ContadorD2 = ContadorD2 + 1;

        if (ContadorD2 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                D2.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "D2";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorD2 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorD2 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            D2.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",D2", "");
            ContadorD2 = 0;
        }
    }

    public void D3() {
        ContadorD3 = ContadorD3 + 1;

        if (ContadorD3 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                D3.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "D3";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorD3 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorD3 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            D3.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",D3", "");
            ContadorD3 = 0;
        }
    }

    public void D4() {
        ContadorD4 = ContadorD4 + 1;

        if (ContadorD4 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                D4.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "D4";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorD4 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorD4 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            D4.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",D4", "");
            ContadorD4 = 0;
        }
    }

    public void D5() {
        ContadorD5 = ContadorD5 + 1;

        if (ContadorD5 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                D5.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "D5";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorD5 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorD5 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            D5.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",D5", "");
            ContadorD5 = 0;
        }
    }

    public void D6() {
        ContadorD6 = ContadorD6 + 1;

        if (ContadorD6 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                D6.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "D6";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorD6 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorD6 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            D6.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",D6", "");
            ContadorD6 = 0;
        }
    }

    public void D7() {
        ContadorD7 = ContadorD7 + 1;

        if (ContadorD7 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                D7.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "D7";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorD7 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorD7 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            D7.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",D7", "");
            ContadorD7 = 0;
        }
    }

    public void D8() {
        ContadorD8 = ContadorD8 + 1;

        if (ContadorD8 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                D8.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "D8";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorD8 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorD8 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            D8.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",D8", "");
            ContadorD8 = 0;
        }
     }

    public void D9() {
        ContadorD9 = ContadorD9 + 1;

        if (ContadorD9 == 1) {
            if (SillasElegir != 0) {
                SillasElegir = SillasElegir - 1;
                LSTotal.setText("" + SillasElegir);
                D9.setBackground(Color.YELLOW);
                SillasSeleccionadas = SillasSeleccionadas + "," + "D9";
            } else if (SillasElegir == 0) {
                JOptionPane.showMessageDialog(null, "Ya selecciono la catidad de sillas máximas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                ContadorD9 = 0;
                SillasElegir = 0;
            }

        } else if (ContadorD9 == 2) {
            SillasElegir = SillasElegir + 1;
            LSTotal.setText("" + SillasElegir);
            D9.setBackground(null);
            SillasSeleccionadas = SillasSeleccionadas.replace(",D9", "");
            ContadorD9 = 0;
        }
    }

    public void TraerDatosPeli(String idPelicula) {
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

}//Cierre del programa.
