/*
 * 文件名：ProductProvider.java 修改人：zhangsitian 修改时间：2019年2月13日
 */

package com.zzz.learn.mybatis.dao.annotation;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.CollectionUtils;


public abstract class BaseProvider<T> {

    protected static final String conditionName = "condition";

    protected static final String recordName = "record";

    protected static final String comma = ",";

    protected Class<T> genericClass;

    protected String tableName;

    protected Map<String, String> underlinFieldNameMap;

    protected String baseSelectColumn;

    @SuppressWarnings("unchecked")
    public BaseProvider() {
        genericClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        tableName = getTableName();
        underlinFieldNameMap = getUnderlineFieldNameMap();
        baseSelectColumn = getBaseSelectColumn();
    }

    public String selectListByRecord(Map<String, Object> param) {
        @SuppressWarnings("unchecked")
        T condition = (T)param.get(conditionName);
        SQL sql = new SQL().SELECT(baseSelectColumn).FROM(tableName);
        Field[] fields = genericClass.getDeclaredFields();
        for (Field field : fields) {
            Object value = getValue(condition, field.getName());
            boolean isBlankStr = field.getType().equals(String.class) && "".equals(value);
            if (value == null || isBlankStr) {
                continue;
            }
            String fieldName = field.getName();
            String sqlFieldName = underlinFieldNameMap.get(fieldName);
            String whereStr = sqlFieldName + " = #{" + conditionName + "." + fieldName + "}";
            sql.WHERE(whereStr);
        }
        return sql.toString();
    }

    public String selectByRecord(Map<String, Object> param) {
        return selectListByRecord(param) + " LIMIT 0,1";
    }

    public String selectById(Map<String, Object> param) {
        SQL sql = new SQL().SELECT(baseSelectColumn).FROM(tableName);
        sql.WHERE("id = #{id}");
        return sql.toString();
    }

    public String selectByIds(Map<String, Object> param) {
        SQL sql = new SQL().SELECT(baseSelectColumn).FROM(tableName);
        Long[] values = (Long[])param.get("array");
        List<String> idList = new ArrayList<String>();
        for (int i = 0; i < values.length; i++ ) {
            idList.add(values[i].longValue() + "");
        }
        String whereStr = "id in (" + join(idList, " AND ") + ")";
        sql.WHERE(whereStr);
        System.out.println(sql.toString());
        return sql.toString();
    }

    public String updateById(Map<String, Object> param) {
        SQL sql = update(param);
        sql.WHERE(" id = #{" + recordName + ".id} ");
        System.out.println(sql.toString());
        return sql.toString();
    }

