import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionRunner {
    public static void main(String[] args) {
        AnnotatedClass annotatedClass = new AnnotatedClass();
        Class<?> klass = annotatedClass.getClass();
        for (Method method : klass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(InvokeMethod.class)) {
                InvokeMethod annotation = method.getAnnotation(InvokeMethod.class);
                int times = annotation.times();
                int modifiers = method.getModifiers();
                if (java.lang.reflect.Modifier.isProtected(modifiers) || java.lang.reflect.Modifier.isPrivate(modifiers)
                        || java.lang.reflect.Modifier.isPublic(modifiers)) {
                    method.setAccessible(true);
                    System.out.println("Вызов метода " + method.getName() + " раз = " + times);
                    for (int i = 0; i < times; i++) {
                        try {
                            if (method.getParameterCount() == 0) {
                                method.invoke(annotatedClass);
                            } else if (method.getParameterCount() == 1) {
                                method.invoke(annotatedClass, "Тестовый параметр");
                            } else {
                                method.invoke(annotatedClass, 321, " Тест");
                            }
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
