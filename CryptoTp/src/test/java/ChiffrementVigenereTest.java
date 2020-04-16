import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ChiffrementVigenereTest {

    @Test
    public void testChiffrementCesar() {
        ChiffrementCesar cipher = new ChiffrementCesar();
        String cipheredMessage = cipher.crypter("ceci est un test de chiffrement plus consequent pour le test", 3);
        assertEquals("fhfl hvw xq whvw gh fkliiuhphqw soxv frqvhtxhqw srxu oh whvw", cipheredMessage);
    }

    @Test
    public void testDechiffrementCesar() {
        ChiffrementCesar cipher = new ChiffrementCesar();
        String cipheredMessage = cipher.decrypter("fhfl hvw xq whvw gh fkliiuhphqw soxv frqvhtxhqw srxu oh whvw", 3);
        assertEquals("ceci est un test de chiffrement plus consequent pour le test", cipheredMessage);
    }

    @Test
    public void testDechiffrementCesarSansDecalage() {
        int offset = new ChiffrementCesar().breakCipher("fhfl hvw xq whvw gh fkliiuhphqw soxv frqvhtxhqw srxu oh whvw");
        assertEquals(offset, 3);

        assertEquals(new ChiffrementCesar().decrypter("fhfl hvw xq whvw gh fkliiuhphqw soxv frqvhtxhqw srxu oh whvw", offset),
                "ceci est un test de chiffrement plus consequent pour le test");
    }

}
