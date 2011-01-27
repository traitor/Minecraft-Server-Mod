
DO NOT RUN Minecraft_Servero.jar. DO NOT. IT WILL NOT WORK.

Edit the newly created files accordingly.

If you wish to use mysql you will have to run the server once, open server.properties and set data-source to mysql.
Run it again, it'll error out. Don't worry. There should be a mysql.properties file now. Edit to your server, username, password, and db.
Don't forget to execute the included minecraft.sql file on your database.

To compile the source you will have to add minecraft_servero.jar to your class path.
If using an IDE:
Eclipse:
Right click your project, hit properties. Go to build path then hit the libraries tab. Add minecraft_servero.jar
Netbeans:
Right click Libraries and then Add JAR/Folder then open minecraft_servero.jar