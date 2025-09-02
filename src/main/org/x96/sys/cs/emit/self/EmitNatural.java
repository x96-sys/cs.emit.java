package org.x96.sys.cs.emit.self;

import org.x96.sys.cs.emit.self.arch.Emit;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Natural;

public class EmitNatural extends Emit<Natural> {
    public EmitNatural(Natural natural) {
        super(natural);
    }

    @Override
    public String toCS() {
        return String.format("0x%X", t.b());
    }
}
