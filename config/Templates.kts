// Templates module compilation script
import kotlin.modules.*
import java.util.ArrayList
import java.io.File
import java.io.FileFilter

private val FILTER_RECURSIVE = object: FileFilter {
	public override fun accept(pathname: File): Boolean = pathname.isDirectory() || pathname.getPath().endsWith(".kt")
}

private val FILTER_FILES_ONLY = object: FileFilter {
	public override fun accept(pathname: File): Boolean = pathname.isFile() && pathname.getPath().endsWith(".kt")
}

fun project() {
	module("Templates") {
		// Sources
		for ( source in listFiles("src/templates", FILTER_RECURSIVE) ) sources += source

		/*
		for ( source in listFiles("src/templates/org/lwjgl/generator", FILTER_RECURSIVE) ) sources += source
		//for ( source in listFiles("src/templates/org/lwjgl/openal", FILTER_RECURSIVE) ) sources += source
		//for ( source in listFiles("src/templates/org/lwjgl/opencl", FILTER_RECURSIVE) ) sources += source
		for ( source in listFiles("src/templates/org/lwjgl/opengl", FILTER_FILES_ONLY) ) sources += source
		for ( source in listFiles("src/templates/org/lwjgl/system/glfw", FILTER_RECURSIVE) ) sources += source
		*/

		// Boot classpath - this is needed if -noJdk is used.
		//val JAVA_HOME = System.getProperty("java.home")!!.replace('\\', '/')
		//val jdkJars = listFiles("$JAVA_HOME/lib", ".jar")
		//for ( jar in jdkJars )
			//classpath += jar

		// Compilation classpath
		classpath += "libs/kotlinc/lib/kotlin-runtime.jar"
		classpath += "src/templates"

		// External annotations
		annotationsPath += "libs/kotlinc/lib/kotlin-jdk-annotations.jar"
		annotationsPath += "config/kotlin"
	}
}

private fun listFiles(path: String, filter: FileFilter): List<String> {
	val root = File(path)
	if ( !root.isDirectory() )
		throw IllegalArgumentException("Root path is not a directory: $path")

	val files = ArrayList<String>()

	listFiles(File(path), filter, files)

	return files
}

private fun listFiles(dir: File, filter: FileFilter, output: MutableList<String>) {
	val files = dir.listFiles(filter)
	if ( files == null )
		return

	for ( file in files ) {
		if ( file.isDirectory() )
			listFiles(file, filter, output)
		else
			output.add(file.getAbsolutePath())
	}
}