Model ```SomeModel``` has two fields:
- id UUID
- data JSONB

Main class has two methods to perform ```UPDATE``` in two different ways:
- using ```DB.update(someModel)``` method
- using ```UpdateQuery``` via ```DB.update(SomeModel.class)``` call

First one works as expected, but second one generates exception:
```
javax.persistence.PersistenceException: No ScalarType registered for class java.util.HashMap
```

To run project:
```
mvn package
java -jar target/ebean-update-issue-1.0-SNAPSHOT-jar-with-dependencies.jar
```

You'll see something like this:
```
txn[1001] insert into some_model (id, data) values (?,?); -- bind(21a22571-8295-4ac5-b65b-fc52e1f1bd56,{numberField=1, textField=okUpdate})
txn[1001] Inserted [SomeModel] [21a22571-8295-4ac5-b65b-fc52e1f1bd56]
txn[1001] Commit
txn[1002] update some_model set data=? where id=?; -- bind({numberField=1, booleanField=true, textField=ok...,21a22571-8295-4ac5-b65b-fc52e1f1bd56)
txn[1002] Updated [SomeModel] [21a22571-8295-4ac5-b65b-fc52e1f1bd56]
txn[1002] Commit
txn[1003] insert into some_model (id, data) values (?,?); -- bind(43ad95e0-9add-4261-8757-8a171800c415,{numberField=2, textField=failUpdate})
txn[1003] Inserted [SomeModel] [43ad95e0-9add-4261-8757-8a171800c415]
txn[1003] Commit
txn[1004] Commit
Exception in thread "main" javax.persistence.PersistenceException: No ScalarType registered for class java.util.HashMap
        at io.ebeaninternal.server.persist.Binder.getScalarType(Binder.java:191)
        at io.ebeaninternal.server.persist.Binder.bindObject(Binder.java:216)
        at io.ebeaninternal.server.querydefn.OrmUpdateProperties$SimpleValue.bind(OrmUpdateProperties.java:73)
        at io.ebeaninternal.server.querydefn.OrmUpdateProperties.bind(OrmUpdateProperties.java:164)
        at io.ebeaninternal.server.query.CQueryPredicates.bind(CQueryPredicates.java:127)
        at io.ebeaninternal.server.query.CQueryPredicates.bind(CQueryPredicates.java:119)
        at io.ebeaninternal.server.query.CQueryUpdate.execute(CQueryUpdate.java:91)
        at io.ebeaninternal.server.query.CQueryEngine.executeUpdate(CQueryEngine.java:81)
        at io.ebeaninternal.server.query.CQueryEngine.update(CQueryEngine.java:76)
        at io.ebeaninternal.server.query.DefaultOrmQueryEngine.update(DefaultOrmQueryEngine.java:76)
        at io.ebeaninternal.server.core.OrmQueryRequest.update(OrmQueryRequest.java:394)
        at io.ebeaninternal.server.core.DefaultServer.update(DefaultServer.java:1334)
        at io.ebeaninternal.server.querydefn.DefaultOrmQuery.update(DefaultOrmQuery.java:1509)
        at io.ebeaninternal.server.expression.DefaultExpressionList.update(DefaultExpressionList.java:391)
        at org.example.Main.failUpdate(Main.java:46)
        at org.example.Main.main(Main.java:14)
```
