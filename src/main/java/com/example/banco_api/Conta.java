package com.example.banco_api;

import jakarta.persistence.*;

@Entity
@Table(name = "contas")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Integer numero;

    @Column(nullable = false)
    private Double saldo = 0.0;

    public Conta() {}

    public Conta(Integer numero) {
        this.numero = numero;
        this.saldo = 0.0;
    }

    public Long getId() {
        return id;
    }

    public Integer getNumero() {
        return numero;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void depositar(Double valor) {
        if (valor > 0) {
            saldo += valor;
        }
    }

    public void sacar(Double valor) {
        if (valor <= saldo) {
            saldo -= valor;
        }
    }
}
