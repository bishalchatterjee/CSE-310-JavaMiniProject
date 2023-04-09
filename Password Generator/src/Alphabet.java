public class Alphabet {
	
	public static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
	public static final String NUMBERS = "1234567890";
	public static final String SYMBOLS = "!@#$%^&*()-_=+\\/~?";
	
	private final StringBuilder storage;


	public Alphabet(boolean uppercaseIncluded, boolean lowercaseIncluded, boolean numbersIncluded, boolean specialCharactersIncluded) {
		
		storage = new StringBuilder();
		
		if (uppercaseIncluded) storage.append(UPPERCASE_LETTERS);
		
		if (lowercaseIncluded) storage.append(LOWERCASE_LETTERS);
		
		if (numbersIncluded) storage.append(NUMBERS);
		
		if (specialCharactersIncluded) storage.append(SYMBOLS);
		
	}
	
	public String getAlphabet() {
		return storage.toString();
	}
}
