package com.eztech.springbase.common;

import com.eztech.springbase.entity.FieldAttribute;
import com.eztech.springbase.enums.FieldTypeEnums;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 代码生成器
 *
 * @author chenqinru
 * @date 2023/08/30
 */
@Service
public final class CodeGenerator {

    private static final String BASE_PATH_PREFIX = "src/main/java/";

    private final JdbcTemplate jdbcTemplate;

    public CodeGenerator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void generate(String basePath, String tableName, String className) {
        String entityPath = BASE_PATH_PREFIX + basePath + "/entity/";
        String basePackage = pathToPackage(basePath);
        generateEntity(entityPath, basePackage, tableName, className);

        String controllerPath = BASE_PATH_PREFIX + basePath + "/controller/";
        generateController(controllerPath, basePackage, className);

        String servicePath = BASE_PATH_PREFIX + basePath + "/service/";
        generateService(servicePath, basePackage, className);

        String serviceImplPath = BASE_PATH_PREFIX + basePath + "/service/impl/";
        generateServiceImpl(serviceImplPath, basePackage, className);

        String repositoryPath = BASE_PATH_PREFIX + basePath + "/repository/";
        generateRepository(repositoryPath, basePackage, className);
    }

    public void generateEntity(String entityPath, String basePackage, String tableName, String className) {
        String sql = "select column_name, column_type, column_comment from information_schema.columns " +
                "where table_name = '" + tableName + "' and table_schema = (select database())";
        Set<String> set = new HashSet<>();
        set.add("id");
        set.add("createTime");
        set.add("updateTime");
        set.add("createdTime");
        set.add("createdBy");
        set.add("updatedTime");
        set.add("updatedBy");
        set.add("isDeleted");
        set.add("remark");
        List<FieldAttribute> fieldAttributes = jdbcTemplate.queryForList(sql)
                .stream()
                .map(this::buildFieldAttributeFromMap)
                .filter(fieldAttribute ->!set.contains(fieldAttribute.getName())).collect(Collectors.toList());

        HashMap<String, Object> map = new HashMap<>();
        map.put("className", className);
        map.put("basePackage", basePackage);
        map.put( "fieldAttributes", fieldAttributes);
        map.put("tableName", tableName);

        generateClass(entityPath, "entity.java.ftl", className, map);
    }

    public void generateController(String controllerPath, String basePackage, String className) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("className", className);
        map.put("basePackage", basePackage);

        generateClass(controllerPath, "controller.java.ftl", className + "Controller", map);
    }

    private void generateRepository(String repositoryPath, String basePackage, String className) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("className", className);
        map.put("basePackage", basePackage);

        generateClass(repositoryPath, "repository.java.ftl", className + "Repository", map);
    }

    private void generateServiceImpl(String serviceImplPath, String basePackage, String className) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("className", className);
        map.put("basePackage", basePackage);

        generateClass(serviceImplPath, "serviceImpl.java.ftl", className + "ServiceImpl", map);
    }

    private void generateService(String servicePath, String basePackage, String className) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("className", className);
        map.put("basePackage", basePackage);

        generateClass(servicePath, "service.java.ftl", className + "Service", map);
    }

    private void generateClass(String path, String templateName, String className, Map<String, Object> params) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassLoaderForTemplateLoading(CodeGenerator.class.getClassLoader(), "templates");
        try {
            Template template = configuration.getTemplate(templateName);

            // 如果目录不存在，则创建
            Path p = Paths.get(path);
            if (!Files.exists(p)) {
                Files.createDirectories(p);
            }

            template.process(params, Files.newBufferedWriter(Paths.get(path, className + ".java")));
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将map转为FieldAttribute对象
     */
    private FieldAttribute buildFieldAttributeFromMap(Map<String, Object> map) {
        String columnName = map.get("column_name").toString();
        String columnType = map.get("column_type").toString();
        String columnComment = map.get("column_comment").toString();

        // 获取字段类型枚举
        FieldTypeEnums type = FieldTypeEnums.of(columnType).orElse(FieldTypeEnums.VARCHAR);
        // 将字段名转换为驼峰命名
        columnName = underlineToCamel(columnName);

        return new FieldAttribute(columnName, type, columnComment);
    }

    /**
     * 将path转换为包名
     */
    private String pathToPackage(String path) {
        return path.replace("/", ".").replace("\\", ".");
    }

    /**
     * 下划线转驼峰
     */
    private String underlineToCamel(String str) {
        StringBuilder result = new StringBuilder();
        String[] a = str.split("_");
        for (String s : a) {
            if (result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }
}
