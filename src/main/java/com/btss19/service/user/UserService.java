package com.btss19.service.user;

import com.btss19.model.dto.UserLoginDTO;
import com.btss19.model.entity.User;

public interface UserService {

    boolean register(User user);

    User login(UserLoginDTO userLoginDTO);

    User getCurrentUser();
}
