package org.x96.sys.cs.emit.self;

import org.junit.jupiter.api.Test;
import org.x96.sys.cs.emit.self.EmitNatural;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Natural;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmitNaturalTest {
    @Test
    void happy() {
        assertEquals("0xFF", new EmitNatural(new Natural(255)).toCS());
        assertEquals("0x10", new EmitNatural(new Natural(16)).toCS());
        assertEquals("0xF", new EmitNatural(new Natural(15)).toCS());
    }
}
