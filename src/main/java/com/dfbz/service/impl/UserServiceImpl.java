package com.dfbz.service.impl;

import com.dfbz.dao.UserDao;
import com.dfbz.dao.impl.UserDaoImpl;
import com.dfbz.pojo.User;
import com.dfbz.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao dao = new UserDaoImpl();

    @Override
    public boolean validateUser(User user) {
        return dao.validateUser(user);
    }
}
