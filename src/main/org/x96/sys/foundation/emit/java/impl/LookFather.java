package org.x96.sys.foundation.emit.java.impl;

import org.x96.sys.foundation.cs.ir.manuscript.manifest.Manifest;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.Mesh;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.Track;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.*;
import org.x96.sys.foundation.emit.java.arch.java.Klass;
import org.x96.sys.foundation.cs.lexer.token.Kind;

import org.x96.sys.foundation.util.S;

public class LookFather {

    private Klass k;
    private String n;
    private String i;
    private final Manifest t;

    public LookFather(Manifest t) {
        this.t = t;
        this.n = "0";
        switch (t.characterization().facet()) {
            case Mesh mesh -> {
                lookFatherMesh(mesh);
            }
            case Track track -> {
                lookFatherTrack(track);
            }
        }
        this.k = new Klass(n.getBytes(), "", null);
        if (i != null) {
            this.k.synapse(i);
        }
    }

    private void lookFatherTrack(Track track) {
        lookFatherNucleus(track.nuclei()[0]);
    }

    private void lookFatherMesh(Mesh mesh) {
        lookFatherNucleus(mesh.nuclei()[0]);
    }

    private void lookFatherNucleus(Nucleus nucleus) {
        switch (nucleus) {
            case Natural natural -> {
                lookFatherNatural(natural);
            }
            case Switch switcher -> {
                lookFatherSwitch(switcher);
            }
            case Term term -> {
                lookFatherTerm(term);
            }
            case Text text -> {
                lookFatherText(text);
            }
            case Trace trace -> {
                lookFatherTrace(trace);
            }
        }
    }

    private void lookFatherNatural(Natural n) {
        if (n.b() > 0x7F) {
            throw new RuntimeException("fora da faixa");
        } else {
            lookByte(n.b());
        }
    }

    private void lookByte(int b) {
        this.i = (String.format("org.x96.sys.foundation.cs.lexer.visitor.entry.terminals.c%s.%s",
                (b & 0xFF) / 0x10, S.toCamelCase(Kind.is(b).name())));
        this.n = S.toCamelCase(Kind.is(b).name());

        if (S.toCamelCase(t.identity().raw()).equals(n)) {
            this.n = this.i;
            this.i = null;
        }

    }

    private void lookFatherSwitch(Switch switcher) {
        this.i = "org.x96.sys.foundation.cs.lexer.visitor.Visitor";
        this.n = "Visitor";
    }

    private void lookFatherTerm(Term term) {
        this.n = S.toCamelCase(term.identity().raw());
    }

    private void lookFatherText(Text text) {
        if (text.raw().length > 1) {
            throw new UnsupportedOperationException(
                    String.format(
                            "LL(1) switch requires 1-byte text; got %d bytes.",
                            text.raw().length));
        }
        lookByte(text.raw()[0]);
    }

    private void lookFatherTrace(Trace trace) {
        lookFatherFleck(trace.flecks()[0]);
    }

    public Klass k() {
        return this.k;
    }

    private void lookFatherFleck(Fleck fleck) {
        switch (fleck) {
            case Range range -> {
                lookByte(range.from().b());
            }
            case Switch aSwitch -> {
            }
            case Term term -> {
                lookFatherTerm(term);
            }
            case Text text -> {
            }
            case Trace trace -> {
                lookFatherTrace(trace);
            }
        }
    }
}
