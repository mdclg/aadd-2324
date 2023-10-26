package journals.servicio.test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

import utils.PropertiesReader;

public class ProgramaMongoDB {

	public static Document createDocument(String nombre, LocalDate fechaFundacion, String jefe, List<String> paises,
			String calle, Integer numero, String ciudad, String codigoPostal) {

		Document d = new Document();
		d.append("nombre", nombre).append("fecha_fundacion", fechaFundacion);
		d.append("editor_jefe", jefe);
		d.append("paises_publicacion", paises);

		Document direccion = new Document();
		direccion.append("calle", calle);
		direccion.append("numero", numero);
		direccion.append("ciudad", ciudad);
		direccion.append("codigo_postal", codigoPostal);
		d.append("sede", direccion);
		return d;
	}

	public static String insertOneDocument(MongoCollection<Document> coleccion, Document d) {
		InsertOneResult result = coleccion.insertOne(d);

		if (result.getInsertedId() != null)
			return result.getInsertedId().asObjectId().getValue().toString();
		return null;
	}

	public static void updateOneDocument(MongoCollection<Document> coleccion, Document filtro, Document actualizacion) {

		

		UpdateResult result = coleccion.updateOne(filtro, new Document("$set", actualizacion));
		System.out.println(result);

	}

	public static void upsertDocument(MongoCollection<Document> coleccion, Document filtro, Document actualizacion) {

		UpdateOptions opciones = new UpdateOptions();

		opciones.upsert(true);

		UpdateResult result = coleccion.updateOne(filtro, new Document("$set", actualizacion), opciones);
		System.out.println(result);

	}
	
	public static void deleteDocument(MongoCollection<Document> coleccion, Document documento) {

		
		DeleteResult result = coleccion.deleteOne(documento);
		System.out.println(result);

	}
	
	
	
	

	public static void main(String[] args) {

		PropertiesReader properties;
		try {
			properties = new PropertiesReader("mongo.properties");

			String connectionString = properties.getProperty("mongouri");

			MongoClient mongoClient = MongoClients.create(connectionString);

			MongoDatabase database = mongoClient.getDatabase("Cluster0");
			MongoCollection<Document> coleccion = database.getCollection("editorial");

			Document d = createDocument("Elsevier",  LocalDate.now(), "Pietro Eco", Arrays.asList("España", "Francia", "Reino Unico", "Alemania", "Portugal"), 
					"La almazara", 18, "Murcia", "30890");
			
			

			InsertOneResult result = coleccion.insertOne(d);
			
			
			Document filtro =new Document("_id", result.getInsertedId());
			/*Document nuevoNombre = new Document("nombre", "Elsejuan");
			updateOneDocument(coleccion, filtro, nuevoNombre);
			*/
			/*
			Document nuevoNombre = new Document("nombre", "Elsejuan");
			upsertDocument(coleccion, new Document("_id", "fadfadfafaff"), nuevoNombre);
			*/
			
			//deleteDocument(coleccion, filtro);
			

			mongoClient.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
