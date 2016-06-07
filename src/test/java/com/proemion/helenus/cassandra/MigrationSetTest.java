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

import com.jcabi.aspects.Tv;
import java.util.ArrayList;
import java.util.Collection;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

/**
 * Tests for {@link MigrationSet}.
 * @author Armin Braun (armin.braun@proemion.com)
 * @version $Id$
 * @since 0.1
 */
public final class MigrationSetTest {

    /**
     * {@link MigrationSet} can share the highest identifier found in its member
     * migrations.
     * @throws Exception On failure
     */
    @Test
    public void sharesHighestMemberIdentifier() throws Exception {
        final Migration first = Mockito.mock(Migration.class);
        Mockito.when(first.identifier()).thenReturn((long) Tv.FIFTY);
        final Migration second = Mockito.mock(Migration.class);
        Mockito.when(second.identifier()).thenReturn((long) Tv.THOUSAND);
        final Migration third = Mockito.mock(Migration.class);
        Mockito.when(third.identifier()).thenReturn((long) Tv.HUNDRED);
        final Collection<Migration> migrations = new ArrayList<>(3);
        migrations.add(first);
        migrations.add(second);
        migrations.add(third);
        MatcherAssert.assertThat(
            new MigrationSet(migrations).identifier(),
            Matchers.is((long) Tv.THOUSAND)
        );
    }

    /**
     * {@link MigrationSet} can run migrations oldest first.
     * @throws Exception On failure
     */
    @Test
    public void runsOldestToNewest() throws Exception {
        final Migration first = Mockito.mock(Migration.class);
        Mockito.when(first.identifier()).thenReturn((long) Tv.FIFTY);
        final Migration second = Mockito.mock(Migration.class);
        Mockito.when(second.identifier()).thenReturn((long) Tv.THOUSAND);
        final Migration third = Mockito.mock(Migration.class);
        Mockito.when(third.identifier()).thenReturn((long) Tv.HUNDRED);
        final Collection<Migration> migrations = new ArrayList<>(3);
        migrations.add(first);
        migrations.add(second);
        migrations.add(third);
        new MigrationSet(migrations).run();
        final InOrder order = Mockito.inOrder(first, third, second);
        order.verify(first).run();
        order.verify(third).run();
        order.verify(second).run();
    }
}
