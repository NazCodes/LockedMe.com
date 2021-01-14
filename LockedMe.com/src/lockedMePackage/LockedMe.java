package lockedMePackage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class LockedMe {
	Scanner scanner = new Scanner(System.in);
	final String FIRSTFOLDER = "C:\\TextFiles";
	final String SECONDFOLDER = "C:\\TextFiles\\CopyTextFiles";
	String mainMenuOptions;
	String newFilePath;
	String filePath;
	String secondFilePath;
	Path path;
	Path path2;

	public void welcomeScreen() {
		System.out.println("Application: LockedMe.com");
		System.out.println("Developer: Nazmul Hoque");
	}

	public void mainMenu() throws IOException {
		System.out.println("--Main Menu--");
		System.out.println("Please Type 1, 2, or 3");
		System.out.println("1.) Show Files In Ascending Order");
		System.out.println("2.) Perform Operations");
		System.out.println("3.) Close Application");
		mainMenuOptions = scanner.nextLine();
		switch (mainMenuOptions) {
		case "1":
			showFilesInAscendingOrder();
			mainMenu();

		case "2":
			secondMenu();

		case "3":
			closeApplication();
		}

	}

	public void secondMenu() throws IOException {
		System.out.println("--------------");
		System.out.println("1.) Add a File");
		System.out.println("2.) Delete a file");
		System.out.println("3.) Search For a file");
		System.out.println("4.) Go Back To Main Menu");
		System.out.println("--------------");
		collectFileOperation();
	}

	public void collectFileOperation() throws IOException {
		System.out.println("Please Type 1, 2, 3, or 4:");
		String option = scanner.nextLine();
		switch (option) {
		case "1":
			addFile();
			break;

		case "2":
			deleteFile();
			break;

		case "3":
			searchFile();
			break;

		case "4":
			mainMenu();

		}
		secondMenu();
	}

	public void showFilesInAscendingOrder() {
		System.out.println("------------------");
		System.out.println("Sorting Files...");
		System.out.println("Showing Files In Ascending Order:");
		File[] files = new File(FIRSTFOLDER).listFiles();
		Set<String> sorted = new TreeSet<String>();
		for (File file : files) {
			if (!file.isFile()) {
				continue;
			}
			sorted.add(file.getName());
		}
		sorted.forEach(System.out::println);
		System.out.println("------------------");
	}

	public void addFile() throws InvalidPathException {
		System.out.println("Please provide a file path:");
		filePath = scanner.nextLine();
		path = Paths.get(filePath);
		System.out.println("Adding File...");
		if (!Files.exists(path)) {
			System.out.println(path.getFileName() + " Does Not Exist, And Could Not Be Added");
			return;
		}

		newFilePath = SECONDFOLDER + "/" + path.getFileName();
		int inc = 0;
		while (Files.exists(Paths.get(newFilePath))) {
			inc++;
			newFilePath = SECONDFOLDER + "/" + inc + "_" + path.getFileName();
		}
		try {
			Files.copy(path, Paths.get(newFilePath));
		} catch (IOException e) {
			System.out.println("Unable to copy file to " + newFilePath);
		}

	}

	public void searchFile() {
		System.out.println("Please provide a file path to Search:");
		filePath = scanner.nextLine();
		path = Paths.get(filePath);
		System.out.println("Searching For File...");
		if (Files.exists(path)) {
			System.out.println(path.getFileName() + " Was Found");
		} else {
			System.out.println(path.getFileName() + " Was Not Found");
		}
	}

	public void deleteFile() throws IOException {
		try {
			System.out.println("Please Provide A File Path To Delete");
			secondFilePath = scanner.nextLine();
			path2 = Paths.get(secondFilePath);
			System.out.println("Deleting File...");
			if(Files.deleteIfExists(path2)) {
			System.out.println(path2.getFileName() + " Has Been Deleted");
			}else {
				System.out.println(path2.getFileName() + " Was Not Deleted");
			}
		} catch (IOException e) {
			System.out.println("Error, Could Not Delete File");
		}
	}

	public void closeApplication() {
		System.out.println("Closing Application...");
		System.out.println("Application Has Closed");
		System.exit(0);
	}

}
