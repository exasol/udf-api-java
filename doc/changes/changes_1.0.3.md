# Exasol UDF API for Java 1.0.3, released 2023-09-26

Code name: Fix CVE-2023-42503

## Summary

This release fixes CVE-2023-42503 in `org.apache.commons:commons-compress` by upgrading dependencies.

## Security

* #19: Fixed CVE-2023-42503 in `org.apache.commons:commons-compress`

## Dependency Updates

### Compile Dependency Updates

* Removed `com.exasol:maven-project-version-getter:1.2.0`

### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:6.5.0` to `6.6.2`
* Updated `com.exasol:hamcrest-resultset-matcher:1.5.2` to `1.6.0`
* Added `com.exasol:maven-project-version-getter:1.2.0`
* Updated `com.exasol:test-db-builder-java:3.4.1` to `3.5.0`
* Updated `org.junit.jupiter:junit-jupiter-engine:5.9.2` to `5.10.0`
* Updated `org.junit.jupiter:junit-jupiter-params:5.9.2` to `5.10.0`
* Updated `org.mockito:mockito-junit-jupiter:5.0.0` to `5.5.0`
* Added `org.slf4j:slf4j-jdk14:2.0.9`
* Updated `org.testcontainers:junit-jupiter:1.17.6` to `1.19.0`

### Plugin Dependency Updates

* Removed `com.exasol:artifact-reference-checker-maven-plugin:0.4.1`
* Updated `com.exasol:error-code-crawler-maven-plugin:1.2.1` to `1.3.0`
* Updated `com.exasol:project-keeper-maven-plugin:2.9.1` to `2.9.12`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.10.1` to `3.11.0`
* Updated `org.apache.maven.plugins:maven-deploy-plugin:3.0.0` to `3.1.1`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.1.0` to `3.4.0`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.0.0-M7` to `3.1.2`
* Updated `org.apache.maven.plugins:maven-gpg-plugin:3.0.1` to `3.1.0`
* Updated `org.apache.maven.plugins:maven-jar-plugin:3.2.0` to `3.3.0`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.4.1` to `3.5.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.0.0-M7` to `3.1.2`
* Added `org.basepom.maven:duplicate-finder-maven-plugin:2.0.1`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.3.0` to `1.5.0`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.13.0` to `2.16.0`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.8` to `0.8.10`
