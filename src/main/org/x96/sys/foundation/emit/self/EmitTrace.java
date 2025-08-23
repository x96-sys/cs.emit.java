package org.x96.sys.foundation.emit.self;

import org.x96.sys.foundation.emit.self.arch.Emit;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Trace;

public class EmitTrace extends Emit<Trace> {

    public EmitTrace(Trace trace) {
        super(trace);
    }

    @Override
    public String toCS() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < t.flecks().length; i++) {
            sb.append(new EmitFleck(t.flecks()[i]).toCS());
            if (i < t.flecks().length - 1) {
                sb.append(" ");
            }
        }
        sb.append("]");
        sb.append(new EmitOccurrence(t.occurrence()).toCS());
        return sb.toString();
    }
}
