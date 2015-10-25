/*
 * Created on 24 Oct 2015 ( Time 23:20:28 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.kumasi.journal.business.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.kumasi.journal.domain.BookCategory;
import com.kumasi.journal.domain.jpa.BookCategoryEntity;
import com.kumasi.journal.domain.jpa.BookCategoryEntityKey;
import com.kumasi.journal.business.service.BookCategoryService;
import com.kumasi.journal.business.service.mapping.BookCategoryServiceMapper;
import com.kumasi.journal.persistence.PersistenceServiceProvider;
import com.kumasi.journal.persistence.services.BookCategoryPersistence;
import org.springframework.stereotype.Component;

/**
 * Implementation of BookCategoryService
 */
@Component
public class BookCategoryServiceImpl implements BookCategoryService {

	private BookCategoryPersistence bookCategoryPersistence;

	@Resource
	private BookCategoryServiceMapper bookCategoryServiceMapper;
	
	public BookCategoryServiceImpl() {
		bookCategoryPersistence = PersistenceServiceProvider.getService(BookCategoryPersistence.class);
	}
		
	@Override
	public BookCategory findById(Integer bookId, Integer categoryId) {
		BookCategoryEntity entity = bookCategoryPersistence.load(bookId, categoryId);
		return bookCategoryServiceMapper.mapBookCategoryEntityToBookCategory(entity);
	}

	@Override
	public List<BookCategory> findAll() {
		List<BookCategoryEntity> entities = bookCategoryPersistence.loadAll();
		List<BookCategory> beans = new ArrayList<BookCategory>();
		for(BookCategoryEntity entity : entities) {
			beans.add(bookCategoryServiceMapper.mapBookCategoryEntityToBookCategory(entity));
		}
		return beans;
	}

	@Override
	public BookCategory save(BookCategory bookCategory) {
		return update(bookCategory) ;
	}

	@Override
	public BookCategory create(BookCategory bookCategory) {

		BookCategoryEntity bookCategoryEntity = new BookCategoryEntity();
		bookCategoryServiceMapper.mapBookCategoryToBookCategoryEntity(bookCategory, bookCategoryEntity);
		BookCategoryEntity bookCategoryEntitySaved = bookCategoryPersistence.save(bookCategoryEntity);
		return bookCategoryServiceMapper.mapBookCategoryEntityToBookCategory(bookCategoryEntitySaved);
	}

	@Override
	public BookCategory update(BookCategory bookCategory) {
		BookCategoryEntity bookCategoryEntity = bookCategoryPersistence.load(bookCategory.getBookId(), bookCategory.getCategoryId());
		bookCategoryServiceMapper.mapBookCategoryToBookCategoryEntity(bookCategory, bookCategoryEntity);
		BookCategoryEntity bookCategoryEntitySaved = bookCategoryPersistence.save(bookCategoryEntity);
		return bookCategoryServiceMapper.mapBookCategoryEntityToBookCategory(bookCategoryEntitySaved);
	}

	@Override
	public void delete(Integer bookId, Integer categoryId) {
		bookCategoryPersistence.delete(bookId, categoryId);
	}

	public BookCategoryPersistence getBookCategoryPersistence() {
		return bookCategoryPersistence;
	}

	public void setBookCategoryPersistence(BookCategoryPersistence bookCategoryPersistence) {
		this.bookCategoryPersistence = bookCategoryPersistence;
	}

	public BookCategoryServiceMapper getBookCategoryServiceMapper() {
		return bookCategoryServiceMapper;
	}

	public void setBookCategoryServiceMapper(BookCategoryServiceMapper bookCategoryServiceMapper) {
		this.bookCategoryServiceMapper = bookCategoryServiceMapper;
	}

}
