package journals.repositorio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;

import journals.modelo.Editorial;
import repositorio.RepositorioException;
import repositorio.RepositorioMongoDB;
import utils.PropertiesReader;

public class RepositorioEditorialAdHocMongoDB extends RepositorioMongoDB<Editorial>
		implements RepositorioEditorialAdHoc {

	protected MongoClient mongoClient;
	protected MongoDatabase database;
	protected MongoCollection<Editorial> coleccion;

	public RepositorioEditorialAdHocMongoDB() {
		PropertiesReader properties;
		try {
			properties = new PropertiesReader("mongo.properties");

			String connectionString = properties.getProperty("mongouri");

			MongoClient mongoClient = MongoClients.create(connectionString);

			String mongoDatabase = properties.getProperty("mongodatabase");

			database = mongoClient.getDatabase(mongoDatabase);

			CodecRegistry defaultCodecRegistry = CodecRegistries.fromRegistries(
					MongoClientSettings.getDefaultCodecRegistry(),
					CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));

			coleccion = database.getCollection("editorial", Editorial.class).withCodecRegistry(defaultCodecRegistry);

		} catch (Exception e) {

		}
	}

	@Override
	public MongoCollection<Editorial> getColeccion() {
		return coleccion;
	}

	@Override
	public List<Editorial> getEditorialByPaises(List<String> paises) throws RepositorioException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Editorial> getEditorialByPaisesv2(List<String> paises) throws RepositorioException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Editorial> getEditorialByYears(int year) throws RepositorioException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Editorial> getEditorialSinDireccion() throws RepositorioException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Document> getEstadisticasPorPais() throws RepositorioException {
		// TODO Auto-generated method stub
		return null;
	}
	

	
}
