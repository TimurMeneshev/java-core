package runtimeAnnotations;

import java.awt.event.ActionListener;
import java.lang.reflect.*;

public class ActionListenerInstaller {
    public static void processAnnotations(Object obj) {
        try {
            Class<?> cl = obj.getClass();
            for (Method m : cl.getDeclaredMethods()) {
                ActionListenerFor a = m.getAnnotation(ActionListenerFor.class);
                if (a != null) {
                    Field f = cl.getDeclaredField(a.source());
                    f.setAccessible(true);
                    addListener(f.get(obj), obj, m);
                }
            }
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    public static void addListener(Object source, final Object param, final Method m) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var handler = new InvocationHandler() {
            public Object invoke(Object proxy, Method m, Object[] args) throws InvocationTargetException, IllegalAccessException {
                return m.invoke(param);
            }
        };

        Object listener = Proxy.newProxyInstance(null, new Class[] { java.awt.event.ActionListener.class }, handler);
        Method adder = source.getClass().getMethod("addActionListener", ActionListener.class);
        adder.invoke(source, listener);
    }
}
