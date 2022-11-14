# Exasol UDF API for Java 1.0.1, released 2022-11-14

Code name: Added missing `getTimestamp` method

## Summary

The interface method `getTimestamp(int column)` in class `ExaIterator` got lost during migration. 

## Bugfixes

* #16: Added missing interface method

## Dependency Updates

### Compile Dependency Updates

* Added `com.exasol:maven-project-version-getter:1.2.0`

### Plugin Dependency Updates

* Updated `org.apache.maven.plugins:maven-jar-plugin:2.4` to `3.2.0`