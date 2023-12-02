package org.example;

import jdk.internal.classfile.impl.ClassPrinterImpl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class LibrarySystem {

    public static void main(String[] args) {
        // Sample usage of the classes with lambda expressions
        Book book1 = new Book("Java Programming", "John Doe", "123456789");
        Thesis thesis1 = new Thesis("Data Science", "Jane Smith", "Data Science Research",
                "This is an abstract.", "2023-01-01");
        CD dvd1 = new CD("Movie Title", "Director Name", "Movie Producer", "Movie Director", 120);

        Author author1 = new Author("Author One");
        author1.addAuthoredBook(book1);

        LibraryUser user1 = new LibraryUser("John Doe", "1001");

        // Lambda expressions for borrowing and returning
        LibraryOperation borrowOperation = item -> {
            item.borrow();
            System.out.println("Customized borrowing operation for " + item.getType());
        };

        LibraryOperation returnOperation = item -> {
            item.returnItem();
            System.out.println("Customized returning operation for " + item.getType());
        };

        user1.borrowItem(book1, borrowOperation);
        user1.borrowItem(thesis1, borrowOperation);
        user1.borrowItem(dvd1, borrowOperation);

        user1.returnItem(book1, returnOperation);
    }

    // Functional interface for library operations
    interface LibraryOperation {
        void perform(LibraryItem item);
    }

    // Interface for items that can be borrowed
    interface Borrowable {

    }

    // Abstract class representing common attributes of library items
    abstract static class LibraryItem implements Borrowable {
        protected String title;
        protected String author;
        protected boolean availability;

        public LibraryItem(String title, String author) {
            this.title = title;
            this.author = author;
            this.availability = true;
        }

        private boolean isAvailable() {
            return availability;
        }

        private void borrow() {
            if (availability) {
                availability = false;
                System.out.println("Item " + title + " borrowed successfully.");
            } else {
                System.out.println("Item " + title + " is not available for borrowing.");
            }
        }

        private void returnItem() {
            availability = true;
            System.out.println("Item " + title + " returned successfully.");
        }

        public abstract String getType();
    }

    // Class representing a Book
    static class Book extends LibraryItem {

        public Book(String title, String author, String ISBN) {
            super(title, author);
        }

        @Override
        public String getType() {
            return "Book";
        }

        public void setISBN(String ISBN) {
        }
    }

    // Class representing a Theses or Dissertation
    static class Thesis extends LibraryItem {
        private String topic;
        private String abstractText;
        private String datePublished;

        public Thesis(String title, String author, String topic, String abstractText, String datePublished) {
            super(title, author);
            this.topic = topic;
            this.abstractText = abstractText;
            this.datePublished = datePublished;
        }

        @Override
        public String getType() {
            return "Thesis";
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public String getAbstractText() {
            return abstractText;
        }

        public void setAbstractText(String abstractText) {
            this.abstractText = abstractText;
        }

        public String getDatePublished() {
            return datePublished;
        }

        public void setDatePublished(String datePublished) {
            this.datePublished = datePublished;
        }
    }

    // Class representing a CD or DVD
    static class CD extends LibraryItem {
        private String producer;
        private String director;
        private int playtime;

        public CD(String title, String author, String producer, String director, int playtime) {
            super(title, author);
            this.producer = producer;
            this.director = director;
            this.playtime = playtime;
        }

        @Override
        public String getType() {
            return "CD/DVD";
        }

        public String getProducer() {
            return producer;
        }

        public void setProducer(String producer) {
            this.producer = producer;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public int getPlaytime() {
            return playtime;
        }

        public void setPlaytime(int playtime) {
            this.playtime = playtime;
        }
    }

    // Class representing an Author
    static class Author {
        private String name;
        private List<LibraryItem> authoredBooks;

        public Author(String name) {
            this.name = name;
            this.authoredBooks = new ArrayList<>();
        }

        public void addAuthoredBook(LibraryItem book) {
            authoredBooks.add(book);
        }
    }

    // Class representing a Library User
    static class LibraryUser {
        private String name;
        private String ID;
        private LinkedList<LibraryItem> borrowedAssets; // Using a LinkedList to store borrowed items

        public LibraryUser(String name, String ID) {
            this.name = name;
            this.ID = ID;
            this.borrowedAssets = new LinkedList<>();
        }

        public void borrowItem(LibraryItem item, LibraryOperation borrowOperation) {
            if (item.isAvailable()) {
                borrowOperation.perform(item);
                borrowedAssets.add(item); // Add the borrowed item to the LinkedList
            } else {
                System.out.println("Item is not available for borrowing.");
            }
        }

        public void returnItem(LibraryItem item, LibraryOperation returnOperation) {
            returnOperation.perform(item);
            borrowedAssets.remove(item); // Remove the returned item from the LinkedList
        }
    }


        // Lambda function for returning an item
        public void returnItem(LibraryItem item, LibraryOperation returnOperation) {
            returnOperation.perform(item);
            ClassPrinterImpl.MapNodeImpl borrowedAssets = null;
            borrowedAssets.remove(item);
        }
    }
