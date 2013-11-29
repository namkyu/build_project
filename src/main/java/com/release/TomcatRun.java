package com.release;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.naming.resources.VirtualDirContext;

/**
 * @FileName : TomcatRun.java
 * @Project : build_project
 * @작성자 : nklee
 * @프로그램설명 :
 */
public class TomcatRun {

	/**
	 * <pre>
	 * main
	 *
	 * <pre>
	 * @param args
	 * @throws ServletException
	 * @throws LifecycleException
	 */
	public static void main(String[] args) throws ServletException, LifecycleException {
		String webappDirLocation = "src/main/web/";
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(10000);

		StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());

		// declare an alternate location for your "WEB-INF/classes" dir:
		File additionWebInfClasses = new File("target/classes");
		VirtualDirContext resources = new VirtualDirContext();
		resources.setExtraResourcePaths("/WEB-INF/classes=" + additionWebInfClasses);
		ctx.setResources(resources);

		tomcat.start();
		tomcat.getServer().await();
	}
}
