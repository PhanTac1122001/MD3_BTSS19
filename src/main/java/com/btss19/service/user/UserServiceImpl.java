package com.btss19.service.user;

import com.btss19.model.dao.user.UserDao;
import com.btss19.model.dto.UserLoginDTO;
import com.btss19.model.entity.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private HttpSession session;
    @Override
    public boolean register(User user) {
        return userDao.register(user);
    }

    @Override
    public User getCurrentUser() {
        return (User) session.getAttribute("userLogin");
    }

    @Override
    public User login(UserLoginDTO userLoginDTO) {
        User user=userDao.fingUserByEmail(userLoginDTO.getEmail());
        if (user !=null){
            if(BCrypt.checkpw(userLoginDTO.getPassword(),user.getPassWord())){

                return user;
            }
        }

        return null;
    }
}
