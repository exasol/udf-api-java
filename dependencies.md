<!-- @formatter:off -->
# Dependencies

## Test Dependencies

| Dependency                                     | License                          |
| ---------------------------------------------- | -------------------------------- |
| [Test containers for Exasol on Docker][0]      | [MIT License][1]                 |
| [Testcontainers :: JUnit Jupiter Extension][2] | [MIT][3]                         |
| [Test Database Builder for Java][4]            | [MIT License][5]                 |
| [Matcher for SQL Result Sets][6]               | [MIT License][7]                 |
| [JUnit Jupiter API][8]                         | [Eclipse Public License v2.0][9] |
| [JUnit Jupiter Params][8]                      | [Eclipse Public License v2.0][9] |
| [Hamcrest][10]                                 | [BSD-3-Clause][11]               |
| [mockito-junit-jupiter][12]                    | [MIT][13]                        |
| [Maven Project Version Getter][14]             | [MIT License][15]                |
| [SLF4J JDK14 Provider][16]                     | [MIT][17]                        |

## Plugin Dependencies

| Dependency                                              | License                                     |
| ------------------------------------------------------- | ------------------------------------------- |
| [Apache Maven Clean Plugin][18]                         | [Apache-2.0][19]                            |
| [Apache Maven Install Plugin][20]                       | [Apache-2.0][19]                            |
| [Apache Maven Resources Plugin][21]                     | [Apache-2.0][19]                            |
| [Apache Maven Site Plugin][22]                          | [Apache-2.0][19]                            |
| [SonarQube Scanner for Maven][23]                       | [GNU LGPL 3][24]                            |
| [Apache Maven Toolchains Plugin][25]                    | [Apache-2.0][19]                            |
| [Apache Maven Compiler Plugin][26]                      | [Apache-2.0][19]                            |
| [Apache Maven Enforcer Plugin][27]                      | [Apache-2.0][19]                            |
| [Maven Flatten Plugin][28]                              | [Apache Software Licenese][19]              |
| [Project Keeper Maven plugin][29]                       | [The MIT License][30]                       |
| [Apache Maven JAR Plugin][31]                           | [Apache-2.0][19]                            |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][32] | [ASL2][33]                                  |
| [Maven Surefire Plugin][34]                             | [Apache-2.0][19]                            |
| [Versions Maven Plugin][35]                             | [Apache License, Version 2.0][19]           |
| [duplicate-finder-maven-plugin Maven Mojo][36]          | [Apache License 2.0][37]                    |
| [Apache Maven Artifact Plugin][38]                      | [Apache-2.0][19]                            |
| [Apache Maven Deploy Plugin][39]                        | [Apache-2.0][19]                            |
| [Apache Maven GPG Plugin][40]                           | [Apache-2.0][19]                            |
| [Apache Maven Source Plugin][41]                        | [Apache License, Version 2.0][19]           |
| [Apache Maven Javadoc Plugin][42]                       | [Apache-2.0][19]                            |
| [Nexus Staging Maven Plugin][43]                        | [Eclipse Public License][44]                |
| [Maven Failsafe Plugin][45]                             | [Apache-2.0][19]                            |
| [JaCoCo :: Maven Plugin][46]                            | [EPL-2.0][47]                               |
| [Quality Summarizer Maven Plugin][48]                   | [MIT License][49]                           |
| [error-code-crawler-maven-plugin][50]                   | [MIT License][51]                           |
| [Git Commit Id Maven Plugin][52]                        | [GNU Lesser General Public License 3.0][53] |

[0]: https://github.com/exasol/exasol-testcontainers/
[1]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[2]: https://java.testcontainers.org
[3]: http://opensource.org/licenses/MIT
[4]: https://github.com/exasol/test-db-builder-java/
[5]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[6]: https://github.com/exasol/hamcrest-resultset-matcher/
[7]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[8]: https://junit.org/junit5/
[9]: https://www.eclipse.org/legal/epl-v20.html
[10]: http://hamcrest.org/JavaHamcrest/
[11]: https://raw.githubusercontent.com/hamcrest/JavaHamcrest/master/LICENSE
[12]: https://github.com/mockito/mockito
[13]: https://opensource.org/licenses/MIT
[14]: https://github.com/exasol/maven-project-version-getter/
[15]: https://github.com/exasol/maven-project-version-getter/blob/main/LICENSE
[16]: http://www.slf4j.org
[17]: https://opensource.org/license/mit
[18]: https://maven.apache.org/plugins/maven-clean-plugin/
[19]: https://www.apache.org/licenses/LICENSE-2.0.txt
[20]: https://maven.apache.org/plugins/maven-install-plugin/
[21]: https://maven.apache.org/plugins/maven-resources-plugin/
[22]: https://maven.apache.org/plugins/maven-site-plugin/
[23]: http://docs.sonarqube.org/display/PLUG/Plugin+Library/sonar-scanner-maven/sonar-maven-plugin
[24]: http://www.gnu.org/licenses/lgpl.txt
[25]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[26]: https://maven.apache.org/plugins/maven-compiler-plugin/
[27]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[28]: https://www.mojohaus.org/flatten-maven-plugin/
[29]: https://github.com/exasol/project-keeper/
[30]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[31]: https://maven.apache.org/plugins/maven-jar-plugin/
[32]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[33]: http://www.apache.org/licenses/LICENSE-2.0.txt
[34]: https://maven.apache.org/surefire/maven-surefire-plugin/
[35]: https://www.mojohaus.org/versions/versions-maven-plugin/
[36]: https://basepom.github.io/duplicate-finder-maven-plugin
[37]: http://www.apache.org/licenses/LICENSE-2.0.html
[38]: https://maven.apache.org/plugins/maven-artifact-plugin/
[39]: https://maven.apache.org/plugins/maven-deploy-plugin/
[40]: https://maven.apache.org/plugins/maven-gpg-plugin/
[41]: https://maven.apache.org/plugins/maven-source-plugin/
[42]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[43]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[44]: http://www.eclipse.org/legal/epl-v10.html
[45]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[46]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[47]: https://www.eclipse.org/legal/epl-2.0/
[48]: https://github.com/exasol/quality-summarizer-maven-plugin/
[49]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[50]: https://github.com/exasol/error-code-crawler-maven-plugin/
[51]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[52]: https://github.com/git-commit-id/git-commit-id-maven-plugin
[53]: http://www.gnu.org/licenses/lgpl-3.0.txt
