

package sptv21library;

import entity.Author;
import entity.Book;
import entity.History;
import entity.Reader;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Scanner;


public class App {
    private Scanner scanner;
    private Book[] books;
    private Reader[] readers;
    private History[] histories;

    public App() {
        scanner = new Scanner(System.in);
        books = new Book[0];
        readers = new Reader[0];
        histories = new History[0];
    }
    
    public void run(){
        boolean repeat = true;
        
        History history = null;
        do{
            System.out.println("Задачи: ");
            System.out.println("0. Закончить программу");
            System.out.println("1. Добавить книгу");
            System.out.println("2. Добавить читателя");
            System.out.println("3. Выдать книгу");
            System.out.println("4. Вернуть книгу");
            System.out.println("5. Список выданных книг");
            System.out.println("6. Список книг");
            System.out.println("7. Список читателей");
            System.out.println("8. Редактировать книгу");
            System.out.print("Выберите задачу: ");
            int task = scanner.nextInt();
            scanner.nextLine();
            switch (task) {
                case 0:
                    repeat = false;
                    break;
                case 1:
                    System.out.println("1. Добавить книгу");
                    System.out.print("Введите название книги: ");
                    String bookName = scanner.nextLine();
                    System.out.print("Введите год издания книги: ");
                    String publishedYear = scanner.nextLine();
                    System.out.print("Введите количество экземпляров книги: ");
                    String quantity = scanner.nextLine();
                    
                    Book book = createBook(bookName, new Integer(quantity), Integer.parseInt(publishedYear));
                    System.out.print("Введите количество авторов книги:");
                    int countAuthorsInBook = scanner.nextInt(); scanner.nextLine();
                    for (int i = 0; i < countAuthorsInBook; i++) {
                        System.out.print("Введите имя автора "+(i+1)+": ");
                        String firstname = scanner.nextLine();
                        System.out.print("Введите фамилию автора "+(i+1)+": ");
                        String lastname = scanner.nextLine();
                        System.out.print("Введите год рождения автора "+(i+1)+": ");
                        String birthday = scanner.nextLine();
                        book.addAuthor(createAuthor(firstname, lastname, new Integer(birthday)));
                    }
                    this.books = Arrays.copyOf(this.books, this.books.length+1);
                    this.books[this.books.length - 1] = book;
                    
                    break;
                case 2:
                    System.out.println("2. Добавить читателя");
                    Reader reader = new Reader();
                    System.out.print("Введите имя читателя: ");
                    reader.setFirstname(scanner.nextLine());
                    System.out.print("Введите фамилию читателя: ");
                    reader.setLastname(scanner.nextLine());
                    System.out.print("Введите телефон читателя: ");
                    reader.setPhone(scanner.nextLine());
                    this.readers = Arrays.copyOf(this.readers, this.readers.length + 1);
                    this.readers[this.readers.length - 1] = reader;
                    break;
                case 3:
                    System.out.println("3. Выдать книгу");
                    System.out.println("Список читателей: ");
                    for (int i = 0; i < readers.length; i++) {
                        System.out.println(i+1+". "+readers[i].getFirstname()+" "+readers[i].getLastname());
                    }
                    System.out.print("Выбери читателя: ");
                    int numberReader = scanner.nextInt(); scanner.nextLine();
                    System.out.println("Список книг: ");
                    for (int i = 0; i < books.length; i++) {
                        System.out.print(i+1+". "+books[i].getBookName()+". ");
                        for (int j = 0; j < books[i].getAuthors().length; j++) {
                            System.out.printf("%s %s %d", 
                                    books[i].getAuthors()[j].getFirstname(),
                                    books[i].getAuthors()[j].getLastname(),
                                    books[i].getAuthors()[j].getBirthday());
                            
                        }
                        System.out.println();
                    }
                    System.out.print("Выбери номер книги: ");
                    int numberBook = scanner.nextInt();scanner.nextLine();
                    history = new History();
                    history.setBook(books[numberBook - 1]);
                    history.setReader(readers[numberReader-1]);
                    history.setTakeOnBook(new GregorianCalendar().getTime());
                    this.histories = Arrays.copyOf(this.histories, this.histories.length + 1);
                    this.histories[this.histories.length-1] = history;
                    System.out.println(history);
                    break;
                case 4: 
                    System.out.println("4. Вернуть книгу");
                    history.setReturnBook(new GregorianCalendar().getTime());
                    System.out.println(history);
                    break;
                case 5: 
                    System.out.println("5. Список выданных книг");
                    for (int i = 0; i < histories.length; i++) {
                        if(histories[i].getReturnBook() == null){
                            System.out.print(i+1+". "+histories[i].getBook().getBookName()+". ");
                            for (int j = 0; j < histories[i].getBook().getAuthors().length; j++) {
                                System.out.printf("%s %s %d", 
                                        histories[i].getBook().getAuthors()[j].getFirstname(),
                                        histories[i].getBook().getAuthors()[j].getLastname(),
                                        histories[i].getBook().getAuthors()[j].getBirthday());

                            }
                            System.out.println();
                        }
                    }
                    break;
                case 6:
                    System.out.println("6. Список книг");
                    for (int i = 0; i < books.length; i++) {
                        System.out.print(i+1+". "+books[i].getBookName()+". ");
                        for (int j = 0; j < books[i].getAuthors().length; j++) {
                            System.out.printf("%s %s %d. ", 
                                    books[i].getAuthors()[j].getFirstname(),
                                    books[i].getAuthors()[j].getLastname(),
                                    books[i].getAuthors()[j].getBirthday());
                            
                        }
                        System.out.println();
                    }
                    break;
                case 7:
                    System.out.println("7. Список читателей");
                    for (int i = 0; i < readers.length; i++) {
                        System.out.printf("%d. %s %s. Телефон: %s%n"
                                ,i+1
                                ,readers[i].getFirstname()
                                ,readers[i].getLastname()
                                ,readers[i].getPhone()
                        );
                    }
                    break;
                case 8:
                    this.books = this.changeBook(books);
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
    public Book[] changeBook(Book[] books) {
        System.out.println("Список книг: ");
        for (int i = 0; i < books.length; i++) {
            System.out.print(i+1+". "+books[i].getBookName()+". ");
            for (int j = 0; j < books[i].getAuthors().length; j++) {
                System.out.printf("%s %s %d", 
                        books[i].getAuthors()[j].getFirstname(),
                        books[i].getAuthors()[j].getLastname(),
                        books[i].getAuthors()[j].getBirthday());

            }
            System.out.println();
        }
        System.out.print("Выберите номер книги для редактирования: ");
        int numBookForEdit = scanner.nextInt(); scanner.nextLine();
        System.out.print("Название книги: ");
        System.out.println(books[numBookForEdit - 1].getBookName());
        System.out.print("Изменить название книги? (y/n)");
        String edit = scanner.nextLine();
        if(edit.equals("y")){
            System.out.print("Введите новое название книги: ");
            books[numBookForEdit - 1].setBookName(scanner.nextLine());
        }
        System.out.print("Год издания книги: ");
        System.out.println(books[numBookForEdit - 1].getPublishedYear());
        System.out.print("Изменить год издания книги? (y/n)");
        edit = scanner.nextLine();
        if(edit.equals("y")){
            System.out.print("Введите новое название книги: ");
            books[numBookForEdit - 1].setPublishedYear(scanner.nextInt());scanner.nextLine();
        }
        System.out.print("Количество экземпляров книги: ");
        System.out.println(books[numBookForEdit - 1].getQuantity());
        System.out.print("Изменить количество экземпляров книги? (y/n)");
        edit = scanner.nextLine();
        if(edit.equals("y")){
            System.out.print("Введите другое количество книги: ");
            books[numBookForEdit - 1].setQuantity(scanner.nextInt());scanner.nextLine();
        }
        System.out.println("Авторов у книги "+books[numBookForEdit - 1].getAuthors().length);
        System.out.println("Изменить количество авторов? (y/n)");
        edit = scanner.nextLine();
        if(edit.equals("y")){// Меняем количество авторов
            System.out.print("Введите новое количество авторов: ");
            int newCountAuthorsInBook = scanner.nextInt();
            scanner.nextLine();
         // количество авторов может быть больше или меньше.
            if(newCountAuthorsInBook < books[numBookForEdit - 1].getAuthors().length){
               //если меньше, выводим нумерованный список авторов и просим указать какого удалить
               // вычисляем на сколько меньше 
                int deltaAuthors = books[numBookForEdit - 1].getAuthors().length - newCountAuthorsInBook;
                for (int n = 0; n < deltaAuthors; n++) {
                    //удаляем лишних (deltaAuthors) авторов из книги
                    books[numBookForEdit - 1] = deleteAuthorBook(books[numBookForEdit - 1]);
                }
            }else{
                for (int i = 0; i < newCountAuthorsInBook; i++) {
                    //если счетчик больше количесвтва авторов
                    if(i >= books[numBookForEdit - 1].getAuthors().length){
                        // добаляем нового автора в книгу
                        Author newAuthor = new Author();
                        System.out.print("Введите имя автора "+(i+1)+": ");
                        newAuthor.setFirstname(scanner.nextLine());
                        System.out.print("Введите фамилию автора "+(i+1)+": ");
                        newAuthor.setLastname(scanner.nextLine());
                        System.out.print("Введите год рождения автора "+(i+1)+": ");
                        newAuthor.setBirthday(scanner.nextInt()); scanner.nextLine();
                        books[numBookForEdit - 1].addAuthor(newAuthor);
                    }else if(i < books[numBookForEdit - 1].getAuthors().length){
                        // изменяем существующих авторов книги
                        System.out.println(i+1+"-й автор: "
                            +books[numBookForEdit - 1].getAuthors()[i].getFirstname()+" "+
                                   books[numBookForEdit - 1].getAuthors()[i].getLastname());
                        System.out.print("Изменить имя автора? (y/n)");
                        edit = scanner.nextLine();
                        if(edit.equals("y")){
                            System.out.print("Введите другое имя автора: ");
                            books[numBookForEdit - 1].getAuthors()[i].setFirstname(scanner.nextLine());
                        }    
                        System.out.print("Изменить фамилию автора? (y/n)");
                        edit = scanner.nextLine();
                        if(edit.equals("y")){
                            System.out.print("Введите другую фамилию автора: ");
                            books[numBookForEdit - 1].getAuthors()[i].setLastname(scanner.nextLine());
                        }    
                        System.out.print("Изменить год рождения автора? (y/n)");
                        edit = scanner.nextLine();
                        if(edit.equals("y")){
                            System.out.print("Введите другой год рождения автора: ");
                            books[numBookForEdit - 1].getAuthors()[i].setBirthday(scanner.nextInt());scanner.nextLine();
                        }    
                    }
                }
            }
        }
        return books;
    }
    
    private Book deleteAuthorBook(Book book) {
        for (int i = 0; i < book.getAuthors().length; i++) {
            System.out.println(
                    i+1+". "+book.getAuthors()[i].getFirstname()+" "+
                            book.getAuthors()[i].getLastname());
        }
        System.out.println("Какого автора удалить? ");
        int numDeleteAuthor = scanner.nextInt();
        scanner.nextLine();
        book.removeAuthor(numDeleteAuthor);
        return book;
    }
}
