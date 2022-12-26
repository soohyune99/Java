package bank;

public class KbBank extends Bank {
	// 출금 시 수수료 50%
	@Override
	public void withdraw(int money) {
		money *=  1.5;
		super.withdraw(money);		// withdraw() 출금을 나타내는 메소드
	}
}



