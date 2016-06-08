package com.proemion.helenus.cassandra.revisions;

import com.proemion.helenus.cassandra.Migration;

/**
 * Cassandra Schema Version.
 * @author Armin Braun (armin.braun@proemion.com)
 * @version $Id$
 * @since 0.1
 */
public interface SchemaVersion {

    /**
     * Returns the version identifier.
     * @return version identifier
     */
    long version();

    /**
     * Returns diff to get to another version.
     */
    Migration diff(long version);
}
