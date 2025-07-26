package com.tsoft.cadastro_usuario.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tsoft.cadastro_usuario.business.UserService;
import com.tsoft.cadastro_usuario.insfrastructure.Dtos.UserDto;
import com.tsoft.cadastro_usuario.insfrastructure.entitys.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UserController
{
   private final UserService userService;

   @GetMapping("/All")
   public ResponseEntity getAll()
   {
      var listUsersSet = userService.getAllUsers();
      return ResponseEntity.ok().body(listUsersSet);
   }

   @PostMapping
   public ResponseEntity<Void> saveUser(@RequestBody UserDto user)
   {
      userService.saveUser(user);
      return ResponseEntity.ok().build();
   }

   @GetMapping("/GetEmail")
   public ResponseEntity<User> findUserByEmail(@RequestParam String email)
   {
      return ResponseEntity.ok(userService.findUserByEmail(email));
   }

   @DeleteMapping
   public ResponseEntity<Void> deleteUserByEmail(@RequestBody String email)
   {
      return ResponseEntity.ok().build();
   }

   @PutMapping
   public ResponseEntity<Void> updateUserBYId(@RequestParam Integer id, @RequestBody UserDto userDto)
   {
      userService.updateUserById(id, userDto);
      return ResponseEntity.ok().build();

   }
}
