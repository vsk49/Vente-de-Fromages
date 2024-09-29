package modele;

public class FromageALUnitéPlusieursChoix extends Fromage {

	public FromageALUnitéPlusieursChoix(String désignation) {
		super(désignation);
	}

	@Override
	public String toString() {
		return super.toString() + ", vendu à l'unité avec plusieurs choix";
	}

}
