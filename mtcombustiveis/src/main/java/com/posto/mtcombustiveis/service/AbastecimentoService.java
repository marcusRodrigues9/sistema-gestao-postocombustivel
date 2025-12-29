package com.posto.mtcombustiveis.service;

import com.posto.mtcombustiveis.entity.Abastecimento;
import com.posto.mtcombustiveis.entity.BombaCombustivel;
import com.posto.mtcombustiveis.entity.dto.AbastecimentoRequest;
import com.posto.mtcombustiveis.exception.BusinessException;
import com.posto.mtcombustiveis.exception.ResourceNotFoundException;
import com.posto.mtcombustiveis.repository.IAbastecimentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AbastecimentoService {
    private final IAbastecimentoRepository abastecimentoRepository;
    private final BombaCombustivelService bombaCombustivelService;

    public AbastecimentoService(IAbastecimentoRepository abastecimentoRepository, BombaCombustivelService bombaCombustivelService) {
        this.abastecimentoRepository = abastecimentoRepository;
        this.bombaCombustivelService = bombaCombustivelService;
    }
    public List<Abastecimento> listarTodos() {
        return abastecimentoRepository.findAll();
    }

    public Abastecimento buscarPorId(Long id) {
        return abastecimentoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Abastecimento não encontrado"));
    }

    public Abastecimento registrarAbastecimento(AbastecimentoRequest request) {
        if (request.getQuantidadeLitros() <= 0) {
            throw new BusinessException("Quantidade de litros deve ser maior que zero");
        }
        BombaCombustivel bomba = bombaCombustivelService.findById(request.getBombaCombustivelId());
        if (bomba.getTipoCombustivel().getPrecoPorLitro() == null) {
            throw new BusinessException("Preço do combustível não definido");
        }
        double precoLitro = bomba.getTipoCombustivel().getPrecoPorLitro();
        double total = request.getQuantidadeLitros() * precoLitro;
        Abastecimento abastecimento = new Abastecimento();
        abastecimento.setBombaCombustivel(bomba);
        abastecimento.setQuantidadeLitros(request.getQuantidadeLitros());
        abastecimento.setValorTotal(total);
        abastecimento.setDataAbastecimento(LocalDateTime.now());
        return abastecimentoRepository.save(abastecimento);
    }

    public void deletar(Long id) {
        Abastecimento abastecimento = buscarPorId(id);
        abastecimentoRepository.delete(abastecimento);
    }
}

