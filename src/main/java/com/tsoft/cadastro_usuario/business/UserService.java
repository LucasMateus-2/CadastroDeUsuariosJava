package com.tsoft.cadastro_usuario.business;

import com.tsoft.cadastro_usuario.insfrastructure.Dtos.UserDto;
import org.hibernate.dialect.function.array.JsonArrayViaElementArgumentReturnTypeResolver;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.tsoft.cadastro_usuario.insfrastructure.Repositorys.UserRepository;
import com.tsoft.cadastro_usuario.insfrastructure.entitys.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService
{
   private final UserRepository repository;

   public UserService(UserRepository repository)
   {
      super();
      this.repository = repository;
   }

   public Set<User> getAllUsers()
   {
      List<User> listUsers = repository.findAll();
      
      Set<User> listUsersSet = new HashSet<>(listUsers);
      return listUsersSet;
   }

   public void saveUser(UserDto dto)
   {
      var user = new User();
      BeanUtils.copyProperties(dto, user);
      repository.saveAndFlush(user);
   }

   public User findUserByEmail(String email)
   {
      return repository.findByEmail(email).orElseThrow(

            () -> new RuntimeException("Email não encontrado"));
   }

   public void deleteUserbyEmail(String Email)
   {
      repository.deleteByEmail(Email);
   }

   public void updateUserById(Integer id, UserDto user)
   {
      var userNdto = new User();
      BeanUtils.copyProperties(user, userNdto);
      User entityUser = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
      User updatedUser = User.builder().email(user.email() != null ? user.email() : entityUser.getEmail()).name(user.name() != null ? user.name() : entityUser.getName()).id(entityUser.getId()).build();
      repository.saveAndFlush(updatedUser);
   }

}
