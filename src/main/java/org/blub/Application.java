package org.blub;

import org.blub.domain.Document;
import org.blub.repository.DocumentRepository;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.*;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.RemoteServer;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.data.neo4j.server.Neo4jServer;

@SpringBootApplication
@EnableNeo4jRepositories(basePackages = "org.blub.repository")
@EnableTransactionManagement
public class Application extends Neo4jConfiguration {

    @Override
    public Neo4jServer neo4jServer() {
        return new RemoteServer("http://localhost:7474","neo4j","neo4");
    }

    @Override
    public SessionFactory getSessionFactory() {
        return new SessionFactory("org.blub.domain");
    }

    public static void main(String[] args) throws Exception {

        SpringApplication.run(Application.class, args);


    }
}
