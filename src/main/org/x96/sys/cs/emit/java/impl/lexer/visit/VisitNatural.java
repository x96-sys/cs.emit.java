package org.x96.sys.cs.emit.java.impl.lexer.visit;

import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Natural;
import org.x96.sys.cs.emit.java.arch.java.Matrix;

public class VisitNatural implements Visiting {
    private final Natural n;

    public VisitNatural(Natural natural) {
        this.n = natural;
    }

    @Override
    public Matrix visit() {
        Matrix m = new Matrix();
        m.g("// natural");
        visitByte(m, (byte) n.b());
        return m;
    }
}
