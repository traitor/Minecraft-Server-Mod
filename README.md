hMod
====================
hMod is a server mod for the Minecraft Beta server software. This is only the source code, if you don't know how to use this you won't find it useful.

Compiling
---------
Add a new project from existing sources in whatever IDE you use. Add minecraft_servero.jar as a library, and mysql-connector-java-bin.jar if need be. Your IDE should compile .class files that you can copy over into a clean minecraft_server.jar, then you can test your changes.


minecraft_servero.jar
---------
You can get this file by running jarjar.bat in the jarjar folder and it will automaticly make minecraft_servero.jar