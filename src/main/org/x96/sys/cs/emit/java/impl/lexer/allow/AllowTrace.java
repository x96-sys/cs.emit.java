package org.x96.sys.cs.emit.java.impl.lexer.allow;

import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Fleck;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Trace;
import org.x96.sys.cs.emit.java.arch.java.Matrix;

public class AllowTrace implements Allowing {
    private final Trace trace;

    public AllowTrace(Trace trace) {
        this.trace = trace;
    }

    @Override
    public Matrix allow() {
        Matrix matrix = new Matrix();
        for (Fleck f : trace.flecks()) {
            matrix.load(new AllowFleck(f).allow());
            if (isFinished(matrix, f)) {
                return matrix;
            }
        }
        return matrix;
    }
}
