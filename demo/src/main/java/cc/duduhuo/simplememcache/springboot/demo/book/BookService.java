package cc.duduhuo.simplememcache.springboot.demo.book;

import cc.duduhuo.simplememcache.SimpleCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);
    private final BookRepository bookRepository;
    private final SimpleCache<String, BookEntity> simpleCache;

    public BookService(BookRepository bookRepository, SimpleCache<String, BookEntity> simpleCache) {
        this.bookRepository = bookRepository;
        this.simpleCache = simpleCache;
        LOGGER.info("simpleCache instance is: {}", this.simpleCache);
    }

    public BookEntity getBookByCode(String code) {
        return simpleCache.getOrLoad(code, bookRepository::getBookByCode);
    }
}
