/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.saferoute;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author andre
 */
public class SafeRouteMySQL  {
    public SafeRouteMySQL(String email, String clave, String tipoConsulta) {
        boolean exito = false;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/saferoute", "root", "");
            Statement stmt = conexion.createStatement();

            if ("registro".equalsIgnoreCase(tipoConsulta)) {
                String check = "SELECT * FROM usuarios WHERE email = '" + email + "'";
                ResultSet rs = stmt.executeQuery(check);

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "El correo ya está registrado.");
                } else {
                    String insert = "INSERT INTO usuarios (email, clave) VALUES ('" + email + "', '" + clave + "')";
                    stmt.executeUpdate(insert);
                    JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.");
                    exito = true;
                }
                rs.close();

            } else if ("login".equalsIgnoreCase(tipoConsulta)) {
                String login = "SELECT * FROM usuarios WHERE email = '" + email + "' AND clave = '" + clave + "'";
                ResultSet rs = stmt.executeQuery(login);

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.");
                    exito = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos.");
                }

                rs.close();
            }

            stmt.close();
            conexion.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL: " + e.getMessage());
        }
    }
    public void guardarMensaje(String mensaje) {
    try {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/saferoute", "root", "");
        Statement stmt = conexion.createStatement();

        String insertMensaje = "INSERT INTO mensaje (mensaje) VALUES ('" + mensaje + "')";
        stmt.executeUpdate(insertMensaje);
        
        JOptionPane.showMessageDialog(null, "Mensaje guardado correctamente.");
        
        stmt.close();
        conexion.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error SQL: " + e.getMessage());
    }
}
    public void guardarRuta(String puntoInicio, String destino) {
    try {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/saferoute", "root", "");
        Statement stmt = conexion.createStatement();

        String insertRuta = "INSERT INTO rutas (punto_inicio, destino) VALUES ('" + puntoInicio + "', '" + destino + "')";
        stmt.executeUpdate(insertRuta);
        
        JOptionPane.showMessageDialog(null, "Ruta guardada correctamente.");
        
        stmt.close();
        conexion.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error SQL: " + e.getMessage());
    }
}
    public static boolean validarLogin(String email, String clave) {
    boolean exito = false;
    try {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/saferoute", "root", "");
        Statement stmt = conexion.createStatement();

        String login = "SELECT * FROM usuarios WHERE email = '" + email + "' AND clave = '" + clave + "'";
        ResultSet rs = stmt.executeQuery(login);

        if (rs.next()) {
            exito = true;
        }

        rs.close();
        stmt.close();
        conexion.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error SQL: " + e.getMessage());
    }

    return exito;
}


}
