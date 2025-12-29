package com.posto.mtcombustiveis.service;

import com.posto.mtcombustiveis.entity.TipoCombustivel;
import com.posto.mtcombustiveis.entity.dto.TipoCombustivelRequest;
import com.posto.mtcombustiveis.exception.BusinessException;
import com.posto.mtcombustiveis.exception.ResourceNotFoundException;
import com.posto.mtcombustiveis.repository.IBombaCombustivelRepository;
import com.posto.mtcombustiveis.repository.ITipoCombustivelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TipoCombustivelService {

    private final ITipoCombustivelRepository tipoCombustivelRepository;
    private final IBombaCombustivelRepository bombaCombustivelRepository;
    public TipoCombustivelService(ITipoCombustivelRepository tipoCombustivelRepository, IBombaCombustivelRepository bombaCombustivelRepository){
        this.tipoCombustivelRepository = tipoCombustivelRepository;
        this.bombaCombustivelRepository = bombaCombustivelRepository;
    }

    //criar novo tipo de combustivel
    public TipoCombustivel create(TipoCombustivelRequest request) {

        tipoCombustivelRepository.findByNome(request.getNome())
                .ifPresent(tc -> {
                    throw new BusinessException("Tipo de combustível já cadastrado");
                });

        TipoCombustivel tipoCombustivel = new TipoCombustivel();
        tipoCombustivel.setNome(request.getNome());
        tipoCombustivel.setPrecoPorLitro(request.getPrecoPorLitro());

        return tipoCombustivelRepository.save(tipoCombustivel);
    }
    //listar todos tipos
    public List<TipoCombustivel> findAll(){
        return tipoCombustivelRepository.findAll();
    }
    //listar unico combustivel pelo id
    public TipoCombustivel findById(Long id){
        return tipoCombustivelRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Tipo de combustivel não encontrado"));
    }
    //atualizar Tipo combustivel, atualiza o nome e preco
    public TipoCombustivel update(Long id, TipoCombustivel novo) {
        TipoCombustivel existente = findById(id);

        tipoCombustivelRepository.findByNome(novo.getNome())
                .filter(tc -> !tc.getId().equals(id))
                .ifPresent(tc -> {
                    throw new BusinessException("Já existe um combustível com esse nome");
                });

        existente.setNome(novo.getNome());
        existente.setPrecoPorLitro(novo.getPrecoPorLitro());

        return tipoCombustivelRepository.save(existente);
    }
    @Transactional
    public void delete(Long id) {
        TipoCombustivel existente = findById(id);

        if (bombaCombustivelRepository.existsByTipoCombustivelId(id)) {
            throw new BusinessException(
                    "Não é possível excluir o tipo de combustível pois existem bombas associadas"
            );
        }
        tipoCombustivelRepository.delete(existente);
    }
}
