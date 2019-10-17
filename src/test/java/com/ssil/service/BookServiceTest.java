package service;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.ssil.exception.ResourceNotFoundExcetion;
import com.ssil.model.Book;
import com.ssil.repository.BookRepository;
import com.ssil.service.BookServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    BookRepository repository;

    @InjectMocks
    private BookServiceImpl bookService = new BookServiceImpl();

    private Book book;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        book = new Book();
        book.setId(1);
        book.setTitle("title1");
    }

    @Test
    public void testGetBooks() {
        List<Book> books = new ArrayList<Book>();
        books.add(book);

        when(repository.findAll()).thenReturn(books);

        List<Book> savedBooks = bookService.getBooks();

        assertEquals("books list size not expected", 1, savedBooks.size());
    }

    @Test
    public void testGetBook() throws ResourceNotFoundExcetion {
        when(repository.findById(1)).thenReturn(java.util.Optional.ofNullable(book));

        Book savedBook = bookService.getBook(1);

        assertEquals("book id not expected", 1, savedBook.getId());
        assertEquals("book title not expected", "title1", savedBook.getTitle());
    }

    @Test(expected = ResourceNotFoundExcetion.class)
    public void testResourceNotFoundExceptionWhenBookNotPresentWithGivenId() throws ResourceNotFoundExcetion {
        bookService.getBook(999);
        fail("this statement execution is not expected");
    }

    @After
    public void tearDown() {
        book = null;
    }


}
