package fr.renater.idegest.tu;

import java.io.IOException;
import java.text.Normalizer;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class GestAjoutUsersTest extends TestCase {
	/** l'OUT */
	private GestAjoutUsers gau;                           // 1

	@Before
	protected void setUp() throws Exception {
		try {
			this.gau = new GestAjoutUsers("testusers.xml");   // 2
		} catch (IOException e) {
			fail("Création de l'OUT impossible !");
		}
	}

	@Test
	public void test2PremiersCarsGenUid() {
		String uid = this.gau.genUid("Bob", "Martin");                      // 3 
		assertTrue("Les 2 premiers caractères sont valides", uid.startsWith("bm"));  // 4
	}
	@Test
	public void test2PremiersCarsGenUidBis() {
		String uid = this.gau.genUid("Bob", "Martin");                 // 1
		String premscar = uid.substring(0, 2);                         // 2
		assertEquals("Les 2 premiers caractères sont valides", "bm", premscar); // 3
	}

	@Test
	public void testMinuscule(){
		String uid = this.gau.genUid("Bob", "Martin");  
		String enminuscule = uid.toLowerCase();
		assertEquals( "les caractère sont en minuscule","bmartin",enminuscule);
	}

	@Test
	public void testCaractere(){
		String uid = this.gau.genUid("Bob", "Martin");  
		int caractere = uid.length();
		if (caractere<5 && caractere>9){
			assertEquals( "Le nombre de caractère est compris entre 5 et 9","bmartin",uid);
		}
		else{
			assertEquals ("le nombre de caratère est inferieur a 5 ou superieur a 9","bmartin",uid);
		}
	}

	@Test
	public void testNormaliser(){
		String uid = this.gau.genUid("Bob", "Martin");  
		String normaliser = Normalizer.normalize(uid, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+","");
		assertEquals("Il ny a pas de caracteres indesirables","bmartin",normaliser);
	}

	@Test
	public void testAdduser(){
		User user;
		User user2;

		user = gau.addUser("Bob","Martin");
		System.out.println(""+user);
		user2=gau.addUser("Bob","Martin");
		System.out.println(""+user2);
		assertEquals("L'uid obtenu n'existe pas","bmartin",user.getLogin());
		assertEquals("L'uid obtenu existe déjà","bmartin1",user2.getLogin());
		

	}


}