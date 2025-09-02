package org.x96.sys.cs.emit.java.impl.lexer.allow;

import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.Track;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.*;
import org.x96.sys.cs.emit.java.arch.java.Matrix;

public class AllowTrack implements Allowing {
    private final Track track;

    public AllowTrack(Track track) {
        this.track = track;
    }

    @Override
    public Matrix allow() {
        Matrix matrix = new Matrix();
        for (Nucleus n : track.nuclei()) {
            matrix.load(new AllowNucleus(n).allow());
            if (isFinished(matrix, n)) {
                return matrix;
            }
        }
        return matrix;
    }

}
