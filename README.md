# Exasol UDF API for Java

[![Build Status](https://github.com/exasol/udf-api-java/actions/workflows/ci-build.yml/badge.svg)](https://github.com/exasol/udf-api-java/actions/workflows/ci-build.yml)
[![Maven Central &ndash; Exasol UDF API for Java](https://img.shields.io/maven-central/v/com.exasol/udf-api-java)](https://search.maven.org/artifact/com.exasol/udf-api-java)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Audf-api-java&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.exasol%3Audf-api-java)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Audf-api-java&metric=security_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Audf-api-java)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Audf-api-java&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Audf-api-java)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Audf-api-java&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Audf-api-java)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Audf-api-java&metric=sqale_index)](https://sonarcloud.io/dashboard?id=com.exasol%3Audf-api-java)

[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Audf-api-java&metric=code_smells)](https://sonarcloud.io/dashboard?id=com.exasol%3Audf-api-java)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Audf-api-java&metric=coverage)](https://sonarcloud.io/dashboard?id=com.exasol%3Audf-api-java)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Audf-api-java&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=com.exasol%3Audf-api-java)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Audf-api-java&metric=ncloc)](https://sonarcloud.io/dashboard?id=com.exasol%3Audf-api-java)

This project contains the API required to build [User Defined Functions](https://docs.exasol.com/db/latest/database_concepts/udf_scripts.htm) (UDFs) for Exasol in the Java programming language.

User Defined Functions extend Exasol with functions and scripts that can be called from within SQL statements.



## Information for API Users

### Dependency

This library is already included in the Exasol database. Therefore, you should include it with the scope `provided` in Maven or `compileOnly` in Gradle. This ensures that the library is available during compilation but is not bundled with your UDF jar, avoiding conflicts with the version pre-installed in the database.

Replace the version numbers below with the latest version.

#### Maven

```xml
<dependency>
    <groupId>com.exasol</groupId>
    <artifactId>udf-api-java</artifactId>
    <version>1.0.10</version>
    <scope>provided</scope>
</dependency>
```

#### Gradle

```groovy
dependencies {
    compileOnly 'com.exasol:udf-api-java:1.0.10'
}
```

* [API documentation as JavaDoc](https://exasol.github.io/udf-api-java)
* [Java tutorials](https://github.com/exasol/exasol-java-tutorial) with examples of how to build, test and run Java UDFs

## Additional Information

* [Changelog](doc/changes/changelog.md)
* [Dependencies](dependencies.md)
* [Test strategy](doc/test_strategy.md)
