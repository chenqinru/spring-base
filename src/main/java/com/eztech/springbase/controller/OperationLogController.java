package com.eztech.springbase.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CQR
 */
@RestController
@Api(tags = "操作日志")
@AllArgsConstructor
@RequestMapping("/log")
public class OperationLogController {

}
