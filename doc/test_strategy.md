# Test Strategy

This document explains the test setup of the [Exasol UDF API for Java](../README.md), the options considered for
measuring integration test coverage, and the decision to not use code coverage as a quality metric for this project.

## Project Setup

This project publishes the Java API that UDF authors compile against when writing Java user defined functions for the Exasol database. In production, the UDF API JAR is provided by the Exasol installation and loaded by the script language container. UDF authors compile against this artifact, but they do not include it in their own UDF JARs.

The integration tests therefore deliberately mirror this deployment model:

* The Maven build creates a test JAR containing probe UDF classes from `src/test/java`.
* The test JAR is uploaded to BucketFS in an Exasol test container.
* The integration tests create Java UDF scripts that load this test JAR with a `%jar` directive.
* The probe UDF classes were compiled against this project, but at runtime they interact with the API classes and
  implementation provided by Exasol.

This setup is primarily a compatibility test. It verifies that code compiled against this project can run in Exasol and call the relevant UDF API methods.

## Coverage Problem

The project is not a typical Java library with executable production logic. Most of the public surface is made of interfaces such as `ExaIterator` and `ExaMetadata`. Interface method declarations do not contain executable instructions that JaCoCo can cover. The executable classes in this repository are mostly exception classes and a small enum.

When the JaCoCo agent is injected into the UDF JVM, it can observe code executed inside the Exasol script language container. However, the code executed there is not this project's API artifact. It is Exasol's provided runtime API and implementation plus the test probe classes. A local JaCoCo report such as `target/site/jacoco/index.html` can therefore show execution data from the UDF JVM while still reporting no meaningful coverage for this project.

## Options Considered

### Inject JaCoCo Into the UDF JVM

This was implemented and verified to collect execution data from the UDF-side JVM.

Rejected as a coverage metric for this project because the executed API implementation belongs to the Exasol runtime, not to this repository. The report does not answer the question "Which code in this project was covered by the integration tests?"

### Include This Project's API JAR in the UDF

This would make JaCoCo observe this repository's classes in the UDF runtime.

Rejected because it would no longer test the real deployment model. The Exasol installation provides the UDF API at runtime, and forcing this project's API JAR into the UDF could hide incompatibilities with the database-provided API.

The resulting coverage would be easier to measure but less relevant.

### Generate Proxy or Wrapper Code Around the API

A proxy could wrap `ExaIterator`, `ExaMetadata`, and related interfaces, record which methods were called, and delegate
to Exasol's runtime objects.

Rejected as code coverage because it would measure the proxy and the probe UDFs, not this project's API. Such a proxy could be useful as an API exercise matrix, but it would be a custom compatibility metric rather than JaCoCo coverage.

It would also add test-only code that has to mirror the whole API surface.

### Add Unit Tests for the Executable Classes

Unit tests could cover exception constructors, message formatting, and enum initialization.

Rejected as a substitute for integration coverage because this would only cover incidental executable bytecode. It would not validate the important compatibility property: UDF code compiled against this API must run against Exasol's provided runtime API.

## Decision

We do not attempt to measure meaningful code coverage for this project with integration tests.

The integration tests remain valuable and should be kept as compatibility and smoke tests. They check the behavior that matters for this artifact: Java UDF code compiled against this project can be loaded and executed by Exasol.

Coverage reports for this project should not be used as a release quality gate. If a build or quality system requires a coverage value, this repository should be excluded from that requirement or the exception should point to this document.

If more insight is needed in the future, the preferred direction is an explicit API exercise matrix that reports which UDF API methods are invoked by smoke tests. That metric must be documented as compatibility coverage, not as JaCoCo code coverage.
