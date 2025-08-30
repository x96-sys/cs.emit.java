package org.x96.sys.cs.emit.java.impl.lexer.allow;

import org.x96.sys.cs.ir.manuscript.manifest.Manifest;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.Mesh;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.Track;
import org.x96.sys.cs.emit.java.arch.java.Matrix;

public class AllowManifest implements Allowing {

    private final Manifest t;

    public AllowManifest(Manifest t) {
        this.t = t;
    }

    @Override
    public Matrix allow() {
        Matrix matrix = new Matrix();
        matrix.setFeedback("boolean");
        matrix.setTag("allowed");
        matrix.g("return");
        switch (t.characterization().facet()) {
            case Mesh mesh -> {
                matrix.g("// mesh");
                matrix.load(new AllowMesh(mesh).allow());
            }
            case Track track -> {
                matrix.g("// track");
                matrix.load(new AllowTrack(track).allow());
            }
        }
        matrix.g(";");
        return matrix;
    }
}
