package com.sms.forum.service.impl;

import com.sms.forum.dao.AccountDao;
import com.sms.forum.model.Account;
import com.sms.forum.service.IAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl implements IAccountService {

    @Resource
    private AccountDao accountDao;

    public List<Account> selectAllUser() {
//        return accountDao.selectAllAccount();
        return accountDao.selectAllUser();
    }

    public Account getUserByPhoneOrEmail(String emailOrPhone, Short state) {
        return accountDao.selectUserByPhoneOrEmail(emailOrPhone, state);
    }

    public Account getUserById(Long userId) {
        return accountDao.selectUserById(userId);
    }

    public Account getAccountByUsername(String username) {
        return accountDao.selectAccountByUsername(username);
    }
}
