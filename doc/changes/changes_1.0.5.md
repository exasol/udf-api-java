# Exasol UDF API for Java 1.0.5, released 2024-02-23

Code name: Fix CVE-2024-26308 and CVE-2024-25710 in test dependency

## Summary

We fixed CVE-2024-26308 and CVE-2024-25710 by updating the transitive test dependency `org.apache.commons:commons-compress` (was 1.24.0) through updating to `exasol-testcontainers` 7.0.1.

Please note that the actual UDF API remains unchanged.

## Features

* 23: Fixed CVE-2024-25710 by updating transitive dependency
* 24: Fixed CVE-2024-26308 by updating transitive dependency

## Dependency Updates

### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:6.6.3` to `7.0.1`
* Updated `com.exasol:hamcrest-resultset-matcher:1.6.2` to `1.6.4`
* Updated `com.exasol:test-db-builder-java:3.5.2` to `3.5.3`
* Updated `org.junit.jupiter:junit-jupiter-engine:5.10.1` to `5.10.2`
* Updated `org.junit.jupiter:junit-jupiter-params:5.10.1` to `5.10.2`
* Updated `org.mockito:mockito-junit-jupiter:5.7.0` to `5.10.0`
* Updated `org.slf4j:slf4j-jdk14:2.0.9` to `2.0.12`
* Updated `org.testcontainers:junit-jupiter:1.19.2` to `1.19.6`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:2.9.16` to `3.0.1`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.2.2` to `3.2.3`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.6.2` to `3.6.3`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.2.2` to `3.2.3`
* Added `org.apache.maven.plugins:maven-toolchains-plugin:3.1.0`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.16.1` to `2.16.2`
