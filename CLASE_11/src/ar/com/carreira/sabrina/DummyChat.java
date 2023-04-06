package ar.com.carreira.sabrina;

import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class DummyChat {

	public static void main(String[] args) {
		//Variables del archivo de texto para leer/escribir mensajes
		Path rutaArchivo = Paths.get("chat.txt");
		//Instanciar Scanner para obtener texto del usuario
		Scanner entrada = new Scanner(System.in);
		
		//Al empezar el proceso, pedir e imprimir nombre
		System.out.println("Hola, cual es tu nombre?");
		String nombre = entrada.nextLine();
		System.out.println("Hola " + nombre + " gracias por participar de este chat!");		
		
		String respuestaUsuario ="";
		
		do {
			//Mostar el menú de opciones
			System.out.println(nombre + ", que quieres hacer? ");
			System.out.println("Pulsa E para escribir un mensaje, L para leer todos los mensajes o X para salir del programa");
			
			respuestaUsuario = entrada.nextLine();
			
			//En base a la opcion elegida...
			switch(respuestaUsuario.toUpperCase()) {
				case "E":
					//Se eligio escribr un mensaje
					System.out.println("Se ha elegido escribir un texto, adelante !");
					respuestaUsuario = entrada.nextLine();
					//Se escribe en el archivo
					//Append?
					try {
						respuestaUsuario+="\n";
						Files.write(rutaArchivo, respuestaUsuario.getBytes(),StandardOpenOption.APPEND);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Tu mensaje se ha guardado en el archivo");
					break;
				case "L":
					//Se eligio leer un mensaje
					System.out.println("Se ha elegido leer los mensajes");
					List<String> mensajes = null;
					try {
						mensajes = Files.readAllLines(rutaArchivo);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//CUIDADO CON LOS ARCHIVOS VACIOS !
					if(mensajes.size() == 0) {
						System.out.println("Lamentablemente no hay mensajes para leer");
					}else {
						System.out.println("Estos son los mensajes que habia guardados");
						for(String linea : mensajes) {
							System.out.println(linea);
						}
					}
					break;
				default:
					if (!respuestaUsuario.toUpperCase().equals("X")){
						System.out.println("Como dice Google, lo siento, no te entendi");
					}
			}
			
			
		}while(!respuestaUsuario.equals("X") && !respuestaUsuario.equals("x"));
		
		System.out.println("Gracias por participar. Adios !");
		//No olvidarse de cerrar el Scanner !
		entrada.close();

	}

}
