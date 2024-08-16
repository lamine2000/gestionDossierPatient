#!/bin/fish

asadmin start-domain gdpdomain;lamine
mvn clean package && asadmin deploy --force target/gdp.war
