package paquete;

import java.sql.Connection;
import javax.swing.JOptionPane;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

public class EnviarReserva {

    public static int IDUsuario = 0, IDPelicula = 0, TotalSillas = 0, IDSala = 0, IDReserva = 0, IDEntradaSala = 0, SillasDisponibles = 0, SillasReservadas = 0, INGRESO=0;
    public static String Fecha = "", Hora = "", SillasReserva = "", SillasNuevas = "";

    public static Connection conexion = null;
    public static OraclePreparedStatement pst = null;
    public static OracleResultSet rs = null;

    public static int EnvioReserva(int IDUser, String IDPeli, String fecha, String hora, int numerosillas, String sillasselect, String sala) {
        int fallo = 0, aux1=0;

        IDUsuario = IDUser;
        IDPelicula = Integer.parseInt(IDPeli);
        TotalSillas = numerosillas;
        Fecha = fecha;
        Hora = hora;
        SillasReserva = sillasselect;
        IDSala = Integer.parseInt(sala);
        
        try {
            IDReserva = CodigoReserva();
            
            Reservas();

            IDEntradaSala = CodigoSala();
            
            aux1=SALAS(IDSala, Fecha, Hora);
            
            if (aux1 == 1) {
                 MODIFICAR();
            } else if (aux1 == 0) {
                NuevoIngresoSala();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            fallo = 1;
        }
        return fallo;
    }

    public static int CodigoReserva() {
        int codigo = 0;
        String cadena = "";
        conexion = ConexionOracle.ConnectDB();
        try {
            String sql = "SELECT numero_reserva FROM RESERVAS WHERE numero_reserva = (SELECT MAX(numero_reserva) from RESERVAS)";
            pst = (OraclePreparedStatement) conexion.prepareStatement(sql);
            rs = (OracleResultSet) pst.executeQuery();

            while (rs.next() != false) {
                cadena = rs.getString("numero_reserva");
            }

            codigo = Integer.parseInt(cadena);
            codigo = codigo + 1;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en Metodo CodigoReserva():\n\n" + e);
        }
        return codigo;
    }

    public static int CodigoSala() {
        int codigo = 0;
        String cadena = "";
        conexion = ConexionOracle.ConnectDB();
        try {
            String sql = "SELECT numero_ingreso FROM SALAS WHERE numero_ingreso = (SELECT MAX(numero_ingreso) from SALAS)";
            pst = (OraclePreparedStatement) conexion.prepareStatement(sql);
            rs = (OracleResultSet) pst.executeQuery();

            while (rs.next() != false) {
                cadena = rs.getString("numero_ingreso");
            }

            codigo = Integer.parseInt(cadena);
            codigo = codigo + 1;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en Metodo CodigoSala():\n\n" + e);
        }
        return codigo;
    }

    public static void Reservas() {
        conexion = ConexionOracle.ConnectDB();
        String sillasreserva = "";

        sillasreserva = SillasReserva.substring(1);
        try {
            String sql = "INSERT INTO RESERVAS (numero_reserva, id_cliente, id_sala_reserva, fecha_funcion, hora_funcion, idpelicula_reserva, numerosillas_reserva, sillas_reserva) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            pst = (OraclePreparedStatement) conexion.prepareStatement(sql);
            pst.setInt(1, IDReserva);
            pst.setInt(2, IDUsuario);
            pst.setInt(3, IDSala);
            pst.setString(4, Fecha);
            pst.setString(5, Hora);
            pst.setInt(6, IDPelicula);
            pst.setInt(7, TotalSillas);
            pst.setString(8, sillasreserva);

            rs = (OracleResultSet) pst.executeQuery();

            JOptionPane.showMessageDialog(null, "RESERVA COMPLETADA\n\n- DATOS RESERVA\n\n- Codigo Reserva: " + IDReserva + "\n- ID Usuario: " + IDUsuario + "\n- Número de Sala: " + IDSala + "\n- Fecha función: " + Fecha + "\n- Hora función: " + Hora + "\n- ID Pelicula: " + IDPelicula + "\n- Total sillas reservadas: " + TotalSillas + "\n- Sillas Reservadas: " + sillasreserva + "\n\nLISTO.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en Metodo Reservas():\n\n" + e);
        }
    }

    public static int SALAS(int idSala, String fecha, String hora) {
        conexion = ConexionOracle.ConnectDB();
        int auxiliar = 0;
        String Ingreso="";
        try {
            String sql = "SELECT numero_ingreso FROM SALAS WHERE numero_sala=? and fecha_sala=? and hora_sala=?";
            pst = (OraclePreparedStatement) conexion.prepareStatement(sql);
            pst.setInt(1, idSala);
            pst.setString(2, fecha);
            pst.setString(3, hora);
            rs = (OracleResultSet) pst.executeQuery();

            while (rs.next() != false) {
                Ingreso=rs.getString("numero_ingreso");
                INGRESO=Integer.parseInt(Ingreso);
                auxiliar = 1;
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en Metodo SALAS():\n\n" + e);
        }
        return auxiliar;
    }

    public static void MODIFICAR() {
        conexion = ConexionOracle.ConnectDB();
        String Disponibles = "", Reservadas = "", SSillas = "", Entrada="", SALA="";
        int disponibles = 0, reservadas = 0;

        try {
            String sql = "SELECT * FROM SALAS WHERE numero_sala=? and fecha_sala=? and hora_sala=?";
            pst = (OraclePreparedStatement) conexion.prepareStatement(sql);
            pst.setInt(1, IDSala);
            pst.setString(2, Fecha);
            pst.setString(3, Hora);
            rs = (OracleResultSet) pst.executeQuery();

            while (rs.next() != false) {
                Entrada=rs.getString("numero_ingreso");
                SALA=rs.getString("numero_sala");
                Disponibles = rs.getString("numerodisponibles_sala");
                Reservadas = rs.getString("numeroreservadas_sala");
                SSillas = rs.getString("sillasreservadas_sala");
            }

            disponibles = Integer.parseInt(Disponibles);
            reservadas = Integer.parseInt(Reservadas);

            disponibles = disponibles - TotalSillas;
            reservadas = reservadas + TotalSillas;
            SSillas = SSillas + "" + SillasReserva;
            ModificarSala(disponibles, reservadas, SSillas);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en Metodo MODIFICAR():\n\n" + e);

        }

    }

    public static void ModificarSala(int disponibles, int Reservadas, String nuevasSillas) {
        conexion = ConexionOracle.ConnectDB();
        int DISPONIBLES = 0, RESERVADAS = 0;
        String NUEVASsillas = "";
        
        
        DISPONIBLES = disponibles;
        RESERVADAS = Reservadas;
        NUEVASsillas = nuevasSillas;
        try {
            String sql = "UPDATE SALAS SET numerodisponibles_sala=?, numeroreservadas_sala=?, sillasreservadas_sala=? WHERE numero_ingreso=" + INGRESO;
             pst = (OraclePreparedStatement) conexion.prepareStatement(sql);
            pst.setInt(1, DISPONIBLES);
            pst.setInt(2, RESERVADAS);
            pst.setString(3, NUEVASsillas);

            rs = (OracleResultSet) pst.executeQuery();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en Metodo ModificarSalas():\n\n" + e);
        }
    }

    public static void NuevoIngresoSala() {
        conexion = ConexionOracle.ConnectDB();
        String sillasreserva = "";
        int totalSillas = 36, disponibles = 0;
        sillasreserva = SillasReserva.substring(1);
        disponibles = 36 - TotalSillas;
        try {
            String sql = "INSERT INTO SALAS (numero_ingreso, numero_sala, fecha_sala, hora_sala, totalsillas_sala, numerodisponibles_sala, numeroreservadas_sala, sillasreservadas_sala) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            pst = (OraclePreparedStatement) conexion.prepareStatement(sql);
            pst.setInt(1, IDEntradaSala);
            pst.setInt(2, IDSala);
            pst.setString(3, Fecha);
            pst.setString(4, Hora);
            pst.setInt(5, totalSillas);
            pst.setInt(6, disponibles);
            pst.setInt(7, TotalSillas);
            pst.setString(8, sillasreserva);

            rs = (OracleResultSet) pst.executeQuery();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en Metodo NuevoIngresoSala():\n\n" + e);
        }
    }

}
