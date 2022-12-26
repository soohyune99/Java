package bank;

public class HanaBank extends Bank {
	// 잔액조회 시 재산 반토막
	@Override
	public int showBalance() {
		setMoney(getMoney() / 2);
		return super.showBalance();
	}
}



