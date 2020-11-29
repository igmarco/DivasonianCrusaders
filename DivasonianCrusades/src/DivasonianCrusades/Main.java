package DivasonianCrusades;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		// Ejecuta el este, Pablo
		
		Scanner sc = new Scanner(System.in);
		
		int número;
		
		do {
			
			System.out.println("Introduzca un número del 1 al 100.");
			
			número = sc.nextInt();
			
		}while(número > 100 || número < 0);
		
		if(número <= 20) {
			
			System.out.println("El gay tiene el autoestima baja. Más te valdría comer más champillones que te suban la moral y dejes de ser tan tremendo maricón. Los lunes no son tan malos como para que llegues tarde a clase tarde porque te pasas la primera parte de la mañana en la cama lloriqueando, pedazo de nenaza.");
			
		}
		else if(número < 80) {
			
			System.out.println("Ah bueno disculpe no sabía que usted era una nenaza normi con peinado de maricón. Pensé que podría tratarle con un cierto respeto porque tendría algo especial que le diferenciase de la basura, pero ya veo que todo lo que puedo encontrar es un montón de mierda ponzoñosa. Gracias entonces bobalicón chupa escrotos.");
			
		}
		else {
			
			System.out.println("Pero mira a quién tenemos aquí. Más de 80, eh gay? Seguro que te crees súper poderoso por tener una pija de más de 8 cm y por depilarte las cejas con cera. Pues que sepas que dormir con tu osito de peluche y rascarte los pelos del culo te delata como pedazo de verga infecta culo roto.");
			
		}
	}

}
