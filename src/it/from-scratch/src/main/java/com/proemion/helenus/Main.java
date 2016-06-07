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
package com.proemion.helenus;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.proemion.helenus.cassandra.Check;
import com.proemion.helenus.cassandra.Migration;
import com.proemion.helenus.cli.Setup;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

/**
 * Setup and Subsequent Schema Bootstrap Test.
 * @author Armin Braun (armin.braun@proemion.com)
 * @version $Id$
 * @since 0.1
 */
public final class Main {

    /**
     * Ctor.
     */
    private Main() {
        // Just an EntryPoint.
    }

    /**
     * Helenus can setup a Schema from scratch.
     * @param args Cli Args
     * @throws Exception On failure
     */
    public static void main(final String... args) throws Exception {
        final int cport = Integer.parseInt(args[0]);
        try (
            final Cluster cluster = Cluster.builder().withPort(cport).build();
            final Session session = cluster.connect()
        ) {
            final String keyspace = "keyspace";
            MatcherAssert.assertThat(
                new Check.KeyspaceExists(session, keyspace).fulfilled(),
                Matchers.is(false)
            );
            new Migration.CreateKeyspace(session, keyspace).run();
            MatcherAssert.assertThat(
                new Check.KeyspaceExists(session, keyspace).fulfilled(),
                Matchers.is(true)
            );
            MatcherAssert.assertThat(
                new Setup(session, keyspace).finished(),
                Matchers.is(false)
            );
        }
    }
}
