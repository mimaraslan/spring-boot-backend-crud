package com.mimaraslan.repository;

import java.util.List;

import com.mimaraslan.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
  List<Client> findByConfirmed(boolean confirmed);

  List<Client> findByFirstNameContaining(String firstName);
}
