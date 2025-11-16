package cc.duduhuo.simplememcache.springboot.demo.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BookRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookRepository.class);

    public BookEntity getBookByCode(String code) {
        LOGGER.info("Get book by code from BookRepository: {}", code);
        return new BookEntity(code, "Book #" + code, "Author #" + code);
    }
}
