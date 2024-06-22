# xml-dtd version 4.3 Release Notes

### June 22, 2024

<br/>

## Highlights

As a last resort, the resolver now attempts to get a known System ID when the
supplied System ID is unknown but the Public ID is known.

<br/>

## Detail of changes

- resolver: attempt to get a known System ID when the supplied System ID is
  unknown but the Public ID is known.
- Add scm section to Maven POM data.
- Bump copyright year to 2024.
- Upgrade to actions/setup-java v4.
- Upgrade to JUnit 5.10.2.
- Bump github/codeql-action from 2 to 3.
- Upgrade Gradle wrapper to 8.5.
- Actions: switch to gradle/actions/wrapper-validation.
- A few modifications to README.
- Default to Linux line endings for source files.
