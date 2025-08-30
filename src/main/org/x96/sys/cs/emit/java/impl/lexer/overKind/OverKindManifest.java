package org.x96.sys.cs.emit.java.impl.lexer.overKind;

import org.x96.sys.cs.ir.manuscript.manifest.Manifest;
import org.x96.sys.cs.emit.java.arch.java.Matrix;
import org.x96.sys.cs.emit.java.impl.lexer.OverKind;

public class OverKindManifest implements OverKind {
    private final Manifest t;

    public OverKindManifest(Manifest t) {
        this.t = t;
    }

    @Override
    public Matrix overKind() {
        Matrix matrix = new Matrix();
        matrix.setTag("overKind");
        matrix.setFeedback("String");
        matrix.g(String.format("return \"%s\";", new String(t.identity().raw())));
        return matrix;
    }
}
