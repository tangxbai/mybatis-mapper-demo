package com.viiyue.mapper.demo.utils;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

import com.viiyue.plugins.mybatis.utils.LoggerUtil;

public class Printer {

	private static final StopWatch monitor = new StopWatch();

	public static void print( String namespace, String methodName, Tester tester ) {
		monitor.reset();
		monitor.start();
		
		Object result = tester.doTest();
		Log log = LogFactory.getLog( namespace + ( methodName == null ? "" : "." + methodName ) );
		log.debug( LoggerUtil.dividingLine );
		if ( result == null ) {
			log.debug( "<== --- Result: null" );
		} else if ( result instanceof BigDecimal ) {
			log.debug( "<== --- Result: " + ( ( BigDecimal ) result ).toString() );
		} else if ( result instanceof List ) {
			for ( Object item : ( List<?> ) result ) {
				log.debug( "<== --- Result: " + ToStringBuilder.reflectionToString( item, ToStringStyle.JSON_STYLE ) );
			}
		} else {
			log.debug( "<== --- Result: " + result );
		}
		log.debug( "==> ----- Time: " + LoggerUtil.getWatchTime( monitor ) );
		log.debug( LoggerUtil.dividingLine + "\n" );
	}

}
