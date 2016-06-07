/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Proemion GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.proemion.helenus.cli;

import com.jcabi.log.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Cli Entry Point.
 * @author Armin Braun (armin.braun@proemion.com)
 * @version $Id$
 * @since 0.1
 */
public final class App {

    /**
     * Cassandra Host Cli Argument.
     */
    private static final String CASSANDRA = "cassandra_host";

    /**
     * Migrations Directory Cli Argument.
     */
    private static final String MIGRATIONS = "migration_class";

    /**
     * Migrations Directory Cli Argument.
     */
    private static final String JAR = "jar";

    /**
     * Cli Option Definitions.
     */
    private static final Options OPTIONS = new Options()
        .addOption("c", App.CASSANDRA, true, "Cassandra Host")
        .addOption("m", App.MIGRATIONS, true, "Migration Runner Class")
        .addOption("j", App.JAR, true, "Migrations Jar");

    /**
     * Parsed CLI Arguments.
     */
    private final CommandLine cli;

    /**
     * Ctor.
     * @param args Cli arguments
     * @throws ParseException On failure
     */
    private App(final String... args) throws ParseException {
        this.cli = new DefaultParser().parse(App.OPTIONS, args);
    }

    /**
     * Entry Point.
     * @param args Cli arguments
     * @throws Exception On failure
     */
    public static void main(final String... args) throws Exception {
        new App(args).exec();
    }

    /**
     * Run the application.
     */
    private void exec() {
        if (this.cli.hasOption(App.CASSANDRA)) {
            if (this.cli.hasOption(App.MIGRATIONS)) {
                Logger.info(App.class, "Not Implemented.");
            } else {
                throw App.missing(App.MIGRATIONS);
            }
        } else {
            throw App.missing(App.CASSANDRA);
        }
    }

    /**
     * Builds IllegalArgumentException for missing cli argument.
     * @param arg Argument missing
     * @return Exception for missing argument
     */
    private static IllegalArgumentException missing(final String arg) {
        return new IllegalArgumentException(
            String.format("Argument %s missing!", arg)
        );
    }
}
