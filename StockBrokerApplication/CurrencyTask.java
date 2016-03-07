import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

public class CurrencyTask extends TimerTask {

	static List<CurrencyRatesBean> beansList = null;

	public CurrencyTask(List<CurrencyRatesBean> list) {
		beansList = new ArrayList<CurrencyRatesBean>();
		for (CurrencyRatesBean currencyValueListTemp : list) {
			beansList.add(currencyValueListTemp);
		}
	}

	@Override
	public void run() {
		alertUser(WebCrawler.parseXML());
	}

	private void alertUser(Map<String, CurrencyRatesBean> map) {
		CurrencyRatesBean currencyRatesBean = null;
		for (int i = beansList.size() - 1; i >= 0; i--) {
			currencyRatesBean = beansList.get(i);
			if (map.containsKey(currencyRatesBean.getSymbol())) {
				CurrencyRatesBean bean = map.get(currencyRatesBean.getSymbol());
				float bid = bean.getBid();
				System.out.println(" Current Bid Value:: " + bean.getBid()
						+ " for currency " + bean.getSymbol());
				if (currencyRatesBean.getTarget() >= currencyRatesBean.getBid()) {
					i = notifyUsers(bid >= currencyRatesBean.getTarget(),
							currencyRatesBean, i, bean, bid);
					exitProcess();
				} else {
					i = notifyUsers(bid < currencyRatesBean.getTarget(),
							currencyRatesBean, i, bean, bid);
					exitProcess();
				}
			}
		}
	}

	private int notifyUsers(boolean selector,
			CurrencyRatesBean currencyRatesBean, int i, CurrencyRatesBean bean,
			float bid) {
		if (selector) {
			i = notify(currencyRatesBean, i, bean);
		}
		return i;
	}

	private void exitProcess() {
		if (beansList.isEmpty()) {
			System.exit(0);
		}
	}

	private int notify(CurrencyRatesBean currencyRatesBean, int i,
			CurrencyRatesBean bean) {
		System.out.println("Target Value:: " + currencyRatesBean.getTarget()
				+ " Achieved at " + bean.getLast() + " for currency "
				+ bean.getSymbol());
		beansList.remove(i);
		i = -1;
		return i;
	}
}
