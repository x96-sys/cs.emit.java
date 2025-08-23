package org.x96.sys.foundation.emit.java.impl.visit;

import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.Track;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Nucleus;
import org.x96.sys.foundation.emit.java.arch.java.Matrix;
import org.x96.sys.foundation.emit.java.impl.allow.AllowNucleus;

public class VisitTrack  implements Visiting {
    private final Track t;

    public VisitTrack(Track track) {
        this.t = track;
    }

    @Override
    public Matrix visit() {
        Matrix matrix = new Matrix();
        matrix.g("// track");
        for (Nucleus n: t.nuclei()) {
            matrix.load(new VisitNucleus(n).visit());
            if (isFinished(matrix, n)) {
                return matrix;
            }
        }
        return matrix;
    }


}
