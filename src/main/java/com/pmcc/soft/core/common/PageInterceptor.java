package com.pmcc.soft.core.common;



import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

@Intercepts( {  
    @Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class}) })  
public class PageInterceptor implements Interceptor {
	private String databaseType ="sqlserver";//数据库类型，不同的数据库有不同的分页方法
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		//对于StatementHandler其实只有两个实现类，一个是RoutingStatementHandler，另一个是抽象类BaseStatementHandler，  
	       //BaseStatementHandler有三个子类，分别是SimpleStatementHandler，PreparedStatementHandler和CallableStatementHandler，  
	       //SimpleStatementHandler是用于处理Statement的，PreparedStatementHandler是处理PreparedStatement的，而CallableStatementHandler是  
	       //处理CallableStatement的。Mybatis在进行Sql语句处理的时候都是建立的RoutingStatementHandler，而在RoutingStatementHandler里面拥有一个  
	       //StatementHandler类型的delegate属性，RoutingStatementHandler会依据Statement的不同建立对应的BaseStatementHandler，即SimpleStatementHandler、  
	       //PreparedStatementHandler或CallableStatementHandler，在RoutingStatementHandler里面所有StatementHandler接口方法的实现都是调用的delegate对应的方法。  
	       //我们在PageInterceptor类上已经用@Signature标记了该Interceptor只拦截StatementHandler接口的prepare方法，又因为Mybatis只有在建立RoutingStatementHandler的时候  
	       //是通过Interceptor的plugin方法进行包裹的，所以我们这里拦截到的目标对象肯定是RoutingStatementHandler对象。  
	       RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();  
	       //通过反射获取到当前RoutingStatementHandler对象的delegate属性  
	       StatementHandler delegate = (StatementHandler)ReflectUtil.getFieldValue(handler, "delegate");
	       //获取到当前StatementHandler的 boundSql，这里不管是调用handler.getBoundSql()还是直接调用delegate.getBoundSql()结果是一样的，因为之前已经说过了  
	       //RoutingStatementHandler实现的所有StatementHandler接口方法里面都是调用的delegate对应的方法。  
	       BoundSql boundSql = handler.getBoundSql();  
	       //拿到当前绑定Sql的参数对象，就是我们在调用对应的Mapper映射语句时所传入的参数对象  
	       Object obj = boundSql.getParameterObject();  
	       //这里我们简单的通过传入的是Page对象就认定它是需要进行分页操作的。  
	       if (obj instanceof BaseExample) {  
	    	   BaseExample example = (BaseExample) obj;  
	    	   if(example.isPageSelect())
	    	   {
	    			//System.out.println("ORACLE分页设置");
		           //通过反射获取delegate父类BaseStatementHandler的mappedStatement属性  
		           MappedStatement mappedStatement = (MappedStatement)ReflectUtil.getFieldValue(delegate, "mappedStatement");  
		           //拦截到的prepare方法参数是一个Connection对象  
		           Connection connection = (Connection)invocation.getArgs()[0];  
		           //获取当前要执行的Sql语句，也就是我们直接在Mapper映射语句中写的Sql语句  
		           String sql = boundSql.getSql();  
		           System.out.println("转换前sql:"+sql.replaceAll("(\n +)+", "\n").replaceAll("\n,\n", ",\n")+"\n");
		           //给当前的page参数对象设置总记录数  
		          // this.setTotalRecord(page,  
		                //  mappedStatement, connection);  
		           //获取分页Sql语句  
		           String pageSql = this.getPageSql(example, sql);  
		           System.out.println("转换后sql:"+pageSql.replaceAll("(\n +)+", "\n").replaceAll("\n,\n", ",\n")+"\n");
		           //利用反射设置当前BoundSql对应的sql属性为我们建立好的分页Sql语句  
		           ReflectUtil.setFieldValue(boundSql, "sql", pageSql);  
	    	   }else{
	    		   MappedStatement mappedStatement = (MappedStatement)ReflectUtil.getFieldValue(delegate, "mappedStatement");  
		           //拦截到的prepare方法参数是一个Connection对象  
		           Connection connection = (Connection)invocation.getArgs()[0];  
		           //获取当前要执行的Sql语句，也就是我们直接在Mapper映射语句中写的Sql语句  
		           String sql = boundSql.getSql();  
		          // System.out.println("sql:"+sql.replaceAll("(\n +)+", "\n").replaceAll("\n,\n", ",\n")+"\n");
	    	   }
	       }else{
	    	   MappedStatement mappedStatement = (MappedStatement)ReflectUtil.getFieldValue(delegate, "mappedStatement");  
	           Connection connection = (Connection)invocation.getArgs()[0];  
	           String sql = boundSql.getSql();
	           //System.out.println("sql:"+sql.replaceAll("(\n +)+", "\n").replaceAll("\n,\n", ",\n")+"\n");
	       }
	       return invocation.proceed();  
	}

	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		 return Plugin.wrap(target, this);  
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		this.databaseType = properties.getProperty("databaseType");
	}
	/** 
     * 根据page对象获取对应的分页查询Sql语句，这里只做了两种数据库类型，Mysql和Oracle 
     * 其它的数据库都 没有进行分页 
     * 
     * @param page 分页对象 
     * @param sql 原sql语句 
     * @return 
     */  
    private String getPageSql(BaseExample example, String sql) {  
       StringBuffer sqlBuffer = new StringBuffer(sql);  
       if(sql.contains("select")&&!sql.contains("count("))
       {
	       if ("mysql".equalsIgnoreCase(databaseType)) {  
	           return getMysqlPageSql(example, sqlBuffer);  
	       } else if ("oracle".equalsIgnoreCase(databaseType)) {  
		           return getOraclePageSql(example, sqlBuffer);  
	       		}else if("sqlserver".equalsIgnoreCase(databaseType)){
	       			return getSqlServerPageSql(example, sqlBuffer);
	       		}
       }
       return sqlBuffer.toString();  
    }  
     
    /** 
     * 获取Mysql数据库的分页查询语句 
     * @param page 分页对象 
     * @param sqlBuffer 包含原sql语句的StringBuffer对象 
     * @return Mysql数据库分页语句 
     */  
    private String getMysqlPageSql(BaseExample example, StringBuffer sqlBuffer) {  
       //计算第一条记录的位置，Mysql中记录的位置是从0开始的。  
       //int offset = (page.getPageNo() - 1) * page.getPageSize();  
       sqlBuffer.append(" limit ").append(example.getVo().getStartIndex()).append(",").append(example.getVo().getRows());  
       return sqlBuffer.toString();  
    }  
     
    /** 
     * 获取Oracle数据库的分页查询语句 
     * @param page 分页对象 
     * @param sqlBuffer 包含原sql语句的StringBuffer对象 
     * @return Oracle数据库的分页查询语句 
     */  
    private String getOraclePageSql(BaseExample example, StringBuffer sqlBuffer) {  
       //计算第一条记录的位置，Oracle分页是通过rownum进行的，而rownum是从1开始的  
      // int offset = (page.getPageNo() - 1) * page.getPageSize() + 1;  
       sqlBuffer.insert(0, "select u.*, rownum r from (").append(") u where rownum <= ").append(example.getVo().getEndIndex());  
       sqlBuffer.insert(0, "select * from (").append(") where r > ").append(example.getVo().getStartIndex());  
       //上面的Sql语句拼接之后大概是这个样子：  
       //select * from (select u.*, rownum r from (select * from t_user) u where rownum < 31) where r >= 16  
       return sqlBuffer.toString();  
    }  
    /** 
     * 获取Oracle数据库的分页查询语句 
     * @param page 分页对象 
     * @param sqlBuffer 包含原sql语句的StringBuffer对象 
     * @return Oracle数据库的分页查询语句 
     */  
    private String getSqlServerPageSql(BaseExample example, StringBuffer sqlBuffer) {  
       //计算第一条记录的位置，Oracle分页是通过rownum进行的，而rownum是从1开始的  
      // int offset = (page.getPageNo() - 1) * page.getPageSize() + 1;  
    	String sql ="";
    	if("typeasc".equals(example.getVo().getStatus())){
    	sql = "select top " + example.getVo().getRows() + "* from (select row_number() over(order by b.type asc, convert(BIGINT,b."+example.getVo().getOrderField()+") desc) as rownumber,* from ("
        			+sqlBuffer.insert(6, " TOP 100 PERCENT ")+")b)a where rownumber > "+(example.getVo().getPage()-1)*example.getVo().getRows(); 
    	}else{
    		sql = "select top " + example.getVo().getRows() + "* from (select row_number() over(order by convert(BIGINT,b."+example.getVo().getOrderField()+") desc) as rownumber,* from ("
        			+sqlBuffer.insert(6, " TOP 100 PERCENT ")+")b)a where rownumber > "+(example.getVo().getPage()-1)*example.getVo().getRows(); 
    	}
    	
    	return sql;
    }  
    /** 
     * 根据原Sql语句获取对应的查询总记录数的Sql语句 
     * @param sql 
     * @return 
     */  
    private String getCountSql(String sql) {  
       int index = sql.indexOf("from");  
       return "select count(*) " + sql.substring(index);  
    }  
    
    /** 
     * 利用反射进行操作的一个工具类 
     * 
     */  
    private static class ReflectUtil {  
       /** 
        * 利用反射获取指定对象的指定属性 
        * @param obj 目标对象 
        * @param fieldName 目标属性 
        * @return 目标属性的值 
        */  
       public static Object getFieldValue(Object obj, String fieldName) {  
           Object result = null;  
           Field field = ReflectUtil.getField(obj, fieldName);  
           if (field != null) {  
              field.setAccessible(true);  
              try {  
                  result = field.get(obj);  
              } catch (IllegalArgumentException e) {  
                  // TODO Auto-generated catch block  
                  e.printStackTrace();  
              } catch (IllegalAccessException e) {  
                  // TODO Auto-generated catch block  
                  e.printStackTrace();  
              }  
           }  
           return result;  
       }  
        
       /** 
        * 利用反射获取指定对象里面的指定属性 
        * @param obj 目标对象 
        * @param fieldName 目标属性 
        * @return 目标字段 
        */  
       private static Field getField(Object obj, String fieldName) {  
           Field field = null;  
          for (Class<?> clazz=obj.getClass(); clazz != Object.class; clazz=clazz.getSuperclass()) {  
              try {  
                  field = clazz.getDeclaredField(fieldName);  
                  break;  
              } catch (NoSuchFieldException e) {  
                  //这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。  
              }  
           }  
           return field;  
       }  
   
       /** 
        * 利用反射设置指定对象的指定属性为指定的值 
        * @param obj 目标对象 
        * @param fieldName 目标属性 
         * @param fieldValue 目标值 
        */  
       public static void setFieldValue(Object obj, String fieldName,  
              String fieldValue) {  
           Field field = ReflectUtil.getField(obj, fieldName);  
           if (field != null) {  
              try {  
                  field.setAccessible(true);  
                  field.set(obj, fieldValue);  
              } catch (IllegalArgumentException e) {  
                  // TODO Auto-generated catch block  
                  e.printStackTrace();  
              } catch (IllegalAccessException e) {  
                  // TODO Auto-generated catch block  
                  e.printStackTrace();  
              }  
           }  
        }  
    }  
}
