package main.aggregation;

import com.mongodb.MongoClient;
import main.model.Product;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.stereotype.Service;

@Service
public class LookupAggregation {

    MongoTemplate mongoTemplate = new MongoTemplate(
            new SimpleMongoDbFactory(new MongoClient(),
                    "shopsandproducts")
    );

    public Double getAverageProductsPrice() {
        String fieldName = "averageProductsPrice";
        GroupOperation group = Aggregation.group().avg("price").as(fieldName);

        TypedAggregation<Product> aggregation = Aggregation.newAggregation(
                Product.class,
                group
        );

        AggregationResults<Product> results =
                mongoTemplate.aggregate(aggregation, Product.class);

        return (Double) getResultFromJson(results.getRawResults().toJson(), fieldName);
    }

    private Object getResultFromJson(String json, String resultName) {
        JSONParser parser = new JSONParser();
        JSONObject resultJson = new JSONObject();
        try {
            resultJson = (JSONObject) parser.parse(json);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray arrJson = (JSONArray)  resultJson.get("results");
        JSONObject averageProductsPriceJson = (JSONObject) arrJson.get(0);
        return averageProductsPriceJson.get(resultName);
    }
}