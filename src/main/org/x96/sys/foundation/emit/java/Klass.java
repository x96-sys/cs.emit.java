
package org.x96.sys.foundation.emit.java;

import org.x96.sys.foundation.util.S;

public class Klass {
    public final byte[] name;
    public final Matrix[] cookies;
    public final String toCS;
    private final String extend;

    public Klass(byte[] name, Matrix[] cookies, String toCS, String extend) {
        this.name = name;
        this.cookies = cookies;
        this.toCS = toCS;
        this.extend = extend;
    }

    @Override
    public String toString() {
        String template = """
                // %s
                public class %s extends %s {

                    public %s(Tokenizer tokenizer) { super(tokenizer); }

                %s
                %s

                    @Override
                    public String overKind() { return "%s"; }

                }
                """.strip();
        return String.format(template, toCS, S.toCamelCase(name), extend, S.toCamelCase(name),
                cookies[0].toString(), cookies[1].toString(), new String(name));
    }
}
