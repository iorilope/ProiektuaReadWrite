/*
 * 2024(e)ko urt. 10(a)
 * Ioritz Lopetegi
 */
package ProiektuaSerial;

import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Class ProeiktuaMain.
 *
 * 
 */
public class ProeiktuaMain {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		 String erabiltzaileizenaString = System.getenv("USERNAME");

		ArrayList<Autoak> Autozerrenda = new ArrayList<Autoak>();

		String direktorioaString="C:\\Users\\"+erabiltzaileizenaString+"\\git\\ProiektuaReadWrite\\ProiektuaSerial\\";
		File direktorioaFile = new File(direktorioaString);

		//Auto batzuk sortu
		Autozerrenda.add(new Autoak("Mercedes", "Benz", 5, "Gasolina", 4, 10.5));
		Autozerrenda.add(new Autoak("BMW", "Serie 1", 4, "Diesel", 2, 8.0));
		Autozerrenda.add(new Autoak("BMW", "Serie 5", 4, "Diesel", 2, 6.0));
		Autozerrenda.add(new Autoak("Toyota", "Corolla", 7, "Hibrido", 5, 15.2));

		System.out.println("==================================================================");
		System.out.println("    Sartu zure aukera");
		System.out.println("    	1-Autoa gehitu");
		System.out.println("    	2-Autoak Kendu");
		System.out.println("    	3-Autoak ikusi");
		System.out.println("	4-Auto kop ikusi");
		System.out.println("	5-Autoak fitxategitik eskuratu");
		System.out.println("==================================================================");

