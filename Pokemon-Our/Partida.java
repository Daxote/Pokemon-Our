import java.io.*;
import java.util.Random;
public class Partida{
	private BufferedReader buffer;
	private Personaje jugador;
	private Combate [] combates;
	private int combateActual,caso;
	private int W;
	private int Posicion;
	private Pokedex pokedexGral;
	private Mapa mapa;
	private String lugar;
	private Mochila mochila;
	private Pokemon jefe;


	Partida(Pokedex pokedexGral){
		buffer = new BufferedReader(new InputStreamReader(System.in));
		combates = new Combate[SetupPokemon.cantidadCombates];
		combateActual = 0;
		this.pokedexGral = pokedexGral;
		this.mapa=new Mapa();
		crearUsuario(pokedexGral);
		this.mochila = new Mochila();
		System.out.println("\n\n********************************************************\n\n");
		System.out.println("Comienza el juego...");
		System.out.println("\n\n********************************************************\n\n");
		menu();
	}
	
	private void crearUsuario(Pokedex pokedexGral){
		String nombre;
		String usuario;
		try{
			System.out.println("\n\n********************************************************\n");
			System.out.println("    POKEMON FUNDAMENTOS DE LENGUAJES DE PROGRAMACIÓN");
			System.out.println("\n********************************************************\n\n");
			System.out.println("            Ingrese su nombre: \n            ");
			nombre = buffer.readLine();
			System.out.println("            Ingrese un nombre de usuario: \n            ");
			usuario = buffer.readLine();

			this.jugador = new Personaje(1,nombre,usuario,pokedexGral);	
		}catch(IOException e){
			System.out.println("Error de lectura desde el teclado...");
		}
	}
	public void menu(){
		int op=0;
		
		try{
			do{
			
			System.out.println("\n\n********************************************************\n\n");
			System.out.println("Selecciona la acción que quieres realizar: ");
			System.out.println("1.- Explorar. ");
			System.out.println("2.- Ver Pokedex. ");
			System.out.println("3.- Mapa. ");
			System.out.println("4.- Mochila. ");
			System.out.println("5.- Gym");
			System.out.println("6.- Terminar Juego. ");
			try{
				op = Integer.parseInt(buffer.readLine());
			}catch(NumberFormatException e){
					System.out.println("\n         Error de lectura desde el teclado...          \n");
				op=0;
			}
			System.out.println("\n\n********************************************************\n\n");
			switch(op){
				case 1: System.out.println("\n\n********************************************************\n\n");
						Explorar();
						System.out.println("\n\n********************************************************\n\n");
						break;
				case 2: System.out.println("\n\n********************************************************\n\n");
						mostrarPokedex();
						buffer.readLine();
						System.out.println("\n\n********************************************************\n\n");
						break;
				case 3:	System.out.println("\n\n********************************************************\n\n");
						this.Posicion=mapa.getPosicion();
						opcionesViajar();
						System.out.println("\n\n********************************************************\n\n");
						break;
				case 4: mochila();
						break;
				case 5: entrar_gimnacio();
						System.out.println("\n\n********************************************************\n\n");
						break;
				case 6: System.out.println("\n\n********************************************************\n\n");
						System.out.println("El juego ha terminado,  gracias por jugar PokemonFLP!!!");
						System.out.println("\n\n********************************************************\n\n\n\n\n\n\n\n\n\n");
						break;
			}

			}while(op!=6);
			//System.out.println("1.- Combate. ");

		}catch(IOException e){
			System.out.println("Error de lectura desde el teclado...");
		}

	}
	//Arreglar problema que aparece despues de una batalla
	private void Explorar(){
		Random num = new Random();
		int b=num.nextInt(3);
		if (b==0){
			System.out.println("Te haz encontrado con un Pokémon salvaje, preparate para el combate");
			crearCombate();
		}
		if (b==1){
			System.out.println("Enhorabuena, haz encontrado una baya");
			mochila.setBayas(mochila.getBayas()+1);
		}
		else{
			System.out.println("No se ha encontrado nada por aqui, ¿que quieres hacer ahora?: ");
		}
	}
	private void mochila(){
		int op=0;
		
		try{
			do{
				System.out.println("Selecciona la acción que quieres realizar: \n");
				System.out.println("1.- Objetos. ");
				System.out.println("2.- Medallas. ");
				System.out.println("3.- Salir ");
				try{
					op = Integer.parseInt(buffer.readLine());
				}catch(NumberFormatException e){
					System.out.println("Error de lectura desde el teclado...");
					op=0;
					break;
				}
				switch(op){
					case 1: System.out.println("Tiene "+mochila.getBayas()+" Bayas y "+mochila.getPosiones()+" Pociones \n");
							break;
					case 2: System.out.println("Tiene "+mochila.getMedallas()+" Medallas\n");
							break;
					case 3: System.out.println("Ha salido de la mochila\n");
				}
			}while(op != 3);
		}catch(IOException e){
			System.out.println("Error de lectura desde el teclado...");
		}
	}
	private void crearCombate(){
		this.W=0;
		int n=SetupPokemon.cantidadPokemones;
		Pokemon aux;
		System.out.println("\n\n********************************************************\n\n");
		System.out.println("Pokedex Personal");
		System.out.println("======= ========\n");
		for(int i=0;i<n;i++){
			aux = jugador.getPokedex().getPokemon(i);
			if(aux.getCapturado())
				System.out.println((i+1)+".- "+aux.getNombre());
		}
		
		int op=-1;
		boolean flag=false;
		try{
			do{
				if(flag) System.out.println("Ingrese una opción válida...\n");
				System.out.println("Elije tu pokemon para la batalla....\n");
				try{
					op = Integer.parseInt(buffer.readLine());
				}catch(NumberFormatException e){
					System.out.println("\nPokedex Personal");
					System.out.println("======= ========");
					for(int i=0;i<n;i++){
						aux = jugador.getPokedex().getPokemon(i);
						if(aux.getCapturado()){
							System.out.println("\n"+(i+1)+".- "+aux.getNombre());
						}
					}
				}
				flag=true;
			}while((op<0)||(op>n));
		}catch(IOException e){
			System.out.println("Error de lectura desde el teclado...\n");
		}
		System.out.println("\n\n********************************************************\n\n");
		if(jugador.getPokedex().getPokemon(op-1).getCapturado()){
			System.out.println("Su pokemon para el combate es "+ jugador.getPokedex().getPokemon(op-1).getNombre());
			System.out.println("\n\n********************************************************\n\n");
			combates[combateActual] = new Combate(jugador.getPokedex().getPokemon(op-1), pokedexGral);
			int rival = combates[combateActual].combatir();
			if (rival > -1){
				jugador.getPokedex().capturarPokemon(rival);
				System.out.println("     Haz capturado un nuevo pokemon!!!    \n");
				this.W=1;
			}
			combateActual+=1;
		}else{
			System.out.println("\n               Opcion Invalida                 \n");
		}
		
	}

