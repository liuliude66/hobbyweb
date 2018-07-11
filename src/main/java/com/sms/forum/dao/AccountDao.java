package com.sms.forum.dao;

import com.sms.forum.model.Account;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDao {
    //List<Account> selectAllAccount();
    //
    List<Account> selectAllUser();

    Account selectUserById(@Param("userId") Long userId);

    Account selectAccountByUsername(@Param("username") String username);

    Account selectUserByPhoneOrEmail(@Param("emailOrPhone") String emailOrPhone, @Param("state") Short state);
}
