Sample Extensibility Modules for Alfresco Share
===============================================

Author: Will Abson

This project is intended to demonstrate the new extensibility features of 
Alfresco Share 4.0 and to make it easy to define your own modules.

All the examples used in this project are taken directly from Dave Draper's
excellent tutorials on http://blogs.alfresco.com/wp/ddraper/, with some
small modifications to the folder structures.

Installation
------------

The add-on has been developed to install on top of an existing Alfresco
4.0+ installation.

An Ant build script is provided to build a JAR file containing the 
custom files, which can then be installed into the 'tomcat/shared/lib' folder 
of your Alfresco installation.

To build the JAR file, run the following command from the base project 
directory.

    ant clean dist-jar

The command should build a JAR file named hello-world-dashlet.jar
in the 'build/dist' directory within your project.

To deploy the dashlet files into a local Tomcat instance for testing, you can 
use the hotcopy-tomcat-jar task. You will need to set the tomcat.home
property in Ant.

    ant -Dtomcat.home=C:/Alfresco/tomcat hotcopy-tomcat-jar
    
Once you have run this you will need to restart Tomcat so that the classpath 
resources in the JAR file are picked up.