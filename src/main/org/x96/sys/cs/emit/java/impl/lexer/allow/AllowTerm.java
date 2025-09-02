package org.x96.sys.cs.emit.java.impl.lexer.allow;

import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Term;
import org.x96.sys.cs.emit.java.arch.java.Matrix;
import org.x96.sys.util.S;

public class AllowTerm implements Allowing {
    private final Term term;

    public AllowTerm(Term term) {
        this.term = term;
    }

    @Override
    public Matrix allow() {
        Matrix matrix = new Matrix();
        matrix.g("// term");
        String k = S.toCamelCase(term.identity().raw());
        String m = term.negate() ? "denied" : "allowed";
        matrix.g(String.format("new %s(tokenizer).%s()", k, m));
        return matrix;
    }
}
