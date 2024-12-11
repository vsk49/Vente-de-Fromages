package modele;

public class FromagePourXPersonnes extends Fromage {

	public FromagePourXPersonnes(String désignation) {
		super(désignation);
	}

	@Override
	public String toString() {
		return super.toString() + ", vendu par nombre de personnes";
	}

}
