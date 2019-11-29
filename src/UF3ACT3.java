import java.net.*;
import java.io.*;
import java.util.*;

public class UF3ACT3 {

	@SuppressWarnings("rawtypes")
	public static void main (String[] args) {

		// Demanem les dades al usuari
		Scanner teclado = new Scanner(System.in);
		System.out.println("url exemple: http://insbaixcamp.org/index.php/2013-10-29-12-24-12/informacio-general-4");
		
		System.out.println("Introdueix la url: ");
		String stringUrl = teclado.nextLine();
		
		System.out.println("Introdueix el nombre de camps que vols mostrar: ");
		int numCamps = teclado.nextInt();
		teclado.nextLine();
		
		System.out.println("Introdueix el patro de busqueda: ");
		String stringPatro = teclado.nextLine();
		try {
			String cadena;
			URL url = new URL(stringUrl);
			URLConnection connexio = url.openConnection();

			System.out.println("===============================================================");
			System.out.println("ADREÇA, DARA I CONTINGUT");
			System.out.println("Adreça [getURL]: " + connexio.getURL());

			Date data = new Date(connexio.getLastModified());
			System.out.println("Data última modificació [getLastModified()]: " + data);
			System.out.println("Tipus de Contingut [getContentType()]" + connexio.getContentType());

			System.out.println("===============================================================");
			System.out.println("TOTS ELS CAMPS DE CAPÇALERA AMB getHeaderFields(): ");

			//Fem servir una estructura Map per a recuperar capçaleres
			Map campsCapçalera = connexio.getHeaderFields();
			Iterator it = campsCapçalera.entrySet().iterator();

			int num = 1;
			while (it.hasNext()) {
				Map.Entry map = (Map.Entry) it.next();
				System.out.println(map.getKey() + ":" + map.getValue());
				num++;
				
			}

			System.out.println("===============================================================");
			System.out.println("Camps de Capçalera");
			// Mostrem les capçaleres
			for (int i = 1; i <= numCamps && i <= num; i++) {
				System.out.println("getHeaderField(" + i + ")=> " + connexio.getHeaderField(i));
			}
			System.out.println("===============================================================");

			System.out.println("Contingut de [url.getFile()]: " + url.getFile());
			BufferedReader pagina = new BufferedReader(new InputStreamReader(url.openStream()));

			while ((cadena = pagina.readLine()) != null) {

				// Mostrem nomes les linies que tinguin la paraula que li indiquem
				if (cadena.contains(stringPatro)){
					System.out.println(cadena);
				}
			}
		}
		catch (MalformedURLException e) { e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
	}
}