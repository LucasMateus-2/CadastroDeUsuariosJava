package com.tsoft.cadastro_usuario.insfrastructure.Repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tsoft.cadastro_usuario.insfrastructure.entitys.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>
{
   Optional<User> findByEmail(String Email);

   @Transactional
   void deleteByEmail(String Email);
}
