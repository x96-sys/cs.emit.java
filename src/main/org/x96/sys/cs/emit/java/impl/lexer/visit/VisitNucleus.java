package org.x96.sys.cs.emit.java.impl.lexer.visit;

import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.*;
import org.x96.sys.cs.emit.java.arch.java.Matrix;

public class VisitNucleus implements Visiting {
    private final Nucleus n;

    public VisitNucleus(Nucleus n) {
        this.n = n;
    }

    @Override
    public Matrix visit() {
        Matrix matrix = new Matrix();
        matrix.g("// nucleus");
        switch (n) {
            case Natural natural -> {
                matrix.load(new VisitNatural(natural).visit());
            }
            case Switch s -> {
                matrix.load(new VisitSwitch(s).visit());
            }
            case Term term -> {
                matrix.load(new VisitTerm(term).visit());
            }
            case Text text -> {
                matrix.load(new VisitText(text).visit());
            }
            case Trace trace -> {
                matrix.load(new VisitTrace(trace).visit());
            }
        }
        return matrix;
    }
}
