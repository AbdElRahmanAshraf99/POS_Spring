package com.pos.Generator;

import org.reflections.Reflections;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Entity;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class RepositoriesGenerator
{
	public static void main(String[] args)
	{
		Reflections reflections = new Reflections("com.pos.Domain");
		Set<Class<?>> entitiesClasses = reflections.getTypesAnnotatedWith(Entity.class);
		String reposPath = getReposPath();
		for (Class<?> entitiesClass : entitiesClasses)
		{
			String fileName = reposPath + entitiesClass.getSimpleName() + "Repository.java";
			File f = new File(fileName);
			if (f.exists())
				continue;
			writeRepoJavaFile(entitiesClass, fileName);
		}

	}

	private static void writeRepoJavaFile(Class<?> entityClass, String fileName)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("package " + getRepositoryPackageName()).append(";".concat(System.lineSeparator()).concat(System.lineSeparator()));
		builder.append(getRepositoryImports(entityClass.getName())).append(System.lineSeparator()).append(System.lineSeparator());
		builder.append(getClassCode(entityClass));
		try (PrintWriter writer = new PrintWriter(new FileOutputStream(fileName)))
		{
			writer.write(builder.toString());
			writer.flush();
		}
		catch (FileNotFoundException e)
		{
			throw new RuntimeException(e);
		}
	}

	private static String getClassCode(Class<?> entityClass)
	{
		return "public interface " + entityClass.getSimpleName() + "Repository extends " + CrudRepository.class.getSimpleName() + "<"
				+ entityClass.getSimpleName() + ", " + Long.class.getSimpleName() + ">" + System.lineSeparator() + "{" + System.lineSeparator() + "}";
	}

	private static String getRepositoryImports(String classPath)
	{
		ArrayList<String> imports = new ArrayList<>();
		imports.add("import " + classPath + ";");
		imports.add("import " + CrudRepository.class.getName() + ";");
		return imports.stream().collect(Collectors.joining(System.lineSeparator()));
	}

	private static String getReposPath()
	{
		String workingDirectory = getWorkingDirectory();
		String mainFolder = "/src/main/java";
		String repositoryPackageName = getRepositoryPackageName();
		return workingDirectory + mainFolder + "/" + repositoryPackageName.replace('.', '/') + "/";
	}

	private static String getRepositoryPackageName()
	{
		return "com.pos.Repository";
	}

	public static String getWorkingDirectory()
	{
		return System.getProperty("user.dir");
	}
}
