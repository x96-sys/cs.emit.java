package org.x96.sys.cs.emit.java.impl.lexer.visit;

import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Text;
import org.x96.sys.cs.emit.java.arch.java.Matrix;

public class VisitText implements Visiting {
    private final Text t;

    public VisitText(Text text) {
        this.t = text;
    }

    @Override
    public Matrix visit() {
        Matrix matrix = new Matrix();
        matrix.g("// text");
        for (byte b : t.raw()) {
            visitByte(matrix, b);
        }
        return matrix;
    }
}
