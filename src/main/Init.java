package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Menu;
import model.SpecialMenu;

public class Init {

	private static Scanner scan = new Scanner(System.in);
	private static List<Menu> listMenu = new ArrayList<Menu>();
	private static List<SpecialMenu> listSpecialMenu = new ArrayList<SpecialMenu>();

	public static void main(String[] args) {
		System.out.println("::FAMILY RESTAURANT::");
		while (true) {
			int choice = mainMenu();
			switch (choice) {
			case 1:
				addMenu(false);
				break;
			case 2:
				addMenu(true);
				break;
			case 3:
				showAll(listSpecialMenu, listMenu);
				break;
			case 4:
				delete(false);
				break;
			case 5:
				delete(true);
				break;
			case 6:
				System.out.println("\nExiting the application");
				System.exit(0);
			default:
				System.out.println("\nIncorrect Choice!");
			}
		}
	}
	
	public static int mainMenu() {
		System.out.println("\n1. Add Regular Menu");
		System.out.println("2. Add Special Menu");
		System.out.println("3. Show All Menu");
		System.out.println("4. Delete Regular Menu");
		System.out.println("5. Delete Special Menu");
		System.out.println("6. Exit");
		return showChoicePrompt("Choice[1-6]: ");
	}
	
	public static void addMenu(Boolean isSpecial) {
		if (isSpecial) {
			SpecialMenu specialMenu = new SpecialMenu();
			System.out.println("\n::ADD SPECIAL MENU::");
			specialMenu.setMenuCode(showMenuCodePrompt("Input Menu Code [S----]: ", true));
			specialMenu.setMenuName(showMenuNamePrompt("Input Menu Name [5-20]: "));
			specialMenu.setMenuPrice(showMenuPricePrompt("Input Menu Price [10000-100000]: "));
			specialMenu.setMenuDiscount(showMenuDiscountPrompt("Input Menu Discount [10% | 25% | 50%]: "));
			listSpecialMenu.add(specialMenu);
			System.out.println("Add Success");
		} else {
			Menu regularMenu = new Menu();
			System.out.println("\n::ADD REGULAR MENU::");
			regularMenu.setMenuCode(showMenuCodePrompt("Input Menu Code [R----]: ", false));
			regularMenu.setMenuName(showMenuNamePrompt("Input Menu Name [5-20]: "));
			regularMenu.setMenuPrice(showMenuPricePrompt("Input Menu Price [10000-100000]: "));
			listMenu.add(regularMenu);
			System.out.println("Add Success");
		}
	}
	
	public static void showAll(List<SpecialMenu> listSp, List<Menu> listM) {
		if (listM.size() > 0) {
			System.out.println("\n::REGULAR MENU::");
			System.out.println(String.format("%30s %25s %10s %25s %10s", "CODE", "|", "NAME", "|", "PRICE"));
			System.out.println(String.format("%s",
					"----------------------------------------------------------------------------------------------------------------"));
			for (Menu m : listM) {
				System.out.println(String.format("%30s %25s %10s %25s %10s", m.getMenuCode(), "|",
						m.getMenuName(), "|", m.getMenuPrice()));
			}
		}

		if (listSp.size() > 0) {
			System.out.println("\n::SPECIAL MENU::");
			System.out.println(String.format("%30s %25s %10s %25s %10s %25s %10s", "CODE", "|", "NAME", "|",
					"PRICE", "|", "DISCOUNT"));
			System.out.println(String.format("%s",
					"---------------------------------------------------------------------------------------------------------------------------------------------"));
			for (SpecialMenu m : listSp) {
				System.out.println(String.format("%30s %25s %10s %25s %10s %25s %10s", m.getMenuCode(), "|",
						m.getMenuName(), "|", m.getMenuPrice(), "|", m.getMenuDiscount()));
			}
		}
	}
	
	public static void delete(Boolean isSpecial) {
		if (isSpecial) {
			System.out.println("\n::DELETE SPECIAL MENU::");
			String spCode = showMenuCodePrompt("Input Menu Code [S----]: ", true);
			Boolean isSpMatch = false;
			SpecialMenu deleteSpMenu = null;
			for (SpecialMenu m : listSpecialMenu) {
				if (m.getMenuCode().equals(spCode)) {
					isSpMatch = true;
					deleteSpMenu = m;
				}
			}
			if (isSpMatch) {
				listSpecialMenu.remove(deleteSpMenu);
				System.out.println("Delete Success");
			} else {
				System.out.println("Code Is Wrong");
			}
		} else {
			System.out.println("\n::DELETE REGULAR MENU::");
			String code = showMenuCodePrompt("Input Menu Code [R----]: ", false);
			Boolean isMatch = false;
			Menu deleteMenu = null;
			for (Menu m : listMenu) {
				if (m.getMenuCode().equals(code)) {
					isMatch = true;
					deleteMenu = m;
				}
			}
			if (isMatch) {
				listMenu.remove(deleteMenu);
				System.out.println("Delete Success");
			} else {
				System.out.println("Code Is Wrong");
			}
		}
	}

	public static int showChoicePrompt(String message) {
		try {
			System.out.print(message);
			String input = scan.nextLine();
			if (input.matches(".*[a-z].*")) {
				showChoicePrompt(message);
			}
			return Integer.parseInt(input);
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	public static String showMenuCodePrompt(String message, Boolean isSpecial) {
		try {
			System.out.print(message);
			String input = scan.nextLine();
			if (isSpecial) {
				if (input.toUpperCase().charAt(0) != 'S' || input.length() != 4) {
					showMenuCodePrompt(message, isSpecial);
				}
			} else {
				if (input.toUpperCase().charAt(0) != 'R' || input.length() != 4) {
					showMenuCodePrompt(message, isSpecial);
				}
			}
			return input;
		} catch (Exception e) {
			System.out.println(e);
		}
		return "";
	}

	public static String showMenuNamePrompt(String message) {
		try {
			System.out.print(message);
			String input = scan.nextLine();
			if (input.length() > 20 || input.length() < 5) {
				showMenuNamePrompt(message);
			}
			return input;
		} catch (Exception e) {
			System.out.println(e);
		}
		return "";
	}

	public static int showMenuPricePrompt(String message) {
		try {
			System.out.print(message);
			String input = scan.nextLine();
			if (input.matches(".*[a-z].*")) {
				showMenuPricePrompt(message);
			}
			if (Integer.parseInt(input) > 100000 || Integer.parseInt(input) < 10000) {
				showMenuPricePrompt(message);
			}
			return Integer.parseInt(input);
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	public static int showMenuDiscountPrompt(String message) {
		try {
			System.out.print(message);
			String input = scan.nextLine();
			if (input.matches(".*[a-z].*")) {
				showMenuDiscountPrompt(message);
			}
			if (Integer.parseInt(input) != 10 && Integer.parseInt(input) != 25 && Integer.parseInt(input) != 50) {
				showMenuDiscountPrompt(message);
			}
			return Integer.parseInt(input);
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

}
