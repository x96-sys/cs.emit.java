package org.x96.sys.cs.emit.java.impl.lexer;

import org.x96.sys.cs.emit.java.impl.lexer.overKind.OverKindManifest;
import org.x96.sys.cs.ir.manuscript.manifest.Manifest;
import org.x96.sys.cs.emit.java.arch.emit.EmitJava;
import org.x96.sys.cs.emit.java.arch.java.Klass;
import org.x96.sys.cs.emit.java.impl.lexer.allow.AllowManifest;
import org.x96.sys.cs.emit.java.impl.lexer.visit.VisitManifest;
import org.x96.sys.cs.emit.self.EmitManifest;

public final class JavaEmitManifest extends EmitJava<Manifest> {

    private Klass j;
    private String pkt;
    public String root;

    public JavaEmitManifest(Manifest t) {
        super(t);
    }

    public void java() {
        Klass k = new Klass(t.identity().raw(), new EmitManifest(t).toCS(), new LookFather(t).k());
        k.synapse("org.x96.sys.lexer.tokenizer.Tokenizer");
        k.synapse("org.x96.sys.lexer.token.Token");
        k.addMatrix(new VisitManifest(t).visit());
        k.addMatrix(new AllowManifest(t).allow());
        k.addMatrix(new OverKindManifest(t).overKind());
        k.setPkt(pkt);
        k.setRoot(root);
        this.j = k;
    }

    public void setPkt(String pkt) {
        this.pkt = pkt;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public void touch() {
        j.touch();
    }

    @Override
    public String toJava() {
        java();
        return j.toString();
    }
}
