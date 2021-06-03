package com.spring.demo.listener.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author xuweizhi
 * @since 2021/06/03 23:49
 */
@Component
public class SpringEventComponent {


    @EventListener
    public void springEvent(AnnotationEvent annotationEvent){
        System.out.println(annotationEvent.getMsg());
    }

}
