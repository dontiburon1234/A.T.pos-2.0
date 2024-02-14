package util;

public class FileLog {

	/*public static Logger testLoger(String nombreArchivoLog){

		final Logger LOG = Logger.getAnonymousLogger();
		FileHandler fh;
		try {
			String sistemaOperativo = System.getProperty("os.name").toUpperCase();
			if (sistemaOperativo.indexOf("WINDOWS") != -1) {
				fh = new FileHandler("C:/tmp/logsCGC/LogATpos_"+nombreArchivoLog+"_%u.log"); 
			} else {
				fh = new FileHandler("/var/logsCGC/LogATpos_"+nombreArchivoLog+"_%u.log"); 
			}
			SimpleFormatter formatter = new SimpleFormatter();  
			fh.setFormatter(formatter); 
			LOG.addHandler(fh);
		} catch (SecurityException e) {
			System.out.println("FileLog testLoger SecurityException "+e);
		} catch (IOException e1) {
			System.out.println("FileLog testLoger IOException "+e1);
		}
		return LOG;		
	}*/
}
