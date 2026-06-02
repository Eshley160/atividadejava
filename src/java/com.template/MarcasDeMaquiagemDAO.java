package com.template;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/* Classe responsável pelas operações no banco de dados relacionadas à tabela marcas_maquiagem. */

public class MarcasDeMaquiagemDAO {

    ArrayList<MarcasDeMaquiagemDTO> listaMaquiagens = new ArrayList<>();

    public ArrayList<MarcasDeMaquiagemDTO> listarMaquiagens() {

        String sql = "SELECT * FROM marcas_maquiagem";

        try (
                Connection c = new Conexao().conectaBD();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                MarcasDeMaquiagemDTO maquiagem = new MarcasDeMaquiagemDTO();

                maquiagem.setId(rs.getInt("id"));
                maquiagem.setNome(rs.getString("nome"));
                maquiagem.setPaisOrigem(rs.getString("pais_origem"));
                maquiagem.setAnoFundacao(rs.getInt("ano_fundacao"));
                maquiagem.setCrueltyFree(rs.getBoolean("cruelty_free"));

                listaMaquiagens.add(maquiagem);
            }
        } catch (SQLException e) {
            Logger.getLogger(MarcasDeMaquiagemDAO.class.getName())
                    .log(Level.SEVERE, null, e);
        }
        return listaMaquiagens;
    }

    public void cadastrarMarca(MarcasDeMaquiagemDTO maquiagem) {
        String sql = "INSERT INTO marcas_maquiagem (nome, pais_origem, ano_fundacao, cruelty_free) VALUES (?, ?, ?, ?)";
        try (
                Connection c = new Conexao().conectaBD();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, maquiagem.getNome());
            ps.setString(2, maquiagem.getPaisOrigem());
            ps.setInt(3, maquiagem.getAnoFundacao());
            ps.setBoolean(4, maquiagem.isCrueltyFree());
            ps.executeUpdate();
        } catch (SQLException e) {

            Logger.getLogger(MarcasDeMaquiagemDAO.class.getName())
                    .log(Level.SEVERE, null, e);
        }
    }

    public void visualizarMarca() {
        String sql = "SELECT * FROM marcas_maquiagem";
        try (
                Connection c = new Conexao().conectaBD();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("País de origem: " + rs.getString("pais_origem"));
                System.out.println("Ano de fundação: " + rs.getInt("ano_fundacao"));
                System.out.println("Cruelty Free: " + rs.getBoolean("cruelty_free"));
            }
        } catch (SQLException e) {
            Logger.getLogger(MarcasDeMaquiagemDAO.class.getName())
                    .log(Level.SEVERE, null, e);
        }
    }

    // Atualiza os dados de uma marca utilizando o id informado
    public void atualizarMarca(MarcasDeMaquiagemDTO marca) {
        String sql = "UPDATE marcas_maquiagem SET nome = ?, pais_origem = ?, ano_fundacao = ?, cruelty_free = ? WHERE id = ?";
        try (
                Connection c = new Conexao().conectaBD();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, marca.getNome());
            ps.setString(2, marca.getPaisOrigem());
            ps.setInt(3, marca.getAnoFundacao());
            ps.setBoolean(4, marca.isCrueltyFree());
            ps.setInt(5, marca.getId());
            ps.executeUpdate();
        } catch (SQLException e) {

            Logger.getLogger(MarcasDeMaquiagemDAO.class.getName())
                    .log(Level.SEVERE, null, e);
        }
    }

    public void excluirMarca(int id) {
        String sql = "DELETE FROM marcas_maquiagem WHERE id = ?";
        try (
                Connection c = new Conexao().conectaBD();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(MarcasDeMaquiagemDAO.class.getName())
                    .log(Level.SEVERE, null, e);
        }
    }
}