package org.x96.sys.cs.emit.java.impl.lexer.visit;

import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Nucleus;
import org.x96.sys.cs.emit.java.arch.java.Matrix;

public interface Visiting {
    public Matrix visit();

    public default boolean isFinished(Matrix matrix, Nucleus n) {
        return false;
    }

    public default void visitByte(Matrix m, byte b) {
        m.i("org.x96.sys.buzz.lexer.visitor.BuzzVisitorMismatch");
        m.g(String.format("if (look() != 0x%X) throw new BuzzVisitorMismatch(this, this.tokenizer);", b));
        m.g("rec(overKind());"); // TODO: mod (rec(overKind()) | rec())
    }
}
