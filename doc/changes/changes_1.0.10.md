# Exasol UDF API for Java 1.0.10, released 2026-06-15

Code name: Test Strategy Revisited

## Summary

In this release we experimented with inject the JaCoCo code coverage agent into the UDF when running the integration test, so that we can measure the test coverage.

In the end we decided against the approach, because however we turned it, the coverage we would measure would not represent the actual API coverage. But the good news is that our attempts resulted in cleaner test code. If you want to learn more, check out the [test strategy](../test_strategy.md).

We also updated a number of dependencies.

## Bugfixes

* #40: Report integration test coverage

## Dependency Updates

### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:7.2.3` to `7.3.0`
* Updated `com.exasol:test-db-builder-java:4.0.0` to `4.0.1`
* Added `org.jacoco:org.jacoco.agent:0.8.14`
* Updated `org.slf4j:slf4j-jdk14:2.0.17` to `2.0.18`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:5.6.1` to `5.6.2`
* Added `org.apache.maven.plugins:maven-dependency-plugin:3.10.0`
