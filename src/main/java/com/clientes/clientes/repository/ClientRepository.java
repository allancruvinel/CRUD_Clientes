package com.clientes.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clientes.clientes.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

}
