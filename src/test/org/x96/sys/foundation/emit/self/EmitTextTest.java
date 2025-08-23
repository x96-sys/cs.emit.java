package org.x96.sys.foundation.emit.self;

import org.junit.jupiter.api.Test;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Text;

import static org.junit.jupiter.api.Assertions.*;

class EmitTextTest {
    @Test
    void happy(){
        assertEquals("'ceci'", new EmitText(new Text("ceci".getBytes())).toCS());
        assertEquals("'sofi'", new EmitText(new Text("sofi".getBytes())).toCS());
    }
}