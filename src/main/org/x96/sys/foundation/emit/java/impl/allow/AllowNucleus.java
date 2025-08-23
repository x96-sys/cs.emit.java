package org.x96.sys.foundation.emit.java.impl.allow;

import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.*;
import org.x96.sys.foundation.emit.java.arch.java.Matrix;

public class AllowNucleus implements Allowing {
    private final Nucleus n;

    public AllowNucleus(Nucleus n) {
        this.n = n;
    }

    @Override
    public Matrix allow() {
        Matrix matrix = new Matrix();
        matrix.g("// nucleus");
        switch (n) {
            case Natural natural -> {
                matrix.load(new AllowNatural(natural).allow());
            }
            case Switch s -> {
                matrix.load(new AllowSwitch(s).allow());
            }
            case Term term -> {
                matrix.load(new AllowTerm(term).allow());
            }
            case Text text -> {
                matrix.load(new AllowText(text).allow());
            }
            case Trace trace -> {
                matrix.load(new AllowTrace(trace).allow());
            }
        }
        return matrix;
    }
}
