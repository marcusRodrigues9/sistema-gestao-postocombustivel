package com.posto.mtcombustiveis.service;

import com.posto.mtcombustiveis.entity.BombaCombustivel;
import com.posto.mtcombustiveis.entity.TipoCombustivel;
import com.posto.mtcombustiveis.entity.dto.BombaCombustivelRequest;
import com.posto.mtcombustiveis.exception.BusinessException;
import com.posto.mtcombustiveis.exception.ResourceNotFoundException;
import com.posto.mtcombustiveis.repository.IAbastecimentoRepository;
import com.posto.mtcombustiveis.repository.IBombaCombustivelRepository;
import com.posto.mtcombustiveis.repository.ITipoCombustivelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BombaCombustivelService {

    private final IBombaCombustivelRepository bombaCombustivelRepository;
    private final TipoCombustivelService tipoCombustivelService;
    private final IAbastecimentoRepository abastecimentoRepository;
    public BombaCombustivelService(
            IBombaCombustivelRepository bombaCombustivelRepository,
            TipoCombustivelService tipoCombustivelService,
            IAbastecimentoRepository abastecimentoRepository
    ){
        this.bombaCombustivelRepository = bombaCombustivelRepository;
        this.tipoCombustivelService = tipoCombustivelService;
        this.abastecimentoRepository = abastecimentoRepository;
    }

    //criar nova Bomba de combustivel
    public BombaCombustivel create(BombaCombustivelRequest request){
        bombaCombustivelRepository.findByNome(request.getNome())
                .ifPresent(b -> {
                    throw new BusinessException("Já existe uma bomba com este nome");
                });
        TipoCombustivel tipo = tipoCombustivelService
                .findById(request.getTipoCombustivelId());

        BombaCombustivel bomba = new BombaCombustivel();
        bomba.setNome(request.getNome());
        bomba.setTipoCombustivel(tipo);

        return bombaCombustivelRepository.save(bomba);
    }

    //listar todas
    public List<BombaCombustivel> findAll(){
        return bombaCombustivelRepository.findAll();
    }
    //listar unica Bomba de combustivel pelo id
    public BombaCombustivel findById(Long id){
        return bombaCombustivelRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Bomba de combustivel não encontrada!"));
    }

    //atualizar Bomba combustivel, atualiza o nome e o tipo de combustivel
    public BombaCombustivel update(Long id, BombaCombustivelRequest novo) {
        BombaCombustivel existente = findById(id);
        bombaCombustivelRepository.findByNome(novo.getNome())
                .filter(bc -> !bc.getId().equals(id))
                .ifPresent(bc -> {
                    throw new BusinessException("Já existe uma bomba com este nome!");
                });

        TipoCombustivel tipo = tipoCombustivelService
                .findById(novo.getTipoCombustivelId());
        existente.setNome(novo.getNome());
        existente.setTipoCombustivel(tipo);

        return bombaCombustivelRepository.save(existente);
    }

    @Transactional
    public void delete(Long id) {
        BombaCombustivel existente = findById(id);
        // VALIDAÇÃO: Verifica se existem abastecimentos vinculados
        if (abastecimentoRepository.existsByBombaCombustivelId(id)) {
            throw new BusinessException(
                    "Não é possível excluir a bomba pois existem abastecimentos registrados"
            );
        }

        bombaCombustivelRepository.delete(existente);
    }
}
