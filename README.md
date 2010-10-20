Minecraft Server Mod
====================
This is a server mod for the Minecraft Alpha server software. This is only the source code, if you don't know how to use this you won't find it useful.

Compiling
---------
Add a new project from existing sources in whatever IDE you use. Add minecraft_server.jar as a library, and mysql-connector-java-bin.jar if need be. Your IDE should compile .class files that you can copy over into a clean minecraft_server.jar, then you can test your changes.

Or you can just open it as a netbeans project, ignore the missing library warning and build minecraft_server.jar will be downloaded for you and stored in lib/.

Or you can run 'ant dist' to download minecraft_server.jar, build everything and place everything in dist/.