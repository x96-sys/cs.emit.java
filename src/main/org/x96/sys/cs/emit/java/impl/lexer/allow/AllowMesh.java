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
        for (int i = 0; i < mesh.nuclei().length; i++) {
            Nucleus n = mesh.nuclei()[i];
            boolean isLast = i == mesh.nuclei().length - 1;
            matrix.load(new AllowNucleus(n).allow());
            if (isLast || isFinished(matrix, n)) {
                return matrix;
            }
        }
        return matrix;
    }
}
