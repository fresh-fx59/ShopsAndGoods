# ShopsAndGoods

This JAVA program intended to show basic operations with data in MongoDB with the help of RESTful API.
Common control panel address http://localhost:8080/control-panel/ for local setup.

Before first start you should setup MongoDB in Docker https://hub.docker.com/_/mongo or on your machine or on remote server and edit settings in 

- application.properties

  spring.data.mongodb.host=localhost
  spring.data.mongodb.port=27017
  spring.data.mongodb.uri=mongodb://localhost/shopsandproducts
  spring.data.mongodb.database=shopsandproducts

- MongoConfig

  final ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
  return new MongoTemplate(mongo(), "shopsandproducts");
