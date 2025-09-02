package org.x96.sys.cs.emit.java.impl.lexer.visit;

import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.*;
import org.x96.sys.cs.emit.java.arch.java.Matrix;

public class VisitFleck implements Visiting {
    private final Fleck f;

    public VisitFleck(Fleck f) {
        this.f = f;
    }

    @Override
    public Matrix visit() {
        Matrix matrix = new Matrix();
        matrix.g("// fleck");
        switch (f) {
            case Range range -> {
                matrix.load(new VisitRange(range).visit());
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
