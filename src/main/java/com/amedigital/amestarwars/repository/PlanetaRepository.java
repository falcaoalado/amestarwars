package com.amedigital.amestarwars.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.amedigital.amestarwars.model.Planeta;

@Repository
public class PlanetaRepository {

	private static final String PLANETA_COLLECTION_NAME = "planetas";

	@Autowired
	private MongoTemplate mongo;

	@SuppressWarnings("deprecation")
	public void add(Planeta planeta) throws Exception {

		if (!mongo.collectionExists(Planeta.class)) {
			mongo.createCollection(Planeta.class);
		}
		
		if (mongo.getCollection(PLANETA_COLLECTION_NAME).count() == 0) {
			planeta.setId(1L);
		} else {
			Query query = new Query().with(new Sort(Sort.Direction.DESC, "_id"));
			Long id = mongo.findOne(query, Planeta.class, PLANETA_COLLECTION_NAME).getId();
			planeta.setId(Long.sum(id, 1L));
		}
		
		mongo.insert(planeta, PLANETA_COLLECTION_NAME);
	}
	
	public List<Planeta> findAll() throws Exception {
		List<Planeta> planetas = mongo.findAll(Planeta.class);
		return planetas;
	}

	public Planeta findByName(String nome) throws Exception {
		Planeta planeta = mongo.findOne(Query.query(Criteria.where("nome").is(nome)), Planeta.class, PLANETA_COLLECTION_NAME);
		return planeta;
	}
	
	public Planeta findById(Long id) throws Exception {
		Planeta planeta = mongo.findById(id, Planeta.class, PLANETA_COLLECTION_NAME);
		return planeta;
	}
	
	public void remove(Planeta planeta) throws Exception {
		mongo.remove(planeta, PLANETA_COLLECTION_NAME);
	}
}