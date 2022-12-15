package DAOs;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionMongoDb {


    private MongoClient mongoClient;
    private static ConnectionMongoDb connectionDb;


//sudo systemctl start mongod
    private ConnectionMongoDb() {
        connectionDb = null;
        mongoClient = null;
    }

    public MongoCollection<Document> getCollection (String table){
        try {
            if (mongoClient == null ){
                mongoClient = MongoClients.create("mongodb://localhost:27017");
            }

            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("trabalho_2");
            MongoCollection<Document> collection = sampleTrainingDB.getCollection(table);
            if (collection == null){
                sampleTrainingDB.createCollection(table);
                collection = sampleTrainingDB.getCollection(table);
            }
            return collection;
        }catch (Exception ignored){}
        return null;
    }

    public static ConnectionMongoDb getInstance() {
        if (connectionDb == null) {
            connectionDb = new ConnectionMongoDb();
        }
        return connectionDb;
    }
}
