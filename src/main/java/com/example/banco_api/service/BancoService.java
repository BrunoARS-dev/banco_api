package com.example.banco_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.banco_api.Conta;
import com.example.banco_api.repository.ContaRepository;
import com.example.banco_api.exception.ContaNaoEncontradaException;
import com.example.banco_api.exception.OperacaoInvalidaException;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BancoService {

    private final ContaRepository repository;

    public BancoService(ContaRepository repository) {
        this.repository = repository;
    }

    public Conta criarConta(Conta conta) {
        return repository.save(conta);
    }

    public Conta buscarConta(Integer numero) {
        Conta conta = repository.findByNumero(numero);

        if (conta == null) {
            throw new ContaNaoEncontradaException("Conta não encontrada");
        }

        return conta;
    }


    public Conta depositar(Integer numero, Double valor) {

        Conta conta = buscarConta(numero);

        if (valor <= 0) {
            throw new OperacaoInvalidaException("Valor inválido para depósito");
        }

        conta.depositar(valor);
        return repository.save(conta);
    }


    public Conta sacar(Integer numero, Double valor) {

        Conta conta = buscarConta(numero);

        if (valor <= 0) {
            throw new OperacaoInvalidaException("Valor inválido para saque");
        }

        if (conta.getSaldo() < valor) {
            throw new OperacaoInvalidaException("Saldo insuficiente");
        }

        conta.sacar(valor);
        return repository.save(conta);
    }


    public List<Conta> listarContas() {
        return repository.findAll();
    }

    @Transactional
    public void transferencia(Integer contaOrigem, Integer contaDestino, Double valor) {
 

        Conta origem = buscarConta(contaOrigem);
        Conta destino = buscarConta(contaDestino);

        if (origem.getNumero().equals(destino.getNumero())) {
            throw new OperacaoInvalidaException("Não é possível transferir para a mesma conta");
        }

        if (origem.getSaldo() < valor) {
            throw new OperacaoInvalidaException("Saldo insuficiente");
        }

        origem.sacar(valor);
        destino.depositar(valor);

        repository.save(origem);
        repository.save(destino);
    }

}