    public String updateByRecord(Map<String, Object> param) {
        @SuppressWarnings("unchecked")
        T condition = (T)param.get(conditionName);
        SQL sql = update(param);
        Field[] fields = genericClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++ ) {
            Field field = fields[i];
            Object value = getValue(condition, field.getName());
            boolean isBlankStr = field.getType().equals(String.class) && "".equals(value);
            if (value == null || isBlankStr) {
                continue;
            }
            String fieldName = field.getName();
            String sqlFieldName = underlinFieldNameMap.get(fieldName);
            sql.WHERE(sqlFieldName + " = #{condition." + fieldName + "}");
        }
        System.out.println(sql.toString());
        return sql.toString();
    }

    protected SQL update(Map<String, Object> param) {
        @SuppressWarnings("unchecked")
        T record = (T)param.get(recordName);
        SQL sql = new SQL().UPDATE(tableName);
        Field[] fields = genericClass.getDeclaredFields();
        List<String> setList = new ArrayList<String>();
        for (int i = 0; i < fields.length; i++ ) {
            Field field = fields[i];
            Object value = getValue(record, field.getName());
            boolean isBlankStr = field.getType().equals(String.class) && "".equals(value);
            if (value == null || isBlankStr) {
                continue;
            }
            String fieldName = field.getName();
            String sqlFieldName = underlinFieldNameMap.get(fieldName);
            if ("id".equals(fieldName)) {
                continue;
            }
            setList.add(sqlFieldName + " = #{" + recordName + "." + fieldName + "}");
        }
        String setStr = join(setList, comma);
        sql.SET(setStr);
        return sql;
    }

    public String insert(Map<String, Object> param) {
        @SuppressWarnings("unchecked")
        T record = (T)param.get(recordName);
        List<String> columnsList = new ArrayList<String>();
        List<String> valuesList = new ArrayList<String>();
        Field[] fields = genericClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++ ) {
            Field field = fields[i];
            Object value = getValue(record, field.getName());
            boolean isBlankStr = field.getType().equals(String.class) && "".equals(value);
            if (value == null || isBlankStr) {
                continue;
            }
            String fieldName = field.getName();
            String sqlFieldName = underlinFieldNameMap.get(fieldName);
            columnsList.add(sqlFieldName);
            valuesList.add("#{" + recordName + "." + fieldName + "}");
        }
        String columns = join(columnsList, comma);
        String values = join(valuesList, comma);
        SQL sql = new SQL().INSERT_INTO(tableName).INTO_COLUMNS(columns).INTO_VALUES(values);
        System.out.println(sql.toString());
        return sql.toString();
    }

    private static String join(List<String> list, String sep) {
        StringBuilder sb = new StringBuilder();
        if (!CollectionUtils.isEmpty(list)) {
            for (int i = 0; i < list.size(); i++ ) {
                sb.append(list.get(i));
                if (i != list.size() - 1) {
                    sb.append(sep);
                }
            }
        }
        return sb.toString();
    }

    protected static Object getValue(Object obj, String fieldName) {
        Object value = null;
        try {
            value = getValueByFieldName(obj, fieldName);
        }
        catch (Exception e) {
            // ignore
        }
        return value;
    }

    // 获取表名
    private String getTableName() {
        return genericClass.getSimpleName().toLowerCase();
    }

    // 获取下划线字段名map
    private Map<String, String> getUnderlineFieldNameMap() {
        Map<String, String> underlineFieldNameMap = new HashMap<String, String>();
        Field[] fields = genericClass.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            if ("orderBy".equals(fieldName)) {
                continue;
            }
            String underlineName = parseHumpToUnderline(fieldName);
            underlineFieldNameMap.put(fieldName, underlineName);
        }
        return underlineFieldNameMap;
    }

    // 获取select列名
    private String getBaseSelectColumn() {
        Field[] fields = genericClass.getDeclaredFields();
        List<String> selectList = new ArrayList<String>();
        for (Field field : fields) {
            String fieldName = field.getName();
            String underlineName = underlinFieldNameMap.get(fieldName);
            selectList.add(underlineName + " AS " + fieldName);
        }
        return join(selectList, comma);
    }

    /**
     * Description: 驼峰转下划线
     */
    private String parseHumpToUnderline(String para) {
        StringBuilder sb = new StringBuilder(para);
        int temp = 0;// 定位
        if (!para.contains("_")) {
            for (int i = 0; i < para.length(); i++ ) {
                if (Character.isUpperCase(para.charAt(i))) {
                    sb.insert(i + temp, "_");
                    temp += 1;
                }
            }
        }
        return sb.toString().toLowerCase();
    }

    /**
     * 获取obj对象fieldName的属性值
     */
    private static Object getValueByFieldName(Object obj, String fieldName)
        throws Exception {
        Field field = getFieldByFieldName(obj, fieldName);
        Object value = null;
        if (field != null) {
            if (field.isAccessible()) {
                value = field.get(obj);
            }
            else {
                field.setAccessible(true);
                value = field.get(obj);
                field.setAccessible(false);
            }
        }
        return value;
    }

    /**
     * 获取父类Field
     */
    private static Field getFieldByFieldName(Object obj, String fieldName) {
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredField(fieldName);
            }
            catch (NoSuchFieldException e) {}
        }
        return null;
    }

}
