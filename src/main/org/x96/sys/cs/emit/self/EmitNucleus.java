package org.x96.sys.cs.emit.self;

import org.x96.sys.cs.emit.self.arch.Emit;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.*;

public class EmitNucleus extends Emit<Nucleus> {
    public EmitNucleus(Nucleus nucleus) {
        super(nucleus);
    }

    @Override
    public String toCS() {
        switch (t) {
            case Natural natural -> {
                return new EmitNatural(natural).toCS();
            }
            case Switch switcher -> {
                return new EmitSwitch(switcher).toCS();
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
