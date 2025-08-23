package org.x96.sys.foundation.emit.self;

import org.x96.sys.foundation.emit.self.arch.Emit;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Nucleus;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Switch;

public class EmitSwitch extends Emit<Switch> {
    public EmitSwitch(Switch aSwitch) {
        super(aSwitch);
    }

    @Override
    public String toCS() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (Nucleus n: t.nuclei()) {
            sb.append(new EmitNucleus(n).toCS());
            if (t.nuclei()[t.nuclei().length - 1] != n){
                sb.append(" | ");
            }
        }
        sb.append(")");
        sb.append(new EmitOccurrence(t.occurrence()).toCS());
        return sb.toString();

    }
}
