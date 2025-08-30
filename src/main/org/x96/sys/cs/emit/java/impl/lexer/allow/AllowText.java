package org.x96.sys.cs.emit.java.impl.lexer.allow;

import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Text;
import org.x96.sys.cs.emit.java.arch.java.Matrix;

public class AllowText implements Allowing {
    private final Text t;

    public AllowText(Text text) {
        this.t = text;
    }

    @Override
    public Matrix allow() {
        Matrix matrix = new Matrix();
        matrix.g("// text");
        if (t.raw().length > 1) {
            throw new UnsupportedOperationException(
                    String.format(
                            "LL(1) switch requires 1-byte text; got %d bytes.",
                            t.raw().length));
        }
        matrix.g(String.format("%s == 0x%X", "look()", t.raw()[0]));
        return matrix;
    }
}
