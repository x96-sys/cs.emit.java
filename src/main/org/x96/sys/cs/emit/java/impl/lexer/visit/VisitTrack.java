package org.x96.sys.cs.emit.java.impl.lexer.visit;

import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.Track;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Nucleus;
import org.x96.sys.cs.emit.java.arch.java.Matrix;

public class VisitTrack implements Visiting {
    private final Track t;

    public VisitTrack(Track track) {
        this.t = track;
    }

    @Override
    public Matrix visit() {
        Matrix matrix = new Matrix();
        matrix.g("// track");
        for (Nucleus n : t.nuclei()) {
            matrix.load(new VisitNucleus(n).visit());
            if (isFinished(matrix, n)) {
                return matrix;
            }
        }
        return matrix;
    }

}
