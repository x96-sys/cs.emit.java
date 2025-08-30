package org.x96.sys.cs.emit.java.impl.lexer.allow;

import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Range;
import org.x96.sys.cs.emit.java.arch.java.Matrix;

public class AllowRange implements Allowing {
    private final Range r;

    public AllowRange(Range range) {
        this.r = range;
    }

    @Override
    public Matrix allow() {
        Matrix matrix = new Matrix();
        matrix.g(String.format("(look() >= 0x%X && look() <= 0x%X)", r.from().b(), r.to().b()));
        return matrix;
    }
}
