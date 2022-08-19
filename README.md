# xml-dtd

Utility classes for processing XML DTDs.

- [Resolver overview](https://css4j.github.io/resolver.html).
- [Javadocs](https://css4j.github.io/api/xml-dtd/3/).
- [Releases](https://github.com/css4j/xml-dtd/releases).

<br/>

## Licensing

This software is provided under a [BSD-style license](LICENSE.txt).

<br/>

## Java™ Runtime Environment requirements

All the classes in the binary package have been compiled with a [Java compiler](https://adoptium.net/)
set to 1.8 compiler compliance level, except the `module-info.java` file.

Building this library requires JDK 11 or higher.

<br/>

## Building from source

### Requirements

To build this project, you need the following software installed:

- The [Git version control system](https://git-scm.com/downloads) is required to
obtain the sources. Any recent version should suffice.
- Java 11 or later. You can install it from your favourite package manager or by
downloading from [Adoptium](https://adoptium.net/).

<br/>

### Building with Gradle

At the `xml-dtd` sources directory, run `gradlew build` to build:

```shell
git clone https://github.com/css4j/xml-dtd.git
cd xml-dtd
./gradlew build
```

<br/>

### Deploying to a Maven repository

Use:
- `./gradlew build publishToMavenLocal` to install in your local Maven repository.
- `./gradlew publish` to deploy to a (generally remote) Maven repository.

Before deploying to a remote Maven repository, please read the
`publishing.repositories.maven` block of
[build.gradle](https://github.com/css4j/xml-dtd/blob/master/build.gradle)
to learn which properties you need to set (like `mavenReleaseRepoUrl`or
`mavenRepoUsername`), either at the [command line](https://docs.gradle.org/current/userguide/build_environment.html#sec:project_properties)
(`-P` option) or your `GRADLE_USER_HOME/gradle.properties` file.

<br/>

## Software dependencies

In case that you do not use a Gradle or Maven build (which would manage the
dependencies according to the relevant `.module` or `.pom` files), the required
and optional library packages are the following:

### Compile-time dependencies

- The [carte-util](https://github.com/css4j/carte-util) library; version 3.6.0
  or higher is recommended.

- The [jclf-text](https://jclf.sourceforge.io/api/io.sf.jclf.text/module-summary.html)
  (5.0.0 or higher) module. See: https://sourceforge.net/projects/jclf

  **It is optional at runtime.**

- The [tokenproducer](https://github.com/css4j/tokenproducer) library; version
  1.1.1 or higher is recommended.

  **It is optional at runtime.**

### Test dependency

- A recent version of [JUnit 4](https://junit.org/junit4/).
