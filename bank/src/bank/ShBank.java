package bank;

public class ShBank extends Bank{
	// 입금 시 수수료 50%
	@Override
	public void deposit(int money) {
		money *= 0.5;	
		super.deposit(money);	// deposit() :입금 동작을 나타내는 메소드
	}
}


