package org.x96.sys.cs.emit.java;
// represent a block of code
public class Matrix {
    // represent each line/instruction/statement/piece of code
    public String[] signals;
    // represent imports
    public String[] synapses;
    // represent a name
    public String tag;
    // represent a return type
    public String feedback;

    public Matrix(String[] signals, String[] synapses) {
        if (signals == null) throw new NullPointerException("code");
        if (synapses == null) throw new NullPointerException("imports");
        this.signals = signals;
        this.synapses = synapses;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return String.format("%s%n", printSource());
    }

    public String printSource() {
        return String.format(
                """
                    @Override
                    public %s %s() {
                        %s
                    }
                """,
                this.feedback, this.tag, String.join("\n        ", this.signals));
    }

    public String printImport() {
        if (synapses.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (String s : synapses) {
            if (!s.isEmpty()) sb.append(String.format("import %s;\n", s));
        }
        return sb.toString();
    }    
}
