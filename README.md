[![Build Status](https://travis-ci.org/Proemion/Helenus.svg?branch=master)](https://travis-ci.org/Proemion/Helenus)
[![license](https://img.shields.io/github/license/mashape/apistatus.svg?maxAge=2592000)]()
[![Dependency Status](https://www.versioneye.com/user/projects/57518e987757a00041b3a144/badge.svg?style=flat)](https://www.versioneye.com/user/projects/57518e987757a00041b3a144)
[![Coverage Status](https://coveralls.io/repos/github/Proemion/Helenus/badge.svg?branch=master)](https://coveralls.io/github/Proemion/Helenus?branch=master)

# Helenus

## Mission Statement

Helenus aims to provide an easy to use and stable Cassandra schema migration
tool, at the comfort level of solutions like Ruby on Rails database migration
functionality. 
Unlike other solutions it has no outside dependency on any RDBMS or other persistent
storage, other than Cassandra itself.

## How to Contribute

Pull requests are welcome, to avoid disappointment though, please make sure
the build passes locally for the branch you wish to merge.

Officially only Oracle JDK 8 is supported by this tool, since the same requirement stands
for recent versions of Apache Cassandra.
Moreover Maven version `3.3.9` is the only officially supported Maven version for
this build, though older versions will likely work down to Maven `3.2.x`.

The build itself can be run via:

```sh
$ mvn clean install -Pqulice
```

## Integration Tests

* [From Scratch](https://github.com/Proemion/Helenus/blob/master/src/it/from-scratch/README.md)
