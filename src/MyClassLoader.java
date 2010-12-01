import java.net.URL;
import java.net.URLClassLoader;

/**
 * Class loader used so we can dynamically load classes. Normal class loader doesn't close the .jar
 * so you can't reload. This fixes that.
 * @author James
 */
public class MyClassLoader extends URLClassLoader {

    /**
     * Creates loader
     * @param urls
     * @param loader
     */
    public MyClassLoader(URL[] urls, ClassLoader loader) {
        super(urls, loader);
    }

    /**
     * Fix here.
     */
    public void close() {
        try {
            Class clazz = java.net.URLClassLoader.class;
            java.lang.reflect.Field ucp = clazz.getDeclaredField("ucp");
            ucp.setAccessible(true);
            Object sun_misc_URLClassPath = ucp.get(this);
            java.lang.reflect.Field loaders = sun_misc_URLClassPath.getClass().getDeclaredField("loaders");
            loaders.setAccessible(true);
            Object java_util_Collection = loaders.get(sun_misc_URLClassPath);
            for (Object sun_misc_URLClassPath_JarLoader : ((java.util.Collection) java_util_Collection).toArray()) {
                try {
                    java.lang.reflect.Field loader = sun_misc_URLClassPath_JarLoader.getClass().getDeclaredField("jar");
                    loader.setAccessible(true);
                    Object java_util_jar_JarFile = loader.get(sun_misc_URLClassPath_JarLoader);
                    ((java.util.jar.JarFile) java_util_jar_JarFile).close();
                } catch (Throwable t) {
                    // if we got this far, this is probably not a JAR loader so
                    // skip it
                }
            }
        } catch (Throwable t) {
            // probably not a SUN VM
        }
        return;
    }
}
