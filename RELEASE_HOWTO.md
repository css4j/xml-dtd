# How to produce a `xml-dtd` release

Please follow these steps to produce a new release of xml-dtd.

## Requirements

- The [Git version control system](https://git-scm.com/downloads) is required to
obtain the sources. Any recent version should suffice.

- Java 11 or later. You can install it from your favourite package manager or by
downloading from [Adoptium](https://adoptium.net/).

- The [`generate_directory_index_caddystyle.py`](https://gist.github.com/carlosame/bd5b68c4eb8e0817d9beb1dcfb4de43d)
script and a recent version of [Python](https://www.python.org/) (required to
run it). The script is necessary to create the index files in the bare-bones
Maven repository currently used by xml-dtd.

## Steps

1) If your local copy of the xml-dtd Git repository exactly matches the current
`master` HEAD, use that copy to execute the `gradlew` commands shown later,
otherwise create a new clone of the `git@github.com:css4j/xml-dtd.git`
repository with `git clone` and use it.

For reference, let your copy of the xml-dtd release code be at
`/path/to/xml-dtd`.

2) Use `changes.sh <new-version>` to create a `CHANGES.txt` file with the
changes from the latest tag. For example if you are releasing `4.2.1`:

```shell
cd /path/to/xml-dtd
./changes.sh 4.2.1
```

Edit the resulting `CHANGES.txt` as convenient, to use it as the basis for the
detailed list of changes when you fill the `RELEASE_NOTES.md` and the new
release in Github.

Complete the Release Notes and check whether the dependencies mentioned in the
`README.md` are correct. Commit the changes.

3) Bump the `version` in the [`build.gradle`](build.gradle) file or remove the
`-SNAPSHOT` suffix as necessary. Commit and push all the changes to the Git
repository.

4) If there is an issue tracking the release, close it.

5) To check that everything is fine, build the code:

```shell
cd /path/to/xml-dtd
./gradlew build
```

6) Clone the `git@github.com:css4j/css4j.github.io.git` repository (which
contains a bare-bones Maven repository) and let `/path/to/css4j.github.io` be
its location.

7) From your copy of the xml-dtd release code, write the new artifacts into
the local copy of the bare-bones Maven repository with:

```shell
cd /path/to/xml-dtd
./gradlew publish -PmavenReleaseRepoUrl="file:///path/to/css4j.github.io/maven"
```

8) Produce the necessary directory indexes in the local copy of the bare-bones
Maven repository using [`generate_directory_index_caddystyle.py`](https://gist.github.com/carlosame/bd5b68c4eb8e0817d9beb1dcfb4de43d):

```shell
cd /path/to/css4j.github.io/maven/io/sf/carte
generate_directory_index_caddystyle.py -r xml-dtd
```

If the changes to the `css4j.github.io` repository look correct, commit them but
do not push yet.

9) Clone the [css4j-dist](https://github.com/css4j/css4j-dist) repository and
execute `./gradlew mergedJavadoc`. Move the javadocs from `build/docs/javadoc`
to `/path/to/css4j.github.io/api/latest`:

```shell
rm -fr /path/to/css4j.github.io/api/latest
mkdir /path/to/css4j.github.io/api/latest
mv /path/to/css4j-dist/build/docs/javadoc/* /path/to/css4j.github.io/api/latest
```

If the changes to the `css4j.github.io` repo look correct, commit them with a
description like "Latest modular Javadocs after xml-dtd 4.2.1" and push.

Check whether the ["Examples" CI](https://github.com/css4j/css4j.github.io/actions/workflows/examples.yml)
triggered by that commit to the `css4j.github.io` repository completed
successfully. A failure could mean that the jar file is not usable with Java 8,
for example.

10) Create a `v<version>` tag in the xml-dtd Git repository. For example:

```shell
cd /path/to/xml-dtd
git tag -s v4.2.1 -m "Release 4.2.1"
git push origin v4.2.1
```

or `git tag -a` instead of `-s` if you do not plan to sign the tag. But it is
generally a good idea to sign a release tag.

Alternatively, you could create the new tag when drafting the Github release
(next step).

11) Draft a new Github release at https://github.com/css4j/xml-dtd/releases

Use the contents of the `RELEASE_NOTES.md` file to summarize the most important
changes in the release description.

Add to the Github release the _jar_ files from this release.

12) Verify that the new [Github packages](https://github.com/orgs/css4j/packages?repo_name=xml-dtd)
were created successfully by the [Gradle Package](https://github.com/css4j/xml-dtd/actions/workflows/gradle-publish.yml)
task.

13) In your local copy of the [css4j-dist](https://github.com/css4j/css4j-dist)
repository, update the xml-dtd version number in the
[maven/install-css4j.sh](https://github.com/css4j/css4j-dist/blob/master/maven/install-css4j.sh)
script. Commit the change, push and look for the completion of that project's
CI.
