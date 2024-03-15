package ua.tqs;

import static java.lang.invoke.MethodHandles.lookup;
// import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
// import static org.junit.jupiter.api.equalTo;
import static org.slf4j.LoggerFactory.getLogger;

// import org.jcp.xml.dsig.internal.dom.Utils;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Format;
import org.slf4j.Logger;

import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import ua.tqs.Utils;

public class BookSteps {
	Library library = new Library();
	List<Book> result = new ArrayList<>();

	/*
	 * create a registered type named iso8601Date to map a string pattern from the
	 * feature
	 * into a custom datatype. Extracted parameters should be strings.
	 */
	@ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
	public LocalDate iso8601Date(String year, String month, String day) {
		return Utils.localDateFromDateParts(year, month, day);
	}

	/**
	 * load a data table from the feature (tabular format) and call this method
	 * for each row in the table. Injected parameter is a map with column name -->
	 * value
	 */
	@DataTableType
	public Book bookEntry(Map<String, String> tableEntry) {
		return new Book(
				tableEntry.get("title"),
				tableEntry.get("author"),
				Utils.isoTextToLocalDate(tableEntry.get("published")));
	}

	@Given("a book with the title {string}, written by {string}, published in {iso8601Date}")
	public void addNewBook(final String title, final String author, final LocalDate published) {
		Book book = new Book(title, author, published);
		library.addBook(book);
	}

	@Given("another book with the title {string}, written by {string}, published in {iso8601Date}")
	public void addAnotherBook(final String title, final String author, final LocalDate published) {
		Book book = new Book(title, author, published);
		library.addBook(book);
	}

	@Given("a {string} book with the title {string}, written by {string}, published in {iso8601Date}")
	public void addNewBook(final String category, final String title, final String author, final LocalDate published) {
		Book book = new Book(title, author, category, published);
		library.addBook(book);
	}

	@Given("another {string} book with the title {string}, written by {string}, published in {iso8601Date}")
	public void addAnotherBook(final String category, final String title, final String author, final LocalDate published) {
		Book book = new Book(title, author, category, published);
		library.addBook(book);
		System.out.println(book.toString());
	}
	
	@When("the customer searches for books published between {iso8601Date} and {iso8601Date}")
	public void searchBooksByYear(final LocalDate from, final LocalDate to) {
		// int fromYear = from.getYear();
		// int toYear = to.getYear();

		result = library.findBooksByYear(from, to);
	}

	@When("the customer searches for books with the author {string}")
	public void searchBooksByAuthor(String author) {

		result = library.findBooksByAuthor(author);
	}

	@When("the customer searches for books with the title {string}")
	public void searchBooksByTitle(String title) {

		result = library.findBooksByTitle(title);
	}

	@When("the customer searches for books with the category {string}")
	public void searchBooksByCategory(String category) {

		result = library.findBooksByCategory(category);
	}

	@Then("{int} books should have been found")
	public void verifyAmountOfBooksFound(final int booksFound) {
		assertThat(result.size(), equalTo(booksFound));
	}

	@Then("Book {int} should have the title {string}")
	public void verifyBookAtPosition(final int position, final String title) {
		assertThat(result.get(position - 1).getTitle(), equalTo(title));
	}

	@Then("Book {int} should have the title {string} and the author {string}")
	public void verifyBookAtPosition(final int position, final String title, final String author) {
		assertThat(result.get(position - 1).getTitle(), equalTo(title));
		assertThat(result.get(position - 1).getAuthor(), equalTo(author));

	}
}
