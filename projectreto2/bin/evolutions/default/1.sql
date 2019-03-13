# Person schema

# --- !Ups
CREATE TABLE Person (
    
    id bigint(20) NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    lastname varchar(255) NOT NULL,
    identification int NOT NULL,    
    identificationtype varchar(10) NOT NULL,
    age int NOT NULL,  
    birthday date NOT NULL,
    PRIMARY KEY (id)
    
);

# --- !Downs

DROP TABLE Person IF EXIST;
