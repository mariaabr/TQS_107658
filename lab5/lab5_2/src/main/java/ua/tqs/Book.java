package ua.tqs;
 
import java.time.LocalDate;
 
public class Book {
	private String title;
	private String author;
	private String category;
	private Double price;
	private LocalDate published;
 
	// constructors, getter, setter

	public Book(String title, String author, LocalDate published) {
		this.title = title;
		this.author = author;
		this.published = published;
	}

	public Book(String title, String author, String category, LocalDate published) {
		this.title = title;
		this.author = author;
		this.category = category;
		this.published = published;
	}

	public Book(String title, Double price) {
		this.title = title;
		this.price = price;
	}


	public String getTitle() {
		return this.title;
	}

	public String getAuthor() {
		return this.author;
	}

	public String getCategory() {
		return this.category;
	}

	public Double getPrice() {
		return this.price;
	}

	public LocalDate getPublished() {
		return this.published;
	}

	public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

	public void setCategory(String category) {
        this.category = category;
    }

	public void setPrice(Double price) {
        this.price = price;
    }

    public void setPublished(LocalDate published) {
        this.published = published;
    }

	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;

        if (obj == this) {
            return true;
        }

        if (this.title.equals(other.title) && this.author.equals(other.author) && this.published.equals(other.published)) {
            return true;
        }

        return false;
    }

	@Override
    public String toString() {
        return getTitle() +
            " / " + getAuthor() +
			" / " + getCategory() +
            " / " + getPublished();
    }
}