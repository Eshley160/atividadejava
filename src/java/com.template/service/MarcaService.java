package com.template.service;

import com.template.model.dao.MarcasDeMaquiagemDAO;
import com.template.model.dto.MarcasDeMaquiagemDTO;

import java.util.ArrayList;

public class MarcaService {

    private final MarcasDeMaquiagemDAO marcaDAO;

    public MarcaService() {
        this.marcaDAO = new MarcasDeMaquiagemDAO();
    }

    public ArrayList<MarcasDeMaquiagemDTO> listarMaquiagens() {
        return marcaDAO.listarMaquiagens();
    }

    public boolean cadastrarMarca(String nome, String paisOrigem, String anoText, boolean crueltyFree) {

        MarcasDeMaquiagemDTO maquiagem = new MarcasDeMaquiagemDTO();
        maquiagem.setNome(nome);
        maquiagem.setPaisOrigem(paisOrigem);
        maquiagem.setAnoFundacao(Integer.parseInt(anoText));
        maquiagem.setCrueltyFree(crueltyFree);

        marcaDAO.cadastrarMarca(maquiagem);
        return true;
    }

    public boolean atualizarMarca(int id, String nome, String paisOrigem, String anoText, boolean crueltyFree) {

        MarcasDeMaquiagemDTO marcaDTO = new MarcasDeMaquiagemDTO();
        marcaDTO.setId(id);
        marcaDTO.setNome(nome);
        marcaDTO.setAnoFundacao(Integer.parseInt(anoText));
        marcaDTO.setPaisOrigem(paisOrigem);
        marcaDTO.setCrueltyFree(crueltyFree);

        marcaDAO.atualizarMarca(marcaDTO);
        return true;
    }

    public void excluirMarca(int id) {
        marcaDAO.excluirMarca(id);
    }
}