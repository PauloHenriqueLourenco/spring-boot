package com.gclick.testefullstack.infra.jpa.repositories;

import com.gclick.testefullstack.infra.jpa.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJpaClientesRepository extends JpaRepository<Cliente, Long> {
}
