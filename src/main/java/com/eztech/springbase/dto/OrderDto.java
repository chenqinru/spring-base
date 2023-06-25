package com.eztech.springbase.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author CQR
 */
@Data
@ApiModel(value = "排序", description = "分页需要的表单数据")
public class OrderDto<T extends OrderDto<?>>{

}

