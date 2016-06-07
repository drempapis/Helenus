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

package com.proemion.test.cassandra;

import com.datastax.driver.core.Session;

/**
 * Cassandra Query.
 * @author Armin Braun (armin.braun@proemion.com)
 * @version $Id$
 * @since 0.1
 */
public interface QueriesCassandra {

    /**
     * Execute the Query.
     */
    void exec();

    /**
     * Default CQL String Query.
     */
    final class Default implements QueriesCassandra {

        /**
         * Cassandra Session.
         */
        private final Session session;

        /**
         * CQL Query.
         */
        private final String query;

        /**
         * Ctor.
         * @param sessin Cassandra Session
         * @param qury CQL Query
         */
        Default(final Session sessin, final String qury) {
            this.session = sessin;
            this.query = qury;
        }

        @Override
        public void exec() {
            this.session.execute(this.query);
        }
    }
}
