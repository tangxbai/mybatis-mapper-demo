package com.viiyue.mapper.demo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.viiyue.mapper.demo.enums.Gender;
import com.viiyue.mapper.demo.mapper.AccountMapper;
import com.viiyue.mapper.demo.model.Account;
import com.viiyue.mapper.demo.utils.Printer;
import com.viiyue.plugins.mybatis.MyBatisMapperFactoryBuilder;
import com.viiyue.plugins.mybatis.condition.Example;

public class App {
	
	static final String NICK_NAME = "唐小白";
	static final String LOGIN_NAME = "tangxbai";
	static final String EMAIL = "tangxbai@hotmail.com";
	
	static void runBaseDeleteMapper( AccountMapper mapper, String namespace ) {
		Printer.print( namespace, "delete", () -> mapper.delete( Account.builder().loginName( LOGIN_NAME ).build() ) );
		Printer.print( namespace, "deleteByPrimaryKey", () -> mapper.deleteByPrimaryKey( 1L ) );
		Printer.print( namespace, "deleteByPrimaryKeyGroup", () -> mapper.deleteByPrimaryKeyGroup( 1L, 2L, 3L, 4L ) );
		Printer.print( namespace, "deleteByPrimaryKeyIndex", () -> mapper.deleteByPrimaryKeyIndex( 0, 1L ) );
		Printer.print( namespace, "deleteByPrimaryKeyIndexGroup", () -> mapper.deleteByPrimaryKeyIndexGroup( 0, 1L, 2L, 3L, 4L ) );
		Printer.print( namespace, "deleteAll", () -> mapper.deleteAll() );
	}
	
	static void runBaseInsertMapper( AccountMapper mapper, String namespace ) {
		// #insert
		Account account = new Account();
		account.setNickName( NICK_NAME );
		account.setLoginName( LOGIN_NAME );
		account.setPassword( EMAIL );
		account.setGender( Gender.MALE );
		account.setAge( 25 );
		account.setDisplay( true );
		Printer.print( namespace, "insert", () -> mapper.insert( account ) );
		
		// #insertBySelective
		Account account2 = new Account();
		account2.setNickName( NICK_NAME );
		account2.setLoginName( LOGIN_NAME + "007" );
		account2.setPassword( EMAIL );
		account2.setRemark( "A java programmer" );
		account2.setAge( 25 );
		account2.setDisplay( false );
		Printer.print( namespace, "insertBySelective", () -> mapper.insertBySelective( account2 ) );
	}
	
	static void runBaseExampleMapper( AccountMapper mapper, String namespace ) {
		Printer.print( namespace, "selectByExample", () -> mapper.selectByExample( Example.query( Account.class ).eq( "id", 1L ).condition( "login_name = #{loginName}", "tangxbai" ).limit( 20, 20 ) ) );
		Printer.print( namespace, "selectByExample", () -> mapper.selectByExample( Example.select( Account.class ).includes( "id", "loginName", "password" ).when().eq( "age", 25 ) ) );
		Printer.print( namespace, "updateByExample", () -> mapper.updateByExample( Example.update( Account.class ).set( "loginName", "password" ).values( "zhangsan", "Aa123456" ) ) );
		Printer.print( namespace, "updateByExample", () -> mapper.updateByExample( Example.update( Account.class ).set( "loginName", "password" ).values( "zhangsan", "Aa123456" ).when().eq( "age", 25 ) ) );
		Printer.print( namespace, "updateByExample", () -> mapper.updateByExample( Example.update( Account.class ).bind( Account.builder().remark( "A java programmer of 007 ..." ).build() ).when().eq( "id", 1L ) ) );
//		Printer.print( namespace, "deleteByExample", () -> mapper.deleteByExample( Example.query( Account.class ).eq( "age", 25 ) ) );
	}
	
	static void runBaseSelectLimitMapper( AccountMapper mapper, String namespace ) {
		Printer.print( namespace, "selectByLimit", () -> mapper.selectByLimit( 0, 10 ) );
	}
	
	static void runBaseSelectMapper( AccountMapper mapper, String namespace ) {
		Printer.print( namespace, "select", () -> mapper.select( Account.builder().loginName( LOGIN_NAME ).build() ) );
		Printer.print( namespace, "selectAll", () -> mapper.selectAll() );
		Printer.print( namespace, "selectByPrimaryKey", () -> mapper.selectByPrimaryKey( 1L ) );
		Printer.print( namespace, "selectByPrimaryKeyGroup", () -> mapper.selectByPrimaryKeyGroup( 1L, 2L, 3L, 4L ) );
		Printer.print( namespace, "selectByPrimaryKeyIndex", () -> mapper.selectByPrimaryKeyIndex( 0, 1L ) );
		Printer.print( namespace, "selectByPrimaryKeyIndexGroup", () -> mapper.selectByPrimaryKeyIndexGroup( 0, 1L, 2L, 3L, 4L ) );
	}
	
	static void runBaseUpdateMapper( AccountMapper mapper, String namespace ) {
		Account account = new Account();
		account.setId( 1L );
		account.setLoginName( LOGIN_NAME );
		account.setVersion( 1L );
		account.setRemark( "UPDATED" );
		Printer.print( namespace, "updateByPrimaryKey", () -> mapper.updateByPrimaryKey( account ) );
		Printer.print( namespace, "updateByPrimaryKeySelective", () -> mapper.updateByPrimaryKeySelective( account ) );
		Printer.print( namespace, "updateByPrimaryKeyIndex", () -> mapper.updateByPrimaryKeyIndex( 0, account ) );
		Printer.print( namespace, "updateByPrimaryKeyIndexSelective", () -> mapper.updateByPrimaryKeyIndexSelective( 0, account ) );
	}
	
