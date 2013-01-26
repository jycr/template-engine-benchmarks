// Autogenerated Jamon implementation
// T:/tmp/template-engine-benchmarks/java/templates/jamon/stocks.jamon

package jamon;

// 2, 3
import teb.model.Stock;

public class stocksImpl
  extends org.jamon.AbstractTemplateImpl
  implements jamon.stocks.Intf

{
  private final java.util.List<Stock> items;
  protected static jamon.stocks.ImplData __jamon_setOptionalArguments(jamon.stocks.ImplData p_implData)
  {
    return p_implData;
  }
  public stocksImpl(org.jamon.TemplateManager p_templateManager, jamon.stocks.ImplData p_implData)
  {
    super(p_templateManager, __jamon_setOptionalArguments(p_implData));
    items = p_implData.getItems();
  }
  
  @Override public void renderNoFlush(final java.io.Writer jamonWriter)
    throws java.io.IOException
  {
    // 7, 1
    
  int lc = 0;
  String klass = "";

    // 11, 1
    jamonWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"\n          \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\n <head>\n  <title>Stock Prices</title>\n  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n  <meta http-equiv=\"Content-Style-Type\" content=\"text/css\" />\n  <meta http-equiv=\"Content-Script-Type\" content=\"text/javascript\" />\n  <link rel=\"shortcut icon\" href=\"/images/favicon.ico\" />\n  <link rel=\"stylesheet\" type=\"text/css\" href=\"/css/style.css\" media=\"all\" />\n  <script type=\"text/javascript\" src=\"/js/util.js\"></script>\n  <style type=\"text/css\">\n  /*<![CDATA[*/\n\nbody {\n    color: #333333;\n    line-height: 150%;\n}\n\nthead {\n    font-weight: bold;\n    background-color: #CCCCCC;\n}\n\n.odd {\n    background-color: #FFCCCC;\n}\n\n.even {\n    background-color: #CCCCFF;\n}\n\n.minus {\n    color: #FF0000;\n}\n\n  /*]]>*/\n  </style>\n\n </head>\n\n <body>\n\n  <h1>Stock Prices</h1>\n\n  <table>\n   <thead>\n    <tr>\n     <th>#</th><th>symbol</th><th>name</th><th>price</th><th>change</th><th>ratio</th>\n    </tr>\n   </thead>\n   <tbody>\n");
    // 64, 1
    for (Stock item: items )
    {
      // 64, 27
      jamonWriter.write("\n    ");
      // 65, 5
      klass = (lc++ % 2 == 0) ? "even" : "odd";
      // 66, 5
      jamonWriter.write("<tr class=\"");
      // 66, 16
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(klass), jamonWriter);
      // 66, 27
      jamonWriter.write("\">\n     <td style=\"text-align: center\">");
      // 67, 37
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(lc), jamonWriter);
      // 67, 45
      jamonWriter.write("</td>\n     <td>\n      <a href=\"/stocks/");
      // 69, 24
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(item.getSymbol()), jamonWriter);
      // 69, 46
      jamonWriter.write("\">");
      // 69, 48
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(item.getSymbol()), jamonWriter);
      // 69, 70
      jamonWriter.write("</a>\n     </td>\n     <td>\n      <a href=\"");
      // 72, 16
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(item.getUrl()), jamonWriter);
      // 72, 35
      jamonWriter.write("\">");
      // 72, 37
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(item.getName()), jamonWriter);
      // 72, 57
      jamonWriter.write("</a>\n     </td>\n     <td>\n      <strong>");
      // 75, 15
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(item.getPrice()), jamonWriter);
      // 75, 36
      jamonWriter.write("</strong>\n     </td>\n     ");
      // 77, 6
      if (item.getChange() < 0.0 )
      {
        // 77, 36
        jamonWriter.write("\n     <td class=\"minus\">");
        // 78, 24
        org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(item.getChange()), jamonWriter);
        // 78, 46
        jamonWriter.write("</td>\n     <td class=\"minus\">");
        // 79, 24
        org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(item.getRatio()), jamonWriter);
        // 79, 45
        jamonWriter.write("</td>\n     ");
      }
      // 80, 6
      else
      {
        // 80, 13
        jamonWriter.write("\n     <td>");
        // 81, 10
        org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(item.getChange()), jamonWriter);
        // 81, 32
        jamonWriter.write("</td>\n     <td>");
        // 82, 10
        org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(item.getChange()), jamonWriter);
        // 82, 32
        jamonWriter.write("</td>\n     ");
      }
      // 83, 12
      jamonWriter.write("\n    </tr>\n");
    }
    // 85, 8
    jamonWriter.write("\n   </tbody>\n  </table>\n\n </body>\n</html>\n");
  }
  
  
}
