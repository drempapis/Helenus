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

package com.proemion.helenus.cassandra.migration;

import com.datastax.driver.core.Session;
import com.google.common.base.Optional;

/**
 * Setup Helenus on a Keyspace.
 * @author Armin Braun (armin.braun@proemion.com)
 * @version $Id$
 * @since 0.1
 */
public final class Setup implements Migration {

    /**
     * Table Used to Store Migrations.
     */
    private static final String TABLE = "helenus_schema_migrations";

    /**
     * Cassandra Session.
     */
    private final Session session;

    /**
     * Keyspace to work on.
     */
    private final String keyspace;

    /**
     * Ctor.
     * @param connection Cassandra session
     * @param kyspace Keyspace
     */
    public Setup(final Session connection, final String kyspace) {
        this.session = connection;
        this.keyspace = kyspace;
    }

    @Override
    public void run() {
        if (this.finished()) {
            throw new IllegalStateException(
                String.format(
                    String.join(
                        " ", "Setup on keyspace %s attempted,",
                        "but %s already contains a migration tracking table!"
                    ), this.keyspace, this.keyspace
                )
            );
        }
        final String type = "type";
        final String version = "version";
        this.session.execute(
            String.format(
                String.join(
                    " ", "CREATE TABLE %s.%s", "(%s text, %s int, ts bigint,",
                    "PRIMARY KEY (%s, %s)) WITH CLUSTERING ORDER BY",
                    "(%s DESC);"
                ),
                this.keyspace, Setup.TABLE, type, version, type, version,
                version
            )
        );
    }

    @Override
    public long identifier() {
        return 1L;
    }

    @Override
    public boolean finished() {
        return Optional.fromNullable(
            this.session.getCluster().getMetadata().getKeyspace(this.keyspace)
                .getTable(Setup.TABLE)
        ).isPresent();
    }
}
