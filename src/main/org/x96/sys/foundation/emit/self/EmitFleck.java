package org.x96.sys.foundation.emit.self;

import org.x96.sys.foundation.emit.self.arch.Emit;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.*;

public class EmitFleck extends Emit<Fleck> {
    public EmitFleck(Fleck fleck) {
        super(fleck);
    }

    @Override
    public String toCS() {
        switch (t) {
            case Range range -> {
                return new EmitRange(range).toCS();
            }
            case Switch s -> {
                return new EmitSwitch(s).toCS();
            }
            case Term term -> {
                return new EmitTerm(term).toCS();
            }
            case Text text -> {
                return new EmitText(text).toCS();
            }
            case Trace trace -> {
                return new EmitTrace(trace).toCS();
            }
        }
    }
}
