package org.x96.sys.foundation.emit.java.impl.visit;

import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Nucleus;
import org.x96.sys.foundation.emit.java.arch.java.Matrix;

public interface Visiting {
    public Matrix visit();

    public default boolean isFinished(Matrix matrix, Nucleus n) {
        return false;
    }

    public default void visitByte(Matrix m, byte b) {
        m.i("org.x96.sys.foundation.buzz.cs.lexer.visitor.BuzzVisitorMismatch");
        m.g(String.format("if (look() != 0x%X) throw new BuzzVisitorMismatch(this, this.tokenizer);", b));
        m.g("rec(overKind());"); // TODO: mod (rec(overKind()) | rec())
    }
}
