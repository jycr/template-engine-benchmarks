/*
 * $Copyright: copyright(c) 2007-2011 kuwata-lab.com all rights reserved. $
 * $License: Creative Commons Attribution (CC BY) $
 */
package teb;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.asfun.jangod.base.Configuration;
import net.asfun.jangod.base.ResourceLocater;
import net.asfun.jangod.base.ResourceManager;
import net.asfun.jangod.template.TemplateEngine;
import teb.model.Stock;
import teb.util.ClasspathResourceUtils;

public class Jangod extends _BenchBase {

    TemplateEngine engine;
    private String template = "templates/stocks.jangod.html";
    
    public Jangod() {
        engine = new TemplateEngine();
        engine.getConfiguration().setWorkspace(".");
        setupConfig(engine);
    }

    // Tricks to change ResourceLocater
    protected void setupConfig(final TemplateEngine engine) {
        final Configuration config = engine.getConfiguration();
        try {
            final Field propertiesField = ResourceManager.class.getDeclaredField("locater");
            propertiesField.setAccessible(true);
            propertiesField.set(config, new ClasspathLocator());
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void execute(Writer w0, Writer w1, int ntimes, List<Stock> items) throws Exception {
        String output;
        String tmpl = template;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("items", items);
        while (--ntimes >= 0) {
            output = engine.process(tmpl, params);
            if (ntimes == 0) {
                w1.write(output);
                w1.close();
            }
            else w0.write(output);
        }
    }

    @Override
    protected void execute(OutputStream o0, OutputStream o1, int ntimes, List<Stock> items) throws Exception {
        String output;
        String tmpl = template;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("items", items);
        Writer w1 = new OutputStreamWriter(o1);
        Writer w0 = new OutputStreamWriter(o0);
        if (_BenchBase.bufferMode.get()) {
            w0 = new BufferedWriter(w0);
            w1 = new BufferedWriter(w1);
        }
        while (--ntimes >= 0) {
            output = engine.process(tmpl, params);
            if (ntimes == 0) {
                w1.write(output);
                w1.close();
            }
            else w0.write(output);
        }
    }

    @Override
    protected String execute(int ntimes, List<Stock> items) throws Exception {
        String output = null;
        String tmpl = template;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("items", items);
        while (--ntimes >= 0) {
            output = engine.process(tmpl, params);
        }
        
        return output;
    }

    public static void main(String[] args) {
        new Jangod().run();
    }

    public static class ClasspathLocator implements ResourceLocater {

        @Override
        public String getFullName(final String relativeName, final String relativeDir, final String defaultDir) throws IOException {
            return relativeDir + "/" + relativeName;
        }

        @Override
        public String getFullName(final String relativeName, final String defaultDir) throws IOException {
            return defaultDir + "/" + relativeName;
        }

        @Override
        public String getDirectory(final String fullName) throws IOException {
            return fullName.replaceFirst("[^/]+$", "");
        }

        @Override
        public Reader getReader(final String fullName, final String encoding) throws IOException {
            return ClasspathResourceUtils.getReader(fullName, encoding);
        }

        @Override
        public String getString(final String fullName, final String encoding) throws IOException {
            return ClasspathResourceUtils.getAsString(fullName, encoding);
        }
    }
}