package com.spring.demo.controller;

import com.spring.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuweizhi
 * @since 2019-08-06
 */
@RestController
@RequestMapping("/validated")
@Slf4j
public class ValidatedController {

    @GetMapping
    public void testValidated(@Validated User user, String fix, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FieldError age = bindingResult.getFieldError("age");
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));

            throw new RuntimeException(age.getDefaultMessage());
        }
        log.info(user.toString());
        System.out.println(fix);
    }

}
