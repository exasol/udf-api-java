# Exasol UDF API for Java 1.0.0, released 2022-11-09

Code name: Extracted UDF API from script-languages

## Summary

This is the first separate release of the UDF API. We extracted it from the project [script-languages release 2019-07-26](https://github.com/exasol/script-languages/releases/tag/20190726). Note that the API has been stable for a very long time and this extracted release did not change the API either. We just extracted it to enable a better way of releasing and distributing the API in the future.

## Refactoring

* #1: Extracted UDF API from `script-languages`
* #5: Fixed JavaDoc and code smells

## Dependency Updates

### Test Dependency Updates

* Added `com.exasol:exasol-testcontainers:6.3.1`
* Added `com.exasol:hamcrest-resultset-matcher:1.5.2`
* Added `com.exasol:test-db-builder-java:3.4.1`
* Added `org.hamcrest:hamcrest:2.2`
* Added `org.junit.jupiter:junit-jupiter-engine:5.9.1`
* Added `org.junit.jupiter:junit-jupiter-params:5.9.1`
* Added `org.mockito:mockito-junit-jupiter:4.8.1`
* Added `org.testcontainers:junit-jupiter:1.17.5`

### Plugin Dependency Updates

* Added `com.exasol:artifact-reference-checker-maven-plugin:0.4.1`
* Added `com.exasol:error-code-crawler-maven-plugin:1.2.1`
* Added `com.exasol:project-keeper-maven-plugin:2.9.1`
* Added `io.github.zlika:reproducible-build-maven-plugin:0.16`
* Added `org.apache.maven.plugins:maven-clean-plugin:2.5`
* Added `org.apache.maven.plugins:maven-compiler-plugin:3.10.1`
* Added `org.apache.maven.plugins:maven-deploy-plugin:2.7`
* Added `org.apache.maven.plugins:maven-enforcer-plugin:3.1.0`
* Added `org.apache.maven.plugins:maven-failsafe-plugin:3.0.0-M7`
* Added `org.apache.maven.plugins:maven-install-plugin:2.4`
* Added `org.apache.maven.plugins:maven-jar-plugin:2.4`
* Added `org.apache.maven.plugins:maven-resources-plugin:2.6`
* Added `org.apache.maven.plugins:maven-site-plugin:3.3`
* Added `org.apache.maven.plugins:maven-surefire-plugin:3.0.0-M7`
* Added `org.codehaus.mojo:flatten-maven-plugin:1.3.0`
* Added `org.codehaus.mojo:versions-maven-plugin:2.13.0`
* Added `org.jacoco:jacoco-maven-plugin:0.8.8`
* Added `org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.1.2184`
* Added `org.sonatype.ossindex.maven:ossindex-maven-plugin:3.2.0`