	static void runAggregateFunctionMapper( AccountMapper mapper, String namespace ) {
		Printer.print( namespace, "selectStatisticByAggregateFunction", () -> mapper.selectStatisticByAggregateFunction( Example.count( Account.class, "*" ).when().eq( "gender", Gender.FEMALE ).orderBy( "createTime" ) ) );
		Printer.print( namespace, "selectStatisticListByAggregateFunction", () -> mapper.selectStatisticListByAggregateFunction( Example.summation( Account.class, "age", "version" ).groupBy( "gender" ).orderBy( "createTime", false ) ) );
	}
	
	static void runForUpdateMapper( AccountMapper mapper, String namespace ) {
		Printer.print( namespace, "selectForUpdate", () -> mapper.selectForUpdate( Account.builder().loginName( LOGIN_NAME ).build() ) );
		Printer.print( namespace, "selectByPrimaryKeyForUpdate", () -> mapper.selectByPrimaryKeyForUpdate( 1L ) );
		Printer.print( namespace, "selectByPrimaryKeyGroupForUpdate", () -> mapper.selectByPrimaryKeyGroupForUpdate( 1L, 2L, 3L, 4L ) );
		Printer.print( namespace, "selectByPrimaryKeyIndexForUpdate", () -> mapper.selectByPrimaryKeyIndexForUpdate( 0, 1L ) );
		Printer.print( namespace, "selectByPrimaryKeyIndexGroupForUpdate", () -> mapper.selectByPrimaryKeyIndexGroupForUpdate( 0, 1L, 2L, 3L, 4L ) );
	}
	
	static void runLogicallyDeleteMapper( AccountMapper mapper, String namespace ) {
		Printer.print( namespace, "logicallyDelete", () -> mapper.logicallyDelete( Account.builder().loginName( LOGIN_NAME ).build() ) );
		Printer.print( namespace, "logicallyDeleteAll", () -> mapper.logicallyDeleteAll() );
		Printer.print( namespace, "logicallyDeleteByPrimaryKey", () -> mapper.logicallyDeleteByPrimaryKey( 1L ) );
		Printer.print( namespace, "logicallyDeleteByPrimaryKeyGroup", () -> mapper.logicallyDeleteByPrimaryKeyGroup( 1L, 2L, 3L, 4L ) );
		Printer.print( namespace, "logicallyDeleteByPrimaryKeyIndex", () -> mapper.logicallyDeleteByPrimaryKeyIndex( 0, 1L ) );
		Printer.print( namespace, "logicallyDeleteByPrimaryKeyIndexGroup", () -> mapper.logicallyDeleteByPrimaryKeyIndexGroup( 0, 1L, 2L, 3L, 4L ) );
	}
	
	static void runRecycleBinMapper( AccountMapper mapper, String namespace ) {
		Printer.print( namespace, "selectAllDeleted", () -> mapper.selectAllDeleted() );
		Printer.print( namespace, "restore", () -> mapper.restore( Account.builder().loginName( LOGIN_NAME ).build() ) );
		Printer.print( namespace, "restoreAllDeleted", () -> mapper.restoreAllDeleted() );
		Printer.print( namespace, "restoreByPrimaryKey", () -> mapper.restoreByPrimaryKey( 80358911527006208L ) );
		Printer.print( namespace, "restoreByPrimaryKeyGroup", () -> mapper.restoreByPrimaryKeyGroup( 1L, 2L, 3L, 4L ) );
		Printer.print( namespace, "restoreByPrimaryKeyIndex", () -> mapper.restoreByPrimaryKeyIndex( 0, 1L ) );
		Printer.print( namespace, "restoreByPrimaryKeyIndexGroup", () -> mapper.restoreByPrimaryKeyIndexGroup( 0, 1L, 2L, 3L, 4L ) );
	}
	
	static void runAccountMapper( AccountMapper mapper, String namespace ) {
		Printer.print( namespace, "selectByLoginName", () -> mapper.selectByLoginName( "tangxbai" ) );
	}
	
	public static void main( String [] args ) throws Exception {
		
		SqlSessionFactory factory = new MyBatisMapperFactoryBuilder().build( Resources.getResourceAsStream( "mybatis.xml" ) );
		SqlSession session = factory.openSession();
		
		Class<AccountMapper> mapperType = AccountMapper.class;
		String namespace = mapperType.getName();
		AccountMapper mapper = session.getMapper( mapperType );
		
		runBaseDeleteMapper( mapper, namespace );
		runBaseInsertMapper( mapper, namespace );
		runBaseExampleMapper( mapper, namespace );
		runBaseSelectLimitMapper( mapper, namespace );
		runBaseSelectMapper( mapper, namespace );
		runBaseUpdateMapper( mapper, namespace );
		runAggregateFunctionMapper( mapper, namespace );
		runForUpdateMapper( mapper, namespace );
		runLogicallyDeleteMapper( mapper, namespace );
		runRecycleBinMapper( mapper, namespace );
		runAccountMapper( mapper, namespace );
		
		session.commit();
	}
	
}
