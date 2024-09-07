![CI workflow](https://github.com/css4j/xml-dtd/actions/workflows/build.yml/badge.svg)

# xml-dtd

Utility classes for processing XML DTDs.

- [Resolver overview](https://css4j.github.io/resolver.html).
- [Javadocs](https://css4j.github.io/api/latest/io.sf.carte.xml.dtd/module-summary.html).
- [Releases](https://github.com/css4j/xml-dtd/releases).

<br/>

## Modifications to W3C DTDs

The W3C DTDs distributed with this software have been modified so the "Character
mnemonic entities" section of the DTDs was replaced with the HTML5 entities, for
better interoperability of XHTML 1.x and 5.

Also, the relative URLs used to reference DTD modules have been absolutized as
needed, so they can be used with different base URLs. For context, see
https://www.w3.org/MarkUp/2009/xhtml11-issues-20090525.html#ssec4

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

At the `xml-dtd` sources directory, run `./gradlew build` to build:

```shell
git clone https://github.com/css4j/xml-dtd.git
cd xml-dtd
./gradlew build
```

<br/>

### Deploying to a Maven repository

Use:
- `./gradlew publishToMavenLocal` to install in your local Maven repository.
- `./gradlew publish` to deploy to a (generally remote) Maven repository.

Before deploying to a remote Maven repository, please read the
`publishing.repositories.maven` block of
[build.gradle](https://github.com/css4j/xml-dtd/blob/master/build.gradle)
to learn which properties you need to set (like `mavenReleaseRepoUrl`or
`mavenRepoUsername`), either at the [command line](https://docs.gradle.org/current/userguide/build_environment.html#sec:project_properties)
(`-P` option) or your `GRADLE_USER_HOME/gradle.properties` file.

<br/>

## Usage from a Gradle project

If your Gradle project depends on xml-dtd, you can use this project's own Maven
repository in a `repositories` section of your build file:

```groovy
repositories {
    maven {
        url "https://css4j.github.io/maven/"
        mavenContent {
            releasesOnly()
        }
        content {
            // Include io.sf.carte as well as other groups under io.sf
            includeGroupByRegex 'io\\.sf\\..*'

            // Alternative to the regex:
            //includeGroup 'io.sf.carte'
        }
    }
}
```
please use that repository only for the artifact groups that it supplies.

Then, in your `build.gradle` file you can list the dependencies, for example:

```groovy
dependencies {
    api 'io.sf.carte:xml-dtd:4.3'
}
```

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
  3.0 or higher is required.

  **It is optional at runtime.**

### Test dependency

- A recent version of [JUnit 5](https://junit.org/junit5/).
