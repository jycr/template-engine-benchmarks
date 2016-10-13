package teb;

import java.io.*;
import java.util.*;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

import teb.model.Stock;
import teb.util.ClasspathResourceUtils;

public class JMustacheBenchmark extends _BenchBase {

    static Mustache.Compiler compiler;

    public JMustacheBenchmark() {
        compiler = Mustache.compiler().withLoader(new Mustache.TemplateLoader() { 
            @Override
            public Reader getTemplate(String name) throws Exception {
                return new FileReader(new File("templates/", name + ".mustache.html"));
            }
        });
    }

    @Override
    public void execute(Writer w0, Writer w1, int ntimes, List<Stock> items) throws Exception {
        Map<String,Object> root = initContextData(items);
        render(w0, w1, ntimes, root);
    }

    @Override
    public void execute(OutputStream o0, OutputStream o1, int ntimes, List<Stock> items) throws Exception {
        Writer w0 = new OutputStreamWriter(o0);
        Writer w1 = new OutputStreamWriter(o1);
        if (_BenchBase.bufferMode.get()) {
            w0 = new BufferedWriter(w0);
            w1 = new BufferedWriter(w1);
        }
        execute(w0, w1, ntimes, items);
    }

    @Override
    protected String execute(int ntimes, List<Stock> items) throws Exception {
        Writer w0 = new StringWriter();
        Writer w1 = new StringWriter();
        if (_BenchBase.bufferMode.get()) {
            w0 = new BufferedWriter(w0);
            w1 = new BufferedWriter(w1);
        }
        execute(w0, w1, ntimes, items);
        return w1.toString();
    }

    private Map<String,Object> initContextData(List<Stock> items) {
        Map<String,Object> root = new HashMap<String,Object>();
        root.put("items", items);
        return root;
    }

    private void render(Writer w0, Writer w1, int ntimes, Map<String,Object> root) throws IOException {
        Template template = compiler.compile(ClasspathResourceUtils.getReader("templates/stocks.mustache.html"));
        while (--ntimes >= 0) {

            if (ntimes == 0) {
                template.execute(root, w1);
                w1.close();
            } else
                template.execute(root, w0);
        }
    }

    public static void main(String[] args) {
        new JMustacheBenchmark().run();
    }

}
