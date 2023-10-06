package com.experis.no.boxinator.mappers;


import com.experis.no.boxinator.models.User;
import com.experis.no.boxinator.models.dto.user.UserDTO;
import com.experis.no.boxinator.models.dto.user.UserPostDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public abstract UserDTO userToUserDTO(User user);

    public abstract User userPostDTOToUser(UserPostDTO userPostDTO);
    public abstract User userDTOToUser(UserDTO userDTO);


    public abstract Collection<UserDTO> userToUserDTO(Collection<User> userCollection);
}
