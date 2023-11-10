public class TestAccountDeletion {
//	@Test
//	public void testAccountDeletion_1() { // correct account deletion
//		Bank bank = new Bank();
//		bank.addAccount("Jacky", "10000", "123456");
//		boolean result = bank.deleteAccount("0001", "123456");
//		assertTrue(result);
//	}
//
//	@Test
//	public void testAccountDeletion_2() { // incorrect accNo, accNo not exist
//		Bank bank = new Bank();
//		bank.addAccount("Jacky", "10000", "123456");
//		boolean result = bank.deleteAccount("0002", "123456");
//		assertFalse(result);
//	}
//
//	@Test
//	public void testAccountDeletion_3() { // incorrect password
//		Bank bank = new Bank();
//		bank.addAccount("Jacky", "10000", "123456");
//		boolean result = bank.deleteAccount("0001", "1234567");
//		assertFalse(result);
//	}
//	
//	@Test
//	public void testAccountDeletion_4() { // incorrect accNo format
//		Bank bank = new Bank();
//		bank.addAccount("Jacky", "10000", "123456");
//		boolean result = bank.deleteAccount("++0001", "1234567");
//		assertFalse(result);
//	}
//	
//	@Test
//	public void testAccountDeletion_5() { // incorrect password format
//		Bank bank = new Bank();
//		bank.addAccount("Jacky", "10000", "123456");
//		boolean result = bank.deleteAccount("0001", "+++++1234567");
//		assertFalse(result);
//	}
}
