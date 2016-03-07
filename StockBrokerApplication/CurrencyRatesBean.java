public class CurrencyRatesBean {

	private String symbol;
	private float bid;
	private String ask;
	private String high;
	private String low;
	private String direction;
	private String last;
	private float target;

	public float getTarget() {
		return target;
	}

	public void setTarget(float target) {
		this.target = target;
	}

	public CurrencyRatesBean() {
	}

	public CurrencyRatesBean(String symbol, float bid, float target) {
		this.symbol = symbol;
		this.bid = bid;
		this.target = target;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public float getBid() {
		return bid;
	}

	public void setBid(float bid) {
		this.bid = bid;
	}

	public String getAsk() {
		return ask;
	}

	public void setAsk(String ask) {
		this.ask = ask;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

}
