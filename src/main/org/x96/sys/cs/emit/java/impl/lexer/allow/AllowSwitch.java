package org.x96.sys.cs.emit.java.impl.lexer.allow;

import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Nucleus;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Switch;
import org.x96.sys.cs.emit.java.arch.java.Matrix;

public class AllowSwitch implements Allowing {
    private final Switch s;

    public AllowSwitch(Switch s) {
        this.s = s;
    }

    @Override
    public Matrix allow() {
        Matrix matrix = new Matrix();
        matrix.g("// switch");
        var nuclei = s.nuclei();
        for (int i = 0; i < nuclei.length; i++) {
            Nucleus n = nuclei[i];
            matrix.load(new AllowNucleus(n).allow());
            if (i < nuclei.length - 1) {
                matrix.g("||"); // só adiciona se não for o último
            }
        }
        return matrix;
    }
}
