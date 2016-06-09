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

## Integration Tests

* [From Scratch](https://github.com/Proemion/Helenus/blob/master/src/it/from-scratch)
* [From Scratch Cluster](https://github.com/Proemion/Helenus/blob/master/src/it/from-scratch-cluster)
* [From Scratch Spark](https://github.com/Proemion/Helenus/blob/master/src/it/from-scratch-spark)

## CLI Interface (Not Yet Implemented)

This Project ships with a CLI interface in `com.proemion.helenus.cli.App`,
which enables the user to run a set of migrations packaged in XML or JAR
format against a given Cassandra cluster.

### Commands

The Cli interface offers 4 commands, designed in analogy to the 
[RoR migration interface](http://edgeguides.rubyonrails.org/active_record_migrations.html).

#### Generate (Not Implemented yet)

Generates the scaffold for a Migration.

##### Usage:

`java -jar helenus.jar generate --type=cql`

Arguments:

* `--type`, `-t` possible values:
  * `cql` cql command scaffold
  * `spark` spark job scaffold

#### Status (Not Implemented yet)

Displays the current status of the Cassandra Schema relative to the
migrations given or lists information about migrations in a given keyspace.

##### Usage:

`java -jar helenus.jar status --cassandra=172.17.0.15:4455 [--jar=migrations.jar]`

Arguments:

* `--cassandra`, `-c` (mandatory) Cassandra Host
* `--jar`, `-j` (optional) Jar Containing the Migrations
* `--keyspace`, `-k` (optional) Keyspace to analyze if no jar given

Note: `--jar` and `--keyspace` are mutually exclusive

#### Run (Not Implemented yet)

##### Usage:

`java -jar helenus.jar run --cassandra=172.17.0.15:4455 [--jar=migrations.jar]`

Arguments:

* `--cassandra`, `-c` (mandatory) Cassandra Host
* `--jar`, `-j` (mandatory) Jar Containing the Migrations

#### Install (Not Implemented yet)

##### Usage:

`java -jar helenus.jar install --cassandra=172.17.0.15:4455 --keyspace=somespace`

Arguments:

* `--cassandra`, `-c` (mandatory) Cassandra Host
* `--keyspace`, `-k` (mandatory) Cassandra Keyspace to install into, will be generated if missing

### Examples

## Migrations

### Schema Migrations

### Data Migrations (Not Yet Implemented)

#### Spark

### Revision Handling

Revision information is stored in the Cassandra database itself.
In order to do so, a keyspace in which the revision tracking table
can be created and maintained needs to be provided to Helenus.
```
+--------------+--------------+-------------+
|  type(text)  | version(int) | ts(bigint)  |
+--------------+--------------+-------------+
| Setup        |            1 |  1465393342 |
| Create Table |            2 |  1465393361 |
+--------------+--------------+-------------+
```

## How to Contribute

Pull requests are very welcome and will be addressed within hours in most cases.
To avoid disappointment though, please make sure the build passes for you
locally for the branch you wish to merge.

Officially only Oracle JDK 8 is supported by this tool, since the same requirement stands
for recent versions of Apache Cassandra.
Moreover Maven version `3.3.9` is the only officially supported Maven version for
this build, though older versions will likely work down to Maven `3.2.x`.

The build itself can be run via:

```sh
$ mvn clean install -Pqulice
```

## CI Infrastructure

Currently only Travis is used to build the project on Oracle Jdk8.
The build should run on both a Linux and OSX setup, to ensure portability
for developers.
