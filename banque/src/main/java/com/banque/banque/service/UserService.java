package com.banque.banque.service;

import com.banque.banque.model.User;

public interface UserService {
    User findByEmail(String email);
    User findById(int id);
    User save(User registration);
    User update(String email, User registration);
}
