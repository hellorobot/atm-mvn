package top.robotman.atm.handler;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import top.robotman.atm.annotation.Autowired;
import top.robotman.atm.annotation.Component;
import top.robotman.atm.annotation.MyAnnotation;


public class ControllerHandlerMapper {
	private static final Map<String, Object> conMap = new HashMap<String, Object>();
	private static final List<String> classFileNames = new ArrayList<String>();
	private static final List<Class> classInstances = new ArrayList<Class>();

	public static void init(String rootPath, String packagePath)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		loadConstrollerClassesAndAutowired(rootPath, packagePath);
	}

	public static void loadConstrollerClassesAndAutowired(String rootPath, String packagePath) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String classPath = "/WEB-INF/classes/";

		String filePath = rootPath + classPath + packagePath;
		
		File file = new File(filePath);
		// 对文件进行遍历，将class文件的包名+类名存放进集合。
		recursiveFile(file);
		//将集合中的类全部初始化，然后就可以遍历初始化过的类来寻找标签。
		instanceClass();
		//遍历初始化过的类来获得标签所在的类，然后创建这个类的对象,把key和实例对象都放进map
		careateObjectByTag();
		//现在最后一步，对有set的函数进行set值。
		//如何知道哪个函数需要set呢？我们这里遍历类里所有的方法，通过标签来确认，
		initObject();
	}
	
	public static void initObject() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Set<Map.Entry<String, Object>> entryset = conMap.entrySet();
		for(Map.Entry<String, Object> entry : entryset) {
			entry.getValue();
			//System.out.println(entry.getValue());
			entry.getKey();
			//System.out.println(entry.getKey());
			Method[] methods =  entry.getValue().getClass().getMethods();
			for(Method method : methods) {
				if(method.getAnnotation(Autowired.class) == null) {
					continue;
				}else {
					String ky = method.getAnnotation(Autowired.class).value();
					method.invoke(entry.getValue(), conMap.get(ky));
				}
			}
		}
	}
	
	
	public static void careateObjectByTag() throws InstantiationException, IllegalAccessException {
		for(Class clazz : classInstances) {
			if(clazz.getAnnotation(Component.class) != null) {
				Component compoment = (Component)clazz.getAnnotation(Component.class);
				String ky = compoment.value();
				conMap.put(ky, clazz.newInstance());
				//System.out.println("xx........"+clazz.getName());
			}
			if(clazz.getAnnotation(MyAnnotation.class) != null) {
				clazz.newInstance();
				MyAnnotation myAnnotation = (MyAnnotation)clazz.getAnnotation(MyAnnotation.class);
				String ky = myAnnotation.value();
				conMap.put(ky, clazz.newInstance());
				//System.out.println(".........."+clazz.getName());
			}			
		}
	}
	
	public static void instanceClass() throws ClassNotFoundException {
		for(String classFileName : classFileNames) {
			Class clazz = Class.forName(classFileName);
			classInstances.add(clazz);
		}	
	}

	public static void recursiveFile(File file) {
		if (file.list().length == 0) {
			return;
		}
		// TODO跳出条件,当文件夹下没有文件就结束当次递归分支

		File[] files = file.listFiles();
		for (File tmpFile : files) {
			// 遍历每一个文件
			if (tmpFile.isDirectory()) {
				// 如果是文件夹，就深入遍历
				recursiveFile(tmpFile);
			}

			if (tmpFile.getName().endsWith(".class")) {
				// 如果遍历到class结尾的文件，就处理它
				// 然而我们需要class文件的包名才能进行forname初始化类，怎么获取呢
				String classFilePath = tmpFile.getPath();
				// 我们获得文件的完整路径，然后对路径进行截取，
				String classPackageName = classFilePath.substring(classFilePath.indexOf("classes") + 8,
						classFilePath.length() - 6);
				//替换掉斜杠
				classPackageName = classPackageName.replace(File.separator, ".");
				classFileNames.add(classPackageName);
			}
		}
	}
	
	public static Object getcontroller(String key) {
		return conMap.get(key);
	}

	public static void main(String[] args) throws InstantiationException, ClassNotFoundException, IllegalAccessException {
		//recursiveFile(new File("E:\\project\\atm-web-x1\\webapp\\WEB-INF\\classes\\"));
		//loadConstrollerClassesAndAutowired("E:\\project\\atm-web-x1\\webapp", "/ ");

	}
}
