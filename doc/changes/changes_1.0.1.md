# Exasol UDF API for Java 1.0.1, released 2022-11-14

Code name: Added missing `getTimestamp` method

## Summary

The interface method `getTimestamp(int column)` in class `ExaIterator` got lost during migration. 

## Features

* #16: Added missing interface method
## Dependency Updates

### Compile Dependency Updates

* Added `com.exasol:maven-project-version-getter:1.2.0`

### Plugin Dependency Updates

* Updated `org.apache.maven.plugins:maven-deploy-plugin:2.7` to `3.0.0`
* Added `org.apache.maven.plugins:maven-gpg-plugin:3.0.1`
* Updated `org.apache.maven.plugins:maven-jar-plugin:2.4` to `3.2.0`
* Added `org.apache.maven.plugins:maven-javadoc-plugin:3.4.1`
* Added `org.apache.maven.plugins:maven-source-plugin:3.2.1`
* Added `org.sonatype.plugins:nexus-staging-maven-plugin:1.6.13`
