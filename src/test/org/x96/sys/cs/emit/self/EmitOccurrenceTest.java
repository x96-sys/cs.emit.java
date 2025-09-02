package org.x96.sys.cs.emit.self;

import org.junit.jupiter.api.Test;
import org.x96.sys.cs.emit.self.EmitOccurrence;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.occurrence.Occurrence;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EmitOccurrenceTest {

    @Test
    void happy() {
        assertEquals("", new EmitOccurrence(Optional.empty()).toCS());
        assertEquals("?", new EmitOccurrence(Optional.of(Occurrence.ZeroOrOne)).toCS());
        assertEquals("*", new EmitOccurrence(Optional.of(Occurrence.ZeroOrMore)).toCS());
        assertEquals("+", new EmitOccurrence(Optional.of(Occurrence.OneOrMore)).toCS());
    }
}
