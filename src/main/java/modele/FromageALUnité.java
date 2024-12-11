package modele;

public class FromageALUnité extends Fromage {

	public FromageALUnité(String désignation) {
		super(désignation);
	}

	@Override
	public String toString() {
		return super.toString() + ", vendu à l'unité";
	}

}
