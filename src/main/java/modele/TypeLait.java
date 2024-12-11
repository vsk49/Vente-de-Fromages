package modele;

public enum TypeLait {
	VACHE("Vache"), CHEVRE("Chèvre"), BREBIS("Brebis");

	private final String typeDeLait;

	TypeLait(String typeDeLait) {
		this.typeDeLait = typeDeLait;
	}

	public String getTypeDeLait() {
		return this.typeDeLait;
	}

}
