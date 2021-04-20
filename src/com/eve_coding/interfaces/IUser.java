package com.eve_coding.interfaces;

import com.eve_coding.model.User;

public interface IUser {

	boolean registerUser(User user);

	User getUserLogin(String email, String password);
}
