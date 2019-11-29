import java.net.*;
import java.io.*;
import java.util.*;

public class Exemple3UrlConn {

	@SuppressWarnings("rawtypes")
	public static void main (String[] args) {

		try {
			String cadena;
			URL url = new URL("http://insbaixcamp.org/index.php/2013-10-29-12-24-12/informacio-general-4");
			URLConnection connexio = url.openConnection();

			System.out.println("===============================================================");
			System.out.println("ADRE�A, DARA I CONTINGUT");
			System.out.println("Adre�a [getURL]: " + connexio.getURL());

			Date data = new Date(connexio.getLastModified());
			System.out.println("Data �ltima modificaci� [getLastModified()]: " + data);
			System.out.println("Tipus de Contingut [getContentType()]" + connexio.getContentType());

			System.out.println("===============================================================");
			System.out.println("TOTS ELS CAMPS DE CAP�ALERA AMB getHeaderFields(): ");

			//Fem servir una estructura Map per a recuperar cap�aleres
			Map campsCap�alera = connexio.getHeaderFields();
			Iterator it = campsCap�alera.entrySet().iterator();

			while (it.hasNext()) {
				Map.Entry map = (Map.Entry) it.next();
				System.out.println(map.getKey() + ":" + map.getValue());
			}

			System.out.println("===============================================================");
			System.out.println("Camps 1 i 4 de Cap�alera");
			System.out.println("getHeaderField(1)=> " + connexio.getHeaderField(1));
			System.out.println("getHeaderField(4)=> " + connexio.getHeaderField(4));
			System.out.println("===============================================================");

			System.out.println("Contingut de [url.getFile()]: " + url.getFile());
			BufferedReader pagina = new BufferedReader(new InputStreamReader(url.openStream()));

			while ((cadena = pagina.readLine()) != null) {

				if (cadena.contains("script")){
					System.out.println(cadena);
				}
			}
		}
		catch (MalformedURLException e) { e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
	}


}