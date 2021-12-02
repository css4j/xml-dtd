# xml-dtd version 4.0.0 Release Notes

### December 3, 2021

<br/>

## Highlights

### Removal of DefaultEntityResolver.resolveEntity(String)

See issue #1.

### Use the Security Manager if available

See issue #2.

## Detail of changes

- Remove `DefaultEntityResolver.resolveEntity(String)`. (#1)
- Use the Security Manager only if available. (#2)
- Gradle: copy licensing files to `META-INF` directory of archive files.
- Gradle: line-ending conversion task now warns if file does not exists.
- Gradle: upgrade wrapper to 7.3.
- Upgrade to JUnit 4.13.2.
- Add a 'changes.sh' script to generate a CHANGES.txt.
- Use Markdown in Release Notes, move non-release information to README.
- README: add a link to the resolver overview/introduction page.
- CI: build with Java 11 and 17.
