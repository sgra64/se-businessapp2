package com.businessapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


/**
 * Helper class to convert between List<String> and one SPLIT_CHAR-separated String.
 * Class is used to map List<String> contacts attribute in Customer.java
 * into a single String column in CUSTOMER table rather than @OneToMany.
 * 
 * Source:
 * https://stackoverflow.com/questions/287201/how-to-persist-a-property-of-type-liststring-in-jpa
 * 
 * @author Sven Graupner
 *
 */
@Converter 
public class StringListConverter implements AttributeConverter<List<String>, String> {
	private static final String SPLIT_CHAR = ";";
	
	@Override
	public String convertToDatabaseColumn( List<String> stringList ) {
		return String.join( SPLIT_CHAR, stringList );
	}
	
	@Override
	public List<String> convertToEntityAttribute( String string ) {
		List<String> list = new ArrayList<>();
		if( string != null ) {
			for( String split : string.split( SPLIT_CHAR ) ) {
				list.add( split );
			}
		}
		return list;
		// does not allow list manipulations such as clear() - throws UnsupportedOperationException
		//return Arrays.asList( string.split( SPLIT_CHAR ) );
	}
}
