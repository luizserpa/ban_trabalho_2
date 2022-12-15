package DAOs;

import Entidades.IdGenerico;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericDAO {



    private ConnectionMongoDb connectionMongoDb = null;
    protected Gson gson = new Gson();


    protected void gerarConn(){
        if (connectionMongoDb == null){
            connectionMongoDb = ConnectionMongoDb.getInstance();
        }
    }

    protected String objectToJson(Object o){
        return gson.toJson(o);
    }


    protected void insertOne(String table, Object o){
        gerarConn();
        if (o instanceof IdGenerico){
            ((IdGenerico) o).set_id(new ObjectId().hashCode());
            Document d = Document.parse(objectToJson(o));
            connectionMongoDb.getCollection(table).insertOne(d);
        }
    }

    private FindIterable <Document>  find (String table, String key ){
        gerarConn();
        MongoCollection<Document> collection = connectionMongoDb.getCollection(table);
        Document query = Document.parse(key);
        return collection.find(query);
    }

    protected String findOne(String table, String key){
        gerarConn();
        Document d = find(table, key).first();
        return d != null ? d.toJson() : "";
    }

    protected List<String> findList(String table, String key){
        gerarConn();
        MongoCursor <Document> list = find(table, key).iterator();

        List<String> d = new ArrayList<>();

        while (list.hasNext()){
            d.add(list.next().toJson());
        }

        return d;
    }

    protected void update (String table, Integer id, Object o){
        if (o instanceof IdGenerico){
            gerarConn();
            MongoCollection<Document> collection = connectionMongoDb.getCollection(table);
            Document d = Document.parse(gson.toJson(o));
            BasicDBObject setQuery = new BasicDBObject();
            setQuery.append("$set", d);


            collection.updateOne(Filters.eq("_id", id), setQuery);
        }

    }

    protected void delete (String table, Integer id){
        gerarConn();
        MongoCollection<Document> collection = connectionMongoDb.getCollection(table);
        collection.deleteOne(Filters.eq("_id", id));
    }

    protected String maxValue (String table, String field){
        gerarConn();
        MongoCollection<Document> collection = connectionMongoDb.getCollection(table);
        AggregateIterable<Document> results = collection.aggregate(Arrays.asList(
                Aggregates.group(null, Accumulators.max("max_value", "$" + field))
        ));

        Document fist = results.first();
        return fist == null ? "0" : fist.get("max_value").toString();
    }

    protected List<String> listar (String table){
        gerarConn();
        List<String> d = new ArrayList<>();
        MongoCollection<Document> collection = connectionMongoDb.getCollection(table);
        MongoCursor<Document> cursor = collection.find().iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();
            d.add(doc.toJson());
        }

        return d;
    }




}
