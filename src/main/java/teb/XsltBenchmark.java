package teb;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.openjdk.jmh.annotations.Benchmark;

import teb.model.Stock;
import teb.model.XmlResponse;

public class XsltBenchmark extends BaseBenchmark {
	private Templates template;
	private JAXBContext context;

	@Override
	public void setup() throws Exception {
		final Source xsltSource = new StreamSource(getTemplateReader(".xslt"));
		final TransformerFactory transFact = TransformerFactory.newInstance();
		template = transFact.newTemplates(xsltSource);
		context = JAXBContext.newInstance(Model.class);
		// debugPrintXmlDataSource();
	}

	public void debugPrintXmlDataSource() throws IOException, JAXBException {
		try (final StringWriter output = new StringWriter()) {
			final Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(Model.create(getParams()), output);
			output.flush();
			System.out.println("================================================================================");
			System.out.println(output);
			System.out.println("================================================================================");
		}
	}

	@Override
	@Benchmark
	public void run() {
		try {
			final JAXBSource input = new JAXBSource(
					context,
					Model.create(getParams()));
			final Result output = new StreamResult(getOutput());
			template
					.newTransformer()
					.transform(input, output);
		} catch (final TransformerException | JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(final String[] args) {
		new XsltBenchmark().test();
	}

	@XmlRootElement
	public static class Model {
		public List<Stock> item;
		public XmlResponse xmlResponse;

		@SuppressWarnings("unchecked")
		public static final Model create(final Map<String, Object> map) {
			final Model result = new Model();
			if (map.get("items") != null) {
				result.item = (List<Stock>) map.get("items");
			} else if (map.get("xmlResponse") != null) {
				result.xmlResponse = (XmlResponse) map.get("xmlResponse");
			}
			return result;
		}
	}
}