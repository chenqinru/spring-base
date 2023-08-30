package com.eztech.springbase.common;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class CodeGeneratorTest {

    @Resource
    private CodeGenerator codeGenerator;

    @Test
    void generate() {
        codeGenerator.generate("com/eztech/springbase", "ez_system_operation_log", "OperationLog");
    }
}
