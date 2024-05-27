package com.turkcell.customerservice.core.utilities.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class NullAwareBeanUtils {

    public static void copyNonNullProperties(Object source, Object target) {
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(target.getClass());

        for (PropertyDescriptor targetPd : targetPds) {
            if (targetPd.getWriteMethod() != null) {
                PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null && sourcePd.getReadMethod() != null) {
                    try {
                        Method readMethod = sourcePd.getReadMethod();
                        if (!readMethod.isAccessible()) {
                            readMethod.setAccessible(true);
                        }
                        Object value = readMethod.invoke(source);

                        if (value != null &&
                                (!(value instanceof String) || (value instanceof String && !((String) value).trim().isEmpty())) &&
                                (!(value instanceof Integer) || (value instanceof Integer && ((Integer) value) != 0))) {
                            Method writeMethod = targetPd.getWriteMethod();
                            if (!writeMethod.isAccessible()) {
                                writeMethod.setAccessible(true);
                            }
                            writeMethod.invoke(target, value);
                        }
                    } catch (Throwable ex) {
                        throw new FatalBeanException("Could not copy properties from source to target", ex);
                    }
                }
            }
        }
    }
}