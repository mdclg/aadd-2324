package journals.servicio.test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import journals.modelo.Direccion;
import journals.modelo.Editorial;
import journals.modelo.Revista;
import journals.repositorio.RepositorioJournalAdHoc;
import repositorio.FactoriaRepositorios;
import utils.PropertiesReader;

public class ProgramaMongoDBCodec {

	public static void main(String[] args) {
		PropertiesReader properties;
		try {
			properties = new PropertiesReader("mongo.properties");

			String connectionString = properties.getProperty("mongouri");
			String databaseString = properties.getProperty("mongodatabase");

			MongoClient mongoClient = MongoClients.create(connectionString);

			MongoDatabase database = mongoClient.getDatabase(databaseString);

			CodecRegistry defaultCodecRegistry = CodecRegistries.fromRegistries(
					MongoClientSettings.getDefaultCodecRegistry(),
					CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));

			MongoCollection<Editorial> coleccion = database.getCollection("editorial", Editorial.class).withCodecRegistry(defaultCodecRegistry);
			
			
			Direccion dir = new Direccion("El pensador", 12, "Murcia", "30234");
			Editorial editorial = new Editorial("Miarma", LocalDate.now(),"Susan Sontag",Arrays.asList("España", "USA", "Canada", "Reino Unido", "Alemania"), dir);
			coleccion.insertOne(editorial);
			
			Direccion dir2 = new Direccion("Alegría", 13, "Alicante", "50293");
			Editorial editorial2 = new Editorial("NewCastle", LocalDate.of(2020, 1, 8),"Jose Antonio Esteban",Arrays.asList("España","Portugal", "Francia", "Reino Unido", "Alemania"), dir2);
			coleccion.insertOne(editorial2);
			
			Direccion dir3 = new Direccion("Whatever", 8, "Murcia", "30190");
			Editorial editorial3 = new Editorial("Paparajote", LocalDate.of(2013, 24, 9),"Carmen García Solís",Arrays.asList("España","Mexico", "Italia", "Austria", "Grecia"), dir3);
			coleccion.insertOne(editorial3);
			
			Direccion dir4 = new Direccion("Rio Júcar", 5, "Almería", "40389");
			Editorial editorial4 = new Editorial("Gatopardo", LocalDate.of(2008, 12, 28),"Elisa Cainegro",Arrays.asList("España","Portugal", "Italia", "Mexico", "Irlanda"), dir4);
			coleccion.insertOne(editorial4);
			
			Direccion dir5 = new Direccion("Príncipe de Asturias", 11, "Gijón", "23223");
			Editorial editorial5 = new Editorial("Duermevela", LocalDate.of(1983, 10, 5),"Almudena Rodríguez",Arrays.asList("España","Mexico", "Colombia", "Ecuador", "Argentina"), dir5);
			coleccion.insertOne(editorial5);
			
			Direccion dir6 = new Direccion("Calle Mayor", 3, "Oviedo", "50890");
			Editorial editorial6 = new Editorial("Hoja de lata", LocalDate.of(2022, 11, 8),"Mario Llomescusa",Arrays.asList("España","Portugal", "Brasil", "Argentina", "Mexico"), dir6);
			coleccion.insertOne(editorial6);
			
			
			Direccion dir7 = new Direccion("Calle Perdida", 12, "Oviedo", "50990");
			Editorial editorial7 = new Editorial("Errata Naturae", LocalDate.of(1995, 4, 4),"Elvira Disco",Arrays.asList("España","Mexico", "Argentina", "Francia", "Italia"), dir7);
			coleccion.insertOne(editorial7);
			
			Direccion dir8 = new Direccion("El mirador", 20, "Gijón", "23456");
			Editorial editorial8 = new Editorial("Periférica", LocalDate.of(2017, 10, 18),"Alicia Laorden",Arrays.asList("España","Reino Unido", "Italia", "Mexico", "Argentina"), dir8);
			coleccion.insertOne(editorial8);
			
			Direccion dir9 = new Direccion("Calle Goya", 15, "Barcelona", "30234");
			Editorial editorial9 = new Editorial("Alba Editorial", LocalDate.of(2005, 1, 28),"Jose Capde",Arrays.asList("España","Portugal", "Colombia", "Mexico", "Ecuador"), dir9);
			coleccion.insertOne(editorial9);
			
			
			Direccion dir10 = new Direccion("Calle Los Alcaldes", 9, "Madrid", "30234");
			Editorial editorial10 = new Editorial("Alianza", LocalDate.of(2006, 3, 17),"Asunción Martínez",Arrays.asList("España","Portugal", "Canada", "Alemania", "Italia"), dir10);
			coleccion.insertOne(editorial10);
			
			Editorial editorial11 = new Editorial("Editorial sin dirección", LocalDate.of(2019, 11, 12),"Prudencia Lagurio",Arrays.asList("España","Portugal", "Irlanda", "Alemania", "Italia"), null);
			coleccion.insertOne(editorial11);
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
