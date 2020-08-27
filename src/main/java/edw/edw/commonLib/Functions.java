package edw.edw.commonLib;

import java.util.Random;

/* Designed By Mehr */

public class Functions {

	private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	private static final int RANDOM_STRING_LENGTH = 8;

	/**
	 * This method generates random string
	 * 
	 * @return
	 */
	public String generateRandomString() {

		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			int number = getRandomNumber();
			char ch = CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	/**
	 * This method generates random numbers
	 * 
	 * @return int
	 */
	private int getRandomNumber() {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(CHAR_LIST.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}

	// generate number with particular range

	public int number() {
		Random random = new Random();
		return showRandomInteger(1000000, 999999999, random);

	}

	private static int showRandomInteger(int aStart, int aEnd, Random aRandom) {
		if (aStart > aEnd) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		// get the range, casting to long to avoid overflow problems
		long range = (long) aEnd - (long) aStart + 1;
		// compute a fraction of the range, 0 <= frac < range
		long fraction = (long) (range * aRandom.nextDouble());
		int randomNumber = (int) (fraction + aStart);
		return randomNumber;
	}

	public String mobileNumber() {
		return "'" + String.valueOf(number()) + "'";
	}

	public String randomGeneratedEmail() {
		return generateRandomString() + "@yopmail.com";
	}

}
