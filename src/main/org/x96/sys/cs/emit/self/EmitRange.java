package org.x96.sys.cs.emit.self;

import org.x96.sys.cs.emit.self.arch.Emit;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Range;

public class EmitRange extends Emit<Range> {
    public EmitRange(Range range) {
        super(range);
    }

    @Override
    public String toCS() {
        return String.format("%s-%s", new EmitNatural(t.from()).toCS(), new EmitNatural(t.to()).toCS());
    }
}
