package org.x96.sys.cs.emit.java.impl.lexer.allow;

import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.Mesh;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.*;
import org.x96.sys.cs.emit.java.arch.java.Matrix;

public class AllowMesh implements Allowing {
    private final Mesh mesh;

    public AllowMesh(Mesh mesh) {
        this.mesh = mesh;
    }

    @Override
    public Matrix allow() {
        Matrix matrix = new Matrix();
        for (Nucleus n : mesh.nuclei()) {
            matrix.load(new AllowNucleus(n).allow());
            if (isFinished(matrix, n)) {
                return matrix;
            }
        }
        return matrix;
    }

}
