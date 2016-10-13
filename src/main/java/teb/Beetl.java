/*
 * $Copyright: copyright(c) 2007-2011 kuwata-lab.com all rights reserved. $
 * $License: Creative Commons Attribution (CC BY) $
 */
package teb;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;

import teb.model.Stock;

public class Beetl extends _BenchBase {

    GroupTemplate group;
    private String template = "templates/";
    
    public Beetl() throws IOException {
        final Configuration cfg = Configuration.defaultConfiguration();
        cfg.setStatementStart("<!--:");
        cfg.setStatementEnd("-->");
        cfg.setPlaceholderStart("${");
        cfg.setPlaceholderEnd("}");
        group = new GroupTemplate(new FileResourceLoader(template, "UTF-8"), cfg);
    }
    
	@Override
    protected void shutdown() {
    }

    @Override
    public void execute(Writer w0, Writer w1, int ntimes, List<Stock> items) throws Exception {
        while (--ntimes >= 0) {
            Template template = group.getTemplate("/stocks.beetl.html");
            template.binding("items", items);
            
            if (ntimes == 0) {
                template.renderTo(w1);
                w1.close();
            }
            else template.renderTo(w0);
        }
    }
    
    @Override
    public void execute(OutputStream o0, OutputStream o1, int ntimes, List<Stock> items) throws Exception {
        while (--ntimes >= 0) {
            Template template = group.getTemplate("/stocks.beetl.html");
            template.binding("items", items);
            
            if (ntimes == 0) {
                template.renderTo(o1);
                o1.close();
            }
            else template.renderTo(o0);
        }
    }

    @Override
    protected String execute(int ntimes, List<Stock> items) throws Exception {
        Writer w0 = new StringWriter(1024 * 10);
        Writer w1 = new StringWriter(1024 * 10);
        if (_BenchBase.bufferMode.get()) {
            w0 = new BufferedWriter(w0);
            w1 = new BufferedWriter(w1);
        }
        while (--ntimes >= 0) {
            Template template = group.getTemplate("/stocks.beetl.html");
            template.binding("items", items);
            
            if (ntimes == 0) {
                template.renderTo(w1);
                w1.close();
            }
            else template.renderTo(w0);
        }
        
        return w1.toString();
    }

    public static void main(String[] args) throws IOException {
        new Beetl().run();
    }
}
