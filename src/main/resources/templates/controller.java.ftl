package ${basePackage}.controller;

import ${basePackage}.service.${className}Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/${className?uncap_first}")
public class ${className}Controller {

    @Resource
    private ${className}Service ${className?uncap_first}Service;

    @GetMapping("/list")
    public void list() {
    }

    @PostMapping("/create")
    public void create() {
    }

    @GetMapping("/{id}")
    public void read(@PathVariable Integer id) {
    }

    @PutMapping("/update")
    public void update() {
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody List<Integer> ids) {
    }
}
