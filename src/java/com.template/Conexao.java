/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.template;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* Classe responsável por realizar a conexão com o banco de dados. */

public class Conexao {

    static String conexao = "jdbc:postgresql://localhost:5432/MarcasDeMaquiagem";
    static String usuario = "postgres";
    static String senha = "postgres";

    //função que retorna conexão com o banco de dados
    public Connection conectaBD() {
        try {
            return DriverManager.getConnection(conexao, usuario, senha); //DriverManager é uma classe responsável por gerenciar drivers de banco de dados e estabelecer conexões.
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
