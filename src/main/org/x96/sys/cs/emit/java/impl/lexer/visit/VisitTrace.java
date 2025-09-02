package org.x96.sys.cs.emit.java.impl.lexer.visit;

import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Fleck;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Trace;
import org.x96.sys.cs.emit.java.arch.java.Matrix;

public class VisitTrace implements Visiting {
    private final Trace t;

    public VisitTrace(Trace trace) {
        this.t = trace;
    }

    @Override
    public Matrix visit() {
        Matrix matrix = new Matrix();
        matrix.g("// trace");
        for (Fleck f : this.t.flecks()) {
            matrix.load(new VisitFleck(f).visit());
        }
        return matrix;
    }
}
