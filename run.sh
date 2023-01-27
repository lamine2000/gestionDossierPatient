#!/bin/fish

asadmin start-domain gdpdomain;
mvn clean package && asadmin deploy --force target/gdp.war
