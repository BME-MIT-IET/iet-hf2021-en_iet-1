package tr.com.srdc.ontmalizer.helper.test;

import static org.junit.Assert.*;

import java.util.Locale;

import org.junit.AfterClass;
import org.junit.Test;

import tr.com.srdc.ontmalizer.helper.NamingUtil;

/**
 * @author rahmivolkan
 */
public class NamingUtilTest {
    private static Locale defaultLocale = Locale.getDefault();
    
	@Test
	public void testCreatePropertyName() {
		final String prefix = "pre";
		final String propName = "problemDate";

		// with no prefix
		assertEquals(propName, NamingUtil.createPropertyName(null, propName));
		assertEquals(propName, NamingUtil.createPropertyName("", propName));
		
		// with prefix
		assertEquals("preProblemDate", NamingUtil.createPropertyName(prefix, propName));
	}
	
	@Test
	public void testCreatePropertyLongName() {
		final String prefix = "name very very long prefix namevery ery very long prefix namevery ery very long prefix namevery ery very long prefix namevery ery very long prefix nameveryery very long prefix nameveryery very long prefix nameveryery very long prefix nameveryery very long prefix nameveryery very long prefix namevery ery very long prefix namevery v ery very long prefix namevery ery very long prefix namevery very long prefix namevery very long prefix namevery very long prefix namevery very long prefix namevery very long prefix namevery very long prefix namevery very long prefix namevery very long prefix namevery very long prefix namevery very long prefix namevery very long prefix name";
		final String propName = "Namelong property namelong property namelong property Namelong property namelong property namelong property Namelong property namelong property namelong property Namelong property namelong property namelong property Namelong property namelong property namelong propertyNamelong property namelong property namelong property Namelong property namelong property namelong property Namelong property namelong property namelong property Namelong property namelong property namelong propertyNamelong property namelong property namelong propertyNamelong property namelong property namelong propertyNamelong property namelong property namelong property Namelong property namelong property namelong propertyNamelong property namelong property namelong propertyNamelong property namelong property namelong propertyNamelong property namelong property namelong propertyNamelong property namelong property namelong property  namelong property namelong property namelong property namelong property namelong property namelong property namelong property namelong property namelong property namelong property name long property namelong property namelong property namelong property namelong property namelong property name long property namevlong property name long property name";
		
		// with prefix
		assertEquals(prefix+propName, NamingUtil.createPropertyName(prefix, propName));
	}
	
	@Test
	public void testCreatePropertyFirstLetterModification() {
		final String prefix = "pre";
		final String propName = "property";

		// with prefix
		assertNotEquals("preproperty", NamingUtil.createPropertyName(prefix, propName));
		assertEquals("preProperty", NamingUtil.createPropertyName(prefix, propName));
	}
	
	@Test
	public void testCreatePropertyNameForDifferentLocales() {
		// Danish locale
		Locale.setDefault(new Locale("dk", "DK"));
		assertEquals("preÆtesting", NamingUtil.createPropertyName("pre", "ætesting"));
		assertEquals("preTesting", NamingUtil.createPropertyName("pre", "testing"));
		
		// Turkish locale (for i)
		Locale.setDefault(new Locale("tr", "TR"));
		assertEquals("preIlaç", NamingUtil.createPropertyName("pre", "ilaç"));

		assertEquals("preIlac", NamingUtil.createPropertyName("pre", "ilac"));
		
		// Arabic locale
		Locale.setDefault(new Locale("ar", "AR"));
		assertEquals("أولي", NamingUtil.createPropertyName("أو", "لي"));
		assertEquals("preTesting", NamingUtil.createPropertyName("pre", "testing"));
		
		// Persian locale
		Locale.setDefault(new Locale("ar", "AR"));
		assertEquals("پپسی", NamingUtil.createPropertyName("پپ", "سی"));
		assertEquals("preTesting", NamingUtil.createPropertyName("pre", "testing"));
	}

    @AfterClass
    public static void restoreLocale() {
        Locale.setDefault(defaultLocale);
    }

}
