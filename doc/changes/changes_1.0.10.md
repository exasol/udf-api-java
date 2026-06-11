# Exasol UDF API for Java 1.0.10, released 2026-06-11

Code name: UDF Code Coverage Reporting

## Summary

In this release we added the missing JaCoCo agent injection to the integration test, so that now the code coverage is reported properly.

## Bugfixes

* 40: Fixed code coverage reporting from UDF.

## Dependency Updates

### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:7.2.3` to `7.3.0`
* Added `org.jacoco:org.jacoco.agent:0.8.14`
* Updated `org.junit.jupiter:junit-jupiter-params:5.14.4` to `6.1.0`
* Updated `org.slf4j:slf4j-jdk14:2.0.17` to `2.0.18`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:5.6.1` to `5.6.2`
* Added `org.apache.maven.plugins:maven-dependency-plugin:3.10.0`
