Alfresco Share component that allows to store database complex information on content metadata following "Alfresco Way". 
This capability adds another level of information enrichment to your content as well as many alternatives for the integration of your data ... and more ;P.

https://www.youtube.com/watch?v=Ayt9vBNQo3k

More info about support, quick start and extended services at http://venzia.es/#contact-form



-------------------------------------------------



#Installation

Tested Server environment.

    Ubuntu Server 10.10 LTS 64bits.
    Java(TM) SE Runtime Environment (build 1.6.0_26-b03).
    Alfresco Community 4.0.d.
    MySQL 5.1.61-0ubuntu0.10.10.1 (for Alfresco and sample Connectors).
    MSSQL Server 2005 (for sample tested Connectors).
    PostgreSQL 7 (for sample tested Connectors).
    Oracle Express 11g (for sample tested Connectors). 

Tested Browsers.

    Chromium & Google Chrome 18.0.1025.151
    Mozilla Firefox 3.6.6 for Windows & 11.0 for Ubuntu Desktop
    Internet Explorer 8.0.6001.18702
    Opera 11.62 

Prerequisites.

    An Alfresco instance running and jdbc driver jar file in tomcat/lib folder (if different to MySQL).
    Installation of AMP module in alfresco.war, sakilaDB files in local (or accesible) MySQL server and copy of jar file in Share webapp lib folder. 

Deploy Steps.

1.Stop Alfresco Server.

2.Copy mdbc-1.0-SNAPSHOT.amp file to $ALF_ROOT/amps.

3.Remove directories :

-$ALF_ROOT/tomcat/webapps/alfresco

-$ALF_ROOT/tomcat/work/Catalina/localhost/alfresco

-$ALF_ROOT/tomcat/work/Catalina/localhost/share

4.Install the amp in alfresco war file with the command :

cd $ALF_ROOT

sudo java -jar bin/alfresco-mmt.jar install amps/mdbc-1.0-SNAPSHOT.amp tomcat/webapps/alfresco.war

(note we use sudo because the installation path is /opt/alfresco-4.0.d and the command is launched for a non-root user)

5.Copy share-mdbc-component-0.1.jar to $ALF_ROOT/tomcat/webapps/share/WEB-INF/lib

6.Start the Server.


#Configuration and Extension

Sample Default Configuration

By default there are two connectors defined in AMP module :

-$ALF_ROOT/tomcat/webapps/alfresco/WEB-INF/classes/alfresco/module/mdbc/connectors/customer-connector.xml

-$ALF_ROOT/tomcat/webapps/alfresco/WEB-INF/classes/alfresco/module/mdbc/connectors/country-connector.xml

These connectors are configured for query a MySQL public database called "SakilaDB".

To install the Sakila sample database go to Installation instructions (Win based) or use the following commands (Unix based) :

You can download a copy of database schema and data from this project download section (SakilaDB file)

mysql -u root -p < path/to/sakila-schema.sql

(Enter root password)

mysql -u root -p < path/to/sakila-data.sql

(Enter root password)

If there are no errors, the process is completed successfully.


NOTE : For use MySQL jdbc Driver on our MetaDBConnectors, customer or country for example, ensure that mysql-connector-java-5.?.?.jar file already exists in $ALF_ROOT/tomcat/lib folder.

Extension Procedure

To carry out the process of generating our own "Connectors" elegantly we recommend downloading the source code, available on the source code Tab and adapt it, following the existing "Connectors" examples, to our needs.
On the other hand you can carry out the configuration of the component content in the original files of the AMP module adapting it to our needs, but what we suggest is to locate any file that can be modified to the folder tomcat/shared/classes/alfresco. This will require defining some configuration files that perform load changes placed in this extension path.
