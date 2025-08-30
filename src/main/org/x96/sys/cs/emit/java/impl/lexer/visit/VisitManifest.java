package org.x96.sys.cs.emit.java.impl.lexer.visit;

import org.x96.sys.cs.ir.manuscript.manifest.Manifest;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.Mesh;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.Track;
import org.x96.sys.cs.emit.java.arch.java.Matrix;

public class VisitManifest implements Visiting {
    private final Manifest t;

    public VisitManifest(Manifest t) {
        this.t = t;
    }

    @Override
    public Matrix visit() {
        Matrix m = new Matrix();
        m.setFeedback("Token[]");
        m.setTag("visit");
        switch (t.characterization().facet()) {
            case Mesh mesh -> {
                m.load(new VisitMesh(mesh).visit());
            }
            case Track track -> {
                m.load(new VisitTrack(track).visit());
            }
        }
        if (t.characterization().archetype().isPresent()) {
            switch (t.characterization().archetype().get()) {
                case Ghost -> {
                    m.g("return stream();");
                }
                case Shell -> {
                    m.i("org.x96.sys.cs.ast.book.passage.pattern.modifier.Shell");
                    m.g("setMod(new Shell((byte) 0x40));");
                    m.g("return stream();");
                }
            }
        } else {
            m.g("return stream();");
        }
        return m;
    }
}
