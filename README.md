# xml-dtd

Utility classes for processing XML DTDs.

- [Javadocs](https://css4j.github.io/api/xml-dtd/3/).
- [Releases](https://github.com/css4j/xml-dtd/releases).

<br/>

## Licensing

This software is provided under a [BSD-style license](LICENSE.txt).

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

At the `xml-dtd` sources directory, run `gradlew build` to build. For example:

```shell
git clone https://github.com/css4j/xml-dtd.git
cd xml-dtd
gradlew build
```
or
```shell
git clone https://github.com/css4j/xml-dtd.git
cd xml-dtd
./gradlew build
```
on Unix-like shells (where the current directory is generally not in the `PATH`).

<br/>

### Deploying to a Maven repository

Use:
- `gradlew build publishToMavenLocal` to install in your local Maven repository.
- `gradlew publish` to deploy to a (generally remote) Maven repository.

Before deploying to a remote Maven repository, please read the
`publishing.repositories.maven` block of
[build.gradle](https://github.com/css4j/xml-dtd/blob/master/build.gradle)
to learn which properties you need to set (like `mavenReleaseRepoUrl`or
`mavenRepoUsername`), either at the [command line](https://docs.gradle.org/current/userguide/build_environment.html#sec:project_properties)
(`-P` option) or your `GRADLE_USER_HOME/gradle.properties` file.
