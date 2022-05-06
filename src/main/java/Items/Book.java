package Items;

import java.sql.Blob;
import java.util.Objects;

public class Book {

    String name;
    String isbn;
    Blob picData;
    int amount;

    public Book(String name,String isbn,Blob picData){

        this.name = name;
        this.picData = picData;
        this.isbn = isbn;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Blob getBlob() {
        return picData;
    }

    public void setBlob(Blob blob) {
        this.picData = blob;
    }
}
