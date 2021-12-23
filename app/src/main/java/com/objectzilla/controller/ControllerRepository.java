package com.objectzilla.controller;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class ControllerRepository {
    private final Map<Class<? extends Controller>, Controller> controllerMap = new HashMap<>();

    public Controller findController(Class<?> controllerClass) {
        return controllerMap.get(controllerClass);
    }

    @Autowired(required = false)
    public void setControllers(Controller[] controllers) {
        for (Controller controller : controllers) {
            controllerMap.put(controller.getClass(), controller);
        }
    }
}
