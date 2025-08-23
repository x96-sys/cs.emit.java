package org.x96.sys.foundation.emit.java.impl.allow;

import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Natural;
import org.x96.sys.foundation.emit.java.arch.java.Matrix;

public class AllowNatural implements Allowing {
    private final Natural natural;

    public AllowNatural(Natural natural) {
        this.natural = natural;
    }

    @Override
    public Matrix allow() {
        Matrix matrix = new Matrix();
        matrix.g("// natural");
        matrix.g(String.format("%s == 0x%X", "look()", natural.b()));
        return matrix;
    }
}
