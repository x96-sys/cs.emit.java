package org.x96.sys.cs.emit.self;

import org.junit.jupiter.api.Test;
import org.x96.sys.cs.emit.self.EmitTerm;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.occurrence.Occurrence;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Identity;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Term;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EmitTermTest {
    @Test
    void happy() {
        assertEquals("sofi", new EmitTerm(new Term(false, new Identity("sofi".getBytes()), Optional.empty())).toCS());
        assertEquals("!sofi", new EmitTerm(new Term(true, new Identity("sofi".getBytes()), Optional.empty())).toCS());
        assertEquals("ceci+",
                new EmitTerm(new Term(false, new Identity("ceci".getBytes()), Optional.of(Occurrence.OneOrMore)))
                        .toCS());
        assertEquals("!ceci?",
                new EmitTerm(new Term(true, new Identity("ceci".getBytes()), Optional.of(Occurrence.ZeroOrOne)))
                        .toCS());

    }
}
