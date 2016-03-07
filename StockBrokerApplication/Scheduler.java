import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;

public class Scheduler {

	public static void schedule() {
		int nextChoice = 1;
		String current_symbol;
		Timer timer = new Timer();
		Scanner scanner = new Scanner(System.in);
		List<CurrencyRatesBean> beansList = new ArrayList<CurrencyRatesBean>();
		UserInput userInput = new UserInput();
		try {
			while (nextChoice == 1) {
				int flag = 0;
				current_symbol = userInput.getSymbol(scanner);
				Map<String, CurrencyRatesBean> map = WebCrawler.parseXML();
				CurrencyRatesBean bean = null;
				if (map.containsKey(current_symbol)) {
					bean = map.get(current_symbol);
					userInput.getTarget(scanner, bean);
					flag = 1;
				}
				if (flag == 0) {
					System.out.println("Currency is invalid......");
				}
				nextChoice = userInput.getChoice(scanner);
				beansList.add(bean);
			}
			CurrencyTask task = new CurrencyTask(beansList);
			timer.schedule(task, 0, 5000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}

}
