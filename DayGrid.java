import java.util.Calendar;
import java.util.GregorianCalendar;

/*
* Alan Luu
* 10/16/2017
* CSS 210 Assignment # 1 
* this program prints a calendar in a certain month and year based on what the user enters 
* into the parameter of the method called showGrid().  
*/

public class DayGrid {
	
	public static void main(String[] args) {
		showGrid(5, 2021);
		showGrid(2, 2021);
		showGrid(4, 2021);
	}

	// method that prints calendar grid based on month and year entered into
	// parameters.
	public static void showGrid(int month, int year) {
		if(month < 1 || month > 12) {
			System.out.println("Month does not exist. Try again.");
			return;
		}
		
		String format = "";
		String header = "+------";
		String[] dayNames = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
		String[] months = { "January", "February", "March", "April", "May", "June", "July", 
				"August", "September", "October", "November", "December" };

		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, 1);

		// print Month
		System.out.print("\t\t      " + months[month - 1] + " " + year + "\n");
		
		// header
		// loops through array and displays each value in the array.
		for (int x = 0; x <= 6; x++) {
			System.out.printf("%-1s %s %1s", format, dayNames[x], format);
		}

		System.out.println();

		for (int z = 1; z <= 7; z++) {
			System.out.printf("%7s", header);
		}

		System.out.print("+");
		System.out.println();

		// body
		// add spaces to top first row of grid depending on week number - 1.
		for (int x = 0; x < calendar.get(Calendar.DAY_OF_WEEK) - 1; x++) {
			System.out.print("|      ");
		}

		// loops and adds vertical lines(|) and the number of days for specified month.
		// calls the daysinMonth()
		// method which contains the number of days value based on the month inputed
		// into parameter.
		for (int i = 1; i <= daysInMonth(month, year); i++) {
			System.out.printf("|");
			System.out.printf("  %2d  ", i);
			// prints to the next row if the remainder of (i + calendar - 1) is 0 divided
			// by 7.
			if ((i + calendar.get(Calendar.DAY_OF_WEEK) - 1) % 7 == 0) {
				System.out.println("|");
			}
		}

		int spaceSize = calendar.get(Calendar.DAY_OF_WEEK);

		// loops depending on the number of days for the specified month less than 31.
		// adds 1 for every loop
		// into spaceSize.
		for (int x = 31; x > daysInMonth(month, year); x--) {
			spaceSize = spaceSize + 1;
		}

		// the number of the week is process through the if statements to determine how
		// many spaces to remove
		// or add for the last row in the grid.
		if (calendar.get(Calendar.DAY_OF_WEEK) % 2 == 1) {
			if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
				spaceSize = spaceSize + 4;
			} else if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
				if (daysInMonth(month, year) == 31) {
					spaceSize = spaceSize + 3;
				} else {
					spaceSize = spaceSize - 4;
				}
			} else if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
				if (daysInMonth(month, year) == 28) {
					spaceSize = spaceSize - 8;
				} else {
					spaceSize = spaceSize - 1;
				}
			}
		}

		else if (calendar.get(Calendar.DAY_OF_WEEK) % 2 == 0) {
			if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
				spaceSize = spaceSize + 2;
			}
			if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
				spaceSize = spaceSize - 2;
			} else if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
				if (daysInMonth(month, year) == 28 || daysInMonth(month, year) == 29) {
					spaceSize = spaceSize - 6;
				} else {
					spaceSize = spaceSize + 1;
				}
			}
		}

		// loops and add the spaces based on the number for spaceSize.
		for (int x = 0; x < +spaceSize; x++) {
			System.out.print("|      ");
		}

		// bottom Header
		System.out.println();
		for (int z = 1; z <= 7; z++) {
			System.out.printf("%7s", header);
		}
		System.out.print("+");
		System.out.println("\n");
	}

	// method from Exercise #4 and returns the number of days based on the month
	// inputed into parameter.
	// Also the year inputed into the parameter will determine if that year has a
	// leap year.
	public static int daysInMonth(int month, int year) {
		int numberOfDays = 0;

		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, 1);

		// If statements used to judge user's input for month and to distribute the
		// correct days for that month.
		if (month > 12 || month <= 0) {
			throw new IllegalArgumentException("Month is out of range. Try again using "
					+ "a # from 1 - 12.");
		} else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || 
				month == 10 || month == 12) {
			numberOfDays = 31;
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			numberOfDays = 30;
		} else if (month == 2) {
			if (calendar.isLeapYear(year)) {
				numberOfDays = 29;
			} else {
				numberOfDays = 28;
			}
		}

		return numberOfDays;
	}

}
