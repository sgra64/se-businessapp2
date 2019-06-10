package com.businessapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.businessapp.logic.IDGenerator;


/**
 * Customer is an Entity-class of someone with whom a business relationship
 * is maintained.
 * 
 * @author Sven Graupner
 * 
 */

@Entity
@Table(name = "Customer")
public class Customer implements EntityIntf {
	private static final long serialVersionUID = 1L;

	/*
	 * Properties.
	 */
	@Id
	@Column(name ="id")
	private final String id;	// Unique, non-null Customer id.

	@Column(name ="name")
	private String name;		// Customer name.

	@Column(name="contacts")
	@Convert(converter = StringListConverter.class)		// map List<String> to single, ';'-separated String
	private final List<String> contacts = new ArrayList<String>();

	@Transient
	private final List<Note> notes = new ArrayList<Note>();

    public enum CustomerStatus { ACTIVE, SUSPENDED, TERMINATED };

	@Column(name="status")
	private CustomerStatus status;


	/**
	 * Private default constructor (required by JSON deserialization).
	 */
	@SuppressWarnings("unused")
	private Customer() { this( null ); }

	/**
	 * Public constructor.
	 * @param name Customer name.
	 */
	public Customer( String name ) {
		this( null, name );
	}

	/**
	 * Public constructor.
	 * @param id Customer id. If null, a new id is generated.
	 * @param name Customer name.
	 */
	private static final IDGenerator IDG = new IDGenerator( "C.", IDGenerator.IDTYPE.AIRLINE, 6 );
	//
	public Customer( String id, String name ) {
		this.id = id==null? IDG.nextId() : id;
		setName( name );
		this.addNote( new Note( this, "Customer record created." ) );
		this.status = CustomerStatus.ACTIVE;
	}


	/*
	 * Public getter/setter methods.
	 */

	/**
	 * Return Customer id.
	 * @return Customer id.
	 */
	public String getId() {		// No setId(). Id's cannot be altered.
		return id;
	}

	/**
	 * Return Customer name.
	 * @return Customer name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set Customer name.
	 * @param name Customer name.
	 */
	public void setName( String name ) {
		this.name = "";
		if( name != null && name.length() > 0 ) {
			if( name.contains( "," ) ) {
				this.name = name;

			} else {
				// reverse name order to show last name first
				StringBuffer sb = new StringBuffer();
				String[] sp = name.split( "[ \t]" );
				int parts = sp.length;
				if( parts > 0 ) {
					sb.append( sp[ --parts ] );
					for( int i = 0; i < parts; i++ ) {
						sb.append( i==0? ", " : " " ).append( sp[i] );
					}
				}
				this.name = sb.toString();
			}
		}
	}

	/**
	 * Return list of Customer contacts.
	 * @return list of Customer contacts.
	 */
	public List<String> getContacts() {
		return contacts;
	}

	/**
	 * Return list of notes stored for a Customer.
	 * @return list of notes.
	 */
	public List<Note> getNotes() {
		return notes;
	}

	/**
	 * Add new note entry.
	 * @param note new Note entry
	 */
	public void addNote( Note note ) {
		notes.add( note );
	}

	/**
	 * Return Customer status.
	 * @return Customer status.
	 */
	public CustomerStatus getStatus() {
		return status;
	}

	/**
	 * Set Customer status.
	 * @param status Customer status.
	 */
	public void setStatus( CustomerStatus status ) {
		this.status = status;
	}

}
