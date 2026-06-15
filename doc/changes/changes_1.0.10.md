# Exasol UDF API for Java 1.0.10, released 2026-06-15

Code name: UDF Code Coverage

## Summary

In this release we inject the JaCoCo code coverage agent into the UDF when running the integration test, so that we can measure the test coverage.

## Bugfixes

* #40: Report integration test coverage

## Dependency Updates

### Test Dependency Updates

* Added `com.exasol:exasol-test-setup-abstraction-java:2.1.11`
* Updated `com.exasol:exasol-testcontainers:7.2.3` to `7.3.0`
* Added `com.exasol:udf-debugging-java:0.6.18`
* Added `org.jacoco:org.jacoco.agent:0.8.14`
* Updated `org.slf4j:slf4j-jdk14:2.0.17` to `2.0.18`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:5.6.1` to `5.6.2`
* Added `org.apache.maven.plugins:maven-dependency-plugin:3.10.0`
