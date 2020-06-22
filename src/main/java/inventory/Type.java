package inventory;

public enum Type {
	
	EPICERIE("Épicerie"), PRODUITSMENAGES("Produits ménagés"), BIENSESSENTIELS("Biens essentiels");
	
	private final String displayValue;
	
	private Type(String displayValue) {
		this.displayValue=displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}
}
