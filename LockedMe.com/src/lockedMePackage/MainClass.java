package lockedMePackage;

import java.io.IOException;

public class MainClass {

	public static void main(String[] args) throws IOException {
		LockedMe myLockedMe = new LockedMe();
		myLockedMe.welcomeScreen();
		myLockedMe.mainMenu();
	}

}
