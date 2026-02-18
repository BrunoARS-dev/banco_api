package com.example.banco_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.banco_api.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    Conta findByNumero(Integer numero);

}
