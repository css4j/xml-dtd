# Contributing to xml-dtd

 You can contribute to the xml-dtd project by raising issues and/or sending `git`
pull requests.

<br/>

## Report issues

 If you find any issue with the software or want to ask for an enhancement, use
the Github's [issue tracker](https://github.com/css4j/xml-dtd/issues).

 Note that some issues may correspond to other subprojects, for example
[carte-util issues](https://github.com/css4j/carte-util/issues).

<br/>

## Pull requests

 To contribute code to this project it is recommended to open an issue first,
explaining the rationale for the changes that you want to implement. Then, in
the title of the pull request (PR) you can include a reference like "fixes #NN"
where NN is the issue number. And it is generally a good idea to base your PR on
a branch that was named after the issue; for example your branch could be named
`issue-14`.

 A PR should only try to fix a single issue, unless it fixes two or more issues
that are very related or effectively the same. And if a commit has two or more
different purposes, it is often better to split it in multiple commits; tools
like the _Git GUI_ are particularly useful for that.

<br/>

### Tests

 All PRs should come with one or more JUnit tests unless the change is a small,
obviously correct fix. Ideally, tests should provide a full coverage of the new
code, except for cases like multiple equivalent comparisons, exceptions that are
very hard to trigger or never thrown (or put as a theoretical safeguard), etc.
However, even one test is better than nothing.

1) For consistency, the names of classes that contain tests (i.e. with `@Test`
annotation(s)) must end with `Test`. This makes easier to tell the actual tests
apart from their helper classes.

2) It is acceptable to mix tests and main source code in the same commit, as it
is immediately obvious to the reviewers which are which, and the tests offer an
insight of what the commit is trying to achieve. You do not need to mention the
tests in the commit message.

3) Tests should not involve remote network connections, unless the subject of
that test is to check the connection or its security. It should be possible to
disable those tests with the `TestConfig.REMOTE_TESTS` toggle.

<br/>

### Code style

 The code style could be summarized by the following points:

- Indent by tabs, not spaces. The automated formatting provided by the Eclipse
IDE is often used.

- `if`-`else` blocks should always use curly braces, even if a single line of
code is involved.

- Long, descriptive variable names are preferred.

- Add comments to explain what the code is trying to do, but avoiding useless
prose that just mimics the code, like _"check if foo is larger than 1"_ as a
comment to `if (foo > 1)`.

- Public and protected methods must have documentation comments.

- Avoid trailing whitespace except for empty lines in Javadoc comments.

- Classes and methods should have the minimum visibility that they require.
A method should not have `protected` visibility when being package-visible could
be enough, unless subclasses in other packages would naturally extend it. For
complex package-level or inner classes, it is acceptable to have `protected`
methods as a mean to document which ones are intended to be overridden by other
classes. (In that case, protected methods do not appear in the Javadocs and
therefore are not part of the API)

<br/>

### Copyright and attribution

 All contributions are submitted under a [Developer Certificate of Origin](DeveloperCertificateOfOrigin.txt).
Although every author maintains their copyright, in case that code from this
project —including your contribution(s)— is used in a way that is deemed as a
license infringement, you are —in principle— not opposed to be represented by
the maintainer of this project in any legal proceedings intended to protect the
license of this project.

 If you modify a source file that happens to have an `@author` tag with the
author name, feel free to remove that field and let `git blame` handle the
attribution.

 It is assumed that if you put an `@author` field with your name in a new file,
the same criteria would apply.

<br/>

## Licensing

 Your contributions are to be submitted according to the licensing of this
project, see the `LICENSE.txt` and `NOTICE.txt` files for more information.

<br/>

## Distribution

 This project [is not being submitted to the Maven Central repository](https://groups.google.com/g/css4j/c/op5jIoINb3M/m/IiiN-LfkDAAJ)
and this is something known to deter some contributors, thus being a relevant
information to put here.
