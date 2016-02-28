package com.gaohuan.mongodb;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.ListCollectionsIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.group;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Updates.inc;
import static com.mongodb.client.model.Updates.set;
import static java.util.Arrays.asList;


/**
 * Created by huan on 2016/2/28.
 */
public class MongodbTest {
    public static void main(String[] args) {

        MongoClient mongo = new MongoClient("192.168.1.118", 10000);
        MongoDatabase db = mongo.getDatabase("local");

        MongoCollection<Document> collection = db.getCollection("new_test");

        /*
        ListCollectionsIterable<Document> collections = db.listCollections();
        MongoCursor<Document> cursor = collections.iterator();
        if (cursor.hasNext()) {
            Document document = cursor.next();
            System.out.println(document.toString());
        }
        */

        /*
        MongoCollection<Document> collection = db.getCollection("new_test");
        Document document = new Document();
        document.append("title", "MongoDB")
                .append("description", "database").
                append("likes", 100).
                append("url", "http://www.w3cschool.cc/mongodb/").
                append("by", "w3cschool.cc");
        collection.insertOne(document);
        */
        /*
        MongoCollection<Document> collection = db.getCollection("new_test");
        MongoCursor<Document> cursor = collection.find().iterator();
        while (cursor.hasNext()) {
            Document document = cursor.next();
            System.out.println(document);
            document.put("title","MongoDB_Update");
            collection.updateOne(Filters.eq("title","MongoDB"),new Document("$set",document));
        }
        */
        /*
        //insertMany
        List<Document> documents = new ArrayList<Document>();
        for (int i = 0; i < 100; i++) {
            documents.add(new Document("i", i));
        }
        collection.insertMany(documents);
        */
        //count
        System.out.println(collection.count());

        //query
        Document firstDoc = collection.find().first();
        System.out.println(firstDoc != null ? firstDoc.toJson() : "");

        //findAll
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }

        // a query
        Document doc = collection.find(eq("i", 71)).first();
        System.out.println(doc != null ? doc.toJson() : "");

        //a set of documents
        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(Document document) {
                System.out.println(document.toJson());
            }
        };
        collection.find(gt("i", 50)).forEach(printBlock);
        collection.find(and(gt("i", 50), lte("i", 100))).forEach(printBlock);

        //sorting
        doc = collection.find(exists("i")).sort(descending("i")).first();
        System.out.println(doc != null ? doc.toJson() : "");

        //project fields

        doc = collection.find().projection(excludeId()).first();
        System.out.println(doc != null ? doc.toJson() : "");

        collection.aggregate(asList(match(gt("i", 0)), project(Document.parse("{ITimes10: {$multiply: ['$i', 10]}}")))).forEach(printBlock);

        doc = collection.aggregate(Collections.singletonList(group(null, sum("total", "$i")))).first();
        System.out.println(doc != null ? doc.toJson() : "");

        //update
        collection.updateOne(eq("i", 10), set("i", 110));

        UpdateResult updateResult = collection.updateMany(lt("i", 100), inc("i", 100));
        System.out.println(updateResult.getModifiedCount());

        collection.deleteOne(eq("i", 110));

        DeleteResult deleteResult = collection.deleteMany(gte("i", 100));
        System.out.println(deleteResult.getDeletedCount());
        /*
        //order bulk
        collection.bulkWrite(Arrays.asList(
                new InsertOneModel<Document>(new Document("_id", 4)),
                new InsertOneModel<Document>(new Document("_id", 5)),
                new InsertOneModel<Document>(new Document("_id", 6)),
                new UpdateOneModel<Document>(new Document("_id", 1), new Document("$set", new Document("x", 2))),
                new DeleteOneModel<Document>(new Document("_id", 2)),
                new ReplaceOneModel<Document>(new Document("_id", 3), new Document("_id", 3).append("x", 4))
                )
        );
        */
        //unorder
        collection.bulkWrite(Arrays.asList(
                new InsertOneModel<Document>(new Document("_id", 4)),
                new InsertOneModel<Document>(new Document("_id", 5)),
                new InsertOneModel<Document>(new Document("_id", 6)),
                new UpdateOneModel<Document>(new Document("_id", 1), new Document("$set", new Document("x", 2))),
                new DeleteOneModel<Document>(new Document("_id", 2)),
                new ReplaceOneModel<Document>(new Document("_id", 3), new Document("_id", 3).append("x", 4))
                ), new BulkWriteOptions().ordered(false)

        );
    }
}
