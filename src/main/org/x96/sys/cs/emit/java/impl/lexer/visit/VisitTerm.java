package org.x96.sys.cs.emit.java.impl.lexer.visit;

import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Term;
import org.x96.sys.cs.emit.java.arch.java.Matrix;
import org.x96.sys.cs.emit.java.impl.lexer.allow.AllowTerm;
import org.x96.sys.util.S;

public class VisitTerm implements Visiting {
    private final Term t;

    public VisitTerm(Term term) {
        this.t = term;
    }

    @Override
    public Matrix visit() {
        Matrix m = new Matrix();
        m.g("// term");
        String k = S.toCamelCase(this.t.identity().raw());
        if (t.occurrence().isPresent()) {
            gerarCodigoComOccurrence(m, k);
        } else {
            if (t.negate()) {
                m.i("org.x96.sys.buzz.lexer.visitor.BuzzVisitorMismatch");
                String a = String.format("new %s(tokenizer).allowed()", k);
                String b = "throw new BuzzVisitorMismatch(this, this.tokenizer)";
                m.g(String.format("if (%s) %s;", a, b));
            } else {
                m.g(String.format("push(new %s(tokenizer).safeVisit());", k));
            }
        }
        return m;
    }

    private void gerarCodigoComOccurrence(Matrix m, String k) {
        Matrix matrix = new AllowTerm(this.t).allow();
        String i = " ".repeat(4);

        String cond = String.join("\n" + i.repeat(3), matrix.signals);

        switch (t.occurrence().get()) {
            case ZeroOrMore -> {
                m.g(String.format("while (%s) {", cond));
                m.g(String.format("%spush(new %s(tokenizer).safeVisit());", i, k));
                m.g("}");
            }
            case OneOrMore -> {
                m.g("do {");
                m.g(String.format("%spush(new %s(tokenizer).safeVisit());", i, k));
                m.g(String.format("} while (%s);", cond));
            }
            case ZeroOrOne -> {
                m.g(String.format("if (%s) {", cond));
                m.g(String.format("%spush(new %s(tokenizer).safeVisit());", i, k));
                m.g("}");
            }
        }
    }

}
