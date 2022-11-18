package lab2.task4;
public class Book {
    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String toString() {
        return "Book" + "title: " + title + ", autor: " + author + '\'' + ", year: " + year + '}';
    }

    public static void main(String[] args) {
        Book b1 = new Book("Morometii", "Marin Preda", 1955);
        System.out.println(b1);

        Book b2 = new Book("Levantul", "Mircea Cărtărescu", 1990);
        System.out.println(b2);
    }
}