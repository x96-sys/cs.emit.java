package org.x96.sys.foundation.emit.java.impl.visit;

import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.Mesh;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Nucleus;
import org.x96.sys.foundation.emit.java.arch.java.Matrix;

public class VisitMesh implements Visiting {
    private final Mesh m;

    public VisitMesh(Mesh mesh) {
        this.m = mesh;
    }

//    @Override
//    public Matrix visit() {
//        Matrix matrix = new Matrix();
//        matrix.g("// mesh");
//        return matrix;
//    }
    @Override
    public Matrix visit() {
        Matrix matrix = new Matrix();
        matrix.g("// track");
        for (Nucleus n: m.nuclei()) {
            matrix.load(new VisitNucleus(n).visit());
            if (isFinished(matrix, n)) {
                return matrix;
            }
        }
        return matrix;
    }
}
