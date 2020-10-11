package com.zzz.fun.mybatis.generate2;

import org.springframework.util.Assert;

import java.io.*;
import java.sql.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MybatisGenerator {

    private static final String PO_TEMPLATE_FILE_PATH = "generate2/PoTemplate.txt";
    private static final String DAO_TEMPLATE_FILE_PATH = "generate2/DaoTemplate.txt";
    private static final String MAPPER_TEMPLATE_FILE_PATH = "generate2/MapperTemplate.txt";

    private static final String DEFAULT_URL = "jdbc:mysql://127.0.0.1:3306/test?useSSL=false";
    private static final String DEFAULT_USERNAME = "root";
    private static final String DEFAULT_PW = "";
    private static final String DEFAULT_BASE_PACKAGE = "com.zzz.learn.mybatis.generate2";

    private String url = DEFAULT_URL;
    private String username = DEFAULT_USERNAME;
    private String password = DEFAULT_PW;
    private String basePackage = DEFAULT_BASE_PACKAGE;
    private ResultSetMetaData struct;

    private String tableName;
    private String poTemplate;
    private String daoTemplate;
    private String mapperTemplate;
    private static boolean isFirstUse = true;

    public static MybatisGenerator create() throws Exception {
        Assert.isTrue(isFirstUse, "Generator use up");
        isFirstUse = false;
        MybatisGenerator generator = new MybatisGenerator();
        generator.poTemplate = getResourceString(PO_TEMPLATE_FILE_PATH);
        generator.daoTemplate = getResourceString(DAO_TEMPLATE_FILE_PATH);
        generator.mapperTemplate = getResourceString(MAPPER_TEMPLATE_FILE_PATH);
        return generator;
    }

    public MybatisGenerator setURL(final String url) {
        Assert.hasLength(url, "url");
        this.url = url;
        return this;
    }

    public MybatisGenerator setUsername(final String username) {
        Assert.hasLength(username, "username");
        this.username = username;
        return this;
    }

    public MybatisGenerator setPassword(final String password) {
        this.password = password;
        return this;
    }

    public MybatisGenerator setBasePackage(final String basePackage) {
        Assert.hasLength(basePackage, "basePackage");
        this.basePackage = basePackage;
        return this;
    }

    public MybatisGenerator setTableName(String tableName) {
        Assert.hasLength(tableName, "tableName");
        this.tableName = tableName;
        return this;
    }

    public void generate() throws Exception {
        Assert.hasLength(this.tableName, "tableName");
        String poName = getCamelName(tableName, true);
        String daoName = poName + "Dao";
        Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
        Assert.isTrue(connection.isValid(100), "bad connection");
        String testSQL = String.format("SELECT  * FROM %s WHERE 1=0", this.tableName);
        ResultSet rs = connection.createStatement().executeQuery(testSQL);
        this.struct = rs.getMetaData();
        System.out.println(daoName + "|" + poName + "|" + struct.getColumnCount());
        for (int i = 1; i <= struct.getColumnCount(); i++) {
            System.out.print(struct.getColumnName(i) + "\t");
            System.out.print(struct.getColumnTypeName(i) + "\t");
            System.out.print(struct.getColumnDisplaySize(i) + "\t");
            System.out.print(struct.getColumnClassName(i) + "\t");
            System.out.println();
        }
        this.daoTemplate = this.daoTemplate.replaceAll(BASE_PKG, this.basePackage);
        this.daoTemplate = this.daoTemplate.replaceAll(PO_NAME, poName);
        this.daoTemplate = this.daoTemplate.replaceAll(DAO_NAME, daoName);
        createFile(daoName + ".java", this.daoTemplate);
        this.poTemplate = this.poTemplate.replaceAll(BASE_PKG, this.basePackage);
        this.poTemplate = this.poTemplate.replaceAll(PO_NAME, poName);
        this.poTemplate = this.poTemplate.replaceAll(IMPORTS, imports());
        this.poTemplate = this.poTemplate.replaceAll(FIELDS, fields());
        this.poTemplate = this.poTemplate.replaceAll(METHODS, methods());
        createFile(poName + ".java", this.poTemplate);
        this.mapperTemplate = this.mapperTemplate.replaceAll(BASE_PKG, this.basePackage);
        this.mapperTemplate = this.mapperTemplate.replaceAll(DAO_NAME, daoName);
        this.mapperTemplate = this.mapperTemplate.replaceAll(PO_NAME, poName);
        this.mapperTemplate = this.mapperTemplate.replaceAll(TABLE_NAME, this.tableName);
        this.mapperTemplate = this.mapperTemplate.replaceAll(BASE_COLUMN_LIST, baseColumnList());
        this.mapperTemplate = this.mapperTemplate.replaceAll(SELECTIVE_INSERT_LIST, selectiveInsertList());
        this.mapperTemplate = this.mapperTemplate.replaceAll(SELECTIVE_INSERT_VALUES, selectiveInsertValues());
        this.mapperTemplate = this.mapperTemplate.replaceAll(BASE_UPDATE_VALUES, baseUpdateValues());
        this.mapperTemplate = this.mapperTemplate.replaceAll(BASE_SELECT_CONDITIONS, baseSelectConditions());
        createFile(poName + "Mapper.xml", this.mapperTemplate);
        System.out.println("complete!");
    }

    private static Set<String> EXCLUSIONS = new HashSet<>(Arrays.asList("id", "gmt_created", "gmt_modified"));
    private static final String BASE_PKG = "%BASEPACKAGE%";
    private static final String DAO_NAME = "%DAONAME%";
    private static final String TABLE_NAME = "%TABLENAME%";
    private static final String BASE_COLUMN_LIST = "%BASE_COLUMN_LIST%";
    private static final String SELECTIVE_INSERT_LIST = "%SELECTIVE_INSERT_LIST%";
    private static final String SELECTIVE_INSERT_VALUES = "%SELECTIVE_INSERT_VALUES%";
    private static final String BASE_UPDATE_VALUES = "%BASE_UPDATE_VALUES%";
    private static final String BASE_SELECT_CONDITIONS = "%BASE_SELECT_CONDITIONS%";
    private static final String PO_NAME = "%PONAME%";
    private static final String IMPORTS = "%IMPORTS%";
    private static final String FIELDS = "%FIELDS%";
    private static final String METHODS = "%METHODS%";


    private String imports() throws SQLException {
        StringBuilder imp = new StringBuilder();
        for (int i = 1; i <= this.struct.getColumnCount(); i++) {
            String underName = this.struct.getColumnName(i);
            if (EXCLUSIONS.contains(underName)) {
                continue;
            }
            String className = this.struct.getColumnClassName(i);
            if (!className.startsWith("java.lang.")) {
                imp.append("import ").append(className).append(";\n");
            }
        }
        return imp.toString();
    }

    private String fields() throws SQLException {
        StringBuilder fields = new StringBuilder();
        for (int i = 1; i <= this.struct.getColumnCount(); i++) {
            String underName = this.struct.getColumnName(i);
            if (EXCLUSIONS.contains(underName)) {
                continue;
            }
            String className = this.struct.getColumnClassName(i);
            int index = className.lastIndexOf(".");
            if (index != -1) {
                className = className.substring(index + 1);
            }
            fields.append("\t").append("private ").append(className).append(" ").append(getCamelName(underName, false)).append(";\n");
        }
        return fields.toString();
    }

    private String methods() throws Exception {
        StringBuilder methods = new StringBuilder();
        for (int i = 1; i <= this.struct.getColumnCount(); i++) {
            String underName = this.struct.getColumnName(i);
            if (EXCLUSIONS.contains(underName)) {
                continue;
            }
            String lowerName = getCamelName(underName, false);
            String upperName = getCamelName(underName, true);
            String className = this.struct.getColumnClassName(i);
            int index = className.lastIndexOf(".");
            if (index != -1) {
                className = className.substring(index + 1);
            }
            methods.append("\t").append("public ").append(className).append(" ").append("get").append(upperName).
                    append("() ").append("{ return ").append(lowerName).append("; }\n");
            methods.append("\t").append("public void set").append(upperName).append("(").append(className).
                    append(" ").append(lowerName).append(") ").append("{ this.").append(lowerName).append(" = ").append(lowerName).append("; }\n");
            methods.append("\n");
        }
        return methods.toString();
    }


    private String baseColumnList() throws Exception {
        StringBuilder baseColumnList = new StringBuilder();
        for (int i = 1; i <= this.struct.getColumnCount(); i++) {
            String underName = this.struct.getColumnName(i);
            String lowerName = getCamelName(underName, false);
            baseColumnList.append("\t\t").append(underName).append(" AS ").append(lowerName);
            if (i < this.struct.getColumnCount()) {
                baseColumnList.append(",\n");
            }
        }
        return baseColumnList.toString();
    }

    private String selectiveInsertList() throws Exception {
        StringBuilder selectiveInsertList = new StringBuilder();
        for (int i = 1; i <= this.struct.getColumnCount(); i++) {
            String underName = this.struct.getColumnName(i);
            String lowerName = getCamelName(underName, false);
            if (!EXCLUSIONS.contains(underName)) {
                boolean isClassString = "java.lang.String".equals(this.struct.getColumnClassName(i));
                selectiveInsertList.append("\t\t").append("<if test=\"").append(lowerName).append("!=null ");
                if (isClassString) {
                    selectiveInsertList.append("and ").append(lowerName).append("!=''\">\n");
                } else {
                    selectiveInsertList.append("\">\n");
                }
                selectiveInsertList.append("\t\t\t").append(underName).append(",\n");
                selectiveInsertList.append("\t\t</if>\n");
            }
        }
        return selectiveInsertList.toString();
    }

    private String selectiveInsertValues() throws Exception {
        StringBuilder selectiveInsertValues = new StringBuilder();
        for (int i = 1; i <= this.struct.getColumnCount(); i++) {
            String underName = this.struct.getColumnName(i);
            String lowerName = getCamelName(underName, false);
            if (!EXCLUSIONS.contains(underName)) {
                boolean isClassString = "java.lang.String".equals(this.struct.getColumnClassName(i));
                selectiveInsertValues.append("\t\t").append("<if test=\"").append(lowerName).append("!=null ");
                if (isClassString) {
                    selectiveInsertValues.append(" and ").append(lowerName).append("!=''\">\n");
                } else {
                    selectiveInsertValues.append("\">\n");
                }
                selectiveInsertValues.append("\t\t\t#{").append(lowerName).append("},\n");
                selectiveInsertValues.append("\t\t</if>\n");
            }
        }
        return selectiveInsertValues.toString();
    }

    private String baseUpdateValues() throws Exception {
        StringBuilder baseUpdateValues = new StringBuilder();
        for (int i = 1; i <= this.struct.getColumnCount(); i++) {
            String underName = this.struct.getColumnName(i);
            String lowerName = getCamelName(underName, false);
            if (!EXCLUSIONS.contains(underName)) {
                boolean isClassString = "java.lang.String".equals(this.struct.getColumnClassName(i));
                baseUpdateValues.append("\t\t").append("<if test=\"record.").append(lowerName).append("!=null");
                if (isClassString) {
                    baseUpdateValues.append(" and record.").append(lowerName).append("!=''\">\n");
                } else {
                    baseUpdateValues.append("\">\n");
                }
                baseUpdateValues.append("\t\t\t").append(underName).append(" = #{record.").append(lowerName).append("},\n");
                baseUpdateValues.append("\t\t</if>\n");
            }
        }
        return baseUpdateValues.toString();
    }


    private String baseSelectConditions() throws Exception {
        StringBuilder baseSelectConditions = new StringBuilder();
        for (int i = 1; i <= this.struct.getColumnCount(); i++) {
            String underName = this.struct.getColumnName(i);
            String lowerName = getCamelName(underName, false);
            String className = this.struct.getColumnClassName(i);
            if (!EXCLUSIONS.contains(underName) || "id".equals(underName)) {
            boolean isClassString = "java.lang.String".equals(className);
                baseSelectConditions.append("\t\t").append("<if test=\"condition.").append(lowerName).append("!=null");
                if (isClassString) {
                    baseSelectConditions.append(" and condition.").append(lowerName).append("!=''\">\n");
                } else {
                    baseSelectConditions.append("\">\n");
                }
                baseSelectConditions.append("\t\t\tAND ").append(underName).append(" = #{condition.").append(lowerName).append("} \n");
                baseSelectConditions.append("\t\t</if>\n");
            }
        }
        return baseSelectConditions.toString();
    }

    private void createFile(String fileName, String template) throws IOException {
        File target = new File(fileName);
        boolean result = target.createNewFile();
        if (result) {
            //do nothing just for sonar
        }
        try (FileWriter fw = new FileWriter(target)) {
            fw.write(template);
            fw.flush();
        }
        System.out.println(fileName);
    }

    private static String getResourceString(String path) throws IOException {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        assert in != null;
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

    private static String getCamelName(String text, Boolean firstCap) {
        boolean cap = firstCap;
        StringBuilder sb = new StringBuilder();
        for (Character c : text.toCharArray()) {
            String now = String.valueOf(c);
            if ("_".equals(now)) {
                cap = true;
            } else {
                if (cap) {
                    sb.append(now.toUpperCase());
                    cap = false;
                } else {
                    sb.append(now.toLowerCase());
                }
            }
        }
        return sb.toString();
    }


}
