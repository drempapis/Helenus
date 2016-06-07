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

package com.proemion.helenus.cassandra;

import com.datastax.driver.core.Session;
import java.util.Date;

/**
 * A Cassandra Schema Migration.
 * @author Armin Braun (armin.braun@proemion.com)
 * @version $Id$
 * @since 0.1
 */
public interface Migration {

    /**
     * Execute the Migration.
     */
    void run();

    /**
     * Sortable Identifier for the Migration.
     * @return Sortable Identifier for the Migration
     */
    long identifier();

    /**
     * Is the migration finished?
     * @return True if migration has finished sucessfully
     */
    boolean finished();

    /**
     * Creates a Keyspace.
     */
    final class CreateKeyspace implements Migration {

        /**
         * Cassandra Session.
         */
        private final Session session;

        /**
         * Keyspace to work on.
         */
        private final String keyspace;

        /**
         * Identifier.
         */
        private final long ident;

        /**
         * Ctor.
         * @param connection Cassandra session
         * @param kyspace Keyspace
         */
        public CreateKeyspace(
            final Session connection, final String kyspace
        ) {
            this(connection, kyspace, new Date().getTime());
        }

        /**
         * Ctor.
         * @param connection Cassandra session
         * @param kyspace Keyspace
         * @param identif Long identifier
         */
        public CreateKeyspace(
            final Session connection, final String kyspace, final long identif
        ) {
            this.session = connection;
            this.keyspace = kyspace;
            this.ident = identif;
        }

        @Override
        public void run() {
            this.session.execute(
                String.format(
                    String.join(
                        " ",
                        "CREATE KEYSPACE %s WITH replication =",
                        "{'class':'SimpleStrategy', 'replication_factor': '1'}",
                        "AND durable_writes = true"
                    ),
                    this.keyspace
                )
            );
        }

        @Override
        public long identifier() {
            return this.ident;
        }

        @Override
        public boolean finished() {
            return new Check.KeyspaceExists(this.session, this.keyspace)
                .fulfilled();
        }
    }
}
