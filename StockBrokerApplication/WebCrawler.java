import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WebCrawler {

	public static Map<String, CurrencyRatesBean> parseXML() {

		try {
			final URL url = new URL("http://rates.fxcm.com/RatesXML");
			final InputStream inputStream = new BufferedInputStream(
					url.openStream());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputStream);

			doc.getDocumentElement().normalize();

			Map<String, CurrencyRatesBean> map = new HashMap<String, CurrencyRatesBean>();
			NodeList nList = doc.getElementsByTagName("Rate");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				CurrencyRatesBean bean = new CurrencyRatesBean();

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					bean.setSymbol(eElement.getAttribute("Symbol"));
					bean.setBid(Float.parseFloat(eElement
							.getElementsByTagName("Bid").item(0)
							.getTextContent()));
					bean.setAsk(eElement.getElementsByTagName("Ask").item(0)
							.getTextContent());
					bean.setHigh(eElement.getElementsByTagName("High").item(0)
							.getTextContent());
					bean.setLow(eElement.getElementsByTagName("Low").item(0)
							.getTextContent());
					bean.setDirection(eElement
							.getElementsByTagName("Direction").item(0)
							.getTextContent());
					bean.setLast(eElement.getElementsByTagName("Last").item(0)
							.getTextContent());
				}
				map.put(bean.getSymbol(), bean);
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
