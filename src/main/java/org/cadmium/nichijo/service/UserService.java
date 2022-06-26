package org.cadmium.nichijo.service;

import org.cadmium.nichijo.entity.User;
import org.cadmium.nichijo.entity.dto.LoginDto;
import org.cadmium.nichijo.mapper.UserMapper;

public interface UserService {

    User find(LoginDto dto);

}
