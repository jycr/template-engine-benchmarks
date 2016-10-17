package teb;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
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

import teb.model.Stock;

public class XsltBenchmark extends BaseBenchmark {
	private Templates template;
	private JAXBContext context;

	@Override
	public void setup() throws Exception {
		final Source xsltSource = new StreamSource(getTemplateReader("xslt"));
		final TransformerFactory transFact = TransformerFactory.newInstance();
		template = transFact.newTemplates(xsltSource);
		context = JAXBContext.newInstance(Model.class);

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
		public final List<Stock> item = new ArrayList<>();

		@SuppressWarnings("unchecked")
		public static final Model create(final Map<String, Object> map) {
			final Model result = new Model();
			result.item.addAll((Collection<? extends Stock>) map.get("items"));
			return result;
		}
	}
}