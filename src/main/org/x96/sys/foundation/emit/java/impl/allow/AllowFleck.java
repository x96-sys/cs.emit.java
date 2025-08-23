package org.x96.sys.foundation.emit.java.impl.allow;

import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.*;
import org.x96.sys.foundation.emit.java.arch.java.Matrix;

public class AllowFleck implements Allowing {
    private final Fleck f;

    public AllowFleck(Fleck f) {
        this.f = f;
    }

    @Override
    public Matrix allow() {
        switch (f) {
            case Range range -> {
                return new AllowRange(range).allow();
            }
            case Switch s -> {
                return new AllowSwitch(s).allow();
            }
            case Term term -> {
                return new AllowTerm(term).allow();
            }
            case Text text -> {
                return new AllowText(text).allow();
            }
            case Trace trace -> {
                return new AllowTrace(trace).allow();
            }
        }
    }
}
