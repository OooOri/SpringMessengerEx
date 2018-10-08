package com.holaris.Messenger.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.stereotype.Repository;

import com.holaris.Messenger.model.Account;

@Repository
public class AccountDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public List<Account> searchAccounts(String searchText) {
		
		
		org.hibernate.search.jpa.FullTextQuery jpaQuery = searchAccountsQuery(searchText);

		
		List<Account> userList = jpaQuery.getResultList();

		return userList;
	}

	public org.hibernate.search.jpa.FullTextQuery searchAccountsQuery(String searchText) {
		
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		org.hibernate.search.query.dsl.QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
				.buildQueryBuilder()
				.forEntity(Account.class)
				.get();
				
		org.apache.lucene.search.Query luceneQuery = queryBuilder
			.keyword()
			.wildcard()
			.onFields("username")
				.boostedTo(5f)
			.matching(searchText + "*")
			.createQuery();
		
		org.hibernate.search.jpa.FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Account.class);
		
		return jpaQuery;
	}

}
