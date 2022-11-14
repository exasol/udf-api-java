package com.exasol.test.testobject;

import com.exasol.ExaIterator;
import com.exasol.ExaMetadata;

/**
 * This class implements the UDF functions that are used in the tests.
 * <p>
 * The class mainly exists to force building against the API provided in this project. If you wrote inline UDFs, they
 * would be compiled <i>inside</i> the database, using the API that is provided in the language container instead of
 * this one. That means that you would not get compiler errors even if the APi in this project is broken (e.g. when
 * interface methods are missing.
 * </p>
 */
public class GetTimestampUdf {
    public static String run(final ExaMetadata metadata, final ExaIterator context) throws Exception {
        // Enforce conversion to String. This saves us timezone handling, a feature that is not the focus of this test.
        return "" + context.getTimestamp(0);
    }
}