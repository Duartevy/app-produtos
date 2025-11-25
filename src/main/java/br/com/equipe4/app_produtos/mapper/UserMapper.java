package br.com.equipe4.app_produtos.mapper;

import br.com.equipe4.app_produtos.model.User;
import br.com.equipe4.app_produtos.service.dto.UserResponseDTO;
import br.com.equipe4.app_produtos.service.dto.request.UserRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true) 
    @Mapping(target = "username", source = "email") 
    User toEntity(UserRequestDTO dto);

    
    @Mapping(target = "login", source = "username") 
    UserResponseDTO toResponseDto(User entity);
}
