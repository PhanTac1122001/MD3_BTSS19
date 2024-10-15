package com.btss19.model.dao.user;

import com.btss19.model.entity.User;

public interface UserDao {

    boolean register(User user);

    User fingUserByEmail(String email);
}
