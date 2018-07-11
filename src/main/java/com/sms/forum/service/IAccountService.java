package com.sms.forum.service;

import com.sms.forum.model.Account;

import java.util.List;

public interface IAccountService {
    //    List<Account> getAccounts();
    List<Account> selectAllUser();

    Account getUserByPhoneOrEmail(String emailOrPhone, Short state);

    Account getUserById(Long userId);

    Account getAccountByUsername(String username);
}
