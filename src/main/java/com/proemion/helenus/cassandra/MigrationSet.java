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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Multiple Migrations.
 * @author Armin Braun (armin.braun@proemion.com)
 * @version $Id$
 * @since 0.1
 */
public final class MigrationSet implements Migration {

    /**
     * Migrations to run.
     */
    private final List<Migration> migrations;

    /**
     * Ctor.
     * @param runs Migrations to run
     */
    public MigrationSet(final Collection<Migration> runs) {
        this.migrations = new ArrayList<>(runs);
    }

    @Override
    public void run() {
        Collections.sort(
            this.migrations,
            (first, second) -> Long.compare(
                first.identifier(), second.identifier()
            )
        );
    }

    @Override
    public long identifier() {
        long max = 0L;
        for (final Migration migration : this.migrations) {
            max = Math.max(migration.identifier(), max);
        }
        return max;
    }

    @Override
    public boolean finished() {
        return false;
    }
}
