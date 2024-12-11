package modele;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestFiltreTypeDeFromage {

	private static Fromages mesArticles;

	@BeforeClass
	public static void setUp() {
		mesArticles = GenerationFromages.générationBaseFromages();
	}

	@Test
	public void testFromagesBrebis() {
		List<Fromage> fromagesBrebis = mesArticles
		        .fromagesAuLaitDe(TypeLait.BREBIS);
		assertEquals(16, fromagesBrebis.size());
		if (!this.verify(fromagesBrebis, TypeLait.BREBIS)) {
			fail("un fromage du mauvais type trouvé dans le filtre !");
		}
	}

	@Test
	public void testFromagesChèvre() {
		List<Fromage> fromagesChèvre = mesArticles
		        .fromagesAuLaitDe(TypeLait.CHEVRE);
		assertEquals(22, fromagesChèvre.size());
		if (!this.verify(fromagesChèvre, TypeLait.CHEVRE)) {
			fail("un fromage du mauvais type trouvé dans le filtre !");
		}
	}

	@Test
	public void testFromagesVache() {
		List<Fromage> fromagesVache = mesArticles
		        .fromagesAuLaitDe(TypeLait.VACHE);
		assertEquals(59, fromagesVache.size());
		if (!this.verify(fromagesVache, TypeLait.VACHE)) {
			fail("un fromage du mauvais type trouvé dans le filtre !");
		}
	}

	private boolean verify(List<Fromage> fromages, TypeLait lait) {
		for (Fromage f : fromages) {
			if (f.getTypeFromage() != lait) {
				return false;
			}
		}
		return true;
	}

}
