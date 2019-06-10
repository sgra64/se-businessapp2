package com.businessapp.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.businessapp.logic.IDGenerator;


/**
 * Article is an Entity-class that represents an item for rent or for sale.
 * 
 * @author Sven Graupner
 * 
 */

@Entity
@Table(name = "Article")
public class Article implements EntityIntf {
	private static final long serialVersionUID = 1L;

	private static final Locale locale  = new Locale( "de" );
	private static final DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance( locale );

	static {
		decimalFormat.applyPattern( "#,###.00" );
	}

	/*
	 * Properties.
	 */
	@Id
	@Column(name ="id")
	private final String id;	// Unique, non-null Article id.

	@Column(name ="name")
	private String name;		// Article name.

	@Column(name ="price")
	private long price;			// Article price [cent].

	/**
	 * Private default constructor (required by JSON deserialization).
	 */
	@SuppressWarnings("unused")
	private Article() { this( null, null, null ); }

	/**
	 * Public constructor.
	 * @param name Article name.
	 */
	public Article( String name, String price ) {
		this( null, name, price );
	}

	/**
	 * Public constructor.
	 * @param id Article id. If null, a new id is generated.
	 * @param name Article name.
	 */
	private static final IDGenerator IDG = new IDGenerator( null, IDGenerator.IDTYPE.NUM, 8 );
	//
	public Article( String id, String name, String price ) {
		this.id = id==null? IDG.nextId() : id;
		this.name = name;
		setPrice( price );
	}


	/*
	 * Public getter/setter methods.
	 */

	/**
	 * Return Article id.
	 * @return Article id.
	 */
	public String getId() {		// No setId(). Id's cannot be altered.
		return id;
	}

	/**
	 * Return Article name.
	 * @return Article name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set Article name.
	 * @param name Article name.
	 */
	public void setName( String name ) {
		this.name = name;
	}


	/**
	 * Return Article price.
	 * @return Article price.
	 */
	public String getPrice() {
		String numberAsString = decimalFormat.format( (double)this.price / 100.0 );
		return numberAsString + " EUR";
	}

	/**
	 * Set Article price.
	 * @param name Article price.
	 */
	public void setPrice( String priceAsStr ) {
		this.price = 0;
		if( priceAsStr != null ) {
			// split numbers, e.g. "1.899,00" from other Strings such as "EUR"
			for( String sp : priceAsStr.split( "[^0-9,.]" ) ) {
				if( sp.length() > 0 ) {
					try {
						double dbl = decimalFormat.parse( sp ).doubleValue() * 100.0;
						this.price = (long)dbl;
						return;

					} catch( ParseException ex2 ) {
					}
				}
			}
		}
	}

}
