package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();
    private final Map<Class<?>, Object> appComponentsByClass = new HashMap<>();

    public AppComponentsContainerImpl(Class<?>... initialConfigClasses) {
        processConfigs(initialConfigClasses);
    }


    private Object newInstanceConfig(Class<?> clazz) {
        try {
            var constructor = clazz.getConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            throw new AppComponentsContainerExeption("Can't instantiate config object", e);
        }
    }

    private void processConfigs(Class<?>... initialConfigClasses) {
        List<ComponentMeta> metas = new ArrayList<>();

        Arrays.stream(initialConfigClasses).forEach(this::checkConfigClass);

        var configs = Arrays.stream(initialConfigClasses)
                .sorted((o1, o2) -> o1.getAnnotation(AppComponentsContainerConfig.class).order() -
                        o2.getAnnotation(AppComponentsContainerConfig.class).order())
                .toList();

        for (Class<?> clazz : configs) {
            processConfig(clazz);
        }
    }

    private List<ComponentMeta> extractComponentsMeta(Class<?> configClass) {
        List<ComponentMeta> metas = new ArrayList<>();

        var declaredMethods = configClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            var component = declaredMethod.getAnnotation(AppComponent.class);
            if (component != null) {
                metas.add(new ComponentMeta(component.order(), component.name(), declaredMethod));
            }
        }

        return metas;
    }

    private void processConfig(Class<?> configClass) {
        checkConfigClass(configClass);

        List<ComponentMeta> metas = extractComponentsMeta(configClass);

        var config = newInstanceConfig(configClass);

        metas.stream().sorted((o1, o2) -> o1.order - o2.order)
                .forEach(meta -> processComponent(meta, config));

    }

    private void processComponent(ComponentMeta meta, Object instanceConfig) {
        var methodParams = meta.method.getParameters();
        List<Object> params = new ArrayList<>();
        for (Parameter param : methodParams) {
            var component = getAppComponent(param.getType());
            params.add(component);
        }

        var clazz = meta.method.getReturnType();
        try {
            var component = meta.method.invoke(instanceConfig, params.toArray());

            if (appComponentsByClass.containsKey(clazz)) {
                throw new AppComponentsContainerExeption(String.format("Component with class %s allready exists", clazz.getName()));
            }
            appComponentsByClass.put(clazz, component);
            if(clazz.isInterface()) {
                appComponentsByClass.put(component.getClass(), component);
            }

            if (appComponentsByName.containsKey(meta.name)) {
                throw new AppComponentsContainerExeption(String.format("Component with name %s allready exists", meta.getName()));
            }
            appComponentsByName.put(meta.getName(), component);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AppComponentsContainerExeption(e.getMessage(), e);
        }
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        return (C) appComponentsByClass.get(componentClass);
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return (C) appComponentsByName.get(componentName);
    }

    private static class ComponentMeta {
        private int order;
        private String name;
        private Method method;

        public ComponentMeta(int order, String name, Method method) {
            this.order = order;
            this.name = name;
            this.method = method;
        }

        public int getOrder() {
            return order;
        }

        public String getName() {
            return name;
        }

        public Method getMethod() {
            return method;
        }

    }
}
