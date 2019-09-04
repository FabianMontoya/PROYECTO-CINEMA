package paquete;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.util.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Transilvania2 extends JFrame implements ActionListener {

    JLabel I1, N1, D1, D2, D3, D4, D5;
    JButton Reservar;
    String Pelicula = "Transilvania", Usuario = "", LPeli = "Hotel Transilvania 2", IDPelicula="1";
    int IDUsuario=0;

    JMenu Menu1;
    JMenuBar MBarra;
    JMenuItem Volver, Salir;

    public Transilvania2(String User, int IDUser) {

        super("Hotel Transilvania 2 ");
        setLayout(null);

        Usuario = User;
        IDUsuario=IDUser;
        I1 = new JLabel(new ImageIcon(getClass().getResource("hotel_transilvania2.jpg")));
        I1.setBounds(5, 15, 140, 200);
        I1.setVisible(true);

        String titulo = "<html><body><font color=#3104B4>Hotel Transilvania 2 </font></body></html>";
        N1 = new JLabel(titulo);
        N1.setBounds(22, 227, 200, 10);

        String texto = "<html><body> <font face=\"Comic Sans MS\">  <i> SINOPSIS:</i></font><p align=\"justify\">Desde su nacimiento, Mavis vive en el lujoso hotel"
                + " Transylvania, que fue creado para proteger a los monstruos de la amenaza de los "
                + "seres humanos. Cuando Mavis está a punto de cumplir la mayoría de edad (118"
                + " años), su padre, el conde Drácula, le prepara una fiesta a "
                + "la que acuden los mejores amigos de la familia: desde"
                + " Frankenstein al Hombre Invisible o la Momia; pero "
                + "también asiste a la celebración un hombre que se"
                + " enamora de Mavis. </p></body></html>";
        D1 = new JLabel(texto);
        D1.setBounds(150, 5, 330, 180);

        String genero = "<html><body> <font face=\"Comic Sans MS\">  <i> GENERO: </i></font>Animación/Comedia</body></html>";
        D2 = new JLabel(genero);
        D2.setBounds(150, 180, 330, 30);

        String clasificacion = "<html><body> <font face=\"Comic Sans MS\">  <i> CLASIFICACIÓN: </i></font>Todos </body></html>";
        D4 = new JLabel(clasificacion);
        D4.setBounds(150, 198, 330, 20);

        String duracion = "<html><body> <font face=\"Comic Sans MS\">  <i> DURACIÓN: </i></font>91 Minutos </body></html>";
        D3 = new JLabel(duracion);
        D3.setBounds(150, 210, 330, 20);

        String idioma = "<html><body> <font face=\"Comic Sans MS\">  <i> IDIOMA: </i></font>Español Latino </body></html>";
        D5 = new JLabel(idioma);
        D5.setBounds(150, 222, 330, 20);

        Reservar = new JButton("Reservar");
        Reservar.setBounds(350, 200, 100, 30);
        Reservar.addActionListener(this);

        add(I1);
        add(D1);
        add(Reservar);
        add(N1);
        add(D2);
        add(D3);
        add(D4);
        add(D5);

        menu();

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
            Transilvania2 c = new Transilvania2(Usuario, IDUsuario);
            c.setSize(500, 350);
            c.setResizable(false);
            c.setVisible(true);
            c.setLocationRelativeTo(null);
        }
    }

    public void menu() {
        MBarra = new JMenuBar();
        setJMenuBar(MBarra);

        Menu1 = new JMenu("Inicio");

        MBarra.add(Menu1);

        Volver = new JMenuItem("Volver a Inicio");
        Salir = new JMenuItem("Salir");

        Menu1.add(Volver);
        Menu1.add(Salir);

        Volver.addActionListener(this);
        Salir.addActionListener(this);

    }

    public static void main(String[] args) {

        /*JFrame ventana = new Transilvania2();
         ventana.setVisible(true);
         ventana.setSize(500, 350);
         ventana.setDefaultCloseOperation(EXIT_ON_CLOSE);*/
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == Volver) {
            setVisible(false);
            INICIO c = new INICIO(Usuario, IDUsuario);
            c.setSize(700, 700);
            c.setResizable(false);
            c.setVisible(true);
            c.setLocationRelativeTo(null);

        }

        if (ae.getSource() == Salir) {

            int ax = JOptionPane.showConfirmDialog(null, "¿Desea cerrar la sesion y salir?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (ax == JOptionPane.YES_OPTION) {
                setVisible(false);
                ControlAcceso aplicacion = new ControlAcceso();
                aplicacion.setSize(300, 300);
                aplicacion.setResizable(false);
                aplicacion.setLocationRelativeTo(null);
                aplicacion.setVisible(true);
                aplicacion.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        }

        if (ae.getSource() == Reservar) {
            int ax = JOptionPane.showConfirmDialog(null, "¿Desea reservar la película " + LPeli + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (ax == JOptionPane.YES_OPTION) {
                setVisible(false);
                ReservarPeli aplicacion = new ReservarPeli(Pelicula, IDPelicula, Usuario, IDUsuario);
                aplicacion.setSize(400, 500);
                aplicacion.setVisible(true);
                aplicacion.setResizable(false);
                aplicacion.setLocationRelativeTo(null);
                aplicacion.setVisible(true);

            }

        }

    }

}
