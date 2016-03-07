import java.util.Scanner;

public class UserInput {
	int nextChoice;
	float target_value;
	String current_symbol;

	public int getChoice(Scanner scanner) {
		System.out.println("Do you need to keep track of more currency.....");
		System.out.println("1. Yes");
		System.out.println("2. No");
		nextChoice = scanner.nextInt();
		return nextChoice;
	}

	public void getTarget(Scanner scanner, CurrencyRatesBean bean) {
		System.out.println("Current Value:::" + bean.getBid());
		System.out.println("Enter Target Value::");
		target_value = scanner.nextFloat();
		bean.setTarget(target_value);
	}

	public String getSymbol(Scanner scanner) {
		System.out.println("Enter Currency to track::");
		current_symbol = scanner.next();
		current_symbol = current_symbol.toUpperCase();
		return current_symbol;
	}
}
