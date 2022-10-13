

package sptv21library;

import entity.Author;
import entity.Book;
import entity.History;
import entity.Reader;
import java.util.GregorianCalendar;
import java.util.Scanner;


public class App {
    
    public void run(){
        Scanner scanner = new Scanner(System.in);
        boolean repeat = true;
        Book book = null;
        Reader reader = null;
        History history = null;
        do{
            System.out.println("Задачи: ");
            System.out.println("0. Закончить программу");
            System.out.println("1. Добавить книгу");
            System.out.println("2. Добавить читателя");
            System.out.println("3. Выдать книгу");
            System.out.println("4. Вернуть книгу");
            System.out.print("Выберите задачу: ");
            int task = scanner.nextInt();
            scanner.nextLine();
            switch (task) {
                case 0:
                    repeat = false;
                    break;
                case 1:
                    System.out.println("1. Добавить книгу");
                    book = createBook("Voina i mir", 3, 1956);
                    book.addAuthor(createAuthor("Lev", "Tolstoy", 1828));
                    break;
                case 2:
                    System.out.println("2. Добавить читателя");
                    reader = new Reader();
                    reader.setFirstname("Ivan");
                    reader.setLastname("Ivanov");
                    reader.setPhone("5656565656");
                    break;
                case 3:
                    System.out.println("3. Выдать книгу");
                    history = new History();
                    history.setBook(book);
                    history.setReader(reader);
                    history.setTakeOnBook(new GregorianCalendar().getTime());
                    System.out.println(history);
                    break;
                case 4: 
                    System.out.println("4. Вернуть книгу");
                    history.setReturnBook(new GregorianCalendar().getTime());
                    System.out.println(history);
                    break;
                default:
                    System.out.println("Выберите задачу из списка!");;
            }
            
        }while(repeat);
        System.out.println("Закрытие программы, досвидания!");
    }
    public Book createBook(String bookName,int quantity, int publishedYear){
        Book book = new Book();
        book.setBookName(bookName);
        book.setPublishedYear(publishedYear);
        book.setQuantity(quantity);
        return book;
    }
    public Author createAuthor(String firstname, String lastname, int birthday){
        Author author = new Author();
        author.setBirthday(birthday);
        author.setFirstname(firstname);
        author.setLastname(lastname);
        return author;
    }
}
