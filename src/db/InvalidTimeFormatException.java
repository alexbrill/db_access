package db;

class InvalidTimeFormatException extends Exception {
	public InvalidTimeFormatException() {
		super("Ошибка: время введено неправильно: ??:??");
	}
}
