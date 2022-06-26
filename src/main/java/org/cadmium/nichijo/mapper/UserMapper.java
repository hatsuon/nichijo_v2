package org.cadmium.nichijo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.cadmium.nichijo.entity.User;
import org.cadmium.nichijo.entity.dto.LoginDto;

@Mapper
public interface UserMapper {

    User selectByUsernameAndPassword(LoginDto dto);

}
