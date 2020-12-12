public class SetupPokemon{
	public static final int cantidadPokemones = 9;
	public static final int cantidadPartidas = 10;
	public static final int cantidadCombates = 30;
	public static final int cantidadAtaques = 3;
	public static final int ps_max = 10;
	public static final int ataque = 10;
	public static final int defensa = 10;
	public static final int experiencia = 2;
	public static final int nivel = 1;
	public static final int velocidad = 10;
	public static final String [] nombres = {"Charmander","Squirtle","Bulbasaur","Ponyta","Pidgeotto","Pikachu","Charizard","Rattata","Arcanine",
											"Graveler","onix","Magmar","Torkoal"};
	public static final String [][] ataques = {{"Arañazo"," Ascuas"," Gruñido"},{"Látigo","Placaje","Burbuja"},{"Placaje","Gruñido","Drenadoras"},
												{"Placaje"," Ascuas"," Pisoton"},{"Ataque Rapido"," Picotazo"," Ala Acero"},
												{"Impactrueno","Látigo","Gruñido"},{"Llamarada"," Furia Dragon"," Pantalla Humo"},
												{"Placaje"," Latigo"," Mordisco"},{"Lanzallamas"," Ataque Rapido"," Mordisco"},
												{"Lanza Roca", "Terremoto", "Penkazo"},{"Caezazo","Fisura","Derribo"},
												{"Rayo Confuso","Lanzallamas","Llamarada"},{"Golpe Cuerpo","Amnesia","Onda ígnea"}};
	public static final String [] habilidades = {"Impactrueno","latigo","Gruñido"};
	public static final Double [][] estadisticaAtaque ={{0.85,0.0},{0.7,0.0,},{0.5,0.0}};
	public static final String [] lideres = {"Nico","Dante","Rorro","Antonio","Erwin"};
	public static final String [][] pokimones = {{"Graveler","Onix"},{"Magmar","Torkoal"},{"Sceptail","Venusaur"},{"Dragonite","Garchom"},{"Marowak","Gengar"}};
	public static final String [][] ataqs = {{"Lanza Roca", "Terremoto", "Penkazo"},{"Caezazo","Fisura","Derribo"},
											{"Rayo Confuso","Lanzallamas","Llamarada"},{"Golpe Cuerpo","Amnesia","Onda ígnea"},
											{"Hoja aguda","Absorver","Portazo"},{"Latigo sepa","Planta feroz","Drenadoras"},
											{"Cola Dragon","Cometa draco","Enfado"},{"Cola dragon","Llamarada","Terremoto"},
											{"Huesomerang","Golpe cabeza","Cabeza palo"},{"Mal de ojo","Maldicion","Languetazo"}
										};
	public static final String [] derrotados ={"no"};

	//public static final String [][] valores = {{10,0,0,5,0,2,0,0,2},{5,0,2,...},...} ?;
}