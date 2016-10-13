/*
 * $Copyright: copyright(c) 2007-2011 kuwata-lab.com all rights reserved. $
 * $License: Creative Commons Attribution (CC BY) $
 */
package teb;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.Template;
import freemarker.template.Version;
import teb.model.Stock;

public class FreeMarker extends _BenchBase {

    private Configuration cfg;
    public FreeMarker() throws Exception {
        cfg = new Configuration(new Version(2, 3, 23));
        cfg.setTemplateLoader(new ClassTemplateLoader(ClassLoader.getSystemClassLoader(),"templates"));
    }

    @Override
    public void execute(Writer w0, Writer w1, int ntimes, List<Stock> items) throws Exception {
        Map root = new HashMap();
        root.put("items", items);
        while (--ntimes >= 0) {
            Template template = cfg.getTemplate("stocks.ftl.html");

            if (ntimes == 0) {
                template.process(root,w1);
                w1.close();
            }
            else template.process(root, w0);
        }
    }

    @Override
    public void execute(OutputStream o0, OutputStream o1, int ntimes, List<Stock> items) throws Exception {
        Map root = new HashMap();
        root.put("items", items);
        Writer w0 = new OutputStreamWriter(o0);
        Writer w1 = new OutputStreamWriter(o1);
        if (_BenchBase.bufferMode.get()) {
            w0 = new BufferedWriter(w0);
            w1 = new BufferedWriter(w1);
        }
        while (--ntimes >= 0) {
            Template template = cfg.getTemplate("stocks.ftl.html");

            if (ntimes == 0) {
                template.process(root, w1);
                w1.close();
            }
            else template.process(root, w0);
        }
    }

    @Override
    protected String execute(int ntimes, List<Stock> items) throws Exception {
        Map root = new HashMap();
        root.put("items", items);
        Writer w0 = new StringWriter();
        Writer w1 = new StringWriter();
        if (_BenchBase.bufferMode.get()) {
            w0 = new BufferedWriter(w0);
            w1 = new BufferedWriter(w1);
        }
        while (--ntimes >= 0) {
            Template template = cfg.getTemplate("stocks.ftl.html");

            if (ntimes == 0) {
                template.process(root, w1);
                w1.close();
            }
            else template.process(root, w0);
        }

        return w1.toString();
    }

    public static void main(String[] args) throws Exception {
        new FreeMarker().run();
    }

}
