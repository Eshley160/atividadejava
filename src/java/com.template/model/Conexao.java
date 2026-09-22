package com.template.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* Classe responsável por realizar a conexão com o banco de dados. */

public class Conexao {

    static String conexao = "jdbc:postgresql://localhost:5432/MarcasDeMaquiagem";
    static String usuario = "postgres";
    static String senha = "postgres";

    // função que retorna conexão com o banco de dados
    public Connection conectaBD() {
        try {
            // Regista explicitamente o driver do PostgreSQL na JVM
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(conexao, usuario, senha);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("O driver do PostgreSQL não está presente no projeto.", e);
        } catch (SQLException e) {
            throw new RuntimeException("Falha na ligação à base de dados: " + e.getMessage(), e);
        }
    }
}