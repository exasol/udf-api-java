package com.exasol.test.testobject;

import com.exasol.ExaIterator;
import com.exasol.ExaMetadata;

import java.lang.reflect.Method;

/**
 * This class uses reflection to exercise the specified context method.
 */
public class MetadataMethodExerciser {
    public static String run(final ExaMetadata metadata, final ExaIterator context) throws Exception {
        final String methodName = context.getString(0);
        final Method method = metadata.getClass().getMethod(methodName);
        method.setAccessible(true);
        // Force conversion to string, so that we only have to deal with one constant return type.
        return "" + method.invoke(metadata);
    }
}
