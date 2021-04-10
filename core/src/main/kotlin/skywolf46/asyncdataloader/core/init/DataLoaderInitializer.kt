package skywolf46.asyncdataloader.core.init

import skywolf46.asyncdataloader.core.abstraction.AbstractDataLoaderInitializer
import java.io.File
import java.lang.reflect.Modifier
import java.net.URL
import java.net.URLClassLoader
import java.util.*
import java.util.jar.JarFile


object DataLoaderInitializer {
    fun initialize() {
        println("AsyncDataLoader | Initializing")

        println("AsyncDataLoader | Extracting classpath...")


        val url = try {
            var value: Array<URL> = arrayOf()
            val thread = Thread.currentThread().contextClassLoader


            try {
                var mcClass: Class<out Any>? = null
                try {
                    mcClass = Class.forName("org.bukkit.Bukkit")
                } catch (_: Exception) {
                }
                if (mcClass != null) {
                    println("AsyncDataLoader | Minecraft bukkit detected! Using plugins directory.")
                    val lst = arrayListOf<URL>()
                    File("plugins/").listFiles().forEach {
                        if (it.extension == "jar") {
                            lst += it.toURI().toURL()
                        }
                    }
                    value = lst.toArray(arrayOf<URL>())
                } else {
                    value = (thread as URLClassLoader).urLs
                    println("AsyncDataLoader | URL extracting succeed with URlClassLoader extraction")
                }

            } catch (ex: Exception) {
                println("AsyncDataLoader | Failed to extract with URLClassLoader. Try with AppClassLoader...")
                try {
                    val field = thread::class.java.getDeclaredField("ucp")
                    field.isAccessible = true
                    val next = field[thread]
                    val method = next::class.java.getMethod("getURLs")
                    value = method(next) as Array<URL>
                    println("AsyncDataLoader | URL extracting succeed with AppClassLoader Reflection")
                } catch (exx: Exception) {
                    println("AsyncDataLoader | Failed to extract methods. Ignoring, and skipping.")
                }
            }
            value
        } catch (e: Exception) {
            e.printStackTrace()
            arrayOf<URL>()
        }
        println("AsyncDataLoader | ${url.size} classpath detected! Extracting initializer...")
        var counter = 0
        for (x in url) {
            try {
                val ucl = URLClassLoader(arrayOf(x), Thread.currentThread().contextClassLoader)
                val file = File(x.toURI())
                val jf = JarFile(file)
                val e = jf.entries()
                if (jf.getJarEntry("loaderDeclaration.properties") == null) {
                    println("AsyncDataLoader | Skipping ${file.name} for no loaderDeclaration.properties (${++counter}/${url.size})")
                    continue
                }
                val property = Properties()
                property.load(jf.getInputStream(jf.getEntry("loaderDeclaration.properties")))
                println("AsyncDataLoader | Scanning ${file.name} as ${property["loaderName"]} v${property["version"]}, from ${property["author"]} (${++counter}/${url.size})")
                while (e.hasMoreElements()) {
                    val je = e.nextElement()
                    if (je.isDirectory || (!je.name.endsWith(".class") && !je.name.endsWith(".kt"))) {
                        continue
                    }
                    // -6 because of .class
                    var className: String = je.name.let {
                        if (it.endsWith(".kt"))
                            return@let it.substring(0, it.length - 3)
                        return@let it.substring(0, it.length - 6)
                    }
                    className = className.replace('/', '.')
                    try {
                        val c: Class<*> = Class.forName(className)
                        if (AbstractDataLoaderInitializer::class.java.isAssignableFrom(c)) {
                            if (c.modifiers.and(Modifier.ABSTRACT) != 0) {
                                println("AsyncDataLoader | Detected initializer from abstract class ${c.simpleName}, skipping...")
                                continue
                            }
                            println("AsyncDataLoader | Detected initializer from ${c.simpleName}, initializing...")
                            try {
                                (c.newInstance() as AbstractDataLoaderInitializer).load()
                            } catch (e: Exception) {
                                println("AsyncDataLoader | Failed to invoke initializer from ${c.simpleName} : ${e.javaClass.simpleName}")
//                                e.printStackTrace()
                            }
                        }
                    } catch (_: Throwable) {

                    }
                }
            } catch (e: Throwable) {
                println("AsyncDataLoader | Failed to extract data from ${File(x.toURI()).name} : ${e.javaClass.simpleName}")
//                e.printStackTrace()
            }
        }
    }
}
