package org.x96.sys.foundation.emit.java.impl.visit;

import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.*;
import org.x96.sys.foundation.cs.lexer.token.Kind;
import org.x96.sys.foundation.emit.java.arch.java.Matrix;
import org.x96.sys.foundation.util.S;

public class VisitSwitch implements Visiting {
    private final Switch s;

    public VisitSwitch(Switch s) {
        this.s = s;
    }

    @Override
    public Matrix visit() {
        Matrix m = new Matrix();
        m.g("// switch");
        m.i("org.x96.sys.foundation.cs.lexer.router.switcher.Switcher");
        m.g("Switcher switcher = new Switcher();");
        for (Nucleus n : s.nuclei()) {
            switcher(m, n);
        }
        m.g("push(switcher.stream(tokenizer));");
        return m;
    }

    private void switcher(Matrix m, Nucleus n) {
        switch (n) {
            case Natural natural -> {
                m.g("// switch natural");
                if (Kind.is((byte) natural.b()) == Kind.UNKNOWN) {
                    throw new UnsupportedOperationException(
                            String.format("Unsupported byte value %d [0x%X] for LL(1) switch (Kind.UNKNOWN).",
                                    natural.b(), natural.b()));
                }
                byte b = (byte) natural.b();
                m.i(String.format(
                        "org.x96.sys.foundation.cs.lexer.visitor.entry.terminals.c%s.%s",
                        (b & 0xFF) / 0x10,
                        S.toCamelCase(Kind.is(b).name())));
                m.g(String.format("switcher.know(%s.class);", (S.toCamelCase(Kind.is(b).name()))));
            }
            case Switch aSwitch -> {
                m.g("// switch switch");
            }
            case Term term -> {
                m.g("// switch term");
                m.g(String.format("switcher.know(%s.class);", S.toCamelCase(term.identity().raw())));
            }
            case Text text -> {
                m.g("// switch text");
                if (text.raw().length > 1) {
                    throw new UnsupportedOperationException(
                            String.format(
                                    "LL(1) switch requires 1-byte text; got %d bytes.",
                                    text.raw().length));
                }
                byte b = text.raw()[0];
                m.i(String.format(
                        "org.x96.sys.foundation.cs.lexer.visitor.entry.terminals.c%s.%s",
                        (b & 0xFF) / 0x10,
                        S.toCamelCase(Kind.is(b).name())));
                m.g(String.format("switcher.know(%s.class);", (S.toCamelCase(Kind.is(b).name()))));
            }
            case Trace trace -> {
                m.g("// switch trace");
            }
        }
    }
}
