package com.zzz.fun.mybatis.common;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

/**
 * 添加分页
 */
@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}))
public class MybatisPagePlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        if (statementHandler.getParameterHandler().getParameterObject() instanceof Map) {
            processSql(statementHandler);
        }
        return invocation.proceed();
    }

    private void processSql(StatementHandler statementHandler) {
        ParameterHandler parameterHandler = statementHandler.getParameterHandler();
        Map<String, Object> parameterMap = (Map<String, Object>) parameterHandler.getParameterObject();
        MetaObject metaObject = MetaObject.forObject(statementHandler,
                SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,
                new DefaultReflectorFactory());
        String sql = statementHandler.getBoundSql().getSql().trim().toLowerCase();
        if (sql.endsWith(";")) {
            sql = sql.substring(0, sql.length() - 1);
        }
        String newSql = sql;
        // 添加分页参数
        if (!sql.contains("limit") && parameterMap.containsKey("pageNum") && parameterMap.containsKey("pageSize")) {
            int pageNum = (Integer) parameterMap.get("pageNum");
            int pageSize = (Integer) parameterMap.get("pageSize");
            newSql = newSql + " limit " + (pageNum - 1) * pageSize + "," + pageSize;
        }
        metaObject.setValue("delegate.boundSql.sql", newSql);
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
