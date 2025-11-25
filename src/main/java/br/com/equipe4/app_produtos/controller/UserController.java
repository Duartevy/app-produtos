package br.com.equipe4.app_produtos.controller;

import br.com.equipe4.app_produtos.controller.utils.UriUtils;
import br.com.equipe4.app_produtos.service.UserService;
import br.com.equipe4.app_produtos.service.dto.UserResponseDTO;
import br.com.equipe4.app_produtos.service.dto.request.UserRequestDTO;
import br.com.equipe4.app_produtos.service.dto.response.UsuarioResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO userRequestDto) {

        UserResponseDTO createdUser = userService.createUser(userRequestDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.id())
                .toUri();

        return ResponseEntity.created(uri).body(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listUsers() {

        return ResponseEntity.ok(userService.listUsers());
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateUser(@PathVariable String id, @RequestBody UserRequestDTO userRequestDto) {
        userService.updateUser(id, userRequestDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
