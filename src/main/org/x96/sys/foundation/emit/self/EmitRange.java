package org.x96.sys.foundation.emit.self;

import org.x96.sys.foundation.emit.self.arch.Emit;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Range;

public class EmitRange extends Emit<Range> {
    public EmitRange(Range range) {
        super(range);
    }

    @Override
    public String toCS() {
        return String.format("%s-%s", new EmitNatural(t.from()).toCS(), new EmitNatural(t.to()).toCS());
    }
}
