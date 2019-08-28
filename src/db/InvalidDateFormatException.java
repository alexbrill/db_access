package db;

public class InvalidDateFormatException extends Exception {
	public InvalidDateFormatException() {
		super("Ошибка: дата введена неправильно: дд.мм.гггг");
	}
}
