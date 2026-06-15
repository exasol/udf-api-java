# Exasol UDF API for Java 2.0.0, released 2026-06-11

Code name: Java 17

## Summary

The Exasol UDF API is now built with Java 17. Java 11 is no longer supported.

**This is a breaking change for [Exasol 7.1](https://docs.exasol.com/db/latest/planning/life_cycle/life_cycle_policy.htm#Exasol)** (which reaches end-of-life end of June 2026).

If you still have UDF that require Java 11, please use release 1.0.9 of the API. Content-wise nothing changed except the JRE compatibility.

In the course of the Java 17 switch, we also upgraded from JUnit 5 to 6 and added the missing JaCoCo agent injection to the integration test, so that now the code coverage is reported properly.

## Bugfixes

* #40: Report integration test coverage

## Dependency Updates

### Test Dependency Updates

* Added `com.exasol:exasol-test-setup-abstraction-java:2.1.11`
* Updated `com.exasol:exasol-testcontainers:7.2.3` to `7.3.0`
* Added `com.exasol:udf-debugging-java:0.6.18`
* Added `org.jacoco:org.jacoco.agent:0.8.14`
* Updated `org.junit.jupiter:junit-jupiter-params:5.14.4` to `6.1.0`
* Updated `org.slf4j:slf4j-jdk14:2.0.17` to `2.0.18`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:5.6.1` to `5.6.2`
* Added `org.apache.maven.plugins:maven-dependency-plugin:3.10.0`
