package org.x96.sys.foundation.emit.java.impl.visit;

import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Range;
import org.x96.sys.foundation.emit.java.arch.java.Matrix;

public class VisitRange implements Visiting {
    private final Range r;

    public VisitRange(Range range) {
        this.r = range;
    }


    @Override
    public Matrix visit() {
        Matrix m = new Matrix();
        m.g("// range");
        m.i("org.x96.sys.foundation.buzz.cs.lexer.visitor.BuzzVisitorMismatch");
        String condition = String.format("look() < 0x%X || look() > 0x%X", r.from().b(), r.to().b());
        m.g(String.format("if (%s) throw new BuzzVisitorMismatch(this, this.tokenizer);", condition));
        m.g("rec();");
        return m;
    }
}
