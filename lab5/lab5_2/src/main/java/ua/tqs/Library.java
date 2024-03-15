package ua.tqs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
	private final List<Book> store = new ArrayList<>();

	public void addBook(final Book book) {
		store.add(book);
	}

	public List<Book> findBooksByYear(final LocalDate from, final LocalDate to) {

		// Calendar end = Calendar.getInstance();
		// end.setTime(to);
		// end.roll(Calendar.YEAR, 1);

		// return store.stream().filter(book -> {
		// return from.before(book.getPublished()) &&
		// end.getTime().after(book.getPublished());
		// }).sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toList());

		LocalDate end = to.plusYears(1);

		return store.stream()
				.filter(book -> from.isBefore(book.getPublished()) && end.isAfter(book.getPublished()))
				.sorted(Comparator.comparing(Book::getPublished).reversed())
				.collect(Collectors.toList());
	}

	public List<Book> findBooksByAuthor(final String author) {


		return store.stream()
            .filter(book -> book.getAuthor().equals(author))
            .sorted(Comparator.comparing(Book::getPublished).reversed())
            .collect(Collectors.toList());
	}

	public List<Book> findBooksByTitle(final String title) {


		return store.stream()
            .filter(book -> book.getTitle().equals(title))
            .sorted(Comparator.comparing(Book::getPublished).reversed())
            .collect(Collectors.toList());
	}

	public List<Book> findBooksByCategory(final String category) {

		return store.stream()
            .filter(book -> book.getCategory().equals(category))
            .sorted(Comparator.comparing(Book::getPublished).reversed())
            .collect(Collectors.toList());
	}
}