#!/bin/fish

asadmin start-domain gdpdomain;lamine modou
mvn clean package && asadmin deploy --force target/gdp.war
