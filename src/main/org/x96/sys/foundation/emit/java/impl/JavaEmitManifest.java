package org.x96.sys.foundation.emit.java.impl;

import org.x96.sys.foundation.emit.java.impl.overKind.OverKindManifest;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.Manifest;
import org.x96.sys.foundation.emit.java.arch.emit.EmitJava;
import org.x96.sys.foundation.emit.java.arch.java.Klass;
import org.x96.sys.foundation.emit.java.impl.allow.AllowManifest;
import org.x96.sys.foundation.emit.java.impl.visit.VisitManifest;
import org.x96.sys.foundation.emit.self.EmitManifest;

public final class JavaEmitManifest extends EmitJava<Manifest> {

    private Klass j;
    private String pkt;
    public String root;

    public JavaEmitManifest(Manifest t) {
        super(t);
    }
    

    public void java() {
        Klass k = new Klass(t.identity().raw(), new EmitManifest(t).toCS(), new LookFather(t).k());
        k.synapse("org.x96.sys.foundation.cs.lexer.tokenizer.Tokenizer");
        k.synapse("org.x96.sys.foundation.cs.lexer.token.Token");
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
    
    public void touch(){
        j.touch();
    }
   

    @Override
    public String toJava() { 
        java();
        return j.toString();
    }
}
