package com.fullstack.irm.repository;

import com.fullstack.irm.entity.Cliente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
