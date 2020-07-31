package ru.waytosky.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import ru.waytosky.entity.Book;

import java.util.concurrent.ThreadLocalRandom;

public class BooksService extends Service<FilteredList<Book>> {
    @Override
    protected Task<FilteredList<Book>> createTask() {

        return new Task<FilteredList<Book>>() {
            @Override
            protected FilteredList<Book> call() throws Exception {

                ObservableList<Book> books = FXCollections.observableArrayList();
                for(int i=0;i<1000;i++){
                    if(isCancelled()){
                        break;
                    }
                    books.add(new Book(ThreadLocalRandom.current().nextInt(0, 1000000)));
                }
                return new FilteredList<>(books, b -> true);
            }
        };
    }
}
