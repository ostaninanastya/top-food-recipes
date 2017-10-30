
#!/bin/bash
$TOMCAT_HOME/bin/shutdown.sh
rm -rf $TOMCAT_HOME/webapps/top_food_recipes*
git pull
mvn clean package
cd target
cp *.war $TOMCAT_HOME/webapps
$TOMCAT_HOME/bin/startup.sh