	public void mostrarPokedex(){
		this.jugador.listarPokedexPersonal();
	}
	public void opcionesViajar(){
		caso=0;
		try{
			do{
				
			System.out.println("\n\n********************************************************\n\n");
			System.out.println("Selecciona la acción que quieres realizar: ");
			System.out.println("1.- Ciudad Actual. ");
			System.out.println("2.- Ciudades Cercanas. ");
			System.out.println("3.- Viajar ");
			System.out.println("4.- Salir del mapa. ");
			try{
				caso = Integer.parseInt(buffer.readLine());
			}catch(NumberFormatException e){
				System.out.println("\n   Error de lectura desde el teclado... \n");
		 	}	

			System.out.println("\n\n********************************************************\n\n");
			switch(caso){
				case 1: System.out.println("*********** CIUDAD ACTUAL ***********");
						System.out.println(""+this.mapa.getLugar(this.Posicion));
						System.out.println("\n\n********************************************************\n\n");
						
						break;
				case 2: System.out.println("*********** CIUDADES CERCANAS ***********");
						if (mapa.getViajar()[this.Posicion][1]=="no"){
							System.out.println("1.-"+mapa.getViajar()[this.Posicion][0]);
						}
						else{
							System.out.println("1.-"+mapa.getViajar()[this.Posicion][0]);
							System.out.println("2.-"+mapa.getViajar()[this.Posicion][1]);
						}
						System.out.println("\n\n********************************************************\n\n");
						break;
				case 3:	System.out.println("*********** VIAJAR A ***********");
						if (this.Posicion==0 || this.Posicion==4){
							System.out.println("1.-"+mapa.getViajar()[this.Posicion][0]);
							try{
								caso = Integer.parseInt(buffer.readLine());
								if(this.Posicion ==0 && caso == 1){
									this.mapa.setPosicion(Posicion+1);
									this.Posicion=mapa.getPosicion();
									
									encuentros();
									System.out.println(" *********** Llegamos a "+this.mapa.getLugar(this.Posicion)+" ***********\n");
									System.out.println("\n\n********************************************************\n\n");
									break;
								}
								else if(Posicion == 4 && caso==1){
									System.out.println("1.-"+mapa.getViajar()[this.Posicion][0]);
									this.mapa.setPosicion(Posicion-1);
									this.Posicion=mapa.getPosicion();
									encuentros();
									System.out.println(" *********** Llegamos a "+this.mapa.getLugar(this.Posicion)+" ***********\n");
									System.out.println("\n\n********************************************************\n\n");
									break;
								}
								
								else{
									System.out.println("\n\n********************************************************\n\n");
									caso=0;
									break;
								}
							}catch(NumberFormatException e){
								System.out.println("\n           Error de lectura desde el teclado...          \n");
								break;
							}
						}
						else if(this.Posicion>=1 && this.Posicion<=4){
							System.out.println("1.-"+mapa.getViajar()[this.Posicion][0]);
							System.out.println("2.-"+mapa.getViajar()[this.Posicion][1]);
							caso = Integer.parseInt(buffer.readLine());
							if(caso == 1){
								this.mapa.setPosicion(Posicion-1);
								this.Posicion=mapa.getPosicion();
								encuentros();
								System.out.println(" *********** Llegamos a "+this.mapa.getLugar(this.Posicion)+" ***********\n");
								System.out.println("\n\n********************************************************\n\n");
								break;
							}
							else if(caso == 2){
								this.mapa.setPosicion(Posicion+1);
								this.Posicion=mapa.getPosicion();
								encuentros();
								System.out.println(" *********** Llegamos a "+this.mapa.getLugar(this.Posicion)+" ***********");
								System.out.println("\n\n********************************************************\n\n");
								break;
							}
							else{
								System.out.println("\n\n********************************************************\n\n");
								break;
							}
						}

				case 4: System.out.println("Ha salido del mapa");
						System.out.println("\n\n*********** PRESIONE ENTER PARA CONTINUAR **********\n\n\n\n\n\n\n\n\n\n");
						break;
				}


				}while(caso!=4);
			}catch(IOException e){
				System.out.println("          Error de lectura desde el teclado...           ");
		}
	}
	public void encuentros(){
		for(int i=1;i<=3;i++){
			System.out.println("\n *********** Viajando a "+this.mapa.getLugar(this.Posicion)+" ***********\n");
			System.out.println("\n                Combate "+i+"/3              \n");
			if(i==1){
				System.out.println("\n          Falta Bastante para llegar        \n");
			}
			else if(i==2){
				System.out.println("\n               A medio camino               \n");
			}
			else{
				System.out.println("\n             Ya casi llegamos               \n");
			}
			crearCombate();
			if(this.W==0){
				i-=1;
			}
		
	}
	
	}
	private void entrar_gimnacio(){
		System.out.println(mapa.getGimnacio(this.Posicion)+"\n");
		int op = 0;
		try{
			do{
				
				System.out.println("1.- Retar al lider");
				System.out.println("2.- Salir");
				op = Integer.parseInt(buffer.readLine());

				switch(op){
					case 1: combateJefe();

					case 2: System.out.println("   Ha Salido del Gimnacio   \n");
				}
			}while(op!=2);
		}catch(IOException e){

		}
	}
	private void combateJefe(){
		this.W=0;
		int n=SetupPokemon.cantidadPokemones;
		Pokemon aux;
		System.out.println("\n\n          Enfrentado al lider de Gimnasio  "+SetupPokemon.lideres[Posicion]+"  \n\n");
		System.out.println("\n\n********************************************************\n\n");
		System.out.println("Pokedex Personal");
		System.out.println("======= ========\n");
		for(int i=0;i<n;i++){
			aux = jugador.getPokedex().getPokemon(i);
			if(aux.getCapturado())
				System.out.println((i+1)+".- "+aux.getNombre());
		}
		
		int op=-1;
		boolean flag=false;
		try{
			do{
				if(flag) System.out.println("Ingrese una opción válida...\n");
				System.out.println("Elije tu pokemon para la batalla....\n");
				try{
					op = Integer.parseInt(buffer.readLine());
				}catch(NumberFormatException e){
					System.out.println("\nPokedex Personal");
					System.out.println("======= ========");
					for(int i=0;i<n;i++){
						aux = jugador.getPokedex().getPokemon(i);
						if(aux.getCapturado()){
							System.out.println("\n"+(i+1)+".- "+aux.getNombre());
						}
					}
				}
				flag=true;
			}while((op<0)||(op>n));
		}catch(IOException e){
			System.out.println("Error de lectura desde el teclado...\n");
		}
		do{
			System.out.println("\n\n********************************************************\n\n");
			if(jugador.getPokedex().getPokemon(op-1).getCapturado()){
				System.out.println("Su pokemon para el combate es "+ jugador.getPokedex().getPokemon(op-1).getNombre());
				System.out.println("\n\n********************************************************\n\n");
				combates[combateActual] = new Combate(jugador.getPokedex().getPokemon(op-1), pokedexGral);
				jefe= new Pokemon(9+W, SetupPokemon.pokimones[Posicion][W], SetupPokemon.ataques[9+W]);
				W+=combates[combateActual].combatirJefe(jefe);				
				combateActual+=1;
				if(W<2){
					System.out.println("\n\n          Enfrentado al lide de Gimnasio "+SetupPokemon.lideres[Posicion]+"\n\n");
					System.out.println("\n\n********************************************************\n\n");
					System.out.println("Pokedex Personal");
					System.out.println("======= ========\n");
					for(int i=0;i<n;i++){
						aux = jugador.getPokedex().getPokemon(i);
						if(aux.getCapturado())
							System.out.println((i+1)+".- "+aux.getNombre());
					}
					
					op=-1;
					flag=false;
					try{
						do{
							if(flag) System.out.println("Ingrese una opción válida...\n");
							System.out.println("Elije tu pokemon para la batalla....\n");
							try{
								op = Integer.parseInt(buffer.readLine());
							}catch(NumberFormatException e){
								System.out.println("\nPokedex Personal");
								System.out.println("======= ========");
								for(int i=0;i<n;i++){
									aux = jugador.getPokedex().getPokemon(i);
									if(aux.getCapturado()){
										System.out.println("\n"+(i+1)+".- "+aux.getNombre());
									}
								}
							}
							flag=true;
						}while((op<0)||(op>n));
					}catch(IOException e){
						System.out.println("Error de lectura desde el teclado...\n");
					}
				}
				
			}else{
				System.out.println("\n               Opcion Invalida                 \n");
			}
		}while(W!=2);
		System.out.println("Felicidades ha Derrotado al Lider de Gimnasio  "+SetupPokemon.lideres[Posicion]+" \n");
		System.out.println("              Medalla del "+mapa.getGimnacio(Posicion)+" obtenida     \n");
		mochila.setMedallas(mochila.getMedallas()+1);
	}
}
