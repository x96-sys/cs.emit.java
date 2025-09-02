/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.x96.sys.cs.emit.java.arch.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.x96.sys.util.S;

import java.util.ArrayList;
import java.util.List;

public class Klass {

    public byte[] name;
    public String pkt;
    public String root;
    public final String cs;
    public final List<String> synapses;
    public final List<Matrix> matrices;
    public final Klass father;

    public Klass(byte[] name, String cs, Klass father) {
        this.name = name;
        this.cs = cs;
        this.synapses = new ArrayList<>();
        this.matrices = new ArrayList<>();
        this.father = father;
    }

    public void setPkt(String pkt) {
        this.pkt = pkt;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public void synapse(String s) {
        this.synapses.add(s);
    }

    public void addMatrix(Matrix m) {
        matrices.add(m);
        for (String s : m.synapses) {
            if (!synapses.contains(s)) {
                synapses.add(s);
            }
        }
    }

    public void setName(byte[] name) {
        this.name = name;
    }

    public void touch() {
        try {
            // monta o caminho do arquivo
            String filePath = String.format(
                    "%s/%s/%s.java",
                    this.root,
                    this.pkt.replaceAll("\\.", "/"),
                    S.toCamelCase(name)
            );

            Path path = Path.of(filePath);

            // cria diretórios se não existirem
            Files.createDirectories(path.getParent());

            // cria arquivo com conteúdo (ou sobrescreve se já existir)
            Files.writeString(path, this.toString());

            System.out.println("Arquivo criado: " + path);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String k = S.toCamelCase(name);

        StringBuilder sb = new StringBuilder();

        if (pkt != null) {
            sb.append(String.format("package %s;%n%n", pkt));
        }

        if (father != null && !father.synapses.isEmpty()) {
            father.synapses.forEach(s -> {
                sb.append("import ").append(s).append(";\n");
            });
            sb.append("\n");
        }

        if (!synapses.isEmpty()) {
            synapses.forEach(s -> {
                sb.append("import ").append(s).append(";\n");
            });
            sb.append("\n");
        }

        sb.append("// ").append(cs).append("\n");
        sb.append("public class ").append(k);
        if (father != null) {
            sb.append(" extends ").append(new String(father.name));
        }
        sb.append(" {\n\n");
        sb.append("    public ").append(k).append("(Tokenizer tokenizer) { super(tokenizer); }\n\n");

        // adiciona cada Matrix gerada
        for (Matrix m : matrices) {
            sb.append(m.printSource()).append("\n");
        }

        sb.append("}\n");
        return sb.toString();
    }
}
