package com.viiyue.mapper.demo.mapper;

import java.util.List;

import com.viiyue.mapper.demo.bean.AccountDTO;
import com.viiyue.mapper.demo.model.Account;
import com.viiyue.plugins.mybatis.annotation.mark.EnableResultMap;
import com.viiyue.plugins.mybatis.mapper.Mapper;

public interface AccountMapper extends Mapper<Account, AccountDTO, Long> {
	
	@EnableResultMap
	List<AccountDTO> selectByLoginName( String loginName );
	
}
