package com.exasol.test.testobject;

import com.exasol.ExaIterator;
import com.exasol.ExaMetadata;

/**
 * This class implements a UDF function that is used in the integration tests. In this case we exercise the
 * {@link ExaIterator#size()} method.
 * <p>
 * The class mainly exists to force building against the API provided in this project. If you wrote inline UDFs, they
 * would be compiled <i>inside</i> the database, using the API that is provided in the language container instead of
 * this one. That means that you would not get compiler errors even if the API in this project is broken (e.g. when
 * interface methods are missing.
 * </p>
 */
public class GetSizeUdf {
    @SuppressWarnings("java:S1172") // Exasol UDF interface requires this signature
    public static long run(final ExaMetadata metadata, final ExaIterator context) throws Exception {
        return context.size();
    }
}