		int aukera;
		do {
			try {
				aukera = sc.nextInt();
				if (aukera < 1 || aukera > 4) {
					System.out.println("Autatutako zenbakiak ez du balio, sartu berriro (1-4)");
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println("Sartu duzun balioa ez da zenbaki bat. Mesedez, zenbaki bat sartu.");
				sc.nextLine();
				aukera = 0; 
			}
		} while (aukera < 1 || aukera > 6);

		if (aukera == 1) {

			System.out.println("Autoa gehitu aukeratu duzu");
			System.out.println("Zenbat auto sartu nahi dituzu");
			int kopurua=0;
			try {
				kopurua = sc.nextInt();
			} catch (java.util.InputMismatchException e) {
				System.out.println("Sartu duzun balioa ez da zenbaki bat. Programa itxiko da.");
				System.exit(0);
			}

			for (int i = 0; i < kopurua; i++) 
			{
				Autoak autoa = new Autoak();

				System.out.println("Sartu autoaren Marka");
				sc.nextLine();
				String markaString = sc.nextLine();
				sc.nextLine();
				autoa.SetMarka(markaString);

				System.out.println("Sartu autoaren Modeloa");
				String modeloaString = sc.nextLine();
				autoa.SetModeloa(modeloaString);

				System.out.println("Sartu Pertsona Kopurua");
				int pertsonakopurua = sc.nextInt();
				autoa.SetPertsonaKop(pertsonakopurua);

				System.out.println("Sartu Erregai Mota");
				String erregaiString = sc.nextLine();
				sc.nextLine();
				autoa.SetErregaiMota(erregaiString);

				System.out.println("Sartu Ate Kopurua");
				int atekopurua = sc.nextInt();
				autoa.SetAte_Kop(atekopurua);

				System.out.println("Sartu Kontsumoa L/100km");
				double kontsumoaa= 0;

				try {
					kontsumoaa = sc.nextDouble();
				} catch (java.util.InputMismatchException e) {
					System.out.println("Sartu duzun balioa ez da zenbaki bat. Programa itxiko da.");
					System.exit(0);
				}

				autoa.SetKontsumoa(kontsumoaa);
				Autozerrenda.add(autoa);

				System.out.println("Sartu non gorde nai duzun, gogoratu .dat fitxategia izan behar duela");
				String aukeratufitxategiaString = sc.next();

				String fitxategiaString = aukeratufitxategiaString;
				File fitxategia=new File(direktorioaString+fitxategiaString);
				if (fitxategiaString.endsWith(".dat")) {
					
				
				try (final FileOutputStream fout = new FileOutputStream(fitxategia);
						final ObjectOutputStream out = new ObjectOutputStream(fout)) {
					out.writeObject(Autozerrenda);
					out.flush();
					System.out.println("Datuak ondo idatzi dira");
				} catch (IOException e) {
					e.printStackTrace();
				}
				for (Autoak auto : Autozerrenda) {
					System.out.println(auto.toString());
				}
				sc.close();
			} else {

				System.out.println("Fitxategiak .dat izan behar du");
			}
			
			}
		}

		else if (aukera == 2) {

			System.out.println("Autoa kendu aukeratu duzu");
			int index = 1;
			for (Autoak auto : Autozerrenda) {
				System.out.println(index + ": " + auto);
				index++;
			}

			System.out.print("Sartu kendu nahi duzun autoaren zenbakia : ");
			int aukera2 = 0;

			try {
				aukera2 = sc.nextInt();
			} catch (java.util.InputMismatchException e) {
				System.out.println("Errorea: Sartu duzun balioa ez da zenbaki bat.");
				sc.nextLine();
			}

			if (aukera2 > 0 && aukera2 <= Autozerrenda.size()) {
				Autozerrenda.remove(aukera2 - 1);
				System.out.println("Auto ondo kendu da.");
				int index2 = 1;
				for (Autoak auto : Autozerrenda) {
					System.out.println(index2 + ": " + auto);
					index2++;
				}
			} else {
				System.out.println("Gaizki aukeratu duzu. Ez da autorik kendu.");
			}
		}
		else if (aukera == 3) {

			System.out.println("Autoak ikusi  aukeratu duzu");

			List<String> markabakarrakList = Autoak.LortumarkabakarrakList(Autozerrenda);

			System.out.println("Erabilgarri dauden markak:");

			for (String marca : markabakarrakList) {
				System.out.println(marca);
			}

			Scanner scanner = new Scanner(System.in);
			System.out.print("Zein auto ikusi nahi duzu: ");
			String aukeratutakomarka = "";

			try {
				aukeratutakomarka = scanner.nextLine();
				Autoak.AutoakMarkaBidez(Autozerrenda, aukeratutakomarka);
			} catch (java.util.InputMismatchException e) {
				System.out.println("Errorea: Sartu duzun balioa ez da onargarria.");
			} catch (Exception e) {
				System.out.println("Errorea: " + e.getMessage());
			} finally {
				sc.close();
				scanner.close();
			}
		}

		else if (aukera == 4) {
			System.out.println("Autoak kop   aukeratu duzu");

			int cantidadTotalCoches = Autoak.AutoakZenbatu(Autozerrenda);
			System.out.println("Auto kopurua: " + cantidadTotalCoches);
		}

		else if (aukera == 5) {
			System.out.println("Datuak berreskuratu aukeratu duzu");
			System.out.println("Aukeratu zein fitxategi berreskuratu nahi duzun");
			 File[] fitxategiaFiles = direktorioaFile.listFiles();
			 File[] archivosDat = Arrays.stream(fitxategiaFiles)
		                .filter(archivo -> archivo.isFile() && archivo.getName().endsWith(".dat"))
		                .toArray(File[]::new);

		       
		        for (File archivo : archivosDat) {
		            System.out.println(archivo.getName());
		        }
			Scanner sarrera = new Scanner(System.in);

			String fitxategiizena = sarrera.nextLine();
			File fitxategiadat = new File(fitxategiizena);
			
			

			if (!fitxategiadat.exists()) {
				System.out.println("Fitxategia : " + fitxategiizena + " Ez da existitzen.");
				return;
			}

			if (!fitxategiadat.getName().endsWith(".dat")) {
				System.out.println("Fitxategia : " + fitxategiizena + " ez da .dat fitxategia");
				return;
			}
			try {

				FileInputStream fileInputStream = new FileInputStream(fitxategiizena);

				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

				Object objetoRecuperado = objectInputStream.readObject();

				objectInputStream.close();
				fileInputStream.close();

				System.out.println("Datuak berreskuratu dira: " + objetoRecuperado);

			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Autatutako zenbakiak ez du balio");
		}

	}

}