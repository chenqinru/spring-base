package ${basePackage}.service.impl;

import ${basePackage}.repository.${className}Repository;
import ${basePackage}.entity.${className};
import ${basePackage}.service.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ${className}ServiceImpl extends BaseServiceImpl<${className}Repository, ${className}> implements ${className}Service {

    @Autowired
    public ${className}ServiceImpl(${className}Repository repository) {
        this.repository = repository;
    }
}
