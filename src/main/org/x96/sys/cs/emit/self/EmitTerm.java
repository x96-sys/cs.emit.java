package org.x96.sys.cs.emit.self;

import org.x96.sys.cs.emit.self.arch.Emit;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Term;

public class EmitTerm extends Emit<Term> {
    public EmitTerm(Term term) {
        super(term);
    }

    @Override
    public String toCS() {
        return String.format(
                "%s%s%s",
                t.negate() ? "!" : "",
                new EmitIdentity(t.identity()).toCS(),
                new EmitOccurrence(t.occurrence()).toCS());
    }
}
