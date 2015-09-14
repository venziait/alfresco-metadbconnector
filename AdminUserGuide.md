# Installation #

_Tested Server environment :_
  * Ubuntu Server 10.10 LTS 64bits.
  * Java(TM) SE Runtime Environment (build 1.6.0\_26-b03).
  * **Alfresco Community 4.0.d**.
  * MySQL 5.1.61-0ubuntu0.10.10.1 (for Alfresco and sample Connectors).
  * MSSQL Server 2005 (for sample tested Connectors).
  * PostgreSQL 7 (for sample tested Connectors).
  * Oracle Express 11g (for sample tested Connectors).

_Tested Browsers :_
  * Chromium & Google Chrome 18.0.1025.151
  * Mozilla Firefox 3.6.6 for Windows & 11.0 for Ubuntu Desktop
  * Internet Explorer 8.0.6001.18702
  * Opera 11.62

_Prerequisites :_
  * An Alfresco instance running and jdbc driver jar file in tomcat/lib folder (if different to MySQL).
  * Installation of AMP module in alfresco.war, sakilaDB files in local (or accesible) MySQL server and copy of jar file in Share webapp lib folder.

**_Deploy Steps :_**<br><br>
<b>1</b>.Stop Alfresco Server.<br><br>
<i>#Alfresco amp module installation</i><br>
<b>2</b>.Copy <a href='http://alfresco-metadbconnector.googlecode.com/files/mdbc-1.0-SNAPSHOT.amp'>mdbc-1.0-SNAPSHOT.amp</a> file to $ALF_ROOT/amps<br>
<b>3</b>.Remove directories :<br>
-$ALF_ROOT/tomcat/webapps/alfresco<br>
-$ALF_ROOT/tomcat/work/Catalina/localhost/alfresco<br>
-$ALF_ROOT/tomcat/work/Catalina/localhost/share<br>
<b>4</b>.Install the amp in alfresco war file with the command :<br>
cd $ALF_ROOT<br>
sudo java -jar bin/alfresco-mmt.jar install amps/mdbc-1.0-SNAPSHOT.amp tomcat/webapps/alfresco.war<br>
(note we use sudo because the installation path is /opt/alfresco-4.0.d and the command is launched for a non-root user)<br><br>
<i>#Alfresco Share jar file</i><br>
<b>5</b>.Copy <a href='http://alfresco-metadbconnector.googlecode.com/files/share-mdbc-component-0.1.jar'>share-mdbc-component-0.1.jar</a> to $ALF_ROOT/tomcat/webapps/share/WEB-INF/lib<br>
<b>6</b>.Start the Server.<br><br>

<h1>Configuration and Extension</h1>

<b><i>Sample Default Configuration</i></b><br>
By default there are two connectors defined in AMP module :<br>
-$ALF_ROOT/tomcat/webapps/alfresco/WEB-INF/classes/alfresco/module/mdbc/connectors/<b>customer-connector.xml</b><br>
-$ALF_ROOT/tomcat/webapps/alfresco/WEB-INF/classes/alfresco/module/mdbc/connectors/<b>country-connector.xml</b><br>
These connectors are configured for query a MySQL public database called "<a href='http://dev.mysql.com/doc/sakila/en/sakila.html'>SakilaDB</a>".<br>
To install the Sakila sample database go to <a href='http://dev.mysql.com/doc/sakila/en/sakila.html#sakila-installation'>Installation</a> instructions (Win based) or use the following commands (Unix based) :<br>
<i>You can download a copy of database schema and data from this project download section (<a href='http://alfresco-metadbconnector.googlecode.com/files/sakila-db.zip'>SakilaDB file</a>)</i><br>
mysql -u root -p < path/to/sakila-schema.sql<br>
(Enter root password)<br>
mysql -u root -p < path/to/sakila-data.sql<br>
(Enter root password)<br>
If there are no errors, the process is completed successfully.<br><br>
NOTE : For use MySQL jdbc Driver on our MetaDBConnectors, customer or country for example, ensure that mysql-connector-java-5.?.?.jar file already exists in $ALF_ROOT/tomcat/lib folder.<br><br>

<b><i>Extension Procedure</i></b><br>
To carry out the process of generating our own "Connectors" elegantly we recommend downloading the source code, available on the <a href='http://code.google.com/p/alfresco-metadbconnector/source/checkout'>source code Tab</a> and adapt it, following the existing "Connectors" examples, to our needs.<br>
On the other hand you can carry out the configuration of the component content in the original files of the AMP module adapting it to our needs, but what we suggest is to locate any file that can be modified to the folder tomcat/shared/classes/alfresco. This will require defining some configuration files that perform load changes placed in this extension path.<br><br>

<b><i>Sample Extension Procedure</i></b><br>
ToDO