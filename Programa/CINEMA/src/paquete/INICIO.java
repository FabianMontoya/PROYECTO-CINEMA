package paquete;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;

/**
 *
 * @author Fabian_Montoya
 */
public class INICIO extends JFrame implements ActionListener {

    JMenu Menu1, Menu2, Menu3;
    JMenuBar MBarra;
    JMenuItem Item1, Item2, Item3, Item4, Item5, Item6;
    JLabel L1, L2, L3, L4, L5, L6, L7, L8;
    JButton img1, img2, img3, img4, img5, img6, img7, img8, b1;
    String Usuario = "";
    int IDUsuario=0;

    public INICIO(String User, int IDUser) {
        super("UCCine - Gestor");
        setLayout(null);
        Usuario = User;
        IDUsuario=IDUser;
        menu();
        botones();
        textos();
        imagenes();

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
            INICIO c = new INICIO(Usuario, IDUsuario);
            c.setSize(700, 700);
            c.setResizable(false);
            c.setVisible(true);
            c.setLocationRelativeTo(null);
        }
    }

    public void menu() {
        MBarra = new JMenuBar();
        setJMenuBar(MBarra);

        Menu1 = new JMenu("Menu 1");
        Menu2 = new JMenu("Menu 2");
        Menu3 = new JMenu("Menu 3");

        MBarra.add(Menu1);
        MBarra.add(Menu2);
        MBarra.add(Menu3);

        Item1 = new JMenuItem("Item 1");
        Item2 = new JMenuItem("Item 2");
        Item3 = new JMenuItem("Item 3");
        Item4 = new JMenuItem("Item 4");
        Item5 = new JMenuItem("Item 5");
        Item6 = new JMenuItem("Item 6");

        Menu1.add(Item1);
        Menu1.add(Item2);

        Menu1.addActionListener(this);

        Menu2.add(Item3);
        Menu2.add(Item4);

        Menu3.add(Item5);
        Menu3.add(Item6);

        Item1.addActionListener(this);
        Item2.addActionListener(this);
        Item3.addActionListener(this);
        Item4.addActionListener(this);
        Item5.addActionListener(this);
        Item6.addActionListener(this);
    }

    public void botones() {
        /*b1 = new JButton("Reservar");
         b1.setBounds(280,610,100,25);
         b1.addActionListener(this);
         b1.putClientProperty( SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
         add (b1);*/
    }

    public void imagenes() {
        img1 = new JButton(new ImageIcon(getClass().getResource("hotel_transilvania2.jpg")));
        img1.setBounds(20, 100, 135, 200);
        add(img1);
        img1.addActionListener(this);

        img2 = new JButton(new ImageIcon(getClass().getResource("rio2.jpg")));
        img2.setBounds(190, 100, 135, 200);
        add(img2);
        img2.addActionListener(this);

        img3 = new JButton(new ImageIcon(getClass().getResource("minions.jpg")));
        img3.setBounds(360, 100, 135, 200);
        add(img3);
        img3.addActionListener(this);

        img4 = new JButton(new ImageIcon(getClass().getResource("diario_noa.jpg")));
        img4.setBounds(530, 100, 135, 200);
        add(img4);
        img4.addActionListener(this);

        img5 = new JButton(new ImageIcon(getClass().getResource("conjuro.jpg")));
        img5.setBounds(20, 360, 135, 200);
        add(img5);
        img5.addActionListener(this);

        img6 = new JButton(new ImageIcon(getClass().getResource("casa_del_demonio.jpg")));
        img6.setBounds(190, 360, 135, 200);
        add(img6);
        img6.addActionListener(this);

        img7 = new JButton(new ImageIcon(getClass().getResource("step_up3.jpg")));
        img7.setBounds(360, 360, 135, 200);
        add(img7);
        img7.addActionListener(this);

        img8 = new JButton(new ImageIcon(getClass().getResource("jurassic_world.jpg")));
        img8.setBounds(530, 360, 135, 200);
        add(img8);
        img8.addActionListener(this);
    }

    public void textos() {
        L1 = new JLabel("Hotel Transilvania 2");
        L1.setBounds(35, 210, 135, 200);
        add(L1);

        L2 = new JLabel("Rio 2");
        L2.setBounds(240, 210, 135, 200);
        add(L2);

        L3 = new JLabel("Los Minions");
        L3.setBounds(400, 210, 135, 200);
        add(L3);

        L4 = new JLabel("El Diario de Noa");
        L4.setBounds(560, 210, 135, 200);
        add(L4);

        L5 = new JLabel("El Conjuro");
        L5.setBounds(60, 470, 135, 200);
        add(L5);

        L6 = new JLabel("La Casa del Demonio");
        L6.setBounds(205, 470, 135, 200);
        add(L6);

        L7 = new JLabel("Step Up 3");
        L7.setBounds(405, 470, 135, 200);
        add(L7);

        L8 = new JLabel("Jurassic World");
        L8.setBounds(560, 470, 135, 200);
        add(L8);
    }

    public static void main(String[] args) {
        //JFrame.setDefaultLookAndFeelDecorated(true);
        //SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.CremeCoffeeSkin");

        /*JFrame cuadro = new INICIO();
         cuadro.setSize (700,700);
         cuadro.setLocationRelativeTo(null); 
         cuadro.setVisible (true);
         cuadro.setResizable(false);
         cuadro.setDefaultCloseOperation(EXIT_ON_CLOSE);*/
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
       

        if (ae.getSource() == img1) {
            setVisible(false);
            Transilvania2 r = new Transilvania2(Usuario, IDUsuario);
            r.setSize(500, 350);
            r.setResizable(false);
            r.setVisible(true);
            r.setLocationRelativeTo(null);

        }

        if (ae.getSource() == img2) {
            setVisible(false);
            Rio2 r = new Rio2(Usuario, IDUsuario);
            r.setSize(500, 350);
            r.setResizable(false);
            r.setVisible(true);
            r.setLocationRelativeTo(null);

        }

        if (ae.getSource() == img3) {
            setVisible(false);
            Minioms r = new Minioms(Usuario, IDUsuario);
            r.setSize(500, 350);
            r.setResizable(false);
            r.setVisible(true);
            r.setLocationRelativeTo(null);

        }

        if (ae.getSource() == img4) {
            setVisible(false);
            Noa r = new Noa(Usuario, IDUsuario);
            r.setSize(500, 350);
            r.setResizable(false);
            r.setVisible(true);
            r.setLocationRelativeTo(null);

        }

        if (ae.getSource() == img5) {
            setVisible(false);
            Conjuro r = new Conjuro(Usuario, IDUsuario);
            r.setSize(500, 350);
            r.setResizable(false);
            r.setVisible(true);
            r.setLocationRelativeTo(null);

        }

        if (ae.getSource() == img6) {
            setVisible(false);
            Demonio r = new Demonio(Usuario, IDUsuario);
            r.setSize(500, 350);
            r.setResizable(false);
            r.setVisible(true);
            r.setLocationRelativeTo(null);

        }

        if (ae.getSource() == img7) {
            setVisible(false);
            Step r = new Step(Usuario, IDUsuario);
            r.setSize(500, 350);
            r.setResizable(false);
            r.setVisible(true);
            r.setLocationRelativeTo(null);

        }

        if (ae.getSource() == img8) {
            setVisible(false);
            Jurassic r = new Jurassic(Usuario, IDUsuario);
            r.setSize(500, 350);
            r.setResizable(false);
            r.setVisible(true);
            r.setLocationRelativeTo(null);

        }
    }

}
